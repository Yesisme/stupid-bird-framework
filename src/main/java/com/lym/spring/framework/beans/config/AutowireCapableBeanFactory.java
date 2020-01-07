package com.lym.spring.framework.beans.config;

import com.lym.spring.framework.beans.factory.BeanFactory;

public interface AutowireCapableBeanFactory extends BeanFactory {

    Object resolveDependency(DependencyDescriptor dependencyDescriptor);
}
