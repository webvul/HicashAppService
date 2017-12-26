package com.hengyuan.hicash.service.validate.query;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.StuAppProvinceReq;
import com.hengyuan.hicash.utils.RegexValidate;

/**
 * hicash手机端学生提现申请查询省验证
 * 
 * @author lihua.Ren
 * @create date 2015-05-27
 *
 */
public class StuAppProvinceVal {
private StuAppProvinceReq queryMsgReq;
	
	public StuAppProvinceVal(StuAppProvinceReq queryMsgReq){
		
		this.queryMsgReq = queryMsgReq;
		
	}

	public String validate(){
		
		if (RegexValidate.isNull(queryMsgReq.getUuid())) {
			return ResultCodes.UUIDNULL;
		}
		
		if (!RegexValidate.isUuid(queryMsgReq.getUuid())) {
			return ResultCodes.UUIDILLEGAL;
		}
		
		return ResultCodes.NORMAL;
	}
	
	
	public StuAppProvinceReq getQueryMsgReq() {
		return queryMsgReq;
	}
}
