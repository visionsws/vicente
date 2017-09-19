package com.fusio.tag.commons.exception;

/**
 * 这个异常和InvalidParamException看起来有点像。但是这个是由Service层抛出，
 * 而InvalidParamException是Controller抛出。跟InvalidDataException的区别是，这个是表示入参的错误，
 * InvalidDataException是表示从数据库表里查出的数据是"垃圾数据"、"残缺数据"。
 * 
 * @author Stone
 *
 */
public class InvalidServiceParamException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	protected InvalidServiceParamException(String msg) {
		super(msg);
	}
}
