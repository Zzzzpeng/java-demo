package com.chen.annotationcac.conf;

import com.chen.annotationcac.bean.MyImportBeanDefinitionRegistrar;
import org.springframework.boot.autoconfigure.AutoConfigurationImportSelector;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@ComponentScan("com.chen.annotationcac")
@Import(MyImportBeanDefinitionRegistrar.class)
//@Import(AutoConfigurationImportSelector.class)
//@EnableAutoConfiguration
public class AcConfig {
//    BeanDefinitionRegistryPostProcessor beanDefinitionRegistryPostProcessor;
//    org.mybatis.spring.SqlSessionFactoryBean sqlSessionFactoryBean;
//    MapperScannerConfigurer mapperScannerConfigurer;
//    AbstractApplicationContext abstractApplicationContext;
}
