package com.hengyuan.hicash.service.validate.update;

import java.util.regex.Pattern;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.CallPhoneDataNumUpdateReq;
import com.hengyuan.hicash.utils.RegexValidate;

/**
 * @author Administrator
 *
 */
public class CallPhoneDataNumUpdateVal {

	private CallPhoneDataNumUpdateReq valReq;

	public CallPhoneDataNumUpdateVal(CallPhoneDataNumUpdateReq val){
		this.valReq = val;
	}

	public String validate() {

		/* 验证uuid */
		if (RegexValidate.isNull(valReq.getUuid())) {
			return ResultCodes.UUIDNULL;
		}

		if (!RegexValidate.isUuid(valReq.getUuid())) {
			return ResultCodes.UUIDILLEGAL;
		}

		/* 验证用户名 */
		if (RegexValidate.isNull(valReq.getUserName())) {
			return ResultCodes.LOGIN_USERNAME_IS_NULL;
		}

		if (!RegexValidate.isUsername(valReq.getUserName())) {
			return ResultCodes.LOGIN_USERNAME_ILLEGAL;
		}
		
		/* 验证用户名 */
		if (RegexValidate.isNull(valReq.getUserName())) {
			return ResultCodes.LOGIN_USERNAME_IS_NULL;
		}

		if (!RegexValidate.isUsername(valReq.getUserName())) {
			return ResultCodes.LOGIN_USERNAME_ILLEGAL;
		}
		/*手机号吗*/
		if(RegexValidate.isNull(valReq.getPhoneNum())){
			return ResultCodes.SENDAMOUNTCODE_MOBILE_ISNULL;
		}
		
		if(!RegexValidate.isIphon(valReq.getPhoneNum())){
			return ResultCodes.SENDAMOUNTCODE_MOBILE_FAIL;
		}
		/*套餐id*/
		if(RegexValidate.isNull(valReq.getPhoneDataId())){
			return ResultCodes.CALL_PHONE_DATA_NUM_ID_NOTNULL;
		}
		
		if(!RegexValidate.isDigit(valReq.getPhoneDataId())){
			return ResultCodes.CALL_PHONE_DATA_NUM_ID_NOTRIGHT;
		}
		
		/*金额*/
		if(RegexValidate.isNull(valReq.getProPrice())){
			return ResultCodes.CALL_PHONE_DATA_AMOUNT_NOTNULL;
		}
		
		if (!RegexValidate.isDigitFloatPos(valReq.getProPrice())) {
			return ResultCodes.CALL_PHONE_DATA_AMOUNT_EXCEPTION;
		}
		
		if (!RegexValidate.isLength(valReq.getProPrice(),1,10)) {
			return ResultCodes.CALL_PHONE_DATA_AMOUNT_LENGTH;
		}
		/*期数*/
		 Pattern pattern = Pattern.compile("[0-9]+");
			if(!pattern.matcher(valReq.getProPeriod()).matches()){
				return ResultCodes.CALL_PHONE_DATA_PERIOD_NOTRIGHT;
			}
		
		
		return ResultCodes.NORMAL;
	}

	public CallPhoneDataNumUpdateReq getValReq() {
		return valReq;
	}

	public void setValReq(CallPhoneDataNumUpdateReq valReq) {
		this.valReq = valReq;
	}

}
