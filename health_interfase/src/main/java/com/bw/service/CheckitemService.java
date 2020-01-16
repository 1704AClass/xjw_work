package com.bw.service;

import java.util.List;

import com.bw.entity.PageResult;
import com.bw.pojo.CheckItem;

public interface CheckitemService {

	/*
	 * t_checkitem表的列表
	 */
	public PageResult List(int page,int size,String name);
	/*
	 * t_checkitem表的添加
	 */
	public void add(CheckItem checkItem);
	/*
	 * t_checkitem表的删除
	 */
	public  void del (Integer id);
	/*
	 * t_checkitem表的根据id查询
	 */
	public CheckItem findOne(Integer id);
	/*
	 * t_checkitem表的修改
	 */
	public void update(CheckItem checkItem);
}
