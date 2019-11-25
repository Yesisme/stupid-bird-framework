package com.lym.spring.framework.test.v3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.lym.spring.framework.beans.BeanDefinition;
import com.lym.spring.framework.beans.factory.support.ConstructorResolver;
import com.lym.spring.framework.beans.factory.support.DefaultBeanFacotry;
import com.lym.spring.framework.beans.factory.xml.XmlBeanDefinitionReader;
import com.lym.spring.framework.core.io.ClassPathResource;
import com.lym.spring.framework.dao.v3.AccuntDao;
import com.lym.spring.framework.dao.v3.ItemDao;
import com.lym.spring.framework.service.v3.ZooService;

public class ConstructorResolverTest {

	@Test
	public void testConstructorResolver() {
		DefaultBeanFacotry beanFacotry = new DefaultBeanFacotry();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFacotry);
		beanFacotry.setClassLoader(ConstructorResolverTest.class.getClassLoader());
		reader.loadBeanDefinition(new ClassPathResource("zoo-v3.xml"));
		BeanDefinition bd = beanFacotry.getBeanDefinition("zoo");
		ConstructorResolver resolver = new ConstructorResolver(beanFacotry);
		ZooService zooService = (ZooService) resolver.autowireConstructor(bd);
		assertNotNull(zooService);
		
		assertTrue(zooService.getAccuntDao() instanceof AccuntDao);
		assertTrue(zooService.getItemDao() instanceof ItemDao);
		assertEquals(2, zooService.getVersion());
	}
}
