package com.hengyuan.hicash.parameters.response.user;

import com.google.gson.Gson;
import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * 民生银行代扣业务身份认证-用于验证交易用户验证交易码。CP0030
 * 
 * @author LiHua.Ren
 * @create date 2015-12-02
 */
public class CmbcIdentifyConfirmResp extends ParmResponse{

	private String valStatus;
	private String bussFlowNo;
	private String errorMsg;
	private String respCode;
	private String respMsg;
	
	

	public String toJson() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}


	/**
	 * @return the bussFlowNo
	 */
	public String getBussFlowNo() {
		return bussFlowNo;
	}

	/**
	 * @param bussFlowNo the bussFlowNo to set
	 */
	public void setBussFlowNo(String bussFlowNo) {
		this.bussFlowNo = bussFlowNo;
	}


	/**
	 * @return the respCode
	 */
	public String getRespCode() {
		return respCode;
	}

	/**
	 * @param respCode the respCode to set
	 */
	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	/**
	 * @return the respMsg
	 */
	public String getRespMsg() {
		return respMsg;
	}

	/**
	 * @param respMsg the respMsg to set
	 */
	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}


	/**
	 * @return the valStatus
	 */
	public String getValStatus() {
		return valStatus;
	}


	/**
	 * @param valStatus the valStatus to set
	 */
	public void setValStatus(String valStatus) {
		this.valStatus = valStatus;
	}


	/**
	 * @return the errorMsg
	 */
	public String getErrorMsg() {
		return errorMsg;
	}


	/**
	 * @param errorMsg the errorMsg to set
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}
