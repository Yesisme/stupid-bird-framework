package com.lym.spring.framework.context.support;

import com.lym.spring.framework.core.io.ClassPathResource;
import com.lym.spring.framework.core.io.Resource;

public class ClassPathApplicationContext extends AbstractApplicationContext{

	public ClassPathApplicationContext(String filePath) {
		super(filePath);
	}
	
	@Override
	public Resource getResourceByPath(String filePath) {
		return new ClassPathResource(filePath,this.getClassLoader());
	}


}
