package com.lym.spring.framework.context.support;

import com.lym.spring.framework.core.io.FileSystemResource;
import com.lym.spring.framework.core.io.Resource;

public class FileSystemApplicationContext extends AbstractApplicationContext{

	public FileSystemApplicationContext(String filePath) {
		super(filePath);
	}

	@Override
	public Resource getResourceByPath(String filePath) {
		return new FileSystemResource(filePath);
	}

}
