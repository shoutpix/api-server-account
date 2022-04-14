package com.swap.apis.controller.account;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swap.apis.model.account.AccountInfo;
import com.swap.apis.model.account.LoginRequestModel;
import com.swap.apis.service.AccountService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/AccountController/")
@RequiredArgsConstructor
public class  AccountController {
	
	private final AccountService accountService ;
	
	@PostMapping("/login")
	@ApiOperation(value = "로그인", tags = "account")
	public ResponseEntity login(@RequestBody LoginRequestModel request) {
		return ResponseEntity.ok().body(Map.of("value", accountService.selectAccountInfo()));
	}
	
	
	@GetMapping("/login2")
	@ApiOperation(value = "로그인", tags = "account")
	public ResponseEntity<Map<String, AccountInfo>> login2() {
		return ResponseEntity.ok().body(Map.of("value", accountService.selectAccountInfo()));
	}
}