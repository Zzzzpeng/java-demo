package com.chen.bookmanager.jetcache;


import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.chen.bookmanager.model.TbUser;


public class JetCacheTest {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        for (int i = 0; i < 10; i++) {
//            userService.getUserById(123L);
        }
    }
}

