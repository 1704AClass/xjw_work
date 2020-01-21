package com.bw.conteroller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bw.constant.MessageConstant;
import com.bw.entity.POIUtils;
import com.bw.entity.Result;
import com.bw.entity.StatusCode;
import com.bw.pojo.OrderSetting;
import com.bw.service.OrderSettingService;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 预约设置
 */

@RestController
@RequestMapping("/ordersetting")
public class OrderSettingController {
    @Reference
    private OrderSettingService orderSettingService;

    //文件上传，实现预约设置数据批量导入
    @RequestMapping("/upload")
    public Result upload(@RequestParam("excelFile") MultipartFile excelFile){
        try {
            List<String[]> list = POIUtils.readExcel(excelFile);//使用POI解析表格数据
            List<OrderSetting> data = new ArrayList<>();
            for (String[] strings : list) {
                String orderDate = strings[0];
                String number = strings[1];
                OrderSetting orderSetting = new OrderSetting(new Date(orderDate),Integer.parseInt(number));
                data.add(orderSetting);
            }
            //通过dubbo远程调用服务实现数据批量导入到数据库
            orderSettingService.add(data);
            return new Result(true,StatusCode.OK, MessageConstant.IMPORT_ORDERSETTING_SUCCESS);
        } catch (IOException e) {
            e.printStackTrace();
            //文件解析失败
            return new Result(false,StatusCode.ERROR, MessageConstant.IMPORT_ORDERSETTING_FAIL);
        }
    }

    //根据月份查询对应的预约设置数据
    @RequestMapping("/getOrderSettingByMonth")
    public Result getOrderSettingByMonth(String date){//date格式为：yyyy-MM
        try{
            List<Map> list = orderSettingService.getOrderSettingByMonth(date);
            return new Result(true,StatusCode.OK,MessageConstant.GET_ORDERSETTING_SUCCESS,list);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,StatusCode.ERROR,MessageConstant.GET_ORDERSETTING_FAIL);
        }
    }

    //根据日期设置对应的预约设置数据
    @RequestMapping("/editNumberByDate")
    public Result editNumberByDate(@RequestBody OrderSetting orderSetting){
        try{
            orderSettingService.editNumberByDate(orderSetting);
            return new Result(true,StatusCode.OK,MessageConstant.ORDERSETTING_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,StatusCode.ERROR,MessageConstant.ORDERSETTING_FAIL);
        }
    }
}