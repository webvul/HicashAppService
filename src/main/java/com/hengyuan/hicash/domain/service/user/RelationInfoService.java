package com.hengyuan.hicash.domain.service.user;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.query.user.RelationInfoQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.user.CustCustomer;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.RelationInfoReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.RelationInfoResp;
import com.hengyuan.hicash.utils.RegexValidate;

/**
 * 
 * @author fish
 *
 * @date 2017年1月11日 上午9:40:46
 */
public class RelationInfoService implements RespService {

	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		RelationInfoQuery collarPersonInfoQuery = new RelationInfoQuery();

		RelationInfoReq req = (RelationInfoReq) parmRequest;
		RelationInfoResp resp = new RelationInfoResp();

		String result = validate(req);

		if (result.equals(ResultCodes.NORMAL)) {

			CustCustomer bean = collarPersonInfoQuery.queryCustCustomer(req.getUserName());
			if (null != bean) {

				resp.setUserName(bean.getUserName());
				resp.setMarital_status(bean.getMaritalStatus());
				
				resp.setImmediate_name(bean.getImmediateName());
				resp.setImmediate_relation(bean.getImmediateRelation());
				resp.setImmediate_mobile(bean.getImmediateMobile());

				resp.setEmergency_name(bean.getEmergencyName());
				resp.setEmergency_relation(bean.getEmergencyRelation());
				resp.setEmergency_mobile(bean.getEmergencyMobile());

				resp.setSpouse_name(bean.getSpouseName());
				resp.setSpouse_mobile(bean.getSpouseMobile());

				resp.setResultCode(ResultCodes.NORMAL);
			} else {
				resp.setResultCode(ResultCodes.CUSTOMER_NOT_FOUNT);
			}
		} else {
			resp.setResultCode(result);
		}
		return resp;
	}

	public String validate(RelationInfoReq req) {

		// 验证用户名
		if (RegexValidate.isNull(req.getUserName())) {
			return ResultCodes.USER_ERROR_ISNULL;
		}

		return ResultCodes.NORMAL;
	}

}
