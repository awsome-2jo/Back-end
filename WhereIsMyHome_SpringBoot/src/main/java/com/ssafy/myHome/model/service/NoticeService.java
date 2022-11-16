package com.ssafy.myHome.model.service;

import java.util.List;
import com.ssafy.myHome.model.NoticeDto;


public interface NoticeService {
	
	int addNotice(NoticeDto notice) throws Exception;
	
	void removeNotice(int no) throws Exception;
	
	void modifyNotice(NoticeDto notice) throws Exception;
	
	NoticeDto searchNoticeByNo(int no) throws Exception;
	
	List<NoticeDto> searchNoticeList() throws Exception;
	
	void countHitByNo(int no) throws Exception;
}
