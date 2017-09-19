package com.fusio.tag.controller;

import java.lang.reflect.ParameterizedType;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.fusio.tag.commons.exception.ResultBean;
import com.fusio.tag.commons.exception.ResultHelper;
import com.fusio.tag.commons.exception.ValidControllerParamUtil;
	
/**
 * 子类要指定T否则会类转换错误
 * 
 * @author Stone
 *
 * @param <T>
 */
@SuppressWarnings(value = { "unchecked" })
@Controller
public abstract class BaseController<T> {
	protected Logger logger = null;
	private Class<T> clazzT;

	/**
	 * 提供更丰富的验证参数的API
	 */
	@Resource
	protected ValidControllerParamUtil validParamUtil;

	public BaseController() {
		ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
		clazzT = (Class<T>) type.getActualTypeArguments()[0];
		logger = LoggerFactory.getLogger(clazzT);
	}

	/**
	 * 私有化，不给子类
	 */
	@Resource
	private ResultHelper resultHelper;

	// 由前端检查登录状态, 后端只做无状态服务器
	// @Resource(name = "redisSessionUtil")
	// protected SessionCheckerI sessionCheckerI;

	/**
	 * 无返回值
	 * 
	 * @return
	 * @author Stone
	 */
	protected ResultBean returnResult() {
		return this.returnResult(null);
	}

	/**
	 * 有返回值
	 * 
	 * @param data
	 * @return
	 * @author Stone
	 */
	protected ResultBean returnResult(Object data) {
		return resultHelper.getSuccResultBean(data);
	}

	
	/**
	 * 在Controller里，如果出现强迫处理的异常，try-catch后用此方法包住抛出。(这样Controller的方法不必声明throws)
	 * 
	 * @param e
	 * @param msg
	 * @author Stone
	 */
	protected void throwRuntimeEx(Exception e, String msg) {
		String err = e.getMessage();
		msg = msg == null ? "" : msg;
		throw new RuntimeException("Controller运行时异常:" + msg + "|" + err, e);
	}

	/**
	 * 重载
	 * 
	 * @param e
	 * @author Stone
	 */
	protected void throwRuntimeEx(Exception e) {
		this.throwRuntimeEx(e, null);
	}
	
	/**
	 * 抛出异常
	 * @param msg
	 * @author Stone
	 */
	protected void throwRuntimeEx(String msg) {
		throw new RuntimeException(msg);
	}
}
