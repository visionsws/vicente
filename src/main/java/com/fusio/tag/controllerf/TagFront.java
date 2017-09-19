package com.fusio.tag.controllerf;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fusio.tag.controller.BaseController;
import com.fusio.tag.service.CatgServiceI;
import com.fusio.tag.service.TagServiceI;
import com.fusio.tag.vo.AllGraph;
import com.fusio.tag.vo.TagGraph;

@Controller
public class TagFront extends BaseController<TagFront> {
	@Resource
	private TagServiceI tagServiceI;
	@Resource
	private CatgServiceI catgServiceI;

	
	@ResponseBody
	@RequestMapping(value = "/tagFront/getFullTagTree")
	public Object getFullTagTree(//
	) {
		long time1 = System.currentTimeMillis();

		AllGraph allGraph = catgServiceI.qryFullGraph();

		long time2 = System.currentTimeMillis();
		System.out.println("查询标签树耗费时间(getFullTagTree)" + (time2 - time1));

		return AllGraphAdapter.adapter(allGraph, null);
	}
	
	@RequestMapping(value = "/tagFront/toTagTreePage")
	public Object toTagTreePage(//
	) {
		String jspPath = "tags/tag-tree";
		return new ModelAndView(jspPath);
	}
	
	
	
	@RequestMapping(value = "/tagFront/toTagTree")
	public Object toTagTree(//
	) {
		String jspPath = "tags/tag-tree";

		long time1 = System.currentTimeMillis();

		TagGraph tagGraph = tagServiceI.qryTagGraph();

		long time2 = System.currentTimeMillis();
		System.out.println("查询标签树耗费时间" + (time2 - time1));

		return new ModelAndView(jspPath).addObject("data", tagGraph);
	}


	@ResponseBody
	@RequestMapping(value = "/tagFront/getTagTreeData")
	public Object getTagTreeData(//
	) {

		long time1 = System.currentTimeMillis();

		TagGraph tagGraph = tagServiceI.qryTagGraph();

		long time2 = System.currentTimeMillis();
		System.out.println("查询标签树耗费时间(getTagTreeData)" + (time2 - time1));

		return TagGraphAdapter.adapter(tagGraph, null);
	}

	
}