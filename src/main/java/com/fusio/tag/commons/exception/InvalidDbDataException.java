package com.fusio.tag.commons.exception;

/**
 * 这个异常表示从库表查询出来的对象不符合正常情况，是属于垃圾数据或者说残缺补全的数据<br>
 * 
 * @author Stone
 *
 */
public class InvalidDbDataException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	protected InvalidDbDataException(String msg) {
		super(msg);
	}

}
