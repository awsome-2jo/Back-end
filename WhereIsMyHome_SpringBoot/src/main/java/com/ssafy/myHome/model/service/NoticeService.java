package com.ssafy.myHome.model.service;

import java.util.List;
import com.ssafy.myHome.model.NoticeDto;
import com.ssafy.myHome.model.NoticeParameterDto;


public interface NoticeService {
	
	int addNotice(NoticeDto notice) throws Exception;
	
	void removeNotice(int no) throws Exception;
	
	int modifyNotice(NoticeDto notice) throws Exception;
	
	NoticeDto searchNoticeByNo(int no) throws Exception;
	
	List<NoticeDto> searchNoticeList(NoticeParameterDto noticeParameter) throws Exception;
	
	void countHitByNo(int no) throws Exception;

	int getTotalCnt(NoticeParameterDto noticeParameter);
}
