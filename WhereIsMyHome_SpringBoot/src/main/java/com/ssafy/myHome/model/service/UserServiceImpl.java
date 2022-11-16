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
	public void removeUser(UserDto user) throws Exception {
		userMapper.deleteUser(user);
	}

	@Override
	public int modifyUser(UserDto user) throws Exception {
		return userMapper.updateUser(user);
	}

	@Override
	public int modifyPass(UserDto user) throws Exception {
		return userMapper.updatePass(user);
	}

	@Override
	public List<UserDto> serachUserList() throws Exception {
		return userMapper.selectUserList();
	}

	@Override
	public boolean checkUserById(UserDto user, String pass) throws Exception {
		String pw = userMapper.checkUserById(user);
		return pw.equals(pass);
	}

	@Override
	public UserDto searchUser(UserDto user) throws Exception {
		return userMapper.selectUser(user);
	}

	@Override
	public UserDto loginUser(UserDto user) throws Exception {
		return userMapper.selectUserInfo(user);
	}

}
