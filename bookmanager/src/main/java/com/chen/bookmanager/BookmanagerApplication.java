package com.chen.bookmanager;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
@EnableScheduling()
@EnableMethodCache(basePackages = "com.chen.bookmanager.jetcache")
@EnableCreateCacheAnnotation
public class BookmanagerApplication {

    public static void main(String[] args) {
        ImportBeanDefinitionRegistrar importBeanDefinitionRegistrar;
        AbstractApplicationContext abstractApplicationContext;
        SpringApplication.run(BookmanagerApplication.class, args);
    }

}
