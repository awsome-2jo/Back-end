package com.ssafy.myHome.model;

import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class AptSearchDto {
	@ApiParam(value = "거래 번호", hidden = true)
	private String no;
	
	@ApiParam(value = "검색어", example = "동아")
	private String keyword;
	
	@ApiParam(value = "지역 코드", example = "11110")
	private String regcode;
	
	@ApiParam(value = "아파트 코드", example = "11110000000001")
	private String aptCode;
	
	@ApiParam(value = "검색량", example = "10")
	private int amount;

	@ApiParam(hidden = true)
	private int page;
}
