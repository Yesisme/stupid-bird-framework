package com.lym.spring.framework.test.v2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.lym.spring.framework.context.ApplicationContext;
import com.lym.spring.framework.context.support.ClassPathApplicationContext;
import com.lym.spring.framework.dao.v2.AccuntDao;
import com.lym.spring.framework.dao.v2.ItemDao;
import com.lym.spring.framework.service.v2.ZooService;

public class ApplicationContextTest {

	@Test
	public void testApplicationContext() {
		ApplicationContext context = new ClassPathApplicationContext("zoo-v2.xml");
		ZooService zoo = (ZooService) context.getBean("zoo");
		assertNotNull(zoo.getAccuntDao());
		assertNotNull(zoo.getItemDao());
		assertTrue(zoo.getAccuntDao() instanceof AccuntDao);
		assertTrue(zoo.getItemDao() instanceof ItemDao);
		assertEquals("lym", zoo.getOwner());
		assertEquals(2, zoo.getVersion());
	}
}
