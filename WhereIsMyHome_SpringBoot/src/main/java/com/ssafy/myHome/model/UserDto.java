package com.ssafy.myHome.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "UserDto : 유저 정보", description = "유저 아이디, 비밀번호, 이름, 이메일, 성별, 나이, 폰번호, 선호사항(1, 2), 선호지역, 선호아파트")
@Data
public class UserDto {
	@ApiModelProperty(value = "아이디")
	private String id;
	
	@ApiModelProperty(value = "비밀번호")
	private String pass;
	
	@ApiModelProperty(value = "이름")
	private String name;
	
	@ApiModelProperty(value = "이메일")
	private String email;
	
	@ApiModelProperty(value = "성별")
	private int gender;
	
	@ApiModelProperty(value = "나이")
	private String age;
	
	@ApiModelProperty(value = "폰번호")
	private String phone;
	
	@ApiModelProperty(value = "선호 사항1")
	private String preferOrder1;
	
	@ApiModelProperty(value = "선호 사항2")
	private String preferOrder2;
	
	@ApiModelProperty(value = "선호 지역")
	private String preferRegArr;
	
	@ApiModelProperty(value = "선호 아파트")
	private String preferAptArr;
	
	@ApiModelProperty(value = "인증 여부")
	private int state;
}
