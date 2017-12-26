package com.hengyuan.hicash.domain.service.param;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.query.param.SameProductQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.param.SameProductReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.param.SameProductResp;

/**
 * 同款商品查询  业务处理
 * @author Cary.Liu
 * @createDate 2015-04-23
 *
 */
public class SameProductService implements RespService {

	private String resultCode = "";
	
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		SameProductReq sameReq = (SameProductReq)parmRequest;
		SameProductResp sameResp = new SameProductResp();
		
		try {
			
			SameProductResp respByQuery = querySamePro(sameReq);
			if(respByQuery != null){
				resultCode = ResultCodes.NORMAL;
				sameResp = respByQuery;
			}else{
				resultCode = ResultCodes.NO_RESULT;
			}
			
		} catch (Exception e) {
			resultCode = ResultCodes.SAMEPRODUCT_EXCEPTION;
		} finally {
			ConnManager.closeConn();
		}
		
		sameResp.setResultCode(resultCode);
		return sameResp;
	}
	
	public SameProductResp querySamePro(SameProductReq sameReq){
		
		SameProductQuery sameQuery = new SameProductQuery();
		SameProductResp resp = sameQuery.queryProShowList(sameReq);
		return resp;
	}

}
