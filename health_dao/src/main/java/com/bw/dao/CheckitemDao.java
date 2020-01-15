package com.bw.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.bw.pojo.CheckItem;

public interface CheckitemDao  {

	@Select("select * from t_checkitem where like %#{name}%")
	public List<CheckItem> List(String name);
	
	public void add(CheckItem checkItem);
	
	public  void del (Integer id);
	
	public CheckItem findOne(Integer id);
	
	public void update(CheckItem checkItem);
}

