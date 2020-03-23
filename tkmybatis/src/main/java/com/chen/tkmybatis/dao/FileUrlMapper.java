package com.chen.tkmybatis.dao;

import com.chen.tkmybatis.po.FileUrl;
import tk.mybatis.mapper.common.Mapper;

import javax.persistence.Table;

@Table(name = "file_url")
public interface FileUrlMapper extends Mapper<FileUrl> {
}
