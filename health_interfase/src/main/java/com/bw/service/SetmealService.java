package com.bw.service;

import com.bw.entity.PageResult;
import com.bw.pojo.Setmeal;

public interface SetmealService {

	void add(Setmeal setmeal, Integer[] checkgroupIds);

	PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);

}
