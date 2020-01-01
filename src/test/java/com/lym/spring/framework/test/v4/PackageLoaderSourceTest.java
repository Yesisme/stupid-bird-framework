package com.lym.spring.framework.test.v4;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.lym.spring.framework.core.io.Resource;
import com.lym.spring.framework.core.io.support.PackageSourceLoader;

import java.util.Arrays;

public class PackageLoaderSourceTest {

	@Test
	public void testPackageLoaderSource() {

		PackageSourceLoader loader = new PackageSourceLoader();
		Resource[] resource = loader.getResource("com.lym.spring.framework.dao.v4");
		assertTrue(2==resource.length);
		
	}
}
