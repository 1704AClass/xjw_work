package com.bw.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.aliyuncs.exceptions.ClientException;
import com.bw.constant.MessageConstant;
import com.bw.constant.RedisMessageConstant;
import com.bw.entity.Result;
import com.bw.entity.SMSUtils;
import com.bw.entity.StatusCode;
import com.bw.pojo.Order;
import com.bw.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import java.util.Map;

/**
 * 体检预约处理
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private JedisPool jedisPool;
    @Reference
    private OrderService orderService;
    //在线体检预约
    @RequestMapping("/submit")
    public Result submit(@RequestBody Map map) throws ClientException{
        String telephone = (String) map.get("telephone");
        //从Redis中获取保存的验证码
        String validateCodeInRedis = jedisPool.getResource().get(telephone + RedisMessageConstant.SENDTYPE_ORDER);
        String validateCode = (String) map.get("validateCode");

        //将用户输入的验证码和Redis中保存的验证码进行比对
        if(validateCodeInRedis != null && validateCode != null && validateCode.equals(validateCodeInRedis)){
            //如果比对成功，调用服务完成预约业务处理
            map.put("orderType", Order.ORDERTYPE_WEIXIN);//设置预约类型，分为微信预约、电话预约
            Result result = null;
            try{
                result = orderService.order(map);//通过Dubbo远程调用服务实现在线预约业务处理
            }catch (Exception e){
                e.printStackTrace();
                return result;
            }
            if(result.isFlag()){
                //预约成功，可以为用户发送短信
            	Sms();
                	
                
            }
            return result;
        }else{
            //如果比对不成功，返回结果给页面
            return new Result(false,StatusCode.ERROR, MessageConstant.VALIDATECODE_ERROR);
        }
    }
    private void Sms() throws ClientException
    {
    	SMSUtils.sendShortMessage("SMS_159771588","15718811600");
    }

    //根据预约ID查询预约相关信息
    @RequestMapping("/findById")
    public Result findById(Integer id){
        try{
            Map map = orderService.findById(id);
            return new Result(true,StatusCode.OK,MessageConstant.QUERY_ORDER_SUCCESS,map);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,StatusCode.ERROR,MessageConstant.QUERY_ORDER_FAIL);
        }
    }
}
