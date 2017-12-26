package com.hengyuan.hicash.service.validate.update;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.CustReceiveAddressReq;
import com.hengyuan.hicash.utils.RegexValidate;

public class SaveOrUpdateAddressVal {

	private CustReceiveAddressReq req;

	public SaveOrUpdateAddressVal(CustReceiveAddressReq req) {
		super();
		this.req = req;
	}

	public CustReceiveAddressReq getReq() {
		return req;
	}

	public void setReq(CustReceiveAddressReq req) {
		this.req = req;
	}

	public String validate() {

		if (RegexValidate.isNull(req.getUuid())) {
			return ResultCodes.UUIDNULL;
		}
		
		if (!RegexValidate.isUuid(req.getUuid())) {
			return ResultCodes.UUIDILLEGAL;
		}
		/* 用户名 */
		if (RegexValidate.isNull(req.getCust_user())) {
			return ResultCodes.USER_ERROR_ISNULL;
		}
		
		if (!RegexValidate.isUsername(req.getCust_user())) {
			return ResultCodes.USER_NAME_ERROR_CANTCHAR;
		}

		/*手机号码*/
		if (RegexValidate.isNull(req.getMobile())) {
			return ResultCodes.FORGETPASSWORd_PHONE_ERROR_ISNULL;
		}
		
		if (!RegexValidate.isIphon(req.getMobile())) {
			return ResultCodes.FORGETPASSWORd_PHONE_ERROR;
		}
		/* 姓名 */
		if (RegexValidate.isNull(req.getReal_name())) {
			return ResultCodes.REGISTER_REALNAME_ISNULL;
		}
		if (!RegexValidate.isRealName(req.getReal_name())) {
			return ResultCodes.REGISTER_REALNAME_ILLEGAL;
		}
		/* 省 */
		if (RegexValidate.isNull(req.getProvince_code())) {
			return ResultCodes.STU_APP_AREA_PRO_NOTNULL;
		}
		/* 市 */
		if (RegexValidate.isNull(req.getCity_code())) {
			return ResultCodes.STU_APP_AREA_CITY_NOTNULL;
		}
		/* 区 */
		if (RegexValidate.isNull(req.getArea_code())) {
			return ResultCodes.AREA_NOTNULL;
		}
		if (RegexValidate.isNull(req.getDetail_address())) {
			return ResultCodes.DETAIL_ADDRESS;
		}
		return ResultCodes.NORMAL;

	}

}
