package com.unitcase.srv.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.rpc.RpcException;
import com.unitcase.entity.User;
import com.unitcase.exception.ExceptionCons;
import com.unitcase.exception.RpcExceptionHelper;
import com.unitcase.service.UserService;
import com.unitcase.srv.mapper.UserMapper;

@Service("userService")
public class UserServiceImpl implements UserService {

	private final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);	
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public void addUser(User user) {
		LOGGER.info("添加用户:" + user);
		userMapper.addUser(user);
	}

	@Override
	public User getUser(Long userId) throws RpcException {
		LOGGER.info("查询用户,用户ID：", userId);
		
		User user = userMapper.getUser(userId);
		
		if (null == user) {
			throw RpcExceptionHelper.newRpcException(ExceptionCons.USER_EXCEPTION.USER_NOT_FOUND);
		}
		return user;
	}

}
