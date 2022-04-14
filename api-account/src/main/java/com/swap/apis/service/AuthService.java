package com.swap.apis.service;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;

import com.swap.apis.model.account.LoginRequestModel;
import com.swap.apis.model.account.SignUpRequestModel;

public interface AuthService {
 
	ResponseEntity<String> Login(LoginRequestModel loginRequestModel);
  
    void LogOut(String accessToken, String refreshToken);
    
    void signUp(SignUpRequestModel request);
}
