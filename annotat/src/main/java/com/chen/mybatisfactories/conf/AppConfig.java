package com.chen.mybatisfactories.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.annotation.Resource;
import javax.annotation.Resources;
import javax.sql.DataSource;

@ComponentScan("com.chen.mybatisfactories")
@Configuration
@EnableTransactionManagement
@ImportResource("classpath:aop.xml")
public class AppConfig  /*implements TransactionManagementConfigurer */{

}
