package com.ssafy.myHome.model;

import lombok.Data;

@Data
public class AptInfoDto {
	private String no;			  // 거래 번호
	
	private String apartmentName; // 아파트 이름
	private String dong;		  // 동 이름
	
	private String aptCode;		  // 아파트 코드
	private String area;		  // 면적
	private String dealAmount;	  // 매매 가격
	
	private String lng;			  // 경도
	private String lat;			  // 위도
	
	private int dealYear;		  // 거래일자
	private int dealMonth;
	private int dealDay;
	
	private int minDealAmount;
	private int maxDealAmount;
	private int minArea;
	private int maxArea;
	
}
