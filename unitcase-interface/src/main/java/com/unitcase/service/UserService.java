package com.unitcase.service;

import com.unitcase.entity.User;
import com.unitcase.exception.UserException;

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
	public User getUser(Long userId) throws UserException;
}
