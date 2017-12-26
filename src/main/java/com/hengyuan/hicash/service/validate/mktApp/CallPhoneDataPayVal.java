package com.hengyuan.hicash.service.validate.mktApp;

import java.util.regex.Pattern;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.mktApp.CreateAppPayReq;
import com.hengyuan.hicash.utils.RegexValidate;

/**
 * @author Administrator
 *
 */
public class CallPhoneDataPayVal {
private CreateAppPayReq valReq;

public CallPhoneDataPayVal(CreateAppPayReq val){
	this.valReq = val;
}

public String validate() {

	/* 验证uuid */
	if (RegexValidate.isNull(valReq.getUuid())) {
		return ResultCodes.UUIDNULL;
	}

	if (!RegexValidate.isUuid(valReq.getUuid())) {
		return ResultCodes.UUIDILLEGAL;
	}

	/* 验证用户名 */
	if (RegexValidate.isNull(valReq.getUserName())) {
		return ResultCodes.LOGIN_USERNAME_IS_NULL;
	}

	if (!RegexValidate.isUsername(valReq.getUserName())) {
		return ResultCodes.LOGIN_USERNAME_ILLEGAL;
	}
	
	/* 验证用户名 */
	if (RegexValidate.isNull(valReq.getUserName())) {
		return ResultCodes.LOGIN_USERNAME_IS_NULL;
	}

	if (!RegexValidate.isUsername(valReq.getUserName())) {
		return ResultCodes.LOGIN_USERNAME_ILLEGAL;
	}
	/*手机号吗*/
	if(RegexValidate.isNull(valReq.getPhoneNum())){
		return ResultCodes.SENDAMOUNTCODE_MOBILE_ISNULL;
	}
	
	if(!RegexValidate.isIphon(valReq.getPhoneNum())){
		return ResultCodes.SENDAMOUNTCODE_MOBILE_FAIL;
	}
	/*套餐id*/
	if(RegexValidate.isNull(valReq.getPhoneDataId())){
		return ResultCodes.CALL_PHONE_DATA_NUM_ID_NOTNULL;
	}
	
	if(!RegexValidate.isDigit(valReq.getPhoneDataId())){
		return ResultCodes.CALL_PHONE_DATA_NUM_ID_NOTRIGHT;
	}
	if(RegexValidate.isNull(valReq.getIsDx())){
		return ResultCodes.CALL_PHONE_DATA_DX_ID_NOTRIGHT;
	}
	
	/* 临时申请件单号 */
	if(RegexValidate.isNull(valReq.getTempAppNo())){
		return ResultCodes.CREATEAPPPAY_TEMPAPPNO_ISNULL;
	}
	
	if(!RegexValidate.isAppNo(valReq.getTempAppNo())){
		return ResultCodes.CREATEAPPPAY_TEMPAPPNO_ILLEGAL;
	}
	
//	/* 持卡人姓名 */
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
	
	/* 银行卡号 */
	if(RegexValidate.isNull(valReq.getBankCard())){
		return ResultCodes.CREATEAPPPAY_BANKCARD_ISNULL;
	}
	
	if(!RegexValidate.isLongNumber(valReq.getBankCard())){
		return ResultCodes.CREATEAPPPAY_BANKCARD_ILLEGAL;
	}
	
	/* 银行卡是否同步个人账户 */
	if(RegexValidate.isNull(valReq.getIsSynPerAcct())){
		return ResultCodes.CREATEAPPPAY_BANKSYN_ISNULL;
	}
	
	if(!RegexValidate.isSysMark(valReq.getIsSynPerAcct())){
		return ResultCodes.CREATEAPPPAY_BANKSYN_ILLEGAL;
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
	
	if(!RegexValidate.isStuId(valReq.getFirstRate()) || !RegexValidate.isLength(valReq.getFirstRate(),1,2)){
		return ResultCodes.CREATEAPPPAY_FIRSTRATE_ILLEGAL;
	}
	
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
