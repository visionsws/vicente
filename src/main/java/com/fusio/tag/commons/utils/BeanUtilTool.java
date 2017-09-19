package com.fusio.tag.commons.utils;

import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;

public class BeanUtilTool {
	
	/**
	 * 转成map
	 * @param bean
	 * @return
	 * @author Stone
	 */
	public static Map<String, Object> getBeanPropertyMap(Object bean) {
		try {
			if (bean == null) {
				return null;
			}
			Map<String, Object> map = PropertyUtils.describe(bean);
			map.remove("class");
			return map;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static Object getBeanPropertyValue(Object bean, String property) {
		try {
			return PropertyUtils.getProperty(bean, property);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
