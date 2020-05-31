package com.chen.mybatisfactories.conf;

import com.chen.mybatisfactories.Po.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
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
//@Import(Post.class)
public class AppConfig  /*implements TransactionManagementConfigurer */{

}
