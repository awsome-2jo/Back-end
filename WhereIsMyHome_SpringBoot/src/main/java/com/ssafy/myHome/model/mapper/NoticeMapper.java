package com.ssafy.myHome.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.myHome.model.NoticeDto;
import com.ssafy.myHome.model.NoticeParameterDto;

@Mapper
public interface NoticeMapper {
	
	int insertNotice(NoticeDto notice) throws Exception;
	
	void deleteNotice(int no) throws Exception;
	
	int updateNotice(NoticeDto notice) throws Exception;
	
	/**
	 * @describe 공지사항 상세 조회하기
	*/
	NoticeDto selectNoticeByNo(int no) throws Exception;
	
	/**
	 * @param noticeParameter 
	 * @describe 공지사항 목록 불러오기
	*/
	List<NoticeDto> selectNoticeList(NoticeParameterDto noticeParameter) throws Exception;
	
	void countHitByNo(int no) throws Exception;

	int getTotalCnt(NoticeParameterDto noticeParameter);
}
