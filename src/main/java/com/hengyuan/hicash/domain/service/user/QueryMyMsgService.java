package com.hengyuan.hicash.domain.service.user;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.query.user.QueryMyMsgQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.QueryMyMsgReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.QueryMyMsgResp;

public class QueryMyMsgService implements RespService{

	private static Logger logger = Logger.getLogger(QueryMyMsgService.class);
	QueryMyMsgQuery queryMyMsgQuery = new QueryMyMsgQuery();
	
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {
		
		QueryMyMsgReq queryMyMsgReq = (QueryMyMsgReq) parmRequest;
		QueryMyMsgResp queryMyMsgResp = new QueryMyMsgResp();
		try{
			
			queryMyMsgResp  =queryMyMsgQuery.queryMyMsg(queryMyMsgReq);
			
			queryMyMsgResp = queryMyMsgQuery.queryNOReadMyMsg(queryMyMsgReq,queryMyMsgResp);	

			queryMyMsgResp.setResultCode(ResultCodes.NORMAL);
			logger.info("查询数据成功");
		}catch(Exception e){
			queryMyMsgResp = new QueryMyMsgResp();
			queryMyMsgResp.setResultCode(ResultCodes.LOAN_PRO_ERRER);
			logger.info("查询结果异常");
		}
		return queryMyMsgResp;
	}

}
