package com.chen.realproject;

import com.chen.realproject.service.IArticleService;
import com.chen.realproject.service.impl.ArticleServiceImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@EnableScheduling
@SpringBootApplication(scanBasePackages = {"com.chen.realproject"})
//@EnableApolloConfig
//@PropertySource("classpath:application.yml")
public class RealProjectApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(RealProjectApplication.class, args);

        System.out.println("finish");
        IArticleService atService = ApplicationContextUtil.ac.getBean(IArticleService.class);
        System.out.println(((ArticleServiceImpl) atService).name);
        ArticleServiceImpl bean = ApplicationContextUtil.ac.getBean(ArticleServiceImpl.class);
        System.out.println(bean.name);
        bean.show();
//        SpringValueDefinitionProcessor springValueDefinitionProcessor;
//        PropertySourcesP rocessor propertySourcesProcessor;
//        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer;
//        SpringValueProcessor springValueProcessor;
//        ApolloJsonValueProcessor apolloJsonValueProcessor;

    }

    public void test(){
        PropertyPlaceholderConfigurer propertyPlaceholderConfigurer;
    }
    @Component
    static class ApplicationContextUtil implements ApplicationContextAware{
        static ApplicationContext ac;
        @Override
        public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            ac = applicationContext;
        }
    }
}
