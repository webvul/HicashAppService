package com.hengyuan.hicash.service.validate.query.custnotic;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.notic.NoticeInfoReq;
import com.hengyuan.hicash.utils.RegexValidate;

public class NoticeInfoVal {

	private NoticeInfoReq infoReq;

	public NoticeInfoVal(NoticeInfoReq infoReq) {
		this.infoReq = infoReq;
	}

	public String validate() {

		if (RegexValidate.isNull(infoReq.getUuid())) {
			return ResultCodes.UUIDNULL;
		}

		if (!RegexValidate.isUuid(infoReq.getUuid())) {
			return ResultCodes.UUIDILLEGAL;
		}
		
		if(RegexValidate.isNull(infoReq.getId())){
			return ResultCodes.NOTICE_DETAIL_ID_NOTNULL;
		}
		
		if(!RegexValidate.isNumber(infoReq.getId())||!RegexValidate.isLength(infoReq.getId(), 1, 5)){
			return ResultCodes.NOTICE_DETAIL_ID_FALSE;
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
