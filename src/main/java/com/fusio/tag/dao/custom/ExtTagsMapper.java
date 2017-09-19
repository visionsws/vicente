package com.fusio.tag.dao.custom;

import java.util.List;
import java.util.Map;

import com.fusio.tag.model.autogen.Tags;

public interface ExtTagsMapper {
	Tags qryDirectParentTag(Map param);
	
	void insertTagsBatch(List list);
	
	
	Integer getIdByTagCode(String code);
	Integer getIdByCatgCode(String code);

	
	String getTagIdByTagCode(String code);
	String getCatgIdByCatgCode(String code);
	
	
	
	List<Integer> getTagAllLevel();
	List<Integer> getCatgAllLevel();
}