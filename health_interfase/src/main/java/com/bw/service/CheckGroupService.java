package com.bw.service;

import com.bw.entity.PageResult;
import com.bw.pojo.CheckGroup;

public interface CheckGroupService {

	void add(CheckGroup checkGroup, Integer[] checkitemIds);

	PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);

}
