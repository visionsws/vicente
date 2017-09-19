package com.fusio.tag.vo;

public class TgIdOrCode {
	private Integer id;
	private String code;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public static void check(TgIdOrCode idOrCode) {
		if (idOrCode == null || idOrCode.getId() == null || idOrCode.getCode() == null
				|| idOrCode.getCode().length() == 0) {
			throw new RuntimeException("id或code必传其一");
		}
	}
}