package com.service;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapper.UserMapper;
import com.po.User;
@Service
public class UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	@Autowired
	private UserMapper userMapper;
	
	private User findUser(HttpServletRequest request) {
		
		User user = userMapper.findUser("0327a822-8c77-418c-8813-7db296bd3d5d");
		
		return user;
		
	}
	
}
