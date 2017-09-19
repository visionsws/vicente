package com.fusio.tag.vo;

public class IdOrCode {
	private String id;
	private String code;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public static void check(IdOrCode idOrCode) {
		if (idOrCode == null || idOrCode.getId() == null || idOrCode.getId().length() == 0 || idOrCode.getCode() == null
				|| idOrCode.getCode().length() == 0) {
			throw new RuntimeException("id或code必传其一");
		}
	}
}