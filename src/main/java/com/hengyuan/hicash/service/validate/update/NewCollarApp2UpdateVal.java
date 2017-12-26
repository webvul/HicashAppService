package com.hengyuan.hicash.service.validate.update;

import java.math.BigDecimal;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.query.app.ApplicationQuery;
import com.hengyuan.hicash.entity.app.ApplicationEntity;
import com.hengyuan.hicash.parameters.request.user.CollarApp1UpdateReq;
import com.hengyuan.hicash.parameters.request.user.NewCollarApp2UpdateReq;
import com.hengyuan.hicash.utils.RegexValidate;

/**
 * hicash手机端白领提现申请1完善参数验证
 * 
 * @author lihua.Ren
 * @create date 2015-06-17
 *
 */
public class NewCollarApp2UpdateVal {

	private NewCollarApp2UpdateReq infoReq;

	public NewCollarApp2UpdateVal(NewCollarApp2UpdateReq infoReq) {
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

		
		
		
	
		
				/* 单位名称 */
				if(RegexValidate.isNull(infoReq.getUnitName())){
				
					return ResultCodes.COLLAR_UNIT_NAME_NOTNULL;
				}
				
				if(!RegexValidate.isStrFilter(infoReq.getUnitName())){
					
					return ResultCodes.COLLAR_UNIT_NAME_FALSE;
				}
				
				if (!RegexValidate.isLength(infoReq.getUnitName(),1,50)) {

					return ResultCodes.COLLAR_UNIT_NAME_LENGTH;
				}
				/* 入职部门 */
				if(RegexValidate.isNull(infoReq.getInDepartment())){
					
					return ResultCodes.COLLAR_UNIT_DPM_NOTNULL;
				}
				
				if (!RegexValidate.isStrFilter(infoReq.getInDepartment())) {

					return ResultCodes.COLLAR_UNIT_DPM_FALSE;
				}
				
				if (!RegexValidate.isLength(infoReq.getInDepartment(),1,10)) {

					return ResultCodes.COLLAR_UNIT_DPM_LENGTH;
				}
				
				
				
				/*工作年限*/
				if(RegexValidate.isNull(infoReq.getWorkDate())){
					
					return ResultCodes.COLLAR_UNIT_WORKTIME_NOTNULL;
				}
				/*入职时间*/
				if(RegexValidate.isNull(infoReq.getUnitStartDateYear())||RegexValidate.isNull(infoReq.getUnitEndDateMonth())){
					
					return ResultCodes.COLLAR_UNIT_STARTDATE_NOTNULL;
				}
				
				if(!RegexValidate.isYear(infoReq.getUnitStartDateYear().trim())){
					return ResultCodes.COLLAR_UNIT_STARTDATE_YEAR_ILLEGAL;
				}
				
				if(!RegexValidate.isMonth(infoReq.getUnitEndDateMonth().trim())){
					return ResultCodes.COLLAR_UNIT_STARTDATE_MONTH_ILLEGAL;
				}
				
				/* 单位地址 */
				if (RegexValidate.isNull(infoReq.getUnitProvince())
						|| RegexValidate.isNull(infoReq.getUnitCity())
						|| RegexValidate.isNull(infoReq.getUnitDistrict())) {
					return ResultCodes.COLLAR_CONTACT_UNIT_AREA_NOTNULL;
				}
				
				if(!RegexValidate.isSelectToAddress(infoReq.getUnitProvince())){
					return ResultCodes.COLLAR_CONTACT_UNIT_PROVINCE_FALSE;
				}
				
				if(!RegexValidate.isSelectToAddress(infoReq.getUnitCity())){
					return ResultCodes.COLLAR_CONTACT_UNIT_CITY_FALSE;
				}
				
				if(!RegexValidate.isSelectToAddress(infoReq.getUnitDistrict())){
					return ResultCodes.COLLAR_CONTACT_UNIT_AREA_FALSE;
				}
				
				/* 单位详细地址 */
				if (RegexValidate.isNull(infoReq.getUnitDetails())) {
					return ResultCodes.COLLAR_CONTACT_UNIT_DETAIL_NOTNUL;
				}
				
				if (!RegexValidate.isStrFilter(infoReq.getUnitDetails())) {
					return ResultCodes.COLLAR_CONTACT_UNIT_DETAIL_FALSE;
				}
				
				if (!RegexValidate.isLength(infoReq.getUnitDetails(),1,50)) {
					return ResultCodes.COLLAR_CONTACT_UNIT_DETAIL_LENGTH;
				}
				
				if(RegexValidate.isExpressTypeCollar(infoReq.getExpressAddressType()))
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
				/* 验证固定电话要么输入正确的固定电话格式 */
				if(!RegexValidate.isNull(infoReq.getUnitPhoneArea()) || !RegexValidate.isNull(infoReq.getUnitPhoneNum())){
					if(!RegexValidate.isTel(infoReq.getUnitPhoneArea(), infoReq.getUnitPhoneNum())){
						return ResultCodes.COLLAR_UNIT_TEL_ILLEGAL;
					}
				}
				/* 当前月收入 */
				if(RegexValidate.isNull(infoReq.getMonthlyIncome())){
					return ResultCodes.COLLAR_MONTH_INCOME_NOTNULL;
				}
				
				if (!RegexValidate.isDigitFloatPos(infoReq.getMonthlyIncome())) {
					return ResultCodes.COLLAR_MONTH_INCOME_EXCEPTION;
				}
				
				if (!RegexValidate.isLength(infoReq.getMonthlyIncome(),1,10)) {
					return ResultCodes.COLLAR_MONTH_INCOME_LENGTH;
				}
				
				
				/* 职位 */
				if (RegexValidate.isNull(infoReq.getJobduties())) {
					return ResultCodes.COLLAR_JOB_ISNULL;
				}
				
				/* 单位规模 */
				if (RegexValidate.isNull(infoReq.getCompanyScale())) {
					return ResultCodes.COLLAR_SCALE_ISNULL;
				}
				
				/* 单位工作年限 */
				if (RegexValidate.isNull(infoReq.getCompanyWorkYear())) {
					return ResultCodes.COLLAR_WORKYEAR_ISNULL;
				}
				
		return ResultCodes.NORMAL;
	}

	
	
	public NewCollarApp2UpdateReq getInfoReq() {
		return infoReq;
	}

	public void setInfoReq(NewCollarApp2UpdateReq infoReq) {
		this.infoReq = infoReq;
	}

	private ApplicationEntity queryAppPay(String appNo){
		
		ApplicationQuery appQuery = new ApplicationQuery();
		return appQuery.queryAppPayByAppNO(appNo);
	}

}
