package com.unitcase.exception;

public class UserException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 712639902971610240L;

	
	public UserException(int code, String msg) {
		super(code, msg);
	}
	
	public UserException(ExceptionCons cons) {
		super(cons.getCode(), cons.getMsg());
	}
}
