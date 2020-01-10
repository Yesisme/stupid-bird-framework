package com.lym.spring.framework.test.v4;

import com.lym.spring.framework.core.annotation.AnnotationAttributes;
import com.lym.spring.framework.core.io.ClassPathResource;
import com.lym.spring.framework.core.stereotype.Component;
import com.lym.spring.framework.core.type.AnnotationMetadata;
import com.lym.spring.framework.core.type.classreading.MetadataReader;
import com.lym.spring.framework.core.type.classreading.SimpleMetadataReader;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class MetadateReaderTest {

    @Test
    public void testGetMetaData(){

        ClassPathResource resource = new ClassPathResource("com/lym/spring/framework/service/v4/ZooService.Class");
        MetadataReader reader = new SimpleMetadataReader(resource);
        AnnotationMetadata amd = reader.getAnnotationMetadata();
        String annotation = Component.class.getName();
        Assert.assertTrue(amd.hasAnnotation(annotation));
        AnnotationAttributes attributes = amd.getAnnotationAttribute(annotation);
        assertEquals("zooService", attributes.get("value"));
        assertFalse(amd.isAbstract());
        assertFalse(amd.isFinal());
        Assert.assertEquals("com.lym.spring.framework.service.v4.ZooService",amd.getClassName());

    }
}
