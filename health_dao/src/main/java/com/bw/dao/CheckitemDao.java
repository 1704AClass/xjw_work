package com.bw.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.bw.pojo.CheckItem;

public interface CheckitemDao  {

	/*
	 * t_checkitem表的列表
	 */
	@Select("select * from t_checkitem where like %#{name}%")
	public List<CheckItem> List(String name);
	/*
	 * t_checkitem表的添加
	 */
	@Insert("insert into t_checkitem set code=#{code},name=#{name},sex=0,age=#{age},price=#{price},type=#{type},attention=#{attention},remark=#{remark}")
	public void add(CheckItem checkItem);
	/*
	 * t_checkitem表的删除
	 */
	@Delete("delete from t_checkitem where id=#{id}")
	public  void del (Integer id);
	/*
	 * t_checkitem表的根据id查询
	 */
	@Select("select * from t_checkitem where id =#{id}")
	public CheckItem findOne(Integer id);
	/*
	 * t_checkitem表的修改
	 */
	@Update("update t_checkitem set code=#{code},name=#{name},sex=0,age=#{age},price=#{price},type=#{type},attention=#{attention},remark=#{remark} where id =#{id}")
	public void update(CheckItem checkItem);
}

