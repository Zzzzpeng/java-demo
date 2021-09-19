package com.chen.hibernatedemo.hibernatedemo.service;

import com.chen.hibernatedemo.hibernatedemo.dao.PostRepository;
import com.chen.hibernatedemo.hibernatedemo.model.GroupPost;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GroupPostService {
    @Autowired
    private PostRepository postRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateStatus(GroupPost groupPost) {

        postRepository.save(groupPost);

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public GroupPost updateStatus1() {
        GroupPost groupPost = postRepository.findById(5L).orElse(null);
        groupPost.setStatus(groupPost.getStatus() - 1);
        GroupPost save = postRepository.save(groupPost);
        return save;
    }

    public void println(GroupPost groupPost) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            if (groupPost != null) {
                objectMapper.writeValueAsString(groupPost);
            }
            groupPost = postRepository.findById(5L).orElse(null);
            System.out.println(objectMapper.writeValueAsString(groupPost));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void println() {
        println(null);
    }

}
