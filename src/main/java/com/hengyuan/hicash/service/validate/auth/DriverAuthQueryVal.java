package com.hengyuan.hicash.service.validate.auth;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.auth.DriverAuthQueryReq;
import com.hengyuan.hicash.utils.RegexValidate;


/**
 * 司机贷-车主认证查询
 * 
 * @author yangkun
 * @create date 2017-12-24
 * 
 */
public class DriverAuthQueryVal{

	private DriverAuthQueryReq infoReq;

	public DriverAuthQueryVal(DriverAuthQueryReq infoReq){
		this.infoReq = infoReq;
	}

	public String validate(){
		
		/* 验证uuid */
		if(RegexValidate.isNull(infoReq.getUuid())){
			return ResultCodes.UUIDNULL;
		}
		
		if(!RegexValidate.isUuid(infoReq.getUuid())){
			return ResultCodes.UUIDILLEGAL;
		}
		
		/* 验证行业代码 */
		if(RegexValidate.isNull(infoReq.getIndustryCode())){
			return ResultCodes.INDUSTRY_CODE_ISNULL;
		}
		/* 验证用户名 */
		if(RegexValidate.isNull(infoReq.getUsername())){
			return ResultCodes.USER_ERROR_ISNULL;
		}
		
		if(!RegexValidate.isUsername(infoReq.getUsername())){
			return ResultCodes.USER_NAME_ERROR_CANTCHAR;
		}
		return ResultCodes.NORMAL;
	}
	
	public DriverAuthQueryReq getInfoReq() {
		return infoReq;
	}

	public void setInfoReq(DriverAuthQueryReq infoReq) {
		this.infoReq = infoReq;
	}
	
	
}
