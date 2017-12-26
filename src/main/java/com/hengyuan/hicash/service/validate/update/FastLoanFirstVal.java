package com.hengyuan.hicash.service.validate.update;

import java.util.regex.Pattern;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.FastLoanFirstUpReq;
import com.hengyuan.hicash.utils.RegexValidate;


/**
 * 保存秒贷期数金额  请求参数验证
 * 
 * @author LiHua.Ren
 * @create date 2015-05-23
 * 
 */
public class FastLoanFirstVal{

	private FastLoanFirstUpReq infoReq;

	public FastLoanFirstVal(FastLoanFirstUpReq infoReq){
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
		if(RegexValidate.isNull(infoReq.getUserName())){
			return ResultCodes.USER_ERROR_ISNULL;
		}
		
		if(!RegexValidate.isUsername(infoReq.getUserName())){
			return ResultCodes.USER_NAME_ERROR_CANTCHAR;
		}
		if(RegexValidate.isNull(infoReq.getFastAmt())){
			return ResultCodes.FAST_LOAN_AMOUNT_NOTNULL;
		}
		
		if (!RegexValidate.isDigitFloatPos(infoReq.getFastAmt())) {
			return ResultCodes.FAST_LOAN_AMOUNT_EXCEPTION;
		}
		
		if (!RegexValidate.isLength(infoReq.getFastAmt(),1,10)) {
			return ResultCodes.FAST_LOAN_AMOUNT_LENGTH;
		}
		 Pattern pattern = Pattern.compile("[0-9]+");
		if(!pattern.matcher(infoReq.getFastPeriod()).matches()){
			return ResultCodes.FAST_LOAN_PERIOD_NOTRIGHT;
		}
		
			
		return ResultCodes.NORMAL;
	}
	
	public FastLoanFirstUpReq getInfoReq() {
		return infoReq;
	}

	public void setInfoReq(FastLoanFirstUpReq infoReq) {
		this.infoReq = infoReq;
	}
	
	
}
