package com.bw.dao;


import java.util.Set;

import org.apache.ibatis.annotations.Select;

import com.bw.pojo.Role;

public interface RoleDao {
	@Select("select  r.* from t_role r ,t_user_role ur where r.id = ur.role_id and ur.user_id = #{userId}")
    public Set<Role> findByUserId(Integer userId);
}
