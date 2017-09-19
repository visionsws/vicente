package com.fusio.tag.vo;

import java.util.ArrayList;
import java.util.List;

import com.fusio.tag.model.autogen.Catg;

public class CatgGraph {
	int level;
	Catg currentCatg;
	List<CatgGraph> subNodes = new ArrayList<>();

	public CatgGraph() {
		super();
	}

	public CatgGraph(Catg currentTag) {
		super();
		this.level = currentTag == null ? 0 : currentTag.getLevel();
		this.currentCatg = currentTag;
	}

	public CatgGraph(Catg currentTag, List<CatgGraph> subNodes) {
		this(currentTag);
		this.subNodes = subNodes;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Catg getCurrentCatg() {
		return currentCatg;
	}

	public void setCurrentCatg(Catg currentCatg) {
		this.currentCatg = currentCatg;
	}

	public List<CatgGraph> getSubNodes() {
		return subNodes;
	}

	public void setSubNodes(List<CatgGraph> subNodes) {
		this.subNodes = subNodes;
	}

}