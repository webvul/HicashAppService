package com.hengyuan.hicash.service.validate.update;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.CollectCardReq;
import com.hengyuan.hicash.utils.RegexValidate;

public class CollectCardVal {
	
	private CollectCardReq cardReq;
	
	public CollectCardReq getCardReq() {
		return cardReq;
	}

	public void setCardReq(CollectCardReq cardReq) {
		this.cardReq = cardReq;
	}

	public CollectCardVal(CollectCardReq cardReq){
		this.cardReq = cardReq;
	}
	
	public String validate() {
		
		if (RegexValidate.isNull(cardReq.getUuid())) {
			return ResultCodes.UUIDNULL;
		}
		
		if (!RegexValidate.isUuid(cardReq.getUuid())) {
			return ResultCodes.UUIDILLEGAL;
		}
		
		// 验证新增还是修改
//		if (!RegexValidate.isNull(cardReq.getSaveOrUpdateFlag())) {
//			if (!("1".equals(cardReq.getSaveOrUpdateFlag()) || "2"
//					.equals(cardReq.getSaveOrUpdateFlag()))) {
//				return ResultCodes.ADD_CHANGE_ERROR;
//			}
//		}
		//卡类型
		if(RegexValidate.isNull(cardReq.getCardType())){
			return ResultCodes.CARD_TYPE_NOTNULL;
		}
		
		if(!Consts.OPENBANK_CARD_TYPE.equals(cardReq.getCardType().trim())){
			return ResultCodes.CARD_TYPE_FALSE;
		}
		
		//验证用户名
		if (RegexValidate.isNull(cardReq.getUserName())) {
			return ResultCodes.USER_ERROR_ISNULL;
		}
		
		if (!RegexValidate.isUsername(cardReq.getUserName())) {
			return ResultCodes.USER_NAME_ERROR_CANTCHAR;
		}
		
		//验证真实姓名
		if(RegexValidate.isNull(cardReq.getRealName())){
			return ResultCodes.CARD_REALNAME_NOTNLULL;
		}
		
		if(!RegexValidate.isRealName(cardReq.getRealName())){
			return ResultCodes.CARD_REALNAME_FALSE;
		}
		
		if(!RegexValidate.isLength(cardReq.getRealName(),1,20)){
			return ResultCodes.CARD_REALNAME_FALSE;
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
		
		if(!RegexValidate.isLength(cardReq.getCardNum(),10,20)){
			return ResultCodes.CARD_NUM_LEN_ERROR;
		}
		
		//验证收款账号
//		if(!RegexValidate.isNull(cardReq.getConfrimCardNum())){
//			
//			if(!RegexValidate.isCard(cardReq.getConfrimCardNum())){
//				return ResultCodes.COFIRM_CARD_NUM_ERROR;
//			}
//			
//			if(!RegexValidate.isLength(cardReq.getConfrimCardNum(),10,20)){
//				return ResultCodes.COFIRM_CARD_NUM_LEN_ERROR;
//			}
//			
//			//验证确认收款账号
//			if(!cardReq.getCardNum().equals(cardReq.getConfrimCardNum())){
//				return ResultCodes.CARD_NUM_COM_ERROR;
//			}
//			
//		}
//		
		
		if(RegexValidate.isNull(cardReq.getDefaultCard())){
			return ResultCodes.CARD_DEFAULT_NOTNULL;
		}
		
		if(!Consts.DEFAULT_CARD_YES.equals(cardReq.getDefaultCard())&&!Consts.DEFAULT_CARD_NO.equals(cardReq.getDefaultCard())){
			return ResultCodes.CARD_DEFAULT_FALSE;
		}
		
		return ResultCodes.NORMAL;
		
	}

}
