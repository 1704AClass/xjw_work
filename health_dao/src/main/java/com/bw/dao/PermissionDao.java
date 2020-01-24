package com.bw.dao;


import java.util.Set;

import org.apache.ibatis.annotations.Select;

import com.bw.pojo.Permission;

public interface PermissionDao {
	@Select("select  p.* from t_permission p ,t_role_permission rp where p.id = rp.permission_id and rp.role_id = #{roleId}")
    public Set<Permission> findByRoleId(Integer roleId);
}
