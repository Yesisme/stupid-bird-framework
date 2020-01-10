package com.lym.spring.framework.test.v4;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.util.LinkedList;

import org.junit.Test;

import com.lym.spring.framework.beans.factory.annotation.AutowiredFieldElement;
import com.lym.spring.framework.beans.factory.annotation.InjectionElement;
import com.lym.spring.framework.beans.factory.annotation.InjectionMetadata;
import com.lym.spring.framework.beans.factory.support.DefaultBeanFacotry;
import com.lym.spring.framework.beans.factory.xml.XmlBeanDefinitionReader;
import com.lym.spring.framework.core.io.ClassPathResource;
import com.lym.spring.framework.dao.v4.AccountDao;
import com.lym.spring.framework.dao.v4.ItemDao;
import com.lym.spring.framework.service.v4.ZooService;

public class InjectMetadataTest {

	@Test
	public void testInjectMetaData() throws Exception, SecurityException {
		DefaultBeanFacotry facotry = new DefaultBeanFacotry();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(facotry);
		reader.loadBeanDefinition(new ClassPathResource("zoo-v4.xml"));
		Class<?> clz = ZooService.class;
		LinkedList<InjectionElement> els =  new LinkedList<>();
		
		{
			Field field = ZooService.class.getDeclaredField("accountDao");
			InjectionElement element = new AutowiredFieldElement(true, field, facotry);
			els.add(element);
		}
		
		{
		   Field f = ZooService.class.getDeclaredField("itemDao");	
		   InjectionElement element = new AutowiredFieldElement(true, f, facotry);
		   els.add(element);
		}
		
		{
			InjectionMetadata metadata = new InjectionMetadata(clz, els);
			ZooService zooService = new ZooService();
			metadata.inject(zooService);
			assertTrue(zooService.getAccountDao() instanceof AccountDao);
			assertTrue(zooService.getItemDao() instanceof ItemDao);
		}
	}
}
