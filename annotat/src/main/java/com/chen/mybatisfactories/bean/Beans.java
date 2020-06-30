package com.chen.mybatisfactories.bean;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import org.springframework.transaction.config.JtaTransactionManagerFactoryBean;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

import static java.lang.Thread.currentThread;

public class Beans {
    @Configuration
    static class Hsh_Db {
        @Bean(name = "dataSource")
        public DataSource dataSource() {
            System.out.println("哈哈哈");
            DruidDataSource dataSource = new DruidDataSource();
            dataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dataSource.setUrl("jdbc:mysql://localhost:3306/hsh_car?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=UTC");
            dataSource.setUsername("root");
            dataSource.setPassword("root");
            return dataSource;
        }

        @Bean(name = "sqlSessionFactory")
        public SqlSessionFactoryBean getSqlSessionFactoryBean(@Qualifier("dataSource") DataSource dataSource,
                                                              ApplicationContext ac) {
            SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
            sqlSessionFactoryBean.setDataSource(dataSource);
            sqlSessionFactoryBean.setConfigLocation(ac.getResource("classpath:mybatis-conf.xml"));
            return sqlSessionFactoryBean;
        }

        @Bean(name = "hshMapperScannerConfigurer")
        public MapperScannerConfigurer mapperScannerConfigurer(@Qualifier("sqlSessionFactory") SqlSessionFactory sessionFactoryBean) {
            MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
            mapperScannerConfigurer.setSqlSessionFactory(sessionFactoryBean);
            mapperScannerConfigurer.setBasePackage("com.chen.mybatisfactories.mapper");
            return mapperScannerConfigurer;
        }

        @Bean
        public PlatformTransactionManager annotationDrivenTransactionManager(@Qualifier("dataSource") DataSource dataSource) {
            return new DataSourceTransactionManager(dataSource);
        }

//        @Bean(name = "transactionManager")
//        public DataSourceTransactionManager transactionManager(@Qualifier("dataSource") DataSource dataSource) {
//            DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
//            transactionManager.setDataSource(dataSource);
//            return transactionManager;
//        }


    }

    @Configuration
    static class Exam_Db {
        @Bean(name = "exam")
        public DataSource dataSource() {
            DruidDataSource dataSource = new DruidDataSource();
            dataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dataSource.setUrl("jdbc:mysql://localhost:3306/exam?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=UTC");
            dataSource.setUsername("root");
            dataSource.setPassword("root");
            return dataSource;
        }

        /**
         * sqlSessionFactoryBean
         *
         * @param dataSource
         * @return
         */
        @Bean(name = "scSqlSessionFactory")
        public SqlSessionFactoryBean getSqlSessionFactoryBean(@Qualifier("exam") DataSource dataSource) {
            SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
            sqlSessionFactoryBean.setDataSource(dataSource);
//            sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("这里指定mybatis配置文件"));
            return sqlSessionFactoryBean;
        }

        @Bean(name = "examMapperScannerConfigurer")
        public MapperScannerConfigurer mapperScannerConfigurer(@Qualifier("scSqlSessionFactory") SqlSessionFactory sessionFactoryBean) {
            MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
            mapperScannerConfigurer.setSqlSessionFactory(sessionFactoryBean);
            mapperScannerConfigurer.setBasePackage("com.chen.mybatisfactories.smapper");
            return mapperScannerConfigurer;
        }
    }

    @Configuration
    static class Apollo_Db {
        @Bean(name = "apolloDb")
        public DataSource dataSource() {
            DruidDataSource dataSource = new DruidDataSource();
            dataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dataSource.setUrl("jdbc:mysql://112.74.85.207:33306/ApolloConfigDB?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC");
            dataSource.setUsername("root");
            dataSource.setPassword("root");
            return dataSource;
        }

        /**
         * sqlSessionFactoryBean
         *
         * @param dataSource
         * @return
         */
        @Bean(name = "apolloSqlSessionFactory")
        public SqlSessionFactoryBean getSqlSessionFactoryBean(@Qualifier("apolloDb") DataSource dataSource) {
            SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
            sqlSessionFactoryBean.setDataSource(dataSource);
//            sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("这里指定mybatis配置文件"));
            return sqlSessionFactoryBean;
        }

        @Bean(name = "apolloMapperScannerConfigurer")
        public MapperScannerConfigurer mapperScannerConfigurer(@Qualifier("apolloSqlSessionFactory") SqlSessionFactory sessionFactoryBean) {
            MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
            mapperScannerConfigurer.setSqlSessionFactory(sessionFactoryBean);
            mapperScannerConfigurer.setBasePackage("com.chen.mybatisfactories.mapper.apollo");
            return mapperScannerConfigurer;
        }
    }


//    public Beans() {
//        System.out.println(".....................bean................");
//    }
//
//    @Bean()
//    public DataSource dataSource(){
//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://localhost:3306/hsh_car_circle?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=UTC");
//        dataSource.setUsername("root");
//        dataSource.setPassword("root");
//        return dataSource;
//    }
//
//    /**
//     * sqlSessionFactoryBean
//     * @param dataSource
//     * @return
//     */
//    @Bean(name = "sqlSessionFactory")
//    public SqlSessionFactoryBean getSqlSessionFactoryBean(DataSource dataSource){
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setDataSource(dataSource);
////        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("com.chen.mybatisfactories.mapper"));
//        return sqlSessionFactoryBean;
//    }
//
//    @Bean
//    public MapperScannerConfigurer mapperScannerConfigurer(SqlSessionFactory sessionFactoryBean){
//        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
//        mapperScannerConfigurer.setSqlSessionFactory(sessionFactoryBean);
//        mapperScannerConfigurer.setBasePackage("com.chen.mybatisfactories.mapper");
//        return mapperScannerConfigurer;
//    }

}
