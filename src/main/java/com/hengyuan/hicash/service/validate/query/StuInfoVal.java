package com.hengyuan.hicash.service.validate.query;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.param.StuInfoReq;
import com.hengyuan.hicash.utils.RegexValidate;

/**
 * 学生个人信息查询验证
* @author dong.liu 
* 2017-1-9 下午6:17:55
* 类说明
 */
public class StuInfoVal extends RegexValidate {
	
	private StuInfoReq stuInfoReq;
	public StuInfoVal(StuInfoReq stuInfoReq){
		
		this.stuInfoReq = stuInfoReq;
		
	}

	public String validate(){
		
		if (RegexValidate.isNull(stuInfoReq.getUuid())) {
			return ResultCodes.UUIDNULL;
		}
		
		if (!RegexValidate.isUuid(stuInfoReq.getUuid())) {
			return ResultCodes.UUIDILLEGAL;
		}
		
		if (RegexValidate.isNull(stuInfoReq.getUserName())) {
			return ResultCodes.USER_ERROR_ISNULL;
		}
		
		if (!RegexValidate.isUsername(stuInfoReq.getUserName())) {
			return ResultCodes.USER_NAME_ERROR_CANTCHAR;
		}
		
		return ResultCodes.NORMAL;
	}
	
	
	public StuInfoReq getstuInfoReq() {
		return stuInfoReq;
	}

	public void setstuInfoReq(StuInfoReq stuInfoReq) {
		this.stuInfoReq = stuInfoReq;
	}
}
