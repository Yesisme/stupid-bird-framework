package com.lym.spring.framework.beans.factory.support;

import com.lym.spring.framework.beans.BeanDefinition;

public interface BeanDefinitionRegistry {

	BeanDefinition getBeanDefinition(String BeanId);
	
	void registryBeanDefinition(String BeanId,BeanDefinition beanDefinition);
}
