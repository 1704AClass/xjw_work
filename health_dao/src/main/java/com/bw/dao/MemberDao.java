package com.bw.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.bw.pojo.Member;

public interface MemberDao {

	@Select("select * from t_member where phoneNumber = #{phoneNumber}")
	Member findByTelephone(String telephone);
	
	@Insert("insert into t_member (fileNumber,name,sex,idCard,phoneNumber,regTime,password,email,birthday,remark) values (#{fileNumber},#{name},#{sex},#{idCard},#{honeNumber},#{regTime},#{password},#{email},#{birthday},#{remark})")
	void add(Member member);

	@Select("select count(id) from t_member where regTime &lt;= #{value}")
	Integer findMemberCountBeforeDate(String date);

	@Select("select count(id) from t_member where regTime &lt;= #{value}")
	Integer findMemberCountByDate(String today);

	@Select(" select count(id) from t_member")
	Integer findMemberTotalCount();

	@Select("select count(id) from t_member where regTime &gt;= #{value}")
	Integer findMemberCountAfterDate(String thisWeekMonday);

}
