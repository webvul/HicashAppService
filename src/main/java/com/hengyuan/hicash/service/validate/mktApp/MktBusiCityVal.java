package com.hengyuan.hicash.service.validate.mktApp;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.mktApp.MktBusiCityReq;

/**
 * 二次营销-业务开放城市 请求参数验证
 * 
 * @author Cary.Liu
 * @create 2015-03-13
 *
 */
public class MktBusiCityVal {

	private MktBusiCityReq mktReq;

	public MktBusiCityVal(MktBusiCityReq mktReq) {
		this.mktReq = mktReq;
	}
	
	public String validate(){
		
//		if(RegexValidate.isNull(mktReq.getCustType())){
//			return ResultCodes.MKTAPP_BUSICITY_CUSTTYPE_ISNULL;
//		}
		
//		if(RegexValidate.isCustType(mktReq.getCustType())){
//			return ResultCodes.MKTAPP_BUSICITY_CUSTTYPE_ILLEGAL;
//		}
		
		return ResultCodes.NORMAL;
	}
			

	public MktBusiCityReq getMktReq() {
		return mktReq;
	}

	public void setMktReq(MktBusiCityReq mktReq) {
		this.mktReq = mktReq;
	}

}
