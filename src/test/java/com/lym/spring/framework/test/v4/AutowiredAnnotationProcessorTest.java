package com.lym.spring.framework.test.v4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lym.spring.framework.beans.factory.annotation.AutowiredAnnotationProcessor;
import com.lym.spring.framework.beans.factory.annotation.AutowiredFieldElement;
import com.lym.spring.framework.beans.factory.annotation.InjectionElement;
import com.lym.spring.framework.beans.factory.annotation.InjectionMetadata;
import com.lym.spring.framework.beans.factory.config.DependencyDescriptor;
import com.lym.spring.framework.beans.factory.support.DefaultBeanFacotry;
import com.lym.spring.framework.dao.v4.AccountDao;
import com.lym.spring.framework.dao.v4.ItemDao;
import com.lym.spring.framework.service.v4.ZooService;

public class AutowiredAnnotationProcessorTest {

	
	AccountDao accountDao = new AccountDao();
	ItemDao itemDao = new ItemDao();
	
	DefaultBeanFacotry factory = new DefaultBeanFacotry() {
		public Object resolveDependency(DependencyDescriptor descriptor) {
			if(descriptor.getFieldType().equals(AccountDao.class)) {
				return accountDao;
			}else if(descriptor.getFieldType().equals(ItemDao.class)) {
				return itemDao;
			}
			throw new RuntimeException("cannot has want to type");
		};
	};
	
	@Test
	public void testGetInjectionMetadata() {
		AutowiredAnnotationProcessor aap = new AutowiredAnnotationProcessor();
		aap.setBeanFactory(factory);
		InjectionMetadata InjectionMetadata = aap.buildAutowiredMetadata(ZooService.class);
		List<InjectionElement> injectionElements = InjectionMetadata.getInjectionElement();
		assertEquals(2, injectionElements.size());
		assertExitsFieldName(injectionElements,"accountDao");
		assertExitsFieldName(injectionElements,"itemDao");
		ZooService zooService = new ZooService();
		InjectionMetadata.inject(zooService);
		assertTrue(zooService.getAccountDao() instanceof AccountDao);
		assertTrue(zooService.getItemDao() instanceof ItemDao);
	}

	private void assertExitsFieldName(List<InjectionElement> injectionElements, String fieldName) {
		for (InjectionElement ij : injectionElements) {
			AutowiredFieldElement element = (AutowiredFieldElement)ij;
			Field f = element.getField();
			if(f.getName().equals(fieldName)) {
				return;
			}
		}	
		Assert.fail(fieldName+"does not exist");
	}
}
