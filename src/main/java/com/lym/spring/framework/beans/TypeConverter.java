package com.lym.spring.framework.beans;

public interface TypeConverter {

	//<T>声明为泛型方法
	// T 返回值
	<T> T convertIfNeccessary(Object value,Class<T> requireType) throws TypeMismatchException;
}
