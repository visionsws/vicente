package com.fusio.tag.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fusio.tag.model.autogen.Catg;
import com.fusio.tag.model.autogen.CatgExample;
import com.fusio.tag.model.autogen.Tags;
import com.fusio.tag.service.CatgServiceI;
import com.fusio.tag.service.TagServiceI;
import com.fusio.tag.vo.AllGraph;
import com.fusio.tag.vo.CatgGraph;
import com.fusio.tag.vo.TagGraph;

@Service
@Transactional
public class CatgService extends BaseService<CatgService>implements CatgServiceI {
	@Resource
	private TagServiceI tagServiceI;

	@Override
	public CatgGraph qryCatgGraph() {

		// 从小到大排序了,如1,2,..
		List<Integer> levs = daoUtil.extTagsMapper.getCatgAllLevel();

		LinkedHashMap<String, List<Catg>> map = new LinkedHashMap<>();// 要保证有顺序
		for (Integer level : levs) {
			CatgExample example = new CatgExample();
			example.createCriteria().andLevelEqualTo(level);
			List<Catg> tags = daoUtil.catgMapper.selectByExample(example);
			map.put(level + "", tags);
		}

		// 构造树状结构
		CatgGraph root = new CatgGraph(null);
		fillValue(root, map);
		return root;
	}

	// 递归塞入每一层的数据
	public static void fillValue(CatgGraph current, LinkedHashMap<String, List<Catg>> map) {
		if (map == null) {
			return;
		}
		if (current == null) {
			current = new CatgGraph();// root
		}

		String key = current.getLevel() + 1 + "";// 当前的下一级加1
		List<Catg> subCatgs = map.get(key);
		if (subCatgs == null) {
			return;
		}

		for (Catg tag : subCatgs) {
			CatgGraph next = new CatgGraph(getNewCatg(tag));
			String currentCatgId = current.getCurrentCatg() == null ? null : current.getCurrentCatg().getCatgId();
			if (current.getLevel() == 0 || (next.getCurrentCatg() != null && next.getCurrentCatg().getPid().equals(currentCatgId))) {
				current.getSubNodes().add(next);
			}

		}

		List<CatgGraph> sub = current.getSubNodes();
		if (sub != null) {
			for (CatgGraph catgGraph : sub) {
				fillValue(catgGraph, map);
			}
		}
	}

	public static Catg getNewCatg(Catg c) {
		// Catg newCatg = new Catg();
		// newCatg.setName(c.getName());
		// newCatg.setFullName(c.getFullName());
		// newCatg.setCatgId(c.getCatgId());
		// newCatg.setLevel(c.getLevel());
		// newCatg.setPid(c.getPid());
		// return newCatg;
		 return c;
	}

	@Override
	public AllGraph qryFullGraph() {
		CatgGraph catgGraph = qryCatgGraph();
		TagGraph tagGraph = tagServiceI.qryTagGraph();

		// 融合
		// int level// level 应该要两个相加起来。

		AllGraph allCatg = transCatgToAllGraph(catgGraph, null);
		AllGraph allTag = transTagToAllGraph(tagGraph, null);

		// 获得Category的叶子节点
		List<AllGraph> catgLeaves = getCatgLeaf(null, allCatg);
		
		// Tags的根节点挂到叶子节点上
		List<AllGraph> tagRoots = allTag.getSubNodes();

		for (AllGraph catgLeaf : catgLeaves) {
			Catg catg = catgLeaf.getCurrentCatg();
			String catgPk = catg.getCatgId();
			
			List<AllGraph> catgSubList = new ArrayList<>();
			for (AllGraph tagRoot : tagRoots) {
				String catgId = tagRoot.getCurrentTag().getCatgId();
				
				if (catgPk.equals(catgId)) {
					catgSubList.add(tagRoot);
				}
			}
			catgLeaf.setSubNodes(catgSubList);
		}
		
		return allCatg;
	}

	
	private AllGraph fusion(AllGraph catg, AllGraph tag) {
		if ( !( (catg.isTag() && !tag.isTag()) || (!catg.isTag() && tag.isTag()) ) ) {
			this.throwRuntimeEx("传参错误");
		}
		
		// 两个入参类型一样的，要求一个传Category一个传tag，顺序错了纠正
		if (catg.isTag()) {
			AllGraph tmp = null;
			tmp = catg;
			catg = tag;
			tag = tmp;
		}
		
		
		// 合并(将tag的结果融合到catg)
		
		
		
		return null;
	}
	
	// 获得所有的Category的叶子节点
	private List<AllGraph> getCatgLeaf(List<AllGraph> result, AllGraph catg) {
		if (result == null) {
			result = new ArrayList<>();
		}
		
		List<AllGraph> subList = catg.getSubNodes();
		if (subList != null && subList.size() > 0) {
			for (AllGraph a : subList) {
				getCatgLeaf(result, a);
			}
		} else {
			result.add(catg);
		}
		
		return result;
	}
	
	
	private AllGraph transTagToAllGraph(TagGraph tagGraph, AllGraph all) {

		if (all == null) {
			all = new AllGraph(0, true);
		}

		List<TagGraph> subNodes = tagGraph.getSubNodes();
		List<AllGraph> children = new ArrayList<>();

		if (subNodes != null && subNodes.size() > 0) {
			for (TagGraph sub : subNodes) {
				AllGraph vo = new AllGraph(sub.getCurrentTag().getLevel(), sub.getCurrentTag());

				transTagToAllGraph(sub, vo);

				children.add(vo);
			}
		}
		all.setSubNodes(children);

		return all;
	}

	private AllGraph transCatgToAllGraph(CatgGraph catgGraph, AllGraph all) {

		if (all == null) {
			all = new AllGraph(0, false);
		}

		List<CatgGraph> subNodes = catgGraph.getSubNodes();
		List<AllGraph> children = new ArrayList<>();

		if (subNodes != null && subNodes.size() > 0) {
			for (CatgGraph sub : subNodes) {
				AllGraph vo = new AllGraph(sub.getCurrentCatg().getLevel(), sub.getCurrentCatg());

				transCatgToAllGraph(sub, vo);

				children.add(vo);
			}
		}
		all.setSubNodes(children);

		return all;
	}

}