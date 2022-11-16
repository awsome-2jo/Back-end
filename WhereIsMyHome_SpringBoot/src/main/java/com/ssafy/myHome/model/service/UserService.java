package com.ssafy.myHome.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.myHome.model.UserDto;


public interface UserService {
	int addUser(UserDto user) throws Exception;
	
	void removeUser(UserDto user) throws Exception;
	
	int modifyUser(UserDto user) throws Exception;

	int modifyPass(UserDto user) throws Exception;
	
	boolean checkUserById(UserDto user, String pass) throws Exception;

	List<UserDto> serachUserList() throws Exception;

	UserDto searchUser(UserDto user) throws Exception;

	UserDto loginUser(UserDto user) throws Exception;
}
