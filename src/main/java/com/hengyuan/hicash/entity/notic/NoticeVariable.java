package com.hengyuan.hicash.entity.notic;

/**
 * 发送短信公共类
 * 
 * @author Eric
 * @create date 2014-07-22
 *
 */
public class NoticeVariable {

	/** 用户名*/
	private String username;
	
	/** 真实姓名*/
	private String realname;
	
	/** 发送邮件变量 START*/
	private String validateUrl;
	/** 发送邮件变量 END*/
	
	/** 手机号*/
	private String telphone;
	
	/** 手机绑定，发送手机认证CODE */
	private String mobileValidateCode;
	
	/** 注册EMAIL */
	private String email;
	
	private String emailAccessUrl;
	
	/** 发布借款URL */
	private String publishLoanUrl;

	/** 开始投标URL */
	private String investUrl;
	
	/** 操作时间 */
	private String operateTime;
	
	/** 借款信息页面URL */
	private String loanDetailUrl;
	
	/** 上传材料页面URL */
	private String uploadMaterialslUrl;
	
	/** 借款标题 */
	private String loanTitle;
	
	/** 借款进度 */
	private String percent;
	
	/** 操作金额 */
	private String operateAmt;

	/**已还金额 */
	private String repayedAmt;
	
	/** 原因 */
	private String reason;
	
	/** 有效时间 */
	private Integer effectTimes;
	
	/** 密码 */
	private String password;
	
	/** 产品 */
	private String product;
	
	/** 项目名称 */
	private String projectname;
	
	/** 借款编号 */
	private String lendNo;
	
	/** 销售代表 */
	private String salesrepresent;
	
	/** 产品型号 */
	private String productsize;
	
	
	/** 商户名称*/
	private String bussName;
	
	/** 商户名称*/
	private String bussAddr;
	
	/** 商户名称*/
	private String bussMobile;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getValidateUrl() {
		return validateUrl;
	}

	public void setValidateUrl(String validateUrl) {
		this.validateUrl = validateUrl;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getMobileValidateCode() {
		return mobileValidateCode;
	}

	public void setMobileValidateCode(String mobileValidateCode) {
		this.mobileValidateCode = mobileValidateCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmailAccessUrl() {
		return emailAccessUrl;
	}

	public void setEmailAccessUrl(String emailAccessUrl) {
		this.emailAccessUrl = emailAccessUrl;
	}

	public String getPublishLoanUrl() {
		return publishLoanUrl;
	}

	public void setPublishLoanUrl(String publishLoanUrl) {
		this.publishLoanUrl = publishLoanUrl;
	}

	public String getInvestUrl() {
		return investUrl;
	}

	public void setInvestUrl(String investUrl) {
		this.investUrl = investUrl;
	}

	public String getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}

	public String getLoanDetailUrl() {
		return loanDetailUrl;
	}

	public void setLoanDetailUrl(String loanDetailUrl) {
		this.loanDetailUrl = loanDetailUrl;
	}

	public String getUploadMaterialslUrl() {
		return uploadMaterialslUrl;
	}

	public void setUploadMaterialslUrl(String uploadMaterialslUrl) {
		this.uploadMaterialslUrl = uploadMaterialslUrl;
	}

	public String getLoanTitle() {
		return loanTitle;
	}

	public void setLoanTitle(String loanTitle) {
		this.loanTitle = loanTitle;
	}

	public String getPercent() {
		return percent;
	}

	public void setPercent(String percent) {
		this.percent = percent;
	}

	public String getOperateAmt() {
		return operateAmt;
	}

	public void setOperateAmt(String operateAmt) {
		this.operateAmt = operateAmt;
	}

	public String getRepayedAmt() {
		return repayedAmt;
	}

	public void setRepayedAmt(String repayedAmt) {
		this.repayedAmt = repayedAmt;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Integer getEffectTimes() {
		return effectTimes;
	}

	public void setEffectTimes(Integer effectTimes) {
		this.effectTimes = effectTimes;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

	public String getLendNo() {
		return lendNo;
	}

	public void setLendNo(String lendNo) {
		this.lendNo = lendNo;
	}

	public String getSalesrepresent() {
		return salesrepresent;
	}

	public void setSalesrepresent(String salesrepresent) {
		this.salesrepresent = salesrepresent;
	}

	public String getProductsize() {
		return productsize;
	}

	public void setProductsize(String productsize) {
		this.productsize = productsize;
	}

	public String getBussName() {
		return bussName;
	}

	public void setBussName(String bussName) {
		this.bussName = bussName;
	}

	public String getBussAddr() {
		return bussAddr;
	}

	public void setBussAddr(String bussAddr) {
		this.bussAddr = bussAddr;
	}

	public String getBussMobile() {
		return bussMobile;
	}

	public void setBussMobile(String bussMobile) {
		this.bussMobile = bussMobile;
	}
	
}
