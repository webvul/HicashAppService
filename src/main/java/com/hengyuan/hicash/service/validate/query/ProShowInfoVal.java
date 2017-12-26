package com.hengyuan.hicash.service.validate.query;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.param.ProShowInfoReq;
import com.hengyuan.hicash.utils.RegexValidate;

/**
 * 产品展示信息 请求参数验证
 * 
 * @author Cary.Liu
 * @createDate 2015-04-22
 *
 */
public class ProShowInfoVal {

	private ProShowInfoReq proShowReq;

	public ProShowInfoVal(ProShowInfoReq proShowReq) {
		this.proShowReq = proShowReq;
	}

	public String validate() {

		if (RegexValidate.isNull(proShowReq.getUuid())) {

			return ResultCodes.UUIDNULL;
		}

		/* 城市代码 */
		if (RegexValidate.isNull(proShowReq.getCityCode())) {

			return ResultCodes.PROSHOWINFO_CITYCODE_ISNULL;
		}

		if (!RegexValidate.isSelectToAddress(proShowReq.getCityCode())) {

			return ResultCodes.PROSHOWINFO_CITYCODE_ILLEGAL;
		}

		/* 频道ID */
		if (RegexValidate.isNull(proShowReq.getChannelId())) {

			return ResultCodes.PROSHOWINFO_CHANNELID_ISNULL;
		}

		if (!RegexValidate.isOnlyNumber(proShowReq.getChannelId())) {

			return ResultCodes.PROSHOWINFO_CHANNELID_ILLEGAL;
		}

		return ResultCodes.NORMAL;
	}

	public ProShowInfoReq getProShowReq() {
		return proShowReq;
	}

	public void setProShowReq(ProShowInfoReq proShowReq) {
		this.proShowReq = proShowReq;
	}

}
