package com.unitcase.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.unitcase.entity.User;
import com.unitcase.service.UserService;

@Controller
@RequestMapping(value = "/user/")
public class UserController extends BaseController {

	private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);	
	
	@Autowired
	private UserService userService;
	
	/**
	 * 创建用户信息
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value ="addUser", method = RequestMethod.POST)
	public Object addUser(@ModelAttribute(value = "user") User user) {
		LOGGER.info("创建用户，用户信息:" + user);
		userService.addUser(user);
		return successResponse();
	}

	/**
	 * 查看用户信息
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value ="getUser")
	public Object getUser(@RequestParam(value = "userId") Long userId) {
		LOGGER.info("查看用户信息，用户id:%d", userId);
		User user = userService.getUser(userId);
		return successResponse(user);
	}
	
	/**
	 * 上传照片
	 * @param request
	 * @param userId
	 * @param imageBest
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/uploadPhoto")
	public Object uploadPhoto(@RequestParam(value="userId",required=true) Long userId,
							  @RequestParam(value="photo",required=true) MultipartFile photo) throws Exception {
		LOGGER.info("上传照片，用户id:%d", userId);

		userService.uploadPhoto(userId, photo.getBytes());
		return successResponse();
	}
}
