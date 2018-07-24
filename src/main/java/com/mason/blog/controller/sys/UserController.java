package com.mason.blog.controller.sys;

import com.mason.blog.entity.PageResult;
import com.mason.blog.entity.User;
import com.mason.blog.service.sys.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
/*@PreAuthorize("hasRole('ADMI')")*/
public class UserController {

	private Logger log = LoggerFactory.getLogger(UserController.class);

	@Resource(name = "userServiceImpl")
	private UserService userService;

	@GetMapping("/user/{loginName}")
	public User userGet(@PathVariable String loginName) {
		User userEntity = userService.getUserByLoginName(loginName);
		log.debug("The method is ending");
		return userEntity;
	}

	/**
	 * 获取user表数据
	 * 
	 * @param loginName
	 * @param pageSize
	 * @param page
	 * @return
	 */
	@GetMapping("/users")
	public PageResult usersList(String loginName, int pageSize, int page) {
		PageResult pageResult = new PageResult();
		pageResult.setData(userService.usersList(loginName, pageSize, page * pageSize));
		pageResult.setTotalCount(userService.usersSize(loginName, pageSize, page * pageSize));
		log.debug("The method is ending");
		return pageResult;
	}

	/**
	 * 新建用户信息
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping("/users/user")
	public User insertUser(@RequestBody User user) {
		userService.insertUser(user);
		log.debug("The method is ending");
		return user;
	}

	/**
	 * 更新用户信息
	 * 
	 * @param user
	 * @param id
	 * @return
	 */
	@PutMapping("/users/{id}")
	public User updateUser(@RequestBody User user, @PathVariable int id) {
		if (user.getId() == id) {
			userService.updateUser(user);
		}
		log.debug("The method is ending");
		return user;
	}

	/**
	 * 删除用户信息
	 * 
	 * @param groupId
	 * @return
	 */
	@DeleteMapping("/users")
	public List<String> deleteUsers(@RequestBody List<String> groupId) {
		userService.deleteUsers(groupId);
		return groupId;
	}
}
