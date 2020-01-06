package com.lym.spring.framework.context.annotation;


import com.lym.spring.framework.beans.BeanDefinition;
import com.lym.spring.framework.beans.factory.annotation.AnnotatedBeanDefinition;
import com.lym.spring.framework.beans.factory.support.BeanDefinitionRegistry;
import com.lym.spring.framework.beans.factory.support.BeanNameGenerator;
import com.lym.spring.framework.core.annotation.AnnotationAttributes;
import com.lym.spring.framework.core.type.AnnotationMetadata;
import com.lym.spring.framework.utils.ClassUtil;
import com.lym.spring.framework.utils.StringUtil;

import java.beans.Introspector;
import java.util.Set;

/*
 * componet注解 beanID生成规则具体实现类
 */
public class AnnotationBeanNameGenerator implements BeanNameGenerator {

	@Override
	public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
		if(definition instanceof AnnotatedBeanDefinition) {
			String beanName = determineBeanNameFromAnnotation((AnnotatedBeanDefinition)definition);
			if(StringUtil.hasText(beanName)) {
				return beanName;
			}
		}
		return buildDefaultBeanName(definition,registry);
	}

	private String buildDefaultBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
		
		return buildDefaultBeanName(definition);
	}

	private String buildDefaultBeanName(BeanDefinition definition) {
		String shortClassName = ClassUtil.getShortName(definition.getBeanClassName());
		return Introspector.decapitalize(shortClassName);
	}

	private String determineBeanNameFromAnnotation(AnnotatedBeanDefinition annotatedDef) {
		AnnotationMetadata amd = annotatedDef.getAnnotationMeta();
		Set<String> types = amd.getAnnotationTypes();
		String beanName = null;
		for (String type : types) {
			AnnotationAttributes attribute = amd.getAnnotationAttribute(type);
			Object value = attribute.get("value");
			if(value instanceof String) {
				String strValue = (String)value;
				if(StringUtil.hasLength(strValue)) {
					beanName = strValue;
				}
			}
		}
		return beanName;
	}

}
