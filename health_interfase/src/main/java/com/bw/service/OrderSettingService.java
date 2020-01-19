package com.bw.service;

import java.util.List;
import java.util.Map;

import com.bw.pojo.OrderSetting;

public interface OrderSettingService {

	void add(List<OrderSetting> data);

	List<Map> getOrderSettingByMonth(String date);

	void editNumberByDate(OrderSetting orderSetting);

}
