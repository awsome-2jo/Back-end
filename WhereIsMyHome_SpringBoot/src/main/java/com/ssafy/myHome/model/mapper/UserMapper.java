package com.ssafy.myHome.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.myHome.model.UserDto;

@Mapper
public interface UserMapper {
	
	int insertUser(UserDto user) throws Exception;
	
	void deleteUser(UserDto user) throws Exception;
	
	int updateUser(UserDto user) throws Exception;
	
	int updatePass(UserDto user) throws Exception;
	/**
	 * @describe 현재 로그인된 유저의 비밀번호와 입력한 비밀번호를 비교하여, 본인 확인
	*/
	String checkUserById(UserDto user) throws Exception;
	
	/**
	 * @describe 현재 로그인된 유저가 admin(관리자)일때, 유저 목록 확인 가능
	*/
	List<UserDto> selectUserList() throws Exception;

	UserDto selectUser(UserDto user) throws Exception;

	UserDto selectUserInfo(UserDto user) throws Exception;
}
