package com.hengyuan.hicash.service.validate.mktApp;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.mktApp.PaymentReq;
import com.hengyuan.hicash.utils.RegexValidate;

/**
 * 临时申请列表 请求参数验证
 * 
 * @author Cary.Liu
 * @create 2015-03-12
 *
 */
public class PaymentVal {

	private PaymentReq paymentReq;

	public PaymentVal(PaymentReq paymentReq) {
		this.paymentReq = paymentReq;
	}

	public String validate(){
		
		if(RegexValidate.isNull(paymentReq.getCityCode())){
			return ResultCodes.PROSHOWINFO_CITYCODE_ISNULL;
		}
		
		if(RegexValidate.isNull(paymentReq.getCustType())){
			return ResultCodes.REGISTER_CUSTTYPE_ISNULL;
		}
		
		return ResultCodes.NORMAL;
	}

	public PaymentReq getPaymentReq() {
		return paymentReq;
	}

	public void setPaymentReq(PaymentReq paymentReq) {
		this.paymentReq = paymentReq;
	}


}
