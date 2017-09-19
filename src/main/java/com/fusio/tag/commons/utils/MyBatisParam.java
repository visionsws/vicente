package com.fusio.tag.commons.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * mybatis的参数帮助类
 * 
 * @author Stone
 *
 */
public class MyBatisParam {

	private Map<String, Object> map;

	private MyBatisParam() {
		this.map = new HashMap<>();
	}

	public static MyBatisParam create() {
		return new MyBatisParam();
	}

	public MyBatisParam put(String key, Object value) {
		map.put(key, value);
		return this;
	}

	public Map<String, Object> get() {
		return map;
	}
}
