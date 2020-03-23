package com.chen.realproject.dao;

import com.chen.realproject.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;
import java.util.Map;

@Mapper
public interface ArticleMapper {
    Article findOne(long id);
    Article findOneByTitle(String title);
    int save(Article article);

    Map findOneAsMap(long id);

    int update(String content);
    List<Article> findList(int pageNum,int pageSize);
}
