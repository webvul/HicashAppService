package com.hengyuan.hicash.service.validate.query;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.ServicePswValReq;
import com.hengyuan.hicash.utils.RegexValidate;

/**
 * 验证用户服务密码 validate
 * 
 * @author Cary.Liu
 * @createDate 2105-06-09
 *
 */
public class ServicePswValVal {

	private ServicePswValReq pswReq;

	public ServicePswValVal(ServicePswValReq pswReq) {
		this.pswReq = pswReq;
	}
	
	public String validate(){
		
		if (RegexValidate.isNull(pswReq.getUuid())) {
			return ResultCodes.UUIDNULL;
		}
		
		if (!RegexValidate.isUuid(pswReq.getUuid())) {
			return ResultCodes.UUIDILLEGAL;
		}
		
		//验证用户名
		if (RegexValidate.isNull(pswReq.getUserName())) {
			return ResultCodes.LOGIN_USERNAME_IS_NULL;
		}
		
		if (!RegexValidate.isUsername(pswReq.getUserName())) {
			return ResultCodes.LOGIN_USERNAME_ILLEGAL;
		}
		
		//验证服务密码
		if (RegexValidate.isNull(pswReq.getServicePsw())) {
			return ResultCodes.SERVICEPSWVAL_CODE_ISNULL;
		}

		if (!RegexValidate.isNumOrLetter(pswReq.getServicePsw())) {
			return ResultCodes.SERVICEPSWVAL_CODE_ILLEGAL;
		}
		
		//验证申请件单号
		if (RegexValidate.isNull(pswReq.getAppNo())) {
			return ResultCodes.SERVICEPSWVAL_APPNO_ISNULL;
		}

		if (!RegexValidate.isAppNo(pswReq.getAppNo())) {
			return ResultCodes.SERVICEPSWVAL_APPNO_ILLEGAL;
		}
		
		//验证临时申请件单号
		if (RegexValidate.isNull(pswReq.getTempAppNo())) {
			return ResultCodes.SERVICEPSWVAL_TEMPAPPNO_ISNULL;
		}

		if (!RegexValidate.isAppNo(pswReq.getTempAppNo())) {
			return ResultCodes.SERVICEPSWVAL_TEMPAPPNO_ILLEGAL;
		}
		
		//提交类型
		if(!RegexValidate.isNull(pswReq.getSubmitType()) && Consts.SUBMITTYPE_MOBILE.equals(pswReq.getSubmitType())){
			//验证动态密码
			if (RegexValidate.isNull(pswReq.getDynamicPsw())) {
				return ResultCodes.SERVICEPSWVAL_DTPSW_ISNULL;
			}
			
			//验证token
			if (RegexValidate.isNull(pswReq.getToken())) {
				return ResultCodes.SERVICEPSWVAL_TOKEN_ISNULL;
			}
			
			//验证运营商代码
			if (RegexValidate.isNull(pswReq.getWebSite())) {
				return ResultCodes.SERVICEPSWVAL_WEBSITE_ISNULL;
			}
			
		}
		
		return ResultCodes.NORMAL;
	}

	public ServicePswValReq getPswReq() {
		return pswReq;
	}

	public void setPswReq(ServicePswValReq pswReq) {
		this.pswReq = pswReq;
	}

}
