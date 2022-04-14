package com.swap.apis.common.definitions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.swap.apis.exception.ApisExceptionType;
import com.swap.apis.exception.JeffException;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EPlatformCode {
	
	NONE(0,"None"),
	EMAIL(1,"Email"),
	PHONE(2,"Phone")
	;
	
	private int value;
	private String code;
    
    private EPlatformCode(int value, String code) {
    	this.value = value;
    	this.code = code;
	}
    
    private String getCode(int value) { 
    	for(EPlatformCode eCode : EPlatformCode.values()) {
    		if(eCode.value == value) return eCode.code;
    	}
    	throw new JeffException(ApisExceptionType.BAD_REQUEST, JeffResultCode.BAD_REQUEST);
    }
    
    @JsonCreator
    public static String fromEPlatformCode(int val){
        for(EPlatformCode code : EPlatformCode.values()){
            if(code.getValue() == val) return code.code;
        }
        throw new JeffException(ApisExceptionType.BAD_REQUEST, JeffResultCode.BAD_REQUEST);
    }
}
