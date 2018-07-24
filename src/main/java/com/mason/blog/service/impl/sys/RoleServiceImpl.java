package com.mason.blog.service.impl.sys;

import com.mason.blog.dao.RoleMapper;
import com.mason.blog.entity.Role;
import com.mason.blog.service.sys.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "roleServiceImpl")
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleMapper roleMapper;

	@Override
	public List<Role> rolesList(int pageSize, int start) {
		return roleMapper.rolesList(pageSize, start);
	}

	@Override
	public Integer rolesSize(int pageSize, int start) {
		return roleMapper.rolesSize(pageSize, start);
	}

	@Override
	public void insertRole(Role role) {
		roleMapper.insertRole(role);
	}

	@Override
	public void updateRole(Role role) {
		roleMapper.updateRole(role);
	}

	@Override
	public void deleteRoles(List<String> groupId) {
		roleMapper.deleteRoles(groupId);
	}

	@Override
	public List<Role> allRoles() {
		return roleMapper.allRoles();
	}

}
