package com.ssafy.myHome.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "AptSearchDto : 아파트 검색 시 필요한 정보", description = "아파트 거래 번호, 검색어, 지역 코드, 아파트 코드, 검색량")
@Data
public class AptSearchDto {
	@ApiModelProperty(value = "거래 번호")
	private String no;

	@ApiModelProperty(value = "검색어", example = "동아")
	private String keyword;
	
	@ApiModelProperty(value = "지역 코드", example = "11110")
	private String regcode;
	
	@ApiModelProperty(value = "아파트 코드", example = "11110000000001")
	private String aptCode;
	
	@ApiModelProperty(value = "검색량", example = "10")
	private int amount;

	@ApiModelProperty(hidden = true)
	private int page;
}
