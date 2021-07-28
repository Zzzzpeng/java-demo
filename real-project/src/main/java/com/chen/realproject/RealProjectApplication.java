package com.chen.realproject;

import com.chen.realproject.conf.Person;
import com.chen.realproject.processor.MyBeanFactoryPostProcessor;
import com.chen.realproject.service.IArticleService;
import com.chen.realproject.service.impl.ArticleServiceImpl;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import sun.net.www.http.HttpClient;

import java.util.Map;
import java.util.Objects;

@EnableScheduling
@SpringBootApplication(scanBasePackages = {"com.chen.realproject"})
//@EnableApolloConfig
//@PropertySource("classpath:application.yml")
@EnableCaching
public class RealProjectApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext ac = SpringApplication.run(RealProjectApplication.class, args);
        String value = ac.getBeanFactory().resolveEmbeddedValue("${czpName}");
        System.out.println(ac.getEnvironment().getProperty("czpName"));

        System.out.println(ac.getBean(Person.class));
        ac.getBean(MyBeanFactoryPostProcessor.class).show();

//        System.out.println("finish");
//        IArticleService atService = ApplicationContextUtil.ac.getBean(IArticleService.class);
//        System.out.println(((ArticleServiceImpl) atService).name);
//        ArticleServiceImpl bean = ApplicationContextUtil.ac.getBean(ArticleServiceImpl.class);
//        System.out.println(bean.name);
//        bean.show();


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
