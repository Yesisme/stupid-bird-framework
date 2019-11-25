package com.lym.spring.framework.beans.factory.support;

import java.lang.reflect.Constructor;
import java.util.List;

import com.lym.spring.framework.beans.BeanDefinition;
import com.lym.spring.framework.beans.ConstructorArgment;
import com.lym.spring.framework.beans.ConstructorArgment.ValueHolder;
import com.lym.spring.framework.beans.SimpleTypeConvert;
import com.lym.spring.framework.beans.factory.BeanDifinitionStoreException;
import com.lym.spring.framework.beans.factory.BeanFactory;
import com.lym.spring.framework.utils.ClassUtil;

/**
 * 解析构造函数的实现类
 * 
 * @author hp
 *
 */
public class ConstructorResolver {

	private BeanFactory beanFactory;

	public ConstructorResolver(BeanFactory beanFactory) {
		this.beanFactory = beanFactory;
	}

	public Object autowireConstructor(BeanDefinition bd) {

		Constructor<?> constructorToUse = null;

		Object[] argToUse = null;

		Class<?> beanClass = null;

		try {
			beanClass = ClassUtil.getDefaultClassLoader().loadClass(bd.getBeanClassName());
		} catch (Exception e) {
			throw new BeanCreationException("");
		}

		SimpleTypeConvert convert = new SimpleTypeConvert();

		BeanDefinitionValueResolver resolver = new BeanDefinitionValueResolver(this.beanFactory);

		ConstructorArgment arg = bd.getConstructorArgment();

		//获取类中的所有构造方法
		Constructor<?>[] conditainal = beanClass.getConstructors();
		
		for (int i = 0; i < conditainal.length; i++) {
			//获取这个构造方法中参数的个人
			 Class<?>[] parameterTypes = conditainal[i].getParameterTypes();
			 //如果不等zoo-v3.xml中构造函数的个数
			if (parameterTypes.length != arg.getArgumentCount()) {
				continue;
			}
			
			argToUse = new Object[parameterTypes.length];

			boolean result = this.valuesMatchTypes(parameterTypes,arg.getValueHolders(), argToUse, convert, resolver);
			
			if(result) {
				constructorToUse = conditainal[i] ;
			}
			
		}
		if(constructorToUse == null) {
			throw new BeanCreationException(bd.getId()+"cannot find a apporiate construtor");
		}
		
		try {
			return constructorToUse.newInstance(argToUse);
		}catch(Exception e) {
			throw new BeanDifinitionStoreException("init bean exception");
		}
	}

	public boolean valuesMatchTypes(Class<?>[] parameterTypes,List<ValueHolder> ValueHolders, Object[] argsToUse, SimpleTypeConvert convert, BeanDefinitionValueResolver resovler) {

		for (int i=0;i<parameterTypes.length;i++) {
			ConstructorArgment.ValueHolder valueHolder = ValueHolders.get(i);
			Object originValue = valueHolder.getValue();
			try {
				Object resovlerValue = resovler.resovlerValueIfNecessary(originValue);
				Object convertValue = convert.convertIfNeccessary(resovlerValue, parameterTypes[i]);
				argsToUse[i] = convertValue;
			}catch(Exception e) {
				return false;
			}
		}
		return true;
	}
}
