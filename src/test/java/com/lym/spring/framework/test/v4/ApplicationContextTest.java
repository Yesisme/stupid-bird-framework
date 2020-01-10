package com.lym.spring.framework.test.v4;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.lym.spring.framework.context.ApplicationContext;
import com.lym.spring.framework.context.support.ClassPathApplicationContext;
import com.lym.spring.framework.service.v4.ZooService;

public class ApplicationContextTest {

	@Test
	public void testApplicaitonContext() {
		ApplicationContext context = new ClassPathApplicationContext("zoo-v4.xml");
		ZooService zooService = (ZooService)context.getBean("zooService");
		assertNotNull(zooService.getAccountDao());
		assertNotNull(zooService.getItemDao());
	}
}
