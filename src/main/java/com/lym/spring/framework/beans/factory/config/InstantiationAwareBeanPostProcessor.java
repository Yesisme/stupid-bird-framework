package com.lym.spring.framework.beans.factory.config;

public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {

	Object beforeInstantiation(Class<?> beanClass, String beanName);

	boolean afterInstantiation(Object bean, String beanName);

	void postProcessPropertyValue(Object bean, String beanName);
}
