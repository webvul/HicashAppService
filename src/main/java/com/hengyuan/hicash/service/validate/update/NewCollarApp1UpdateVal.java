package com.hengyuan.hicash.service.validate.update;

import java.math.BigDecimal;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.query.app.ApplicationQuery;
import com.hengyuan.hicash.entity.app.ApplicationEntity;
import com.hengyuan.hicash.parameters.request.user.CollarApp1UpdateReq;
import com.hengyuan.hicash.parameters.request.user.NewCollarApp1UpdateReq;
import com.hengyuan.hicash.utils.RegexValidate;

/**
 * hicash手机端白领提现申请1完善参数验证
 * 
 * @author lihua.Ren
 * @create date 2015-06-17
 *
 */
public class NewCollarApp1UpdateVal {

	private NewCollarApp1UpdateReq infoReq;

	public NewCollarApp1UpdateVal(NewCollarApp1UpdateReq infoReq) {
		this.infoReq = infoReq;
	}

	public String validate() {

		/* 验证uuid */
		if (RegexValidate.isNull(infoReq.getUuid())) {
			return ResultCodes.UUIDNULL;
		}

		if (!RegexValidate.isUuid(infoReq.getUuid())) {
			return ResultCodes.UUIDILLEGAL;
		}

		/* 验证用户名 */
		if (RegexValidate.isNull(infoReq.getUserName())) {
			return ResultCodes.USER_ERROR_ISNULL;
		}

		if (!RegexValidate.isUsername(infoReq.getUserName())) {
			return ResultCodes.USER_NAME_ERROR_CANTCHAR;
		}

		/* 资料的修改类型 */
		if (RegexValidate.isNull(infoReq.getUpdateType())) {
			return ResultCodes.COLLAR_UPDATE_TYPE_NOTNULL;
		}

		if (!Consts.UPDATE_UNIVERSITY_TYPE_P.equals(infoReq.getUpdateType())
				&& !Consts.UPDATE_UNIVERSITY_TYPE_U.equals(infoReq
						.getUpdateType())) {
			return ResultCodes.COLLAR_UPDATE_TYPE_FALSE;
		}

		if (Consts.UPDATE_UNIVERSITY_TYPE_P.equals(infoReq.getUpdateType())) {

			if (RegexValidate.isNull(infoReq.getAppNo())) {
				return ResultCodes.COLLAR_UPDATE_APPNO_NOTNULL;
			}

			if (!RegexValidate.isNumber(infoReq.getAppNo())
					|| !RegexValidate.isLength(infoReq.getAppNo(), 14, 14)) {
				return ResultCodes.COLLAR_UPDATE_APPNO_FALSE;
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

		if (RegexValidate.isNull(infoReq.getMatrimonySystem())) {

			return ResultCodes.COLLAR_INFO_MATRIMONY_NOTNULL;
		}

		if (!RegexValidate.isSelect(infoReq.getMatrimonySystem())) {

			return ResultCodes.COLLAR_INFO_MATRIMONY_FALSE;
		}
		
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
		
		
		ApplicationEntity appPay = queryAppPay(infoReq.getAppNo());
		
		if(appPay != null && !RegexValidate.isIndustryLlsm(appPay.getIndustryCode())){
			
			/* 借款用途 */
			if(RegexValidate.isNull(infoReq.getLoanUse())){
				return ResultCodes.CREATEAPPPAY_LOANUSE_ISNULL;
			}
			
			if(!RegexValidate.isSelect(infoReq.getLoanUse())){
				return ResultCodes.CREATEAPPPAY_LOANUSE_ILLEGAL;
			}
			
		}
		
		/* 嗨秒贷行业 3000以下（包含） */
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
//			}
//			
//		}
		
				
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
				
				
				if(RegexValidate.isNull(infoReq.getMonthlyConsumption())){
					return ResultCodes.COLLAR_MONTH_CONSUMPTION_NOTNULL;
				}
				
				/* 月消费 */
				if (!RegexValidate.isDigitFloatPos(infoReq.getMonthlyConsumption())) {
					return ResultCodes.COLLAR_MONTH_CONSUMPTION_EXCEPTION;
				}
				
				if (!RegexValidate.isLength(infoReq.getMonthlyConsumption(),1,10)) {
					return ResultCodes.COLLAR_MONTH_CONSUMPTION_LENGTH;
				}
				
				
		return ResultCodes.NORMAL;
	}

	
	
	public NewCollarApp1UpdateReq getInfoReq() {
		return infoReq;
	}

	public void setInfoReq(NewCollarApp1UpdateReq infoReq) {
		this.infoReq = infoReq;
	}

	private ApplicationEntity queryAppPay(String appNo){
		
		ApplicationQuery appQuery = new ApplicationQuery();
		return appQuery.queryAppPayByAppNO(appNo);
	}

}
