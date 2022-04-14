package com.swap.apis.model.account;

import java.math.BigInteger;
import java.sql.Date;

import lombok.Data;

@Data
public class AccountInfo {
  private BigInteger guid;
  private int shardId;
  private String description;
  private int accountType;
  private int accountStatus;
  private BigInteger sanctionSeq;  
  private Date disposalDate;
  private int isLogin;
  private BigInteger jeffWorldGuid; 
  private String jeffWorldId; 
  private Date lastConnDate; 
  private Date createDate;

 }

