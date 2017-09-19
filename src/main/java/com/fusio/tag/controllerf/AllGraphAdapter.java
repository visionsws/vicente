package com.fusio.tag.controllerf;

import java.util.ArrayList;
import java.util.List;

import com.fusio.tag.commons.tools.DataImporter;
import com.fusio.tag.vo.AllGraph;

public class AllGraphAdapter {
	
	
	
	public static AllGraphVo adapter(AllGraph tagGraph, AllGraphVo allGraphVo) {
		if (allGraphVo == null) {
			allGraphVo = new AllGraphVo("全部", tagGraph.getLevel());
		}

		List<AllGraph> subNodes = tagGraph.getSubNodes();
		List<AllGraphVo> children = new ArrayList<>();

		
		for (AllGraph sub : subNodes) {
			AllGraphVo vo = null;
			
			// 由于tags.level的级别是去除了catg之后从1开始的，所以如果画图要把Category也当成tag来展示的话，要加上一个固定值，即多少级别定义为catg
			if (sub.isTag()) {
				vo = new AllGraphVo(sub.getCurrentTag().getName(), sub.getCurrentTag().getLevel() + DataImporter.HOW_MANY_CATG);
			} else {
				vo = new AllGraphVo(sub.getCurrentCatg().getName(), sub.getCurrentCatg().getLevel());// 
			}
			adapter(sub, vo);
			
			// 
			children.add(vo);
		}
		allGraphVo.setChildren(children.isEmpty() ? null : children);
		return allGraphVo;
	}

	public static class AllGraphVo {
		private String name;
		private Integer level;
		private Boolean collapsed;
		private List<AllGraphVo> children;

		public AllGraphVo() {
			super();
		}

		public AllGraphVo(String name, Integer level) {
			super();
			this.name = name;
			this.level = level;
			this.collapsed = level >= 2;
		}

		public AllGraphVo(String name, Integer level, Boolean collapsed, List<AllGraphVo> children) {
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

		public List<AllGraphVo> getChildren() {
			return children;
		}

		public void setChildren(List<AllGraphVo> children) {
			this.children = children;
		}
	}
}
