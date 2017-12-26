package com.hengyuan.hicash.entity.user;

/**
 * 代扣账户验证记录
 * 
 * @author think
 *
 */
public class AccountValRecordEntity {

	private String id;

	private String userName;

	private String identityNo;

	private String openBank;

	private String bankCard;

	private String valResult;

	private String valMsg;

	private String createDate;

	public String getIdentityNo() {
		return identityNo;
	}

	public void setIdentityNo(String identityNo) {
		this.identityNo = identityNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOpenBank() {
		return openBank;
	}

	public void setOpenBank(String openBank) {
		this.openBank = openBank;
	}

	public String getBankCard() {
		return bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	public String getValResult() {
		return valResult;
	}

	public void setValResult(String valResult) {
		this.valResult = valResult;
	}

	public String getValMsg() {
		return valMsg;
	}

	public void setValMsg(String valMsg) {
		this.valMsg = valMsg;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

}
