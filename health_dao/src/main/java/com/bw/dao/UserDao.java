package com.bw.dao;

import org.apache.ibatis.annotations.Select;

import com.bw.pojo.User;

public interface UserDao {
	@Select("select * from t_user where username = #{username}")
    public User findByUsername(String username);
}
