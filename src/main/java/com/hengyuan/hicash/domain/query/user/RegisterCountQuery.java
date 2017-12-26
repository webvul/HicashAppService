package com.hengyuan.hicash.domain.query.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;

/**
 * 注册人数统计 DAO
 * 
 * @author Cary.Liu
 * @create 2014-09-17
 *
 */
public class RegisterCountQuery extends AbstractDAO<Integer> {

	@Override
	public Integer mapping(ResultSet rs) throws SQLException {

		if(rs != null){
			
			return rs.getInt("REGCOUNT");
			
		}
		
		return 0;
	}
	
	/**
	 * 查询注册人数
	 * @param regType 注册类型 0 所有 1 Hicash用户  2 5i5dai用户
	 * @return
	 */
	public Integer queryRegCount(String regType){
		
		StringBuffer querySql = new StringBuffer("SELECT COUNT(*) AS REGCOUNT FROM cust_user WHERE 1 = 1 ");
		
		if(Consts.FINAL_NUMBER_1.equals(regType)){
			querySql.append(" AND  platform_Mark = '1'");
		}else if (Consts.FINAL_NUMBER_2.equals(regType)){
			querySql.append(" AND platform_Mark = '5i5dai_Q'");
		}
		
		
		return ConnManager.singleQuery(querySql.toString(), this);
	}

}
