package com.fusio.tag.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fusio.tag.commons.tools.DataImporter;
import com.fusio.tag.commons.tools.DataImporter.ReadResult;
import com.fusio.tag.commons.utils.DateUtil;
import com.fusio.tag.commons.utils.JsonUtil;
import com.fusio.tag.dao.autogen.CatgMapper;
import com.fusio.tag.dao.autogen.TagsMapper;
import com.fusio.tag.dao.custom.ExtCatgMapper;
import com.fusio.tag.dao.custom.ExtTagsMapper;
import com.fusio.tag.model.autogen.Catg;
import com.fusio.tag.model.autogen.CatgExample;
import com.fusio.tag.model.autogen.Tags;
import com.fusio.tag.model.autogen.TagsExample;
import com.fusio.tag.service.DataImportServiceI;

@Service
@Transactional
public class DataImportService extends BaseService<DataImportService>implements DataImportServiceI {

	// 批量插入的时候把config.properties的switch.mybatisDebugSql改成0，不打印sql，否则会很慢
	@Override
	public void saveCatgAndTags(String sourceFilePath, boolean isRealInsert/*传false的话可以先预览即将的改变*/) {
		try {
			// String sourceFilePath = "d:/标签待入库.txt";
			String destFilePath = sourceFilePath + "_" + DateUtil.getCurTimestampStr() + "_去重和补全";
			DataImporter.wellData(sourceFilePath, destFilePath);

			// 从格式良好的文件读取
			ReadResult r = DataImporter.readData(destFilePath);

			CatgMapper cmapper = daoUtil.getMapper(CatgMapper.class);
			TagsMapper tmapper = daoUtil.getMapper(TagsMapper.class);
			ExtCatgMapper extCMapper = daoUtil.getMapper(ExtCatgMapper.class);
			ExtTagsMapper extTMapper = daoUtil.getMapper(ExtTagsMapper.class);

			System.out.println("================ 插入开始 =================");

			// 本次读取文件，需要导入的(但未去除数据库已有的)
			List<Catg> catgList = r.getCatgList();
			List<Tags> tagList = r.getTagList();

			
			List<String> catgFullNameList = new ArrayList<>(catgList.size());
			for (Catg c : catgList) {
				catgFullNameList.add(c.getFullName());
			}
			List<String> tagFullNameList = new ArrayList<>(tagList.size());
			for (Tags t : tagList) {
				tagFullNameList.add(t.getFullName());
			}
			
			// 去掉已经在库里有数据的
			CatgExample catgExam = new CatgExample();
			catgExam.createCriteria().andFullNameIn(catgFullNameList);
			List<Catg> inCatg = daoUtil.catgMapper.selectByExample(catgExam);// 此次要导入的数据，在库里已有的分类
			
			TagsExample tagExam = new TagsExample();
			tagExam.createCriteria().andFullNameIn(tagFullNameList);
			List<Tags> inTag = daoUtil.tagsMapper.selectByExample(tagExam);// 此次要导入的数据，在库里已有的标签
			
			
			List<String> catgInDb = getCatgFullNameList(inCatg);// 要导入的数据中，在db中也存在的部分。
			List<String> tagInDb = getTagFullNameList(inTag);
			
			// catgList-
			List<Catg> newCatgList = new ArrayList<>();
			for (Catg c : catgList) {
				if (!catgInDb.contains(c.getFullName())) {
					newCatgList.add(c);
				}
			}
			
			// 过滤出本次需要插入的数据
			List<Tags> newTagList = new ArrayList<>();
			for (Tags t : tagList) {
				if (!tagInDb.contains(t.getFullName())) {
					newTagList.add(t);
				}
			}
			
			
			long time1 = System.currentTimeMillis();
			if (newCatgList != null && newCatgList.size() > 0) {
//				for (Catg catg : newCatgList) {
//					cmapper.insertSelective(catg);
//				}
				try {
					if (isRealInsert) {
						extCMapper.insertCatgBatch(newCatgList);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			long time2 = System.currentTimeMillis();

			if (newTagList != null && newTagList.size() > 0) {
//				for (Tags tags : newTagList) {
//					try {
//						tmapper.insertSelective(tags);
//					} catch (Exception e) {
//						System.out.println("出错--->"+tags.getFullName());
//						e.printStackTrace();
//					}
//				}
				try {
					if (isRealInsert) {
						extTMapper.insertTagsBatch(newTagList);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			long time3 = System.currentTimeMillis();
			
			String lo = "分类本需要插入[%s]条，过滤后[%s]条，标签本需要插入[%s]条，过滤后[%s]条";
			String log = String.format(lo, catgList.size(), newCatgList.size(), tagList.size(), newTagList.size());
			System.out.println(log);
			System.out.println(JsonUtil.toJSONString(newCatgList));
			System.out.println(JsonUtil.toJSONString(newTagList));
			
			// 输出到某个文件
			StringBuilder catgFile = new StringBuilder();
			StringBuilder tagFile = new StringBuilder();
			for (Catg catg : newCatgList) {
				catgFile.append(catg.getFullName()).append("\r\n");
			}
			for (Tags tag : newTagList) {
				tagFile.append(tag.getFullName()).append("\r\n");
			}
			OutputStream outputCatg = new FileOutputStream(new File(destFilePath + "_catg"));
			IOUtils.write(catgFile.toString(), outputCatg);
			OutputStream outputTag = new FileOutputStream(new File(destFilePath + "_tag"));
			IOUtils.write(tagFile.toString(), outputTag);
			
			System.out.println("插入catg的条数和耗费时间:" + newCatgList.size() + ",毫秒:" + (time2 - time1));
			System.out.println("插入tag的条数和耗费时间:" + newTagList.size() + ",毫秒:" + (time3 - time2));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	// 维护tags.pid,可重复执行
	@Override
	public void maintainTag_pid() {
		TagsMapper tmapper = daoUtil.getMapper(TagsMapper.class);
		CatgMapper cmapper = daoUtil.getMapper(CatgMapper.class);

		TagsExample example = new TagsExample();
		example.createCriteria().andPidEqualTo(DataImporter.placeholder);
		List<Tags> tagList = tmapper.selectByExample(example);
		for (Tags tag : tagList) {
			String fullName = tag.getFullName();

			String pid = "";
			if (DataImporter.isTagRoot(fullName)) {// 判断该tag是否是root_tag
				pid = "ROOT";
			} else {
				String parentPath = DataImporter.getParentPath(fullName);
				TagsExample ex = new TagsExample();
				ex.createCriteria().andFullNameEqualTo(parentPath);
				List<Tags> l = tmapper.selectByExample(ex);
				if (l.isEmpty() || l.size() > 1) {
					throw new RuntimeException("找不到父类,size:" + l.size());
				}

				pid = l.get(0).getTagId();

			}
			Tags newValue = new Tags();
			newValue.setTagId(tag.getTagId());
			newValue.setPid(pid);
			tmapper.updateByPrimaryKeySelective(newValue);
		}
	}

	// 维护tags.catg_id,可重复执行
	@Override
	public void maintainTag_CatgId() {
		TagsMapper tmapper = daoUtil.getMapper(TagsMapper.class);
		CatgMapper cmapper = daoUtil.getMapper(CatgMapper.class);

		TagsExample example = new TagsExample();
		example.createCriteria().andCatgIdEqualTo(DataImporter.placeholder);
		List<Tags> list = tmapper.selectByExample(example);
		for (Tags tag : list) {
			String fullName = tag.getFullName();
			String catgFullName = DataImporter.getCatgNameByTagFullName(fullName);
			CatgExample ex = new CatgExample();
			ex.createCriteria().andFullNameEqualTo(catgFullName);
			List<Catg> l = cmapper.selectByExample(ex);
			if (l.isEmpty() || l.size() > 1) {
				throw new RuntimeException("找不到归类,tagId:" + tag.getTagId() + ",size:" + l.size());
			}

			String catgId = l.get(0).getCatgId();

			Tags newValue = new Tags();
			newValue.setTagId(tag.getTagId());
			newValue.setCatgId(catgId);
			tmapper.updateByPrimaryKeySelective(newValue);
		}
	}

	// 维护catg.pid,可重复执行
	@Override
	public void maintainCatg_pid() {
		TagsMapper tmapper = daoUtil.getMapper(TagsMapper.class);
		CatgMapper cmapper = daoUtil.getMapper(CatgMapper.class);

		CatgExample example = new CatgExample();
		example.createCriteria().andPidEqualTo(DataImporter.placeholder);
		List<Catg> catgList = cmapper.selectByExample(example);
		for (Catg catg : catgList) {
			String fullName = catg.getFullName();
			String parentPath = DataImporter.getParentPath(fullName);

			String pid = "";
			if (parentPath.equals("/")) {
				pid = "ROOT";
			} else {
				CatgExample ex = new CatgExample();
				ex.createCriteria().andFullNameEqualTo(parentPath);
				List<Catg> l = cmapper.selectByExample(ex);
				if (l.isEmpty() || l.size() > 1) {
					throw new RuntimeException("找不到父类,size:" + l.size());
				}

				pid = l.get(0).getCatgId();

			}
			Catg newValue = new Catg();
			newValue.setCatgId(catg.getCatgId());
			newValue.setPid(pid);
			cmapper.updateByPrimaryKeySelective(newValue);
		}

	}

	// 维护tags.full_id,可重复执行【该字段没那么重要，暂时未完成接口的逻辑】
	@Override
	public void maintainTag_fullId() {
		TagsMapper tmapper = daoUtil.getMapper(TagsMapper.class);
		CatgMapper cmapper = daoUtil.getMapper(CatgMapper.class);

		TagsExample example = new TagsExample();
		List<Tags> list = tmapper.selectByExample(example);

		Map<String, String> map = new HashMap<>();
		for (Tags tag : list) {
			map.put(tag.getFullName(), tag.getTagId());
		}

		// 更新
		for (Tags tag : list) {
			String tagId = tag.getTagId();
			String fullName = tag.getFullName();

		}

	}

	// 维护catg.full_id,可重复执行【该字段没那么重要，暂时未完成接口的逻辑】

	@Override
	public void maintainCatg_fullId() {
		// TODO Auto-generated method stub

	}

	

	private static List<String> getCatgFullNameList(List<Catg> inCatg) {
		if (inCatg == null) {
			return null;
		}
		List<String> result = new ArrayList<>(inCatg.size());
		for (Catg catg : inCatg) {
			result.add(catg.getFullName());
		}
		return result;
	}
	
	private static List<String> getTagFullNameList(List<Tags> inTags) {
		if (inTags == null) {
			return null;
		}
		List<String> result = new ArrayList<>(inTags.size());
		for (Tags t : inTags) {
			result.add(t.getFullName());
		}
		return result;
	}
}