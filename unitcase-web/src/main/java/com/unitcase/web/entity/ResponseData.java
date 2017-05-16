package com.unitcase.web.entity;

public class ResponseData {

	private int code;
	
	private String msg;
	
	private Object data;
	
	public ResponseData() {}
	
	public ResponseData(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public ResponseData(Object data) {
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResponseBody [code=" + code + ", msg=" + msg + ", data=" + data + "]";
	}
}
