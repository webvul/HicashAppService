package com.hengyuan.hicash.service.validate.update;

import java.math.BigDecimal;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.query.app.ApplicationQuery;
import com.hengyuan.hicash.entity.app.ApplicationEntity;
import com.hengyuan.hicash.parameters.request.user.CollarApp1UpdateReq;
import com.hengyuan.hicash.utils.RegexValidate;

/**
 * hicash手机端白领提现申请1完善参数验证
 * 
 * @author lihua.Ren
 * @create date 2015-06-17
 *
 */
public class CollarApp1UpdateVal {

	private CollarApp1UpdateReq infoReq;

	public CollarApp1UpdateVal(CollarApp1UpdateReq infoReq) {
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

		if (RegexValidate.isNull(infoReq.getMatrimonySystem())) {

			return ResultCodes.COLLAR_INFO_MATRIMONY_NOTNULL;
		}

		if (!RegexValidate.isSelect(infoReq.getMatrimonySystem())) {

			return ResultCodes.COLLAR_INFO_MATRIMONY_FALSE;
		}
		/* 已婚信息 */
		if (Consts.HUNYIN_Q001.equals(infoReq.getMatrimonySystem())) {

			/* 配偶姓名 */
			if (RegexValidate.isNull(infoReq.getSpouseName())) {
				return ResultCodes.COLLAR_INFO_SPOUSENAME_ISNULL;
			}

			if (!RegexValidate.isOnlyChinese(infoReq.getSpouseName())
					|| !RegexValidate.isLength(infoReq.getSpouseName(), 1, 50)) {
				return ResultCodes.COLLAR_INFO_SPOUSENAME_ILLEGAL;
			}

			/* 配偶手机 */
			if (RegexValidate.isNull(infoReq.getSpouseMobile())) {
				return ResultCodes.COLLAR_INFO_MOBILE_ISNULL;
			}

			if (!RegexValidate.isIphon(infoReq.getSpouseMobile())) {
				return ResultCodes.COLLAR_INFO_MOBILE_ILLEGAL;
			}

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
		/* 毕业院校 */
		if (RegexValidate.isNull(infoReq.getSchoolName())) {

			return ResultCodes.COLLAR_INFO_SCHOOLNAME_NOTNULL;
		}

		if (!RegexValidate.isOnlyChinese(infoReq.getSchoolName())) {

			return ResultCodes.COLLAR_INFO_SCHOOLNAME_FALSE;
		}

		if (!RegexValidate.isLength(infoReq.getSchoolName(), 1, 50)) {

			return ResultCodes.COLLAR_INFO_SCHOOLNAME_LENGTH;
		}

		/* 学制 */
		if (RegexValidate.isNull(infoReq.getEducationalSystem())) {

			return ResultCodes.COLLAR_INFO_SYSTEM_NOTNULL;
		}

		if (!RegexValidate.isSelect(infoReq.getEducationalSystem())) {

			return ResultCodes.COLLAR_INFO_SYSTEM_FALSE;
		}
		/* 最高学历 */
		if (RegexValidate.isNull(infoReq.getEducationBg())) {

			return ResultCodes.COLLAR_INFO_EDUCATION_NOTNULL;
		}
		if (!RegexValidate.isSelect(infoReq.getEducationBg())) {
			return ResultCodes.COLLAR_UPDATE_SCHOOL_EDUCATION_LENGTH;
		}
		/* 毕业时间 */
		if (RegexValidate.isNull(infoReq.getEndDateYear())
				|| RegexValidate.isNull(infoReq.getEndDateMonth())) {

			return ResultCodes.COLLAR_INFO_SCHOOLENDTIME_NOTNULL;
		}

		if (!RegexValidate.isYear(infoReq.getEndDateYear().trim())) {
			return ResultCodes.CUST_REGISTINFO_ENDYEAR_FAIL;
		}

		if (!RegexValidate.isMonth(infoReq.getEndDateMonth().trim())) {
			return ResultCodes.CUST_REGISTINFO_ENDMON_FAIL;
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
		
		return ResultCodes.NORMAL;
	}

	public CollarApp1UpdateReq getInfoReq() {
		return infoReq;
	}

	public void setInfoReq(CollarApp1UpdateReq infoReq) {
		this.infoReq = infoReq;
	}
	
	private ApplicationEntity queryAppPay(String appNo){
		
		ApplicationQuery appQuery = new ApplicationQuery();
		return appQuery.queryAppPayByAppNO(appNo);
	}

}
