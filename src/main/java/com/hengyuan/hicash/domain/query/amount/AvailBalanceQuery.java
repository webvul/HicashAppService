package com.hengyuan.hicash.domain.query.amount;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 获取用户可用余额
 * @author Cary.Liu
 * @createDate 2015-04-24
 *
 */
public class AvailBalanceQuery extends AbstractDAO<String> {

	@Override
	public String mapping(ResultSet rs) throws SQLException {

		if (rs != null && rs.getObject("BALANCE") != "") {
			String balance = StringUtils.valueOf(rs.getObject("BALANCE"));
			if(balance != null && balance != ""){
				return balance;
			}
			return Consts.FINAL_NUMBER_0;
		} 

		return Consts.FINAL_NUMBER_0;
	}

	/**
	 * 获取用户可用余额
	 */
	public String getAvailBalance(String userName) {

		String querySql = "SELECT f1.BALANCE + IFNULL(SUM(f2.AMOUNT),0) AS BALANCE "
				+ "FROM "
				+ "(SELECT a.ID,a.BALANCE,a.BATCH_TIME FROM acct_account a "
				+ "WHERE a.USERNAME = '" + userName + "') AS f1,"
				+ "acct_transaction AS f2 "
				+ "WHERE f1.ID = f2.ACCOUNT_ID AND f2.CREATE_TIME>f1.BATCH_TIME";
		
		return ConnManager.singleQuery(querySql, this);
	}

	
	/**
	 * 查询用户已还款比例
	 * @param userName
	 * @param applicationNO
	 * @return
	 */
	public String getHKAmount(String userName,String applicationNO) {

		String querySql = "SELECT IFNULL(SUM(TOTAL_AMT_PAID)/SUM(TOTAL_AMT),0) BALANCE FROM loan_repay_plan c JOIN loan_loan a ON c.loan_id = a.id LEFT JOIN d_application_pay b ON a.APPLICATION_ID = b.app_application_no WHERE b.app_application_no = '"+applicationNO+"' AND b.app_username='"+userName+"' AND (b.ALLNODE='HKNODE' OR b.STATUS = 'STATUS27') AND current_term != 0 ";
		
		return ConnManager.singleQuery(querySql, this);
	}

	
	
	public static void main(String[] args) {
		System.out.println(new AvailBalanceQuery().getAvailBalance("liuxinyu"));
	}
	
}
