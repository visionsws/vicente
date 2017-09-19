package com.fusio.tag.commons.utils;

import java.util.UUID;

public class UuidUtil {

	public static String getUUID32Lowercase() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	public static String getUUID32Uppercase() {
		return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
	}

	public static String getUUID36Lowercase() {
		return UUID.randomUUID().toString();
	}

	public static String getUUID36Uppercase() {
		return UUID.randomUUID().toString().toUpperCase();
	}
}
