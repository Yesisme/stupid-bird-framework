package com.lym.spring.framework.context.support;

import com.lym.spring.framework.beans.factory.support.DefaultBeanFacotry;
import com.lym.spring.framework.beans.factory.xml.XmlBeanDefinitionReader;
import com.lym.spring.framework.context.ApplicationContext;
import com.lym.spring.framework.core.io.Resource;

public abstract class AbstractApplicationContext implements ApplicationContext{
 
	private DefaultBeanFacotry factory;
	
	private ClassLoader classLoader;
	
	public AbstractApplicationContext(String filePath) {
		factory = new DefaultBeanFacotry();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		Resource resource = this.getResourceByPath(filePath);
		reader.loadBeanDefinition(resource);
		factory.setClassLoader(this.getClassLoader());
	}
	
	public abstract Resource getResourceByPath(String filePath);
	
	
	public Object getBean(String beanId) {
		
		return this.factory.getBean(beanId);
	}
	
	public ClassLoader getClassLoader() {
		return classLoader;
	}

	public void setClassLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
	}
}
