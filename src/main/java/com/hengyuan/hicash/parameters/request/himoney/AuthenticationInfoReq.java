package com.hengyuan.hicash.parameters.request.himoney;

import com.hengyuan.hicash.parameters.request.RequestSequence;


/**
 * 客户选择认证的项
 * 
 * @author xuexin
 * @create 2017年7月14日 09:53:10
 *
 */
public class AuthenticationInfoReq extends  RequestSequence{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**临时订单号*/
	private String tempAppNo;
	
	/**认证信息基础表id*/
	private String authId;
	
	/**认证用户名*/
	private String userName;

	/**认证时间*/
	private String createDate;
	
	/**状态*/
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTempAppNo() {
		return tempAppNo;
	}

	public void setTempAppNo(String tempAppNo) {
		this.tempAppNo = tempAppNo;
	}

	public String getAuthId() {
		return authId;
	}

	public void setAuthId(String authId) {
		this.authId = authId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	
	
}
