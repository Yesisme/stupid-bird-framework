package com.lym.spring.framework.beans.factory.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

import com.lym.spring.framework.beans.factory.config.AutowireCapableBeanFactory;
import com.lym.spring.framework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.lym.spring.framework.beans.factory.support.BeanCreationException;
import com.lym.spring.framework.utils.AnnotationUtils;

public class AutowiredAnnotationProcessor implements InstantiationAwareBeanPostProcessor{

	private AutowireCapableBeanFactory factory;
	
	private final String requiredParameterName = "required";
	
	private boolean requiredParameterValue =true;
	
	private final Set<Class <? extends Annotation>> autowiredAnnotationTypes = new LinkedHashSet<>();
	
	public AutowiredAnnotationProcessor() {
		autowiredAnnotationTypes.add(Autowired.class);
	}
	
	public void setBeanFactory(AutowireCapableBeanFactory factory) {
		this.factory = factory;
	}
	

	public InjectionMetadata buildAutowiredMetadata(Class<?> clz) {
		LinkedList<InjectionElement> elements = new LinkedList<InjectionElement>();
		Class<?> targetClass = clz;
		do {
			LinkedList<InjectionElement> currentElements = new LinkedList<>();
			for (Field f : targetClass.getDeclaredFields()) {
				Annotation anno = findAutowiredAnnotation(f);
				//非静态
				if(anno!=null) {
					if(Modifier.isStatic(f.getModifiers())) {
						continue;
					}
					boolean required = determineRequiredStatus(anno);
					currentElements.add(new AutowiredFieldElement(required, f, factory));
				}
			}
			targetClass = targetClass.getSuperclass();
			elements.addAll(0,currentElements);
		} while (targetClass!=null && targetClass!=Object.class);
		
		return new InjectionMetadata(clz, elements);
	}
	
	private boolean determineRequiredStatus(Annotation anno) {
		
		return false;
	}

	@Override
	public Object beforeInitialization(Object bean, String beanName) {
		
		return null;
	}

	@Override
	public Object afterInitialization(Object bean, String beanName) {
		return null;
	}

	@Override
	public Object beforeInstantiation(Class<?> beanClass, String beanName) {
		return null;
	}

	@Override
	public boolean afterInstantiation(Object bean, String beanName) {
		return false;
	}
	
	//寻找是否有注解
	private Annotation findAutowiredAnnotation(AccessibleObject ao) {
		for (Class<? extends Annotation> type : autowiredAnnotationTypes) {
			Annotation anno = AnnotationUtils.getAnnotation(ao, type);
			if(anno!=null) {
				return anno;
			}
		}
		return null;
	}

	@Override
	public void postProcessPropertyValue(Object bean, String beanName) {
		InjectionMetadata metadata = buildAutowiredMetadata(bean.getClass());
		try {
			metadata.inject(bean);
		}catch(Exception e) {
			throw new BeanCreationException(beanName+"injected of autowired dependencies failed");
		}
	}

}
