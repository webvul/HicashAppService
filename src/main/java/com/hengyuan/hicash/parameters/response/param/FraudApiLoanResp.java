package com.hengyuan.hicash.parameters.response.param;

/**
 * 同盾验证接口返回参数
 * @author Cary.Liu
 * @createDate 2015-12-10
 *
 */
public class FraudApiLoanResp {

	/** 返回状态 */
	private String status;

	/** 返回消息 */
	private String msg;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
