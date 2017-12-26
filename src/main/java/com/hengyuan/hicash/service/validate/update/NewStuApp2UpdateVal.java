package com.hengyuan.hicash.service.validate.update;

import java.math.BigDecimal;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.query.app.ApplicationQuery;
import com.hengyuan.hicash.domain.query.param.SystemParamQuery;
import com.hengyuan.hicash.entity.app.ApplicationEntity;
import com.hengyuan.hicash.parameters.request.user.NewStuApp1UpdateReq;
import com.hengyuan.hicash.parameters.request.user.NewStuApp2UpdateReq;
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
public class NewStuApp2UpdateVal {
	
	private NewStuApp2UpdateReq infoReq;
	
	public NewStuApp2UpdateVal(NewStuApp2UpdateReq infoReq){
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
	

		


				
				/* 直系亲属姓名 */
				if(RegexValidate.isNull(infoReq.getFamilyName())){
					
					return ResultCodes.STU_APP_FAMILY_NAME_NOTNULL;
				}
				
				if (!RegexValidate.isRealName(infoReq.getFamilyName())) {

					return ResultCodes.STU_APP_FAMILY_NAME_FALSE;
				}
				
				if (!RegexValidate.isLength(infoReq.getFamilyName(),2,50)) {

					return ResultCodes.STU_APP_FAMILY_NAME_LENGTH;
				}
				
				/* 直系亲属关系 */
				if (RegexValidate.isNull(infoReq.getFamilyRelation())) {

					return ResultCodes.STU_APP_FAMILY_RELATION_NOTNULL;
				}
				
				if (!RegexValidate.isSelect(infoReq.getFamilyRelation())) {

					return ResultCodes.STU_APP_FAMILY_RELATION_FALSE;
				}

				/* 直系亲属手机 */
				if (RegexValidate.isNull(infoReq.getFamilyPhone())) {

					return ResultCodes.STU_APP_FAMILY_PHONE_NOTNULL;
				}
				
				if (!RegexValidate.isIphon(infoReq.getFamilyPhone())) {

					return ResultCodes.STU_APP_FAMILY_PHONE_FALSE;
				}
				/* 直系亲属工作单位 */
				if (RegexValidate.isNull(infoReq.getFamilyWorkUnit())) {

					return ResultCodes.STU_APP_FAMILY_UNIT_NOTNULL;
				}
				
				if (!RegexValidate.isStrFilter(infoReq.getFamilyWorkUnit())) {

					return ResultCodes.STU_APP_FAMILY_UNIT_FALSE;
				}

				/* 直系亲属家庭地址 */
				if (RegexValidate.isNull(infoReq.getFamilyAddress())) {

					return ResultCodes.STU_APP_FAMILY_ADDRESS_NOTNULL;
				}
				
				if (!RegexValidate.isStrFilter(infoReq.getFamilyAddress())) {

					return ResultCodes.STU_APP_FAMILY_ADDRESS_FALSE;
				}
				
				if (!RegexValidate.isLength(infoReq.getFamilyAddress(),1,50)) {

					return ResultCodes.STU_APP_FAMILY_ADDRESS_LENGTH;
				}

				/* 紧急联系人姓名 */
				if (RegexValidate.isNull(infoReq.getContactName())) {

					return ResultCodes.STU_APP_CONTACT_NAME_NOTNULL;
				}
				
				if (!RegexValidate.isRealName(infoReq.getContactName())) {

					return ResultCodes.STU_APP_CONTACT_NAME_FALSE;
				}
				
				if(RegexValidate.isCon(infoReq.getFamilyName(), infoReq.getContactName())){
					
					return ResultCodes.STU_APP_NAME_EQ_FALSE;
					
				}
				
				if (!RegexValidate.isLength(infoReq.getContactName(),1,50)) {

					return ResultCodes.STU_APP_CONTACT_NAME_LENGTH;
				}

				/* 紧急联系人关系 */
				if (RegexValidate.isNull(infoReq.getContactRelation())) {

					return ResultCodes.STU_APP_CONTACT_RELATION_NOTNULL;
				}
				SystemParamQuery paramQuery = new SystemParamQuery();

				if (RegexValidate.isCon(
						paramQuery
								.queryParamByDicCode(infoReq.getFamilyRelation())
								.getDicName().trim(),
						paramQuery.queryParamByDicCode(
								infoReq.getContactRelation()).getDicName())) {

					return ResultCodes.STU_APP_RELATION_EQ_FALSE;
				}
				
				if (!RegexValidate.isSelect(infoReq.getContactRelation())) {

					return ResultCodes.STU_APP_RELATION_FALSE;
				}
				
				/* 紧急联系人手机 */
				if (RegexValidate.isNull(infoReq.getContactPhone())) {

					return ResultCodes.STU_APP_CONTACT_PHONE_NOTNULL;
				}
				
				if (!RegexValidate.isIphon(infoReq.getContactPhone())) {

					return ResultCodes.STU_APP_CONTACT_PHONE_FALSE;
				}
				
				if (RegexValidate.isCon(infoReq.getFamilyPhone(),infoReq.getContactPhone())) {

					return ResultCodes.STU_APP_PHONE_EQ_FALSE;
				}
				
//			}
//			
//		}
		
		return ResultCodes.NORMAL;
	}
	
	
	
	
	private ApplicationEntity queryAppPay(String appNo){
		
		ApplicationQuery appQuery = new ApplicationQuery();
		return appQuery.queryAppPayByAppNO(appNo);
	}

	public NewStuApp2UpdateReq getInfoReq() {
		return infoReq;
	}

	public void setInfoReq(NewStuApp2UpdateReq infoReq) {
		this.infoReq = infoReq;
	}
	
	
	
}
