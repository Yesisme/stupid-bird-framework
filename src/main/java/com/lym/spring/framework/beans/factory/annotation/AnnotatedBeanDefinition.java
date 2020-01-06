package com.lym.spring.framework.beans.factory.annotation;

import com.lym.spring.framework.beans.BeanDefinition;
import com.lym.spring.framework.core.type.AnnotationMetadata;

public interface AnnotatedBeanDefinition extends BeanDefinition {

    AnnotationMetadata getAnnotationMeta();
}
