package com.hengyuan.hicash.domain.query.amount;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 获取还款总额
 * @author Cary.Liu
 * @createDate 2015-04-24
 *
 */
public class RepayTotalBalanceQuery extends AbstractDAO<String> {

	@Override
	public String mapping(ResultSet rs) throws SQLException {

		/* 判断查询结果是否存在 */
		if (rs != null) {
			return StringUtils.valueOf(rs.getObject("totalamt"));
		} 

		return Consts.FINAL_NUMBER_0;
	}

	/**
	 * 获取用户近七天还款总额
	 * 
	 */
	public String getCurrentBalance(String userName) {

		String currentBalanceSql = "SELECT (IFNULL(f1.amt1,0.00) + IFNULL(f2.amt2,0.00)) AS totalamt  FROM " +
				"(SELECT SUM(b.balance) AS amt1,b.USERNAME FROM loan_loan_acc b WHERE b.username = '"+userName+"')  f1 " +
				"LEFT OUTER JOIN " +
				"(SELECT SUM(a.total_amt) AS amt2,a.username FROM loan_repay_plan a " +
				"WHERE a.username = '" + userName +"' AND a.CURRENT_TERM <> 0 AND a.repay_plan_status = 'WTRP' AND DATEDIFF(a.due_date,NOW())<=7) f2 " +
				"ON f1.username = f2.username ";
		
		return ConnManager.singleQuery(currentBalanceSql, this);
	}

	
	
	/**
	 * 获取用户近七天还款总额(去300快)
	 * 
	 */
	public String getCurrentBalanceNew(String userName) {

		String currentBalanceSql = "SELECT IFNULL(f1.amt1,0.00) + IFNULL(f2.amt2,0.00) AS totalamt  FROM "+ 
				                    "(SELECT SUM(b.balance) AS amt1,b.USERNAME FROM loan_loan_acc b  JOIN loan_loan c ON b.loan_id = c.id " +
				                    "LEFT JOIN d_application_pay d ON c.APPLICATION_ID = d.app_application_no "+
			                        " where d.HY_INDUSTRY_CODE <> 'THFQ' and b.username = '"+userName+"')  f1 "+
				                    "LEFT OUTER JOIN "+ 
	                                "(SELECT SUM(a.total_amt) AS amt2,a.username FROM loan_repay_plan a "+	
	                                "JOIN loan_loan c ON a.loan_id = c.id LEFT JOIN d_application_pay d ON c.APPLICATION_ID = d.app_application_no "+
			                        " where d.HY_INDUSTRY_CODE <> ('THFQ') and a.username = '"+userName+"' AND a.CURRENT_TERM <> 0 AND a.repay_plan_status = 'WTRP' " +
			                        "AND DATEDIFF(a.due_date,NOW())<=7 ) f2 ON f1.username = f2.username ";
		
		return ConnManager.singleQuery(currentBalanceSql, this);
	}

}
