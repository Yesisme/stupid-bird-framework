package com.lym.spring.framework.core.type;

import com.lym.spring.framework.core.annotation.AnnotationAttributes;

import java.util.Set;

//封装注解元数据
public interface AnnotationMetadata extends ClassMetadata{

    Set<String> getAnnotationTypes();

    boolean hasAnnotation(String annotationType);

    AnnotationAttributes getAnnotationAttribute(String annotationType);
}
