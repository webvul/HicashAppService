package com.hengyuan.hicash.domain.event.apply;



import org.apache.log4j.Logger;

import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.mktApp.LoanLoan;
import com.hengyuan.hicash.exception.LoanLoanException;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * @author Mary.Luo
 *
 */
public class CreateLoanLoanUpdate {
	
	private static Logger logger = Logger.getLogger(CreateLoanLoanUpdate.class);
	
	/**创建还款计划
	 * @param loanLoan
	 * @return
	 */
	public int createLoan(LoanLoan loanLoan) {
		String createSql = "INSERT INTO loan_loan " + " (" + " AMOUNT,"
				+ " USERNAME," + " INTEREST_RATE," + " ACCOUNT_ID," + " TITLE,"
				+ " MONTHS," + " STATUS ,"
				+ " PRODUCT_ID," + " PURPOSE," + " DESCP," + " FINANCE_DESC,"
				+ " IMG_PATH," + " REVOKE_DESC," + " NOTICE_PROCESS_FLAG,"
				+ " PERCENT," + " COMPLETED_INVEST_AMT," + " TOTAL_INVEST_SUM,"
				+ " APPLICATION_ID" + ",CUST_INTERESTRATE,CREATE_TIME,COUPON )" + " VALUES" + " (" + " '"
				+ loanLoan.getAmount()
				+ "',"
				+ " '"
				+ loanLoan.getUsername()
				+ "',"
				+ " '"
				+ loanLoan.getInterestRate()
				+ "',"
				+ " '"
				+ loanLoan.getAccountId()
				+ "',"
				+ " '"
				+ loanLoan.getTitle()
				+ "',"
				+ " '"
				+ loanLoan.getMonths()
				+ "',"
				+ " '"
				+ loanLoan.getStatus()
				+ "',"
				+ " '"
				+ loanLoan.getProductId()
				+ "',"
				+ " '"
				+ loanLoan.getPurpose()
				+ "',"
				+ " '',"
				+ " '',"
				+ " '"
				+ loanLoan.getImgPath()
				+ "',"
				+ " '',"
				+ " "
				+ loanLoan.getNoticeProcessFlag()
				+ ","
				+ " '"
				+ loanLoan.getPercent()
				+ "',"
				+ " '"
				+ loanLoan.getCompletedInvestAmt()
				+ "',"
				+ " '"
				+ loanLoan.getTotalInvertsSum()
				+ "',"
				+ " '"
				+ loanLoan.getApplicationId() + "','"+loanLoan.getCustRate()+"',NOW()"
				+ " ,'"
				+ loanLoan.getCoupon() + "')";
		
		RecordUtils.writeAction(logger, null, createSql);
		return ConnManager.executeUpdate(createSql);
	}
	public void updateLoan(LoanLoan loan)throws LoanLoanException{
		String sql="UPDATE loan_loan set PRODUCT_ID='"+loan.getProductId()+"' , MONTHS='"+loan.getMonths()+"' , amount='"+loan.getAmount()+"' where id='"+loan.getLoanId()+"'";
		RecordUtils.writeAction(logger, null, sql);
		if (ConnManager.executeUpdate(sql) <= 0) {
			throw new LoanLoanException();
		}
	}

}
