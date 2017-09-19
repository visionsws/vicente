package com.fusio.tag.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fusio.tag.model.autogen.TTest;
import com.fusio.tag.service.TTestServiceI;

/**
 * 想要测试的代码写到这里
 * 
 * @author Administrator
 *
 */
@RestController
public class TestController extends BaseController<TestController> {
	@Resource
	private TTestServiceI tTestServiceI;
	
	// 用来检查springmvc和spring两个父子容器之间可能重复扫描的问题，正常情况不用写
	public TestController() {
		System.out.println("@Controller被new(TestController)");
	}

	
	@ResponseBody
	@RequestMapping(value = "/test/test")
	public Object test(HttpServletRequest req, HttpServletResponse resp) {
//		
		TTest tTest = new TTest();
		tTest.setName2("李雷3");
		tTestServiceI.insert(tTest);
		
		
		
//		return returnResult(tTestServiceI.custom());
		return returnResult();
	}
}