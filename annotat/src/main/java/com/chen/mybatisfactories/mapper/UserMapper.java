package com.chen.mybatisfactories.mapper;

import com.chen.mybatisfactories.Po.Post;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

public interface UserMapper {
    Post getOneForUpdate();

    Post getOne();

    int update();

    int addOne(int id);

    int delOne(int id);

    int updateJianxi();

    int decrece(int count);

    int count();
}
