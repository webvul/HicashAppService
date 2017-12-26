package com.hengyuan.hicash.entity.user;

import java.util.Date;

public class CustUserEntity {

	// 用户名
	private String username;
	// 昵称
	private String nickName;
	// 密码
	private String password;
	// 密码加密salt
	private String salt;
	// 邮箱
	private String emailAddress;
	// 用户是否可用
	private Boolean enabled;
	// 邮箱验证字符串
	private String emailValidateStr;
	// 邮箱验证字符串有效期
	private Date emailValidateStrValidTime;
	// 手机验证码
	private String mobileValidateCode;
	// 手机验证码有效期
	private String mobileValidateCodeValidTime;
	// 用户认证分数 暂未使用
	private Integer certificationScore;
	// 用户认证等级 暂未使用
	private String certificationLevel;
	// 用户信用分数 暂未使用
	private Integer creditScore;
	// 用户信用等级 暂未使用
	private String creditLevel;
	// 跑批时间，暂未使用
	private Date batchTime;
	// 最近登录连续验证错误次数
	private int locked;
	// 手机号码
	private String mobileNo;
	// 最近一次登录失败时间
	private Date lastLoginTime;
	// 邮件中的重置密码验证字符串
	private String passwordEmailValidateStr;
	// 邮件中的重置密码验证字符串有效期
	private Date passwordEmailValidateStrValidTime;
	private String token;
	// 信息完成度 暂未使用
	private Integer infoCompletePercent;
	// 认证完成度 暂未使用
	private Integer certyPercent;
	// 信息完成分数 暂未使用
	private Integer infoScore;
	// 邀请人 暂未使用
	private String inviter;

	private String platformMark;// 平台标记

	private String fenQRegisterCode;// fenqimall注册的邀请码

	// 授信额度验证码
	private String amountValidateCode;

	// 授信额度验证码有效时间
	private String amountValidateCodeTime;

	// 接收授信额度验证码的手机号码
	private String amountValidateTempMobile;

	/** 认证标志 */
	private String loginToken;

	/** 页面索引 */
	private String pageIndex;

	/** 是否是二次营销 */
	private String isDoubleSales;

	/** 是否已经抽过奖 */
	private int isLottery;

	/** 剩余抽奖次数 */
	private int lotteryNum;

	private String createTime;

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public int getIsLottery() {
		return isLottery;
	}

	public void setIsLottery(int isLottery) {
		this.isLottery = isLottery;
	}

	public int getLotteryNum() {
		return lotteryNum;
	}

	public void setLotteryNum(int lotteryNum) {
		this.lotteryNum = lotteryNum;
	}

	public String getIsDoubleSales() {
		return isDoubleSales;
	}

	public void setIsDoubleSales(String isDoubleSales) {
		this.isDoubleSales = isDoubleSales;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getEmailValidateStr() {
		return emailValidateStr;
	}

	public void setEmailValidateStr(String emailValidateStr) {
		this.emailValidateStr = emailValidateStr;
	}

	public Date getEmailValidateStrValidTime() {
		return emailValidateStrValidTime;
	}

	public void setEmailValidateStrValidTime(Date emailValidateStrValidTime) {
		this.emailValidateStrValidTime = emailValidateStrValidTime;
	}

	public String getMobileValidateCode() {
		return mobileValidateCode;
	}

	public void setMobileValidateCode(String mobileValidateCode) {
		this.mobileValidateCode = mobileValidateCode;
	}

	public String getMobileValidateCodeValidTime() {
		return mobileValidateCodeValidTime;
	}

	public void setMobileValidateCodeValidTime(
			String mobileValidateCodeValidTime) {
		this.mobileValidateCodeValidTime = mobileValidateCodeValidTime;
	}

	public Integer getCertificationScore() {
		return certificationScore;
	}

	public void setCertificationScore(Integer certificationScore) {
		this.certificationScore = certificationScore;
	}

	public String getCertificationLevel() {
		return certificationLevel;
	}

	public void setCertificationLevel(String certificationLevel) {
		this.certificationLevel = certificationLevel;
	}

	public Integer getCreditScore() {
		return creditScore;
	}

	public void setCreditScore(Integer creditScore) {
		this.creditScore = creditScore;
	}

	public String getCreditLevel() {
		return creditLevel;
	}

	public void setCreditLevel(String creditLevel) {
		this.creditLevel = creditLevel;
	}

	public Date getBatchTime() {
		return batchTime;
	}

	public void setBatchTime(Date batchTime) {
		this.batchTime = batchTime;
	}

	public int getLocked() {
		return locked;
	}

	public void setLocked(int locked) {
		this.locked = locked;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getPasswordEmailValidateStr() {
		return passwordEmailValidateStr;
	}

	public void setPasswordEmailValidateStr(String passwordEmailValidateStr) {
		this.passwordEmailValidateStr = passwordEmailValidateStr;
	}

	public Date getPasswordEmailValidateStrValidTime() {
		return passwordEmailValidateStrValidTime;
	}

	public void setPasswordEmailValidateStrValidTime(
			Date passwordEmailValidateStrValidTime) {
		this.passwordEmailValidateStrValidTime = passwordEmailValidateStrValidTime;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getInfoCompletePercent() {
		return infoCompletePercent;
	}

	public void setInfoCompletePercent(Integer infoCompletePercent) {
		this.infoCompletePercent = infoCompletePercent;
	}

	public Integer getCertyPercent() {
		return certyPercent;
	}

	public void setCertyPercent(Integer certyPercent) {
		this.certyPercent = certyPercent;
	}

	public Integer getInfoScore() {
		return infoScore;
	}

	public void setInfoScore(Integer infoScore) {
		this.infoScore = infoScore;
	}

	public String getInviter() {
		return inviter;
	}

	public void setInviter(String inviter) {
		this.inviter = inviter;
	}

	public String getPlatformMark() {
		return platformMark;
	}

	public void setPlatformMark(String platformMark) {
		this.platformMark = platformMark;
	}

	public String getFenQRegisterCode() {
		return fenQRegisterCode;
	}

	public void setFenQRegisterCode(String fenQRegisterCode) {
		this.fenQRegisterCode = fenQRegisterCode;
	}

	public String getAmountValidateCode() {
		return amountValidateCode;
	}

	public void setAmountValidateCode(String amountValidateCode) {
		this.amountValidateCode = amountValidateCode;
	}

	public String getAmountValidateCodeTime() {
		return amountValidateCodeTime;
	}

	public void setAmountValidateCodeTime(String amountValidateCodeTime) {
		this.amountValidateCodeTime = amountValidateCodeTime;
	}

	public String getAmountValidateTempMobile() {
		return amountValidateTempMobile;
	}

	public void setAmountValidateTempMobile(String amountValidateTempMobile) {
		this.amountValidateTempMobile = amountValidateTempMobile;
	}

	public String getLoginToken() {
		return loginToken;
	}

	public void setLoginToken(String loginToken) {
		this.loginToken = loginToken;
	}

	public String getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(String pageIndex) {
		this.pageIndex = pageIndex;
	}

}
