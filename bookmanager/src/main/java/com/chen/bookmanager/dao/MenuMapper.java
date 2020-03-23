package com.chen.bookmanager.dao;

import com.chen.bookmanager.model.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {
    List<Menu> getMenus();

}
