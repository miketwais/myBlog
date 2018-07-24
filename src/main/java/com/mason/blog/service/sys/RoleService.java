package com.mason.blog.service.sys;

import com.mason.blog.entity.Role;

import java.util.List;

public interface RoleService {

	/**
	 * 获取role列表
	 * 
	 * @param pageSize
	 * @param start
	 * @start
	 */
	public List<Role> rolesList(int pageSize, int start);

	/**
	 * 获取role列表的总量
	 * 
	 * @param pageSize
	 * @param start
	 * @return
	 */
	public Integer rolesSize(int pageSize, int start);

	/**
	 * 新建角色信息
	 * 
	 * @param role
	 */
	public void insertRole(Role role);

	/**
	 * 更新角色信息
	 * 
	 * @param role
	 */
	public void updateRole(Role role);

	/**
	 * 删除角色信息
	 * 
	 * @param groupId
	 */
	public void deleteRoles(List<String> groupId);

	/**
	 * 得到角色全部数据
	 * @return
	 */
	public List<Role> allRoles();
}
