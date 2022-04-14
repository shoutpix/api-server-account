package com.swap.apis.service;

import org.springframework.stereotype.Service;

import com.swap.apis.mapper.account.AccountMapper;
import com.swap.apis.model.account.AccountInfo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

  private final AccountMapper accountMapper;

  public AccountServiceImpl(AccountMapper accountMapper) {
    this.accountMapper = accountMapper;
  }
  
  @Override
  public AccountInfo selectAccountInfo() {
	AccountInfo accountInfo = accountMapper.selectAccountInfo();
  	return accountInfo;
  }
  
}
