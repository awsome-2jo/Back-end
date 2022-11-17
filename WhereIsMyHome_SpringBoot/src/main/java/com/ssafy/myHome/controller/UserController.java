package com.ssafy.myHome.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

import com.ssafy.myHome.model.UserDto;
import com.ssafy.myHome.model.service.JwtService;
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

	@Autowired
	private JwtService jwtService;
	
	final String SECRET_KEY = "SSAFY";
	
	@Operation(summary = "로그인", description = "아이디, 비밀번호를 받아와 access-token과 로그인 결과를 반환한다.")
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserDto user) {
		Map<String, Object> res = new HashMap<>();
		try {
			UserDto loginUser = userService.loginUser(user);
			if (loginUser == null) {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
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
	@PostMapping("/register")
	public ResponseEntity<?> regist(@RequestBody UserDto user) {
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@Operation(summary = "계정 찾기", description = "id, email, name을 받아와 이메일로 임시 비밀번호 전달한다.")
	@PutMapping("/find")
	public ResponseEntity<?> findPass(@RequestBody UserDto user) {
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@Operation(summary = "회원 수정", description = "토큰 유효성 검사 후 id에 해당하는 pass, name, email, phone(+ gender, age, prefer~)정보를 DB에 업데이트한다.")
	@PutMapping("/modify")
	public ResponseEntity<?> modifyUser(@RequestBody UserDto user) {
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@Operation(summary = "회원 탈퇴", description = "토큰 유효성 검사 후 해당 id를 DB에서 삭제한다.")
	@DeleteMapping("/delete")
	public ResponseEntity<?> delete(@RequestParam String id, HttpServletRequest request) {

		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}	
	
	// 관리자 페이지
	@Operation(summary = "회원 목록", description = "관리자 아이디로 호출 시 유저 목록을 불러온다.")
	@GetMapping("/userList")
	public ResponseEntity<?> list(@SessionAttribute UserDto user, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (user.getId().equals("admin")){
				List<UserDto> list = userService.serachUserList();
				return new ResponseEntity<List<UserDto>>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch(Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}
		
//	@GetMapping("/user")
//	public ResponseEntity<?> users() {
//		try {
//			List<UserDto> list = userService.serachUserList();
//			if (list != null && !list.isEmpty()) {
//				return new ResponseEntity<List<UserDto>>(list, HttpStatus.OK);
//			} else {
//				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			return exceptionHandling(e);
//		}
//	}
	
	/*
	@PostMapping("/register")
	public ResponseEntity<?> regist(@RequestBody UserDto user) {
		try {
			int res = userService.addUser(user);

			if (res != 0) {
//				model.addAttribute("msg", "회원가입이 완료되었습니다!");
				return new ResponseEntity<Integer>(res, HttpStatus.OK);
			} else {
//				model.addAttribute("msg", "회원가입을 실패하였습니다!");
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}

	@ResponseBody
	@PutMapping("/findPass")
	public ResponseEntity<?> findPass(@RequestBody UserDto user) {
		int res = userService.modifyPass(user);
		
		if (res != 0) {
//			model.addAttribute("msg", "비밀번호를 수정하였습니다!");
			return new ResponseEntity<Integer>(res, HttpStatus.OK);
		} else {
//			model.addAttribute("msg", "입력하신 정보가 올바르지 않습니다!");
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping("/usercheck")
	public ResponseEntity<?> check(@RequestBody UserDto user, @RequestParam Map<String, String> map) {
		String pass = map.get("pass");
		try {
			if (user.getPass().equals(pass)) {
				return new ResponseEntity<Integer>(1, HttpStatus.OK);
			} else {
//				model.addAttribute("msg", "비밀번호가 틀렸습니다!");
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}
	
	
	@PutMapping("modify")
	public Map<String, Object> modifyUser(@RequestBody UserDto user) {
		// 유저 정보 수정
		int res = userService.modifyUser(user);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("res", res);
		
		// 관리자가 수정한 것일 경우 유저 리스트도 얻어옴 -> 유저 리스트 표기
		if (user.getId().equals("admin")) {
			List<UserDto> list = userService.serachUserList();
			data.put("users", list);
		}
		
		// 수정 내용에 따른 메시지
		String msg = "로그인 부터 해주세요!";
		if(res == 1) {
			msg = "회원을 수정하였습니다!";
		}
		data.put("msg", msg);
		
		return data;
	}
	
	@DeleteMapping("delete")
	public ResponseEntity<?> delete(@RequestBody UserDto user, HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		try {
			if(user!=null && user.getId().equals("admin") && request.getParameter("edit")!=null) {
				UserDto target = new UserDto();
				userService.removeUser(user);
//				model.addAttribute("msg", "회원을 삭제하였습니다!");
				return new ResponseEntity<Void>(HttpStatus.OK);
			}
			else if(user != null) {
				userService.removeUser(user);
				session.invalidate();
//				model.addAttribute("msg", "회원 탈퇴 되었습니다!");
				return new ResponseEntity<Void>(HttpStatus.OK);
			}
			else {
//				model.addAttribute("msg", "로그인 부터 해주세요!");
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}				
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}	
	
	
//	@GetMapping("/userList")
//	public String list(Model model, HttpServletRequest request, HttpServletResponse response) {
//		try {
//			User user = (User) request.getSession().getAttribute("loginUser");
//			if(user==null || !user.getId().equals("admin")) {
//				return "index";
//			}
//			else {
//				List<User> list = userService.serachUserList();
//				model.addAttribute("users", list);
//				return "user/userList";
//			}
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//		return "index";
//	}
	
	// 관리자 페이지
	@GetMapping("/userList")
	public ResponseEntity<?> list(@SessionAttribute UserDto user, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (user.getId().equals("admin")){
				List<UserDto> list = userService.serachUserList();
//				model.addAttribute("users", list);
				return new ResponseEntity<List<UserDto>>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch(Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}
	
	@GetMapping("/user")
	public ResponseEntity<?> users() {
		try {
			List<UserDto> list = userService.serachUserList();
			if (list != null && !list.isEmpty()) {
				return new ResponseEntity<List<UserDto>>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}
	
	 */
	private ResponseEntity<String> exceptionHandling(Exception e) {
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
