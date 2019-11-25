package com.lym.spring.framework.test.v3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.lym.spring.framework.beans.BeanDefinition;
import com.lym.spring.framework.beans.ConstructorArgment.ValueHolder;
import com.lym.spring.framework.beans.config.RuntimeBeanReference;
import com.lym.spring.framework.beans.config.TypedStringValue;
import com.lym.spring.framework.beans.factory.support.DefaultBeanFacotry;
import com.lym.spring.framework.beans.factory.xml.XmlBeanDefinitionReader;
import com.lym.spring.framework.core.io.ClassPathResource;

public class BeanDefinitonTest {

	
	@Test
	public void testBeanDefinition() {
		DefaultBeanFacotry beanFacotry = new DefaultBeanFacotry();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFacotry);
		beanFacotry.setClassLoader(BeanDefinitonTest.class.getClassLoader());
		reader.loadBeanDefinition(new ClassPathResource("zoo-v3.xml"));
		BeanDefinition bd = beanFacotry.getBeanDefinition("zoo");
		assertEquals(bd.getBeanClassName(), "com.lym.spring.framework.service.v3.ZooService");
		assertTrue(bd.getConstructorArgment().getArgumentCount()==3);
		
		List<ValueHolder> valueHolders = bd.getConstructorArgment().getValueHolders();
		
		RuntimeBeanReference rf1 = (RuntimeBeanReference) valueHolders.get(0).getValue();
		assertEquals("accuntDao",rf1.getBeanName());
		
		RuntimeBeanReference rf2 = (RuntimeBeanReference) valueHolders.get(1).getValue();
		assertEquals("itemDao", rf2.getBeanName());
		
		TypedStringValue rf3 = (TypedStringValue) valueHolders.get(2).getValue();
		assertEquals("2",rf3.getValue());
		
	}
}
