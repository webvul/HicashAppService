package com.hengyuan.hicash.parameters.request.param;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 
 * @author fish
 *
 * @date 2017年2月14日 上午10:27:45
 */
public class BankBinReq extends RequestSequence {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String bankCard;

	public String getBankCard() {
		return bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

}
