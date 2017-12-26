package com.hengyuan.hicash.entity.user;

import com.google.gson.Gson;

/**
 * 民生银行代扣业务身份认证-用于验证用户验证码。CP0030
 * 
 * @author LiHua.Ren
 * @create date 2015-11-06
 */
public class CmbcIdentifyConfirmResEntity {
	
	private String returnCode;
	private String bussFlowNo;
	private String errorMsg;
	private String respCode;
	private String respMsg;
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
	 * @param returnCode the returnCode to set
	 */
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
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


}
