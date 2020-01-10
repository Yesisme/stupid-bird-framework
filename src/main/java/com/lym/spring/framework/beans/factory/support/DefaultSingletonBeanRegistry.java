package com.lym.spring.framework.beans.factory.support;

import java.util.concurrent.ConcurrentHashMap;

import com.lym.spring.framework.beans.factory.config.SingletonBeanRegistry;
import com.lym.spring.framework.utils.Assert;
/**
 * 存单例对象
 * @author Administrator
 *
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

	private final ConcurrentHashMap<String, Object> singletonObeject = new ConcurrentHashMap<String, Object>(64);
	
	@Override
	public void registrySingletonBean(String beanName, Object obj) {
		Assert.notNull(beanName, "beanClass cannot be null");
		Object oldBean = this.singletonObeject.get(beanName);
		if(oldBean!=null) {
			throw new IllegalStateException("can not registry" +"[" +obj+ "]" +"under beanName" +  beanName+ "there is aleardy Object"+oldBean);
		}
		this.singletonObeject.put(beanName, obj);
	}

	@Override
	public Object getSingletonBean(String beanName) {
		
		return this.singletonObeject.get(beanName);
	}

}
