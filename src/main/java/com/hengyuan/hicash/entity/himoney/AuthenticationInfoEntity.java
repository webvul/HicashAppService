package com.hengyuan.hicash.entity.himoney;


/**
 * 客户选择认证的项
 * 
 * @author xuexin
 * @create 2017年7月14日 09:53:10
 *
 */
public class AuthenticationInfoEntity {
	
	/**主键*/
	private Integer id;
	
	/**临时订单号*/
	private String tempAppNo;
	
	/**认证信息基础表*/
	private String authId;
	
	/**status01 已认证，status02 审核中【待数据推送】 ，status03 已完成*/
	private String status;
	
	/**认证的用户名*/
	private String userName;
	
	/**认证时间*/
	private String createDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
