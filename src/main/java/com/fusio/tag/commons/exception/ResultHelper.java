package com.fusio.tag.commons.exception;

import org.springframework.stereotype.Component;

@Component
public class ResultHelper {
	
	/**
	 * 获得成功的ResultBean
	 * 
	 * @param data
	 * @return
	 */
	public ResultBean getSuccResultBean(Object data) {
		return new ResultBean(ResultCode.SUCCESS_CODE, ResultCode.SUCCESS_MSG, data);
	}


	/**
	 * 获得业务失败的ResultBean
	 * @param retCode
	 * @param msg
	 * @param data
	 * @return
	 * @author Stone
	 */
	public ResultBean getBusiFailResultBean(String retCode, String msg, Object data) {
		return new ResultBean(retCode, msg, data);
	}
	
	/**
	 * 获得找不到接口的ResultBean
	 * @param msg
	 * @return
	 * @author Stone
	 */
	public ResultBean getMethodNotFoundResultBean(String msg) {
		if (msg == null || msg.length() <= 0) {
			msg = ResultCode.METHOD_NOT_FOUND_MSG;
		}
		return new ResultBean(ResultCode.METHOD_NOT_FOUND_CODE, msg, null);
	}
	
	/**
	 * 获得运行时异常的ResultBean(这种是意料之外的异常)
	 * 
	 * @param msg
	 * @return
	 * @author Stone
	 */
	public ResultBean getRuntimeExResultBean(String msg) {
		if (msg == null || msg.length() <= 0) {
			msg = ResultCode.RUNTIME_EX_MSG;
		}
		return new ResultBean(ResultCode.RUNTIME_EX_CODE, msg, null);
	}
	
	/**
	 * 错误的接口入参
	 * @param msg
	 * @return
	 * @author Stone
	 */
	public ResultBean getWrongMethodParamResultBean(String msg) {
		if (msg == null || msg.length() <= 0) {
			msg = ResultCode.WRONG_METHOD_PARAM_MSG;
		}
		return new ResultBean(ResultCode.WRONG_METHOD_PARAM_CODE, msg, null);
	}
	
	/**
	 * 获得数据错误(垃圾数据、残缺数据)的ResultBean
	 * @param msg
	 * @return
	 * @author Stone
	 */
	public ResultBean getInvalidDataResultBean(String msg) {
		if (msg == null || msg.length() <= 0) {
			msg = ResultCode.INVALID_DATA_MSG;
		}
		return new ResultBean(ResultCode.INVALID_DATA_CODE, msg, null);
	}
	
	/**
	 * 获得没有权限调用(摘要错误)的ResultBean
	 * @return
	 * @author Stone
	 */
	public ResultBean getNotAuthResultBean() {
		return new ResultBean(ResultCode.NOT_AUTH_CODE, ResultCode.NOT_AUTH_MSG, null);
	}
}
