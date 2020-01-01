package com.lym.spring.framework.core.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.lym.spring.framework.utils.Assert;

public class FileSystemResource implements Resource{
	
	private final File file;

	public FileSystemResource(File file) {
		Assert.notNull(file, "filepath cannot be null");
		this.file = file;
	}

	public FileSystemResource(String filePath) {
		Assert.notNull(filePath, "filepath cannot be null");
		this.file = new File(filePath);
	}
	
	@Override
	public InputStream getInputStream() {
		InputStream in = null;
		try {
			in = new FileInputStream(this.file);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return in;
	}

	@Override
	public String getDescription() {
		return this.file.getAbsolutePath();
	}

}
