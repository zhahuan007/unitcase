package com.unitcase.web.controller;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.unitcase.exception.ExceptionCons;
import com.unitcase.service.UserService;
import com.unitcase.web.BaseControllerTest;
import com.unitcase.web.entity.ResponseData;
import com.unitcase.web.util.JsonHelper;

public class UserControllerTest extends BaseControllerTest {
	
	@Autowired
	private UserService userService;
	
	@Test
	public void test() {
		System.out.println(userService);
	}
	
	/**
	 * 添加用户
     */
	@Test
	public void addUser() {
		
		String userId = "10003";
		String name = "abc";
		String email= "abc@mail.com";
		try {
			MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/user/addUser")
					.param("userId", userId)
					.param("name", name)
					.param("email", email))
					.andDo(MockMvcResultHandlers.print())
					.andReturn();
			System.out.println("查询结果:" + result.getResponse().getContentAsString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 查看用户信息
	 */
	@Test
	public void getUser() {
		String userId = "101";
		try {
			//测试异常用户ID
			MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/user/getUser")
					.param("userId", userId))
					.andDo(MockMvcResultHandlers.print())
					.andReturn();
			System.out.println("查询结果:" + result.getResponse().getContentAsString());
			
			String json = result.getResponse().getContentAsString();
			ResponseData response = JsonHelper.fromJson(json, ResponseData.class);
			Assert.assertTrue(response.getCode() == ExceptionCons.USER_EXCEPTION.USER_NOT_FOUND.getCode());
			
			//测试正常用户ID
			userId = "10001";
			result = mockMvc.perform(MockMvcRequestBuilders.get("/user/getUser")
					.param("userId", userId))
					.andDo(MockMvcResultHandlers.print())
					.andReturn();
			System.out.println("查询结果:" + result.getResponse().getContentAsString());
			json = result.getResponse().getContentAsString();
			response = JsonHelper.fromJson(json, ResponseData.class);
			Assert.assertTrue(response.getCode() == 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 上传图片
     */
	@Test
	public void uploadPhoto() {
		String photo = "photo";
		final String userId = "10001";
		try {
			MockMultipartFile frontImageFile = new MockMultipartFile(photo, new byte[1]);

	        MockMultipartHttpServletRequestBuilder builder = MockMvcRequestBuilders.fileUpload("/user/uploadPhoto");
	        builder.with(new RequestPostProcessor() {
	            @Override
	            public MockHttpServletRequest postProcessRequest(MockHttpServletRequest request) {
	                request.setMethod("POST");
	                request.addParameter("userId", userId);
	                return request;
	            }
	        });
	        MvcResult result = mockMvc.perform(builder
	                .file(frontImageFile))
	        .andDo(MockMvcResultHandlers.print())
			.andReturn();
			
			System.out.println("查询结果:" + result.getResponse().getContentAsString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
