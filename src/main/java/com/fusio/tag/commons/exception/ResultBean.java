package com.fusio.tag.commons.exception;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ResultBean {
	private static final DateFormat DF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private String retCode;
	private String msg;
	private String time = DF.format(new Date());
	private Object data;
	
	/**
	 * 受保护的方法
	 * @param retCode
	 * @param msg
	 * @param data
	 */
	protected ResultBean(String retCode, String msg, Object data) {
		this.retCode = retCode;
		this.msg = msg;
		this.data = data;
	}
	public String getRetCode() {
		return retCode;
	}
	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
