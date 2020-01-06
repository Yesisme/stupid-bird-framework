package com.lym.spring.framework.beans.factory.support;

import com.lym.spring.framework.beans.BeanDefinition;

public interface BeanNameGenerator {

    String generateBeanName(BeanDefinition definition,BeanDefinitionRegistry registry);
}
