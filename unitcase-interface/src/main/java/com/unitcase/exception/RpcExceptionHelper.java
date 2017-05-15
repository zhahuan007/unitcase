package com.unitcase.exception;

import com.alibaba.dubbo.rpc.RpcException;

public class RpcExceptionHelper {

	public static RpcException newRpcException(ExceptionCons cons) {
		return newRpcException(cons.getCode(), cons.getMsg());
	}
	
	public static RpcException newRpcException(int code, String msg) {
		return new RpcException(code, msg);
	}
}
