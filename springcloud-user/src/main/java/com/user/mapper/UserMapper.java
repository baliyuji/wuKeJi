package com.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.user.po.User;
@Mapper
public interface UserMapper {

	//@Select("select * from lm_user where id = #{id}")
	User findUser(@Param("id") String id);
}
