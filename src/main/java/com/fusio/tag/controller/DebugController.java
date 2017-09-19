package com.fusio.tag.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fusio.tag.commons.prop.ConfigProperties;
import com.fusio.tag.commons.prop.TimestampConfig;

/**
 * 访问路径由这里的值和方法上的@RequestMapping的值决定，例如ajaxMethod()这个方法：/testController/
 * ajaxMethod来访问
 * 
 * @author Stone
 *
 */
@Controller
public class DebugController extends BaseController<DebugController> {
	
	@ResponseBody
	@RequestMapping(value = "/debugController/refreshConfig")
	public synchronized Object refreshConfig() {
		ConfigProperties.initConstVars();
		TimestampConfig.initConstVars();
		
		Map<String, Object> map = new HashMap<>();
		map.put("flag", true);
		map.put("retCode", "1");
		map.put("msg", "刷新成功");
		map.put("data", null);
		map.put("time", "最后刷新时间: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/debugController/forward/{page}")
	public Object forward(HttpServletRequest req, HttpServletResponse resp, @PathVariable String page) {
		
		return new ModelAndView("forward:/WEB-INF/jsp/test/jsp/" + page + ".jsp").addObject(page);
	}
}