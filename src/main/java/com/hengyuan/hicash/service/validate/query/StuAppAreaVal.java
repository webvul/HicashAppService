package com.hengyuan.hicash.service.validate.query;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.StuAppAreaReq;
import com.hengyuan.hicash.utils.RegexValidate;

/**
 * hicash手机端学生提现申请查询区验证
 * 
 * @author lihua.Ren
 * @create date 2015-05-27
 *
 */
public class StuAppAreaVal {
private StuAppAreaReq queryMsgReq;
	
	public StuAppAreaVal(StuAppAreaReq queryMsgReq){
		
		this.queryMsgReq = queryMsgReq;
		
	}

	public String validate(){
		
		if (RegexValidate.isNull(queryMsgReq.getUuid())) {
			return ResultCodes.UUIDNULL;
		}
		
		if (!RegexValidate.isUuid(queryMsgReq.getUuid())) {
			return ResultCodes.UUIDILLEGAL;
		}

		if (RegexValidate.isNull(queryMsgReq.getCityCode())) {
			return ResultCodes.STU_APP_AREA_PRO_NOTNULL;
		}
		if(!RegexValidate.isSelectToAddress(queryMsgReq.getCityCode())){
			return ResultCodes.STU_APP_AREA_PRO_FALSE;
		}
		return ResultCodes.NORMAL;
	}
	
	
	public StuAppAreaReq getQueryMsgReq() {
		return queryMsgReq;
	}
	

}
