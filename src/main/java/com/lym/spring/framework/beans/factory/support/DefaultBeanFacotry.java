package com.lym.spring.framework.beans.factory.support;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.lym.spring.framework.beans.BeanDefinition;
import com.lym.spring.framework.beans.SimpleTypeConvert;
import com.lym.spring.framework.beans.TypeConverter;
import com.lym.spring.framework.beans.config.ConfigurableBeanFactory;
import com.lym.spring.framework.beans.factory.BeanFactory;
import com.lym.spring.framework.beans.factory.PropertyValue;
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
		//初始化bean
		Object bean = initBean(bd);
		
		//注入bean
		populateBean(bd,bean);
		
		return bean;
	}

	private void populateBean(BeanDefinition bd, Object bean) {
		
		List<PropertyValue> pvs = bd.getPropertyValues();
		if(pvs==null || pvs.size()==0) {
			return;
		}
		TypeConverter convert = new SimpleTypeConvert();
		BeanDefinitionValueResolver resolver = new BeanDefinitionValueResolver(this);
		try {
			for (PropertyValue pv : pvs) {
				String propertyName = pv.getName(); 
				Object originValue = pv.getValue();
				Object resovlerValue = resolver.resovlerValueIfNecessary(originValue);
				//得到一个Bean的所有set信息
				BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
				PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
				for (PropertyDescriptor pd : propertyDescriptors) {
					if(pd.getName().equals(propertyName)) {
						Object convertValue = convert.convertIfNeccessary(resovlerValue, pd.getPropertyType());
						pd.getWriteMethod().invoke(bean, convertValue);
						break;
					}
				}
			}
		}catch(Exception e) {
			
		}
		
		
	}

	public Object initBean(BeanDefinition bd) {
		if(bd.hasConstructorArgment()) {
			ConstructorResolver resolver = new ConstructorResolver(this);
			return resolver.autowireConstructor(bd);
		}else {
			String beanClassName = bd.getBeanClassName();
			try {
				Class<?> clazz = this.getClassLoader().loadClass(beanClassName);
				return clazz.newInstance();
			}catch(Exception e) {
				throw new BeanCreationException("create bean "+"【"+beanClassName+"】"+"fail");
			}
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
