package com.hengyuan.hicash.service.validate.update;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.mktApp.CreateAppPayReq;
import com.hengyuan.hicash.parameters.request.mktApp.CreateCreditReq;
import com.hengyuan.hicash.utils.RegexValidate;

/**
 * 
* @author dong.liu 
* 2017-3-23 下午6:52:29
* 类说明:正式申请件validate验证
 */
		
public class CreateCreditVal {

	private CreateCreditReq   valReq;
	
	public CreateCreditVal(CreateCreditReq valReq){
		this.valReq = valReq;
	}

	public String validate(){
		
		
		/* 临时申请件单号 */
		if(RegexValidate.isNull(valReq.getTemp_no())){
			return ResultCodes.CREATEAPPPAY_TEMPAPPNO_ISNULL;
		}
		
//		if(!RegexValidate.isAppNo(valReq.getTemp_no())){
//			return ResultCodes.CREATEAPPPAY_TEMPAPPNO_ILLEGAL;
//		}
		
		return ResultCodes.NORMAL;
	}

	public CreateCreditReq getValReq() {
		return valReq;
	}

	public void setValReq(CreateCreditReq valReq) {
		this.valReq = valReq;
	}
	
	
}
