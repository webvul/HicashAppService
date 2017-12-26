package com.hengyuan.hicash.service.validate.query;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.RegisterCountReq;
import com.hengyuan.hicash.utils.RegexValidate;

/**
 * 注册人数统计 参数验证
 * 
 * @author Cary.Liu
 * @create 2014-09-17
 * 
 */
public class RegisterCountVal {

	private RegisterCountReq countReq;

	public RegisterCountVal(RegisterCountReq countReq) {
		this.countReq = countReq;
	}
	
	public String validate(){
		
		if (RegexValidate.isNull(countReq.getUuid())) {
			return ResultCodes.UUIDNULL;
		}
		
		if (!RegexValidate.isUuid(countReq.getUuid())) {
			return ResultCodes.UUIDILLEGAL;
		}
		
		if(RegexValidate.isNull(countReq.getRegisterType())){
			return ResultCodes.REGISTERCOUNT_TYPE_NOTNULL;
		}
		
		if (!Consts.FINAL_NUMBER_0.equals(countReq.getRegisterType())
				&& !Consts.FINAL_NUMBER_1.equals(countReq.getRegisterType())
				&& !Consts.FINAL_NUMBER_2.equals(countReq.getRegisterType())) {
			
			return ResultCodes.REGISTERCOUNT_TYPE_ILLEGAL;
		}
		
		
		return ResultCodes.NORMAL;
	}

	public RegisterCountReq getCountReq() {
		return countReq;
	}

	public void setCountReq(RegisterCountReq countReq) {
		this.countReq = countReq;
	}

}
