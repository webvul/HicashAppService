package com.hengyuan.hicash.domain.query.param;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.utils.RecordUtils;

public class SingleIntQuery extends AbstractDAO<Integer> {

	private static Logger logger = Logger.getLogger(SingleIntQuery.class);
	
	@Override
	public Integer mapping(ResultSet rs) throws SQLException {

		if(rs != null){
			return rs.getInt("singleCol");
		}
		
		return Integer.parseInt(Consts.FINAL_NUMBER_0);
	}

	/**
	 * 获取申请件数量
	 * @param userName
	 * @return
	 */
	public Integer queryAppCountByNode(String userName){
		
		String querySql = "SELECT COUNT(*) AS singleCol FROM d_application_pay WHERE app_username = '"+ userName +"' AND ALLNODE NOT IN ('GBNODE','HKNODE')";
		
		RecordUtils.writeAction(logger, null, querySql);
		
		return ConnManager.singleQuery(querySql, this);
	}
	
	/**
	 * 获取发送短信验证码验证
	 * @param userName
	 * @return
	 */
	public Integer querySendSmsVerifyCount(String userName,String verifyCode){
		
		String querySql = "SELECT COUNT(*)  AS singleCol FROM SAFE_VERIFYCODE_RECORD WHERE 1 = 1 AND USERNAME = '"+userName+"' AND VERIFY_CODE = '"+verifyCode+"'";
		
		RecordUtils.writeAction(logger, null, querySql);
		
		return ConnManager.singleQuery(querySql, this);
	}
	
	/**
	 * 获取用户Id
	 * @param userName
	 * @return
	 */
	public Integer queryUserId(String userName){
		
		String querySql = "SELECT ID AS singleCol FROM cust_user WHERE USERNAME = '"+userName+"'";
		
		RecordUtils.writeAction(logger, null, querySql);
		
		return ConnManager.singleQuery(querySql, this);
	}
	
}
