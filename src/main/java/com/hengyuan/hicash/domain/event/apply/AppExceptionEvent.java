package com.hengyuan.hicash.domain.event.apply;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.app.AppExceptionEntity;
import com.hengyuan.hicash.utils.RecordUtils;



/**
 * 记录金额计算错误的申请件
 * @author Cary.Liu
 * @createDate 2015-07-03
 *
 */
public class AppExceptionEvent {
	
	private static Logger logger = Logger.getLogger(AppExceptionEvent.class);

	public void recordExceptionApp(AppExceptionEntity entity){
		
		
		String updateSql = "INSERT INTO app_application_exception "
				+ "(USERNAME,APPLICATION_ID,LOAN_PRODUCT_APP,LOAN_PRODUCT_LOAN,LOAN_AMOUNT_APP,LOAN_AMOUNT_LOAN) "
				+ "VALUES('"+ entity.getUserName() +"','"+ entity.getAppNo() +"','"+ entity.getLoanProductApp() +"','"+ entity.getLoanProductLoan() +"',"+ entity.getLoanAmountApp() +","+ entity.getLoanAmountLoan() +")";
		
		RecordUtils.writeAction(logger, null, updateSql);
		
		ConnManager.executeUpdate(updateSql);
	}
	
	/*public static void main(String[] args) {
		
		AppExceptionEvent event = new AppExceptionEvent();
		AppExceptionEntity entity = new AppExceptionEntity();
		entity.setUserName("liuxinyu");
		entity.setUserName("liuxinyu");
		entity.setAppNo("7100100109290");
		entity.setLoanProductApp("60");
		entity.setLoanProductLoan("60");
		entity.setLoanAmountApp(new BigDecimal(1111));
		entity.setLoanAmountLoan(new BigDecimal(1112));
		event.recordExceptionApp(entity);
		
	}*/
	
}
