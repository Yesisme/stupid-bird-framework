package com.lym.spring.framework.beans.factory.annotation;

import java.util.List;

public class InjectionMetadata {

	private final Class<?> targetClass;
	
	private List<InjectionElement> injectElements;
	
	public InjectionMetadata(Class<?> targetClass,List<InjectionElement> injectElements) {
		this.targetClass = targetClass;
		this.injectElements = injectElements;
	}
	
	public List<InjectionElement> getInjectionElement(){
		return this.injectElements;
	}
	
	public void inject(Object target) {
		if(this.injectElements==null || this.injectElements.isEmpty()) {
			return;
		}
		
		for (InjectionElement ij : injectElements) {
			ij.inject(target);
		}
	}
}
