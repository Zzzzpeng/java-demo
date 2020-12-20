package com.chen.mybatisfactories.service;

import com.chen.mybatisfactories.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {
    int update(long time);

    Object getOne();

    int addOne(int id);

    int delOne(int id);

    int updateJianxi();

    int decrece(int count, long time);

    void noTxTtest();

    int salee();
}
