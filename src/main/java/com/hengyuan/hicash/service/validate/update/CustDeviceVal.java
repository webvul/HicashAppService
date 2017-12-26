package com.hengyuan.hicash.service.validate.update;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.CustDeviceReq;
import com.hengyuan.hicash.utils.RegexValidate;

/**
 * 
* @author dong.liu 
* 2017-3-23 下午6:52:29
* 类说明:正式申请件validate验证
 */
		
public class CustDeviceVal {

	private CustDeviceReq   valReq;
	
	public CustDeviceVal(CustDeviceReq valReq){
		this.valReq = valReq;
	}

	public String validate(){
		
		/* 申请件单号 */
		if(RegexValidate.isNull(valReq.getApp_no())){
			return ResultCodes.STU_UPDATE_APPNO_NOTNULL;
		}
		
		/* 设备系统 */
		if(RegexValidate.isNull(valReq.getSb_system())){
			return ResultCodes.DDSJ_SB_SYSTEM_IS_NULL;
		}
		/* 设备型号 */
		if(RegexValidate.isNull(valReq.getSb_type())){
			return ResultCodes.DDSJ_SB_TYPE_IS_NULL;
		}
		
		/* 类型:TX/提现,SX/授信 */
		if(RegexValidate.isNull(valReq.getType())){
			return ResultCodes.DDSJ_TYPE_IS_NULL;
		}
		
		/* 唯一标示 */
		if(RegexValidate.isNull(valReq.getUdid())){
			return ResultCodes.DDSJ_USER_UDID_IS_NULL;
		}
		
		/* 用户名 */
		if(RegexValidate.isNull(valReq.getUsername())){
			return ResultCodes.ADDMERAPP_USERNAME_ISNULL;
		}
		
		return ResultCodes.NORMAL;
	}
	
	

	public CustDeviceReq getValReq() {
		return valReq;
	}

	public void setValReq(CustDeviceReq valReq) {
		this.valReq = valReq;
	}

	
	
	
}
