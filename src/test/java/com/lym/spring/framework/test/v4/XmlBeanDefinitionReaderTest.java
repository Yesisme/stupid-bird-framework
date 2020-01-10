package com.lym.spring.framework.test.v4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Scanner;

import org.junit.Test;

import com.lym.spring.framework.beans.BeanDefinition;
import com.lym.spring.framework.beans.factory.support.DefaultBeanFacotry;
import com.lym.spring.framework.beans.factory.xml.XmlBeanDefinitionReader;
import com.lym.spring.framework.core.annotation.AnnotationAttributes;
import com.lym.spring.framework.core.annotation.ScannedGenericBeanDefinition;
import com.lym.spring.framework.core.io.ClassPathResource;
import com.lym.spring.framework.core.stereotype.Component;
import com.lym.spring.framework.core.type.AnnotationMetadata;

public class XmlBeanDefinitionReaderTest {

	@Test
	public void testXmlBeanDefinition() {
		DefaultBeanFacotry factory = new DefaultBeanFacotry();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		reader.loadBeanDefinition(new ClassPathResource("zoo-v4.xml"));
		
		String annotationName = Component.class.getName();
		
		{
			BeanDefinition bd = factory.getBeanDefinition("zooService");
			ScannedGenericBeanDefinition sbd = (ScannedGenericBeanDefinition) bd;
			AnnotationMetadata annotationMeta = sbd.getAnnotationMeta();
			assertTrue(annotationMeta.hasAnnotation(annotationName));
			AnnotationAttributes attribute = annotationMeta.getAnnotationAttribute(annotationName);
			assertEquals("zooService", attribute.get("value"));
		}
		
		{
			BeanDefinition bd = factory.getBeanDefinition("accountDao");
			assertTrue(bd instanceof ScannedGenericBeanDefinition);
			ScannedGenericBeanDefinition sbd = (ScannedGenericBeanDefinition)bd;
			AnnotationMetadata amd = sbd.getAnnotationMeta();
			assertTrue(amd.hasAnnotation(annotationName));
			AnnotationAttributes attributes = amd.getAnnotationAttribute(annotationName);
		}
		
		{
			BeanDefinition bd = factory.getBeanDefinition("itemDao");
			assertTrue(bd instanceof ScannedGenericBeanDefinition);
			ScannedGenericBeanDefinition sbd = (ScannedGenericBeanDefinition)bd;
			AnnotationMetadata amd = sbd.getAnnotationMeta();
			assertTrue(amd.hasAnnotation(annotationName));
			AnnotationAttributes attributes = amd.getAnnotationAttribute(annotationName);
		}
	}
}
