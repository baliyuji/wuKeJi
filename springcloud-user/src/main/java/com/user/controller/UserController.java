package com.user.controller;

import com.user.mapper.UserMapper;
import com.user.po.User;
import com.user.util.Reply;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private UserMapper userMapper;

	@PostMapping(value = "/findUser")
	@ResponseBody
	private Reply findUser(HttpServletRequest request) {
		logger.info("进入UserController");
		User user = userMapper.findUser("0327a822-8c77-418c-8813-7db296bd3d5d");

		return Reply.success().data(user);

	}
}
