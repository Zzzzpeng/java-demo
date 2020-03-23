package com.chen.annotationcac;

import com.chen.annotationcac.conf.AcConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AcConfig.class);

//        GenericBeanDefinition beanDefinition = (GenericBeanDefinition) ac.getBeanDefinition("acConfig");
//        beanDefinition.setBeanClass(NameService.class);
//        System.out.println(ac.getBean(UserService.class));
//        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
//        for (String beanDefinitionName : beanDefinitionNames) {
//            System.out.println(beanDefinitionName);
//        }

    }

}
