package com.fusio.tag.commons.exception;

public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private String retCode;
	private String msg;
	private Object data;

	protected BusinessException(String retCode, String msg, Object data) {
		super(msg);
		try {
			int ret = Integer.parseInt(retCode);
			if (!(ret >= 20000 && ret <= 30000)) {
				throw new RuntimeException("后端业务码设置不在规定范围内");
			}
		} catch (Exception e) {
			throw new RuntimeException("后端业务码设置错误:" + e.getMessage(), e);
		}
		
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

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
