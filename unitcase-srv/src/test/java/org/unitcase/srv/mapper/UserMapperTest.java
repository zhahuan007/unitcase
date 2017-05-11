package org.unitcase.srv.mapper;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.unitcase.srv.BaseMapperTest;

import com.unitcase.entity.User;
import com.unitcase.srv.mapper.UserMapper;

public class UserMapperTest extends BaseMapperTest {

	@Autowired
	private UserMapper userMapper;
	
	/**
	 * 创建用户
	 */
	@Test
	public void addUser() {
		User user = new User();
		user.setUserId(101L);
		user.setName("testAbc");
		user.setEmail("xxx@abc.com");
		userMapper.addUser(user);
	}
	
	/**
	 * 查询用户
	 */
	@Test
	public void getUser() {
		Long userId = 10001L;
		
		User user = userMapper.getUser(userId);
		System.out.println(user);
		
		Assert.assertTrue(null != user);
		Assert.assertEquals("姓名不匹配", "abc", user.getName());
	}
	
}
