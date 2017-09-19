package com.fusio.tag.vo;

import java.util.ArrayList;
import java.util.List;

import com.fusio.tag.model.autogen.Catg;
import com.fusio.tag.model.autogen.Tags;

public class AllGraph {
	int level;
	Catg currentCatg;
	Tags currentTag;
	boolean isTag;// 
	List<AllGraph> subNodes = new ArrayList<>();
	
	
	public AllGraph() {
		super();
	}
	
	
	
	
	
	public AllGraph(int level, boolean isTag) {
		super();
		this.level = level;
		this.isTag = isTag;
	}





	public AllGraph(int level, Catg currentCatg) {
		super();
		this.level = level;
		this.currentCatg = currentCatg;
		this.isTag = false;
	}


	public AllGraph(int level, Tags currentTag) {
		super();
		this.level = level;
		this.currentTag = currentTag;
		this.isTag = true;
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
	public Tags getCurrentTag() {
		return currentTag;
	}
	public void setCurrentTag(Tags currentTag) {
		this.currentTag = currentTag;
	}
	public boolean isTag() {
		return isTag;
	}
	public void setTag(boolean isTag) {
		this.isTag = isTag;
	}
	public List<AllGraph> getSubNodes() {
		return subNodes;
	}
	public void setSubNodes(List<AllGraph> subNodes) {
		this.subNodes = subNodes;
	}
}