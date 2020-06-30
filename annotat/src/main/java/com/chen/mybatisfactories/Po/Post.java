package com.chen.mybatisfactories.Po;

import com.chen.mybatisfactories.annotation.MyComponent;

//@MyComponent
public class Post {
    int id;
    int group_id;
    int content_id;
    int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public int getContent_id() {
        return content_id;
    }

    public void setContent_id(int content_id) {
        this.content_id = content_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", group_id=" + group_id +
                ", content_id=" + content_id +
                ", status=" + status +
                '}';
    }
}
