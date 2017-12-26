package com.hengyuan.hicash.domain.service.user;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.query.user.QueryAuthQuery;
import com.hengyuan.hicash.domain.query.user.SaveReserveNum;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.user.QueryAuthEntity;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.QueryAuthReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.QueryAuthResp;
import com.hengyuan.hicash.utils.StringUtils;


public class QueryAuthService implements RespService{

	private static Logger logger = Logger.getLogger(QueryAuthService.class);
	private QueryAuthQuery queryAuthQuery = new QueryAuthQuery();
	private SaveReserveNum saveReserveNum = new SaveReserveNum();
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {
		QueryAuthReq queryAuthReq = (QueryAuthReq) parmRequest;
		QueryAuthResp queryAuthResp = new QueryAuthResp();
		
		try{
			//查询表里是否有该用户
			QueryAuthEntity query1= saveReserveNum.queryReserveNumber(queryAuthReq.getUserName());
			if(query1==null){
				saveReserveNum.saveReserveNumber(queryAuthReq);
			}else if(query1!=null&&!StringUtils.isEmpty(queryAuthReq.getReserveNumber())
					&&!StringUtils.isEmpty(queryAuthReq.getReservePassword())){
				saveReserveNum.updateReserveNumber(queryAuthReq);
			}
			logger.info("保存数据成功");
			
			QueryAuthEntity query2= saveReserveNum.queryReserveNumber(queryAuthReq.getUserName());
			//查询额度信息表
			QueryAuthEntity query= queryAuthQuery.queryData(queryAuthReq.getUserName());
			logger.info("查询数据成功");
			if(query2!= null&&!"".equals(query2.getReserveNumber())){
				queryAuthResp.setReserveNumber(query2.getReserveNumber());
				queryAuthResp.setReservePassword(query2.getReservePassword());
				
			}else{
				queryAuthResp.setReserveNumber("");
				queryAuthResp.setReservePassword("");
			}
			//第一次授信
			if(query == null){
				if(query2!= null&&!"".equals(query2.getReserveNumber())){
					queryAuthResp.setMobile(query2.getReserveNumber());
					
				}else{
					queryAuthResp.setMobile("");
				}	
				queryAuthResp.setIsEdit("Y");
				queryAuthResp.setCount("0");
				queryAuthResp.setIsRequired("1");
				queryAuthResp.setMaxAmount("最高可享额度为¥50000");
				queryAuthResp.setResultCode(ResultCodes.NORMAL);
			}else{
				if(query.getMobile()!=null&&!"".equals(query.getMobile())){
					queryAuthResp.setMobile(query.getMobile());
					queryAuthResp.setIsEdit("N");
				}else{
					if(query2!= null&&!"".equals(query2.getReserveNumber())){
						queryAuthResp.setMobile(query2.getReserveNumber());
					}else{
						queryAuthResp.setMobile("");
					}
					queryAuthResp.setIsEdit("Y");
				}
				queryAuthResp.setCount(query.getCount());
				queryAuthResp.setIsRequired("0");
				queryAuthResp.setMaxAmount("最高可享额度为¥50000");
				queryAuthResp.setResultCode(ResultCodes.NORMAL);
			}
			
			
			
		}catch(Exception e){
			logger.error("查询结果异常",e);
			queryAuthResp = new QueryAuthResp();
			queryAuthResp.setResultCode(ResultCodes.LOAN_PRO_ERRER);
		}
		
		return queryAuthResp;
	}

}
