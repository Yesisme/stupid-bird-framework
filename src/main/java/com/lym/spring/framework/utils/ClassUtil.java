package com.lym.spring.framework.utils;

import java.util.HashMap;
import java.util.Map;

public abstract class ClassUtil {
	
	private static final Map<Class<?>,Class<?>> wrapperToPrimitiveTypeMap = new HashMap<>();
	
	private static final Map<Class<?>,Class<?>> primitiveTypeMapToWrapper = new HashMap<>();
	
	static {
		wrapperToPrimitiveTypeMap.put(Boolean.class, boolean.class);
		wrapperToPrimitiveTypeMap.put(Byte.class, byte.class);
		wrapperToPrimitiveTypeMap.put(Character.class, char.class);
		wrapperToPrimitiveTypeMap.put(Double.class, double.class);
		wrapperToPrimitiveTypeMap.put(Float.class, float.class);
		wrapperToPrimitiveTypeMap.put(Integer.class, int.class);
		wrapperToPrimitiveTypeMap.put(Long.class, long.class);
		wrapperToPrimitiveTypeMap.put(Short.class, short.class);

		for (Map.Entry<Class<?>, Class<?>> classClassEntry : wrapperToPrimitiveTypeMap.entrySet()) {
			primitiveTypeMapToWrapper.put(classClassEntry.getValue(), classClassEntry.getKey());
		}
	}
	
	//判断类型是否需要转换
	public static boolean isAssignableValue(Object value,Class<?> requireType) {
		Assert.notNull(requireType, "转换类型不能为空");
		return (value!=null?isAssignable(value.getClass(),requireType):!requireType.isPrimitive());
	}
	
	
	public static boolean isAssignable(Class<?> valueClass,Class<?>requireType) {
		Assert.notNull(valueClass, "valueClass cannot be null");
		Assert.notNull(requireType, "requireType cannot be null");
	
		if(valueClass.isAssignableFrom(requireType)) {
			return true;
		}
		
		//判断需要转换的类型是否是原始类型
		if(requireType.isPrimitive()) {
			//是从map中获取
			Class<?> resovlerPrimitive = wrapperToPrimitiveTypeMap.get(valueClass);
			if(resovlerPrimitive!=null && requireType.equals(resovlerPrimitive)) {
				return true;
			}
		}else {
			Class<?> resolvedWrapper = primitiveTypeMapToWrapper.get(valueClass);
			if(resolvedWrapper!=null && requireType.equals(resolvedWrapper)) {
				return true;
			}
		}
		return false;	
	}
	
	public static ClassLoader getDefaultClassLoader() {
		ClassLoader cl = null;
		//获取
		try {
			cl = Thread.currentThread().getContextClassLoader();
		}catch(Exception e) {
			
		}
		
		if(cl == null) {
			cl = ClassUtil.class.getClassLoader();
			
			if(cl==null) {
				try {
					cl = ClassLoader.getSystemClassLoader();
				}catch(Exception e) {
					
				}
			}
		}	
		return cl;
	}
	
	
}
