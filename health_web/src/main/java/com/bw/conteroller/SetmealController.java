package com.bw.conteroller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bw.constant.MessageConstant;
import com.bw.entity.PageResult;
import com.bw.entity.QiniuUtils;
import com.bw.entity.QueryPageBean;
import com.bw.entity.RedisConstant;
import com.bw.entity.Result;
import com.bw.entity.StatusCode;
import com.bw.pojo.Setmeal;
import com.bw.service.SetmealService;

import redis.clients.jedis.JedisPool;


@RequestMapping("setmeal")
@RestController
public class SetmealController {
	@Reference
	private SetmealService setmealService;
	@Autowired
	private JedisPool jedisPool;
	
	@RequestMapping("/upload")
	public Result upload(@RequestParam("imgFile") MultipartFile imgFile)
	{
		try {
			//获取原始文件名
			String originalFilename = imgFile.getOriginalFilename();
			int lastIndexOf = originalFilename.lastIndexOf(".");
			//获取文件后缀
			String suffix = originalFilename.substring(lastIndexOf-1);
			//使用UUID随机产生文件名称，防止同名文件覆盖
			String fileName = UUID.randomUUID().toString() + suffix;
			QiniuUtils.upload2Qiniu(imgFile.getBytes(),fileName);
			jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_RESOURCES,fileName
					);
			return new Result(true, StatusCode.OK,MessageConstant.PIC_UPLOAD_SUCCESS, fileName);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			//图片上传失败
			return new Result(false,StatusCode.ERROR,MessageConstant.PIC_UPLOAD_FAIL);
		}
	}
	@RequestMapping("add")
	public Result add(@RequestBody Setmeal setmeal,Integer[] checkgroupIds)
	{
		 try {
			setmealService.add(setmeal,checkgroupIds);
			}catch (Exception e){
			//新增套餐失败
			return new Result(false,StatusCode.ERROR,MessageConstant.ADD_SETMEAL_FAIL);
			}
			//新增套餐成功
			return new Result(true,StatusCode.OK,MessageConstant.ADD_SETMEAL_SUCCESS);
	}
	@RequestMapping("findPage")
	public PageResult findPage(@RequestBody QueryPageBean queryPageBean)
	{
		PageResult pageResult = setmealService.pageQuery(
		queryPageBean.getCurrentPage(),
		queryPageBean.getPageSize(),
		queryPageBean.getQueryString()
		);
		return pageResult;
	}

}
