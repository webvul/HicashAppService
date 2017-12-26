package com.hengyuan.hicash.service.validate.update;

import java.math.BigDecimal;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.query.app.ApplicationQuery;
import com.hengyuan.hicash.domain.query.param.SystemParamQuery;
import com.hengyuan.hicash.entity.app.ApplicationEntity;
import com.hengyuan.hicash.parameters.request.user.NewStuApp1UpdateReq;
import com.hengyuan.hicash.parameters.request.user.StuApp1UpdateReq;
import com.hengyuan.hicash.utils.RegexValidate;
import com.hengyuan.hicash.utils.StringUtils;


/**
 * hicash手机端学生提现申请完善1验证参数类
 * 
 * @author lihua.Ren
 * @create date 2015-05-27
 *
 */
public class NewStuApp1UpdateVal {
	
	private NewStuApp1UpdateReq infoReq;
	
	public NewStuApp1UpdateVal(NewStuApp1UpdateReq infoReq){
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
		
		/* 资料的修改类型 */
		if(RegexValidate.isNull(infoReq.getUpdateType())){
			return ResultCodes.STU_APP_UPDATE_TYPE_NOTNULL;
		}
		
		if (!Consts.UPDATE_UNIVERSITY_TYPE_P.equals(infoReq.getUpdateType())
				&& !Consts.UPDATE_UNIVERSITY_TYPE_U.equals(infoReq
						.getUpdateType())) {
			return ResultCodes.STU_APP_UPDATE_TYPE_FAIL;
		}
		
		if(Consts.UPDATE_UNIVERSITY_TYPE_P.equals(infoReq.getUpdateType())){
			
			if(RegexValidate.isNull(infoReq.getAppNo())){
				return ResultCodes.STU_UPDATE_APPNO_NOTNULL;
			}
			
			if(!RegexValidate.isNumber(infoReq.getAppNo())||!RegexValidate.isLength(infoReq.getAppNo(), 14,14)){
				return ResultCodes.STU_APP_UPDATE_APPNO_FAIL;
			}
			
		}
		/* 真实姓名 */
		if (RegexValidate.isNull(infoReq.getRealName())) {
			return ResultCodes.REGISTER_REALNAME_ISNULL;
		}

		if (!RegexValidate.isRealName(infoReq.getRealName())) {
			return ResultCodes.REGISTER_REALNAME_ILLEGAL;
		}

		/* 身份证号码 */
		if (RegexValidate.isNull(infoReq.getIdentiyNo())) {
			return ResultCodes.REGISTER_IDCARD_ISNULL;
		}

		if (!RegexValidate.isIdCard(infoReq.getIdentiyNo())) {
			return ResultCodes.REGISTER_IDCARD_ILLEGAL;
		}
		
//		if (RegexValidate.isNull(infoReq.getIptStuMajor())) {
//			return ResultCodes.STU_APP_UPDATE_SCHOOL_MAJOR_NOTNULL;
//		}
//		
//		if (!RegexValidate.isOnlyChinese(infoReq.getIptStuMajor())) {
//			return ResultCodes.STU_APP_UPDATE_SCHOOL_MAJOR_FALSE;
//		}
//		
//		if (!RegexValidate.isLength(infoReq.getIptStuMajor(),1,20)) {
//			return ResultCodes.STU_APP_UPDATE_SCHOOL_MAJOR_LENGTH;
//		}

	
		/* 验证固定电话要么输入正确要么为空的固定电话格式 */
		if (!RegexValidate.isNull(infoReq.getHomePhoneArea())
				|| !RegexValidate.isNull(infoReq.getHomePhoneNum())) {
			if (!RegexValidate.isTel(infoReq.getHomePhoneArea(),
					infoReq.getHomePhoneNum())) {
				return ResultCodes.USER_TEL_FALSE;
			}
		}
		/* qq号码 */
		if (RegexValidate.isNull(infoReq.getQqNumber())) {
			return ResultCodes.USER_QQ_NOTNUll;
		}
		if (!RegexValidate.isNull(infoReq.getQqNumber())) {
			if (!RegexValidate.isDigitPos(infoReq.getQqNumber())) {
				return ResultCodes.USER_QQ_FALSE;
			}
		}
		
		/* 借款用途 */
		if(RegexValidate.isNull(infoReq.getLoanUse())){
			return ResultCodes.CREATEAPPPAY_LOANUSE_ISNULL;
		}
		
		if(!RegexValidate.isSelect(infoReq.getLoanUse())){
			return ResultCodes.CREATEAPPPAY_LOANUSE_ILLEGAL;
		}
		
		/* 嗨秒贷行业 3000以下（包含）本次不上线 */
//		ApplicationEntity appPay = queryAppPay(infoReq.getAppNo());
//		if(appPay != null){
//			
//			if(RegexValidate.isIndustryHmd(appPay.getIndustryCode()) && new BigDecimal(appPay.getApplyAmount()).compareTo(new BigDecimal(Consts.HMD_PRICE_BURST)) <= 0){
//				
				/* 民族 */
				if(RegexValidate.isNull(infoReq.getNation())){
					return ResultCodes.USERUPDATEINFO_NATION_ISNULL;
				}
				
				if(!RegexValidate.isDescFlag(infoReq.getNation(),20)){
					return ResultCodes.USERUPDATEINFO_NATION_ILLEGAL;
				}
				
				/* 身份证有效期限 */
				if(RegexValidate.isNull(infoReq.getIdCardValStartDate()) || RegexValidate.isNull(infoReq.getIdCardValEndDate())){
					return ResultCodes.USERUPDATEINFO_IDCARDVAL_ISNULL;
				}
				
				if(!RegexValidate.isDate(infoReq.getIdCardValStartDate()) || !RegexValidate.isDate(infoReq.getIdCardValEndDate())){
					return ResultCodes.USERUPDATEINFO_IDCARDVAL_ILLEGAL;
				}
				
				/* 身份证有效期 */
				if(RegexValidate.isNull(infoReq.getIdCardValidity())){
					return ResultCodes.USERUPDATEINFO_IDCARDTIME_ISNULL;
				}
				
				if(!RegexValidate.isSelect(infoReq.getIdCardValidity())){
					return ResultCodes.USERUPDATEINFO_IDCARDTIME_ILLEGAL;
				}
//				
				
				if (RegexValidate.isNull(infoReq.getSchoolProvince())
						|| RegexValidate.isNull(infoReq.getSchoolCity())
						|| RegexValidate.isNull(infoReq.getSchoolDistrict())) {
					return ResultCodes.STU_APP_SCHOOL_AREA_NOTNULL;
				}
				
				if(!RegexValidate.isSelectToAddress(infoReq.getSchoolProvince())){
						return ResultCodes.STU_APP_SCHOOL_PROVINCE_FALSE;
				}
				
				if(!RegexValidate.isSelectToAddress(infoReq.getSchoolCity())){
					return ResultCodes.STU_APP_SCHOOL_CITY_FALSE;
				}
				
				if(!RegexValidate.isSelectToAddress(infoReq.getSchoolDistrict())){
					return ResultCodes.STU_CONTACT_SCHOOL_AREA_FALSE;
				}
				
				/* 验证学校详细地址 */
				if (RegexValidate.isNull(infoReq.getSchoolDetails())) {
					return ResultCodes.STU_APP_SCHOOL_AREA_DETAIL_NOTNULL;
				}
				
				if (!RegexValidate.isStrFilter(infoReq.getSchoolDetails())) {
					return ResultCodes.STU_APP_SCHOOL_AREA_DETAIL_FALSE;
				}
				
				if (!RegexValidate.isLength(infoReq.getSchoolDetails(),0,50)) {
					return ResultCodes.STU_APP_SCHOOL_AREA_DETAIL_LENGTH;
				}

				if (RegexValidate.isNull(infoReq.getHomeProvince())
						|| RegexValidate.isNull(infoReq.getHomeCity())
						|| RegexValidate.isNull(infoReq.getHomeDistrict())) {
					return ResultCodes.STU_APP_HOME_AREA_NOTNULL;
				}
				
				if(!RegexValidate.isSelectToAddress(infoReq.getHomeProvince())){
					return ResultCodes.STU_APP_CONTACT_HOME_PROVINCE_FALSE;
				}
				
				if(!RegexValidate.isSelectToAddress(infoReq.getHomeCity())){
					return ResultCodes.STU_APP_CONTACT_HOME_CITY_FALSE;
				}
				
				if(!RegexValidate.isSelectToAddress(infoReq.getHomeDistrict())){
					return ResultCodes.STU_APP_CONTACT_HOME_AREA_FALSE;
				}

				if (RegexValidate.isNull(infoReq.getHomeDetails())) {
					return ResultCodes.STU_APP_HOME_AREA_DETAIL_NOTNULL;
				}
				
				if (!RegexValidate.isStrFilter(infoReq.getHomeDetails())) {
					return ResultCodes.STU_APP_HOME_AREA_DETAIL_FALSE;
				}
				
				if (!RegexValidate.isLength(infoReq.getHomeDetails(),0,50)) {
					return ResultCodes.STU_APP_HOME_AREA_DETAIL_LENGTH;
				}
				if(RegexValidate.isExpressType(infoReq.getExpressAddressType()))
				{
					return ResultCodes.STU_APP_EXPRESSL_ADDRESSTYPE_FALSE;
				}
		    //如果是其他地址
				if(infoReq.getExpressAddressType().equals(Consts.OTHER_CODE))
				{

					if (RegexValidate.isNull(infoReq.getExpressProvince())
							|| RegexValidate.isNull(infoReq.getExpressCity())
							|| RegexValidate.isNull(infoReq.getExpressDistrict())) {
						return ResultCodes.STU_APP_EXPRESS_AREANOTNULL;
					}
					
					if(!RegexValidate.isSelectToAddress(infoReq.getExpressProvince())){
							return ResultCodes.STU_APP_EXPRESS_PROVINCE_FALSE;
					}
					
					if(!RegexValidate.isSelectToAddress(infoReq.getExpressCity())){
						return ResultCodes.STU_APP_EXPRESS_CITY_FALSE;
					}
					
					if(!RegexValidate.isSelectToAddress(infoReq.getExpressDistrict())){
						return ResultCodes.STU_APP_EXPRESSL_AREA_FALSE;
					}
					
					/* 验证学校详细地址 */
					if (RegexValidate.isNull(infoReq.getExpressDetails())) {
						return ResultCodes.STU_APP_EXPRESSL_AREA_DETAIL_NOTNULL;
					}
					
					if (!RegexValidate.isStrFilter(infoReq.getExpressDetails())) {
						return ResultCodes.STU_APP_EXPRESSL_AREA_DETAIL_FALSE;
					}
					
					if (!RegexValidate.isLength(infoReq.getExpressDetails(),0,50)) {
						return ResultCodes.STU_APP_EXPRESSL_AREA_DETAIL_LENGTH;
					}
					
				}
				
				
				
//			}
//			
//		}
		
		return ResultCodes.NORMAL;
	}
	
	
	
	
	public NewStuApp1UpdateReq getInfoReq() {
		return infoReq;
	}

	public void setInfoReq(NewStuApp1UpdateReq infoReq) {
		this.infoReq = infoReq;
	}

	private ApplicationEntity queryAppPay(String appNo){
		
		ApplicationQuery appQuery = new ApplicationQuery();
		return appQuery.queryAppPayByAppNO(appNo);
	}
	
	
}
