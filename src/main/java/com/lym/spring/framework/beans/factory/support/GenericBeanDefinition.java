package com.lym.spring.framework.beans.factory.support;

import java.util.ArrayList;
import java.util.List;

import com.lym.spring.framework.beans.BeanDefinition;
import com.lym.spring.framework.beans.factory.PropertyValue;

public class GenericBeanDefinition implements BeanDefinition{
	
	private String id;
	
	private String beanClassName;
	
	//默认单例为true
	private boolean singleton = true;
	
	private boolean prototype = false;
	
	//默认单例为空
	private String scope =SCOPE_DEFAULT;
	
	private List<PropertyValue> propertyValues = new ArrayList<PropertyValue>();
	
	public GenericBeanDefinition(String id,String beanClassName) {
		this.id = id;
		this.beanClassName = beanClassName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBeanClassName() {
		return beanClassName;
	}

	public void setBeanClassName(String beanClassName) {
		this.beanClassName = beanClassName;
	}

	public boolean isSingleton() {
		return singleton;
	}

	public void setSingleton(boolean singleton) {
		this.singleton = singleton;
	}

	public boolean isPrototype() {
		return prototype;
	}

	public void setPrototype(boolean prototype) {
		this.prototype = prototype;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
		this.singleton = SCOPE_SINGLETON.equals(scope) || SCOPE_DEFAULT.equals(scope);
		this.prototype = SCOPE_PROTOTYPE.equals(scope);
	}

	@Override
	public List<PropertyValue> getPropertyValues() {
		return this.propertyValues;
	}
}
