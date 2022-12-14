package com.ssafy.myHome.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
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
@RequestMapping
@Api("외부 API 컨트롤러")
public class ExternalApiController {
	@Operation(summary = "뉴스기사 받아오기", description = "(필수X) query(검색어, 기본으로 앞에 '부동산' 붙음), display(표시개수), start(시작점), sort(정렬기준) -> json 형태로 반환")
	@GetMapping(value = "/naver/news", produces = "text/plain;charset=UTF-8")
	public ResponseEntity<?> getNews(@RequestParam(required = false, defaultValue = "") String query,
			@RequestParam(required = false, defaultValue = "10") Integer display,
			@RequestParam(required = false, defaultValue = "1") Integer start,
			@RequestParam(required = false, defaultValue = "sim") String sort) throws Exception {
		String kind = "news.json?";
		return getArticles(kind, "부동산 " + query, display, start, sort, 0);
	}

	@Operation(summary = "블로그 포스팅 받아오기", description = "(필수X) query(검색어, 기본으로 앞에 '부동산' 붙음), display(표시개수), start(시작점), sort(정렬기준) -> json 형태로 반환")
	@GetMapping(value = "/naver/blog", produces = "text/plain;charset=UTF-8")
	public ResponseEntity<?> getBlog(@RequestParam(required = false, defaultValue = "") String query,
			@RequestParam(required = false, defaultValue = "10") Integer display,
			@RequestParam(required = false, defaultValue = "1") Integer start,
			@RequestParam(required = false, defaultValue = "sim") String sort) throws Exception {
		String kind = "blog.json?";
		return getArticles(kind, "부동산 " + query, display, start, sort, 1);
	}
	
	@Operation(summary = "학교 정보 받아오기", description = "")
	@GetMapping(value = "/school/info", produces = "text/plain;charset=UTF-8")
	public ResponseEntity<?> getSchool(
			@RequestParam(required = false, defaultValue = "") String schName, 
			@RequestParam(required = false, defaultValue = "") String location) throws Exception {
		String apiKey = "af7dff02d8c448b1bc5dd069efe61b2e";
		
		schName = URLEncoder.encode(schName, "UTF-8");
		location = URLEncoder.encode(location, "UTF-8");

		String apiURL = "https://open.neis.go.kr/hub/schoolInfo?Type=json&SCHUL_NM=" 
						+ schName + "&LCTN_SC_NM=" + location + "&KEY=" + apiKey;
		
		Map<String, String> requestHeaders = new HashMap<>();
		String responseBody = get(apiURL, requestHeaders);
		
		JsonParser jsonParser = new BasicJsonParser();
		Map<String, Object> map = jsonParser.parseMap(responseBody);
		
		ArrayList list = (ArrayList)map.get("schoolInfo");
		ArrayList list2 = (ArrayList)((Map)list.get(1)).get("row");
		
		Map<String, Object> res = new HashMap<String, Object>();
		
		res.put("schName", ((Map)list2.get(0)).get("SCHUL_NM").toString());
		res.put("coedu", ((Map)list2.get(0)).get("COEDU_SC_NM").toString());
		res.put("public", ((Map)list2.get(0)).get("FOND_SC_NM").toString());
		res.put("tel", ((Map)list2.get(0)).get("ORG_TELNO").toString());
		res.put("location", ((Map)list2.get(0)).get("ORG_RDNMA").toString());
		res.put("web", ((Map)list2.get(0)).get("HMPG_ADRES").toString());
		
		JSONObject jres = new JSONObject(res);
		
		return new ResponseEntity<String>(jres.toString(), HttpStatus.OK);
	}
	
	public ResponseEntity<?> getArticles(String kind, String query, Integer display, Integer start, String sort,
			int type) throws Exception {
		String clientId = "erff13rBEZnSA5y3uKYt"; // 애플리케이션 클라이언트 아이디
		String clientSecret = "QGz0PHnbn5"; // 애플리케이션 클라이언트 시크릿

		try {
			// 검색어
			query = URLEncoder.encode(query, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("검색어 인코딩 실패", e);
		}

		String apiURL = "https://openapi.naver.com/v1/search/" + kind + "query=" + query + "&display=" + display
				+ "&start=" + start + "&sort=" + sort; // JSON 결과
		
		Map<String, String> requestHeaders = new HashMap<>();
		requestHeaders.put("X-Naver-Client-Id", clientId);
		requestHeaders.put("X-Naver-Client-Secret", clientSecret);

		String responseBody = get(apiURL, requestHeaders);
		// ========================================================
		
		JsonParser jsonParser = new BasicJsonParser();
		Map<String, Object> map = jsonParser.parseMap(responseBody);
		
		String defImg = "https://blogimgs.pstatic.net/nblog/mylog/post/og_default_image_160610.png";
		JSONArray arr = new JSONArray();
		ArrayList list = (ArrayList)map.get("items");
		String[] words = {"title", "originallink", "description"};
		String[] nexts = {"originallink", "link", "pubDate"};
		if (list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> item = new HashMap<>();
				String link = "";
				String image = "";
				
				String tmp = list.get(i).toString().replaceAll(":\"", "=").replaceAll("\"", "").replaceAll("	", "")
								.replaceAll("link","*link").replaceAll("pubDate","*pubDate").replaceAll("original\\*link", "*originallink");
				
				// 뉴스 기사 썸네일
				if (type == 0) {
					int cnt = tmp.length() - tmp.replace(String.valueOf('{'), "").length();
					for (int loop = 0; loop < cnt; loop++) {
						int idx = 0;
						int next = 0;
						for (int j = 0; j < words.length; j++) {
							idx = tmp.indexOf(words[j]) + words[j].length() + 1;
							String val = tmp.substring(idx, (tmp.substring(idx).indexOf("*") + idx)).replaceAll("	", "").trim();
							val = val.substring(0, val.lastIndexOf(","));
							item.put(words[j], val);
						}
						tmp = tmp.substring(idx);
						Document doc = Jsoup.connect(item.get("originallink").toString()).get();
						image = doc.select("meta[property='og:image']").attr("content");
						
					}
				}
				// 블로그 포스팅 썸네일
				else {
					if (list.get(i) instanceof String) {
						item = jsonParser.parseMap((String) list.get(i));
					} else {
						item = (Map<String, Object>) list.get(i);
					}
					link = (String) item.get("link");

					Document doc = Jsoup.connect(link).get();
					// iframe 태그에 있는 진짜 블로그 주소 가져오기
					Elements iframes = doc.select("iframe#mainFrame");
					String src = iframes.attr("src");
					// 진짜 블로그 주소 document 가져오기
					String url2 = "http://blog.naver.com" + src;
					Document doc2 = Jsoup.connect(url2).get();

					// 블로그에서 원하는 블로그 페이지 가져오기
					String[] blog_logNo = src.split("&");
					String[] logNo_split = blog_logNo[1].split("=");
					String logNo = logNo_split[1];

					// 찾고자 하는 블로그 본문 가져오기
					String real_blog_addr = "div#post-view" + logNo;

					Elements blog_element = doc2.select(real_blog_addr);
					// 블로그 썸네일 가져오기
					image = doc2.select("meta[property=og:image]").get(0).attr("content");

					if (image.equals("") || image.equals(null))
						image = defImg;
				}

				item.put("image", image);
				JSONObject jmap = new JSONObject(item);
				arr.add(jmap);
			}
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<String>(arr.toString(), HttpStatus.OK);
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
