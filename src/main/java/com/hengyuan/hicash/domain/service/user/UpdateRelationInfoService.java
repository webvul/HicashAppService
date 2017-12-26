package com.hengyuan.hicash.domain.service.user;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.event.user.UpdateRelationInfoEvent;
import com.hengyuan.hicash.domain.query.user.CustUserQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.user.CustUserEntity;
import com.hengyuan.hicash.exception.UpdateCustomerException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.RelationInfoReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.RelationInfoResp;
import com.hengyuan.hicash.utils.RegexValidate;

/**
 * 
 * @author fish
 *
 * @date 2017年1月11日 上午10:38:36
 */
public class UpdateRelationInfoService implements RespService {

	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		UpdateRelationInfoEvent event = new UpdateRelationInfoEvent();

		RelationInfoReq req = (RelationInfoReq) parmRequest;
		RelationInfoResp resp = new RelationInfoResp();
		 CustUserEntity custUser = queryCust(req.getUserName());
		 if(custUser != null){
			 req.setMobile(custUser.getMobileNo());
		 }
		try {
			String result = validate(req);
			if (result.equals(ResultCodes.NORMAL)) {
				event.updateCustomerRelationInfo(req);
				resp.setResultCode(ResultCodes.NORMAL);
			} else {
				resp.setResultCode(result);
			}
		} catch (UpdateCustomerException e) {
			resp.setResultCode(ResultCodes.UPDATE_CUSTOMER_EXCEPTION);
		}
		resp.setUuid(req.getUuid());
		return resp;
	}
	
	public CustUserEntity queryCust(String username){
			
			CustUserQuery custQuery = new CustUserQuery();
			
			return custQuery.queryByUserName(username);
		}
		
	
	
	public String validate(RelationInfoReq req) {

		// 验证UUID
		if (RegexValidate.isNull(req.getUuid())) {
			return ResultCodes.UUIDNULL;
		}

		// 验证用户名
		if (RegexValidate.isNull(req.getUserName())) {
			return ResultCodes.USER_ERROR_ISNULL;
		}

		// 验证直系亲属姓名
		if (RegexValidate.isNull(req.getImmediate_name())) {
			return ResultCodes.STU_APP_FAMILY_NAME_NOTNULL;
		}

		// 验证直系亲属关系
		if (RegexValidate.isNull(req.getImmediate_relation())) {
			return ResultCodes.STU_APP_FAMILY_RELATION_NOTNULL;
		}

		// 验证直系亲属手机号
		if (RegexValidate.isNull(req.getImmediate_mobile())) {
			return ResultCodes.STU_APP_FAMILY_PHONE_NOTNULL;
		}
		if(!RegexValidate.isIphon(req.getImmediate_mobile())){
			return ResultCodes.SENDAMOUNTCODE_MOBILE_FAIL;
		}
		

		// 验证紧急联系人姓名
		if (RegexValidate.isNull(req.getEmergency_name())) {
			return ResultCodes.STU_APP_CONTACT_NAME_NOTNULL;
		}

		// 验证紧急联系人关系
		if (RegexValidate.isNull(req.getEmergency_relation())) {
			return ResultCodes.STU_APP_CONTACT_RELATION_NOTNULL;
		}

		// 验证紧急联系人手机号
		if (RegexValidate.isNull(req.getEmergency_mobile())) {
			return ResultCodes.STU_APP_CONTACT_PHONE_NOTNULL;
		}
		if(!RegexValidate.isIphon(req.getEmergency_mobile())){
			return ResultCodes.SENDAMOUNTCODE_MOBILE_FAIL;
		}

		
		// 验证婚姻状况
		if (RegexValidate.isNull(req.getMarital_status())) {
			return ResultCodes.COLLAR_INFO_MATRIMONY_NOTNULL;
		}
		//验证手机号
		if (req.getMobile().equals(req.getImmediate_mobile())
			||req.getMobile().equals(req.getEmergency_mobile())
			||req.getImmediate_mobile().equals(req.getEmergency_mobile())
			
				) {
			return ResultCodes.SENDSUPPLIERCODE_MOBILE_EXIST;
		}
		
		
		/* 2017年3月24日 14:20:13扩展滴滴司机贷，不做配偶信息验证*/
		// 如果已婚, 需要验证配偶姓名和手机号，
		if (req.getMarital_status().equals(Consts.HUNYIN_Q001)) {
			if (!RegexValidate.isNull(req.getSpouse_name())) {
				if(req.getSpouse_name().equals(req.getImmediate_name())||req.getSpouse_name().equals(req.getEmergency_name())){
					return  ResultCodes.COLLAR_INFO_RE;
				}
			}
			
			if (!RegexValidate.isNull(req.getSpouse_mobile())) {
				if(req.getSpouse_mobile().equals(req.getImmediate_mobile())
				||req.getSpouse_mobile().equals(req.getEmergency_mobile())
				||req.getSpouse_mobile().equals(req.getMobile())
						){
					return  ResultCodes.SENDSUPPLIERCODE_MOBILE_EXIST;
				}
			}
		}
		
			
		return ResultCodes.NORMAL;
	}

}
