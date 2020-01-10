package com.lym.spring.framework.beans.factory.annotation;

import java.lang.reflect.Field;

import com.lym.spring.framework.beans.factory.config.AutowireCapableBeanFactory;
import com.lym.spring.framework.beans.factory.config.DependencyDescriptor;
import com.lym.spring.framework.beans.factory.support.BeanCreationException;

public class AutowiredFieldElement extends InjectionElement{
	
	boolean require;

	public AutowiredFieldElement(boolean require,Field f, AutowireCapableBeanFactory autowireCapableBeanFactory) {
		super(f, autowireCapableBeanFactory);
		this.require = require;
	}

	public Field getField() {
		return (Field) this.member;
	}
	@Override
	public void inject(Object target) {
		Field field = this.getField();
		try {
			DependencyDescriptor dp = new DependencyDescriptor(field, this.require);
			Object value = this.autowireCapableBeanFactory.resolveDependency(dp);
			if(value !=null) {
				field.setAccessible(true);
				field.set(target, value);
			}
		}catch (Exception e) {
			throw new BeanCreationException("could not autowired field"+field);
		}
		
	}
	

}
