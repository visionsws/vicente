package com.fusio.tag.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fusio.tag.model.autogen.TgCatg;
import com.fusio.tag.model.autogen.TgTag;
import com.fusio.tag.model.autogen.TgTagExample;
import com.fusio.tag.service.TgTagServiceI;
import com.fusio.tag.service.cmm.CommonServiceI;
import com.fusio.tag.vo.TgIdOrCode;

@Service
@Transactional
public class TgTagService extends BaseService<TgTagService>implements TgTagServiceI {
	@Resource
	private CommonServiceI commonServiceI;

	@Override
	public TgTag qryDirectParentTag(TgIdOrCode tgIdOrCode) {
		int id = trans2id(tgIdOrCode);
		
		TgTag tag = daoUtil.tgTagMapper.selectByPrimaryKey(id);

		return tag == null ? null : daoUtil.tgTagMapper.selectByPrimaryKey(tag.getParentId());
	}

	// 不包括自身,由近到远
	@Override
	public List<TgTag> qryAllParentTags(TgIdOrCode tgIdOrCode) {
		int id = trans2id(tgIdOrCode);

		TgTag tag = daoUtil.tgTagMapper.selectByPrimaryKey(id);
		if (tag == null) {
			return new ArrayList<>(0);
		}
		
		// 装由近到远的记录,如0直接父节点,1远一点...
		List<TgTag> list = new ArrayList<>();
		TgTag parent  = tag;
		while (true) {
			parent = daoUtil.tgTagMapper.selectByPrimaryKey(parent.getParentId());
			if (parent == null) {
				break;
			}
			list.add(parent);
		}
		
		return list;
	}

	@Override
	public TgCatg qryCatgBelongedTo(TgIdOrCode tgIdOrCode) {
		int id = trans2id(tgIdOrCode);

		TgTag tag = daoUtil.tgTagMapper.selectByPrimaryKey(id);
		if (tag == null) {
			return null;
		}
		
		return daoUtil.tgCatgMapper.selectByPrimaryKey(tag.getCategoryId());
	}

	// 查出所有子,但没有分层归类
	@Override
	public List<TgTag> qryAllChildrenTags(TgIdOrCode tgIdOrCode) {
		int id = trans2id(tgIdOrCode);

		TgTag tag = daoUtil.tgTagMapper.selectByPrimaryKey(id);
		if (tag == null) {
			return new ArrayList<>(0);
		}
		
		// 不包括自身
		TgTagExample example = new TgTagExample();
		example.createCriteria().andPathLike(tag.getPath() + "%").andIdNotEqualTo(tag.getId());
		
		return daoUtil.tgTagMapper.selectByExample(example);
	}

	public Integer trans2id(TgIdOrCode tgIdOrCode) {
		if (tgIdOrCode == null //
				|| (tgIdOrCode.getId() == null //
				&& (tgIdOrCode.getCode() == null || tgIdOrCode.getCode().length() == 0))) {
			throw new RuntimeException("id或code必传其一");
		}
		
		return tgIdOrCode.getId() != null ?  tgIdOrCode.getId() : commonServiceI.getIdByTagCode(tgIdOrCode.getCode());
	}

	
	public static class TagGraph {
		int level;
		TagGraph currentNode;
		List<TagGraph> subNodes;
		
		public TagGraph() {
			super();
		}
		public TagGraph(int level, TagGraph currentNode, List<TagGraph> subNodes) {
			super();
			this.level = level;
			this.currentNode = currentNode;
			this.subNodes = subNodes;
		}
		public int getLevel() {
			return level;
		}
		public void setLevel(int level) {
			this.level = level;
		}
		public TagGraph getCurrentNode() {
			return currentNode;
		}
		public void setCurrentNode(TagGraph currentNode) {
			this.currentNode = currentNode;
		}
		public List<TagGraph> getSubNodes() {
			return subNodes;
		}
		public void setSubNodes(List<TagGraph> subNodes) {
			this.subNodes = subNodes;
		}
	}


	@Override
	public TagGraph qryTagGraph() {
		
		
		return null;
	}
}