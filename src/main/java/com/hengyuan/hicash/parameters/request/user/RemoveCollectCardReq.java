package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 删除银行卡 请求参数
 * 
 * @author Cary.Liu
 * @create 2014-08-15
 * 
 */
public class RemoveCollectCardReq extends RequestSequence {

	private static final long serialVersionUID = 1L;

	/** 银行卡主键 */
	private String cardId;

	/** 用户名 */
	private String userName;

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
