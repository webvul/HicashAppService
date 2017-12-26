package com.hengyuan.hicash.service.validate.update;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.StuApp2UpdateReq;
import com.hengyuan.hicash.utils.RegexValidate;


/**
 * hicash手机端学生提现申请完善2验证参数类
 * 
 * @author lihua.Ren
 * @create date 2015-05-27
 *
 */
public class StuApp2UpdateVal {

private StuApp2UpdateReq contactInfoReq;
	
	public StuApp2UpdateVal(StuApp2UpdateReq contactInfoReq){
		this.contactInfoReq = contactInfoReq;
	}
	
	public String validate(){
		
		/* 验证uuid */
		if(RegexValidate.isNull(contactInfoReq.getUuid())){
			return ResultCodes.UUIDNULL;
		}
		
		if(!RegexValidate.isUuid(contactInfoReq.getUuid())){
			return ResultCodes.UUIDILLEGAL;
		}
		
		/* 验证用户名 */
		if(RegexValidate.isNull(contactInfoReq.getUserName())){
			return ResultCodes.USER_ERROR_ISNULL;
		}
		
		if(!RegexValidate.isUsername(contactInfoReq.getUserName())){
			return ResultCodes.USER_NAME_ERROR_CANTCHAR;
		}
		
		/* 资料的修改类型 */
		if(RegexValidate.isNull(contactInfoReq.getUpdateType())){
			return ResultCodes.USER_COLLACT_TYPE_NOTNULL;
		}
		
		if (!Consts.UPDATE_UNIVERSITY_TYPE_P.equals(contactInfoReq.getUpdateType())
				&& !Consts.UPDATE_UNIVERSITY_TYPE_U.equals(contactInfoReq
						.getUpdateType())) {
			return ResultCodes.USER_COLLACT_TYPE_FALSE;
		}
		
		if(Consts.UPDATE_UNIVERSITY_TYPE_P.equals(contactInfoReq.getUpdateType())){
			
			if(RegexValidate.isNull(contactInfoReq.getAppNo())){
				return ResultCodes.USER_COLLACT_APPNO_NOTNULL;
			}
			
			if(!RegexValidate.isNumber(contactInfoReq.getAppNo())||!RegexValidate.isLength(contactInfoReq.getAppNo(), 14,14)){
				return ResultCodes.USER_COLLACT_APPNO_FALSE;
			}
			
		}
		
		if (RegexValidate.isNull(contactInfoReq.getSchoolProvince())
				|| RegexValidate.isNull(contactInfoReq.getSchoolCity())
				|| RegexValidate.isNull(contactInfoReq.getSchoolDistrict())) {
			return ResultCodes.STU_APP_SCHOOL_AREA_NOTNULL;
		}
		
		if(!RegexValidate.isSelectToAddress(contactInfoReq.getSchoolProvince())){
				return ResultCodes.STU_APP_SCHOOL_PROVINCE_FALSE;
		}
		
		if(!RegexValidate.isSelectToAddress(contactInfoReq.getSchoolCity())){
			return ResultCodes.STU_APP_SCHOOL_CITY_FALSE;
		}
		
		if(!RegexValidate.isSelectToAddress(contactInfoReq.getSchoolDistrict())){
			return ResultCodes.STU_CONTACT_SCHOOL_AREA_FALSE;
		}
		
		/* 验证学校详细地址 */
		if (RegexValidate.isNull(contactInfoReq.getSchoolDetails())) {
			return ResultCodes.STU_APP_SCHOOL_AREA_DETAIL_NOTNULL;
		}
		
		if (!RegexValidate.isStrFilter(contactInfoReq.getSchoolDetails())) {
			return ResultCodes.STU_APP_SCHOOL_AREA_DETAIL_FALSE;
		}
		
		if (!RegexValidate.isLength(contactInfoReq.getSchoolDetails(),0,50)) {
			return ResultCodes.STU_APP_SCHOOL_AREA_DETAIL_LENGTH;
		}

		if (RegexValidate.isNull(contactInfoReq.getHomeProvince())
				|| RegexValidate.isNull(contactInfoReq.getHomeCity())
				|| RegexValidate.isNull(contactInfoReq.getHomeDistrict())) {
			return ResultCodes.STU_APP_HOME_AREA_NOTNULL;
		}
		
		if(!RegexValidate.isSelectToAddress(contactInfoReq.getHomeProvince())){
			return ResultCodes.STU_APP_CONTACT_HOME_PROVINCE_FALSE;
		}
		
		if(!RegexValidate.isSelectToAddress(contactInfoReq.getHomeCity())){
			return ResultCodes.STU_APP_CONTACT_HOME_CITY_FALSE;
		}
		
		if(!RegexValidate.isSelectToAddress(contactInfoReq.getHomeDistrict())){
			return ResultCodes.STU_APP_CONTACT_HOME_AREA_FALSE;
		}

		if (RegexValidate.isNull(contactInfoReq.getHomeDetails())) {
			return ResultCodes.STU_APP_HOME_AREA_DETAIL_NOTNULL;
		}
		
		if (!RegexValidate.isStrFilter(contactInfoReq.getHomeDetails())) {
			return ResultCodes.STU_APP_HOME_AREA_DETAIL_FALSE;
		}
		
		if (!RegexValidate.isLength(contactInfoReq.getHomeDetails(),0,50)) {
			return ResultCodes.STU_APP_HOME_AREA_DETAIL_LENGTH;
		}
		if(RegexValidate.isExpressType(contactInfoReq.getExpressAddressType()))
		{
			return ResultCodes.STU_APP_EXPRESSL_ADDRESSTYPE_FALSE;
		}
    //如果是其他地址
		if(contactInfoReq.getExpressAddressType().equals(Consts.OTHER_CODE))
		{

			if (RegexValidate.isNull(contactInfoReq.getExpressProvince())
					|| RegexValidate.isNull(contactInfoReq.getExpressCity())
					|| RegexValidate.isNull(contactInfoReq.getExpressDistrict())) {
				return ResultCodes.STU_APP_EXPRESS_AREANOTNULL;
			}
			
			if(!RegexValidate.isSelectToAddress(contactInfoReq.getExpressProvince())){
					return ResultCodes.STU_APP_EXPRESS_PROVINCE_FALSE;
			}
			
			if(!RegexValidate.isSelectToAddress(contactInfoReq.getExpressCity())){
				return ResultCodes.STU_APP_EXPRESS_CITY_FALSE;
			}
			
			if(!RegexValidate.isSelectToAddress(contactInfoReq.getExpressDistrict())){
				return ResultCodes.STU_APP_EXPRESSL_AREA_FALSE;
			}
			
			/* 验证学校详细地址 */
			if (RegexValidate.isNull(contactInfoReq.getExpressDetails())) {
				return ResultCodes.STU_APP_EXPRESSL_AREA_DETAIL_NOTNULL;
			}
			
			if (!RegexValidate.isStrFilter(contactInfoReq.getExpressDetails())) {
				return ResultCodes.STU_APP_EXPRESSL_AREA_DETAIL_FALSE;
			}
			
			if (!RegexValidate.isLength(contactInfoReq.getExpressDetails(),0,50)) {
				return ResultCodes.STU_APP_EXPRESSL_AREA_DETAIL_LENGTH;
			}
			
		}
		return ResultCodes.NORMAL;
	}

	public StuApp2UpdateReq getContactInfoReq() {
		return contactInfoReq;
	}

	public void setContactInfoReq(StuApp2UpdateReq contactInfoReq) {
		this.contactInfoReq = contactInfoReq;
	}
	
	

}
