package com.hengyuan.hicash.service.validate.query;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.param.SameProductReq;
import com.hengyuan.hicash.utils.RegexValidate;

/**
 * 同款商品查询 请求参数验证
 * 
 * @author Cary.Liu
 * @createDate 2015-04-23
 *
 */
public class SameProductVal {

	private SameProductReq sameReq;

	public SameProductVal(SameProductReq sameReq) {
		this.sameReq = sameReq;
	}
	
	public String validate(){
		
		if(RegexValidate.isNull(sameReq.getCityCode())){
			return ResultCodes.SAMEPRODUCT_NOCITY;
		}
		
		if(!RegexValidate.isSelectToAddress(sameReq.getCityCode())){
			return ResultCodes.SAMEPRODUCT_CITY_ILLEGAL;
		}
		
		if(RegexValidate.isNull(sameReq.getProClass())){
			return ResultCodes.SAMEPRODUCT_PROCLASS_ISNULL;
		}
		
		return ResultCodes.NORMAL;
	}

	public SameProductReq getSameReq() {
		return sameReq;
	}

	public void setSameReq(SameProductReq sameReq) {
		this.sameReq = sameReq;
	}

}
