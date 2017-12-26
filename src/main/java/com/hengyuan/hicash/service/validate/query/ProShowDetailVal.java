package com.hengyuan.hicash.service.validate.query;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.param.ProShowDetailReq;
import com.hengyuan.hicash.utils.RegexValidate;

/**
 * 产品展示详情 请求参数验证
 * 
 * @author Cary.Liu
 * @createDate 2015-04-22
 *
 */
public class ProShowDetailVal {

	private ProShowDetailReq proShowReq;

	public ProShowDetailVal(ProShowDetailReq proShowReq) {
		this.proShowReq = proShowReq;
	}

	public String validate() {

		if (RegexValidate.isNull(proShowReq.getUuid())) {

			return ResultCodes.UUIDNULL;
		}

		if (RegexValidate.isNull(proShowReq.getMerProId())) {

			return ResultCodes.PROSHOWDETAIL_PROID_ISNULL;
		}

		if (!RegexValidate.isOnlyNumber(proShowReq.getMerProId())) {

			return ResultCodes.PROSHOWDETAIL_PROID_ILLEGAL;
		}

		return ResultCodes.NORMAL;
	}

	public ProShowDetailReq getProShowReq() {
		return proShowReq;
	}

	public void setProShowReq(ProShowDetailReq proShowReq) {
		this.proShowReq = proShowReq;
	}

}
