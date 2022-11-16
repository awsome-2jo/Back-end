package com.ssafy.myHome.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.myHome.model.NoticeDto;

@Mapper
public interface NoticeMapper {
	
	int insertNotice(NoticeDto notice) throws Exception;
	
	void deleteNotice(int no) throws Exception;
	
	void updateNotice(NoticeDto notice) throws Exception;
	
	/**
	 * @describe 공지사항 상세 조회하기
	*/
	NoticeDto selectNoticeByNo(int no) throws Exception;
	
	/**
	 * @describe 공지사항 목록 불러오기
	*/
	List<NoticeDto> selectNoticeList() throws Exception;
	
	void countHitByNo(int no) throws Exception;
}
