package com.bw.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.bw.pojo.Setmeal;
import com.github.pagehelper.Page;

public interface SetmealDao {

	@Insert("insert into t_setmeal (code,name,sex,age,helpCode,price,remark,attention,img) values (#{code},#{name},#{sex},#{age},#{helpCode},#{price},#{remark},#{attention},#{img})")
	void add(Setmeal setmeal);
	@Insert("insert into t_setmeal_checkgroup (setmeal_id,checkgroup_id) values (#{setmeal_id},#{checkgroup_id})")
	void setSetmealAndCheckGroup(Map<String, Integer> map);
	@Select("select * from t_setmeal where code = #{value} or name = #{value} or helpCode = #{value}")
	Page<Setmeal> selectByCondition(String queryString);
	@Select("select * from t_setmeal where id=#{id}")
	Setmeal findById(int id);
	
	@Select("select * from t_setmeal")
	List<Setmeal> findAll();
	@Select("select s.name,count(o.id) as value from t_order o ,t_setmeal s where o.setmeal_id = s.id group by s.name")
	List<Map<String, Object>> findSetmealCount();

}
