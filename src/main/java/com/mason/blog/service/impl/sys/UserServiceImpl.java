package com.mason.blog.service.impl.sys;

import com.mason.blog.dao.UserMapper;
import com.mason.blog.entity.User;
import com.mason.blog.service.sys.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "userServiceImpl")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public void insert(User User) {
		userMapper.insert(User);
	}

	@Override
	public void del(User User) {
		userMapper.del(User);
	}
	@Override
	public User getUserByLoginName(String loginName) {
		return userMapper.getUserByLoginName(loginName);
	}

	@Override
	public List<User> usersList(String loginName, int pageSize, int start) {
		return userMapper.usersList( loginName,  pageSize,  start);
	}

	@Override
	public Integer usersSize(String loginName, int pageSize, int start) {
		return userMapper.usersSize(loginName, pageSize, start);
	}

	@Override
	public void insertUser(User User) {
		/*String password = null;
		try {
			password = MD5Util.encrypt(User.getPassword());
			User.setPassword(password);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}*/
		//User.setPassword(new Md5PasswordEncoder().encodePassword(User.getPassword(), null));
		User.setPassword("{bcrypt}"+new BCryptPasswordEncoder().encode(User.getPassword()));
		userMapper.insertUser(User);
	}

	@Override
	public void updateUser(User User) {
		//User.setPassword(new Md5PasswordEncoder().encodePassword(User.getPassword(), null));
		User.setPassword("{bcrypt}"+new BCryptPasswordEncoder().encode(User.getPassword()));
		userMapper.updateUser(User);
	}

	@Override
	public void deleteUsers(List<String> groupId) {
		userMapper.deleteUsers(groupId);
	}

}
