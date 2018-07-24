package com.mason.blog.controller.sys;

import com.mason.blog.entity.Menu;
import com.mason.blog.entity.PageResult;
import com.mason.blog.entity.User;
import com.mason.blog.service.sys.MenuService;
import com.mason.blog.service.sys.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class MenuController {

	private Logger log = LoggerFactory.getLogger(MenuController.class);

	@Resource(name = "menuServiceImpl")
	private MenuService menuService;

	@Resource(name = "userServiceImpl")
	private UserService userService;

	/**
	 * 获取该用户的菜单权限
	 * 
	 * @param loginName
	 * @return
	 */
	@GetMapping("/menu/{loginName}")
	public List<Menu> menuList(@PathVariable String loginName) {
		User userEntity = userService.getUserByLoginName(loginName);
		List<Menu> menuList = menuService.menuList(userEntity.getId());
		return menuList;
	}

	/**
	 * 获取menus表数据
	 * 
	 * @param pageSize
	 * @param page
	 * @return
	 */
	@GetMapping("/menus")
	public PageResult menusList(int pageSize, int page, String menuId) {
		PageResult pageResult = new PageResult();
		pageResult.setData(menuService.menusList(pageSize, page * pageSize, menuId));
		pageResult.setTotalCount(menuService.menusSize(pageSize, page * pageSize, menuId));
		log.debug("The method is ending");
		return pageResult;
	}

	/**
	 * 通过parentId得到menus列表
	 * 
	 * @param parentId
	 * @return
	 */
	@GetMapping("/menus/parentId")
	public List<Menu> menusByParentId(int parentId) {
		return menuService.menusByParentId(parentId);
	}

	/**
	 * 新建菜单信息
	 * 
	 * @param menu
	 * @return
	 */
	@PostMapping("/menus/menu")
	public Menu insertMenu(@RequestBody Menu menu) {
		menuService.insertMenu(menu);
		log.debug("The method is ending");
		return menu;
	}

	/**
	 * 修改菜单信息
	 * 
	 * @param menu
	 * @param id
	 * @return
	 */
	@PutMapping("/menus/{id}")
	public Menu updateMenu(@RequestBody Menu menu, @PathVariable int id) {
		if (menu.getId() == id) {
			menuService.updateMenu(menu);
		}
		log.debug("The method is ending");
		return menu;
	}

	/**
	 * 删除菜单信息
	 * 
	 * @param groupId
	 * @return
	 */
	@DeleteMapping("/menus")
	public List<String> deleteMenus(@RequestBody List<String> groupId) {
		menuService.deleteMenus(groupId);
		return groupId;
	}

	/**
	 * 获取二级菜单
	 * @return
	 */
	@GetMapping("/menus/submenus")
	public List<Menu> getSubmenus() {
		return menuService.getSubmenus();
	}
}
