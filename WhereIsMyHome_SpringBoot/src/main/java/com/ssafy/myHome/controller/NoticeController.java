package com.ssafy.myHome.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.ssafy.myHome.model.NoticeDto;
import com.ssafy.myHome.model.UserDto;
import com.ssafy.myHome.model.service.NoticeService;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;

@CrossOrigin("*")
@RestController
@RequestMapping("/notice")
@Api("Notice 컨트롤러 API")
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	@Operation(summary = "공지사항 목록", description = "공지사항 목록을 반환한다.")
	@GetMapping("/list")
	public ResponseEntity<?> list() {
		List<NoticeDto> list;
		try {
			list = noticeService.searchNoticeList();
			return new ResponseEntity<List<NoticeDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@Operation(summary = "공지사항 상세정보", description = "공지사항 상세정보를 불러온다.")
	@GetMapping("/detail")
	public ResponseEntity<?> detail(@RequestParam int no) {
		try {
			noticeService.countHitByNo(no);
			NoticeDto notice = noticeService.searchNoticeByNo(no);
			return new ResponseEntity<NoticeDto>(notice, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@Operation(summary = "공지사항 수정", description = "토큰 유효성 검사 후 글을 삭제할 권한(관리자 혹은 작성자)이 있으면 공지사항을 수정한다.")
	@PutMapping("/modify")
	public ResponseEntity<?> modify(@RequestBody NoticeDto notice) {
		try {
			noticeService.modifyNotice(notice);
			return new ResponseEntity<Integer>(notice.getNo(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}		
	}
	
	@Operation(summary = "공지사항 추가", description = "공지사항을 새로운 게시글을 추가한다.")
	@PostMapping("/add")
	public ResponseEntity<?> add(@RequestBody NoticeDto notice) {
		try {
			noticeService.addNotice(notice);
			return new ResponseEntity<Integer>(notice.getNo(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@Operation(summary = "공지사항 삭제", description = "토큰 유효성 검사 후 글을 삭제할 권한(관리자 혹은 작성자)이 있으면 공지사항을 삭제한다.")
	@DeleteMapping("/delete")
	public ResponseEntity<?> delete(@RequestBody UserDto loginUser, @RequestParam int no) {
		if(loginUser.getId().equals("admin")) {
			try {
				noticeService.removeNotice(no);
				return new ResponseEntity<Void>(HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
