package com.lym.spring.framework.beans.config;

public class RuntimeBeanReference {

	//property标签中  ref的值
	private String beanName;
	
	public RuntimeBeanReference(String beanName) {
		this.beanName = beanName;
	}
	
	public String getBeanName() {
		return this.beanName;
	}
}
