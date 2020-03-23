package com.chen.tkmybatis;

import com.chen.tkmybatis.dao.FileUrlMapper;
import com.chen.tkmybatis.dao.UserMapper;
//import com.chen.tkmybatis.downloader.ChromDriverWorker;
import com.chen.tkmybatis.po.FileUrl;
//import org.checkerframework.checker.units.qual.A;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TkmybatisApplicationTests {
//    @Autowired
//    MenuService menuService;
//    @Autowired
//    MenuMapper menuMapper;
//    @Autowired
//    TestMenuMapper testMenuMapper;
//    @Autowired
//    MenuDetailMapper menuDetailMapper;
    @Autowired
    FileUrlMapper fileUrlMapper;
//    @Autowired
//    ChromDriverWorker chromDriverWorker;
    @Autowired
    UserMapper userMapper;

    @Test
    public void te(){
        System.out.println(userMapper.findAll());
    }
//
//    @Test
//    public void contextLoads() {
////        List<Menu> menuList = menuService.getMenuList();
////        System.out.println(menuList);
////
////        List<Menu> allMenu = menuMapper.getAllMenu();
////        System.out.println(allMenu);
//        Example example1 = Example.builder(Menu.class)
//                            .select("id", "name")
//                            .where(Sqls.custom().andEqualTo("id", 5L))
//                            .build();
//        menuMapper.selectByExample(example1);
//
//
//        Example example = new Example(Menu.class);
//        Example.Criteria criteria = example.createCriteria();
//        criteria.andLike("name", "%鸡腿");
////        Menu menu = new Menu();
////        menu.setName("%薯条%");
//        System.out.println(menuMapper.selectByExample(example).toString());
//    }
//
//    @Test
//    public void tableTest() throws JSONException {
//        List<Menu> menus = menuMapper.selectAll();
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("data",menus);
//        System.out.println(jsonObject.toString());
//
//    }
//    @Test
//    public void tableTest1() throws JSONException {
//        List<TestMenu> menus = testMenuMapper.selectAll();
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("data",menus);
//        System.out.println(jsonObject.toString());
//    }
//    @Test
//    public void innerClassTest() throws JSONException {
//        Menu.Detail cond = Menu.Detail.builder().id("1").build();
//        Menu.Detail detail = menuDetailMapper.selectOne(cond);
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("data",detail);
//        System.out.println(jsonObject.toString());
//    }

    @Test
    public void fileUrlTest(){
        Example example = Example.builder(FileUrl.class).select("url","fileSize").where(Sqls.custom().andEqualTo("catalog", "八上")).build();
        List<FileUrl> fileUrls = fileUrlMapper.selectByExample(example);
//        fileUrls.stream().filter(item->{
//            String fileSize = item.getFileSize();
//            fileSize.split(".");
//            if(fileSize.contains("1.82 MB"));
//        });
        System.out.println(fileUrls.size());
        List<String> collect = fileUrls.stream().map(FileUrl::getUrl).distinct().collect(Collectors.toList());
        System.out.println(collect.size());
    }
//    @Test
//    public void downloadTest(){
//        chromDriverWorker.download();
//    }

}
