package com.lym.spring.framework.beans.propertyEditor;

import java.beans.PropertyEditorSupport;

import com.lym.spring.framework.utils.StringUtil;
/**
 * 转换boolean的实现类
 * @author hp
 *
 */
public class CustomBooleanEditor extends PropertyEditorSupport{

	private final String VALUE_YES ="yes";
	private final String VALUE_NO = "no";
	private final String VALUE_ON="on";
	private final String VALUE_OFF="off";
	private final String VALUE_TRUE ="true";
	private final String VALUE_FALSE="false";

	private final Boolean allowEmpty;
	
	public CustomBooleanEditor(Boolean allowEmpty) {
		this.allowEmpty = allowEmpty;
	}
	
	public void setAsText(String input) {
		if(this.allowEmpty && !StringUtil.hasLength(input)) {
			setValue(null);
		}else if(input.equals(VALUE_YES)||input.equals(VALUE_ON)||input.equals(VALUE_TRUE)) {
			setValue(Boolean.TRUE);
		}else if(input.equals(VALUE_NO)||input.equals(VALUE_OFF)||input.equals(VALUE_FALSE)) {
			setValue(Boolean.FALSE);
		}else {
			throw new IllegalArgumentException("the type"+input+"not implements");
		}
		
	}
	
	public String getAsText(String str) {
		if(Boolean.TRUE.equals(getValue())) {
			return VALUE_TRUE;
		}else if(Boolean.FALSE.equals(getValue())) {
			return VALUE_FALSE;
		}
		return "";
	}
}
