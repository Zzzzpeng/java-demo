package com.chen.bookmanager;

import com.chen.bookmanager.dao.ClassMapper;
import com.chen.bookmanager.dao.MenuMapper;
import com.chen.bookmanager.dao.PayDescVoMapper;
import com.chen.bookmanager.dao.food.FoodMenuMapper;
import com.chen.bookmanager.dao.food.OrderDetailMapper;
import com.chen.bookmanager.model.PayDescVo;
import com.chen.bookmanager.model.foodorder.Menu;
import com.chen.bookmanager.model.foodorder.OrderDetail;
import com.chen.bookmanager.service.FoodMenuService;
import net.sf.json.JSONObject;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.AliasFor;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@org.mybatis.spring.boot.test.autoconfigure.MybatisTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE )
@RunWith(SpringRunner.class)
@ComponentScan("com.chen.bookmanager")
public class MybatisTest {

//    @Autowired
//    SqlSessionFactory sqlSessionFactory;
//
//    @Autowired
//    private MenuMapper menuMapper;
//
    @Autowired
    private com.chen.bookmanager.dao.food.FoodMenuMapper foodMenuMapper;
//
//    @Autowired
//    private ClassMapper classMapper;
//
//    @Autowired
//    private OrderDetailMapper orderDetailMapper;
//
    @Autowired
    FoodMenuService foodMenuService;


    @Autowired
    PayDescVoMapper payDescVoMapper;

    @Autowired
    SqlSession sqlSession;


    @Test
    public void booleanTest(){
//        Menu menu = new Menu("5","香辣牛蛙", BigDecimal.valueOf(222L),false);
//        foodMenuMapper.save(menu);
        System.out.println(foodMenuMapper.getMenus().toString());

    }





    @Test
    @Rollback(false)
    public void mutiInsert(){
        payDescVoMapper.selectAll();
    }




//    @Test
//    /*一级缓存仅会话内有效,不显示在'Cache Hit Ratio'中,经会话commit或者close才会写到二级缓存*/
//    public void cacheTest(){
//        SqlSession sqlSession1 = sqlSessionFactory.openSession();
//        SqlSession sqlSession2 = sqlSessionFactory.openSession();
//        FoodMenuMapper mapper1 = sqlSession1.getMapper(FoodMenuMapper.class);
//        FoodMenuMapper mapper2 = sqlSession2.getMapper(FoodMenuMapper.class);
//        mapper1.getMenus();
//        sqlSession1.commit();
//        mapper2.getMenus();
//    }
//    @Test
//    public void menuTest(){
//        JSONObject json = new JSONObject();
//
//            json.put("data",menuMapper.getMenus());
//        System.out.println(json.toString());
//    }
//
//    @Test
//    public void classTest(){
//        JSONObject json = new JSONObject();
//
//        json.put("data",classMapper.getClassByid());
//        System.out.println(json.toString());
//    }
//
//    @Test
//    public void OrderDetailMapperTest(){
//        JSONObject json = new JSONObject();
//        List<OrderDetail> orderDetailListByOrderId = orderDetailMapper.getOrderDetailListByOrderId("1");
//        json.put("data", orderDetailListByOrderId);
//        System.out.println(json.toString());
//    }
//
//    @Test
//    public void menusTest(){
////        List<Menu> menus = foodMenuMapper.getMenus();
////        System.out.println(menus);
////        Menu menu = new Menu();
////        menu.setId("1");menu.setPrice(11);
//////        foodMenuMapper.updateMenuPrice(menu);
////        List<Menu> menus1 = foodMenuMapper.getMenus();
////        System.out.println(menus1);
////        foodMenuService.getMenus();
////        foodMenuService.getMenus();
//    }
}
