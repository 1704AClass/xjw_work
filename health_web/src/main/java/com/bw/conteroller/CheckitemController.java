;'0'
3'0












...2.2225.3.03package com.bw.conteroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bw.constant.MessageConstant;
import com.bw.entity.Result;
import com.bw.entity.StatusCode;
import com.bw.pojo.CheckItem;
import com.bw.service.CheckitemService;

@RestController
@RequestMapping("checkitem")
public class CheckitemController {
	
	//注入service
	@Reference
	private CheckitemService checkitemService;
	/**
	 * 列表
	 * 分页
	 * 模糊
	 * @param page
	 * @param size
	 * @param name
	 * @return
	 */
	@RequestMapping("list")
	public Result list(int page, int size, String name)
	{
		//抛出异常
		try {
			//返回正确Result对象和列表
			return new Result(true, StatusCode.OK, "", checkitemService.List(page, size, name));
		} catch (Exception e) {
			// TODO: handle exception
			return new Result(false, StatusCode.ERROR, "网络连接失败");
		}
		
	}
	/**
	 * 添加
	 * @param checkItem
	 * @return
	 */
	@RequestMapping("add")
	public Result add(@RequestBody CheckItem checkItem)
	{
		//抛出异常
		try {
			//添加方法
			checkitemService.add(checkItem);
			//返回正确的Result
			return new Result(true, StatusCode.OK, "添加成功");
		} catch (Exception e) {
			// TODO: handle exception
			
			e.printStackTrace();
			return new Result(false, StatusCode.ERROR, "添加失败");
		}
		
	}
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping("del")
	public Result del(Integer id)
	{
		//抛出异常
		try {
			//调用删除方法
			checkitemService.del(id);
			//返回正确Result
			return new Result(true, StatusCode.OK, "删除成功");
		} catch (Exception e) {
			// TODO: handle exception
			//
			e.printStackTrace();
			return new Result(false, StatusCode.ERROR , "删除失败");
		}
	}
	/**
	 * 修改
	 * @param checkItem
	 * @return
	 */
	@RequestMapping("update")
	public Result update(@RequestBody CheckItem checkItem)
	{
		//抛出异常
		try {
			//修改
			checkitemService.update(checkItem);
			return new Result(true, StatusCode.OK, "修改成功");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new Result(false, StatusCode.ERROR, "修改失败");
		}
	}
	/**
	 * 回显
	 * @param id
	 * @return
	 */
	@RequestMapping("findOne")
	public Result findOne(Integer id)
	{
		//抛出异常
		try {
			//返回正确Result和findone方法
			return new Result(true, StatusCode.OK, "", checkitemService.findOne(id));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new Result(false, StatusCode.ERROR, "网络连接失败");
		}
	}

	//查询所有
	/*@RequestMapping("/findAll")
	public Result findAll(){
	  List<CheckItem> checkItemList = checkitemService.findAll();
	  if(checkItemList != null && checkItemList.size() > 0){
	    Result result = new Result(true,
	MessageConstant.QUERY_CHECKITEM_SUCCESS);
	    result.setData(checkItemList);
	    return result;
	  }
	  return new Result(false,StatusCode.OK,MessageConstant.QUERY_CHECKITEM_FAIL);
	}*/
	@RequestMapping("/findAll")
	public Result findAll()
	{
		List<CheckItem> checkItemList = checkitemService.findAll();
		if(checkItemList!=null&&checkItemList.size()>0){
			return new Result(true, StatusCode.OK, MessageConstant.QUERY_CHECKITEM_SUCCESS, checkItemList);
		}
		return new Result(false, StatusCode.ERROR, MessageConstant.QUERY_CHECKITEM_FAIL);
		}
			
	
}
