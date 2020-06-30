package com.chen.mybatisfactories.service;

import com.chen.mybatisfactories.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {
    int update();

    Object getOne();

    int addOne();

    int updateJianxi();

    int decrece(int count, long time);

    void noTxTtest();
}
