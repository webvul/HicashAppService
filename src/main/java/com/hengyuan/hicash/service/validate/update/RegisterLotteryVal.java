package com.hengyuan.hicash.service.validate.update;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.RegisterLotteryReq;
import com.hengyuan.hicash.utils.RegexValidate;

/**
 * 用户注册抽奖 validate
 * 
 * @author Cary.Liu
 * @createDate 2016-01-11
 *
 */
public class RegisterLotteryVal {

	private RegisterLotteryReq lotteryReq;

	public RegisterLotteryVal(RegisterLotteryReq lotteryReq) {
		this.lotteryReq = lotteryReq;
	}
	
	public String validate(){
		
		//用户名
		if (RegexValidate.isNull(lotteryReq.getUserName())) {
			return ResultCodes.USER_ERROR_ISNULL;
		}

		if (!RegexValidate.isUsername(lotteryReq.getUserName())) {
			return ResultCodes.USER_NAME_ERROR_CANTCHAR;
		}
		
		return ResultCodes.NORMAL;
	}

	public RegisterLotteryReq getLotteryReq() {
		return lotteryReq;
	}

	public void setLotteryReq(RegisterLotteryReq lotteryReq) {
		this.lotteryReq = lotteryReq;
	}

}
