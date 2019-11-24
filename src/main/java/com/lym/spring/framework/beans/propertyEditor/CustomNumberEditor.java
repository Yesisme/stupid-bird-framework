package com.lym.spring.framework.beans.propertyEditor;

import java.beans.PropertyEditorSupport;
import java.text.NumberFormat;

import com.lym.spring.framework.utils.NumberUtil;
import com.lym.spring.framework.utils.StringUtil;
/**
 * 转换数字的实现类
 * @author hp
 *
 */
public class CustomNumberEditor extends PropertyEditorSupport {

	
	private final Class<? extends Number> numberClass;
	
	private final boolean allowEmpty;
	
	private final NumberFormat numberFormat;
	
	public CustomNumberEditor(Class<? extends Number> numberClass,boolean allowEmpty) {
		this(numberClass,allowEmpty,null);
	}
	
	public CustomNumberEditor(Class<? extends Number> numberClass,boolean allowEmpty,NumberFormat numberFormat) {
		if(numberClass==null || !Number.class.isAssignableFrom(numberClass)) {
			throw new IllegalArgumentException("the property must be subClass of Number");
		}
		this.allowEmpty =allowEmpty;
		this.numberClass=numberClass;
		this.numberFormat =numberFormat;
	}
	
	public void setAsText(String text) {
		if(this.allowEmpty && !StringUtil.hasText(text)) {
			setValue(null);
		}else if(this.numberFormat!=null) {
			setValue(NumberUtil.parseNumber(text, this.numberClass,this.numberFormat));
		}else {
			setValue(NumberUtil.parseNumber(text,this.numberClass));
		}
	}
	
	public void setValue(Object value) {
		if(value instanceof Number) {
			super.setValue(NumberUtil.convertNumberToTargetClass((Number) value, this.numberClass));
		}else {
			super.setValue(value);
		}
	}
	
	public String getAsText() {
		Object value = getValue();
		if(value==null) {
			return "";
		}else if(this.numberFormat!=null) {
			return this.numberFormat.format(value);
		}else {
			return value.toString();
		}
	}
}
