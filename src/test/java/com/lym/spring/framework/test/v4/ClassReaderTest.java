package com.lym.spring.framework.test.v4;

import com.lym.spring.framework.core.io.ClassPathResource;
import com.lym.spring.framework.core.type.classreading.ClassMetaReaderingVisitor;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.asm.ClassReader;

import java.io.IOException;

public class ClassReaderTest {

    @Test
    public void testGetClasMetaData() throws IOException {

        ClassPathResource resource = new ClassPathResource("com/lym/spring/framework/service/v4/ZooService.Class");

        ClassReader reader = new ClassReader(resource.getInputStream());

        ClassMetaReaderingVisitor visitor = new ClassMetaReaderingVisitor();

        reader.accept(visitor,ClassReader.SKIP_DEBUG);
        Assert.assertFalse(visitor.isAbstract());
        Assert.assertFalse(visitor.isFinal());
        Assert.assertFalse(visitor.isInterface());
        Assert.assertEquals(visitor.getClassName(),"com.lym.spring.framework.service.v4.ZooService");
        Assert.assertEquals("java.lang.Object",visitor.getSuperClassName());
        Assert.assertEquals(0, visitor.getInterfaces().length);
    }
}
