package com.chen.bookmanager.dao.food;

import com.chen.bookmanager.model.foodorder.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FoodMenuMapper {
    List<Menu> getMenus();
    int updateMenuPrice(Menu menu);
    int save(Menu menu);
}
