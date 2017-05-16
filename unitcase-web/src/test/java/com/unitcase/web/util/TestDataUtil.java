package com.unitcase.web.util;

import java.util.Date;

import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.unitcase.entity.User;
import com.unitcase.exception.ExceptionCons;
import com.unitcase.exception.RpcExceptionHelper;
import com.unitcase.service.UserService;

public class TestDataUtil {

	/**
	 * 用户接口mock
	 * @return
	 */
	public UserService createUserService() {
		UserService userService = Mockito.mock(UserService.class);
		
		Mockito.doNothing().when(userService).addUser(Mockito.any(User.class));
		Mockito.doNothing().when(userService).uploadPhoto(Mockito.anyLong(), Mockito.any(byte[].class));
		
		final Long errUserId = 101L;
		
		Mockito.when(userService.getUser(Mockito.anyLong())).thenAnswer(new Answer<User>() {

			@Override
			public User answer(InvocationOnMock invocation) throws Throwable {
				Long userId = invocation.getArgumentAt(0, Long.class);
				
				if (userId.compareTo(errUserId) == 0) {
					throw RpcExceptionHelper.newRpcException(ExceptionCons.USER_EXCEPTION.USER_NOT_FOUND);
				}
				
				User user = new User();
				user.setUserId(userId);
				user.setName("test");
				user.setEmail("abc@mail.com");
				user.setRegDate(new Date());
				return user;
			}
		});
		return userService;
	}
	
}
