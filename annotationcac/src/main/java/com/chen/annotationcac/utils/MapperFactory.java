package com.chen.annotationcac.utils;

import com.chen.annotationcac.dao.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.ApplicationContextInitializer;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

public class MapperFactory {
    private static SqlSessionFactory sqlSessionFactory;
    static {
        String resource = "mybatis/conf/mybatis-config.xml";
        InputStream inputStream;
        try {
            inputStream = Resources.getResourceAsStream(resource);
            //从 XML 中构建 SqlSessionFactory
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("*********Resources.getResourceAsStream(resource)....error*******");
        }

    }
    static SqlSession getSqlSession(){
        //获取session
        return sqlSessionFactory.openSession();
    }

    public static void main(String[] args) throws ClassNotFoundException {
//        MapperFactoryBean mapperFactoryBean;
//        Set set = new HashSet();
//        String name = null;
//        Optional<String> optional = Optional.ofNullable(name);
//        optional.ifPresent(set::add);
//        System.out.println(set);
        findOne();
//        Class.forName("com.mysql.jdbc.Driver");
    }

    static void findOne(){
        SqlSession sqlSession = null;
        try {
            sqlSession = getSqlSession();
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            Map postByid = mapper.getPostByUserId(999L);
            System.out.println(postByid);
            sqlSession.rollback();
        }finally {
            if (sqlSession != null)
                sqlSession.close();
        }
    }
    class Plugin implements Interceptor{

        @Override
        public Object intercept(Invocation invocation) throws Throwable {
            return null;
        }

        @Override
        public Object plugin(Object target) {
            return null;
        }

        @Override
        public void setProperties(Properties properties) {

        }
    }
}
