package com.unitcase.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.rpc.RpcException;
import com.unitcase.web.entity.ResponseData;

public abstract class BaseController {

	private static final ResponseData SUCCESS_RESPONSE = new ResponseData(null);
	
	public ResponseData successResponse() {
		return SUCCESS_RESPONSE;
	}
	
	public ResponseData successResponse(Object data) {
		return new ResponseData(data);
	}
	
	public ResponseData failResponse(int code, String msg) {
		return new ResponseData(code, msg);
	}
	
	/**
	 * 必填参数未填异常处理
	 * @param e
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler({RpcException.class})
	public Object missingServletRequestParameterExceptionHandler(Exception e, HttpServletRequest request, HttpServletResponse response) {
		RpcException exception = RpcException.class.cast(e);
		return failResponse(exception.getCode(), exception.getMessage());
	}
	
}
