package com.lym.spring.framework.beans.factory.support;

import java.util.concurrent.ConcurrentHashMap;

import com.lym.spring.framework.beans.BeanDefinition;
import com.lym.spring.framework.beans.config.ConfigurableBeanFactory;
import com.lym.spring.framework.beans.factory.BeanFactory;
import com.lym.spring.framework.utils.ClassUtil;

public class DefaultBeanFacotry extends DefaultSingletonBeanRegistry implements BeanFactory,BeanDefinitionRegistry,ConfigurableBeanFactory{

	private ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(64);
	
	private ClassLoader classLoader;
	
	@Override
	public Object getBean(String beanId) {
		BeanDefinition bd = this.getBeanDefinition(beanId);
		if(bd.isSingleton()) {
			Object bean = this.getSingletonBean(bd.getBeanClassName());
			if(bean==null) {
				Object singletonObject = createBean(bd);
				this.registrySingletonBean(bd.getBeanClassName(), singletonObject);
				return singletonObject;
			}
			return bean;
		}
		return createBean(bd);
	}

	private Object createBean(BeanDefinition bd) {
		Object bean = initBean(bd.getBeanClassName());
		return bean;
	}

	public Object initBean(String beanClassName) {
		try {
			Class<?> clazz = this.getClassLoader().loadClass(beanClassName);
			return clazz.newInstance();
		}catch(Exception e) {
			throw new BeanCreationException("create bean "+"【"+beanClassName+"】"+"fail");
		}
	}
	@Override
	public BeanDefinition getBeanDefinition(String beanId) {
		
		return this.beanDefinitionMap.get(beanId);
	}

	@Override
	public void registryBeanDefinition(String beanId, BeanDefinition beanDefinition) {
		this.beanDefinitionMap.put(beanId, beanDefinition);
	}

	@Override
	public void setClassLoader(ClassLoader classLoader) {
		this.classLoader = classLoader==null?ClassUtil.getDefaultClassLoader():classLoader;
	}

	@Override
	public ClassLoader getClassLoader() {
	
		return this.classLoader;
	}
}
