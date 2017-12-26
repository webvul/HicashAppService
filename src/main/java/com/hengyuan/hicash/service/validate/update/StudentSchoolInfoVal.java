package com.hengyuan.hicash.service.validate.update;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.StudentSchoolInfoReq;
import com.hengyuan.hicash.utils.RegexValidate;


/**
 * 学生完善资料(修改学校信息)
 * 
 * @author Cary.Liu
 * @create date 2014-08-05
 */
public class StudentSchoolInfoVal {
	
	private StudentSchoolInfoReq infoReq;
	
	public StudentSchoolInfoVal(StudentSchoolInfoReq infoReq){
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
			return ResultCodes.STU_UPDATE_TYPE_NOTNULL;
		}
		
		if (!Consts.UPDATE_UNIVERSITY_TYPE_P.equals(infoReq.getUpdateType())
				&& !Consts.UPDATE_UNIVERSITY_TYPE_U.equals(infoReq
						.getUpdateType())) {
			return ResultCodes.STU_UPDATE_TYPE_FAIL;
		}
		
		if(Consts.UPDATE_UNIVERSITY_TYPE_P.equals(infoReq.getUpdateType())){
			
			if(RegexValidate.isNull(infoReq.getAppNo())){
				return ResultCodes.STU_UPDATE_APPNO_NOTNULL;
			}
			
			if(!RegexValidate.isNumber(infoReq.getAppNo())||!RegexValidate.isLength(infoReq.getAppNo(), 14,14)){
				return ResultCodes.STU_UPDATE_APPNO_FAIL;
			}
			
		}
		
		/* 学生类型 */
		if (RegexValidate.isNull(infoReq.getStuType())) {
			return ResultCodes.STU_UPDATE_STUTYPE_NOTNULL;
		}
		
		if(RegexValidate.isStuType(infoReq.getStuType())){
			return ResultCodes.STU_UPDATE_STUTYPE_FAIL;
		}
		
		/* 验证学校 */
		if(RegexValidate.isNull(infoReq.getSchoolId())){
			return ResultCodes.STU_UPDATE_SCHOOL_NAME_NOTNULL;
		}
		
		if(!RegexValidate.isNumber(infoReq.getSchoolId())){
			return ResultCodes.STU_UPDATE_SCHOOL_NAME_FALSE;
		}
		
		if(!RegexValidate.isLength(infoReq.getSchoolId(),1,10)){
			return ResultCodes.STU_UPDATE_SCHOOL_NAME_LENGTH;
		}
		
		if(RegexValidate.isNull(infoReq.getSchoolSystem())){
			return ResultCodes.STU_UPDATE_SCHOOL_SYSTEM_NOTNULL;
		}
		
		if(!RegexValidate.isSelect(infoReq.getSchoolSystem())){
			return ResultCodes.STU_UPDATE_SCHOOL_SYSTEM_LENGTH;
		}
		
		if(RegexValidate.isNull(infoReq.getEducationBk())){
			return ResultCodes.STU_UPDATE_SCHOOL_EDUCATION_NOTNULL;
		}
		
		if(!RegexValidate.isSelect(infoReq.getEducationBk())){
			return ResultCodes.STU_UPDATE_SCHOOL_EDUCATION_LENGTH;
		}
		
		if(RegexValidate.isNull(infoReq.getStudyTimeYear())||RegexValidate.isNull(infoReq.getStudyTimeMonth())){
			return ResultCodes.STU_UPDATE_SCHOOL_STUDYTIME_NOTNULL;
		}
		
		if(!RegexValidate.isYear(infoReq.getStudyTimeYear().trim())){
			return ResultCodes.STU_UPDATE_SCHOOL_DATE_YEAR_ILLEGAL;
		}
		
		if(!RegexValidate.isMonth(infoReq.getStudyTimeMonth().trim())){
			return ResultCodes.STU_UPDATE_SCHOOL_DATE_MONTH_ILLEGAL;
		}
		
		if(RegexValidate.isNull(infoReq.getSltGrade())){
			return ResultCodes.STU_UPDATE_SCHOOL_GRADE_NOTNULL;
		}
		
		if(!RegexValidate.isSelect(infoReq.getSltGrade())){
			return ResultCodes.STU_UPDATE_SCHOOL_GRADE_LENGTH;
		}
		
		/* 验证学号 */
		if (RegexValidate.isNull(infoReq.getIptStuId())) {
			return ResultCodes.STU_UPDATE_SCHOOL_STUID_NOTNULL;
		}
		
		if (!RegexValidate.isNumOrLetter(infoReq.getIptStuId())) {
			return ResultCodes.STU_UPDATE_SCHOOL_STUID_FALSE;
		}
		
		if (!RegexValidate.isLength(infoReq.getIptStuId(),0,18)) {
			return ResultCodes.STU_UPDATE_SCHOOL_STUID_LENGTH;
		}
		
		if (RegexValidate.isNull(infoReq.getIptStuDepartment())) {
			return ResultCodes.STU_UPDATE_SCHOOL_DPM_NOTNULL;
		}
		
		if (!RegexValidate.isOnlyChinese(infoReq.getIptStuDepartment())) {
			return ResultCodes.STU_UPDATE_SCHOOL_DPM_FALSE;
		}
		
		if (!RegexValidate.isLength(infoReq.getIptStuDepartment(),1,20)) {
			return ResultCodes.STU_UPDATE_SCHOOL_DPM_LENGTH;
		}
		
		if (RegexValidate.isNull(infoReq.getIptStuMajor())) {
			return ResultCodes.STU_UPDATE_SCHOOL_MAJOR_NOTNULL;
		}
		
		if (!RegexValidate.isOnlyChinese(infoReq.getIptStuMajor())) {
			return ResultCodes.STU_UPDATE_SCHOOL_MAJOR_FALSE;
		}
		
		if (!RegexValidate.isLength(infoReq.getIptStuMajor(),1,20)) {
			return ResultCodes.STU_UPDATE_SCHOOL_MAJOR_LENGTH;
		}
		
		
		return ResultCodes.NORMAL;
	}
	
	public StudentSchoolInfoReq getInfoReq() {
		return infoReq;
	}

	public void setInfoReq(StudentSchoolInfoReq infoReq) {
		this.infoReq = infoReq;
	}
	
	
}
