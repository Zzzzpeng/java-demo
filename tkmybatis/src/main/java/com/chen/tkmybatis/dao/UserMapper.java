package com.chen.tkmybatis.dao;

import com.chen.tkmybatis.po.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper /*extends tk.mybatis.mapper.common.Mapper<UserInfo> */{
    List<User> findAll();
}
