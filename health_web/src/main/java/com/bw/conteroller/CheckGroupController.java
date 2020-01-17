package com.bw.conteroller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bw.constant.MessageConstant;
import com.bw.entity.PageResult;
import com.bw.entity.QueryPageBean;
import com.bw.entity.Result;
import com.bw.entity.StatusCode;
import com.bw.pojo.CheckGroup;
import com.bw.service.CheckGroupService;

@RequestMapping("CheckGroup")
@RestController
public class CheckGroupController {
	@Reference
	private CheckGroupService checkGroupService;
	/**
	 * 添加checkgroup表和中间表
	 * @param checkGroup
	 * @param checkitemIds
	 * @return
	 */
	@RequestMapping("/add")
	public Result add(@RequestBody CheckGroup checkGroup,Integer[] checkitemIds)
	{
		try {
			checkGroupService.add(checkGroup,checkitemIds);
			}catch (Exception e){
			//新增失败
	return new Result(false,StatusCode.ERROR,MessageConstant.ADD_CHECKGROUP_FAIL);
	}
		//新增成功
		return new Result(true,StatusCode.OK,MessageConstant.ADD_CHECKGROUP_SUCCESS);
	}
	/**
	 * 分页,模糊查询
	 * @param queryPageBean
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
		PageResult pageQuery = checkGroupService.pageQuery(queryPageBean.getCurrentPage(), queryPageBean.getPageSize(), queryPageBean.getQueryString());
		return pageQuery;
	}

}
