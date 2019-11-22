package com.lym.spring.framework.test.v1;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import com.lym.spring.framework.core.io.ClassPathResource;
import com.lym.spring.framework.core.io.FileSystemResource;
import com.lym.spring.framework.core.io.Resource;

public class ResourceTest {

	@Test
	public void testClassPath() {
		InputStream in =null;
		Resource r = new ClassPathResource("zoo-v1.xml");
		try {
			in = r.getInputStream();
			assertNotNull(in);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			if(in!=null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Test
	public void testFileSystem() {
		InputStream in =null;
		Resource r = new FileSystemResource("E:\\spring-gear\\zoo-v1.xml");
		try {
			in = r.getInputStream();
			assertNotNull(in);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			if(in!=null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
