package com.swap.apis.common.definitions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum JeffResultCode {
	
	OK("Ok", 0, "Ok"),
	NOT_IMPLEMENTED("NotImplemented", 100, "NotImplemented"),
	BAD_REQUEST("BadRequest", 101, "BadRequest"),
	INTERNAL_SERVER_ERROR("InternalServerError", 102, "InternalServerError"),
	RESOURCE_NOT_FOUND("ResourceNotFound", 103, "ResourceNotFound"),
    DB_EXCEPTION("DBException", 104, "DBException"),
    RESTAPI_EXCEPTION("RESTApiException", 105, "RESTApiException"),
	
    /**
	 * 인증 관련
	 * code number : 200 ~ 
	 */
    UN_AUTHORIZED("Unauthorized", 200, "인증되지 않은 사용자"), 
    NEW_PLATFORM_ACCOUNT("NewPlatformAccount", 201, "새 플랫폼 계정"), 
    PLATFORM_AUTH_FAILED("PlatformAuthFailed", 202, "플랫폼 인증 실패"),
    IDENTITY_SERVER_FAILED("IdentityServerFailed", 203, "IDServer 인증 실패"),
    INVALID_ACCESS_TOKEN("InvalidAccessToken", 204, "IDServer 인증 실패"),
    INVALID_GUID("InvalidGuid", 205, "잘못된 유저키"),
    REFRESH_TOKEN_FAILED("RefreshTokenFailed", 206, "리플래시토큰 갱신 실패"),
	SIGNOUT_FAILED("SignOutFailed", 207, "로그아웃 실패"),
	NOT_FOUND_TOKEN_INFO("NotFoundTokenInfo", 208, "토큰정보를 찾을 수 없습니다."),
	NOT_FOUND_SESSION_INFO("NotFoundSessionInfo", 209, "세션 정보를 찾을 수 없습니다."),
	NOT_FOUND_SERVICE_INFO("NotFoundServiceInfo", 210, "서비스 정보를 찾을 수 없음"),
	NOT_FOUND_VERSION_INFO("NotFoundVersionInfo", 211, "버전 정보를 찾을 수 없음"),
	NOT_FOUND_SERVER_INFO("NotFoundServerInfo", 212, "서버 정보를 찾을 수 없음"),
	;
  
	private String code;
    private int value;
    private String explanation;
    
    private JeffResultCode(String code, int value, String explanation) {
		this.code = code;
		this.value = value;
		this.explanation = explanation;
	}
}
