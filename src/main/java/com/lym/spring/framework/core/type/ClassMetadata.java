package com.lym.spring.framework.core.type;

public interface ClassMetadata {

    String getClassName();

    boolean isFinal();

    boolean isAbstract();

    boolean isInterface();

    boolean hasSuperClass();

    String getSuperClassName();

}
