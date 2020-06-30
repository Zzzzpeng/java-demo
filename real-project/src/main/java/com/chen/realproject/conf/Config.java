package com.chen.realproject.conf;

import com.chen.realproject.entity.Article;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public Article article(){
        return new Article();
    }
}
