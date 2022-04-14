package com.swap.apis.model.account;

import com.swap.apis.common.definitions.EPlatformCode;

import lombok.Data;

@Data
public class LoginRequestModel {
	private EPlatformCode platformCode; 
	private String userID; 
	private String password;
 }

