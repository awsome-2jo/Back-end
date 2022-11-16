package com.ssafy.myHome.model;

import lombok.Data;

@Data
public class AptDealDto {
	private String no;			  // 거래 번호
	private String dealAmount;	  // 거래 금액
	private int dealYear;		  // 거래 일자
	private int dealMonth;
	private int dealDay;
	private String area;		  // 면적
	private String floor;		  // 층
}
