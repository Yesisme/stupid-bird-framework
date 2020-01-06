package com.lym.spring.framework.core.type.classreading;

import com.lym.spring.framework.core.io.Resource;
import com.lym.spring.framework.core.type.AnnotationMetadata;
import com.lym.spring.framework.core.type.ClassMetadata;
import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.springframework.asm.ClassReader;

import java.io.InputStream;

public class SimpleMetadataReader implements MetadataReader{


    private final Logger log = Logger.getLogger(SimpleMetadataReader.class);

    private final Resource resource;

    private final ClassMetadata classMetadata;

    private final AnnotationMetadata annotationMetadata;

    public SimpleMetadataReader(Resource resource) {

        InputStream in = resource.getInputStream();
        ClassReader reader = null;
        try {
            reader = new ClassReader(in);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e1) {
                    log.error(e1.getMessage());
                }
            }
            AnnotationMetaDataReaderingVisitor visitor = new AnnotationMetaDataReaderingVisitor();
            reader.accept(visitor, ClassReader.SKIP_DEBUG);
            this.resource = resource;
            this.classMetadata = visitor;
            this.annotationMetadata = visitor;

        }
    }

    @Override
    public ClassMetadata getClassMetadate() {

         return this.classMetadata;
    }

    @Override
    public Resource getResource() {
        return this.resource;
    }

    @Override
    public AnnotationMetadata getAnnotationMetadata() {
        return this.annotationMetadata;
    }
}
