package com.chen.mybatisfactories.service.impl;

import com.chen.mybatisfactories.mapper.UserMapper;
import com.chen.mybatisfactories.service.GoodService;
import com.chen.mybatisfactories.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserServiceImpl implements UserService, GoodService {
    @Autowired
    UserMapper userMapper;

    @Transactional(rollbackFor = Exception.class,isolation = Isolation.REPEATABLE_READ, propagation = Propagation.NEVER)
    public int update() {
        System.out.println(Thread.currentThread().getName()+":  "+userMapper.getOne());
        int update;
        update = userMapper.update();
        System.out.println(Thread.currentThread().getName()+"执行update");
        System.out.println(Thread.currentThread().getName()+":  "+userMapper.getOne());
        return update;
//        return 0;
    }

    @Transactional(rollbackFor = Exception.class,isolation = Isolation.REPEATABLE_READ)
    public int decrece(int count,long time) {
        System.out.println(Thread.currentThread().getName()+":  "+userMapper.getOne());
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int update = userMapper.decrece(count);
        System.out.println(Thread.currentThread().getName() + "执行decrece(count),count=" + count);
        System.out.println(Thread.currentThread().getName()+":  "+userMapper.getOne());
        return update;
//        return 0;
    }

    @Override
    public void noTxTtest() {

    }

    @Override
    @Transactional(rollbackFor = Exception.class,isolation = Isolation.REPEATABLE_READ)
    public Object getOne() {
        System.out.println(Thread.currentThread().getName()+":  "+userMapper.getOne());
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        userMapper.update();
//        System.out.println(Thread.currentThread().getName()+"执行update");
        System.out.println(Thread.currentThread().getName()+":  "+userMapper.getOne());
        return null;
}

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public int addOne() {
        System.out.println(Thread.currentThread().getName()+":  執行addOne");
        userMapper.addOne();
        System.out.println(Thread.currentThread().getName()+":  addOne执行完毕");
        return 0;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public int updateJianxi() {
        System.out.println(Thread.currentThread().getName()+":  執行updateJianxi");
        userMapper.updateJianxi();
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+":  updateJianxi执行完毕");
        return 0;
    }

    @Override
    public int sale(int id, int num) {
        System.out.println("执行sale");
        return 0;
    }

}
