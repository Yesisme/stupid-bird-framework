package com.lym.spring.framework.beans.factory.support;

import com.lym.spring.framework.beans.config.RuntimeBeanReference;
import com.lym.spring.framework.beans.config.TypedStringValue;
import com.lym.spring.framework.beans.factory.BeanFactory;

public class BeanDefinitionValueResolver {

	private BeanFactory beanFactory;
	
	public BeanDefinitionValueResolver(BeanFactory beanFactory) {
		this.beanFactory = beanFactory;
	}
	
	public Object resovlerValueIfNecessary(Object value) {
		if(value instanceof RuntimeBeanReference) {
			RuntimeBeanReference runtimeBeanReference = (RuntimeBeanReference) value;
			String beanName = runtimeBeanReference.getBeanName();
			Object bean = this.beanFactory.getBean(beanName);
			return bean;
		}else if(value instanceof TypedStringValue) {
			TypedStringValue typedStringValue = (TypedStringValue) value;
			String origiValue = typedStringValue.getValue();
			return origiValue;
		}
		throw new IllegalArgumentException("the type of" +value+"not implemtns" );
	}
}
