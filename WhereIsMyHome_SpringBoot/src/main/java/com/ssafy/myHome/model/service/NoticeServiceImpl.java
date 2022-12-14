package com.ssafy.myHome.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ssafy.myHome.model.NoticeDto;
import com.ssafy.myHome.model.NoticeParameterDto;
import com.ssafy.myHome.model.mapper.NoticeMapper;

@Service
public class NoticeServiceImpl implements NoticeService {
	@Autowired
	private NoticeMapper noticeMapper;

	@Override
	public int addNotice(NoticeDto notice) throws Exception {
		return noticeMapper.insertNotice(notice);
	}

	@Override
	public void removeNotice(int no) throws Exception {
		noticeMapper.deleteNotice(no);
	}

	@Override
	public int modifyNotice(NoticeDto notice) throws Exception {
		return noticeMapper.updateNotice(notice);
	}

	@Override
	public NoticeDto searchNoticeByNo(int no) throws Exception {
		return noticeMapper.selectNoticeByNo(no);
	}

	@Override
	public List<NoticeDto> searchNoticeList(NoticeParameterDto noticeParameter) throws Exception {
		return noticeMapper.selectNoticeList(noticeParameter);
	}

	@Override
	public void countHitByNo(int no) throws Exception {
		noticeMapper.countHitByNo(no);
	}

	@Override
	public int getTotalCnt(NoticeParameterDto noticeParameter) {
		return noticeMapper.getTotalCnt(noticeParameter);
	}

}
