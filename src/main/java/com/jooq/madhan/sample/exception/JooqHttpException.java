package com.jooq.madhan.sample.exception;

public class JooqHttpException {

	private static final long serialVersionUID = 1L;

	private String httpErrorCode;
	private String httpErrorStatus;

	public JooqHttpException() {
		super();
	}

	public JooqHttpException(String errorCode, String errorMessage) {
		this.httpErrorCode = errorCode;
		this.httpErrorStatus = errorMessage;
	}

	public String getHttpErrorCode() {
		return httpErrorCode;
	}

	public String getHttpErrorStatus() {
		return httpErrorStatus;
	}
}
