package com.lym.spring.framework.utils;

public abstract class StringUtil {

	public static boolean hasLength(String str) {
		return hasLength((CharSequence) str);
	}

	public static boolean hasLength(CharSequence ch) {
		return (ch != null && ch.length() > 0);
	}

	public static boolean hasText(String str) {
		return hasText((CharSequence) str);
	}

	public static boolean hasText(CharSequence cs) {
		if (!hasLength(cs)) {
			return false;
		}
		int strlength = cs.length();
		for (int i = 0; i < strlength; i++) {
			if (!Character.isWhitespace(cs.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	public static String trimAllWhitespace(String str) {
		if (!StringUtil.hasLength(str)) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str);
		int index=0;
		while(sb.length()>index) {
			for(int i=0;i<str.length();i++) {
				if(Character.isWhitespace(str.charAt(i))) {
					sb.deleteCharAt(str.charAt(i));
				}else {
					index++;
				}
			}
		}
		return sb.toString();
	}
}
