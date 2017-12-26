package com.hengyuan.hicash.domain.service.user;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.event.user.UpdateCustCardEvent;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.exception.UpdateCustomerException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.UpdateCustCardReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.UpdateCustCardResp;
import com.hengyuan.hicash.utils.RegexValidate;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 
 * @author fish
 *
 * @date 2017年1月15日 下午2:12:26
 */
public class UpdateCustCardService implements RespService {

	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		UpdateCustCardEvent event = new UpdateCustCardEvent();

		UpdateCustCardReq req = (UpdateCustCardReq) parmRequest;
		UpdateCustCardResp resp = new UpdateCustCardResp();

		try {
			String result = validate(req);
			if (result.equals(ResultCodes.NORMAL)) {
				event.updateCustomerPersonInfo(req);
			}
			resp.setResultCode(result);
		} catch (UpdateCustomerException e) {
			resp.setResultCode(ResultCodes.UPDATE_CUSTOMER_EXCEPTION);
		}
		resp.setUuid(req.getUuid());
		return resp;
	}

	public String validate(UpdateCustCardReq req) {

		// 验证用户名
		if (RegexValidate.isNull(req.getUserName())) {
			return ResultCodes.USER_ERROR_ISNULL;
		}

		// 验证省
		if (RegexValidate.isNull(req.getPermanentAddressProvince())) {
			return ResultCodes.UPDATELANUSERINFO_CITY_ISNULL;
		}

		// 验证市
		if (RegexValidate.isNull(req.getPermanentAddressCity())) {
			return ResultCodes.UPDATELANUSERINFO_CITY_ISNULL;
		}

		// 验证区
		if (RegexValidate.isNull(req.getPermanentAddressArea())) {
			return ResultCodes.UPDATELANUSERINFO_CITY_ISNULL;
		}

		// 验证街道
		if (RegexValidate.isNull(req.getPermanentAddressRaod())) {
			return ResultCodes.UPDATELANUSERINFO_CITY_ISNULL;
		}

		// 验证民族
		if (RegexValidate.isNull(req.getNation())) {
			return ResultCodes.USERUPDATEINFO_NATION_ISNULL;
		}

		// 验证有效期起
		if (RegexValidate.isNull(req.getIdCardValStartDate())) {
			return ResultCodes.USERUPDATEINFO_IDCARDVAL_ISNULL;
		}

		// 验证有效期止
		if (RegexValidate.isNull(req.getIdCardValEndDate())) {
			return ResultCodes.USERUPDATEINFO_IDCARDVAL_ISNULL;
		}

		// 验证真实姓名
		if (RegexValidate.isNull(req.getName())) {
			return ResultCodes.REGISTER_REALNAME_ISNULL;
		}

		// 验证身份证号
		if (RegexValidate.isNull(req.getIdentityNo())) {
			return ResultCodes.BLACK_CERTNO_UPDATE_NOTNULL;
		}
		if (!RegexValidate.isIdCard(req.getIdentityNo())) {
			return ResultCodes.REGISTER_IDCARD_ILLEGAL;
		}

		// 验证签发机关
		if (RegexValidate.isNull(req.getIdcardFrom())) {
			return ResultCodes.IDCARDFROM_ISNULL;
		}
		//验证行业
		if (!RegexValidate.isNull(req.getHyIndustryCode())) {
			String sex=StringUtils.getGenderByIdCard(req.getIdentityNo());
			if(!"女".equals(sex)){
				return ResultCodes.HINS_SEX_IS_GIRL;
			}
			
		}

		return ResultCodes.NORMAL;
	}
}
