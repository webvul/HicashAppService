package com.hengyuan.hicash.service.validate.auth;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.auth.DriverAuthSaveReq;
import com.hengyuan.hicash.utils.RegexValidate;


/**
 * 司机贷-车主认证保存
 * 
 * @author yangkun
 * @create date 2017-12-24
 * 
 */
public class DriverAuthSaveVal{

	private DriverAuthSaveReq infoReq;

	public DriverAuthSaveVal(DriverAuthSaveReq infoReq){
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
		
		/* 验证临时订单号 */
		if(RegexValidate.isNull(infoReq.getTempApplicationNo())){
			return ResultCodes.TEMPAPPNO_ERROR_ISNULL;
		}
		/* 验证司机账号 */
		if(RegexValidate.isNull(infoReq.getDriverUsername())){
			return ResultCodes.DDSJ_RESERVER_NUMBER_ISNULL;
		}
		/* 验证type */
		if(RegexValidate.isNull(infoReq.getType())){
			return ResultCodes.DRIVER_AUTH_TYPE_NOTNULL;
		}
		if(!"5".equals(infoReq.getType())){
			return ResultCodes.DRIVER_AUTH_TYPE_NOTSUPPER;
		}
		
		return ResultCodes.NORMAL;
	}
	
	public DriverAuthSaveReq getInfoReq() {
		return infoReq;
	}

	public void setInfoReq(DriverAuthSaveReq infoReq) {
		this.infoReq = infoReq;
	}
	
	
}
