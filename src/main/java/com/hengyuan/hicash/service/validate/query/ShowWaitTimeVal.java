package com.hengyuan.hicash.service.validate.query;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.JxlOrgApiApplicationsReq;
import com.hengyuan.hicash.parameters.request.user.ShowWaitTimeReq;
import com.hengyuan.hicash.utils.RegexValidate;

/** 
 * @author blianke.qin 
 * 2017年1月10日 上午11:43:26
 * 类说明 
 */
public class ShowWaitTimeVal {
	private ShowWaitTimeReq showWaitTimeReq;
	public ShowWaitTimeVal(ShowWaitTimeReq Req){
		 this.showWaitTimeReq=Req;
	}
	
    public String validate() {
		
		//验证流水号
		if (RegexValidate.isNull(showWaitTimeReq.getAppNo())) {
			return ResultCodes.STU_UPDATE_APPNO_NOTNULL;
		}
		if(!RegexValidate.isAppNo(showWaitTimeReq.getAppNo())){
			return ResultCodes.STU_UPDATE_APPNO_FAIL;
		}
		return ResultCodes.NORMAL;
		
	}

	public ShowWaitTimeReq getShowWaitTimeReq() {
		return showWaitTimeReq;
	}

	public void setShowWaitTimeReq(ShowWaitTimeReq showWaitTimeReq) {
		this.showWaitTimeReq = showWaitTimeReq;
	}
    
	
}
