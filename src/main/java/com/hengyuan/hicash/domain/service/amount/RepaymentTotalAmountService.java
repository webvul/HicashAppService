package com.hengyuan.hicash.domain.service.amount;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.query.amount.RepayTotalBalanceQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.amount.RepaymentTotalAmountReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.amount.RepaymentTotalAmountResp;

/**
 * 还款总额（近七天） service
 * @author Cary.Liu
 * @createDate 2015-10-15
 *
 */
public class RepaymentTotalAmountService implements RespService {

	private String resultCode = "";
	
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		RepaymentTotalAmountReq totalReq = (RepaymentTotalAmountReq)parmRequest;
		RepaymentTotalAmountResp totalResp = new RepaymentTotalAmountResp();
		
		try {
			
			totalResp.setLateTotal(getRepayTotal(totalReq.getUserName()));
			resultCode = ResultCodes.NORMAL;
			
		} catch (Exception e) {
			resultCode = ResultCodes.REPAYMENTAMOUNT_EXCEPTION;
			e.printStackTrace();
		} finally {
			ConnManager.closeConn();
		}
		
		totalResp.setResultCode(resultCode);
		return totalResp;
	}
	
	/**
	 * 近七天还款总额
	 * @param userName
	 * @return
	 */
	public String getRepayTotal(String userName){
		
		RepayTotalBalanceQuery totalQuery = new RepayTotalBalanceQuery();
		return totalQuery.getCurrentBalance(userName);
	}

}
