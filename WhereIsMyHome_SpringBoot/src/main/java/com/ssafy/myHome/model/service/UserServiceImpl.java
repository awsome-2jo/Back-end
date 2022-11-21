package com.ssafy.myHome.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.myHome.model.UserDto;
import com.ssafy.myHome.model.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {
	private UserMapper userMapper;
	
	public UserServiceImpl(UserMapper userMapper) throws Exception {
		this.userMapper = userMapper;
	}

	@Override
	public int addUser(UserDto user) throws Exception {
		return userMapper.insertUser(user);
	}

	@Override
	public void removeUser(String id) throws Exception {
		userMapper.deleteUser(id);
	}

	@Override
	public int modifyUser(UserDto user) throws Exception {
		return userMapper.updateUser(user);
	}

	@Override
	public int findUserPass(UserDto user) throws Exception {
		return userMapper.updatePass(user);
	}

	@Override
	public List<UserDto> serachUserList() throws Exception {
		return userMapper.selectUserList();
	}

	@Override
	public String findUserId(UserDto user) throws Exception {
		return userMapper.findUserId(user);
	}

	@Override
	public UserDto searchUser(String id) throws Exception {
		return userMapper.selectUser(id);
	}

	@Override
	public UserDto loginUser(UserDto user) throws Exception {
		return userMapper.selectUserInfo(user);
	}

	@Override
	public int duplicateUser(String id) throws Exception {
		return userMapper.duplecateUser(id);
	}

	@Override
	public void certifyUser(String email, String userKey) {
		userMapper.certifyUser(email, userKey);
	}

}
