package com.lym.spring.framework.test.v2;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

import com.lym.spring.framework.beans.propertyEditor.CustomBooleanEditor;

public class CustomBooleanEditorTest {

	@Test
	public void testCustomBoolean() {
		
		CustomBooleanEditor editor = new CustomBooleanEditor(true);
		
		editor.setAsText("true");
		assertEquals(true, ((Boolean)editor.getValue()).booleanValue());
		editor.setAsText("false");
		assertEquals(false, ((Boolean)editor.getValue()).booleanValue());
		
		editor.setAsText("on");
		assertEquals(true, ((Boolean)editor.getValue()).booleanValue());
		editor.setAsText("off");
		assertEquals(false, ((Boolean)editor.getValue()).booleanValue());
		
		editor.setAsText("yes");
		assertEquals(true, ((Boolean)editor.getValue()).booleanValue());
		editor.setAsText("no");
		assertEquals(false, ((Boolean)editor.getValue()).booleanValue());
		
		try {
			editor.setAsText("323.13");
		}catch(IllegalArgumentException e) {
			return;
		}
		Assert.fail();
	}
}
