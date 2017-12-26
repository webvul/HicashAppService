package com.hengyuan.hicash.entity.user;

/**
 * 后台用户表
 * 
 * @author Andy.Niu
 * @create 2014-08-07
 */
public class ApproveUser {

	/** 主键 */
	private String id;

	/** 用户名 */
	private String userName;

	/** 密码 */
	private String passWord;

	/** 加密字符串 */
	private String salt;

	/** 是否可用 */
	private String enabled;

	/** 真实姓名 */
	private String realName;

	/** 逻辑码 */
	private String logicCode;

	/** 电话号码 */
	private String mobileNo;

	/** 邮箱地址 */
	private String emailAddress;

	/** 用户类型 */
	private String userType;

	/** 员工号 */
	private String userNumber;

	/** 省份 */
	private String provice;

	/** 城市代码 */
	private String cityCode;

	/** 地区 */
	private String area;

	/** 商户ID */
	private String supInfoId;

	/** 所属售点 */
	private String saleSite;

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getProvice() {
		return provice;
	}

	public void setProvice(String provice) {
		this.provice = provice;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getSupInfoId() {
		return supInfoId;
	}

	public void setSupInfoId(String supInfoId) {
		this.supInfoId = supInfoId;
	}

	public String getSaleSite() {
		return saleSite;
	}

	public void setSaleSite(String saleSite) {
		this.saleSite = saleSite;
	}

	public String getLogicCode() {
		return logicCode;
	}

	public void setLogicCode(String logicCode) {
		this.logicCode = logicCode;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}

}
