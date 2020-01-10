package com.lym.spring.framework.beans.factory.annotation;

import java.lang.reflect.Member;

import com.lym.spring.framework.beans.factory.config.AutowireCapableBeanFactory;

public abstract class InjectionElement {
	
	protected Member member;
	
	protected AutowireCapableBeanFactory autowireCapableBeanFactory;
	
	public InjectionElement(Member member,AutowireCapableBeanFactory autowireCapableBeanFactory) {
		this.member = member;
		this.autowireCapableBeanFactory = autowireCapableBeanFactory;
	}
	
	public abstract void inject(Object target);

}
