package com.hengyuan.hicash.service.validate.update;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.UpdateBankCardReq;
import com.hengyuan.hicash.utils.RegexValidate;

/**
 * 修改银行卡 请求参数验证
 * 
 * @author Cary.Liu
 * @create 2014-09-29
 * 
 */
public class UpdateBankCardVal {

	private UpdateBankCardReq cardReq;
	
	public UpdateBankCardVal(UpdateBankCardReq cardReq){
		this.cardReq = cardReq;
	}

	public String validate(){
		
		if (RegexValidate.isNull(cardReq.getUuid())) {
			return ResultCodes.UUIDNULL;
		}
		
		if (!RegexValidate.isUuid(cardReq.getUuid())) {
			return ResultCodes.UUIDILLEGAL;
		}
		
		//验证用户名
		if (RegexValidate.isNull(cardReq.getUserName())) {
			return ResultCodes.USER_ERROR_ISNULL;
		}
		
		if (!RegexValidate.isUsername(cardReq.getUserName())) {
			return ResultCodes.USER_NAME_ERROR_CANTCHAR;
		}
		
		//验证开户行
		if(RegexValidate.isNull(cardReq.getBank())){
			return ResultCodes.CARD_ADDRESS_ISNULL;
		}
		
		if(!RegexValidate.isSelect(cardReq.getBank())){
			return ResultCodes.CARD_BANK_TYPE;
		}
		
		//验证开户支行
		if(RegexValidate.isNull(cardReq.getOpenBank())){
			return ResultCodes.CARD_ISNULL;
		}
		
		if(!RegexValidate.isStrFilter(cardReq.getOpenBank())){
			return ResultCodes.CARD_BANCK_FALSE;
		}
		
		if(!RegexValidate.isLength(cardReq.getOpenBank(),1,100)){
			return ResultCodes.CARD_BANCK_LENGTH;
		}
		
		//验证开户城市-省
		if(RegexValidate.isNull(cardReq.getProvince())){
			return ResultCodes.CARD_PROVINCE_ISNULL;
		}
		
		if(!RegexValidate.isSelectToAddress(cardReq.getProvince())){
			return ResultCodes.CARD_PROVINCE_VAL_FALSE;
		}
		
		//验证开户城市-市
		if(RegexValidate.isNull(cardReq.getCity())){
			return ResultCodes.CARD_CITY_ISNULL;
		}
		
		if(!RegexValidate.isSelectToAddress(cardReq.getCity())){
			return ResultCodes.CARD_CITY_VAL_FALSE;
		}
		
		//验证开户城市-区
		if (RegexValidate.isNull(cardReq.getAreaCode())) {
			return ResultCodes.CARD_AREA_ISNULL;
		}

		if (!RegexValidate.isSelectToAddress(cardReq.getAreaCode())) {
			return ResultCodes.CARD_AREA_ILLEGAL;
		}
		
		//验证收款账号
		if(RegexValidate.isNull(cardReq.getCardNum())){
			return ResultCodes.CARD_NUM_ISNULL;
		}
		
		if(!RegexValidate.isCard(cardReq.getCardNum())){
			return ResultCodes.CARD_NUM_ERROR;
		}
		
		if(!RegexValidate.isLength(cardReq.getCardNum(),5,20)){
			return ResultCodes.CARD_NUM_LEN_ERROR;
		}
		
		return ResultCodes.NORMAL;
	}
	
	public UpdateBankCardReq getCardReq() {
		return cardReq;
	}

	public void setCardReq(UpdateBankCardReq cardReq) {
		this.cardReq = cardReq;
	}
	
	
	
}
