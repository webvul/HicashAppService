package com.hengyuan.hicash.service.validate.query.custnotic;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.notic.NoticeInfoReq;
import com.hengyuan.hicash.utils.RegexValidate;

public class NoticeTitleVal {

	private NoticeInfoReq infoReq;

	public NoticeTitleVal(NoticeInfoReq infoReq) {
		this.infoReq = infoReq;
	}

	public String validate() {

		if (RegexValidate.isNull(infoReq.getUuid())) {
			return ResultCodes.UUIDNULL;
		}

		if (!RegexValidate.isUuid(infoReq.getUuid())) {
			return ResultCodes.UUIDILLEGAL;
		}

		return ResultCodes.NORMAL;
	}

	public NoticeInfoReq getInfoReq() {
		return infoReq;
	}

	public void setInfoReq(NoticeInfoReq infoReq) {
		this.infoReq = infoReq;
	}

}
