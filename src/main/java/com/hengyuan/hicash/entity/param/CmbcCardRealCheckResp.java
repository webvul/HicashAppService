package com.hengyuan.hicash.entity.param;

import com.google.gson.Gson;

/**
 * @author LiHua.Ren 民生银行代收银行卡实名认证响应entity
 *
 */
public class CmbcCardRealCheckResp {

	private String returnCode;
	private String tranFlow;
	private String merchantNo;
	private String errorMsg;
	private String isBcnAndIdnConform;
	private String bankName;

	/**
	 * @return the tranFlow
	 */
	public String getTranFlow() {
		return tranFlow;
	}

	/**
	 * @param tranFlow
	 *            the tranFlow to set
	 */
	public void setTranFlow(String tranFlow) {
		this.tranFlow = tranFlow;
	}

	/**
	 * @return the isBcnAndIdnConform
	 */
	public String getIsBcnAndIdnConform() {
		return isBcnAndIdnConform;
	}

	/**
	 * @param isBcnAndIdnConform
	 *            the isBcnAndIdnConform to set
	 */
	public void setIsBcnAndIdnConform(String isBcnAndIdnConform) {
		this.isBcnAndIdnConform = isBcnAndIdnConform;
	}

	/**
	 * @return the bankName
	 */
	public String getBankName() {
		return bankName;
	}

	/**
	 * @param bankName
	 *            the bankName to set
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String toJson() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}

	/**
	 * @return the returnCode
	 */
	public String getReturnCode() {
		return returnCode;
	}

	/**
	 * @param returnCode
	 *            the returnCode to set
	 */
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	/**
	 * @return the merchantNo
	 */
	public String getMerchantNo() {
		return merchantNo;
	}

	/**
	 * @param merchantNo
	 *            the merchantNo to set
	 */
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	/**
	 * @return the errorMsg
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * @param errorMsg
	 *            the errorMsg to set
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}
