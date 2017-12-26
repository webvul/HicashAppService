package com.hengyuan.hicash.parameters.response.user;

import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * @author Administrator
 *
 */
public class CallPhoneQuartetProtocolAppResp  extends ParmResponse {

	/**商品价格*/
	private String proAmount;
	/**期数Id*/
	private String loanProductId;
	/**产品类型*/
	private String proType;
	private String applyAmount;
	
	/**
	 * @return the proType
	 */
	public String getProType() {
		return proType;
	}
	/**
	 * @param proType the proType to set
	 */
	public void setProType(String proType) {
		this.proType = proType;
	}
	/**
	 * @return the proAmount
	 */
	public String getProAmount() {
		return proAmount;
	}
	/**
	 * @param proAmount the proAmount to set
	 */
	public void setProAmount(String proAmount) {
		this.proAmount = proAmount;
	}
	/**
	 * @return the loanProductId
	 */
	public String getLoanProductId() {
		return loanProductId;
	}
	/**
	 * @param loanProductId the loanProductId to set
	 */
	public void setLoanProductId(String loanProductId) {
		this.loanProductId = loanProductId;
	}
	/**
	 * @return the applyAmount
	 */
	public String getApplyAmount() {
		return applyAmount;
	}
	/**
	 * @param applyAmount the applyAmount to set
	 */
	public void setApplyAmount(String applyAmount) {
		this.applyAmount = applyAmount;
	}

}
