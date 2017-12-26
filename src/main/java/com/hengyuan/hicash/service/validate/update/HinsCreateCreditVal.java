package com.hengyuan.hicash.service.validate.update;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.mktApp.HinsCreateCreditReq;
import com.hengyuan.hicash.utils.RegexValidate;

/**
 * 	
* @author dong.liu 
* 2017-5-13 上午11:30:34
* 类说明:嗨女神正式申请件validate验证
 */
public class HinsCreateCreditVal {

	private HinsCreateCreditReq   valReq;
	
	public HinsCreateCreditVal(HinsCreateCreditReq valReq){
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

	public HinsCreateCreditReq getValReq() {
		return valReq;
	}

	public void setValReq(HinsCreateCreditReq valReq) {
		this.valReq = valReq;
	}
	
	
}
