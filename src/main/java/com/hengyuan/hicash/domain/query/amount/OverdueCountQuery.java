package com.hengyuan.hicash.domain.query.amount;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 获取逾期数
 * @author Cary.Liu
 * @createDate 2015-04-24
 *
 */
public class OverdueCountQuery extends AbstractDAO<String> {

	@Override
	public String mapping(ResultSet rs) throws SQLException {

		if(rs != null){
			return StringUtils.valueOf(rs.getObject("overCount"));
		}
		
		return Consts.FINAL_NUMBER_0;
	}

	/**
	 * 获取逾期数
	 * @param userName
	 * @return
	 */
	public String getOverCountByUserName(String userName){
		
		String sql="SELECT COUNT(1) as overCount FROM loan_loan_acc WHERE username='"+userName+"' AND loan_status ='D'";
		
		return ConnManager.singleQuery(sql, this);
	}
	
}
