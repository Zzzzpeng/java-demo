package com.chen.realproject;

import com.chen.realproject.dao.ArticleMapper;
import com.chen.realproject.data.PageVo;
import com.chen.realproject.entity.Article;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.apache.ibatis.reflection.wrapper.ObjectWrapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RealProjectApplicationTests {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void cacheTest() throws JsonProcessingException {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("name", "zjag");
        PageVo pageVo = new PageVo();
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(pageVo));
//        redisTemplate.multi();
//        redisTemplate.exec();
    }
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
