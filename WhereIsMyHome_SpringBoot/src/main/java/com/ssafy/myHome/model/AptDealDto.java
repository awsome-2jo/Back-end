package com.ssafy.myHome.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 아파트 거래정보 DTO
 */

@ApiModel(value = "AptDealDto : 아파트 거래 정보", description = "아파트 거래 번호, 매매 가격, 거래 년도, 거래 월, 거래 일, 면적, 층수")
@Data
public class AptDealDto {
	@ApiModelProperty(value = "거래 번호")
	private String no;			  
	
	@ApiModelProperty(value = "매매 가격")
	private String dealAmount;	  
	
	@ApiModelProperty(value = "거래 년도")
	private int dealYear;		  
	
	@ApiModelProperty(value = "거래 월")
	private int dealMonth;
	
	@ApiModelProperty(value = "거래 일")
	private int dealDay;
	
	@ApiModelProperty(value = "면적")
	private String area;		
	
	@ApiModelProperty(value = "층수")
	private String floor;		  

	@ApiModelProperty(value = "거래 취소 여부")
	private String cancelDealType;
}
