package com.chen.hibernatedemo.hibernatedemo.dao;

import com.chen.hibernatedemo.hibernatedemo.model.GroupPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<GroupPost, Long> {

}