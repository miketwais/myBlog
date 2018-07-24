package com.mason.blog.dao;

import com.mason.blog.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Mapper
@Component(value = "RoleMapper")
public interface RoleMapper {

    /**
     * 获取role列表
     * @param pageSize
     * @param start
     * @return
     */
    public ArrayList<Role> rolesList(@Param("pageSize") int pageSize,@Param("start") int start);

    /**
     * 获取role列表的总量
     * @param pageSize
     * @param start
     * @return
     */
    public Integer rolesSize(@Param("pageSize") int pageSize,@Param("start") int start);

    /**
     * 新建角色信息
     * @param role
     */
    public void insertRole(@Param("role") Role role);

    /**
     * 更新角色信息
     * @param role
     */
    public void updateRole(@Param("role") Role role);

    /**
     * 删除角色信息
     * @param groupId
     */
    public void deleteRoles(@Param("groupId") List<String> groupId);

    /**
     * 通过id得到Modules集合
     * @param id
     * @return
     */
    public List<String> getModulesById(@Param("id") int id);

    /**
     * 得到角色全部数据
     * @return
     */
    public List<Role> allRoles();

    /**
     * 通过UserId得到对应的role
     * @param id
     * @return
     */
    public List<String> getRolesByUserId(int id);
}