package com.chen.realproject.service.impl;

import com.chen.realproject.dao.ArticleMapper;
import com.chen.realproject.data.PageVo;
import com.chen.realproject.entity.Article;
import com.chen.realproject.service.IArticleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@Service
public class ArticleServiceImpl implements IArticleService {

    @Resource(name = "articleMapper")
    ArticleMapper articleMapper;


    @Override
    public Object findOne(long id) {

//        return articleMapper.findOneAsMap(id);
        return articleMapper.findOne(id);
    }

    @Override
    public Object findList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<Article> page = (Page<Article>) articleMapper.findList(pageNum, pageSize);
//        Page<BrandVo> page = (Page<BrandVo>) brandMapper.getBrandList();
        PageVo pageVo = new PageVo(page.getTotal(),page.getPageNum(),page.getPageSize(),page.getPages(),page.getResult());
        return pageVo;
    }


}
