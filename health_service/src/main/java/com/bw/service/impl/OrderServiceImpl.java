package com.bw.service.impl;

import java.sql.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.bw.dao.OrderDao;
import com.bw.entity.DateUtils;
import com.bw.entity.Result;
import com.bw.service.OrderService;
@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderDao orderDao;
	
	@Override
	public Result order(Map map) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map findById(Integer id) throws Exception {
	
		Map map = orderDao.findById4Detail(id);
		if(map!=null)
		{
			Date orderDate = (Date)map.get("orderDate");
			map.put("orderDate", DateUtils.parseDate2String(orderDate));
		}
		return map;
	}
}

