package com.swap.apis.exception;

import com.swap.apis.common.definitions.JeffResultCode;

import lombok.Data;

@Data
public class JeffException extends RuntimeException {

	private final ApisExceptionType ApisExceptionType;
	private final JeffResultCode jeffResultCode;
	
	public JeffException(ApisExceptionType ApisExceptionType, JeffResultCode jeffResultCode) {
		super(jeffResultCode.getExplanation());
		this.ApisExceptionType = ApisExceptionType;
		this.jeffResultCode = jeffResultCode;
	}

	@Override
	public String toString() {
		return "JeffException{" + "exceptionType=" + ApisExceptionType.getType() + ", jeffResultCode=" + jeffResultCode.getValue() + ", message='" + getMessage() + '\'' + '}';
	}
}