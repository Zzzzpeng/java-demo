package com.chen.bookmanager.jetcache;

import com.alicp.jetcache.anno.CacheInvalidate;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.chen.bookmanager.model.TbUser;

public interface UserService {
    @Cached(expire = 3600, cacheType = CacheType.REMOTE,name = "UserService.getUserById")
    TbUser getUserById(long userId,String name);

    @CacheInvalidate(name = "UserService.getUserById")
    void delete(long userId,String name);

}
