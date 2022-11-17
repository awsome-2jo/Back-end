package com.ssafy.myHome.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "AptInfoDto : 아파트 정보", description = "아파트 거래 번호, 아파트 이름, 동, 아파트 코드, 면적, 매매 가격, 위도, 경도, 거래 년도, 거래 월, 거래 일")
@Data
public class AptInfoDto {
	@ApiModelProperty(value = "아파트 코드")
	private String aptCode;		
	
	@ApiModelProperty(value = "아파트 이름")
	private String apartmentName;  

	@ApiModelProperty(value = "도로명주소")
	private String roadName;		
	
	@ApiModelProperty(value = "지번")
	private String jibun;		

	@ApiModelProperty(value = "위도")
	private String lat;		
	
	@ApiModelProperty(value = "경도")
	private String lng;			
	
	@ApiModelProperty(value = "건축년도")
	private String buildYear;
	
	private int minDealAmount;
	private int maxDealAmount;
	
	private int minArea;
	private int maxArea;

//	@ApiModelProperty(value = "면적")
//	private String area;		  
//	
//	@ApiModelProperty(value = "매매 가격")
//	private String dealAmount;
//
//	@ApiModelProperty(value = "거래 년도")
//	private int dealYear;		
//	
//	@ApiModelProperty(value = "거래 월")
//	private int dealMonth;
//	
//	@ApiModelProperty(value = "거래 일")
//	private int dealDay;
}
