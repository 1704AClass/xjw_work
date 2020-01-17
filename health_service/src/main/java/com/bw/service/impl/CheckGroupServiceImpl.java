package com.bw.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.bw.dao.CheckGroupDao;
import com.bw.entity.PageResult;
import com.bw.pojo.CheckGroup;
import com.bw.pojo.CheckItem;
import com.bw.service.CheckGroupService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
@Service
public class CheckGroupServiceImpl implements CheckGroupService {

	@Autowired
	private CheckGroupDao checkGroupDao;
	@Override
	/**
	 * 添加checkgroup表
	 */
	public void add(CheckGroup checkGroup, Integer[] checkitemIds) {
		// TODO Auto-generated method stub
		checkGroupDao.add(checkGroup);
		setCheckGroupAndCheckItem(checkGroup.getId(),checkitemIds);
	}
	/**
	 * 添加中间表
	 * @param id
	 * @param checkitemIds
	 */
	private void setCheckGroupAndCheckItem(Integer id, Integer[] checkitemIds) {
		// TODO Auto-generated method stub
		if(checkitemIds!=null &&checkitemIds.length >0)
		{
			for (Integer checkitemid : checkitemIds) {
				Map<String, Integer> map = new HashMap<>();
				map.put("checkgroup_id",id);
				map.put("checkitem_id",checkitemid);
				checkGroupDao.setCheckGroupAndCheckItem(map);
			}
		}
	}
	/**
	 * 分页模糊查询
	 */
	@Override
	public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString) {
		PageHelper.startPage(currentPage, pageSize);
		if(queryString==null)
		{
			queryString="";
		}
		Page<CheckItem> page = checkGroupDao.selectByCondition(queryString);
		return new PageResult(page.getTotal(),page.getResult());
	}

}
