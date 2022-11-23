package com.ssafy.myHome.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.json.BasicJsonParser;
import org.springframework.boot.json.JsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;

@CrossOrigin("*")
@RestController
@RequestMapping("/school")
@Api("School 컨트롤러 Api")
public class SchoolController {
	
	@Operation(summary = "", description = "")
	@GetMapping(value = "/info", produces = "text/plain;charset=UTF-8")
	public ResponseEntity<?> getInfo(
			@RequestParam(required = false, defaultValue = "") String schName, 
			@RequestParam(required = false, defaultValue = "") String location
			) throws Exception {
		String apiKey = "af7dff02d8c448b1bc5dd069efe61b2e";
		
		schName = URLEncoder.encode(schName, "UTF-8");
		location = URLEncoder.encode(location, "UTF-8");

		String apiURL = "https://open.neis.go.kr/hub/schoolInfo?Type=json&SCHUL_NM=" 
						+ schName + "&LCTN_SC_NM=" + location + "&KEY=" + apiKey;
		
		System.out.println(apiURL);
		
		Map<String, String> requestHeaders = new HashMap<>();
		String responseBody = get(apiURL, requestHeaders);
		
		System.out.println(responseBody);
		
		JsonParser jsonParser = new BasicJsonParser();
		Map<String, Object> map = jsonParser.parseMap(responseBody);
		
		System.out.println(map.get("schoolInfo"));
		
		
		return new ResponseEntity<String>(responseBody, HttpStatus.OK);
	}
	private static String get(String apiUrl, Map<String, String> requestHeaders) {
		HttpURLConnection con = connect(apiUrl);
		try {
			con.setRequestMethod("GET");
			for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
				con.setRequestProperty(header.getKey(), header.getValue());
			}

			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
				return readBody(con.getInputStream());
			} else { // 오류 발생
				return readBody(con.getErrorStream());
			}
		} catch (IOException e) {
			throw new RuntimeException("API 요청과 응답 실패", e);
		} finally {
			con.disconnect();
		}
	}

	private static HttpURLConnection connect(String apiUrl) {
		try {
			URL url = new URL(apiUrl);
			return (HttpURLConnection) url.openConnection();
		} catch (MalformedURLException e) {
			throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
		} catch (IOException e) {
			throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
		}
	}

	private static String readBody(InputStream body) {
		InputStreamReader streamReader = new InputStreamReader(body);

		try (BufferedReader lineReader = new BufferedReader(streamReader)) {
			StringBuilder responseBody = new StringBuilder();

			String line;
			while ((line = lineReader.readLine()) != null) {
				responseBody.append(line);
			}

			return responseBody.toString();
		} catch (IOException e) {
			throw new RuntimeException("API 응답을 읽는 데 실패했습니다.", e);
		}
	}
}
