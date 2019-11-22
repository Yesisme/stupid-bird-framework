package com.lym.spring.framework.beans.config;

public class TypedStringValue {

	//property标签中 value的值
	private String value;
	
	public TypedStringValue(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
