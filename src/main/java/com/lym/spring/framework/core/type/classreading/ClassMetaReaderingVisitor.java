package com.lym.spring.framework.core.type.classreading;

import com.lym.spring.framework.core.type.ClassMetadata;
import com.lym.spring.framework.utils.ClassUtil;
import jdk.internal.org.objectweb.asm.Opcodes;
import org.springframework.asm.ClassVisitor;
import org.springframework.asm.SpringAsmInfo;

public class ClassMetaReaderingVisitor extends ClassVisitor implements ClassMetadata {

    private String className;

    private boolean isFinal;

    private boolean isAbstract;

    private boolean isInterface;

    private boolean hasSuperClass;

    private String[] interfaces;

    private String superClassName;

    @Override
    public boolean hasSuperClass() {
        return false;
    }

    public ClassMetaReaderingVisitor(int i) {
        super(i);
    }

    public ClassMetaReaderingVisitor() {
        super(SpringAsmInfo.ASM_VERSION);
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String supername,String[] interfaces) {
        this.className = ClassUtil.convertToClassPathResource(name);
        this.isAbstract = ((access & Opcodes.ACC_ABSTRACT)!=0);
        this.isFinal = ((access & Opcodes.ACC_FINAL)!=0);
        this.isInterface = ((access & Opcodes.ACC_INTERFACE)!=0);
        if(supername!=null){
            this.superClassName = ClassUtil.convertToClassPathResource(supername);
        }
        this.interfaces = new String[interfaces.length];
        for(int i=0;i<interfaces.length;i++){
            this.interfaces[i] = ClassUtil.convertToClassPathResource(interfaces[i]);
        }
    }

    public boolean isFinal() {
        return isFinal;
    }

    public void setFinal(boolean aFinal) {
        isFinal = aFinal;
    }

    public boolean isAbstract() {
        return isAbstract;
    }

    public void setAbstract(boolean anAbstract) {
        isAbstract = anAbstract;
    }

    public boolean isInterface() {
        return isInterface;
    }

    public void setInterface(boolean anInterface) {
        isInterface = anInterface;
    }

    public String[] getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(String[] interfaces) {
        this.interfaces = interfaces;
    }

    public String getSuperClassName() {
        return superClassName;
    }

    public void setSuperClassName(String superClassName) {
        this.superClassName = superClassName;
    }

    public boolean isHasSuperClass() {
        return hasSuperClass;
    }

    public void setHasSuperClass(boolean hasSuperClass) {
        this.hasSuperClass = hasSuperClass;
    }
}
