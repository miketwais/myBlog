package com.mason.blog.service.sys;

import com.mason.blog.entity.Menu;

import java.util.List;

public interface MenuService {

	/**
	 * 得到菜单List
	 * 
	 * @param id
	 * @return
	 */
	List<Menu> menuList(int id);

	/**
	 * 获取menus列表
	 * 
	 * @param pageSize
	 * @param menuId
	 * @param start
	 * @return
	 */
	List<Menu> menusList(int pageSize, int start, String menuId);

	/**
	 * 获取menus列表的总量
	 * 
	 * @param pageSize
	 * @param pageSize
	 * @param menuId
	 * @param start
	 * @return
	 */
	Integer menusSize(int pageSize, int start, String menuId);

	/**
	 * 新建菜单信息
	 * 
	 * @param menu
	 */
	void insertMenu(Menu menu);

	/**
	 * 修改菜单信息
	 * 
	 * @param menu
	 */
	void updateMenu(Menu menu);

	/**
	 * 删除菜单信息
	 * 
	 * @param groupId
	 */
	void deleteMenus(List<String> groupId);

	/**
	 * 通过parentId得到menus列表
	 * 
	 * @param parentId
	 * @return
	 */
	List<Menu> menusByParentId(int parentId);

	/**
	 * 获取二级菜单
	 * @return
	 */
	List<Menu> getSubmenus();

}
