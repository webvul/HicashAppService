package com.hengyuan.hicash.service.validate.update;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.ResetPswUpdateReq;
import com.hengyuan.hicash.utils.RegexValidate;

/**
 * 忘记密码-修改密码 validate
 * @author Cary.Liu
 * @createDate 2015-06-12
 * 
 */
public class ResetPswUpdateVal {

	private ResetPswUpdateReq pswUpdateReq;

	public ResetPswUpdateVal(ResetPswUpdateReq pswUpdateReq) {
		this.pswUpdateReq = pswUpdateReq;
	}
	
	public String validate(){
		
		/* 验证uuid */
		if(RegexValidate.isNull(pswUpdateReq.getUuid())){
			return ResultCodes.UUIDNULL;
		}
		
		if(!RegexValidate.isUuid(pswUpdateReq.getUuid())){
			return ResultCodes.UUIDILLEGAL;
		}
		
		if(RegexValidate.isNull(pswUpdateReq.getMobileNo())){
			return ResultCodes.SENDPSWCODE_MOBILE_ISNULL;
		}
		
		if(!RegexValidate.isIphon(pswUpdateReq.getMobileNo())){
			return ResultCodes.SENDPSWCODE_MOBILE_ILLEGAL;
		}
		
		/* 新密码 */
		if(RegexValidate.isNull(pswUpdateReq.getNewPassWord())){
			return ResultCodes.RESETPSW_PSW_ISNULL;
		}
		
		if(!RegexValidate.isStringFilter(pswUpdateReq.getNewPassWord())||!RegexValidate.isLength(pswUpdateReq.getNewPassWord(), 4, 20)){
			return ResultCodes.RESETPSW_PSW_ILLEGAL;
		}
		
		return ResultCodes.NORMAL;
	}
	

	public ResetPswUpdateReq getPswUpdateReq() {
		return pswUpdateReq;
	}

	public void setPswUpdateReq(ResetPswUpdateReq pswUpdateReq) {
		this.pswUpdateReq = pswUpdateReq;
	}

}
