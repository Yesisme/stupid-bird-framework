package com.lym.spring.framework.test.v1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.lym.spring.framework.beans.BeanDefinition;
import com.lym.spring.framework.beans.factory.support.DefaultBeanFacotry;
import com.lym.spring.framework.beans.factory.xml.XmlBeanDefinitionReader;
import com.lym.spring.framework.core.io.ClassPathResource;
import com.lym.spring.framework.service.v1.ZooService;

public class BeanFactoryTest {

	DefaultBeanFacotry factory;
	XmlBeanDefinitionReader reader;
	
	@Before
	public void setUp() {
		factory = new DefaultBeanFacotry();
		reader = new XmlBeanDefinitionReader(factory);
	}
	
	@Test
	public void testGetBean() {
		reader.loadBeanDefinition(new ClassPathResource("zoo-v1.xml"));
		BeanDefinition bd = factory.getBeanDefinition("zoo");
		factory.setClassLoader(BeanFactoryTest.class.getClassLoader());
		assertTrue(bd.isSingleton());
		assertFalse(bd.isPrototype());
		assertEquals(bd.getScope(), BeanDefinition.SCOPE_DEFAULT);
		assertEquals(bd.getBeanClassName(), "com.lym.spring.framework.service.v1.ZooService");
		ZooService zooService = (ZooService) factory.getBean("zoo");
		assertNotNull(zooService);
		ZooService zooService1 = (ZooService) factory.getBean("zoo");
		assertEquals(zooService, zooService1);
	}
}
