package com.hengyuan.hicash.service.validate.query;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.amount.AppScheduleReq;
import com.hengyuan.hicash.utils.RegexValidate;

/**
 * 查询申请件进度 请求参数验证
 * 
 * @author Cary.Liu
 * @createDate 2015-04-24
 *
 */
public class AppScheduleVal {

	private AppScheduleReq scheduleReq;

	public AppScheduleVal(AppScheduleReq scheduleReq) {
		this.scheduleReq = scheduleReq;
	}

	public String validate(){
		
		if (RegexValidate.isNull(scheduleReq.getUuid())) {
			return ResultCodes.UUIDNULL;
		}
		
		if (!RegexValidate.isUuid(scheduleReq.getUuid())) {
			return ResultCodes.UUIDILLEGAL;
		}
		
		//验证用户名
		if (RegexValidate.isNull(scheduleReq.getUserName())) {
			return ResultCodes.LOGIN_USERNAME_IS_NULL;
		}
		
		if (!RegexValidate.isUsername(scheduleReq.getUserName())) {
			return ResultCodes.LOGIN_USERNAME_ILLEGAL;
		}
		
		return ResultCodes.NORMAL;
	}
	
	public AppScheduleReq getScheduleReq() {
		return scheduleReq;
	}

	public void setScheduleReq(AppScheduleReq scheduleReq) {
		this.scheduleReq = scheduleReq;
	}

}
