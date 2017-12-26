package com.hengyuan.hicash.domain.service.user;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.query.param.AreaQuery;
import com.hengyuan.hicash.domain.query.param.CityQuery;
import com.hengyuan.hicash.domain.query.param.ProvinceQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.user.CustCustomer;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.CollarPersonReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.CollarPersonResp;
import com.hengyuan.hicash.utils.RegexValidate;

/**
 * 
 * @author fish
 *
 * @date 2017年1月9日 下午7:03:09
 */
public class CollarPersonInfoService implements RespService {

	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		CollarPersonInfoQuery collarPersonInfoQuery = new CollarPersonInfoQuery();
		ProvinceQuery provinceQuery = new ProvinceQuery();
		CityQuery cityQuery = new CityQuery();
		AreaQuery areaQuery = new AreaQuery();

		CollarPersonReq req = (CollarPersonReq) parmRequest;
		CollarPersonResp resp = new CollarPersonResp();

		String result = validate(req);

		if (result.equals(ResultCodes.NORMAL)) {

			CustCustomer customer = collarPersonInfoQuery.queryCustCustomer(req.getUserName());

			if (null != customer) {
				resp.setReal_name(customer.getRealName());
				resp.setId_no(customer.getIdentityNo());
				resp.setMarital_status(customer.getMaritalStatus());
				resp.setEducation_code(customer.getNowEduCation());
				resp.setProvince(customer.getOtherAdressProvince());
				resp.setCity(customer.getOtherAdressCity());
				resp.setArea(customer.getOtherAdressArea());
				resp.setAddress(customer.getOtherAccommodationAddress());

				resp.setEmail(customer.getEmailAdress());

				resp.setUnit_name(customer.getUnitName());
				resp.setUnit_phone_area(customer.getUnitTelArea());
				resp.setUnit_phone(customer.getUnitTel());
				resp.setUnit_province(customer.getWorkAreaProvince());
				resp.setUnit_city(customer.getWorkAreaCity());
				resp.setUnit_area(customer.getWorkAreaArea());
				resp.setUnit_address(customer.getWorkAreaRoad());

				resp.setWork_year(customer.getWorkExperience());

				resp.setProvince_name(provinceQuery.queryProvince(resp.getProvince()).getProvName());
				resp.setCity_name(cityQuery.queryCity(resp.getCity()).getCityName());
				resp.setArea_name(areaQuery.queryArea(resp.getArea()).getAreaName());

				resp.setUnit_province_name(provinceQuery.queryProvince(resp.getUnit_province()).getProvName());
				resp.setUnit_city_name(cityQuery.queryCity(resp.getUnit_city()).getCityName());
				resp.setUnit_area_name(areaQuery.queryArea(resp.getUnit_area()).getAreaName());

				// resp.setCredit_card(customer.getCreditCard());

				resp.setIdcard_validity_startdate(customer.getIdCardValStartDate());
				resp.setIdcard_validity_enddate(customer.getIdCardValEndDate());
				resp.setFulltimeDriver(customer.getFulltimeDriver());
				resp.setUnit_properties(customer.getUnitProperties());
				resp.setLoan_purpose(customer.getLoanUse());
				
				resp.setResultCode(ResultCodes.NORMAL);
			} else {
				resp.setResultCode(ResultCodes.CUSTOMER_NOT_FOUNT);
			}
		} else {
			resp.setResultCode(result);
		}
		return resp;
	}

	public String validate(CollarPersonReq req) {

		// 验证用户名
		if (RegexValidate.isNull(req.getUserName())) {
			return ResultCodes.USER_ERROR_ISNULL;
		}

		return ResultCodes.NORMAL;
	}

}
