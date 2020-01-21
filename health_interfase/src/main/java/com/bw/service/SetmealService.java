package com.bw.service;

import java.util.List;

import com.bw.entity.PageResult;
import com.bw.pojo.Setmeal;

public interface SetmealService {

	void add(Setmeal setmeal, Integer[] checkgroupIds);

	PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);

	Setmeal findById(int id);

	List<Setmeal> findAll();

}
