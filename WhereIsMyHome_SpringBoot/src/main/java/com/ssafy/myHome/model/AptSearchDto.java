package com.ssafy.myHome.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "AptSearchDto : 아파트 검색 시 필요한 정보", description = "지역 코드, 아파트 코드, 검색량, 검색어, 시작 위치, 검색 년도 범위, 가격 범위, 면적 범위")
@Data
public class AptSearchDto {
	@ApiModelProperty(value = "지역 코드", example = "11110")
	private String regcode = "";

	@ApiModelProperty(value = "아파트 코드")
	private String aptCode = "";
	
	@ApiModelProperty(value = "검색량", example = "10")
	private int amount = 10;
	
	@ApiModelProperty(value = "검색어", example = "동아")
	private String keyword = "";
	
	@ApiModelProperty(value = "시작 위치")
	private int start = 0;
	
	@ApiModelProperty(value = "최소 년도")
	private float minYear = 2015;
	
	@ApiModelProperty(value = "최대 년도")
	private float maxYear = 2022;
	
	@ApiModelProperty(value = "최소 거래가")
	private float minDeal = 0;
	
	@ApiModelProperty(value = "최대 거래가")
	private float maxDeal = 999999;
	
	@ApiModelProperty(value = "최소 면적")
	private float minArea = 0;
	
	@ApiModelProperty(value = "최대 면적")
	private float maxArea = 999999;
	
}
