package com.chen.mybatisfactories.bean;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

public class Beans {
    @Configuration
    static class Hsh_Db{
        @Bean(name = "hsh")
        public DataSource dataSource(){
            DruidDataSource dataSource = new DruidDataSource();
            dataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dataSource.setUrl("jdbc:mysql://localhost:3306/hsh_car?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=UTC");
            dataSource.setUsername("root");
            dataSource.setPassword("root");
            return dataSource;
        }

        /**
         * sqlSessionFactoryBean
         * @param dataSource
         * @return
         */
        @Bean(name = "sqlSessionFactory")
        public SqlSessionFactoryBean getSqlSessionFactoryBean(@Qualifier("hsh") DataSource dataSource){
            SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
            sqlSessionFactoryBean.setDataSource(dataSource);
//            sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("这里指定mybatis配置文件"));
            return sqlSessionFactoryBean;
        }

        @Bean(name = "hshMapperScannerConfigurer")
        public MapperScannerConfigurer mapperScannerConfigurer(@Qualifier("sqlSessionFactory") SqlSessionFactory sessionFactoryBean){
            MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
            mapperScannerConfigurer.setSqlSessionFactory(sessionFactoryBean);
            mapperScannerConfigurer.setBasePackage("com.chen.mybatisfactories.mapper");
            return mapperScannerConfigurer;
        }
    }

    @Configuration
    static class Exam_Db{
        @Bean(name = "exam")
        public DataSource dataSource(){
            DruidDataSource dataSource = new DruidDataSource();
            dataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dataSource.setUrl("jdbc:mysql://localhost:3306/exam?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=UTC");
            dataSource.setUsername("root");
            dataSource.setPassword("root");
            return dataSource;
        }

        /**
         * sqlSessionFactoryBean
         * @param dataSource
         * @return
         */
        @Bean(name = "scSqlSessionFactory")
        public SqlSessionFactoryBean getSqlSessionFactoryBean(@Qualifier("exam")  DataSource dataSource){
            SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
            sqlSessionFactoryBean.setDataSource(dataSource);
//            sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("这里指定mybatis配置文件"));
            return sqlSessionFactoryBean;
        }

        @Bean(name = "examMapperScannerConfigurer")
        public MapperScannerConfigurer mapperScannerConfigurer(@Qualifier("scSqlSessionFactory") SqlSessionFactory sessionFactoryBean){
            MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
            mapperScannerConfigurer.setSqlSessionFactory(sessionFactoryBean);
            mapperScannerConfigurer.setBasePackage("com.chen.mybatisfactories.smapper");
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
