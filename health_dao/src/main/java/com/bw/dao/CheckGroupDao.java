package com.bw.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.bw.pojo.CheckGroup;
import com.bw.pojo.CheckItem;
import com.github.pagehelper.Page;

public interface CheckGroupDao {

	@Insert(" insert into t_checkgroup(code,name,sex,helpCode,remark,attention) values (#{code},#{name},#{sex},#{helpCode},#{remark},#{attention})")
	void add(CheckGroup checkGroup);

	@Insert("insert into t_checkgroup_checkitem(checkgroup_id,checkitem_id) values (#{checkgroup_id},#{checkitem_id})")
	void setCheckGroupAndCheckItem(Map<String, Integer> map);

	@Select("select * from t_checkgroup where code = #{queryString} or name = #{queryString} or helpCode = #{queryString}")
	Page<CheckItem> selectByCondition(String queryString);

}
