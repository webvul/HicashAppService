package com.hengyuan.hicash.service.validate.mktApp;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.mktApp.CreateAppPayReq;
import com.hengyuan.hicash.utils.RegexValidate;

/**
 * 创建申请 请求参数验证
 * @author Cary.Liu
 * @updateDate 2015-01-09 V2.2
 * 
 * destory
 *
 */
public class CreateAppPayVal extends RegexValidate {
	
	private CreateAppPayReq valReq;
	
	public CreateAppPayVal(CreateAppPayReq valReq){
		this.valReq = valReq;
	}

	public String validate(){
		
		/* 验证uuid */
		if(RegexValidate.isNull(valReq.getUuid())){
			return ResultCodes.UUIDNULL;
		}
		
		if(!RegexValidate.isUuid(valReq.getUuid())){
			return ResultCodes.UUIDILLEGAL;
		}
		
		/* 验证用户名 */
		if(RegexValidate.isNull(valReq.getUserName())){
			return ResultCodes.LOGIN_USERNAME_IS_NULL;
		}
		
		if(!RegexValidate.isUsername(valReq.getUserName())){
			return ResultCodes.LOGIN_USERNAME_ILLEGAL;
		}
		
		if(RegexValidate.isNull(valReq.getMerProId())){
			return ResultCodes.CREATEAPPPAY_MERPROID_ISNULL;
		}
		
		if(!RegexValidate.isNumber(valReq.getMerProId())){
			return ResultCodes.CREATEAPPPAY_MERPROID_ILLEGAL;
		}
		
		/* 3c需要产品名称 */
		if(RegexValidate.isNull(valReq.getProductName())){
			return ResultCodes.CREATEAPPPAY_PRODUCTNAME_ISNULL;
		}
		
		/* 交易金额 */
		if(RegexValidate.isNull(valReq.getTranPrice())){
			return ResultCodes.CREATEAPPPAY_PRICE_ISNULL;
		}
		
		if(!RegexValidate.isDigitPos(valReq.getTranPrice()) || !RegexValidate.isLength(valReq.getTranPrice(),1,10)){
			return ResultCodes.CREATEAPPPAY_PRICE_ILLEGAL;
		}
		
		/* 商户ID */
		if(RegexValidate.isNull(valReq.getSupplierId())){
			return ResultCodes.CREATEAPPPAY_SUPPLIERID_ISNULL;
		}
		
		if(!RegexValidate.isNumber(valReq.getSupplierId())){
			return ResultCodes.CREATEAPPPAY_SUPPLIERID_ILLEGAL;
		}
		
		/* 售点ID */
		if(RegexValidate.isNull(valReq.getSiteId())){
			return ResultCodes.CREATEAPPPAY_SITEID_ISNULL;
		}
		
		if(!RegexValidate.isNumber(valReq.getSiteId())){
			return ResultCodes.CREATEAPPPAY_SITEID_ILLEGAL;
		}
		
		/* 贷款产品ID */
		if(RegexValidate.isNull(valReq.getLoanProduct())){
			return ResultCodes.CREATEAPPPAY_LOANPRODUCT_ISNULL;
		}
		
		if(!RegexValidate.isNumber(valReq.getLoanProduct())){
			return ResultCodes.CREATEAPPPAY_LOANPRODUCT_ILLEGAL;
		}
		
		/* 首付比率 */
		if(RegexValidate.isNull(valReq.getFirstRate())){
			return ResultCodes.CREATEAPPPAY_FIRSTRATE_ISNULL;
		}
		
		if(!RegexValidate.isStuId(valReq.getFirstRate()) || !RegexValidate.isLength(valReq.getFirstRate(),1,2)){
			return ResultCodes.CREATEAPPPAY_FIRSTRATE_ILLEGAL;
		}
		
		/* 申请备注 */
//		if(RegexValidate.isNull(valReq.getProductDetail())){
//			return ResultCodes.CREATEAPPPAY_DETAIL_ISNULL;
//		}
//		
//		if(!RegexValidate.isDescFlag(valReq.getProductDetail(), 200)){
//			return ResultCodes.CREATEAPPPAY_DETAIL_ILLEGAL;
//		}
		
		/* 申请方式 */
		if(RegexValidate.isNull(valReq.getApplyType())){
			return ResultCodes.CREATEAPPPAY_APPLYTYPE_ISNULL;
		}
		
		if(!RegexValidate.isApplyType(valReq.getApplyType())){
			return ResultCodes.CREATEAPPPAY_APPLYTYPE_ISNULL;
		}
		
		//客户类型
		if(RegexValidate.isNull(valReq.getCustType())){
			return ResultCodes.CREATEAPPPAY_CUSTTYPE_ISNULL;
		}
		
		if(!RegexValidate.isCustType(valReq.getCustType())){
			return ResultCodes.CREATEAPPPAY_CUSTTYPE_ILLEGAL;
		}
		
//		if(Consts.APPFLOW_TYPE_CASH.equals(valReq.getApplyType())){
//			
//			if(RegexValidate.isNull(valReq.getBankNo())){
//				return ResultCodes.CREATEAPPPAY_BANKCARDNO_ISNULL;
//			}
//			
//			if(!RegexValidate.isNumber(valReq.getBankNo())){
//				return ResultCodes.CREATEAPPPAY_BANKCARDNO_ILLEGAL;
//			}
//			
//		}
		
		/* 返点比例 */
		if(RegexValidate.isNull(valReq.getRebate())){
			return ResultCodes.CREATEAPPPAY_REBATE_ISNULL;
		}
		
		if(!RegexValidate.isDigitFloatPos(valReq.getRebate())){
			return ResultCodes.CREATEAPPPAY_REBATE_ILLEGAL;
		}
		
		return ResultCodes.NORMAL;
	}
	
	public CreateAppPayReq getValReq() {
		return valReq;
	}

	public void setValReq(CreateAppPayReq valReq) {
		this.valReq = valReq;
	}
}
