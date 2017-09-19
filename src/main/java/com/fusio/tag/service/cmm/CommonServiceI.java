package com.fusio.tag.service.cmm;

public interface CommonServiceI {
	
	// tg_tag和tg_catg表
	Integer getIdByTagCode(String code);
	Integer getIdByCatgCode(String code);
	
	// tags和catg表
	String getTagIdByTagCode(String code);
	String getCatgIdByCatgCode(String code);
}