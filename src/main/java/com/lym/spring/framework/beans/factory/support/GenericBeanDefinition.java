package com.lym.spring.framework.beans.factory.support;

import java.util.ArrayList;
import java.util.List;

import com.lym.spring.framework.beans.BeanDefinition;
import com.lym.spring.framework.beans.ConstructorArgment;
import com.lym.spring.framework.beans.factory.PropertyValue;

public class GenericBeanDefinition implements BeanDefinition{
	
	private String id;
	
	private String beanClassName;

	private Class<?> beanClass;
	
	//默认单例为true
	private boolean singleton = true;
	
	private boolean prototype = false;
	
	//默认单例为空
	private String scope =SCOPE_DEFAULT;
	
	private List<PropertyValue> propertyValues = new ArrayList<PropertyValue>();
	
	private ConstructorArgment constructorArgment = new ConstructorArgment();
	
	public GenericBeanDefinition(String id,String beanClassName) {
		this.id = id;
		this.beanClassName = beanClassName;
	}

	public GenericBeanDefinition() {

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

	@Override
	public ConstructorArgment getConstructorArgment() {
		return this.constructorArgment;
	}

	@Override
	public boolean hasConstructorArgment() {
		
		return !this.constructorArgment.getValueHolders().isEmpty();
	}

	@Override
	public boolean hasBeanClass() {
		return this.beanClassName!=null;
	}

	@Override
	public Class<?> getBeanClass() {
		if(this.beanClass == null) {
			throw new IllegalStateException(this.getBeanClassName() +"has not be into ");
		}
		return this.beanClass;
	}

	@Override
	public Class<?> resolveBeanClass(ClassLoader classLoader) throws ClassNotFoundException {
		Class<?> beanClass = getBeanClass();
		if(beanClass == null){
			return null;
		}
		Class<?> resovleBeanClass = classLoader.loadClass(beanClassName);
		this.beanClass = resovleBeanClass;
		return this.beanClass;
	}
}
