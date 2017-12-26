package com.hengyuan.hicash.service.validate.update;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.SaveMyMessageReq;
import com.hengyuan.hicash.utils.RegexValidate;

public class SaveMyMessageVal {
	
	 	private SaveMyMessageReq valReq;

		public SaveMyMessageVal(SaveMyMessageReq valReq) {
			this.valReq = valReq;
		}
		public String validate(){
			
			
			/* 姓名*/
			if(RegexValidate.isNull(valReq.getName())){
				return ResultCodes.REGISTER_REALNAME_ISNULL;
			}
			/* 消息code*/
			if(RegexValidate.isNull(valReq.getCode())){
				return ResultCodes.DDSJ_CODE_IS_NULL;
			}
			/* 用户名 */
			if(RegexValidate.isNull(valReq.getUserName())){
				return ResultCodes.ADDMERAPP_USERNAME_ISNULL;
			}
			/* 身份证号 */
			if(RegexValidate.isNull(valReq.getIdNo())){
				return ResultCodes.IDCARD_ISNULL;
			}
			/* 手机号 */
			if(RegexValidate.isNull(valReq.getMobile())){
				return ResultCodes.SENDAMOUNTCODE_MOBILE_ISNULL;
			}
			
			
			return ResultCodes.NORMAL;
			
		}
}
