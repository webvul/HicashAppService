package com.hengyuan.hicash.entity.user;

import java.math.BigDecimal;

public class CustLimit {

	// 用户姓名
	private String userName;
	// 申请额度
	private BigDecimal applyAmt;
	// 授信额度
	private BigDecimal trustAmt;
	// 可用额度
	private BigDecimal useAmt;
	// 最高额度
	private BigDecimal maxAmt;
	// 冻结额度
	private BigDecimal blockAmt;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public BigDecimal getApplyAmt() {
		return applyAmt;
	}

	public void setApplyAmt(BigDecimal applyAmt) {
		this.applyAmt = applyAmt;
	}

	public BigDecimal getTrustAmt() {
		return trustAmt;
	}

	public void setTrustAmt(BigDecimal trustAmt) {
		this.trustAmt = trustAmt;
	}

	public BigDecimal getUseAmt() {
		return useAmt;
	}

	public void setUseAmt(BigDecimal useAmt) {
		this.useAmt = useAmt;
	}

	public BigDecimal getMaxAmt() {
		return maxAmt;
	}

	public void setMaxAmt(BigDecimal maxAmt) {
		this.maxAmt = maxAmt;
	}

	public BigDecimal getBlockAmt() {
		return blockAmt;
	}

	public void setBlockAmt(BigDecimal blockAmt) {
		this.blockAmt = blockAmt;
	}

}
