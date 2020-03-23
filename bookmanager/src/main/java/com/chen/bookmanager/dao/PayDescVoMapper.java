package com.chen.bookmanager.dao;

import com.chen.bookmanager.model.PayDescVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PayDescVoMapper extends tk.mybatis.mapper.common.Mapper<PayDescVo> {
    int insert(@Param("items") List<PayDescVo> payDescVoMapperList);
}
