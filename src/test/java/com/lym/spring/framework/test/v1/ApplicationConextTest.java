package com.lym.spring.framework.test.v1;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.lym.spring.framework.context.ApplicationContext;
import com.lym.spring.framework.context.support.AbstractApplicationContext;
import com.lym.spring.framework.context.support.ClassPathApplicationContext;
import com.lym.spring.framework.context.support.FileSystemApplicationContext;
import com.lym.spring.framework.service.v1.ZooService;

public class ApplicationConextTest {

	@Test
	public void testClassPathApplicationContext() {
		AbstractApplicationContext context = new ClassPathApplicationContext("zoo-v1.xml");
		context.setClassLoader(ApplicationConextTest.class.getClassLoader());
		ZooService zooService = (ZooService) context.getBean("zoo");
		assertNotNull(zooService);	
	}
	
	
	@Test
	public void testFileSystemApplication() {
		ApplicationContext context = new FileSystemApplicationContext("E:\\spring-gear\\zoo-v1.xml");
		ZooService zooService = (ZooService) context.getBean("zoo");
		assertNotNull(zooService);
	}
	
	
}
