package com.mason.blog.dao;

import com.mason.blog.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Mapper
@Component(value="UserMapper")
public interface UserMapper {
    public ArrayList<User> select(@Param("user") User user);

    public void del(@Param("user") User user);

    public void update(@Param("user") User user);

    public void insert(@Param("user") User user);

    /**
     * 通过登录名拿到用户信息
     * @return
     */
    public User getUserByLoginName(@Param("loginName") String loginName);

    /**
     * 获取user列表
     * @param loginName
     * @param pageSize
     * @param start
     * @return
     */
    public ArrayList<User> usersList(@Param("loginName")String loginName,@Param("pageSize") int pageSize,@Param("start") int start);

    /**
     * 获取user列表的总量
     * @param loginName
     * @param pageSize
     * @param start
     * @return
     */
    public Integer usersSize(@Param("loginName")String loginName,@Param("pageSize") int pageSize,@Param("start") int start);

    /**
     * 新建用户信息
     * @param user
     */
    public void insertUser(@Param("user") User user);

    /**
     * 更新用户信息
     * @param user
     */
    public void updateUser(@Param("user") User user);

    /**
     * 删除用户信息
     * @param groupId
     */
    public void deleteUsers(@Param("groupId") List<String> groupId);
}