package com.hengyuan.hicash.entity.user;


/**
 * 用户联系信息绑定验证表
 * 
 * @author Eric
 * @create date 2014-08-19
 * @table cust_certification
 */
public class CustCertificationEntity {
	
	/* 用户名 */
	private String username;
	
	/* 验证类型 */
	private String certificationType;
	
	/* 验证结果 */
	private String status;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCertificationType() {
		return certificationType;
	}

	public void setCertificationType(String certificationType) {
		this.certificationType = certificationType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
