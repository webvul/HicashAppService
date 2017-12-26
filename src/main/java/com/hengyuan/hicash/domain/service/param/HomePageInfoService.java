package com.hengyuan.hicash.domain.service.param;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.query.param.BannerQuery;
import com.hengyuan.hicash.domain.query.param.ProShowInfoQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.domain.service.amount.AppScheduleService;
import com.hengyuan.hicash.entity.app.AccountPay;
import com.hengyuan.hicash.entity.param.BannerEntity;
import com.hengyuan.hicash.entity.param.ProShowEntity;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.amount.AppScheduleReq;
import com.hengyuan.hicash.parameters.request.param.HomePageInfoReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.amount.AppScheduleResp;
import com.hengyuan.hicash.parameters.response.param.HomePageInfoResp;

/**
 * app首页展示(图片和热卖商品) service
 * @author Cary.Liu
 * @createDate 2015-06-05
 *
 */
public class HomePageInfoService implements RespService {

	private String resultCode = "";
	
	/** 还款提醒数 */
	private int hkNumber = 0;

	/** 进度查询数 */
	private int jdNumber = 0;

	/** 嗨秒贷数 */
	private int hmdNumber = 0;
	
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		HomePageInfoReq infoReq = (HomePageInfoReq)parmRequest;
		HomePageInfoResp infoResp = new HomePageInfoResp();
		
		try {
			
			/* 获取首页展示图 */
			infoResp.setBannerList(queryBannerInfo(infoReq));
			
			infoResp.setProList(queryHotProduct(infoReq));
			
			if(!StringUtils.isEmpty(infoReq.getUserName())){
				getApplyNumber(infoReq.getUserName());
			}
			
			infoResp.setHkNumber(hkNumber);
			infoResp.setJdNumber(jdNumber);
			infoResp.setHmdNumber(hmdNumber);
			resultCode = ResultCodes.NORMAL;
					
		} catch (Exception e) {
			resultCode = ResultCodes.HOMEPAGE_EXCEPTION;
		} finally {
			ConnManager.closeConn();
		}
		
		infoResp.setResultCode(resultCode);
		return infoResp;
	}
	
	private List<BannerEntity> queryBannerInfo(HomePageInfoReq infoReq){
		
		BannerQuery query = new BannerQuery();
		return query.queryBannerInfoByCity(infoReq.getCityCode());
	}
	
	private List<ProShowEntity> queryHotProduct(HomePageInfoReq infoReq){
		
		ProShowInfoQuery query = new ProShowInfoQuery();
		return query.queryHotProList(infoReq.getCityCode());
	}
	
	public void getApplyNumber(String userName){
		
		AppScheduleReq appReq = new AppScheduleReq();
		appReq.setUserName(userName);
		AppScheduleService appService = new AppScheduleService();
		AppScheduleResp appResp = (AppScheduleResp)appService.responseValue(appReq);
		List<AccountPay> payList = appResp.getAppList();
		List<AccountPay> repayList = appResp.getRepayPlans();
		
		if(payList != null && payList.size() > 0 ){
			jdNumber = payList.size();
		}
		if(repayList != null && repayList.size() > 0 ){
			hkNumber = repayList.size();
		}
		
	}

}
