package com.bw.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.container.page.PageHandler;
import com.bw.dao.CheckitemDao;
import com.bw.entity.PageResult;
import com.bw.pojo.CheckItem;
import com.bw.service.CheckitemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
@Service
public class CheckitemServiceImpl implements CheckitemService {

	@Autowired
	private CheckitemDao checkitemDao;
	@Override
	public PageResult List(int page,int size,String name) {
		PageHelper.startPage(page, size);
		if(name==null)
		{
			name="";
		}
		PageInfo<CheckItem> list=new PageInfo<>(checkitemDao.List(name));
		return new PageResult(list.getTotal(),list.getList());
		
	}

	@Override
	public void add(CheckItem checkItem) {
		// TODO Auto-generated method stub
		checkitemDao.add(checkItem);
	}

	@Override
	public void del(Integer id) {
		// TODO Auto-generated method stub
		checkitemDao.del(id);
	}

	@Override
	public CheckItem findOne(Integer id) {
		// TODO Auto-generated method stub
		return checkitemDao.findOne(id);
	}

	@Override
	public void update(CheckItem checkItem) {
		// TODO Auto-generated method stub
		checkitemDao.update(checkItem);
	}

}
