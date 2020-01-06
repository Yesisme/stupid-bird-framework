package com.lym.spring.framework.core.type.classreading;

import com.lym.spring.framework.core.io.Resource;
import com.lym.spring.framework.core.type.AnnotationMetadata;
import com.lym.spring.framework.core.type.ClassMetadata;


public interface MetadataReader {

    Resource getResource();

    //注解元数据信息
    AnnotationMetadata getAnnotationMetadata();

    //类的元数据信息
    ClassMetadata getClassMetadate();

}
