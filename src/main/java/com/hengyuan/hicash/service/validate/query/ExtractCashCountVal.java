package com.hengyuan.hicash.service.validate.query;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.ExtractCashCountReq;
import com.hengyuan.hicash.utils.RegexValidate;

/**
 * 提现笔数 请求参数验证
 * 
 * @author Cary.Liu
 * @create 2014-09-17
 * 
 */
public class ExtractCashCountVal {

	private ExtractCashCountReq cashCountReq;

	public ExtractCashCountVal(ExtractCashCountReq cashCountReq) {
		this.cashCountReq = cashCountReq;
	}
	
	public String validate(){
		
		if (RegexValidate.isNull(cashCountReq.getUuid())) {
			return ResultCodes.UUIDNULL;
		}
		
		if (!RegexValidate.isUuid(cashCountReq.getUuid())) {
			return ResultCodes.UUIDILLEGAL;
		}
		
		if(!RegexValidate.isNull(cashCountReq.getTrimNum())){
			
			if(!RegexValidate.isDigitFloatPos(cashCountReq.getTrimNum())){
				return ResultCodes.EXTRACTCASH_TRIM_ILLEGAL;
			}
			
		}
		
		return ResultCodes.NORMAL;
	}

	public ExtractCashCountReq getCashCountReq() {
		return cashCountReq;
	}

	public void setCashCountReq(ExtractCashCountReq cashCountReq) {
		this.cashCountReq = cashCountReq;
	}

}
