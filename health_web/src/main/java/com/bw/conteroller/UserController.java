package com.bw.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bw.constant.MessageConstant;
import com.bw.entity.Result;
import com.bw.entity.StatusCode;

/**
 * 用户操作
 */
@RestController
@RequestMapping("/user")
public class UserController {
    //获得当前登录用户的用户名
    @RequestMapping("/getUsername")
    public Result getUsername(){
        //当Spring security完成认证后，会将当前用户信息保存到框架提供的上下文对象
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(user);
        if(user != null){
            String username = user.getUsername();
            return new Result(true,StatusCode.OK, MessageConstant.GET_USERNAME_SUCCESS,username);
        }

        return new Result(false,StatusCode.ERROR, MessageConstant.GET_USERNAME_FAIL);
    }
}
