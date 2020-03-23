package com.chen.realproject;

import com.chen.realproject.dao.ArticleMapper;
import com.chen.realproject.entity.Article;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RealProjectApplicationTests {
    @Autowired
    ArticleMapper articleMapper;



    @Test
    public void contextLoads() throws JSONException {
        Article one = articleMapper.findOne(0L);
//        one = articleMapper.findOneByTitle("第一篇文章","随便写写");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data",one);
        System.out.println(jsonObject.toString());
    }

    @Test
    public void outPutMapTest(){
        Map oneAsMap = articleMapper.findOneAsMap(0L);
        System.out.println(oneAsMap.toString());
    }

    @Test
    public void jdbcTypeTest(){
//        Article article = new Article();
//        article.setAuthorId("zyf");
//        article.setTitle("嘻嘻嘻");
//        int res = articleMapper.save(article);
        articleMapper.findOneByTitle(null);

    }

    @Test
    public void updateTest(){
        articleMapper.update("嫖一哈");
    }

}
