package com.hengyuan.hicash.domain.query.mktApp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.mktApp.LoanLoan;
import com.hengyuan.hicash.entity.param.LoanStatus;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;


/**
 * @author Cary.Liu
 * 
 */
public class LoanloanQuery extends AbstractDAO<LoanLoan> {
	
	private static Logger logger = Logger.getLogger(LoanloanQuery.class);

	@Override
	public LoanLoan mapping(ResultSet rs) throws SQLException {
		
		LoanLoan loanLoan = new LoanLoan();

		if (rs != null) {

			loanLoan.setLoanId(StringUtils.valueOf(rs.getObject("ID")));
			loanLoan.setAccountId(StringUtils.valueOf(rs.getObject("account_id")));
			loanLoan.setLoanId(StringUtils.valueOf(rs.getObject("id")));
			loanLoan.setAmount(StringUtils.valueOf(rs.getObject("amount")));
			loanLoan.setProductId(StringUtils.valueOf(rs.getObject("product_id")));
		}
		return loanLoan;
	}
	
	
	
	/**
	 * 根据appPayNo查询Loan_loan信息
	 */
	public LoanLoan queryLoanLoanByAppId(String applicationNo){
		
		String sql="select id,account_id,amount,product_id from loan_loan where application_id='"+applicationNo+"'";
		
		RecordUtils.writeAction(logger, applicationNo, sql);
		return ConnManager.singleQuery(sql, this);
		
	}
	public LoanLoan queryLoanByAppNo(String applicationNo, LoanStatus status,LoanStatus status1) {
		
	
		String sql = "select id,account_id,amount,product_id from loan_loan where APPLICATION_ID ='"+applicationNo+"' and (status ='"+status+"' or status ='"+status1+"')";
		RecordUtils.writeAction(logger, applicationNo, sql);
		return ConnManager.singleQuery(sql, this);
	
	}
	
	/**
	 * 获取Loan
	 * @param appNo
	 * @return
	 */
	public LoanLoan queryLoanByStatus(String appNo) {
	
		String sql = "SELECT id,account_id,amount,product_id FROM loan_loan WHERE APPLICATION_ID ='"+appNo+"' AND STATUS IN ('"+LoanStatus.LPUB+"','"+LoanStatus.LFIN+"')";

		return ConnManager.singleQuery(sql, this);
	}
	
	
	/**
	 * 根据userName查询用户嗨女神行业还款中的订单合同金额
	 * @param userName
	 * @return
	 */
	public List<LoanLoan> queryLoanAmount(String userName) {
	
		String sql = "SELECT b.id,b.account_id,b.amount,b.product_id FROM	d_application_pay a,loan_loan b WHERE a.app_username = '"+userName+"' AND a.ALLNODE IN ('HKNODE') AND a.hy_industry_code = 'HINS' AND b.APPLICATION_ID = a.app_application_no";
		
		return ConnManager.executeQuery(sql, this);
	}
	
}
