package com.chen.annotationcac.conf;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

@Configuration
//@MapperScan({"com.chen.annotationcac.dao"})
@ComponentScan({"com.chen.annotationcac"})
@ImportResource("classpath:spring.xml")
//@Import(MySimpleMapper.class)
public class MybatisConfig {
    /**
     * 数据源
     * @return
     */
//    @Bean()
    public DataSource dataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/hsh_car_circle?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }

    /**
     * sqlSessionFactoryBean
     * @param dataSource
     * @return
     */
//    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactoryBean getSqlSessionFactoryBean(DataSource dataSource){
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("com.chen.annotationcac.dao"));
        return sqlSessionFactoryBean;
    }
}
