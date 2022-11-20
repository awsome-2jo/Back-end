package com.ssafy.myHome.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "NoticeParameterDto : 공지사항 파라미터 정보", description = "공지사항 글을 얻기위한 부가적인 파라미터정보.")
public class NoticeParameterDto {
	
	@ApiModelProperty(value = "현재 페이지 번호")
	private int page = 1;
	
	@ApiModelProperty(value = "페이지당 글갯수")
	private int amount = 10;
	
	@ApiModelProperty(value = "페이지의 시작 글번호")
	private int start = 1;
	
	@ApiModelProperty(value = "검색 조건")
	private String key = "title";
	
	@ApiModelProperty(value = "필독여부")
	private int type = 0;
	
	@ApiModelProperty(value = "검색어")
	private String keyword;
}
