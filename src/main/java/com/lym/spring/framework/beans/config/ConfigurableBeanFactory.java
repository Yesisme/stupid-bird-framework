package com.lym.spring.framework.beans.config;
/**
 * 提供获取calssloader的方法
 */
import com.lym.spring.framework.beans.factory.BeanFactory;

public interface ConfigurableBeanFactory extends AutowireCapableBeanFactory{

	void setClassLoader(ClassLoader classLoader);
	
	ClassLoader getClassLoader();
	
}
