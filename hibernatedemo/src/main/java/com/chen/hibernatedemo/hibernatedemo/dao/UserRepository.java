package com.chen.hibernatedemo.hibernatedemo.dao;

import com.chen.hibernatedemo.hibernatedemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // 通过用户名称查找用户
    public User findByUserName(String userName);

    // 通过用户名称或者邮箱查找用户
    public User findByUserNameOrEmail(String userName, String email);

    // 通过用户名称或者邮箱查找用户
    public User findUserById(Long id);

    // 模糊查询邮箱查找用户
    List<User> findByEmailLike(String email);

    @Transactional()
    @Query(value = "update user set nickName=?1 where id =?2 ",nativeQuery = true)
    @Modifying
    int updateNickname(String newName,Long id);


    User save(User user);
}