package com.chen.bookmanager.service;

import com.chen.bookmanager.model.foodorder.Menu;

import java.util.List;

public interface FoodMenuService {
    List<Menu> getMenus();
    int updateMenuPrice(Menu menu);
}
