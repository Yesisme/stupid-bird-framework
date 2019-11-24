package com.lym.spring.framework.test.v2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;

import com.lym.spring.framework.beans.SimpleTypeConvert;
import com.lym.spring.framework.beans.TypeConverter;
import com.lym.spring.framework.beans.TypeMismatchException;

public class TypeConverterTest {

	@Test
	public void testNumber() {
		TypeConverter typeConvert = new SimpleTypeConvert();
		Integer i = typeConvert.convertIfNeccessary("3", Integer.class);
		assertEquals(3, i.intValue());

		try {
			typeConvert.convertIfNeccessary("3.1", Integer.class);
		} catch (TypeMismatchException e) {
			return;
		}

		Assert.fail();
	}

	@Test
	public void testBoolean() {
		TypeConverter typeConverter = new SimpleTypeConvert();
		Boolean b = typeConverter.convertIfNeccessary("on", Boolean.class);
		assertEquals(true, b);

		try {
			typeConverter.convertIfNeccessary("xxxyyyzzz", Boolean.class);
		} catch (TypeMismatchException e) {
			return;
		}
		fail();
	}
}
