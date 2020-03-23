package com.chen.bookmanager.service.impl;

import com.chen.bookmanager.dao.food.FoodMenuMapper;
import com.chen.bookmanager.model.foodorder.Menu;
import com.chen.bookmanager.service.FoodMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("foodMenuService")
public class FoodMenuServiceImpl implements FoodMenuService {
    @Autowired
    private FoodMenuMapper foodMenuMapper;

    @Transactional
    @Override
    public List<Menu> getMenus() {
        return foodMenuMapper.getMenus();
    }

    @Transactional
    @Override
    public int updateMenuPrice(Menu menu) {
        return foodMenuMapper.updateMenuPrice(menu);
    }
}
