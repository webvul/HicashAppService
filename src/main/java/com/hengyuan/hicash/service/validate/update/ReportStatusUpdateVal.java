package com.hengyuan.hicash.service.validate.update;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.param.DreportStatusReq;
import com.hengyuan.hicash.utils.RegexValidate;

/**
 * hicash手机端白领提现申请1完善参数验证
 * 
 * @author lihua.Ren
 * @create date 2015-06-17
 *
 */
public class ReportStatusUpdateVal {

	private DreportStatusReq infoReq;

	public ReportStatusUpdateVal(DreportStatusReq infoReq) {
		this.infoReq = infoReq;
	}

	public String validate() {

		/* 验证用户名 */
		if (RegexValidate.isNull(infoReq.getTempAppNo())) {
			return ResultCodes.CREATEAPPPAY_TEMPAPPNO_ISNULL;
		}

		//认证项
		if (RegexValidate.isNull(infoReq.getAuthen())) {
			return ResultCodes.AUTNEN_IS_NOTNULL;
		}
		//认证状态不能为空
		if (RegexValidate.isNull(infoReq.getAuthenStatus())) {
			return ResultCodes.AUTNENSTATUS_IS_NOTNULL;
		}
		
		return ResultCodes.NORMAL;
	}
	
	public DreportStatusReq getInfoReq() {
		return infoReq;
	}

	public void setInfoReq(DreportStatusReq infoReq) {
		this.infoReq = infoReq;
	}

	

}
