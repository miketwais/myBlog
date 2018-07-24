package com.mason.blog.entity;

public class Relation {
    private Integer userId;

    private Integer roleId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer user_id) {
        this.userId = user_id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer role_id) {
        this.roleId = role_id;
    }
}