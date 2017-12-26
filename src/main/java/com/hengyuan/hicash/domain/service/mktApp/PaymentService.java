package com.hengyuan.hicash.domain.service.mktApp;

import java.util.List;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.query.mktApp.PaymenyQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.mktApp.PaymentReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.mktApp.PayAmountResp;

/**
 * 获取首付比例业务处理
 * @author Cary.Liu
 * @create 2015-03-12
 *
 */
public class PaymentService implements RespService {
	
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {
		
		PaymentReq paymentReq = (PaymentReq)parmRequest;
		PayAmountResp payAmountResp = new PayAmountResp();
		
		try {
			
			List<String> list = queryPayRate(paymentReq.getCityCode(),paymentReq.getCustType());
	        
			if(list.isEmpty()){
				list = queryPayRate("000000", paymentReq.getCustType());
			}
			
			payAmountResp.setPatRate(list);
			payAmountResp.setResultCode(ResultCodes.NORMAL);
			
		} catch (Exception e) {
			payAmountResp.setResultCode(ResultCodes.PAY_RATE_ERROR);
		} finally {
			ConnManager.closeConn();
		}
		
		return payAmountResp;
	}

	public List<String> queryPayRate(String cityCode,String custType){
		PaymenyQuery paymenyQuery = new PaymenyQuery();
		
		return paymenyQuery.queryPayRate(cityCode, custType);
	}
	
}
