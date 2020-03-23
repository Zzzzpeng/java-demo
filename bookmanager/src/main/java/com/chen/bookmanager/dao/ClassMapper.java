package com.chen.bookmanager.dao;

import com.chen.bookmanager.model.Class;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ClassMapper {
    Class getClassByid();
}
