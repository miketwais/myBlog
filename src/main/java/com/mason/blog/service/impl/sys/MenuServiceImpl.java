package com.mason.blog.service.impl.sys;

import com.mason.blog.dao.MenuMapper;
import com.mason.blog.dao.RoleMapper;
import com.mason.blog.entity.Menu;
import com.mason.blog.service.sys.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("menuServiceImpl")
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuMapper menuMapper;
	
	@Autowired
	private RoleMapper roleMapper;

	@Override
	public List<Menu> menuList(int id) {
		List<String> idList = roleMapper.getModulesById(id);
		
		String idstemp = "";
		for (String idtemp : idList) {
			idstemp = idstemp + idtemp;
		}
		String[] ids = idstemp.split(";");
		List<Menu> parentMenuList = menuMapper.getParentMenuListById(ids);
		List<Menu> childrenMenuList = menuMapper.getMenuListById(ids);
		List<Menu> menuList = new ArrayList<Menu>();

		for (Menu parentMenu : parentMenuList) {
			List<Menu> menuListTemp = new ArrayList<Menu>();
			for (Menu childrenMenu : childrenMenuList) {
				if (parentMenu.getId() == childrenMenu.getParentId()) {
					menuListTemp.add(childrenMenu);
				}
			}
			parentMenu.setChildren(menuListTemp);
			menuList.add(parentMenu);
		}

		return menuList;
	}

	@Override
	public List<Menu> menusList(int pageSize, int start, String menuId) {
		return menuMapper.menusList(pageSize, start, menuId);
	}

	@Override
	public Integer menusSize(int pageSize, int start, String menuId) {
		return menuMapper.menusSize(pageSize, start, menuId);
	}

	@Override
	public void insertMenu(Menu menu) {
		menuMapper.insertMenu(menu);
	}

	@Override
	public void updateMenu(Menu menu) {
		menuMapper.updateMenu(menu);
	}

	@Override
	public void deleteMenus(List<String> groupId) {
		menuMapper.deleteMenus(groupId);
	}

	@Override
	public List<Menu> menusByParentId(int parentId) {
		return menuMapper.menusByParentId(parentId);
	}

	@Override
	public List<Menu> getSubmenus() {
		return menuMapper.getSubmenus();
	}

}
