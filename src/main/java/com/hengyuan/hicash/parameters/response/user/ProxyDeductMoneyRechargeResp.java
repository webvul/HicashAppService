package com.hengyuan.hicash.parameters.response.user;

import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * 嗨秒贷
 * 验证用户收款银行卡信息,代扣快捷充值5块钱
 * 代理扣款
 * @author Lihua.Ren
 * @createDate 2015-11-03
 *
 */
public class ProxyDeductMoneyRechargeResp extends ParmResponse {
	private String valStatus;
	private String bussFlowNo;
//	private String errorMsg;
	private String respCode;
	private String respMsg;
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
	
}
