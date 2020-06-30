package com.chen.realproject.processor;

import com.chen.realproject.conf.Person;
import com.chen.realproject.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

//@Component
@ConditionalOnBean(Article.class)
@ConditionalOnClass(Article.class)
//开启对@ConfigurationProperties注解的支持
@EnableConfigurationProperties(Person.class)
@Configuration
public class MyBeanFactoryPostProcessor implements BeanPostProcessor {
    @Autowired
    private Person person;

    public void show(){
        System.out.println(person);
    }
    public MyBeanFactoryPostProcessor() {
        System.out.println("MyBeanFactoryPostProcessor....instantiat");
    }


}
