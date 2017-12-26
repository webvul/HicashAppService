package com.hengyuan.hicash.entity.param;

import com.google.gson.Gson;

/**
 * @author LiHua.Ren 民生银行代收交易响应entity
 *
 */
public class CmbcGFAResEntity {

	private String returnCode;
	private String bussFlowNo;
	private String errorMsg;

	/** 银行返回的原始代码 */
	private String respCode;

	/** 银行返回的错误信息 */
	private String respMsg;

	// private String tranState;

	/**
	 * @return the returnCode
	 */
	public String getReturnCode() {
		return returnCode;
	}

	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getRespMsg() {
		return respMsg;
	}

	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}

	/**
	 * @param returnCode
	 *            the returnCode to set
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
	 * @param bussFlowNo
	 *            the bussFlowNo to set
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
	 * @param errorMsg
	 *            the errorMsg to set
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	// /**
	// * @return the tranState
	// */
	// public String getTranState() {
	// return tranState;
	// }
	// /**
	// * @param tranState the tranState to set
	// */
	// public void setTranState(String tranState) {
	// this.tranState = tranState;
	// }
	public String toJson() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
}
