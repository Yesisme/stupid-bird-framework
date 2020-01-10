package com.lym.spring.framework.beans.factory.config;

import java.util.List;

public interface ConfigurableBeanFactory extends AutowireCapableBeanFactory{

	void setClassLoader(ClassLoader classLoader);
	
	ClassLoader getClassLoader();
	
	void addPostBeanProcessor(BeanPostProcessor beanPostProcessor);
	
	List<BeanPostProcessor> getBeanPostProcessor();
}
