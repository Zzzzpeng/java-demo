package com.chen.mybatisfactories;

import com.chen.mybatisfactories.Po.Post;
import com.chen.mybatisfactories.bean.*;
import com.chen.mybatisfactories.conf.AppConfig;
import com.chen.mybatisfactories.mapper.UserMapper;
import com.chen.mybatisfactories.mapper.apollo.AppMapper;
import com.chen.mybatisfactories.service.Engine;
import com.chen.mybatisfactories.service.GoodService;
import com.chen.mybatisfactories.service.TestService;
import com.chen.mybatisfactories.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.*;

public class Test {



    public static void main(String[] args) throws Exception {
//        autoAopTest();
//        autoNameAopTest();
//        beanFactoryMergeAopTest();
//        mybatisTest();
//        springTransactionTest();
//        autoAopTest();
//        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
//        Engine engineFactoryBean = (Engine) ac.getBean("engineFactoryBean");
//        engineFactoryBean.fire();
//        ac.getBean(Car.class).work();
//        Object engine = ac.getBean("engine");
////        System.out.println(engine);


        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService bean = ac.getBean("userServiceImpl",UserService.class);

//        TestService testService = ac.getBean(TestService.class);
//        testService.transactionTest();


//        new Thread(() -> bean.updateForUpdate(5000)).start();
//        Thread.sleep(1100);
//        new Thread(() -> bean.getOne()).start();

//        bean.salee();

//        new Thread(()->bean.getOne()).start();
//        Thread.sleep(80);
//        new Thread(()->bean.update()).start();




        //可重复读&修改同一行数据,可重复读失效(dml语句是当前读,不再读开始版本而是读最新版本)
        //如果t1先修改,t1事务结束之前t2中的dml会阻塞
//        new Thread(()->bean.decrece(1,3000L),"t1").start();
//        Thread.sleep(500);
//        new Thread(()->bean.decrece(1,0),"t2").start();


        //间隙锁测试,线程1区间修改,线程2去读
        jianXiSuoTest(bean);

    }



    /**
     * 间隙锁测试,线程1区间修改,线程2去读
     * 事务中进行区间修改时,会锁区间,此时阻塞其它事务对这个区间的insert,del操作.
     * 如果条件修改不能命中索引,会导致锁表
     * @param bean
     * @throws InterruptedException
     */
    public static void jianXiSuoTest(UserService bean) throws InterruptedException {
        new Thread(()->bean.updateJianxi()).start();
//        Thread.sleep(1000);
//        new Thread(()->bean.addOne(3)).start();
//        new Thread(()->bean.addOne(3)).start();
    }



    @org.junit.Test
    public void mybatisTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        UserMapper bean = ac.getBean(UserMapper.class);
        System.out.println(bean.getOne());
    }

    @org.junit.Test
    public void mybatisTransationTest() throws InterruptedException {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService bean = (UserService) ac.getBean("userServiceImpl");
//        bean.getOne();
        new Thread(()->bean.getOne()).start();
        Thread.sleep(200);
        new Thread(()->bean.updateForUpdate(0)).start();
        System.out.println(bean.getOne());
    }

    public static void beanFactoryMergeAopTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        Car bean = ac.getBean(Car.class);
        bean.work();
    }
    @org.junit.Test
    public  void autoNameAopTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        Engine bean = ac.getBean("engine",Engine.class);
        System.out.println(bean);

        UserService bean1 = (UserService) ac.getBean("userServiceImpl");
        bean1.updateForUpdate(0);
//        UserService bean = (UserService) ac.getBean("myBean");
//        bean.update();

//        System.out.println(bean == bean1);
    }

    public static void autoAopTest(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService bean = (UserService) ac.getBean("userService");
        bean.updateForUpdate(0);
        ((GoodService) bean).sale(1, 1);
    }

    public static void aopTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService bean = (UserService) ac.getBean("bookDaoProxy");
        bean.updateForUpdate(0);
        ((GoodService) bean).sale(1, 1);
    }

    public static void springTransactionTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService bean = (UserService) ac.getBean("userService");
        bean.updateForUpdate(0);
    }

    @org.junit.Test
    public void importTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        Post bean = ac.getBean(Post.class);
        System.out.println(bean);
    }

    @org.junit.Test
    public void mybatisApolloTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppMapper bean = ac.getBean(AppMapper.class);
        System.out.println(bean.getAll());
    }
    @org.junit.Test
    public void placeHolderTest(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        ValueBean bean = ac.getBean(ValueBean.class);
        bean.show();
    }
    @org.junit.Test
    public void jdbcTest() throws SQLException {
        Connection connection = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            //1.注册驱动
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());

            //2.建立连接
            //方法一  参数一：协议+访问数据库，参数二：用户名，参数三：密码
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hsh_car?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=UTC", "root", "root");

            //方法二
//            DriverManager.getConnection("jdbc:msql://localhost/student?user=root&password=password");

            //3.创建statement，跟数据库打交道一定需要这个对象
            st = connection.createStatement();

            //4.执行查询
            String sql = "select * from stu";
            rs = st.executeQuery(sql);

            //5.遍历查询每一条记录
            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");

                System.out.println("id = " + id + "; name = " + name + "; age = " + age);
            }
            //进行资源释放
            rs.close();
            st.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
