package com.chen.annotationcac.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

//@Mapper
public interface UserMapper{
    Map getPostByUserId(long pid);
}
