package com.bw.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bw.constant.MessageConstant;
import com.bw.entity.Result;
import com.bw.entity.StatusCode;
import com.bw.pojo.Setmeal;
import com.bw.service.SetmealService;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 套餐管理
 */

@RestController
@RequestMapping("/setmeal")
public class SetmealController {
    @Reference
    private SetmealService setmealService;
    //查询所有套餐
    @RequestMapping("/getSetmeal")
    public Result getAllSetmeal(){
        try{
            List<Setmeal> list = setmealService.findAll();
            return new Result(true,StatusCode.OK, MessageConstant.GET_SETMEAL_LIST_SUCCESS,list);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,StatusCode.ERROR, MessageConstant.GET_SETMEAL_LIST_FAIL);
        }
    }

    //根据套餐ID查询套餐详情（套餐基本信息、套餐对应的检查组信息、检查组对应的检查项信息）
    @RequestMapping("/findById")
    public Result findById(int id){
        try{
            Setmeal setmeal = setmealService.findById(id);
            return new Result(true,StatusCode.OK, MessageConstant.QUERY_SETMEAL_SUCCESS,setmeal);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,StatusCode.ERROR, MessageConstant.QUERY_SETMEAL_FAIL);
        }
    }
}
