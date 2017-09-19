package com.fusio.tag.service;

public interface DataImportServiceI {
	void saveCatgAndTags(String sourceFilePath, boolean isRealInsert/*传false的话可以先预览即将的改变*/);
	
	void maintainTag_pid();
	
	void maintainTag_CatgId();

	void maintainTag_fullId();
	
	void maintainCatg_pid();
	
	void maintainCatg_fullId();
}