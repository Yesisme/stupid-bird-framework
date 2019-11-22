package com.lym.spring.framework.beans.config;
/**
 * 存单例的对象和获取单例的对象
 * @author Administrator
 *
 */
public interface SingletonBeanRegistry {

	void registrySingletonBean(String beanName,Object obj);
	Object getSingletonBean(String beanName);
}
