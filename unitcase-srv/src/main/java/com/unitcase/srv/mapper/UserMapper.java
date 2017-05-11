package com.unitcase.srv.mapper;

import com.unitcase.entity.User;

public interface UserMapper {

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
	public User getUser(Long userId);
	
}
