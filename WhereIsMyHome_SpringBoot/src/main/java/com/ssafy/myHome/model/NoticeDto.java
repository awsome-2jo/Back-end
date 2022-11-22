package com.ssafy.myHome.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "NoticeDto : 공지사항 정보", description = "공지사항 번호, 제목, 작성자, 작성일자, 조회수, 내용")
@Data
public class NoticeDto {
	@ApiModelProperty(value = "번호")
	private int no;
	
	@ApiModelProperty(value = "제목")
	private String title;
	
	@ApiModelProperty(value = "작성자")
	private String author;
	
	@ApiModelProperty(value = "작성일자")
	private String regDate;
	
	@ApiModelProperty(value = "조회수")
	private int hit;
	
	@ApiModelProperty(value = "내용")
	private String content;
	
	@ApiModelProperty(value = "필독 여부")
	private int type = 0;
}
