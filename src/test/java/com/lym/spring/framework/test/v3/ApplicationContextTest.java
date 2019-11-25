package com.lym.spring.framework.test.v3;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.lym.spring.framework.context.ApplicationContext;
import com.lym.spring.framework.context.support.ClassPathApplicationContext;
import com.lym.spring.framework.dao.v3.AccuntDao;
import com.lym.spring.framework.dao.v3.ItemDao;
import com.lym.spring.framework.service.v3.ZooService;

public class ApplicationContextTest {

	@Test
	public void testApplicaitonContext() {
		ApplicationContext context = new ClassPathApplicationContext("zoo-v3.xml");
		ZooService zoo =(ZooService)context.getBean("zoo");
		assertNotNull(zoo.getAccuntDao());
		assertNotNull(zoo.getItemDao());
		assertNotNull(zoo.getVersion());
		
		assertTrue(zoo.getAccuntDao() instanceof AccuntDao);
		assertTrue(zoo.getItemDao() instanceof ItemDao);
		
	}
}
