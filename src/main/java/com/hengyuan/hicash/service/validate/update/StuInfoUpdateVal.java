package com.hengyuan.hicash.service.validate.update;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.param.StuInfoReq;
import com.hengyuan.hicash.utils.RegexValidate;

/**
 * 
* @author dong.liu 
* 2017-1-10 下午2:43:51
* 类说明:学生个人信息修改请求参数  请求参数验证
 */
public class StuInfoUpdateVal {

	private StuInfoReq infoReq;

	public StuInfoUpdateVal(StuInfoReq infoReq){
		this.infoReq = infoReq;
	}

	public String validate(){
		
		/* 验证uuid */
		if(RegexValidate.isNull(infoReq.getUuid())){
			return ResultCodes.UUIDNULL;
		}
		
		if(!RegexValidate.isUuid(infoReq.getUuid())){
			return ResultCodes.UUIDILLEGAL;
		}
		
		/* 验证用户名 */
		if(RegexValidate.isNull(infoReq.getUserName())){
			return ResultCodes.USER_ERROR_ISNULL;
		}
		
		if(!RegexValidate.isUsername(infoReq.getUserName())){
			return ResultCodes.USER_NAME_ERROR_CANTCHAR;
		}
	
		/* 真实姓名 */
		if (RegexValidate.isNull(infoReq.getRealName())) {
			return ResultCodes.REGISTER_REALNAME_ISNULL;
		}

		if (!RegexValidate.isRealName(infoReq.getRealName())) {
			return ResultCodes.REGISTER_REALNAME_ILLEGAL;
		}
		/* 身份证号码 */
		if (RegexValidate.isNull(infoReq.getIdentityNo())) {
			return ResultCodes.REGISTER_IDCARD_ISNULL;
		}

		if (!RegexValidate.isIdCard(infoReq.getIdentityNo())) {
			return ResultCodes.REGISTER_IDCARD_ILLEGAL;
		}
		/* 婚姻状况 */
		if(RegexValidate.isNull(infoReq.getMaritalStatus())){
		
			return ResultCodes.COLLAR_INFO_MATRIMONY_NOTNULL;
		}
		
		/* 验证邮箱 */
		if (RegexValidate.isNull(infoReq.getEmailAdress())) {
			return ResultCodes.CALL_PHONE_QUARTET_PROTOCOL_EMAIL_ISNULL;
		}
		if (!RegexValidate.isEmail(infoReq.getEmailAdress())) {
			return ResultCodes.CALL_PHONE_QUARTET_PROTOCOL_EMAIL_ERROR;
		}
		/* 省市区地址 */
		if (RegexValidate.isNull(infoReq.getOtherAdressProvince())
				|| RegexValidate.isNull(infoReq.getOtherAdressCity())
				|| RegexValidate.isNull(infoReq.getOtherAdressArea())
				|| RegexValidate.isNull(infoReq.getOtherAccommodationAddress())) {
			return ResultCodes.STU_CONTACT_NOW_AREA_NOTNULL;
		}
		
		/* 现居详细地址*/
		if(RegexValidate.isNull(infoReq.getOtherAccommodationAddress())){
		
			return ResultCodes.STU_CONTACT_NOW_AREA_DETAIL_NOTNULL;
		}
		
		if(!RegexValidate.isSelectToAddress(infoReq.getOtherAdressProvince())){
			return ResultCodes.STU_CONTACT_NOW_PROVINCE_FALSE;
		}
		
		if(!RegexValidate.isSelectToAddress(infoReq.getOtherAdressCity())){
			return ResultCodes.STU_CONTACT_NOW_CITY_FALSE;
		}
		
		if(!RegexValidate.isSelectToAddress(infoReq.getOtherAdressArea())){
			return ResultCodes.STU_CONTACT_NOW_AREA_FALSE;
		}
		
		//贷款用途
//		if (RegexValidate.isNull(infoReq.getLoan_purpose())) {
//			return ResultCodes.CREATEAPPPAY_LOANUSE_ISNULL;
//		}
//		
		return ResultCodes.NORMAL;
	}

	public StuInfoReq getInfoReq() {
		return infoReq;
	}

	public void setInfoReq(StuInfoReq infoReq) {
		this.infoReq = infoReq;
	}
	
	
	
	
}
