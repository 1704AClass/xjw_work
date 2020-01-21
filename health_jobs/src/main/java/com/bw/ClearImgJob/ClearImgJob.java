package com.bw.ClearImgJob;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.bw.entity.QiniuUtils;
import com.bw.entity.RedisConstant;

import redis.clients.jedis.JedisPool;

public class ClearImgJob {
	@Autowired
	private JedisPool jedisPool;
	
	public void clearImg(){
	//根据Redis中保存的两个set集合进行差值计算，获得垃圾图片名称集合
		
	Set<String> set =jedisPool.getResource().sdiff(RedisConstant.SETMEAL_PIC_RESOURCES,RedisConstant.SETMEAL_PIC_DB_RESOURCES);
		if(set != null){
			for	(String	picName	:set)	{
		//删除七牛云服务器上的图片
		QiniuUtils.deleteFileFromQiniu(picName);
		//从Redis集合中删除图片名称
		jedisPool.getResource().
		srem(RedisConstant.SETMEAL_PIC_RESOURCES,picName);
			}
		}
	}
}