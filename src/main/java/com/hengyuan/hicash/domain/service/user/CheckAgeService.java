package com.hengyuan.hicash.domain.service.user;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.query.user.CustomerQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.user.CustomerEntity;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.CheckAgeReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.CheckAgeResp;
import com.hengyuan.hicash.utils.HRIdentityUtil;

public class CheckAgeService implements RespService {

	private static Logger logger = Logger.getLogger(CheckAgeService.class);



	private CustomerQuery customerQuery = new CustomerQuery();

	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		CheckAgeReq appReq = (CheckAgeReq) parmRequest;
		CheckAgeResp appResp = new CheckAgeResp();

		String resultCode = ResultCodes.NORMAL;

		try {
			CustomerEntity customer=customerQuery.queryCustByUser(appReq.getUsername());
			if(customer!=null){
				if("KHL1".equals(customer.getCustType())){
					appResp.setIs_xuexin("1");
				}else{
					int age=HRIdentityUtil.getAppUseAge(customer.getIdentityNo());
					if(age<23){
						appResp.setIs_xuexin("1");
					}else{
						appResp.setIs_xuexin("0");
					}
				}
				
			}else{
				resultCode=ResultCodes.REGISTER_NOT_EXIST;
			}
		} catch (Exception e) {
			logger.error("查询异常", e);
			resultCode = ResultCodes.REGISTER_QUERY_EXCEPTION;
		}

		appResp.setResultCode(resultCode);
		return appResp;
	}

	
	public static void main(String[] args) {
		
		int age=HRIdentityUtil.getAppUseAge("360121199207100057");
			System.out.println(age);

	}

}
