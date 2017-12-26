package com.hengyuan.hicash.service.validate.query;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.SupplierInfoByCityMsgReq;
import com.hengyuan.hicash.utils.RegexValidate;

/**
 * 根据城市code查询商户
 * 
 * @author lihua.Ren
 * @create date 2016-01-26
 *
 */
public class SupplierInfoByCityMsgVal {

private SupplierInfoByCityMsgReq queryMsgReq;
	
	public SupplierInfoByCityMsgVal(SupplierInfoByCityMsgReq queryMsgReq){
		
		this.queryMsgReq = queryMsgReq;
		
	}

	public String validate(){
		
		if (RegexValidate.isNull(queryMsgReq.getUuid())) {
			return ResultCodes.UUIDNULL;
		}
		
		if (!RegexValidate.isUuid(queryMsgReq.getUuid())) {
			return ResultCodes.UUIDILLEGAL;
		}

		if (RegexValidate.isNull(queryMsgReq.getCityCode())) {
			return ResultCodes.STU_APP_AREA_CITY_NOTNULL;
		}
		if(!RegexValidate.isSelectToAddress(queryMsgReq.getCityCode())){
			return ResultCodes.STU_APP_AREA_CITY_FALSE;
		}
		return ResultCodes.NORMAL;
	}
	
	
	public SupplierInfoByCityMsgReq getQueryMsgReq() {
		return queryMsgReq;
	}
	

}
