package com.ssafy.myHome.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "UserDto : 유저 정보", description = "유저 정보를 나타낸다.")
@Data
public class UserDto {
	@ApiModelProperty(value = "유저 아이디")
	private String id;
	@ApiModelProperty(value = "유저 비밀번호")
	private String pass;
	@ApiModelProperty(value = "유저 이메일")
	private String email;
	@ApiModelProperty(value = "유저 이름")
	private String name;
	@ApiModelProperty(value = "유저 폰번호")
	private String phone;
	@ApiModelProperty(value = "유저 나이")
	private String age;
	@ApiModelProperty(value = "유저 성별")
	private String gender;
}
