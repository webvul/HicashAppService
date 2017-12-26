package com.hengyuan.hicash.service.validate.query;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.YiDaoMarkReq;
import com.hengyuan.hicash.utils.RegexValidate;

/**
 * 人脸识别 val
 * 
 * @author 0493
 * @createDate 2017-05-11
 *
 */
public class YiDaoMarkVal {

	private YiDaoMarkReq yiDaoMarkReq;

	public YiDaoMarkVal(YiDaoMarkReq yiDaoMarkReq) {
		this.yiDaoMarkReq = yiDaoMarkReq;
	}

	public String validate() {

		if (RegexValidate.isNull(yiDaoMarkReq.getUser_name())) {
			return ResultCodes.USER_ERROR_ISNULL;
		}
		
		if (!RegexValidate.isUsername(yiDaoMarkReq.getUser_name())) {
			return ResultCodes.USER_NAME_ERROR_CANTCHAR;
		}

		// 身份证号
		if (RegexValidate.isNull(yiDaoMarkReq.getId_no())) {
			return ResultCodes.CUSTOMER_NOT_FOUNT;
		}
		if (!RegexValidate.isIdCard(yiDaoMarkReq.getId_no())) {
			return ResultCodes.CUSTOMER_NOT_FOUNT;
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

	public YiDaoMarkReq getYiDaoMarkReq() {
		return yiDaoMarkReq;
	}

	public void setYiDaoMarkReq(YiDaoMarkReq yiDaoMarkReq) {
		this.yiDaoMarkReq = yiDaoMarkReq;
	}

}
