package com.lym.spring.framework.beans;

import java.beans.PropertyEditor;
import java.util.HashMap;
import java.util.Map;

import com.lym.spring.framework.beans.propertyEditor.CustomBooleanEditor;
import com.lym.spring.framework.beans.propertyEditor.CustomNumberEditor;
import com.lym.spring.framework.utils.ClassUtil;

public class SimpleTypeConvert implements TypeConverter{

	private Map<Class<?>, PropertyEditor> defaultEditors;
	
	public SimpleTypeConvert() {};
	
	@Override
	public <T> T convertIfNeccessary(Object value, Class<T> requireType) throws TypeMismatchException {
		if(ClassUtil.isAssignableValue(value, requireType)) {
			return (T) value;
		}
		if(value instanceof String) {
			PropertyEditor defaultEditor = findPropertyEditor(requireType);
			try {
				defaultEditor.setAsText((String)value);
			}catch(IllegalArgumentException e) {
				throw new TypeMismatchException(value, requireType);
			}
			return (T) defaultEditor.getValue();
		}else {
			throw new RuntimeException("Todo : can't convert value for "+value +" class:"+requireType);
		}
	}

	private PropertyEditor findPropertyEditor(Class<?> requireType) {
		PropertyEditor propertyEditor = this.getDefaultEditor(requireType);
		if(propertyEditor==null) {
			throw new RuntimeException("Editor for" + requireType + "has not been implemented");
		}
		return propertyEditor;
	}

	private PropertyEditor getDefaultEditor(Class<?> requireType) {
		if(this.defaultEditors==null) {
			 createDefaultEditor(requireType);
		}
		return this.defaultEditors.get(requireType);
	}

	private void createDefaultEditor(Class<?> requireType) {
		this.defaultEditors = new HashMap<>(64);
		this.defaultEditors.put(Integer.class, new CustomNumberEditor(Integer.class, true));
		this.defaultEditors.put(int.class,new CustomNumberEditor(Integer.class, false));
		this.defaultEditors.put(Boolean.class,new CustomBooleanEditor(true));
		this.defaultEditors.put(boolean.class, new CustomBooleanEditor(false));	
	}
	
}
