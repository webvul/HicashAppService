package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * @author Administrator
 *
 */
public class BlackCertNoUpdateReq extends RequestSequence {

	private static final long serialVersionUID = 3L;

	/** 身份证号码 */
	private String certNo;

	/** 非请求参数 */
	private String requestIp;

	public String getRequestIp() {
		return requestIp;
	}

	public void setRequestIp(String requestIp) {
		this.requestIp = requestIp;
	}

	/**
	 * @return the certNo
	 */
	public String getCertNo() {
		return certNo;
	}

	/**
	 * @param certNo
	 *            the certNo to set
	 */
	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

}
