package com.fusio.tag.service.impl;

import java.lang.reflect.ParameterizedType;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fusio.tag.commons.exception.ExceptionUtil;
import com.fusio.tag.commons.exception.ValidDbDataUtil;
import com.fusio.tag.commons.exception.ValidServiceParamUtil;
import com.fusio.tag.dao.DaoUtil;

@Service
public abstract class BaseService<T> {
	protected Logger logger = null;
	private Class<T> clazzT;

	@Resource
	protected DaoUtil daoUtil;
	
	@Resource
	protected ValidDbDataUtil validDbDataUtil;

	@Resource
	protected ValidServiceParamUtil validParamUtil;

	protected void throwBusinessEx(String retCode, String msg, Object data) {
		throw ExceptionUtil.getBusiEx(retCode, msg, data);
	}

	public BaseService() {
		ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
		clazzT = (Class<T>) type.getActualTypeArguments()[0];
		logger = LoggerFactory.getLogger(clazzT);
	}

	/**
	 * 当Service遇到强制需要捕获的异常,或者自己想捕获的时候使用
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
	
	protected void throwRuntimeEx(String msg) {
		throw new RuntimeException(msg);
	}
}
