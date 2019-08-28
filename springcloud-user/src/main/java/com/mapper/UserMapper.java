package com.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.po.User;

@Mapper
public interface UserMapper {

	@Select("select * from lm_user where id = #{id}")
	User findUser(@Param("id") String id);
}
