package com.hengyuan.hicash.service.validate.update;

import java.math.BigDecimal;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.query.app.ApplicationQuery;
import com.hengyuan.hicash.entity.app.ApplicationEntity;
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
public class StuApp1UpdateVal {
	
	private StuApp1UpdateReq infoReq;
	
	public StuApp1UpdateVal(StuApp1UpdateReq infoReq){
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
	
		/* 验证学校 */
		if(RegexValidate.isNull(infoReq.getSchoolId())){
			return ResultCodes.STU_APP_UPDATE_SCHOOL_NAME_NOTNULL;
		}
		
		if(!RegexValidate.isNumber(infoReq.getSchoolId())){
			return ResultCodes.STU_APP_UPDATE_SCHOOL_NAME_FALSE;
		}
		
		if(!RegexValidate.isLength(infoReq.getSchoolId(),1,10)){
			return ResultCodes.STU_APP_UPDATE_SCHOOL_NAME_LENGTH;
		}
		/* 验证学号 */
		if (RegexValidate.isNull(infoReq.getIptStuId())) {
			return ResultCodes.STU_APP_UPDATE_SCHOOL_STUID_NOTNULL;
		}
		
		if (!RegexValidate.isNumOrLetter(infoReq.getIptStuId())) {
			return ResultCodes.STU_APP_UPDATE_SCHOOL_STUID_FALSE;
		}
		
		if (!RegexValidate.isLength(infoReq.getIptStuId(),0,18)) {
			return ResultCodes.STU_APP_UPDATE_SCHOOL_STUID_LENGTH;
		}
		
		if(RegexValidate.isNull(infoReq.getEducationBk())){
			return ResultCodes.STU_APP_UPDATE_SCHOOL_EDUCATION_NOTNULL;
		}
		
		if(!RegexValidate.isSelect(infoReq.getEducationBk())){
			return ResultCodes.STU_APP_UPDATE_SCHOOL_EDUCATION_LENGTH;
		}
		

		if (RegexValidate.isNull(infoReq.getIptStuDepartment())) {
			return ResultCodes.STU_APP_UPDATE_SCHOOL_DPM_NOTNULL;
		}
		
		if (!RegexValidate.isOnlyChinese(infoReq.getIptStuDepartment())) {
			return ResultCodes.STU_APP_UPDATE_SCHOOL_DPM_FALSE;
		}
		
		if (!RegexValidate.isLength(infoReq.getIptStuDepartment(),1,20)) {
			return ResultCodes.STU_APP_UPDATE_SCHOOL_DPM_LENGTH;
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

		if(RegexValidate.isNull(infoReq.getStudyTimeYear())||RegexValidate.isNull(infoReq.getStudyTimeMonth())){
			return ResultCodes.STU_APP_UPDATE_SCHOOL_STUDYTIME_NOTNULL;
		}
		
		if(!RegexValidate.isYear(infoReq.getStudyTimeYear().trim())){
			return ResultCodes.STU_APP_UPDATE_SCHOOL_DATE_YEAR_ILLEGAL;
		}
		
		if(!RegexValidate.isMonth(infoReq.getStudyTimeMonth().trim())){
			return ResultCodes.STU_APP_UPDATE_SCHOOL_DATE_MONTH_ILLEGAL;
		}
		
		if(RegexValidate.isNull(infoReq.getSltGrade())){
			return ResultCodes.STU_APP_UPDATE_SCHOOL_GRADE_NOTNULL;
		}
		
		if(!RegexValidate.isSelect(infoReq.getSltGrade())){
			return ResultCodes.STU_APP_UPDATE_SCHOOL_GRADE_LENGTH;
		}

		if(RegexValidate.isNull(infoReq.getSchoolSystem())){
			return ResultCodes.STU_APP_UPDATE_SCHOOL_SYSTEM_NOTNULL;
		}
		
		if(!RegexValidate.isSelect(infoReq.getSchoolSystem())){
			return ResultCodes.STU_APP_UPDATE_SCHOOL_SYSTEM_LENGTH;
		}
		
		/* 学生类型 */
		if (RegexValidate.isNull(infoReq.getStuType())) {
			return ResultCodes.STU_APP_UPDATE_STUTYPE_NOTNULL;
		}
		
		if(RegexValidate.isStuType(infoReq.getStuType())){
			return ResultCodes.STU_APP_UPDATE_STUTYPE_FAIL;
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
//			}
//			
//		}
		
		return ResultCodes.NORMAL;
	}
	
	public StuApp1UpdateReq getInfoReq() {
		return infoReq;
	}

	public void setInfoReq(StuApp1UpdateReq infoReq) {
		this.infoReq = infoReq;
	}
	
	
	private ApplicationEntity queryAppPay(String appNo){
		
		ApplicationQuery appQuery = new ApplicationQuery();
		return appQuery.queryAppPayByAppNO(appNo);
	}
	
	
}
