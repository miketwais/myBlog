package com.mason.blog.service.sys;

import com.mason.blog.entity.User;

import java.util.List;

public interface UserService {
	public void insert(User user);

	public void del(User user);

	/**
	 * 通过登录名得到用户信息
	 * @param loginName
	 * @return
	 */
	public User getUserByLoginName(String loginName);

	/**
	 * 获取user列表
	 * @param loginName
	 * @param pageSize
	 * @param start
	 * @return
	 */
	public List<User> usersList(String loginName, int pageSize, int start);

	/**
	 * 获取user列表的总量
	 * @param loginName
	 * @param pageSize
	 * @param start
	 * @return
	 */
	public Integer usersSize(String loginName, int pageSize, int start);

	/**
	 * 新建用户信息
	 * @param user
	 */
	public void insertUser(User user);

	/**
	 * 更新用户信息
	 * @param user
	 */
	public void updateUser(User user);

	/**
	 * 删除用户信息
	 * @param groupId
	 */
	public void deleteUsers(List<String> groupId);
}
