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
    @Autowired
    UserService userService;

    @Transactional(rollbackFor = Exception.class,isolation = Isolation.REPEATABLE_READ)
    public int updateForUpdate(long time) {
        System.out.println(Thread.currentThread().getName()+":  "+userMapper.getOneForUpdate());
        System.out.println(Thread.currentThread().getName()+"执行update");
        int update;
        update = userMapper.update();
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName()+":  "+userMapper.getOne());
        return update;
    }

//    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRES_NEW)
    public int updateREQUIRES_NEW() {
        System.out.println(Thread.currentThread().getName()+"执行update");
        int update;
        update = userMapper.update();
        return update;
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
        System.out.println(Thread.currentThread().getName()+":  "+"執行getOne");
        System.out.println(Thread.currentThread().getName()+":  "+userMapper.getOne());
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        userMapper.update();
        return null;
}

    @Override
//    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.NOT_SUPPORTED)
    public int addOne(int id) {
        System.out.println(Thread.currentThread().getName()+":  執行addOne");
        userMapper.addOne(id);
        System.out.println(Thread.currentThread().getName()+":  addOne执行完毕");
        return 0;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public int delOne(int id) {
        System.out.println(Thread.currentThread().getName()+":  執行delOne");
        userMapper.delOne(id);
        System.out.println(Thread.currentThread().getName()+":  delOne执行完毕");
        return 0;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public int updateJianxi() {
        System.out.println(Thread.currentThread().getName()+":  執行updateJianxi");
        userMapper.updateJianxi();
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+":  updateJianxi执行完毕");
        return 0;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public int salee() {
        System.out.println("执行salee");
        addOne(3);
        throw new RuntimeException();
    }

    @Override
    public int sale(int id, int num) {
        return 0;
    }
}
