package com.unitcase.service;

import org.omg.CORBA.UserException;

import com.alibaba.dubbo.rpc.RpcException;
import com.unitcase.entity.User;

public interface UserService {

	/**
	 * 创建用户
	 * @param user
	 */
	public void addUser(User user);
	
	/**
	 * 查询用户
	 * @param userId
	 * @return
	 * @throws UserException
	 */
	public User getUser(Long userId) throws RpcException;
	
	/**
	 * 上传照片
	 * @param userId
	 * @param fileBytes
	 */
	public void uploadPhoto(Long userId, byte[] fileBytes);
}
