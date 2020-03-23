package com.chen.annotationcac.bean;

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;


public class MySimpleMapper implements DeferredImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[] { MySimpleMapper.class.getName() };
    }
}
