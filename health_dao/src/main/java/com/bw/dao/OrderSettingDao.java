package com.bw.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.bw.pojo.OrderSetting;

public interface OrderSettingDao {

	@Select("select count(*) from t_ordersetting where orderDate = #{orderDate}")
	long findCountByOrderDate(Date orderDate);

	@Update(" update t_ordersetting set number = #{number} where orderDate = #{orderDate}")
	void editNumberByOrderDate(OrderSetting orderSetting);
	@Insert("insert into t_ordersetting (orderDate,number,reservations) values(#{orderDate},#{number},#{reservations})")
	void add(OrderSetting orderSetting);
	@Select(" select * from t_ordersetting where orderDate between #{dateBegin} and #{dateEnd}")
	List<OrderSetting> getOrderSettingByMonth(Map map);

}
