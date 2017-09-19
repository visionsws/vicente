package com.fusio.tag.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fusio.tag.service.ImportByUploadServiceI;

@RestController
public class ImportByUploadController extends BaseController<ImportByUploadController> {
	@Resource
	private ImportByUploadServiceI importByUploadServiceI;

	@ResponseBody
	@RequestMapping(value = "/importByUploadController/upload")
	public Object upload(//
			HttpServletRequest req, //
			HttpServletResponse resp//
	) {
		
		
		return returnResult();
	}
}