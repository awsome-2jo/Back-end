package com.ssafy.myHome.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	@GetMapping("/gugun/{code}")
	public ResponseEntity<?> gugun(@PathVariable("code") String code) throws Exception {
		return new ResponseEntity<List<SidoGugunCodeDto>>(aptService.getGugun(code), HttpStatus.OK);
	}
	
	@Operation(summary = "동 정보", description = "구군 코드를 기반으로 동 정보를 반환한다.")
	@GetMapping("/dong/{code}")
	public ResponseEntity<?> dong(@PathVariable("code") String code) throws Exception {
		return new ResponseEntity<List<SidoGugunCodeDto>>(aptService.getDong(code), HttpStatus.OK);
	}
	
	@Operation(summary = "아파트 목록", description = "지역코드(regcode), 검색 양(amount)(+ 추가옵션)을 보내면 해당하는 아파트 목록을 반환한다.")
	@GetMapping("/list")
	public ResponseEntity<?> listApt(AptSearchDto aptSearch) {
		try {
			List<AptInfoDto> list;
			list = aptService.selectApart(aptSearch);
			
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
	
	@Operation(summary = "아파트 목록 개수", description = "지역코드(regcode)(+검색어)를 보내면 해당하는 지역의 아파트 개수(count)를 반환한다.")
	@GetMapping("/list/count")
	public ResponseEntity<?> listAptCount(AptSearchDto aptSearch) {
		try {
			int count = aptService.countApart(aptSearch);
			
			if (count >= 0) {
				Map<String, Object> res = new HashMap<>();
				res.put("count", count);
				return new ResponseEntity<Map<String, Object>>(res, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Operation(summary = "지역별 거래통계", description = "지역코드(regcode)(+검색어)를 보내면 해당하는 지역의 dealAvg(평균 거래랑), priceAvg(평균 실거래가)를 반환한다.")
	@GetMapping("/list/stats")
	public ResponseEntity<?> listAptStats(AptSearchDto aptSearch) {
		try {
			List<AptInfoDto> list;
			list = aptService.selectApartByRegcode(aptSearch);
			
			if (list != null && !list.isEmpty()) {
				Map<String, Object> res = new HashMap<>();
				int count = list.size();
				float sum = 0, avg = 0;
				int aptNums = 0;
				String aptCode = list.get(0).getAptCode();
				
				for (int i = 0; i < count; i++) {
					sum += Float.parseFloat(list.get(i).getDealAmount().replaceAll(",",""));
					String tmp = list.get(i).getAptCode();
					if (!aptCode.equals(tmp)) {
						aptCode = tmp;
						aptNums++;
					} 
				}
				
				avg = sum / count;
				res.put("count", count);
				res.put("priceAvg", Math.round(avg));
				res.put("dealAvg", Math.round((float)count / aptNums));
				return new ResponseEntity<Map<String, Object>>(res, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Operation(summary = "아파트 상세정보", description = "아파트 코드(aptCode)를 보내면 해당 아파트의 상세정보를 반환한다.")
	@GetMapping("/detail/{aptCode}")
	public ResponseEntity<?> detailApt(@PathVariable String aptCode) {
		try {
			List<AptDealDto> list = aptService.selectApartDetail(aptCode);
			AptInfoDto aptInfo = aptService.selectApartByAptCode(aptCode);
			
			if (list != null && !list.isEmpty() && aptInfo != null) {
				Map<String, Object> res = new HashMap<>();
				res.put("buildYear", aptInfo.getBuildYear());
				res.put("roadName", aptInfo.getRoadName() + " " + Integer.parseInt(aptInfo.getRoadNameBonbun()));
				res.put("apartmentName", aptInfo.getApartmentName());
				res.put("lng", aptInfo.getLng());
				res.put("lat", aptInfo.getLat());
				res.put("list", list);
				
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
