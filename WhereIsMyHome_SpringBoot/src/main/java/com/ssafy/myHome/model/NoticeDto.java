package com.ssafy.myHome.model;

import lombok.Data;

@Data
public class NoticeDto {
	private int no;
	private String title;
	private String regDate;
	private int hit;
	private String content;
}
