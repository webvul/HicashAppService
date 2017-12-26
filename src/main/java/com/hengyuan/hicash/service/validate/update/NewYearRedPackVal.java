package com.hengyuan.hicash.service.validate.update;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.activity.NewYearRedPackReq;
import com.hengyuan.hicash.utils.RegexValidate;

/**
 * 新年红包抽取 validate
 * 
 * @author Cary.Liu
 * @createDate 2016-02-01
 *
 */
public class NewYearRedPackVal {

	private NewYearRedPackReq redPackReq;

	public NewYearRedPackVal(NewYearRedPackReq redPackReq) {
		this.redPackReq = redPackReq;
	}
	
	public String validate(){
		
		//用户名
		if (RegexValidate.isNull(redPackReq.getUserName())) {
			return ResultCodes.USER_ERROR_ISNULL;
		}

		if (!RegexValidate.isUsername(redPackReq.getUserName())) {
			return ResultCodes.USER_NAME_ERROR_CANTCHAR;
		}
		
		return ResultCodes.NORMAL;
	}

	public NewYearRedPackReq getRedPackReq() {
		return redPackReq;
	}

	public void setRedPackReq(NewYearRedPackReq redPackReq) {
		this.redPackReq = redPackReq;
	}

}
