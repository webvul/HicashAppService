package com.hengyuan.hicash.domain.query.app;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

public class HaierApplicationQuery extends AbstractDAO<Integer>{

	@Override
	public Integer mapping(ResultSet rs) throws SQLException {
		if(rs != null){
			return rs.getInt("count");
		}
		
		return 0;
	}
	
	/**
	 *  计算该客户还款中有多少订单

	 * @param userName
	 * @return
	 */
	public Integer queryHKApp(String userName){
		String sql = "SELECT COUNT(pay.app_application_no) as count FROM d_application_pay AS pay WHERE pay.app_username = '"+userName+"' AND pay.ALLNODE = 'HKNODE'";
		RecordUtils.writeAction(logger, null, sql);
		return ConnManager.singleQuery(sql, this);
	}

	
	
	/**
	 *  计算该客户还款中有多少订单（属于海尔）

	 * @param userName
	 * @return
	 */
	public Integer queryHKAppHaier(String userName){
		String sql = "SELECT COUNT(pay.app_application_no) count FROM d_application_pay AS pay LEFT JOIN LOAN_PRODUCT AS product ON pay.app_creditproduct_id = product.ID WHERE pay.app_username = '"+userName+"' AND pay.ALLNODE = 'HKNODE' AND product.INVESTOR_NAME = 'haier_finance'";
		RecordUtils.writeAction(logger, null, sql);
		return ConnManager.singleQuery(sql, this);
	}
	
	
	/**
	 *  查询订单逾期数

	 * @param userName
	 * @return
	 */
	public Integer queryOverdueApp(List<String> appList){
		String appNO = StringUtils.getSelects(appList);
		String sql = "SELECT COUNT(loan.APPLICATION_ID) as count FROM loan_loan AS loan LEFT JOIN loan_loan_acc AS loanacc ON loan.ID = loanacc.LOAN_ID WHERE loan.APPLICATION_ID IN ("+appNO+") AND loanacc.LOAN_STATUS = 'D'";
		RecordUtils.writeAction(logger, null, sql);
		return ConnManager.singleQuery(sql, this);
	}
}
