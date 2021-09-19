package com.chen.hibernatedemo.hibernatedemo.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "group_post") //引入@Table注解，name赋值为表名
public class GroupPost implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column()
    private Long id;

    @Column(name = "group_id")
    private Long groupId;

    @Column(name = "content_id")
    private Long contentId;

    @Column(name = "status")
    private Integer status;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}