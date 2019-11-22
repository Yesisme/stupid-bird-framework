package com.lym.spring.framework.beans;

import java.util.List;

import com.lym.spring.framework.beans.factory.PropertyValue;

/**
 * 描述一个Bean的作用域，设置获取作用域，获取bean的class路径
 * @author Administrator
 *
 */
public interface BeanDefinition {
	
	public static final String SCOPE_SINGLETON = "singleton";
	
	public static final String SCOPE_PROTOTYPE = "prototype";
	
	public static final String SCOPE_DEFAULT = ""; 
	
	public void setScope(String scope);
	public String getScope();
	
	public boolean isSingleton();
	
	public boolean isPrototype();
	
	public String getBeanClassName();
	
	public List<PropertyValue> getPropertyValues();
	
}
