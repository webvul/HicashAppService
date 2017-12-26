package com.hengyuan.hicash.service.validate.query;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.YiDaoMarkApiReq;
import com.hengyuan.hicash.utils.RegexValidate;

/**
 * 人脸识别 val
 * 
 * @author 0493
 * @createDate 2017-05-11
 *
 */
public class YiDaoMarkApiVal {

	private YiDaoMarkApiReq yiDaoMarkApiReq;

	public YiDaoMarkApiVal(YiDaoMarkApiReq yiDaoMarkApiReq) {
		this.yiDaoMarkApiReq = yiDaoMarkApiReq;
	}

	public String validate() {

		

		// 身份证号
		if (RegexValidate.isNull(yiDaoMarkApiReq.getId_no())) {
			return ResultCodes.IDCARD_ISNULL;
		}
		if (!RegexValidate.isIdCard(yiDaoMarkApiReq.getId_no())) {
			return ResultCodes.REGISTER_IDCARD_ILLEGAL;
		}
//		if("YDBS".equals(yiDaoMarkReq.getWhich_part())||RegexValidate.isNull(yiDaoMarkReq.getWhich_part())){//如果易道
//		// 分数
//		if (RegexValidate.isNull(yiDaoMarkReq.getMark() + "")) {
//			return ResultCodes.SCORE_ISNULLL;
//		}
//		if (!RegexValidate.isDigitFloatPos(yiDaoMarkReq.getMark() + "")) {
//			return ResultCodes.SCORE_ILLEGAL;
//		}
//
//		// 高阈值
//		if (RegexValidate.isNull(yiDaoMarkReq.getThreshold_high())) {
//			return ResultCodes.THRESHOLD_HIGH_ISNULL;
//		}
//
//		// 低阀值
//		if (RegexValidate.isNull(yiDaoMarkReq.getThreshold_low())) {
//			return ResultCodes.THRESHOLD_LOW_ISNULL;
//		}
//		}else if("FACE".equals(yiDaoMarkReq.getWhich_part())){//face++
//			/* 识别机构 */
//			if (RegexValidate.isNull(yiDaoMarkReq.getFace_str())) {
//				return ResultCodes.CHECK_FACE_WHICHPART_EXCEPTION;
//			}	
//		}else{
//			return ResultCodes.CHECK_FACE_WHICHPART_NULL;	
//		}
		return ResultCodes.NORMAL;
	}

	public YiDaoMarkApiReq getYiDaoMarkApiReq() {
		return yiDaoMarkApiReq;
	}

	public void setYiDaoMarkApiReq(YiDaoMarkApiReq yiDaoMarkApiReq) {
		this.yiDaoMarkApiReq = yiDaoMarkApiReq;
	}

	

}
