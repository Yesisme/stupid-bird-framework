package com.lym.spring.framework.core.io;

import java.io.InputStream;

public interface Resource {

	InputStream getInputStream();
	
	String getDescription();
}
