package com.chen.bookmanager.jetcache;

import com.chen.bookmanager.model.TbUser;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Override
    public TbUser getUserById(long userId,String name) {
       TbUser tbUser = new TbUser();
        System.out.println("执行计算");
        return tbUser;
    }

    @Override
    public void delete(long userId, String name) {
        System.out.println("删除缓存");
    }
}
