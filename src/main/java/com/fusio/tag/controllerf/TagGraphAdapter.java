package com.fusio.tag.controllerf;

import java.util.ArrayList;
import java.util.List;

import com.fusio.tag.model.autogen.Tags;
import com.fusio.tag.vo.TagGraph;

public class TagGraphAdapter {

	public static TagGraphVo adapter(TagGraph tagGraph, TagGraphVo tagGraphVo) {
		if (tagGraphVo == null) {
			tagGraphVo = new TagGraphVo("全部", tagGraph.getLevel());
		}

		List<TagGraph> subNodes = tagGraph.getSubNodes();
		List<TagGraphVo> children = new ArrayList<>();

		
		for (TagGraph sub : subNodes) {
			TagGraphVo vo = new TagGraphVo(sub.getCurrentTag().getName(), sub.getCurrentTag().getLevel());
			adapter(sub, vo);
			
			// 
			children.add(vo);
		}
		tagGraphVo.setChildren(children.isEmpty() ? null : children);
		return tagGraphVo;
	}

	public static class TagGraphVo {
		private String name;
		private Integer level;
		private Boolean collapsed;
		private List<TagGraphVo> children;

		public TagGraphVo() {
			super();
		}

		public TagGraphVo(String name, Integer level) {
			super();
			this.name = name;
			this.level = level;
			this.collapsed = level >= 2;
		}

		public TagGraphVo(String name, Integer level, Boolean collapsed, List<TagGraphVo> children) {
			super();
			this.name = name;
			this.level = level;
			this.collapsed = collapsed;
			this.children = children;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Integer getLevel() {
			return level;
		}

		public void setLevel(Integer level) {
			this.level = level;
		}

		public Boolean getCollapsed() {
			return collapsed;
		}

		public void setCollapsed(Boolean collapsed) {
			this.collapsed = collapsed;
		}

		public List<TagGraphVo> getChildren() {
			return children;
		}

		public void setChildren(List<TagGraphVo> children) {
			this.children = children;
		}
	}
}
