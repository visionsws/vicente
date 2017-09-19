package com.fusio.tag.commons.exception;

/**
 * 这个异常表示前端传的参数格式不正确，由Controller层抛出
 * 
 * @author Stone
 *
 */
public class InvalidControllerParamException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	protected InvalidControllerParamException(String msg) {
		super(msg);
	}
}
