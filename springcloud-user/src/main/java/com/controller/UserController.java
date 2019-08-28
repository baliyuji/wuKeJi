package com.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mapper.UserMapper;
import com.po.User;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	@Autowired
	private UserMapper userMapper;

	@PostMapping(value = "/findUser")
	@ResponseBody
	private User findUser(HttpServletRequest request) {

		User user = userMapper.findUser("0327a822-8c77-418c-8813-7db296bd3d5d");

		return user;

	}
}
