package com.swap.apis.controller.authentication;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swap.apis.model.account.LoginRequestModel;
import com.swap.apis.model.account.SignUpRequestModel;
import com.swap.apis.service.AuthService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/Authentication")
@RequiredArgsConstructor
public class  AuthenticationController {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

	private final AuthService authService ;
	
	@PostMapping("/login")
	@ApiOperation(value = "로그인", tags = "Authentication")
	public ResponseEntity login(@RequestBody LoginRequestModel request) {
		ResponseEntity<String> res = authService.Login(request);
		return ResponseEntity.ok().body(res.getBody());
	}

	@PostMapping("/SignUp")
	@ApiOperation(value = "회원가입", tags = "Authentication")
	public ResponseEntity signUp(@RequestBody SignUpRequestModel request) {
		authService.signUp(request);
		return ResponseEntity.ok().body(Map.of("result", "success"));
	}
	
	@PostMapping("/MFALogin")
	@ApiOperation(value = "다중인증 (2차 인증)", tags = "Authentication")
	public ResponseEntity MFALogin(@RequestBody LoginRequestModel request) {
		ResponseEntity<String> res = authService.Login(request);
		return ResponseEntity.ok().body(res.getBody());
	}
	
	@PostMapping("/JeffworldLogin")
	@ApiOperation(value = "제프월드 로그인 확인", tags = "Authentication")
	public ResponseEntity JeffworldLogin(@RequestBody LoginRequestModel request) {
		ResponseEntity<String> res = authService.Login(request);
		return ResponseEntity.ok().body(res.getBody());
	}	
	
	@PostMapping("/AccountLink")
	@ApiOperation(value = "제프월드 계정 연동", tags = "Authentication")
	public ResponseEntity AccountLink(@RequestBody LoginRequestModel request) {
		ResponseEntity<String> res = authService.Login(request);
		return ResponseEntity.ok().body(res.getBody());
	}
	
}