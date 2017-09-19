package com.fusio.tag.commons.busi;

import com.fusio.tag.commons.utils.UuidUtil;

public class PrimaryKeyGenerator {
	
	/**
	 * 获取tagId,全小写
	 * @return
	 * @author Stone
	 */
	public static String getTagId() {
		return UuidUtil.getUUID32Lowercase();
	}
	
	/**
	 * 获取catgId,全大写
	 * @return
	 * @author Stone
	 */
	public static String getCatgId() {
		return UuidUtil.getUUID32Uppercase();
	}
	
}
