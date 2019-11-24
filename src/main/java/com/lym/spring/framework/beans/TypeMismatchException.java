package com.lym.spring.framework.beans;

public class TypeMismatchException extends BeansException{

	private transient Object value;
	private Class<?> requireType;
	
	public TypeMismatchException(Object value,Class<?> requireType) {
		super("cannot "+value +"to convert"+requireType);
		this.requireType = requireType;
		this.value = value;
	}

	public TypeMismatchException(String message) {
		super(message);
	}
	
	public TypeMismatchException(String message,Throwable cause) {
		super(message,cause);
	}
	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public Class<?> getRequireType() {
		return requireType;
	}

	public void setRequireType(Class<?> requireType) {
		this.requireType = requireType;
	}
}
