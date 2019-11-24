package com.lym.spring.framework.test.v2;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.lym.spring.framework.beans.config.RuntimeBeanReference;
import com.lym.spring.framework.beans.config.TypedStringValue;
import com.lym.spring.framework.beans.factory.support.BeanDefinitionValueResolver;
import com.lym.spring.framework.beans.factory.support.DefaultBeanFacotry;
import com.lym.spring.framework.beans.factory.xml.XmlBeanDefinitionReader;
import com.lym.spring.framework.core.io.ClassPathResource;
import com.lym.spring.framework.dao.v2.AccuntDao;

public class BeanDefinitonValueResovlerTest {

	private DefaultBeanFacotry beanFactory; 
	
	private BeanDefinitionValueResolver resovler;
	
	@Before
	public void setUp() {
		beanFactory = new DefaultBeanFacotry();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
		reader.loadBeanDefinition(new ClassPathResource("zoo-v2.xml"));
		beanFactory.setClassLoader(BeanDefinitonValueResovlerTest.class.getClassLoader());
		resovler = new BeanDefinitionValueResolver(beanFactory);
		
	}
	
	@Test
	public void testRuntimeBeanReference() {
		RuntimeBeanReference runtimeBeanReference = new RuntimeBeanReference("accuntDao");
		Object accuntDaoBean = this.resovler.resovlerValueIfNecessary(runtimeBeanReference);
		assertNotNull(accuntDaoBean);
		assertTrue(accuntDaoBean instanceof AccuntDao);
	}
	
	@Test
	public void testTypedStringValue() {
		TypedStringValue value = new TypedStringValue("test");
		Object originValue = this.resovler.resovlerValueIfNecessary(value);
		assertNotNull(originValue);
		assertTrue(originValue.equals("test"));
	}
}
