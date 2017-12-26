package com.hengyuan.hicash.service.validate.update;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.UpdateTempAppInfoReq;
import com.hengyuan.hicash.utils.RegexValidate;

/** 
 * @author dong.liu 
 * 2017-3-2 下午2:32:21
 * 类说明 :参数验证
 */
public class UpdateTempAppInfoVal {
	
	private UpdateTempAppInfoReq req;
	
	public UpdateTempAppInfoVal(UpdateTempAppInfoReq req){
		this.req = req;
	}

	public String validate(){
	
		/**
		 * 临时申请件单号
		 */
//		if(RegexValidate.isNull(req.getTempAppNo())){
//			return ResultCodes.STU_UPDATE_APPNO_NOTNULL;
//		}
		
		/**
		 * 申请设备
		 */
		if(RegexValidate.isNull(req.getApplyFrom())){
			return ResultCodes.APPLY_FROM_ISNULL;
		}
		/**
		 *行业
		 */
		if(RegexValidate.isNull(req.getIndustryCode())){
			return ResultCodes.INDUSTRY_CODE_ISNULL;
		}
		/**
		 * 节点
		 */
		if(RegexValidate.isNull(req.getNode())){
			return ResultCodes.NODE_ISNULL;
		}
		/**
		 *  状态
		 */
		if(RegexValidate.isNull(req.getStatus())){
			return ResultCodes.STATUS_ISNULL;
		}
		/**
		 * 身份
		 */
		if(RegexValidate.isNull(req.getCustType())){
			return ResultCodes.MKTAPP_BUSICITY_CUSTTYPE_ISNULL;
		}
		
		return ResultCodes.NORMAL;
	}	
	
}
