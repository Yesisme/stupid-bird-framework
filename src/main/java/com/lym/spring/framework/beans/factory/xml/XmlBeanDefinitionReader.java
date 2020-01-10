package com.lym.spring.framework.beans.factory.xml;


import java.io.InputStream;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.lym.spring.framework.beans.BeanDefinition;
import com.lym.spring.framework.beans.factory.BeanDifinitionStoreException;
import com.lym.spring.framework.beans.factory.PropertyValue;
import com.lym.spring.framework.beans.factory.config.RuntimeBeanReference;
import com.lym.spring.framework.beans.factory.config.TypedStringValue;
import com.lym.spring.framework.beans.factory.support.BeanDefinitionRegistry;
import com.lym.spring.framework.beans.factory.support.GenericBeanDefinition;
import com.lym.spring.framework.context.annotation.ClassPathBeanDefinitionScanner;
import com.lym.spring.framework.core.io.Resource;
import com.lym.spring.framework.utils.StringUtil;

public class XmlBeanDefinitionReader {

	private BeanDefinitionRegistry registry;
	
	private final String ATTRIBUTE_ID = "id";
	
	private final String ATTRIBUTE_CLASS = "class";
	
	private final String ATTRIBUTE_SCOPE = "scope";
	
	private final String ATTRIBUTE_PROPERTY="property";
	
	private final String ATTRIBUTE_NAME = "name";
	
	private final String ATTRIBUTE_REF ="ref";
	
	private final String ATTRIBUTE_VALUE ="value";
	
	private final String ATTRIBUTE_CONSTRUCTOR="constructor-arg";
	
	private final String ATTRIBUTE_TYPE = "type";

	public static final String BEANS_NAMESPACE_URI = "http://www.springframework.org/schema/beans";

	public static final String CONTEXT_NAMESPACE_URI = "http://www.springframework.org/schema/context";

	public static final String BASE_PACKAGE_ATTRIBUTE = "base-package";
	
	Log log = LogFactory.getLog(XmlBeanDefinitionReader.class);
	
	public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
		this.registry = registry;
	}
	
	public void loadBeanDefinition(Resource resource) {

		InputStream in = null;
		try {
			in = resource.getInputStream();
			SAXReader reader = new SAXReader();
			Document doc = reader.read(in);
			Element root = doc.getRootElement();//<beans>
			Iterator ite = root.elementIterator();
			while(ite.hasNext()) {
				Element ele = (Element) ite.next();//<bean>
				String namespaceURI = ele.getNamespaceURI();
				if(this.isDefaultNamespace(namespaceURI)){
					parseDefaultElement(ele);
				}else if(this.isContextNamespace(namespaceURI)){
					parseComponentElement(ele);
				}

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

	private void parseDefaultElement(Element ele){
		String beanId = ele.attributeValue(ATTRIBUTE_ID);
		String className = ele.attributeValue(ATTRIBUTE_CLASS);
		BeanDefinition bd = new GenericBeanDefinition(beanId,className);
		if(ele.attributeValue(ATTRIBUTE_SCOPE)!=null) {
			bd.setScope(ele.attributeValue(ATTRIBUTE_SCOPE));
		}
		//解析构造constructorArgment
		parseConstructorArgElements(ele,bd);
		//接续property
		parsePropertyElement(ele,bd);
		this.registry.registryBeanDefinition(beanId, bd);
	}

	private void parseComponentElement(Element element) {
		String basePackage = element.attributeValue(BASE_PACKAGE_ATTRIBUTE);
		ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(registry);
		scanner.doScan(basePackage);
	}

	private void parseConstructorArgElements(Element beanEle, BeanDefinition bd) {
		
		Iterator ite = beanEle.elementIterator(ATTRIBUTE_CONSTRUCTOR);
		while(ite.hasNext()) {
			Element construtorEle = (Element) ite.next();
			parseConstructorArgElement(construtorEle,bd);
		}
		
	}
	
	public void parseConstructorArgElement(Element construtorEle, BeanDefinition bd) {
		  construtorEle.attribute(ATTRIBUTE_TYPE);
		  construtorEle.attribute(ATTRIBUTE_NAME);
		  Object obj = parsePropertyValue(construtorEle, null);
		  bd.getConstructorArgment().addArgumentValue(obj);
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

	public boolean isDefaultNamespace(String namespaceUri) {
		return (!StringUtil.hasLength(namespaceUri) || BEANS_NAMESPACE_URI.equals(namespaceUri));
	}

	public boolean isContextNamespace(String namespaceUri) {
		return (!StringUtil.hasLength(namespaceUri) || CONTEXT_NAMESPACE_URI.equals(namespaceUri));
	}
}
