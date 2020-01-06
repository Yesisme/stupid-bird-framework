package com.lym.spring.framework.core.type.classreading;

import com.lym.spring.framework.core.annotation.AnnotationAttributes;
import com.lym.spring.framework.core.type.AnnotationMetadata;
import jdk.internal.org.objectweb.asm.Type;
import org.springframework.asm.AnnotationVisitor;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class AnnotationMetaDataReaderingVisitor extends ClassMetaReaderingVisitor implements AnnotationMetadata {


    private Set<String> annotationSet = new LinkedHashSet<>(4);

    private Map<String, AnnotationAttributes> attributeMap = new LinkedHashMap<>();

    public AnnotationMetaDataReaderingVisitor(){}

    @Override
    public AnnotationVisitor visitAnnotation(final String desc, boolean visible) {

        String className = Type.getType(desc).getClassName();

        this.annotationSet.add(className);

        return new AnnotationAttributesReadingVisitor(className,this.attributeMap);
    }

    public Set<String> getAnnotationTypes(){
        return this.annotationSet;
    }

    public boolean hasSuperClass(){
        return  false;
    }

    public boolean hasAnnotation(String annotationType){
        return  this.annotationSet.contains(annotationType);
    }

    public AnnotationAttributes getAnnotationAttribute(String annotationType){
        return this.attributeMap.get(annotationType);
    }
}
