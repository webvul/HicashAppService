package com.hengyuan.hicash.service.validate.update;

import java.math.BigDecimal;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.query.app.ApplicationQuery;
import com.hengyuan.hicash.entity.app.ApplicationEntity;
import com.hengyuan.hicash.parameters.request.user.CollarApp1UpdateReq;
import com.hengyuan.hicash.parameters.request.user.NewCollarApp3UpdateReq;
import com.hengyuan.hicash.utils.RegexValidate;

/**
 * hicash手机端白领提现申请1完善参数验证
 * 
 * @author lihua.Ren
 * @create date 2015-06-17
 *
 */
public class NewCollarApp3UpdateVal {

	private NewCollarApp3UpdateReq infoReq;

	public NewCollarApp3UpdateVal(NewCollarApp3UpdateReq infoReq) {
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
		
		
				
				
			
				
		return ResultCodes.NORMAL;
	}

	
	
	public NewCollarApp3UpdateReq getInfoReq() {
		return infoReq;
	}

	public void setInfoReq(NewCollarApp3UpdateReq infoReq) {
		this.infoReq = infoReq;
	}

	private ApplicationEntity queryAppPay(String appNo){
		
		ApplicationQuery appQuery = new ApplicationQuery();
		return appQuery.queryAppPayByAppNO(appNo);
	}

}
