package com.fusio.tag.vo;

import java.util.ArrayList;
import java.util.List;

import com.fusio.tag.model.autogen.Tags;

public class TagGraph {
	int level;
	Tags currentTag;
	List<TagGraph> subNodes = new ArrayList<>();

	public TagGraph() {
		super();
	}

	public TagGraph(Tags currentTag) {
		super();
		this.level = currentTag == null ? 0 : currentTag.getLevel();
		this.currentTag = currentTag;
	}

	public TagGraph(Tags currentTag, List<TagGraph> subNodes) {
		this(currentTag);
		this.subNodes = subNodes;
	}

	public int getLevel() {
		return level;
	}

	public Tags getCurrentTag() {
		return currentTag;
	}

	public void setCurrentTag(Tags currentTag) {
		this.currentTag = currentTag;
	}

	public List<TagGraph> getSubNodes() {
		return subNodes;
	}

	public void setSubNodes(List<TagGraph> subNodes) {
		this.subNodes = subNodes;
	}

}