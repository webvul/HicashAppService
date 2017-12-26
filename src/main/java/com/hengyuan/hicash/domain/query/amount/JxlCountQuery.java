package com.hengyuan.hicash.domain.query.amount;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.utils.StringUtils;

public class JxlCountQuery extends AbstractDAO<String> {
	
	@Override
	public String mapping(ResultSet rs) throws SQLException {

		if(rs != null){
			return StringUtils.valueOf(rs.getObject("jxlCount"));
		}
		
		return Consts.FINAL_NUMBER_0;
	}


	public String queryJxlCount(String custName,String custMobileNo,String identityNo){
		
		String sql =" SELECT COUNT(reportID) AS jxlCount FROM jxl_person WHERE id_card_num = '"+identityNo+"' " +
				" AND phoneNumber = '"+custMobileNo+"' " +   
				" AND real_name = '"+custName+"' " + 
				" AND TIMESTAMPDIFF(DAY,reportdatetime,NOW() ) <= 7 ";
		
		return ConnManager.singleQuery(sql, this);
	}

	//查询滴滴报告
	public String queryddCount(String custName,String custMobileNo,String identityNo){
		
		String sql =" SELECT COUNT(status) AS jxlCount FROM hy_appdrops WHERE user_identityNo= '"+identityNo+"' " +
				" AND user_name = '"+custName+"' " + 
				" AND TIMESTAMPDIFF(DAY,CREATE_TIME,NOW() ) <= 7 order by CREATE_TIME desc limit 1 ";
		
		return ConnManager.singleQuery(sql, this);
	}
	
	//查询外卖报告
	public String querywaimaiCount(String custName,String custMobileNo,String identityNo){
			
			String sql =" SELECT COUNT(status) AS jxlCount FROM hy_report_record WHERE id_no = '"+identityNo+"' " +
					" AND mobile = '"+custMobileNo+"' " +   
					" AND name = '"+custName+"' " + 
					" AND type = 1 " + 
					" AND TIMESTAMPDIFF(DAY,create_time,NOW() ) <= 7 ";
			
			return ConnManager.singleQuery(sql, this);
		}
	
	//查询公积金报告
	public String querygjjCount(String custName,String custMobileNo,String identityNo){
		
		String sql =" SELECT COUNT(status) AS jxlCount FROM hy_report_record WHERE id_no = '"+identityNo+"' " +
				" AND mobile = '"+custMobileNo+"' " +   
				" AND name = '"+custName+"' " + 
				" AND type = 2 " + 
				" AND TIMESTAMPDIFF(DAY,create_time,NOW() ) <= 7 ";
		
		
		return ConnManager.singleQuery(sql, this);
	}
}
