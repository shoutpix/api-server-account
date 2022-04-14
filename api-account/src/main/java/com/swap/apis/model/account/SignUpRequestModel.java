package com.swap.apis.model.account;

import lombok.Data;

@Data
public class SignUpRequestModel {
  private String platformCode;
  private String userID;
  private String password;
  private String countryNumber;
 }

