package com.lym.spring.framework.test.v2;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.lym.spring.framework.beans.BeanDefinition;
import com.lym.spring.framework.beans.factory.PropertyValue;
import com.lym.spring.framework.beans.factory.config.RuntimeBeanReference;
import com.lym.spring.framework.beans.factory.support.DefaultBeanFacotry;
import com.lym.spring.framework.beans.factory.xml.XmlBeanDefinitionReader;
import com.lym.spring.framework.core.io.ClassPathResource;

public class BeanDefinitonTest {

	@Test
	public void testBeanDefinition() {
		DefaultBeanFacotry factory = new DefaultBeanFacotry();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		reader.loadBeanDefinition(new ClassPathResource("zoo-v2.xml"));
		BeanDefinition bd = factory.getBeanDefinition("zoo");
		List<PropertyValue> pvs = bd.getPropertyValues();
		assertTrue(pvs.size()==4);
		
		{
			PropertyValue accuntDao = this.getPropertyValue("accuntDao",pvs);
			assertNotNull(accuntDao);
			assertTrue(accuntDao.getValue() instanceof RuntimeBeanReference);
		}
		
		{
			PropertyValue itemDao = this.getPropertyValue("itemDao",pvs);
			assertNotNull(itemDao);
			assertTrue(itemDao.getValue() instanceof RuntimeBeanReference);
		}
		}

	private PropertyValue getPropertyValue(String beanName, List<PropertyValue> pvs) {
		for (PropertyValue pv : pvs) {
			if(beanName.equals(pv.getName())) {
				return pv;
			}
		}
		return null;
		
	}
}
