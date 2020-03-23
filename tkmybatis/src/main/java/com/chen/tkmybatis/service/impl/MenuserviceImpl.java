package com.chen.tkmybatis.service.impl;

import com.chen.tkmybatis.dao.MenuMapper;
import com.chen.tkmybatis.po.foodorder.Menu;
import com.chen.tkmybatis.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MenuserviceImpl implements MenuService {
    @Autowired
    MenuMapper menuMapper;

    @Override
    public List<Menu> getMenuList() {
        Example example = new Example(Menu.class);
        Example.Criteria criteria = example.createCriteria();
//        criteria.andEqualTo("id",3L);
        criteria.andLessThanOrEqualTo("id",3L);
        criteria.orEqualTo("id",5L);
        List<Menu> menus = menuMapper.selectByExample(example);
        return menus;
    }

    public static void main(String[] args) {
        String str = "=IF(LEFT(H2,1)=”2\",\"电商”,IF(ISNUMBER(FIND(\"财务”,M2))=TRUE,”财司”,IF\n" +
                "(LEFT(H2,1)=”1”,IF(LEFT(H2, 4)=”1403”,”国股”,IF(OR(ISNUMBER(FIND(\"北京银行”,M2))=TRUE,ISNUMBER(FIND(\"上海银行”,M2))=TRUE,ISNUMBER(FIND(\"南京银行”,M2))=TRUE, ISNUMBER(FIND(”宁波银行”,M2))=TRUE, ISNUMBER(FIND(\"江苏银行”，M2)=TRUE, ISNUMBER(F IND(\"浙商银行”，M2))=TRUE, ISNUMBER(FIND(渤海银行”，M2)=TRUE),\n" +
                "\"大商\",IF(LEFT (H2,4)>\"1310\",\"城商\",\"国股))),\"城商\"))))";
        System.out.println(str.replaceAll("“|”","\"").replaceAll(" ",""));
        String s = "=IF(LEFT(H2,1)=\"2\",\"电商\",IF(ISNUMBER(FIND(\"财务\",M2))=TRUE,\"财司\",IF\n" +
                "(LEFT(H2,1)=\"1\",IF(LEFT(H2,4)=\"1403\",\"国股\",IF(OR(ISNUMBER(FIND(\"北京银行\",M2))=TRUE,ISNUMBER(FIND(\"上海银行\",M2))=TRUE,ISNUMBER(FIND(\"南京银行\",M2))=TRUE,ISNUMBER(FIND(\"宁波银行\",M2))=TRUE,ISNUMBER(FIND(\"江苏银行\"，M2)=TRUE,ISNUMBER(FIND(\"浙商银行\",M2))=TRUE,ISNUMBER(FIND(\"渤海银行\",M2)=TRUE),\n" +
                "\"大商\",IF(LEFT(H2,4)>\"1310\",\"城商\",\"国股\"))),\"城商\"))))";
        System.out.println(s.replaceAll("ISNUMBER\\(FIND","contains"));
        Pattern pattern = Pattern.compile("上海|宁波");
        Matcher matcher = pattern.matcher("123");

    }
}
