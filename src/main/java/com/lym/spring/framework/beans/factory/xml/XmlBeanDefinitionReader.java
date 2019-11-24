package com.lym.spring.framework.beans.factory.xml;


import java.io.InputStream;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.lym.spring.framework.beans.BeanDefinition;
import com.lym.spring.framework.beans.config.RuntimeBeanReference;
import com.lym.spring.framework.beans.config.TypedStringValue;
import com.lym.spring.framework.beans.factory.BeanDifinitionStoreException;
import com.lym.spring.framework.beans.factory.PropertyValue;
import com.lym.spring.framework.beans.factory.support.BeanDefinitionRegistry;
import com.lym.spring.framework.beans.factory.support.GenericBeanDefinition;
import com.lym.spring.framework.core.io.Resource;

public class XmlBeanDefinitionReader {

	private BeanDefinitionRegistry registry;
	
	private final String ATTRIBUTE_ID = "id";
	
	private final String ATTRIBUTE_CLASS = "class";
	
	private final String ATTRIBUTE_SCOPE = "scope";
	
	private final String ATTRIBUTE_PROPERTY="property";
	
	private final String ATTRIBUTE_NAME = "name";
	
	private final String ATTRIBUTE_REF ="ref";
	
	private final String ATTRIBUTE_VALUE ="value";
	
	Log log = LogFactory.getLog(XmlBeanDefinitionReader.class);
	
	public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
		this.registry = registry;
	}
	
	public void loadBeanDefinition(Resource resource) {
		SAXReader reader = new SAXReader();
		InputStream in = resource.getInputStream();
		try {
			Document doc = reader.read(in);
			Element root = doc.getRootElement();//<beans>
			Iterator ite = root.elementIterator();
			while(ite.hasNext()) {
				Element ele = (Element) ite.next();//<bean>
				String beanId = ele.attributeValue(ATTRIBUTE_ID);
				String className = ele.attributeValue(ATTRIBUTE_CLASS);
				BeanDefinition bd = new GenericBeanDefinition(beanId,className);
				String scope = root.attributeValue(ATTRIBUTE_SCOPE);
				if(root.attributeValue(ATTRIBUTE_SCOPE)!=null) {
					bd.setScope(root.attributeValue(ATTRIBUTE_SCOPE));
				}
				parsePropertyElement(ele,bd);
				this.registry.registryBeanDefinition(beanId, bd);
			}
		}catch(Exception e) {
			throw new BeanDifinitionStoreException("can not load" + resource.getDescription() + "please check it");
		}finally {
			if(in!=null) {
				try {
					in.close();
				}catch(Exception e) {
					System.out.println("io close fail");
				}
			}
		}
	}

	//传入一个<bean>
	private void parsePropertyElement(Element element, BeanDefinition bd) {
		Iterator ite = element.elementIterator(ATTRIBUTE_PROPERTY);
		while(ite.hasNext()) {
			Element propertyEle = (Element) ite.next();
			String nameValue = propertyEle.attributeValue(ATTRIBUTE_NAME);
			Object obj = parsePropertyValue(propertyEle,nameValue);
			PropertyValue propertyValue = new PropertyValue(nameValue, obj);
			bd.getPropertyValues().add(propertyValue);		
		}
	}

	//解析<property>
	private Object parsePropertyValue(Element propertyEle, String nameValue) {
		boolean hasRefValue = propertyEle.attribute(ATTRIBUTE_REF)!=null;
		boolean hasOriginValue = propertyEle.attribute(ATTRIBUTE_VALUE)!=null;
		if(hasRefValue) {
			String refValue = propertyEle.attributeValue(ATTRIBUTE_REF);
			RuntimeBeanReference runtimeBeanReference = new RuntimeBeanReference(refValue);
			return runtimeBeanReference;
		}else if(hasOriginValue) {
			String originValue = propertyEle.attributeValue(ATTRIBUTE_VALUE);
			TypedStringValue typedStringValue = new TypedStringValue(originValue);
			return typedStringValue;
		}else {
			throw new RuntimeException(propertyEle +"must specify a ref or value");
		}
	}
}
