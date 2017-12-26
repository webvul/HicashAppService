package com.hengyuan.hicash.service.validate.update;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.RemoveCollectCardReq;
import com.hengyuan.hicash.utils.RegexValidate;

public class RemoveCollectCardVal {

	private RemoveCollectCardReq cardReq;

	public RemoveCollectCardVal(RemoveCollectCardReq cardReq) {
		this.cardReq = cardReq;
	}
	
	public String validate(){
		
		/* 验证uuid */
		if(RegexValidate.isNull(cardReq.getUuid())){
			return ResultCodes.UUIDNULL;
		}
		
		if(!RegexValidate.isUuid(cardReq.getUuid())){
			return ResultCodes.UUIDILLEGAL;
		}
		
		// 验证用户名
		if (RegexValidate.isNull(cardReq.getUserName())) {
			return ResultCodes.USER_ERROR_ISNULL;
		}

		if (!RegexValidate.isUsername(cardReq.getUserName())) {
			return ResultCodes.USER_NAME_ERROR_CANTCHAR;
		}
				
		if(RegexValidate.isNull(cardReq.getCardId())){;
			return ResultCodes.REMOVE_CARD_ID_NOTNULL;
		}
		
		if(!RegexValidate.isNumber(cardReq.getCardId())||!RegexValidate.isLength(cardReq.getCardId(), 1, 5)){
			return ResultCodes.REMOVE_CARD_ID_FALSE;
		}
		
		
		return ResultCodes.NORMAL;
	}

	public RemoveCollectCardReq getCardReq() {
		return cardReq;
	}

	public void setCardReq(RemoveCollectCardReq cardReq) {
		this.cardReq = cardReq;
	}

}
