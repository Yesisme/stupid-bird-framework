package com.lym.spring.framework.context.annotation;


import com.lym.spring.framework.beans.BeanDefinition;
import com.lym.spring.framework.beans.factory.BeanDifinitionStoreException;
import com.lym.spring.framework.beans.factory.support.BeanDefinitionRegistry;
import com.lym.spring.framework.beans.factory.support.BeanNameGenerator;
import com.lym.spring.framework.beans.factory.support.DefaultBeanFacotry;
import com.lym.spring.framework.core.annotation.ScannedGenericBeanDefinition;
import com.lym.spring.framework.core.io.Resource;
import com.lym.spring.framework.core.io.support.PackageReSourceLoader;
import com.lym.spring.framework.core.stereotype.Component;
import com.lym.spring.framework.core.type.AnnotationMetadata;
import com.lym.spring.framework.core.type.classreading.MetadataReader;
import com.lym.spring.framework.core.type.classreading.SimpleMetadataReader;
import com.lym.spring.framework.utils.StringUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class ClassPathBeanDefinitionScanner {


    private final BeanDefinitionRegistry registry;

    private PackageReSourceLoader packageResourceLoader = new PackageReSourceLoader();

    private BeanNameGenerator beanNameGenerator = new AnnotationBeanNameGenerator();

    private final Log logger = LogFactory.getLog(getClass());


    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry){
        this.registry = registry;
    }


    public Set<BeanDefinition> doScan(String packageToScan) {

        Set<BeanDefinition> beanDefinitions = new LinkedHashSet<BeanDefinition>();
        String[] basePackages = StringUtil.tokenizeToStringArray(packageToScan,",");

        for(String basePackage : basePackages){
            Set<BeanDefinition> candidateComponents = findCandidateComponents(basePackage);
            for (BeanDefinition bd:candidateComponents) {
                beanDefinitions.add(bd);
                this.registry.registryBeanDefinition(bd.getId(),bd);
            }
        }
        return beanDefinitions;
    }


    private Set<BeanDefinition> findCandidateComponents(String basePackage){

        Set<BeanDefinition> beanDefinitions = new LinkedHashSet<>();

        Resource[] resources = packageResourceLoader.getResource(basePackage);

        for (Resource resource:resources) {

            try {
                MetadataReader reader = new SimpleMetadataReader(resource);
                if (reader.getAnnotationMetadata().hasAnnotation(Component.class.getName())){
                    AnnotationMetadata annotationMetadata = reader.getAnnotationMetadata();
                    ScannedGenericBeanDefinition sdb = new ScannedGenericBeanDefinition(annotationMetadata);
                    String beanName = this.beanNameGenerator.generateBeanName(sdb, this.registry);
                    sdb.setId(beanName);
                    beanDefinitions.add(sdb);
                }
            }catch (Exception e){
                throw new BeanDifinitionStoreException("Faild to reader candidate componetn class "+resource);
            }
        }
        return  beanDefinitions;
    }
}
