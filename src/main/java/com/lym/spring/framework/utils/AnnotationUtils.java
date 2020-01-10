package com.lym.spring.framework.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;

public class AnnotationUtils {

	public static Annotation getAnnotation(AccessibleObject ao,Class<? extends Annotation> type) {
		Annotation ann = ao.getAnnotation(type);
		if(ann==null) {
			
		}
		return ann;
	}
	
}
