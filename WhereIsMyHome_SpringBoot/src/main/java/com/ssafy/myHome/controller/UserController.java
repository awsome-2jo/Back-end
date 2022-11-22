package com.ssafy.myHome.controller;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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

import com.ssafy.myHome.model.UserDto;
import com.ssafy.myHome.model.service.JwtService;
import com.ssafy.myHome.model.service.JwtServiceImpl;
import com.ssafy.myHome.model.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;

@CrossOrigin("*")
@RestController
@RequestMapping("/user")
@Api("User 컨트롤러 Api")
public class UserController {

	@Autowired
	private UserService userService;

	private JwtService jwtService = JwtServiceImpl.getInstance();

	@Operation(summary = "로그인", description = "아이디, 비밀번호를 받아와 access-token과 로그인 결과를 반환한다.")
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserDto user) {
		Map<String, Object> res = new HashMap<>();
		try {
			UserDto loginUser = userService.loginUser(user);
			if (loginUser == null) {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
			
			if (loginUser.getState() == 0) {
				return new ResponseEntity<Void>(HttpStatus.RESET_CONTENT);
			}
			
			String token = jwtService.create("userId", loginUser.getId(), "access-token");

			res.put("userInfo", loginUser);
			res.put("access-token", token);
			return new ResponseEntity<Map<String, Object>>(res, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@Operation(summary = "회원가입", description = "id, pass, name, email, phone(+ gender, age, prefer~)정보로 새로운 유저를 생성하여 DB에 저장한다.")
	@PostMapping("/regist")
	public ResponseEntity<?> regist(@RequestBody UserDto user) {
		try {
			String userKey = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10);
			user.setUserKey(userKey);
			userService.addUser(user);
			sendMail(user.getEmail(), userKey, 0);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	
	@Operation(summary = "이메일인증", description = "회원가입 후 이메일 인증, 해당 요청이 들어오면 회원의 state : 0 -> 1")
	@GetMapping("/certify/{userKey}")
	public ResponseEntity<?> registEmail(@PathVariable String userKey) {
		try {
			userService.certifyUser(userKey);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@Operation(summary = "아이디 찾기", description = "name, email, phone을 받아와 해당하는 id를 반환한다.")
	@PostMapping("/find/id")
	public ResponseEntity<?> findId(@RequestBody UserDto user) {
		try {
			return new ResponseEntity<String>(userService.findUserId(user), HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	// email send api
	@Operation(summary = "비밀번호 찾기", description = "id, name, email, phone을 받아와 이메일로 임시 비밀번호 전달한다.")
	@PostMapping("/find/pass")
	public ResponseEntity<?> findPass(@RequestBody UserDto user) {
		try {
			String tmpPass = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10);
			user.setPass(tmpPass);
			userService.findUserPass(user);
			sendMail(user.getEmail(), tmpPass, 1);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@Operation(summary = "회원 수정", description = "토큰 유효성 검사 후 id에 해당하는 pass, name, email, phone(+ gender, age, prefer~)정보를 DB에 업데이트한다.")
	@PutMapping("/modify")
	public ResponseEntity<?> modifyUser(@RequestBody UserDto user, HttpServletRequest request) {
		try {
			String token = request.getHeader("access-token");

			if (jwtService.isUsable(token)) {
				userService.modifyUser(user);
				return new ResponseEntity<Void>(HttpStatus.OK);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@Operation(summary = "회원 탈퇴", description = "토큰 유효성 검사 후 해당 id를 DB에서 삭제한다.")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable String id, HttpServletRequest request) {
		try {
			String token = request.getHeader("access-token");

			if (jwtService.isUsable(token)) {
				userService.removeUser(id);
				return new ResponseEntity<Void>(HttpStatus.OK);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	// 관리자 페이지
	@Operation(summary = "회원 목록", description = "관리자 아이디로 호출 시 유저 목록을 불러온다.")
	@GetMapping("/list")
	public ResponseEntity<?> list(HttpServletRequest request) {
		try {
			String token = request.getHeader("access-token");

			if (jwtService.isUsable(token)) {
				Map<String, Object> map = parseToken(token);
				if (((String) map.get("userId")).equals("admin")) {
					List<UserDto> list = userService.serachUserList();
					return new ResponseEntity<List<UserDto>>(list, HttpStatus.OK);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@Operation(summary = "아이디 중복 확인", description = "id를 넘기면 중복된 아이디 체크")
	@GetMapping("/duplicate/{id}")
	public ResponseEntity<?> duplicate(@PathVariable String id) {
		try {
			return new ResponseEntity<Integer>(userService.duplicateUser(id), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}

	@Operation(summary = "회원 정보", description = "토큰 유효성 검사 후 회원 정보를 반환")
	@GetMapping("/info")
	public ResponseEntity<?> info(HttpServletRequest request) {
		try {
			String token = request.getHeader("access-token");

			if (jwtService.isUsable(token)) {
				Map<String, Object> map = parseToken(token);
				return new ResponseEntity<UserDto>(userService.searchUser((String) map.get("userId")), HttpStatus.OK);
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

	public static void sendMail(String email, String key, int type) {
		String host = "smtp.naver.com"; // 네이버일 경우 네이버 계정, gmail경우 gmail 계정
		String user = "homeinpage@naver.com"; // 패스워드
		String password = "home-in-page";
		
		// SMTP 서버 정보를 설정한다.
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", 587);
		props.put("mail.smtp.auth", "true");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
			

			if (type == 0) {
				// 메일 제목
				message.setSubject("Home:in 회원가입 인증 안내 메일");

				// 메일 내용
				message.setContent("<a href=\"http://localhost:8080/confirm/" + key + "\">여기를 눌러주세요</a>", "text/html;charset=euc-kr");
				
			} else {
				// 메일 제목
				message.setSubject("Home:in 비밀번호 안내 메일");

				// 메일 내용
				message.setText("고객님의 임시 비밀번호는 \r\n" + key + "\r\n입니다.");
			}
			// send the message
			Transport.send(message);
			System.out.println("Success Message Send");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
