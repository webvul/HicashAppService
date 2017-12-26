package com.hengyuan.hicash.service.validate.order;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.order.TempOrderIsExistReq;
import com.hengyuan.hicash.utils.RegexValidate;


/**
 * 判断临时订单是否存在  请求参数验证
 * 
 * @author yangkun
 * @create date 2017-12-21
 * 
 */
public class TempOrderIsExistVal{

	private TempOrderIsExistReq infoReq;

	public TempOrderIsExistVal(TempOrderIsExistReq infoReq){
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
		
		/* 验证用户名 */
		if(RegexValidate.isNull(infoReq.getUsername())){
			return ResultCodes.USER_ERROR_ISNULL;
		}
		
		if(!RegexValidate.isUsername(infoReq.getUsername())){
			return ResultCodes.USER_NAME_ERROR_CANTCHAR;
		}
		
			
		return ResultCodes.NORMAL;
	}
	
	public TempOrderIsExistReq getInfoReq() {
		return infoReq;
	}

	public void setInfoReq(TempOrderIsExistReq infoReq) {
		this.infoReq = infoReq;
	}
	
	
}
