package com.fusio.tag.commons.utils;

import java.util.regex.Pattern;

public class UrlSlashFilter {
	private static final Pattern p = Pattern.compile("");
	
	public static void main(String[] args) {
		String s = "http://sdfsdfsdfs//sdfsdf/foo///bar";
		String ss = s.replaceAll("([^:])[\\/\\\\]{2,}", "/");
		System.out.println(ss);
		String r = s.replaceAll("//", "/").replaceAll(":/", "://");
		System.out.println(r);
	}
}
