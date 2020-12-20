package com.chen.realproject.service.impl;

import com.chen.realproject.dao.ArticleMapper;
import com.chen.realproject.data.PageVo;
import com.chen.realproject.entity.Article;
import com.chen.realproject.service.IArticleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class ArticleServiceImpl implements IArticleService {
    public ArticleServiceImpl() {
        System.out.println("init");
    }

    @Resource(name = "articleMapper")
    ArticleMapper articleMapper;
    @Value("${czpName}")
    public String name;
    @Override
    @Transactional
    public Object findOne() {
        Map map = null;
        map = articleMapper.find();

//        return articleMapper.findOneAsMap(id);

        return map;
    }

    @Override
    public Object findList(int pageNum, int pageSize) {
        System.out.println(name);
        PageHelper.startPage(pageNum, pageSize);
        Page<Article> page = (Page<Article>) articleMapper.findList(pageNum, pageSize);
//        Page<BrandVo> page = (Page<BrandVo>) brandMapper.getBrandList();
        PageVo pageVo = new PageVo(page.getTotal(),page.getPageNum(),page.getPageSize(),page.getPages(),page.getResult());
        return pageVo;
    }

    @Override
    public void show() {
        System.out.println(name);
    }


}
