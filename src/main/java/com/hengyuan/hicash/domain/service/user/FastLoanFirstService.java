package com.hengyuan.hicash.domain.service.user;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.query.app.TempApplyQuery;
import com.hengyuan.hicash.domain.query.user.CustomerQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.app.TempApplyEntity;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.FastLoanFirstReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.FastLoanFirstMsgResp;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * @author Administrator
 *
 */
public class FastLoanFirstService implements RespService{

	private static Logger logger = Logger.getLogger(StuApp1Service.class);

	private TempApplyQuery tempQuery = new TempApplyQuery();;
	
	private CustomerQuery customerQuery = new CustomerQuery();
	

	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {
		FastLoanFirstReq req = (FastLoanFirstReq) parmRequest;
		FastLoanFirstMsgResp resp = new FastLoanFirstMsgResp();
		
		try{
			String custType = customerQuery.queryCustType(req.getUserName());
			if (Consts.APP_CUSTOMER_TYPE_KHL1.equals(custType)) {
				TempApplyEntity tempEntity = tempQuery.queryTempApplyByNo(req.getUserName(),req.getTempAppNo());

				if(tempEntity != null){
					resp.setTempAppNo(tempEntity.getTempAppNo());
					resp.setFastAmt(tempEntity.getApplyPrice());
					resp.setFastPeriod(tempEntity.getLoanProduct());
					resp.setUserName(tempEntity.getUserName());
					
					resp.setResultCode(ResultCodes.NORMAL);
				}else{
					resp = new FastLoanFirstMsgResp();
					resp.setResultCode(ResultCodes.STU_APP_DETAIL_QUERY_NOT_FOUND);
				}
			}else {
				if (custType != null) {
					resp = new FastLoanFirstMsgResp();
					resp.setResultCode(ResultCodes.STU_APP_DETAIL_CUSTTYPE_NOT_FOUND);
				}else {
					resp = new FastLoanFirstMsgResp();
					resp.setResultCode(ResultCodes.STU_APP_DETAIL_QUERY_NOT_FOUND);
				}
			}
			
		}catch (Exception e) {
			resp = new FastLoanFirstMsgResp();
			resp.setResultCode(ResultCodes.STU_APP_DETAIL_QUERY_EXCEPTION);
			
			/* 记录错误信息 */
			RecordUtils.writeError(logger, req.getUuid(), ResultCodes.STU_APP_DETAIL_QUERY_EXCEPTION, e);
		} finally {
			ConnManager.closeConn();
		}

		
		return resp;
	}
}
