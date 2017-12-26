package com.hengyuan.hicash.domain.service.user;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.query.app.ExtractCashCountQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.ExtractCashCountReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.ExtractCashCountResp;

/**
 * 提现笔数 查询服务
 * 
 * @author Cary.Liu
 * @create 2014-09-17
 *
 */
public class ExtractCashCountService implements RespService {

	private String resultCode = "";
	
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		ExtractCashCountReq cashCountReq = (ExtractCashCountReq)parmRequest;
		ExtractCashCountResp cashCountResp = new ExtractCashCountResp();
		
		try {
			
			cashCountResp.setSuccCount(querySuccCount(cashCountReq.getTrimNum()));
			resultCode = ResultCodes.NORMAL;
		} catch (Exception e) {
			resultCode = ResultCodes.EXTRACTCASH_COUNT_EXCEPTION;
		}
		
		cashCountResp.setResultCode(resultCode);
		return cashCountResp;
	}
	
	/**
	 * 
	 * @param trimNum 调整系数
	 * @return
	 */
	public String querySuccCount(String trimNum){
		ExtractCashCountQuery countQuery = new ExtractCashCountQuery();
		Integer queryCount = countQuery.queryExtractCount();
		
		if(trimNum != null){
			return (int)(Math.ceil((queryCount * Float.parseFloat(trimNum)) + 13968)) + "";
		}else{
			return (queryCount + 13968) + "";
		}
		
	}

}
