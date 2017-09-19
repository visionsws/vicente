package com.fusio.tag.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fusio.tag.service.TagServiceI;
import com.fusio.tag.vo.IdOrCode;

@RestController
public class TagController extends BaseController<TagController> {
	@Resource
	private TagServiceI tagServiceI;

	@ResponseBody
	@RequestMapping(value = "/tag/qryTagInfo")
	public Object qryTagInfo(//
			IdOrCode idOrCode//
	) {
		return returnResult(tagServiceI.qryTagInfo(idOrCode));
	}

	@ResponseBody
	@RequestMapping(value = "/tag/qryAllTagInfo")
	public Object qryAllTagInfo(//
	) {
		return returnResult(tagServiceI.qryAllTagInfo());
	}

	@ResponseBody
	@RequestMapping(value = "/tag/qryDirectParentTag")
	public Object qryDirectParentTag(//
			IdOrCode idOrCode//
	) {
		return returnResult(tagServiceI.qryDirectParentTag(idOrCode));
	}

	@ResponseBody
	@RequestMapping(value = "/tag/qryAllParentTags")
	public Object qryAllParentTags(//
			IdOrCode idOrCode//
	) {
		return returnResult(tagServiceI.qryAllParentTags(idOrCode));
	}

	@ResponseBody
	@RequestMapping(value = "/tag/qryCatgBelongedTo")
	public Object qryCatgBelongedTo(//
			IdOrCode idOrCode//
	) {
		return returnResult(tagServiceI.qryCatgBelongedTo(idOrCode));
	}

	@ResponseBody
	@RequestMapping(value = "/tag/qryAllChildrenTags")
	public Object qryAllChildrenTags(//
			IdOrCode idOrCode//
	) {
		return returnResult(tagServiceI.qryAllChildrenTags(idOrCode));
	}

	@ResponseBody
	@RequestMapping(value = "/tag/qryTagGraph")
	public Object qryTagGraph(//
	) {
		return returnResult(tagServiceI.qryTagGraph());
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/tag/qryAllCatg")
	public Object qryAllCatg(//
	) {
		return returnResult(tagServiceI.qryAllCatg());
	}
	
	
	/**
	 * 根据层级获取标签，可用于别的项目
	 * 1、FD项目创建群体
	 * 2、FM项目表单，选项关联标签
	 * @param pid
	 * @param level
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/tag/qryTagsByLevel")
	public Object qryTagsByLevel(//
			@RequestParam(required = false) String pid, //父ID
			@RequestParam(required = false,defaultValue="0") Integer level //等级
	) {
		if(level==0){
			return returnResult(tagServiceI.qryAllCatgs());
		}else if(level==1){
			return returnResult(tagServiceI.qryTagsByCatgId(pid));
		}else {
			return returnResult(tagServiceI.qryTagsByTagId(pid));
		}
	}
	
	/**
	 * 根据标签Id获取标签
	 * @param ids 标签id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/tag/qryTagsByIds")
	public Object qryTagsByIds(//
			@RequestParam(required = true) String ids 
	) {
		return returnResult(tagServiceI.qryTagsByIds(ids));
	}
	
	/**
	 * 根据标签名模糊查询标签信息
	 * @param name 标签名
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/tag/qryTagsByName")
	public Object qryTagsByName(//
			@RequestParam(required = true) String name 
	) {
		return returnResult(tagServiceI.qryTagsByName(name));
	}
	
	/**
	 * 根据标签名精确查询标签信息(自动打标签用到)
	 * @param name 标签名
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/tag/qryExactTagsByName")
	public Object qryExactTagsByName(//
			@RequestParam(required = true) String name 
	) {
		return returnResult(tagServiceI.qryExactTagsByName(name));
	}
	
	/**
	 * 根据标签Id获取标签
	 * @param ids 标签id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/tag/qrySingleTag")
	public Object qrySingleTag(//
			@RequestParam(required = true) String tagId 
	) {
		return returnResult(tagServiceI.qrySingleTag(tagId));
	}
	
	/**
	 * 根据标签Id获取标签
	 * @param ids 标签id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/tag/qryMultipleTags")
	public Object qryMultipleTags(//
			@RequestParam(required = true) String tagIds 
	) {
		return returnResult(tagServiceI.qryMultipleTags(tagIds));
	}
	
}