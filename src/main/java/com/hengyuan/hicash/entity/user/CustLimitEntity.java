package com.hengyuan.hicash.entity.user;


public class CustLimitEntity {

	// 用户姓名
	private String userName;
	// 申请额度
	private String applyAmt;
	// 授信额度
	private String trustAmt;
	// 可用额度
	private String useAmt;
	// 最高额度
	private String maxAmt;
	// 冻结额度
	private String blockAmt;
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getApplyAmt() {
		return applyAmt;
	}
	public void setApplyAmt(String applyAmt) {
		this.applyAmt = applyAmt;
	}
	public String getTrustAmt() {
		return trustAmt;
	}
	public void setTrustAmt(String trustAmt) {
		this.trustAmt = trustAmt;
	}
	public String getUseAmt() {
		return useAmt;
	}
	public void setUseAmt(String useAmt) {
		this.useAmt = useAmt;
	}
	public String getMaxAmt() {
		return maxAmt;
	}
	public void setMaxAmt(String maxAmt) {
		this.maxAmt = maxAmt;
	}
	public String getBlockAmt() {
		return blockAmt;
	}
	public void setBlockAmt(String blockAmt) {
		this.blockAmt = blockAmt;
	}
		
		
		
}
