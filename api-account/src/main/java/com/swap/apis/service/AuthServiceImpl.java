package com.swap.apis.service;

import java.util.HashMap;

import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.swap.apis.common.definitions.JeffResultCode;
import com.swap.apis.exception.ApisException;
import com.swap.apis.exception.ApisExceptionType;
import com.swap.apis.exception.JeffException;
import com.swap.apis.model.account.LoginRequestModel;
import com.swap.apis.model.account.SignUpRequestModel;
import com.swap.apis.util.HttpUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
	private Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

	@Value("${identityServer.clientid}")
	private String clientId;

	@Value("${identityServer.secretkey}")
	private String secretKey;

	@Value("${identityServer.scope}")
	private String scope;

	@Value("${identityServer.host}")
	private String host;

	@Value("${identityServer.loginurl}")
	private String loginurl;

	@Value("${identityServer.logouturl}")
	private String logoutUrl;

	/**
	 * 로그인
	 *
	 * @param LoginRequestModel 로그인 시 requestModel
	 * @return ResponseEntity<String>
	 */
	@Override
	public ResponseEntity<String> Login(LoginRequestModel loginRequestModel) {

		// 토큰발급
		HttpUtil httpUtil = new HttpUtil();
		HashMap<String, Object> params = new HashMap<>();

		String authUrl = host + loginurl;
		log.debug("  >>>>>>>>>>>>>>>>> authUrl : " + authUrl);
		params.put("ClientID", clientId);
		params.put("ClientSecret", secretKey);
		params.put("Scope", scope);
		params.put("PlatformCode", loginRequestModel.getPlatformCode().getCode());
		params.put("PlatformID", loginRequestModel.getUserID());
		params.put("Password", loginRequestModel.getPassword());

		ResponseEntity<String> response = httpUtil.sendByPost(authUrl, params);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new ApisException(ApisExceptionType.UNAUTHORIZED, "not unauthorized");
		}

		return response;
	}

	@Override
	public void LogOut(String accessToken, String refreshToken) {
		// TODO Auto-generated method stub

	}

	@Override
	public void signUp(SignUpRequestModel request) {
		// if (request == null) {
		String code = JeffResultCode.BAD_REQUEST.name();
		int value = JeffResultCode.BAD_REQUEST.getValue();
		throw new JeffException(ApisExceptionType.BAD_REQUEST, JeffResultCode.BAD_REQUEST);

		// }
	}

}
