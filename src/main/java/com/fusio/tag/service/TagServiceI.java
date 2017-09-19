package com.fusio.tag.service;

import java.util.List;

import com.fusio.tag.model.autogen.Catg;
import com.fusio.tag.model.autogen.Tags;
import com.fusio.tag.vo.IdOrCode;
import com.fusio.tag.vo.TagGraph;

public interface TagServiceI {

	// 查询Tag信息
	Tags qryTagInfo(IdOrCode idOrCode);
	
	// 查出所有的标签
	List<Tags> qryAllTagInfo();

	// 查询直接父节点,没有的时候返回null
	Tags qryDirectParentTag(IdOrCode idOrCode);

	// 查询所有父节点,返回值顺序是0->最近,1->稍远...
	List<Tags> qryAllParentTags(IdOrCode idOrCode);

	// 查询某个标签所属的分类,tagId类型必须是标签类型,否则返回null
	Catg qryCatgBelongedTo(IdOrCode idOrCode);

	// 查询某个标签下的所有子标签,不允许传入
	List<Tags> qryAllChildrenTags(IdOrCode idOrCode);

	// 查询全graph图
	TagGraph qryTagGraph();

	// 查询所有分类
	List<Catg> qryAllCatg();
	
	//获取标签信息信息
	List<Tags> qryAllCatgs();
	List<Tags> qryTagsByCatgId(String catgId);
	List<Tags> qryTagsByTagId(String tagId);
	
	//根据Id获取标签详情
	String qryTagsByIds(String ids);
	
	List<Tags> qryTagsByName(String name);
	
	Tags qryExactTagsByName(String name);
	
	Tags qrySingleTag(String tagId);
	
	List<Tags> qryMultipleTags(String tagIds);
}