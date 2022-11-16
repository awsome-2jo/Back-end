package com.ssafy.myHome.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.myHome.model.AptDealDto;
import com.ssafy.myHome.model.AptInfoDto;
import com.ssafy.myHome.model.AptSearchDto;
import com.ssafy.myHome.model.SidoGugunCodeDto;
import com.ssafy.myHome.model.service.AptService;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;

@CrossOrigin("*")
@RestController
@RequestMapping("/apt")
@Api("Apt 컨트롤러 API")
public class AptController {
	
	@Autowired
	private AptService aptService;
	
	@Operation(summary = "시도 정보", description = "시도 정보를 반환한다.")
	@GetMapping("/sido")
	public ResponseEntity<?> sido() throws Exception {
		return new ResponseEntity<List<SidoGugunCodeDto>>(aptService.getSido(), HttpStatus.OK);
	}

	@Operation(summary = "구군 정보", description = "시도 코드를 기반으로 구군 정보를 반환한다.")
	@GetMapping("/gugun")
	public ResponseEntity<?> gugun(@RequestParam("code") String code) throws Exception {
		return new ResponseEntity<List<SidoGugunCodeDto>>(aptService.getGugun(code), HttpStatus.OK);
	}
	
	@Operation(summary = "동 정보", description = "구군 코드를 기반으로 동 정보를 반환한다.")
	@GetMapping("/dong")
	public ResponseEntity<?> dong(@RequestParam("code") String code) throws Exception {
		return new ResponseEntity<List<SidoGugunCodeDto>>(aptService.getDong(code), HttpStatus.OK);
	}
	
	@Operation(summary = "아파트 목록", description = "지역코드(regcode), 검색 양(amount)(+ 검색어)를 보내면 해당하는 아파트 목록을 반환한다.")
	@GetMapping("/list")
	public ResponseEntity<?> listAptByLoca(AptSearchDto aptSearch) {
		try {
			List<AptInfoDto> list;
			
			if (aptSearch.getKeyword() != null) {
				list = aptService.selectApartByName(aptSearch);
			}
			else { 
				list = aptService.selectApartByDong(aptSearch);
			}

			if (list != null && !list.isEmpty()) {
				return new ResponseEntity<List<AptInfoDto>>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Operation(summary = "아파트 상세정보", description = "아파트 코드(aptCode)를 보내면 해당 아파트의 상세정보를 반환한다.")
	@GetMapping("/detail")
	public ResponseEntity<?> detailApt(AptSearchDto aptSearch) {
		try {
			List<AptDealDto> list = aptService.selectApartDetail(aptSearch);
			
			if (list != null && !list.isEmpty()) {
				int size = list.size();
				float sum = 0, avg = 0;
				Map<String, Object> res = new HashMap<>();
				
				for (int i = 0; i < size; i++) {
					sum += Float.parseFloat(list.get(i).getDealAmount().replaceAll(",", ""));
					if (list.get(i).getNo().equals(aptSearch.getNo())) {
						System.out.println(aptSearch.getNo());
						res.put("aptDetail", list.get(i));
					}
				}
				avg = sum / list.size();
				
				res.put("list", list);
				res.put("avg", avg);
				res.put("count", size);
				
				return new ResponseEntity<Map<String, Object>>(res, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
