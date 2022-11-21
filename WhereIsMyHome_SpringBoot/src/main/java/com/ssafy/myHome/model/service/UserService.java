package com.ssafy.myHome.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.myHome.model.UserDto;


public interface UserService {
	int addUser(UserDto user) throws Exception;
	
	void removeUser(String id) throws Exception;
	
	int modifyUser(UserDto user) throws Exception;

	String findUserId(UserDto user) throws Exception;
	
	int findUserPass(UserDto user) throws Exception;
	
	void sendEmail(UserDto user, String div) throws Exception;

	List<UserDto> serachUserList() throws Exception;

	UserDto searchUser(String id) throws Exception;

	UserDto loginUser(UserDto user) throws Exception;

	int duplicateUser(String id) throws Exception;
}
