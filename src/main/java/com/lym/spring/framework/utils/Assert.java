package com.lym.spring.framework.utils;

public abstract class Assert {

	public static void notNull(Object obj ,String message) {
		if(obj == null) {
			throw new IllegalArgumentException(message);
		}
	}
}
