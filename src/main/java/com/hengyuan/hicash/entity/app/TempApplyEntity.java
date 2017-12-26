package com.hengyuan.hicash.entity.app;

/**
 * 临时申请对象
 * 
 * @author Cary.Liu
 *
 */
public class TempApplyEntity {

	private String id;

	/** 临时申请单号 */
	private String tempAppNo;

	/** 客户用户名 */
	private String userName;

	/** 申请价格 */
	private String applyPrice;

	/** 信贷产品 */
	private String loanProduct;

	/** 用户手持身份证正面照 */
	private String userIdcardFrontUrl;

	/** 用户手持身份证正面照缩略图 */
	private String userIdcardFrontUrlThum;

	/** 身份证正面照 */
	private String idcardFrontUrl;

	/** 身份证正面照缩略图 */
	private String idcardFrontUrlThum;

	/** 身份证反面照 */
	private String idcardVersoUrl;

	/** 身份证反面照缩略图 */
	private String idcardVersoUrlThum;

	/** 创建时间 */
	private String createDate;
	/*电信*/
	private String phoneNo;//用户选择的手机号
    private String phoneDataId;//套餐Id
    private String proDataName;//商品名称（名称+颜色）
	/** 0 填写资料 1 真实申请件创建成功（假待审批状态） 2 交易关闭 3 已提交至审批 */
	private String createAppFlg;
	//打点
	private String validateType;
	//引流来源
	private String imDrainage; 
	
	
	public String getImDrainage() {
		return imDrainage;
	}

	public void setImDrainage(String imDrainage) {
		this.imDrainage = imDrainage;
	}
	
	

	public String getUserIdcardFrontUrlThum() {
		return userIdcardFrontUrlThum;
	}

	public void setUserIdcardFrontUrlThum(String userIdcardFrontUrlThum) {
		this.userIdcardFrontUrlThum = userIdcardFrontUrlThum;
	}

	public String getIdcardFrontUrlThum() {
		return idcardFrontUrlThum;
	}

	public void setIdcardFrontUrlThum(String idcardFrontUrlThum) {
		this.idcardFrontUrlThum = idcardFrontUrlThum;
	}

	public String getIdcardVersoUrlThum() {
		return idcardVersoUrlThum;
	}

	public void setIdcardVersoUrlThum(String idcardVersoUrlThum) {
		this.idcardVersoUrlThum = idcardVersoUrlThum;
	}

	public String getCreateAppFlg() {
		return createAppFlg;
	}

	public void setCreateAppFlg(String createAppFlg) {
		this.createAppFlg = createAppFlg;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTempAppNo() {
		return tempAppNo;
	}

	public void setTempAppNo(String tempAppNo) {
		this.tempAppNo = tempAppNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getApplyPrice() {
		return applyPrice;
	}

	public void setApplyPrice(String applyPrice) {
		this.applyPrice = applyPrice;
	}

	public String getLoanProduct() {
		return loanProduct;
	}

	public void setLoanProduct(String loanProduct) {
		this.loanProduct = loanProduct;
	}

	public String getUserIdcardFrontUrl() {
		return userIdcardFrontUrl;
	}

	public void setUserIdcardFrontUrl(String userIdcardFrontUrl) {
		this.userIdcardFrontUrl = userIdcardFrontUrl;
	}

	public String getIdcardFrontUrl() {
		return idcardFrontUrl;
	}

	public void setIdcardFrontUrl(String idcardFrontUrl) {
		this.idcardFrontUrl = idcardFrontUrl;
	}

	public String getIdcardVersoUrl() {
		return idcardVersoUrl;
	}

	public void setIdcardVersoUrl(String idcardVersoUrl) {
		this.idcardVersoUrl = idcardVersoUrl;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the phoneNo
	 */
	public String getPhoneNo() {
		return phoneNo;
	}

	/**
	 * @param phoneNo the phoneNo to set
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	/**
	 * @return the phoneDataId
	 */
	public String getPhoneDataId() {
		return phoneDataId;
	}

	/**
	 * @param phoneDataId the phoneDataId to set
	 */
	public void setPhoneDataId(String phoneDataId) {
		this.phoneDataId = phoneDataId;
	}

	/**
	 * @return the proDataName
	 */
	public String getProDataName() {
		return proDataName;
	}

	/**
	 * @param proDataName the proDataName to set
	 */
	public void setProDataName(String proDataName) {
		this.proDataName = proDataName;
	}

	public String getValidateType() {
		return validateType;
	}

	public void setValidateType(String validateType) {
		this.validateType = validateType;
	}

}
