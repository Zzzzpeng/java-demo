package com.chen.realproject.service;

import com.chen.realproject.entity.Article;

public interface IArticleService {
    Object findOne();
    Object findList(int pageNum,int pageSize);
    void show();
}
