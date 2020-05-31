//package com.chen.realproject.processor;
//
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.config.BeanDefinition;
//import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
//import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//@Component
//public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
//    @Override
//    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
//        String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
//        List<BeanDefinition> bdList = new ArrayList<>();
//        for (String beanDefinitionName : beanDefinitionNames) {
//            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanDefinitionName);
//            bdList.add(beanDefinition);
//        }
//        System.out.println(bdList);
//    }
//}
