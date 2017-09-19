package com.fusio.tag.commons.exception;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 通用的处理所有Controller的异常类
 * 
 * @author Stone
 *
 */
@ControllerAdvice(basePackages={"com.fusio.tag.controller"})
public class ControllerExceptionAdvice {
	private static Logger logger = LoggerFactory.getLogger(ControllerExceptionAdvice.class);// slf4j提供了更友好的日志接口
	public ControllerExceptionAdvice() {
		System.out.println("@ControllerAdvice被new(ControllerExceptionAdvice)");
	}
	@Resource
	private ResultHelper resultHelper;
	
	
	/**
	 * 处理业务类的错误(例如业务禁止等等)
	 * 
	 * @param req
	 * @param resp
	 * @param e
	 * @return
	 * @author Stone
	 */
	@ResponseBody
	@ExceptionHandler(value = { BusinessException.class })
	public ResultBean handleBusiFail(HttpServletRequest req, HttpServletResponse resp, Exception e) {
		printStackTrace(e);
		BusinessException busiEx = (BusinessException) e;
		logger.warn("发生业务错误", e);
		return resultHelper.getBusiFailResultBean(busiEx.getRetCode(), busiEx.getMsg(), busiEx.getData());
	}

	/**
	 * 处理接口访问方式不正确(GET/POST/PUT/DELETE...)<br>
	 * 
	 * @param req
	 * @param resp
	 * @param e
	 * @return
	 * @author Stone
	 */
	@ResponseBody
	@ExceptionHandler(value = { HttpRequestMethodNotSupportedException.class })
	public ResultBean handleMethodNotFound(HttpServletRequest req, HttpServletResponse resp, Exception e) {
		printStackTrace(e);
		logger.warn("接口访问方式不正确(GET/POST/PUT/DELETE...)", e);
		String msg = "接口访问方式不正确(GET/POST/PUT/DELETE...):" + e.getMessage();
		return resultHelper.getMethodNotFoundResultBean(msg);
	}

	
	/**
	 * 传参错误(必传的没传,参数类型转换错误,或者手动抛出InvalidParamException(有的时候验证框架并不完美,需要手动验证并抛出)等等)
	 * 
	 * @param req
	 * @param resp
	 * @param e
	 * @return
	 * @author Stone
	 */
	@ResponseBody
	@ExceptionHandler(value = { MissingServletRequestParameterException.class, TypeMismatchException.class,
			ConstraintViolationException.class, InvalidControllerParamException.class, InvalidServiceParamException.class })
	public ResultBean handleParamException(HttpServletRequest req, HttpServletResponse resp, Exception e) {
		printStackTrace(e);
		logger.warn("validationException接口入参错误", e);
		String msg = e.getMessage();
		return resultHelper.getWrongMethodParamResultBean(msg);
	}

	/**
	 * 处理数据错误的异常(数据错误指从库表中查出的数据有残缺、不符合数据模型设计等情况)
	 * 
	 * @param req
	 * @param resp
	 * @param e
	 * @return
	 * @author Stone
	 */
	@ResponseBody
	@ExceptionHandler(value = { InvalidDbDataException.class })
	public ResultBean handleInvalidDataException(HttpServletRequest req, HttpServletResponse resp, Exception e) {
		printStackTrace(e);
		logger.error("handleInvalidDataException数据问题(残缺)错误", e);
		String msg = e.getMessage() + ExceptionUtil.getPrintStackTrace(e);
		return resultHelper.getInvalidDataResultBean(msg);
	}

	
	@ResponseBody
	@ExceptionHandler(value = { BindException.class })
	public ResultBean handleBindException(HttpServletRequest req, HttpServletResponse resp, Exception e) {
		printStackTrace(e);
		BindException bindException = (BindException) e;
		List<ObjectError> objectErrors = bindException.getAllErrors();
		StringBuffer buf = new StringBuffer();
		for (ObjectError error : objectErrors) {
			buf.append(error.getCodes()[1]).append(error.getDefaultMessage()).append("; ");
		}
		// TODO 这里再细化错误
		logger.warn("validationBindException接口入参错误", e);
		String msg = buf.toString();
		return resultHelper.getWrongMethodParamResultBean(msg);
	}
	
	/**
	 * 处理运行时异常的ResultBean(这种是意料之外的异常)
	 * 
	 * @param req
	 * @param resp
	 * @param e
	 * @return
	 * @author Stone
	 */
	@ResponseBody
	@ExceptionHandler(value = Exception.class)
	public ResultBean handleOtherException(HttpServletRequest req, HttpServletResponse resp, Exception e) {
		printStackTrace(e);
		logger.error("其他运行时异常：", e);
		String msg = ExceptionUtil.getPrintStackTrace(e);
		return resultHelper.getRuntimeExResultBean(msg);
	}
	
	private static void printStackTrace(Exception e) {
		e.printStackTrace();
	}
}
