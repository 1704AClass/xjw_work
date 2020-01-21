package com.bw.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.bw.dao.SetmealDao;
import com.bw.entity.PageResult;
import com.bw.entity.RedisConstant;
import com.bw.pojo.Setmeal;
import com.bw.service.SetmealService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import redis.clients.jedis.JedisPool;

@Service
public class SetmealServiceImpl implements SetmealService {

	@Autowired
	private SetmealDao setmealDao;
	@Autowired
	private JedisPool jedisPool;
	@Override
	public void add(Setmeal setmeal, Integer[] checkgroupIds) {
		// TODO Auto-generated method stub
		setmealDao.add(setmeal);
		if(checkgroupIds!=null&&checkgroupIds.length>0){
		//绑定套餐和检查组的多对多关系
		setSetmealAndCheckGroup(setmeal.getId(),checkgroupIds);
		}
		//将图片名称保存到Redis
		savePic2Redis(setmeal.getImg());
	}
	private void savePic2Redis(String img) {
		// TODO Auto-generated method stub
		jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES,img);
	}
	private void setSetmealAndCheckGroup(Integer id, Integer[] checkgroupIds) {
		// TODO Auto-generated method stub
		for (Integer checkgroupId : checkgroupIds) {
			Map<String,Integer> map = new HashMap<>();
			map.put("setmeal_id",id);
			map.put("checkgroup_id",checkgroupId);
			setmealDao.setSetmealAndCheckGroup(map);
			}
	}
	@Override
	public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString) {
		PageHelper.startPage(currentPage,pageSize);
		Page<Setmeal> page = setmealDao.selectByCondition(queryString);
		return new PageResult(page.getTotal(),page.getResult());
	}
	@Override
	public Setmeal findById(int id) {
		// TODO Auto-generated method stub
		return setmealDao.findById(id);
	}
	@Override
	public List<Setmeal> findAll() {
		// TODO Auto-generated method stub
		return setmealDao.findAll();
	}

}
