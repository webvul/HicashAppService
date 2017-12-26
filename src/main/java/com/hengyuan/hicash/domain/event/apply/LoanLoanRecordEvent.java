package com.hengyuan.hicash.domain.event.apply;


import org.apache.log4j.Logger;

import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.mktApp.LoanLoan;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * @author Cary.Liu
 * @updateDate 2015-01-15
 * 
 */
public class LoanLoanRecordEvent {
	
	private static Logger logger = Logger.getLogger(LoanLoanRecordEvent.class);

	public int createLoanRecord(LoanLoan loan){
		String loanRecordSql = "INSERT INTO loan_loan_record " + "("
				+ "ACCOUNT_ID," + "LOAN_ID," + "RECORD_TYPE," + "AMOUNT"
				+ ")" + "VALUES" + "('" + loan.getAccountId()
				+ "'," + "'" + loan.getLoanId() + "'," + "'RCPA'," + "'"
				+ loan.getAmount() + "'" + ")";
		
		RecordUtils.writeAction(logger, null, loanRecordSql);
		return ConnManager.executeUpdate(loanRecordSql);
		
	}
	
}
