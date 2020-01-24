package com.bw.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Select;

public interface OrderDao {

	@Select("select m.name member ,s.name setmeal,o.orderDate orderDate,o.orderType orderType from t_order o, t_member m, t_setmeal s where o.member_id=m.id and o.setmeal_id=s.id and o.id=#{id}")
	Map findById4Detail(Integer id);

	@Select("select count(id) from t_order where orderDate = #{value}")
	Integer findOrderCountByDate(String today);

	@Select("select count(id) from t_order where orderDate &gt;= #{value}")
	Integer findOrderCountAfterDate(String thisWeekMonday);

	@Select("select count(id) from t_order where orderDate = #{value} and orderStatus = '已到诊'")
	Integer findVisitsCountByDate(String today);

	@Select(" select count(id) from t_order where orderDate &gt;= #{value} and orderStatus = '已到诊'")
	Integer findVisitsCountAfterDate(String thisWeekMonday);

}
