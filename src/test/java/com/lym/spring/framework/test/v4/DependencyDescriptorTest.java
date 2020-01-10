package com.lym.spring.framework.test.v4;

import java.lang.reflect.Field;

import org.junit.Assert;
import org.junit.Test;

import com.lym.spring.framework.beans.factory.config.DependencyDescriptor;
import com.lym.spring.framework.beans.factory.support.DefaultBeanFacotry;
import com.lym.spring.framework.beans.factory.xml.XmlBeanDefinitionReader;
import com.lym.spring.framework.core.io.ClassPathResource;
import com.lym.spring.framework.dao.v4.AccountDao;
import com.lym.spring.framework.service.v4.ZooService;

public class DependencyDescriptorTest {

    @Test
    public void testDependencyDescriptor() throws NoSuchFieldException {
        DefaultBeanFacotry facotry = new DefaultBeanFacotry();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(facotry);
        reader.loadBeanDefinition(new ClassPathResource("zoo-v4.xml"));
        Field f = ZooService.class.getDeclaredField("accountDao");
        DependencyDescriptor descritor = new DependencyDescriptor(f,true);
        
        Object o = facotry.resolveDependency(descritor);
        Assert.assertTrue(o instanceof AccountDao);
    }
}
