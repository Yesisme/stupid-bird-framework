package com.lym.spring.framework.test.v4;

import com.lym.spring.framework.beans.BeanDefinition;
import com.lym.spring.framework.beans.factory.support.DefaultBeanFacotry;
import com.lym.spring.framework.context.annotation.ClassPathBeanDefinitionScanner;
import com.lym.spring.framework.core.annotation.AnnotationAttributes;
import com.lym.spring.framework.core.annotation.ScannedGenericBeanDefinition;
import com.lym.spring.framework.core.stereotype.Component;
import com.lym.spring.framework.core.type.AnnotationMetadata;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ClassPathBeandefinitionScannerTest {

    @Test
    public void testParseScnnaerBean() {
        DefaultBeanFacotry factory = new DefaultBeanFacotry();
        String basePackage = "com.lym.spring.framework.dao.v4,com.lym.spring.framework.service.v4";
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(factory);

        scanner.doScanner(basePackage);

        String annotaiton = Component.class.getName();

        {
            BeanDefinition bd = factory.getBeanDefinition("zooService");

            ScannedGenericBeanDefinition sdb = (ScannedGenericBeanDefinition) bd;
            AnnotationMetadata annotationMeta = sdb.getAnnotationMeta();
            Assert.assertTrue(annotationMeta.hasAnnotation(annotaiton));
            AnnotationAttributes annotationAttribute = annotationMeta.getAnnotationAttribute(annotaiton);
            assertEquals("zooService",annotationAttribute.get("value"));
        }

        {
            BeanDefinition bd = factory.getBeanDefinition("itemDao");
            ScannedGenericBeanDefinition sdb = (ScannedGenericBeanDefinition) bd;
            AnnotationMetadata annotationMeta = sdb.getAnnotationMeta();
            Assert.assertTrue(annotationMeta.hasAnnotation(annotaiton));
            AnnotationAttributes annotationAttribute = annotationMeta.getAnnotationAttribute(annotaiton);
        }

        {
            BeanDefinition bd = factory.getBeanDefinition("accountDao");
            ScannedGenericBeanDefinition sdb = (ScannedGenericBeanDefinition) bd;
            AnnotationMetadata annotationMeta = sdb.getAnnotationMeta();
            Assert.assertTrue(annotationMeta.hasAnnotation(annotaiton));
            AnnotationAttributes annotationAttribute = annotationMeta.getAnnotationAttribute(annotaiton);
        }

    }
}
