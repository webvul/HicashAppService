package com.hengyuan.hicash.service.validate.query;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.StuAppCityReq;
import com.hengyuan.hicash.utils.RegexValidate;

/**
 * hicash手机端学生提现申请查询城市验证
 * 
 * @author lihua.Ren
 * @create date 2015-05-27
 *
 */
public class StuAppCityVal {

private StuAppCityReq queryMsgReq;
	
	public StuAppCityVal(StuAppCityReq queryMsgReq){
		
		this.queryMsgReq = queryMsgReq;
		
	}

	public String validate(){
		
		if (RegexValidate.isNull(queryMsgReq.getUuid())) {
			return ResultCodes.UUIDNULL;
		}
		
		if (!RegexValidate.isUuid(queryMsgReq.getUuid())) {
			return ResultCodes.UUIDILLEGAL;
		}

		if (RegexValidate.isNull(queryMsgReq.getProvinceCode())) {
			return ResultCodes.STU_APP_AREA_CITY_NOTNULL;
		}
		if(!RegexValidate.isSelectToAddress(queryMsgReq.getProvinceCode())){
			return ResultCodes.STU_APP_AREA_CITY_FALSE;
		}
		return ResultCodes.NORMAL;
	}
	
	
	public StuAppCityReq getQueryMsgReq() {
		return queryMsgReq;
	}
	

}
