package com.swap.apis.util;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpUtil {

	// 헤더
	private HttpHeaders headers;
	// 요청 바디 : <Key, Value> 쌍
	private MultiValueMap<String, String> body;
	// 타임아웃
	private HttpComponentsClientHttpRequestFactory factory;
	// 요청 URL
	private StringBuilder urlBuilder;
	// 요청 Server URL
	private String serverUrl;

	private boolean queryStringToken;

	private ObjectMapper objectMapper;

	private JSONObject jsonObject;

    @Value("${restTemplate.factory.readTimeout}")
    private int READ_TIMEOUT;
    
    @Value("${restTemplate.factory.connectTimeout}")
    private int CONNECT_TIMEOUT;
    
	
	// 요청 방식
	private String method;

	public HttpUtil() {
		this.headers = new HttpHeaders();
		this.factory = new HttpComponentsClientHttpRequestFactory();
		this.factory.setConnectTimeout(CONNECT_TIMEOUT);
		this.factory.setReadTimeout(READ_TIMEOUT);
		this.body = new LinkedMultiValueMap<String, String>();
		this.queryStringToken = true;
		this.objectMapper = new ObjectMapper();
	}

	/**
	 * content-type 설정 : new MediaType 설정 값
	 *
	 * @param type
	 * @param subType
	 * @param charSet
	 * @return
	 */
	public HttpUtil contentType(String type, String subType, String charSet) {
		this.headers.setContentType(new MediaType(type, subType, Charset.forName(charSet)));
		return this;
	}

	/**
	 * connect-timeout 설정<br>
	 * default : 5초
	 *
	 * @param time
	 * @return
	 */
	public HttpUtil connectTimeout(int time) {
		this.factory.setConnectTimeout(time);
		return this;
	}

	/**
	 * read-timeout 설정<br>
	 * default : 5초
	 *
	 * @param time
	 * @return
	 */
	public HttpUtil readTimeout(int time) {
		this.factory.setReadTimeout(time);
		return this;
	}

	/**
	 * 요청 URL 설정
	 *
	 * @param url
	 * @return
	 */
	public HttpUtil url(String url) {
		this.urlBuilder = new StringBuilder();
		urlBuilder.append(url);
		return this;
	}

	/**
	 * 쿼리스트링 설정
	 *
	 * @param name
	 * @param value
	 * @return
	 */
	public HttpUtil queryString(String name, String value) {
		Assert.notNull(urlBuilder, "url 미입력");

		if (queryStringToken) {
			urlBuilder.append("?").append(name).append("=").append(value);
			queryStringToken = false;
		} else {
			urlBuilder.append("&").append(name).append("=").append(value);
		}

		return this;
	}

	/**
	 * 요청 방식 설정(get, post)
	 *
	 * @param method
	 * @return
	 */
	public HttpUtil method(String method) {
		this.method = method.toUpperCase();
		return this;
	}

	/**
	 * 요청 헤더 설정
	 *
	 * @param name
	 * @param value
	 * @return
	 */
	public HttpUtil header(String name, String value) {
		headers.set(name, value);
		return this;
	}

	/**
	 * body 요청 파라미터 설정 : key, value
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public HttpUtil body(String key, String value) {
		this.body.add(key, value);
		return this;
	}

	/**
	 * body 요청 파라미터 설정 : map
	 *
	 * @param params
	 * @return
	 */
	public HttpUtil body(HashMap<String, Object> params) {
		Iterator<String> itr = params.keySet().iterator();
		while (itr.hasNext()) {
			String key = itr.next();
			body.add(key, (String) params.get(key));
		}
		return this;
	}

	/**
	 * HTTP 요청 후 결과 반환(status, header, body)
	 *
	 * @return
	 */
	public HashMap<String, Object> build() {
		HashMap<String, Object> result = new HashMap<>();

		RestTemplate restTemplate = new RestTemplate(factory);
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(body, headers);
		serverUrl = urlBuilder.toString();

		ResponseEntity<String> response = null;
		if ("GET".equals(method)) {
			response = restTemplate.exchange(serverUrl, HttpMethod.GET, entity, String.class);
		} else if ("POST".equals(method)) {
			response = restTemplate.exchange(serverUrl, HttpMethod.POST, entity, String.class);
		}

		result.put("status", response.getStatusCode());
		result.put("header", response.getHeaders());
		result.put("body", response.getBody());

		return result;
	}

	/**
	 * 인증서버 token 받을 시 활용
	 *
	 * @param domain      애플리케이션에 할당된 도메인
	 * @param apiPort     API Port 번호
	 * @param appId       애플리케이션 아이디
	 * @param authKey     애플리케이션의 Auth Key
	 * @param channelName 이벤트와 메시지를 보낼 채널
	 * @param event       이벤트
	 * @param message     메시지
	 * 
	 * @return ResponseEntity<String> 
	 */

	public ResponseEntity<String> sendByPost(String serverUrl, HashMap<String, Object> params) {

		RestTemplate restTemplate = new RestTemplate(factory);
		String body = null;
		try {
			body = objectMapper.writeValueAsString(params);
		} catch (JsonProcessingException e) {

			e.printStackTrace();
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

		HttpEntity entity = new HttpEntity(body, headers);

		ResponseEntity<String> res = restTemplate.postForEntity(serverUrl, entity, String.class);
		
		return res;
	}

}
