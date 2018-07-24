package com.mason.blog.controller.sys;

import com.mason.blog.entity.PageResult;
import com.mason.blog.entity.Role;
import com.mason.blog.service.sys.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class RoleController {

	private Logger log = LoggerFactory.getLogger(RoleController.class);

	@Resource(name = "roleServiceImpl")
	private RoleService roleService;

	/**
	 * 获取role表数据
	 * 
	 * @param pageSize
	 * @param page
	 * @return
	 */
	@GetMapping("/roles")
	public PageResult rolesList(String loginName, int pageSize, int page) {
		PageResult pageResult = new PageResult();
		pageResult.setData(roleService.rolesList(pageSize, page * pageSize));
		pageResult.setTotalCount(roleService.rolesSize(pageSize, page * pageSize));
		log.debug("The method is ending");
		return pageResult;
	}

	/**
	 * 新建角色信息
	 * 
	 * @param role
	 * @return
	 */
	@PostMapping("/roles/role")
	public Role insertRole(@RequestBody Role role) {
		roleService.insertRole(role);
		log.debug("The method is ending");
		return role;
	}

	/**
	 * 更新角色信息
	 * 
	 * @param role
	 * @param id
	 * @return
	 */
	@PutMapping("/roles/{id}")
	public Role updateRole(@RequestBody Role role, @PathVariable int id) {
		if (role.getId() == id) {
			roleService.updateRole(role);
		}
		log.debug("The method is ending");
		return role;
	}

	/**
	 * 删除角色信息
	 * 
	 * @param groupId
	 * @return
	 */
	@DeleteMapping("/roles")
	public List<String> deleteRoles(@RequestBody List<String> groupId) {
		roleService.deleteRoles(groupId);
		return groupId;
	}
	
	/**
	 * 得到角色全部数据
	 * @return
	 */
	@GetMapping("/roles/all")
	public List<Role> allRoles(){
		return roleService.allRoles();
	}
}
