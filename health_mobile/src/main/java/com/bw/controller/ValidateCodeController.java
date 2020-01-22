package com.bw.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bw.constant.MessageConstant;
import com.bw.constant.RedisMessageConstant;
import com.bw.entity.Result;
import com.bw.entity.SMSUtils;
import com.bw.entity.StatusCode;
import com.bw.entity.ValidateCodeUtils;

import redis.clients.jedis.JedisPool;

/**
 * 验证码操作
 */

@RestController
@RequestMapping("/validateCode")
public class ValidateCodeController {
    @Autowired
    private JedisPool jedisPool;

    //用户在线体检预约发送验证码
    @RequestMapping("/send4Order")
    public Result send4Order(String telephone){
        //随机生成4位数字验证码
        Integer validateCode = ValidateCodeUtils.generateValidateCode(4);
        //给用户发送验证码
        try{
            SMSUtils.sendShortMessage(SMSUtils.VALIDATE_CODE,telephone);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,StatusCode.ERROR, MessageConstant.SEND_VALIDATECODE_FAIL);
        }
        //将验证码保存到redis（5分钟）
        jedisPool.getResource().setex(telephone + RedisMessageConstant.SENDTYPE_ORDER,300,validateCode.toString());
        return new Result(true,StatusCode.OK,MessageConstant.SEND_VALIDATECODE_SUCCESS);
    }
}
