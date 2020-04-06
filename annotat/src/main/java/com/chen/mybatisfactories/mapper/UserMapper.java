package com.chen.mybatisfactories.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

public interface UserMapper {
    Map getOne();

    int update();
}
