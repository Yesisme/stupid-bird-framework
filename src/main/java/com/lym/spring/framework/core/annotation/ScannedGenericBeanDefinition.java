package com.lym.spring.framework.core.annotation;

import com.lym.spring.framework.beans.factory.annotation.AnnotatedBeanDefinition;
import com.lym.spring.framework.beans.factory.support.GenericBeanDefinition;
import com.lym.spring.framework.core.type.AnnotationMetadata;

public class ScannedGenericBeanDefinition extends GenericBeanDefinition implements AnnotatedBeanDefinition {

    private final AnnotationMetadata annotationMetadata;

    public ScannedGenericBeanDefinition(AnnotationMetadata annotationMetadata){
        super();
        this.annotationMetadata = annotationMetadata;
        setBeanClassName(this.annotationMetadata.getClassName());
    }

    @Override
    public AnnotationMetadata getAnnotationMeta() {
        return this.annotationMetadata;
    }
}
