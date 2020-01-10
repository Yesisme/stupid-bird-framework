package com.lym.spring.framework.beans.factory.config;

import java.lang.reflect.Field;

public class DependencyDescriptor {

    private Field field;

    private boolean require;

    public DependencyDescriptor(Field field,boolean require){
        this.field = field;
        this.require = require;
    }

    public Class<?> getFieldType(){
        if(this.field!=null){
            return this.field.getType();
        }
        throw new RuntimeException("only support field type");
    }

    public boolean isRequire(){
        return this.require;
    }
}
