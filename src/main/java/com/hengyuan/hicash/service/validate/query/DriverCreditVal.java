package com.hengyuan.hicash.service.validate.query;


import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.JxlOrgApiApplicationsReq;
import com.hengyuan.hicash.utils.RegexValidate;

/** 
 * @author blianke.qin
 * 2017年1月11日 下午6:51:13
 * 类说明 
 */
public class DriverCreditVal {
	
	private JxlOrgApiApplicationsReq Req;
	
	public DriverCreditVal(JxlOrgApiApplicationsReq jxlOrgApiApplicationsReq){
		 this.Req=jxlOrgApiApplicationsReq;
	}

	/*
	*//**	用户名不能为空*//*
	public static final String USERNAME_ISNULL= "37510";
	
	*//**	time不能为空*//*
	public static final String TIME_ISNULL= "37511";
	
	*//**	服务密码不能为空*//*
	public static final String PASSWORD_ISNULL= "37514";
	
	*//**	短信验证码不能为空*//*
	public static final String MESSAGE_ISNULL= "37515";
	
	*//**	认证流水token不能为空*//*
	public static final String SEQ_NO_ISNULL= "37516";
	*/
	
	public String validate(){

		if (RegexValidate.isNull(Req.getUsername())) {
			return ResultCodes.USERNAME_ISNULL;
		}
		if (RegexValidate.isNull(Req.getTime())) {
			return ResultCodes.TIME_ISNULL;
		}
		if (RegexValidate.isNull(Req.getMobile())) {
			return ResultCodes.SENDAMOUNTCODE_MOBILE_ISNULL;
		}
		
		if (RegexValidate.isNull(Req.getTemp_app_no())) {
			return ResultCodes.CREATEAPPPAY_TEMPAPPNO_ISNULL;
		}
		
		if(!Req.getTime().equals("1") && !Req.getTime().equals("2") && !Req.getTime().equals("3") && !Req.getTime().equals("4")){
			return ResultCodes.TIME_EXCEPTION;
		}

		if ("2".equals(Req.getTime())) {

//			if (RegexValidate.isNull(Req.getPassword())) {
//				return ResultCodes.PASSWORD_ISNULL;
//			}
			if (RegexValidate.isNull(Req.getSeq_no())) {
				return ResultCodes.SEQ_NO_ISNULL;
			}

		} else if ("3".equals(Req.getTime())) {
//			if (RegexValidate.isNull(Req.getPassword())) {
//				return ResultCodes.PASSWORD_ISNULL;
//			}
			if (RegexValidate.isNull(Req.getMessage())) {
				return ResultCodes.MESSAGE_ISNULL;
			}
			if (RegexValidate.isNull(Req.getSeq_no())) {
				return ResultCodes.SEQ_NO_ISNULL;
			}

		} else if ("4".equals(Req.getTime())) {
//			if (RegexValidate.isNull(Req.getPassword())) {
//				return ResultCodes.PASSWORD_ISNULL;
//			}
			if (RegexValidate.isNull(Req.getSeq_no())) {
				return ResultCodes.SEQ_NO_ISNULL;
			}

		}
		return ResultCodes.NORMAL;
		
	}
	
	public JxlOrgApiApplicationsReq getReq() {
		return Req;
	}

	public void setReq(JxlOrgApiApplicationsReq req) {
		Req = req;
	}
	
	
	
}
