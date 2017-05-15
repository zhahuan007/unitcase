package org.unitcase.client.service;

import org.junit.Test;
import org.omg.CORBA.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.unitcase.client.BaseSrvTest;

import com.alibaba.dubbo.rpc.RpcException;
import com.unitcase.entity.User;
import com.unitcase.service.UserService;

public class UserServiceTest extends BaseSrvTest {

	@Autowired
	private UserService userService;
	
	/**
	 * 创建用户
	 */
	@Test
	public void addUser() {
		User user = new User();
		user.setUserId(10002L);
		user.setName("huans");
		user.setEmail("xxx@xxx.com");
		userService.addUser(user);
	}
	
	/**
	 * 查询用户
	 * @throws UserException 
	 */
	@Test
	public void getUser() throws UserException {
		Long userId = 10002L;
		User user = null;
		
		try {
			userService.getUser(userId);
		} catch (RpcException e) {
			System.out.println(e.toString());
		}
		
		userId = 10001L;
		user = userService.getUser(userId);
		
		System.out.println(user);
	}
	
}
