package com.hengyuan.hicash.service.validate.update;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.mktApp.CreateAppPayReq;
import com.hengyuan.hicash.utils.RegexValidate;

/**
 * 创建申请件 validate
 * 创建嗨秒贷申请 
 * @author Cary.Liu
 * @createDate 2015-05-27
 */
public class CreateAppPayByMDVal {

	private CreateAppPayReq valReq;
	
	public CreateAppPayByMDVal(CreateAppPayReq valReq){
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
		
		/* 临时申请件单号 */
		if(RegexValidate.isNull(valReq.getTempAppNo())){
			return ResultCodes.CREATEAPPPAY_TEMPAPPNO_ISNULL;
		}
		
//		if(!RegexValidate.isAppNo(valReq.getTempAppNo())){
//			return ResultCodes.CREATEAPPPAY_TEMPAPPNO_ILLEGAL;
//		}
		
		/* 交易金额 */
		if(RegexValidate.isNull(valReq.getTranPrice())){
			return ResultCodes.CREATEAPPPAY_PRICE_ISNULL;
		}
		
		if(!RegexValidate.isDigitPos(valReq.getTranPrice()) || !RegexValidate.isLength(valReq.getTranPrice(),1,10)){
			return ResultCodes.CREATEAPPPAY_PRICE_ILLEGAL;
		}
		
		/* 贷款产品ID */
		if(RegexValidate.isNull(valReq.getLoanProduct())){
			return ResultCodes.LOANPRODUCT_ERROR_ISNULL;
		}
		
		/* 持卡人姓名 */
		if(RegexValidate.isNull(valReq.getBankRealName())){
			return ResultCodes.CREATEAPPPAY_BANKREALNAME_ISNULL;
		}
		
		if(!RegexValidate.isRealName(valReq.getBankRealName())){
			return ResultCodes.CREATEAPPPAY_BANKREALNAME_ILLEGAL;
		}
		
		/* 开户行 */
		if(RegexValidate.isNull(valReq.getOpenBank())){
			return ResultCodes.CREATEAPPPAY_OPENBAN_ISNULL;
		}
		
		if(!RegexValidate.isStuId(valReq.getOpenBank())){
			return ResultCodes.CREATEAPPPAY_OPENBAN_ILLEGAL;
		}
//		/**
//		 * 开户支行
//		 */
//		if(RegexValidate.isNull(valReq.getOpenBankBranch())){
//			return ResultCodes.CARD_ADDRESS_ISNULL;
//		}
		
		/* 银行卡号 */
		if(RegexValidate.isNull(valReq.getBankCard())){
			return ResultCodes.CREATEAPPPAY_BANKCARD_ISNULL;
		}
		
		if(!RegexValidate.isLongNumber(valReq.getBankCard())){
			return ResultCodes.CREATEAPPPAY_BANKCARD_ILLEGAL;
		}
		
		/* 银行卡是否同步个人账户 */
//		if(RegexValidate.isNull(valReq.getIsSynPerAcct())){
//			return ResultCodes.CREATEAPPPAY_BANKSYN_ISNULL;
//		}
//		
//		if(!RegexValidate.isSysMark(valReq.getIsSynPerAcct())){
//			return ResultCodes.CREATEAPPPAY_BANKSYN_ILLEGAL;
//		}
		
		/* 产品ID */
		if(RegexValidate.isNull(valReq.getMerProId())){
			return ResultCodes.CREATEAPPPAY_MERPROID_ISNULL;
		}
		
		if(!RegexValidate.isNumber(valReq.getMerProId())){
			return ResultCodes.CREATEAPPPAY_MERPROID_ILLEGAL;
		}
		
		/* 3c需要产品名称 */
//		if(RegexValidate.isNull(valReq.getProductName())){
//			return ResultCodes.CREATEAPPPAY_PRODUCTNAME_ISNULL;
//		}
		
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
		
//		if(!RegexValidate.isStuId(valReq.getFirstRate()) || !RegexValidate.isLength(valReq.getFirstRate(),1,2)){
//			return ResultCodes.CREATEAPPPAY_FIRSTRATE_ILLEGAL;
//		}
		
		/* 申请方式 */
//		if(RegexValidate.isNull(valReq.getApplyType())){
//			return ResultCodes.CREATEAPPPAY_APPLYTYPE_ISNULL;
//		}
//		
//		if(!RegexValidate.isApplyType(valReq.getApplyType())){
//			return ResultCodes.CREATEAPPPAY_APPLYTYPE_ISNULL;
//		}
		
		//客户类型
		if(RegexValidate.isNull(valReq.getCustType())){
			return ResultCodes.CREATEAPPPAY_CUSTTYPE_ISNULL;
		}
		
		if(!RegexValidate.isCustType(valReq.getCustType())){
			return ResultCodes.CREATEAPPPAY_CUSTTYPE_ILLEGAL;
		}
		
		/* 返点比例 */
//		if(RegexValidate.isNull(valReq.getRebate())){
//			return ResultCodes.CREATEAPPPAY_REBATE_ISNULL;
//		}
//		
//		if(!RegexValidate.isDigitFloatPos(valReq.getRebate())){
//			return ResultCodes.CREATEAPPPAY_REBATE_ILLEGAL;
//		}
		
		/* 申请来源 */
		if(RegexValidate.isNull(valReq.getApplyFrom())){
			return ResultCodes.APPLY_FROM_ISNULL;
		}
		
//		if(!RegexValidate.isApplyFrom(valReq.getApplyFrom())){
//			return ResultCodes.APPLY_FROM_ILLEGAL;
//		}
		
		/* 省市 */
		if(RegexValidate.isNull(valReq.getProvince())){
			return ResultCodes.CREATEAPP_BANK_PRO_NOTNULL;
		}
		
		if(RegexValidate.isNull(valReq.getCity())){
			return ResultCodes.CREATEAPP_BANK_CITY_NOTNULL;
		}
		/**
		 *  开户行区不能为空
		 */
		if(RegexValidate.isNull(valReq.getCity())){
			return ResultCodes.CARD_AREA_ISNULL;
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
