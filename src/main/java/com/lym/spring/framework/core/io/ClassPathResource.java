package com.lym.spring.framework.core.io;

import java.io.InputStream;

import com.lym.spring.framework.utils.ClassUtil;
public class ClassPathResource implements Resource{
	
	private final String path;
	private final ClassLoader classLoader;
	
	
	public ClassPathResource(String filePath) {
		this(filePath,(ClassLoader)null);
	}
	
	public ClassPathResource(String filePath,ClassLoader classLoader) {
		this.path = filePath;
		this.classLoader = classLoader==null?ClassUtil.getDefaultClassLoader():classLoader;
	}

	@Override
	public InputStream getInputStream(){
		InputStream in = null;
		try {
			in = this.classLoader.getResourceAsStream(this.path);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return in;
	}

	@Override
	public String getDescription() {
		return this.path;
	}

}
