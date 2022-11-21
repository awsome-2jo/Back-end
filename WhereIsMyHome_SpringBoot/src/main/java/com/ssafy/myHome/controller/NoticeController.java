package com.ssafy.myHome.controller;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.BasicJsonParser;
import org.springframework.boot.json.JsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.myHome.model.NoticeDto;
import com.ssafy.myHome.model.NoticeParameterDto;
import com.ssafy.myHome.model.UserDto;
import com.ssafy.myHome.model.service.JwtService;
import com.ssafy.myHome.model.service.JwtServiceImpl;
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

	private JwtService jwtService = JwtServiceImpl.getInstance();

	@Operation(summary = "공지사항 목록", description = "검색량(amount), 시작페이지(+ 추가 검색어, type은 기본 0)를 보내면 공지사항 목록(list)을 반환한다.")
	@GetMapping("/list")
	public ResponseEntity<?> list(NoticeParameterDto noticeParameter) {
		try {
			noticeParameter.setStart((noticeParameter.getPage() - 1) * noticeParameter.getAmount());
			List<NoticeDto> list;
			list = noticeService.searchNoticeList(noticeParameter);

			return new ResponseEntity<List<NoticeDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}

	@Operation(summary = "공지사항 개수 반환", description = "검색량(amount)를 보내면 총 공지사항 개수(total), 총 페이지 수(totalpage)를 반환한다.")
	@GetMapping("/list/count")
	public ResponseEntity<?> count(NoticeParameterDto noticeParameter) {
		try {
			Map<String, Object> res = new HashMap<>();
			int page = noticeParameter.getPage();
			if (page <= 0)
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

			int total = noticeService.getTotalCnt(noticeParameter);
			res.put("total", total);
			res.put("totalpage", (total - 1) / noticeParameter.getAmount() + 1);

			return new ResponseEntity<Map<String, Object>>(res, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}

	@Operation(summary = "공지사항 상세정보", description = "공지사항 상세정보를 불러온다.")
	@GetMapping("/detail/{no}")
	public ResponseEntity<?> detail(@PathVariable int no) {
		try {
			noticeService.countHitByNo(no);
			NoticeDto notice = noticeService.searchNoticeByNo(no);
			return new ResponseEntity<NoticeDto>(notice, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}

	@Operation(summary = "공지사항 수정", description = "토큰 유효성 검사 후 글을 삭제할 권한(관리자 혹은 작성자)이 있으면 공지사항을 수정한다.")
	@PutMapping("/modify")
	public ResponseEntity<?> modify(@RequestBody NoticeDto notice, HttpServletRequest request) {
		try {
			String token = request.getHeader("access-token");

			if (jwtService.isUsable(token)) {
				Map<String, Object> map = parseToken(token);
				if (((String) map.get("userId")).equals("admin")) {
					noticeService.modifyNotice(notice);
					return new ResponseEntity<Integer>(notice.getNo() ,HttpStatus.OK);
				}
			}
//			noticeService.modifyNotice(notice);
//			return new ResponseEntity<Integer>(notice.getNo() ,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@Operation(summary = "공지사항 추가", description = "공지사항을 새로운 게시글을 추가한다.")
	@PostMapping("/add")
	public ResponseEntity<?> add(@RequestBody NoticeDto notice, HttpServletRequest request) {
		try {
			String token = request.getHeader("access-token");

			if (jwtService.isUsable(token)) {
				Map<String, Object> map = parseToken(token);
				if (((String) map.get("userId")).equals("admin")) {
					noticeService.addNotice(notice);
					System.out.println(notice.getNo());
					return new ResponseEntity<Integer>(notice.getNo(), HttpStatus.OK);
				}
				
				System.out.println("여긴 옴?");
			}
//			noticeService.addNotice(notice);
//			return new ResponseEntity<Integer>(notice.getNo(), HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}

		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@Operation(summary = "공지사항 삭제", description = "토큰 유효성 검사 후 공지사항을 삭제한다.")
	@DeleteMapping("/delete/{no}")
	public ResponseEntity<?> delete(@PathVariable int no, HttpServletRequest request) {
		try {
			String token = request.getHeader("access-token");

			if (jwtService.isUsable(token)) {
				Map<String, Object> map = parseToken(token);
				if (((String) map.get("userId")).equals("admin")) {
					noticeService.removeNotice(no);
					return new ResponseEntity<Void>(HttpStatus.OK);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}

		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	private Map<String, Object> parseToken(String token) {
		String[] check = token.split("\\.");
		Base64.Decoder decoder = Base64.getDecoder();
		String payload = new String(decoder.decode(check[1]));

		JsonParser jsonParser = new BasicJsonParser();
		return jsonParser.parseMap(payload);
	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
