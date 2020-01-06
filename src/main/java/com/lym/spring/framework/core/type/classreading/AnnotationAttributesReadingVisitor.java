package com.lym.spring.framework.core.type.classreading;

import com.lym.spring.framework.core.annotation.AnnotationAttributes;
import org.springframework.asm.AnnotationVisitor;
import org.springframework.asm.SpringAsmInfo;

import java.util.Map;

public final class AnnotationAttributesReadingVisitor extends AnnotationVisitor {

    private final String attributType;

    private final Map<String, AnnotationAttributes> attributeMap;

    AnnotationAttributes attribute = new AnnotationAttributes();

    public AnnotationAttributesReadingVisitor(String attributeType, Map<String, AnnotationAttributes> attributeMap){
        super(SpringAsmInfo.ASM_VERSION);
        this.attributType = attributeType;
        this.attributeMap = attributeMap;
    }


    @Override
    public void visit(String attributeName, Object attributeValue) {
        this.attribute.put(attributeName,attributeValue);
    }

    @Override
    public void visitEnd() {
        this.attributeMap.put(this.attributType,this.attribute);
    }
}
