package com.lym.spring.framework.beans.factory.config;

public interface BeanPostProcessor {

	Object beforeInitialization(Object bean,String beanName);
	
	Object afterInitialization(Object bean,String beanName);
}
