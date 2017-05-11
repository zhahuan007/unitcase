package com.unitcase.exception;

public interface ExceptionCons {
	
	/**
	 * 异常code
	 * @return
	 */
	public int getCode();
	
	/**
	 * 异常msg
	 * @return
	 */
	public String getMsg();
	
	/**
	 * 用户异常
	 * @author zhahuan
	 *
	 */
	public enum USER_EXCEPTION implements ExceptionCons {
		
		USER_SERVICE_ERROR(100, "用户系统接口异常"),
		USER_NOT_FOUND(101, "用户不存在");
		
		private int code;
		
		private String msg;
		
		USER_EXCEPTION(int code, String msg){
			this.code = code;
			this.msg = msg;
		}

		@Override
		public int getCode() { return this.code; }

		@Override
		public String getMsg() { return this.msg; }
	}
}
