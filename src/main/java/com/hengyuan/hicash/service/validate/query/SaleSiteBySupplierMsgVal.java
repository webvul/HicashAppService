package com.hengyuan.hicash.service.validate.query;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.SaleSiteBySupplierMsgReq;
import com.hengyuan.hicash.utils.RegexValidate;

/**
 * 根据商户id查询售点
 * 
 * @author lihua.Ren
 * @create date 2016-01-26
 *
 */
public class SaleSiteBySupplierMsgVal {

private SaleSiteBySupplierMsgReq queryMsgReq;
	
	public SaleSiteBySupplierMsgVal(SaleSiteBySupplierMsgReq queryMsgReq){
		
		this.queryMsgReq = queryMsgReq;
		
	}

	public String validate(){
		
		if (RegexValidate.isNull(queryMsgReq.getUuid())) {
			return ResultCodes.UUIDNULL;
		}
		
		if (!RegexValidate.isUuid(queryMsgReq.getUuid())) {
			return ResultCodes.UUIDILLEGAL;
		}

		if (RegexValidate.isNull(queryMsgReq.getSupplierId())) {
			return ResultCodes.CREATEAPPPAY_SUPPLIERID_ISNULL;
		}
		if(!RegexValidate.isNumber(queryMsgReq.getSupplierId())){
			return ResultCodes.CREATEAPPPAY_SUPPLIERID_ILLEGAL;
		}
		return ResultCodes.NORMAL;
	}
	
	
	public SaleSiteBySupplierMsgReq getQueryMsgReq() {
		return queryMsgReq;
	}
	

}
