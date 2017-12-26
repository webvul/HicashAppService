package com.hengyuan.hicash.domain.query.amount;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.app.LoanLoanAccEntity;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

public class LoanLoanAccQuery extends AbstractDAO<LoanLoanAccEntity> {

	private static Logger logger = Logger.getLogger(LoanLoanAccQuery.class);
	
	@Override
	public LoanLoanAccEntity mapping(ResultSet rs) throws SQLException {

		LoanLoanAccEntity loanloanAcc = new LoanLoanAccEntity();
		
		/* 判断查询结果是否存在 */
		if (rs != null) {
			loanloanAcc.setCurTerm(StringUtils.valueOf(rs.getObject("cur_term")));
			loanloanAcc.setCurPayAmt(StringUtils.valueOf(rs.getObject("current_pay_amt")));
			loanloanAcc.setBalance(StringUtils.valueOf(rs.getObject("balance")));
			loanloanAcc.setRemainPrin(StringUtils.valueOf(rs.getObject("remain_prin")));
			loanloanAcc.setRemainInt(StringUtils.valueOf(rs.getObject("remain_int")));
			loanloanAcc.setRemainFee(StringUtils.valueOf(rs.getObject("remain_fee")));
			
			loanloanAcc.setNextStmtDate(StringUtils.valueOf(rs.getObject("next_stmt_date")));
			loanloanAcc.setAvailAbleDate(StringUtils.valueOf(rs.getObject("AVAILABLE_DATE")));
			loanloanAcc.setCreditDay(StringUtils.valueOf(rs.getObject("creditDay")));
			
			loanloanAcc.setUnpaidFee(StringUtils.valueOf(rs.getObject("fee")));
			loanloanAcc.setUnpaidPrin(StringUtils.valueOf(rs.getObject("unpaid_prin")));
			loanloanAcc.setUnpaidInt(StringUtils.valueOf(rs.getObject("unpaid_int")));
			loanloanAcc.setUnpaidMthFee(StringUtils.valueOf(rs.getObject("unpaid_mth_fee")));
			loanloanAcc.setTotalTerm(StringUtils.valueOf(rs.getObject("total_term")));
			loanloanAcc.setCreditName(StringUtils.valueOf(rs.getObject("creditName")));
			
		} else {
			loanloanAcc= null;
		}

		return loanloanAcc;
	}

	
	
	/**根据获得plan信息
	 * @param userName
	 * @param appPayNo
	 * @return
	 */
	public LoanLoanAccEntity getLoanPlanInfo( String userName,String appPayNo) {

		String availBalanceSql = "SELECT a.total_term,SUM(CASE WHEN c.REPAY_PLAN_STATUS IN('NMRF','EPRF','PPRF') THEN 1 ELSE 0  END) " +
				"AS cur_term,a.current_pay_amt,a.balance,SUM(c.PRINCIPAL-c.PRIN_PAID) AS remain_prin, " +
				"SUM(c.INTEREST-c.INT_PAID) AS remain_int,SUM(c.MTH_FEE-c.MTH_FEE_PAID) AS remain_fee, " +
				"a.next_stmt_date,a.AVAILABLE_DATE,e.creditDay, " +
				"a.unpaid_prin,a.unpaid_int,a.unpaid_mth_fee, " +
				"a.unpaid_pnlt_int+a.unpaid_ot_int+a.unpaid_ot_fee+a.unpaid_lpc AS fee,e.creditName FROM  " +
				"loan_loan_acc a,d_application_pay b,loan_repay_plan c ,loan_loan d,d_input_app e " +
				"WHERE a.LOAN_ID = c.LOAN_ID AND b.app_application_no = d.APPLICATION_ID AND b.app_application_no = e.applicationNo AND d.ID = c.LOAN_ID " +
				"AND b.app_username='" + userName + "' AND b.app_application_no = '" + appPayNo +"' AND c.CURRENT_TERM<>0 " +
				"GROUP BY a.LOAN_ID ";
		
		RecordUtils.writeAction(logger, null, availBalanceSql);
		return ConnManager.singleQuery(availBalanceSql, this);

	}

}
