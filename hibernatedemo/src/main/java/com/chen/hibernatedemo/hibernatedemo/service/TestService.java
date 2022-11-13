package com.chen.hibernatedemo.hibernatedemo.service;

import com.chen.hibernatedemo.hibernatedemo.dao.PostRepository;
import com.chen.hibernatedemo.hibernatedemo.model.GroupPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestService {
    @Autowired
    private GroupPostService groupPostService;

    @Autowired
    private PostRepository postRepository;

    @Transactional()
    public void test() {


        GroupPost groupPost = postRepository.findById(5L).orElse(null);
        Integer status = groupPost.getStatus();
        System.out.println("status:"+status);

        groupPost.setStatus(--status);
        groupPostService.updateStatus(groupPost);

//        groupPost.setStatus(--status);
//        groupPostService.updateStatus(groupPost);
        groupPostService.updateStatus1();

        groupPostService.println();
    }
}
