package com.hengyuan.hicash.domain.service.user;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.event.user.YiDaoMarkEvent;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.user.HyReportRecordEntity;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.FundWaiMaiReportExistReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.FundWaiMaiReportExistResp;

public class FundWaiMaiRepotExistService implements RespService{
	
	private static Logger logger = Logger.getLogger(FundWaiMaiRepotExistService.class);
	
//存在则更新，不存在就添加
	
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {
		  
		FundWaiMaiReportExistResp infoResp = new FundWaiMaiReportExistResp();
		 
		FundWaiMaiReportExistReq infoReq = (FundWaiMaiReportExistReq)parmRequest;
		
		try{

			//保存公积金,外卖
		    HyReportRecordEntity reportEntity=new HyReportRecordEntity();
			reportEntity.setMobile(infoReq.getMobileNo());
			reportEntity.setName(infoReq.getRealName());
			reportEntity.setId_no(infoReq.getIdentityNo());
			reportEntity.setType(infoReq.getType());//
			YiDaoMarkEvent event=new YiDaoMarkEvent();
			event.saveHyReportRecord(reportEntity);
	
		infoResp.setResultCode(ResultCodes.NORMAL);
		
		}catch(Exception e){
			
		 logger.error(infoReq.getIdentityNo()+":操作公积金外卖授权表异常");	
		 
		 infoResp.setResultCode(ResultCodes.CHECK_GJJ_ISORNOT_NULL);
		 
		}
		return infoResp;
	}

}
