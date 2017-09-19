package com.fusio.tag.service;

import java.util.List;

import com.fusio.tag.model.autogen.TgCatg;
import com.fusio.tag.model.autogen.TgTag;
import com.fusio.tag.service.impl.TgTagService.TagGraph;
import com.fusio.tag.vo.TgIdOrCode;

public interface TgTagServiceI {
	// 查询直接父节点,没有的时候返回null
	TgTag qryDirectParentTag(TgIdOrCode tgIdOrCode);
	
	// 查询所有父节点,返回值顺序是0->最近,1->稍远...
	List<TgTag> qryAllParentTags(TgIdOrCode tgIdOrCode);
	
	// 查询某个标签所属的分类,tagId类型必须是标签类型,否则返回null
	TgCatg qryCatgBelongedTo(TgIdOrCode tgIdOrCode);
	
	// 查询某个标签下的所有子标签,不允许传入
	List<TgTag> qryAllChildrenTags(TgIdOrCode tgIdOrCode);
	
	// 查询所有的树
	TagGraph qryTagGraph();
	
}