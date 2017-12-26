package com.hengyuan.hicash.domain.service.user;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.event.user.YiDaoMarkEvent;
import com.hengyuan.hicash.domain.query.user.CustFundShouQuanQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.user.CustFundShouquanEntity;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.DoOrNotFundReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.DoOrNotFundResp;

public class DoOrNotFundService implements RespService{
	
	private static Logger logger = Logger.getLogger(DoOrNotFundService.class);
	
//存在则更新，不存在就添加
	
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {
		  
		DoOrNotFundResp infoResp = new DoOrNotFundResp();
		 
		DoOrNotFundReq infoReq = (DoOrNotFundReq)parmRequest;
		
		try{
			
		CustFundShouQuanQuery recordQuery=new CustFundShouQuanQuery();
		
		YiDaoMarkEvent event=new YiDaoMarkEvent();
		
		if(null!=recordQuery.queryGJJIdNo(infoReq.getIdentityNo(), infoReq.getUuid())){//如果存在此用户的公积金记录,更新
		
			event.updateFundRecord(infoReq.getIsOrNot(),infoReq.getIdentityNo());
			
		}else{
			
			//保存公积金
			CustFundShouquanEntity reportEntity=new CustFundShouquanEntity();
			reportEntity.setShou_quan(infoReq.getIsOrNot());
			reportEntity.setName(infoReq.getRealName());
			reportEntity.setId_no(infoReq.getIdentityNo());
			reportEntity.setType("GJJ");//GJJ
			
			event.saveFundRecord(reportEntity);
		}
		
		infoResp.setResultCode(ResultCodes.NORMAL);
		
		}catch(Exception e){
			
		 logger.error(infoReq.getIdentityNo()+":操作公积金授权表异常");	
		 
		 infoResp.setResultCode(ResultCodes.CHECK_GJJ_RECORD_EXCEPTION);
		 
		}
		return infoResp;
	}

}
