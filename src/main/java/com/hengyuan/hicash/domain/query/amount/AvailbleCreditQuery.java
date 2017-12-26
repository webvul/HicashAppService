package com.hengyuan.hicash.domain.query.amount;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 获取账户可用额度查询
 * 
 * @author Cary.Liu
 * @createDate 2015-03-25
 */
public class AvailbleCreditQuery extends AbstractDAO<String> {

	private static Logger logger = Logger.getLogger(AvailbleCreditQuery.class);

	@Override
	public String mapping(ResultSet rs) throws SQLException {

		/* 判断查询结果是否存在 */
		if (rs != null) {

			return StringUtils.valueOf(rs.getObject(1));

		}
		return null;
	}

	/**
	 * 获取账户可用额度
	 * 
	 * @param availCreditReq
	 * @return
	 * @author Andy.Niu
	 * @create 2014-07-30
	 */
	public String getAvailCredit(String userName, String uuid) {

		String credit_sql = "SELECT CASE WHEN (a.TRUST_AMOUNT-a.BLOCK_AMOUNT-IFNULL(d.principal,0.00))<0.00 THEN 0.00 "
				+ "ELSE (a.TRUST_AMOUNT-a.BLOCK_AMOUNT-IFNULL(d.principal,0.00)) "
				+ "END AS user_amount "
				+ "FROM (SELECT e.TRUST_AMOUNT,e.BLOCK_AMOUNT,e.USER_NAME FROM cust_limit e WHERE e.USER_NAME = '"
				+ userName
				+ "') AS a "
				+ "LEFT OUTER JOIN "
				+ "(SELECT IFNULL(SUM(c.PRINCIPAL),0.00) AS principal,b.username FROM loan_loan_acc b,loan_repay_plan c ,loan_loan p "
				+ "WHERE b.username = '"
				+ userName
				+ "' AND b.LOAN_ID = c.LOAN_ID AND b.LOAN_ID = p.ID AND c.REPAY_PLAN_STATUS  IN ('WTRP','REXP') AND p.STATUS IN('LEND') GROUP BY b.username) AS d "
				+ "ON a.USER_NAME = d.username";

		RecordUtils.writeAction(logger, uuid, credit_sql);

		return ConnManager.singleQuery(credit_sql, this);

	}

	/**
	 * 获取已知流水号已还本金
	 * 
	 * @return
	 * @author yusong
	 * @create 2017-02-23
	 */
	public String getPrinPaid(String useAppNo, String uuid) {
		String paid_sql = "SELECT SUM(plan.PRIN_PAID) FROM loan_loan AS loan "
				+ "LEFT JOIN loan_repay_plan AS plan "
				+ "ON loan.id = plan.loan_id " + "WHERE APPLICATION_ID IN ("
				+ useAppNo + ") ";
		RecordUtils.writeAction(logger, uuid, paid_sql);
		return ConnManager.singleQuery(paid_sql, this);
	}

	/**
	 * 
	 * @param appNo
	 * @param uuid
	 * @return
	 */
	public String getNotPayAmt(String appNo, String uuid) {
		String sql = "SELECT SUM(plan.TOTAL_AMT - plan.TOTAL_AMT_PAID) FROM loan_loan AS loan "
				+ "LEFT JOIN loan_repay_plan AS plan "
				+ "ON loan.id = plan.loan_id " + "WHERE APPLICATION_ID =  "
				+ "'" + appNo + "'";
		RecordUtils.writeAction(logger, uuid, sql);
		return ConnManager.singleQuery(sql, this);

	}

}
