package com.unitcase.exception;

public class BaseException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4760041273813322516L;
	
	private final int code;
	
	private final String msg;
	
	public BaseException(int code, String msg) {
		super(msg);
		this.code = code;
		this.msg  = msg;
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
}
