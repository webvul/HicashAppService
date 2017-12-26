package com.hengyuan.hicash.service.validate.query;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.SearchBankCardReq;
import com.hengyuan.hicash.utils.RegexValidate;

/**
 * 查询银行卡信息 参数验证
 * 
 * @author Cary.Liu
 * @create 2014-09-29
 * 
 */
public class SearchBankCardVal {

	private SearchBankCardReq cardReq;

	public SearchBankCardVal(SearchBankCardReq cardReq) {
		this.cardReq = cardReq;
	}

	public String validate() {
		
		if (RegexValidate.isNull(cardReq.getUuid())) {
			return ResultCodes.UUIDNULL;
		}
		
		if (!RegexValidate.isUuid(cardReq.getUuid())) {
			return ResultCodes.UUIDILLEGAL;
		}
		
		//验证用户名
		if (RegexValidate.isNull(cardReq.getUserName())) {
			return ResultCodes.LOGIN_USERNAME_IS_NULL;
		}
		//不能有非法字符
		if (!RegexValidate.isUsername(cardReq.getUserName())) {
			return ResultCodes.LOGIN_USERNAME_ILLEGAL;
		}
		
		return ResultCodes.NORMAL;
	}

	public SearchBankCardReq getCardReq() {
		return cardReq;
	}

	public void setCardReq(SearchBankCardReq cardReq) {
		this.cardReq = cardReq;
	}

}
