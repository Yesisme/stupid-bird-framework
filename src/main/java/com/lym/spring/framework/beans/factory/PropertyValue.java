package com.lym.spring.framework.beans.factory;

public class PropertyValue {

	//properties标签中name的值
	private String name;
	
	//对应RuntimeBeanReference或者TypedStringValue
	private Object value;
	
	//默认为未转换
	private boolean converted = false;
	
	//转换的值
	private Object convertedValue;
	
	public PropertyValue(String name,Object value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public Object getValue() {
		return value;
	}

	public synchronized boolean isConverted() {
		return converted;
	}

	public void setConverted(boolean converted) {
		this.converted = converted;
	}

	public synchronized Object getConvertedValue() {
		return convertedValue;
	}

	public synchronized void setConvertedValue(Object convertedValue) {
		this.converted = true;
		this.convertedValue = convertedValue;
	}

}
