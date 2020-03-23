package com.chen.annotationcac.conf;

import com.chen.annotationcac.bean.MyImportBeanDefinitionRegistrar;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@ComponentScan("com.chen.annotationcac")
//@Import(MyImportBeanDefinitionRegistrar.class)

public class AcConfig {
//    BeanDefinitionRegistryPostProcessor beanDefinitionRegistryPostProcessor;
//    org.mybatis.spring.SqlSessionFactoryBean sqlSessionFactoryBean;
//    MapperScannerConfigurer mapperScannerConfigurer;
//    AbstractApplicationContext abstractApplicationContext;
}
