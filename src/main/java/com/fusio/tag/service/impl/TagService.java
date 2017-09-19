package com.fusio.tag.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fusio.tag.commons.busi.KeyConst;
import com.fusio.tag.commons.tableenum.Tags_isLeaf;
import com.fusio.tag.commons.tableenum.Tags_status;
import com.fusio.tag.commons.utils.BeanUtilTool;
import com.fusio.tag.commons.utils.MyBatisParam;
import com.fusio.tag.model.autogen.Catg;
import com.fusio.tag.model.autogen.CatgExample;
import com.fusio.tag.model.autogen.TagGroupJh;
import com.fusio.tag.model.autogen.TagGroupJhExample;
import com.fusio.tag.model.autogen.TagJh;
import com.fusio.tag.model.autogen.TagJhExample;
import com.fusio.tag.model.autogen.Tags;
import com.fusio.tag.model.autogen.TagsExample;
import com.fusio.tag.service.TagServiceI;
import com.fusio.tag.service.cmm.CommonServiceI;
import com.fusio.tag.vo.IdOrCode;
import com.fusio.tag.vo.TagGraph;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

@Service
@Transactional
public class TagService extends BaseService<TagService>implements TagServiceI {

	@Resource
	private CommonServiceI commonServiceI;
	
	/*** cached 缓存最大数量 **/  
    private static final Integer TAG_CACHE_MAXIMUMSIZE = 2000;//  
    /*** 缓存项在给定时间内没有被写访问（创建或覆盖），则回收 **/  
    private static final Integer TAG_EXPIRE_AFTER_ACCESS_MINUTES = 300; 
    
    //根据IP和卡ID缓存
    private static Cache<String, List<Tags>> tagCache = CacheBuilder.newBuilder()
    		.recordStats()
			.maximumSize(TAG_CACHE_MAXIMUMSIZE)
			.expireAfterAccess(TAG_EXPIRE_AFTER_ACCESS_MINUTES, TimeUnit.MINUTES)
			.build();
    
    /*** cached 缓存最大数量 **/  
    private static final Integer SINGLE_TAG_CACHE_MAXIMUMSIZE = 2000;//  
    //根据IP和卡ID缓存
    private static Cache<String, Tags> singleTagCache = CacheBuilder.newBuilder()
    		.recordStats()
			.maximumSize(SINGLE_TAG_CACHE_MAXIMUMSIZE)
			.expireAfterAccess(TAG_EXPIRE_AFTER_ACCESS_MINUTES, TimeUnit.MINUTES)
			.build();
	
	
	
	public String checkAndTrans2tagId(IdOrCode idOrCode) {
		if (idOrCode == null || //
				((idOrCode.getId() == null || idOrCode.getId().length() == 0)//
						&& (idOrCode.getCode() == null || idOrCode.getCode().length() == 0))) {
			throw new RuntimeException("id或code必传其一");
		}
		return idOrCode.getId() != null ?  idOrCode.getId() : commonServiceI.getTagIdByTagCode(idOrCode.getCode());
	}
	
	@Override
	public Tags qryDirectParentTag(IdOrCode idOrCode) {
		String tagId = checkAndTrans2tagId(idOrCode);
		
		return daoUtil.extTagsMapper.qryDirectParentTag(MyBatisParam.create().put("tagId", tagId).get());
	}

	@Override
	public List<Tags> qryAllParentTags(IdOrCode idOrCode) {
		String tagId = checkAndTrans2tagId(idOrCode);
		List<Tags> result = new ArrayList<>();

		while (true) {
			Tags tag = daoUtil.extTagsMapper.qryDirectParentTag(MyBatisParam.create().put("tagId", tagId).get());
			if (tag != null) {
				result.add(tag);
				tagId = tag.getTagId();
			} else {
				break;
			}
		}
		return result;
	}

	@Override
	public Catg qryCatgBelongedTo(IdOrCode idOrCode) {
		String tagId = checkAndTrans2tagId(idOrCode);
		
		Tags tag = daoUtil.tagsMapper.selectByPrimaryKey(tagId);
		if (tag != null) {
			return daoUtil.catgMapper.selectByPrimaryKey(tag.getCatgId());
		}
		return null;
	}

	@Override
	public List<Tags> qryAllChildrenTags(IdOrCode idOrCode) {
		String tagId = checkAndTrans2tagId(idOrCode);
		Tags tag = daoUtil.tagsMapper.selectByPrimaryKey(tagId);
		
		if (tag != null) {
			TagsExample example = new TagsExample();
			example.createCriteria().andFullNameLike(tag.getFullName() + "%").andTagIdNotEqualTo(tagId);
			return daoUtil.tagsMapper.selectByExample(example);
		}
		
		return new ArrayList<>(0);
	}

	@Override
	public Tags qryTagInfo(IdOrCode idOrCode) {
		String tagId = checkAndTrans2tagId(idOrCode);
		
		return daoUtil.tagsMapper.selectByPrimaryKey(tagId);
	}

	@Override
	public TagGraph qryTagGraph() {
		
		// 从小到大排序了,如1,2,..
		List<Integer> levs = daoUtil.extTagsMapper.getTagAllLevel();
		
		LinkedHashMap<String, List<Tags>> map = new LinkedHashMap<>();// 要保证有顺序
		for (Integer level : levs) {
			TagsExample example = new TagsExample();
			example.createCriteria().andLevelEqualTo(level);
			List<Tags> tags = daoUtil.tagsMapper.selectByExample(example);
			map.put(level + "", tags);
		}
		
		
		// 构造树状结构
		TagGraph root = new TagGraph(null);
		fillValue(root, map);
		return root;
	}
	
	// 递归塞入每一层的数据
	public static void fillValue(TagGraph current, LinkedHashMap<String, List<Tags>> map) {
		if (map == null) {
			return;
		}
		if (current == null) {
			current = new TagGraph();// root
		}
		
		String key = current.getLevel() + 1 + "";// 当前的下一级加1
		List<Tags> subTags = map.get(key);
		if (subTags == null) {
			return;
		}
		
		for (Tags tag : subTags) {
			TagGraph next = new TagGraph(getNewTag(tag));
			String currentTagId = current.getCurrentTag() == null ? null : current.getCurrentTag().getTagId();
			if (current.getLevel()==0 || (next.getCurrentTag() != null && next.getCurrentTag().getPid().equals(currentTagId))) {
				current.getSubNodes().add(next);
			}
			
		}
		
		List<TagGraph> sub = current.getSubNodes();
		if (sub != null) {
			for (TagGraph tagGraph : sub) {
				fillValue(tagGraph, map);
			}
		}
	}
	
	public static Tags getNewTag(Tags tag) {
		Tags newTag = new Tags();
		newTag.setName(tag.getName());
		newTag.setFullName(tag.getFullName());
		newTag.setTagId(tag.getTagId());
		newTag.setLevel(tag.getLevel());
		newTag.setPid(tag.getPid());
		newTag.setCatgId(tag.getCatgId());
		return newTag;
		//return tag;
	}
	@Override
	public List<Tags> qryAllTagInfo() {
		TagsExample example = new TagsExample();
		return daoUtil.tagsMapper.selectByExample(example);
	}

	@Override
	public List<Catg> qryAllCatg() {
		CatgExample example = new CatgExample();
		
		return daoUtil.catgMapper.selectByExample(example);
	}
	
	@Override
	public List<Tags> qryAllCatgs() {
		String key = KeyConst.LIST_TAGS_BY_PID_+"all_catgs";
		List<Tags> redList = null;
		// 没有使用CacheLoader
		try {
			redList = tagCache.get(key, new Callable<List<Tags>>() {
		    @Override
		    public List<Tags> call() throws Exception {
		    	CatgExample example = new CatgExample();
				example.createCriteria().andStatusEqualTo(Tags_status.NORMAL.val);
				example.setOrderByClause("updated_at");
				List<Catg> list = daoUtil.catgMapper.selectByExample(example);
				List<Tags> tagslist = new ArrayList<Tags>();
				for(Catg catg:list){
					Tags tag = new Tags();
					tag.setTagId(catg.getCatgId());
					tag.setName(catg.getName());
					tag.setLevel(0);
					tag.setFullName(catg.getFullName());
					tag.setCatgId(catg.getCatgId());
					tag.setPid(catg.getPid());
					tag.setStatus(catg.getStatus());
					tag.setFullId(catg.getFullId());
					tag.setCreatedAt(catg.getCreatedAt());
					tag.setCreatedBy(catg.getCreatedBy());
					tag.setUpdatedAt(catg.getUpdatedAt());
					tag.setUpdatedBy(catg.getUpdatedBy());
					tag.setCode(catg.getCode());
					tag.setIsLeaf(Tags_isLeaf.NO.val);
					tagslist.add(tag);
				}
				return tagslist;
		    }
		  });
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return redList;
	}

	@Override
	public List<Tags> qryTagsByCatgId(String catgId) {
		this.validParamUtil.notBlank(catgId);
		String key = KeyConst.LIST_TAGS_BY_PID_+catgId;
		final String fiCatgId = catgId;
		List<Tags> redList = null;
		try {
			redList = tagCache.get(key, new Callable<List<Tags>>() {
		    @Override
		    public List<Tags> call() throws Exception {
		    	TagsExample example = new TagsExample();
				example.createCriteria().andCatgIdEqualTo(fiCatgId).andLevelEqualTo(1).andStatusEqualTo(Tags_status.NORMAL.val);
				List<Tags> list = daoUtil.tagsMapper.selectByExample(example);
				return list;
		    }
		  });
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return redList;
	}

	@Override
	public List<Tags> qryTagsByTagId(String tagId) {
		this.validParamUtil.notBlank(tagId);
		String key = KeyConst.LIST_TAGS_BY_PID_+tagId;
		final String fiTagId = tagId;
		List<Tags> redList = null;
		try {
			redList = tagCache.get(key, new Callable<List<Tags>>() {
		    @Override
		    public List<Tags> call() throws Exception {
		    	TagsExample example = new TagsExample();
				example.createCriteria().andPidEqualTo(fiTagId).andStatusEqualTo(Tags_status.NORMAL.val);
				List<Tags> list = daoUtil.tagsMapper.selectByExample(example);
				return list;
		    }
		  });
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return redList;
	}

	@Override
	public String qryTagsByIds(String ids) {
		this.validParamUtil.notBlank(ids);
		String[] tagIds = ids.split(",");
		StringBuffer buf = new StringBuffer();
		for (String tagId:tagIds) {
			String key = KeyConst.SINGLE_TAGS_BY_ID_+tagId;
			final String fiTagId = tagId;
			Tags tag  = null;
			try {
				tag  = singleTagCache.get(key, new Callable<Tags>() {
			    @Override
			    public Tags call() throws Exception {
			    	Tags nmTag = daoUtil.tagsMapper.selectByPrimaryKey(fiTagId);
					return nmTag;
			    }
			  });
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			if (tag != null) {
				buf.append(tag.getTagId()).append(":").append(tag.getName()).append(";");
			}
		}
		buf.deleteCharAt(buf.length()-1);
		return buf.toString();// 逗号分隔的中文
	}
	
	@Override
	public List<Tags> qryTagsByName(String name) {
		this.validParamUtil.notBlank(name);
		String likeName = "%"+name+"%";
		TagsExample example = new TagsExample();
		example.createCriteria().andNameLike(likeName).andStatusEqualTo(Tags_status.NORMAL.val);
		List<Tags> list = daoUtil.tagsMapper.selectByExample(example);
		
		return list;// 逗号分隔的中文
	}
	
	@Override
	public Tags qryExactTagsByName(String name) {
		Tags tag = null;
		this.validParamUtil.notBlank(name);
		TagsExample example = new TagsExample();
		example.createCriteria().andNameEqualTo(name).andStatusEqualTo(Tags_status.NORMAL.val);
		List<Tags> list = daoUtil.tagsMapper.selectByExample(example);
		if(list != null && !list.isEmpty()){
			tag = list.get(0);
		}
		return tag;
	}

	@Override
	public Tags qrySingleTag(String tagId) {
		this.validParamUtil.notBlank(tagId);
		String key = KeyConst.SINGLE_TAGS_BY_ID_+tagId;
		final String fiTagId = tagId;
		Tags tag  = null;
		try {
			tag  = singleTagCache.get(key, new Callable<Tags>() {
		    @Override
		    public Tags call() throws Exception {
		    	Tags nmTag = daoUtil.tagsMapper.selectByPrimaryKey(fiTagId);
				return nmTag;
		    }
		  });
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return tag;
	}
	
	@Override
	public List<Tags> qryMultipleTags(String ids) {
		this.validParamUtil.notBlank(ids);
		String[] tagIds = ids.split(",");
		List<Tags> list = new ArrayList<Tags>();
		for (String tagId:tagIds) {
			String key = KeyConst.SINGLE_TAGS_BY_ID_+tagId;
			final String fiTagId = tagId;
			Tags tag  = null;
			try {
				tag  = singleTagCache.get(key, new Callable<Tags>() {
			    @Override
			    public Tags call() throws Exception {
			    	Tags nmTag = daoUtil.tagsMapper.selectByPrimaryKey(fiTagId);
					return nmTag;
			    }
			  });
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			if (tag != null) {
				list.add(tag);
			}
		}
		return list;// 逗号分隔的中文
	}
	
}