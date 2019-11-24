package com.lym.spring.framework.test.v2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;

import com.lym.spring.framework.beans.propertyEditor.CustomNumberEditor;

public class CustomNumberEditorTest {

	@Test
	public void testCustomNumberEditor() {
		CustomNumberEditor editor = new CustomNumberEditor(Integer.class, true);
		editor.setAsText("3");
		Object value = editor.getValue();
		assertTrue(value instanceof Integer);
		assertEquals(3, ((Integer)editor.getValue()).intValue());
		
		editor.setAsText("");
		assertTrue(editor.getValue() ==null);
		try {
			editor.setAsText("3.1");
		}catch(IllegalArgumentException e) {
			return;
		}
		Assert.fail();
		
	}
}
