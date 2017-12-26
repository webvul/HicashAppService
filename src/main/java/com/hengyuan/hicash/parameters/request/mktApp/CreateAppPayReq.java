package com.hengyuan.hicash.parameters.request.mktApp;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 
 * 创建申请件接收参数对象
 * 
 * @author Cary.Liu
 * @create date 2014-09-24
 * 
 */

public class CreateAppPayReq extends RequestSequence {

	private static final long serialVersionUID = 1L;

	/** 返点比率 */
	private String rebate;

	/** 客户用户名 */
	private String userName;

	/** 产品ID */
	private String proId;

	/** 交易金额 */
	private String tranPrice;// 商品价格除期数

	/** 销售码 */
	private String saleCode;

	/** 申请方式 */
	private String applyType;
	// /** 申请类型-现金 */申请方式为现金
	// public static String APPFLOW_TYPE_CASH = "CASH";

	/** 客户类型 */
	private String custType;

	/** 首付比率 */
	private String firstRate;

	/** 贷款产品ID */
	private String loanProduct;// 期数ID

	/** 产品名称 */
	private String productName;

	/** 省份代码 */
	private String province;

	/** 城市代码 */
	private String city;

	/** 开户行对应数据库ID */
	private String bankNo;

	/** 毕业时间 */
	private String endSchoolTime;

	/** 商户ID */
	private String supplierId;

	/** 售点ID */
	private String siteId;

	/** 商户输入商品总价 */
	private String merProPrice;

	/** 商户产品ID */
	private String merProId;

	/* 嗨秒贷请求参数 */

	/** 临时申请件单号 */
	private String tempAppNo;

	/** 开户人姓名 */
	private String bankRealName;

	/** 开户行 */
	private String openBank;

	/** 银行卡号 */
	private String bankCard;

	/** 是否同步到个人收款账户 */
	private String isSynPerAcct;

	/** 是否为嗨秒贷申请 */
	private String mdFlag;// 是嗨秒贷则传1，保存银行卡卡号，开户行

	/** 申请来源 */
	private String applyFrom;

	private String browserStr;

	/* 电信专案手机 */

	// /** 商品价格*/
	// private String proPrice;

	/** 商品颜色 */
	private String proColor;

	// /** 选择期数 */
	// private String proPeriod;

	/** 是否为电信申请 */
	private String isDx;
	/** 套餐ID */
	private String phoneDataId;
	/** 电话号码 */
	private String phoneNum;

	// 增加推荐人信息
	/** 推荐人 */
	private String reference;
	/** 被推荐人 */
	private String recommend;
	/** 推荐时间 */
	private String referenceTime;
	/** 推荐业务：申请，注册 */
	private String bussiness;
	/** 来源页面 */
	private String upPage;
	/** ip */
	private String referenceIP;

	/** 是否进行同盾验证 */
	private String isTdValidate;

	/** 同盾验证token */
	private String tdToken;

	/** 申请来源（第三方） */
	private String custAppFrom;

	/** 活动来源 */
	private String activeFrom;
	
	/**申请件邀请码*/
	private String custInvitecode;
	/**
	 * 2017-08嗨钱迭代，去掉支行
	 * 开户支行
	 */
//	private String openBankBranch;
	/**
	 * 开户城市-区
	 */
	private String area;

	private String coupon_id;
	
	private String imDrainage; //引流来源
	

	
	
	public String getImDrainage() {
		return imDrainage;
	}

	public void setImDrainage(String imDrainage) {
		this.imDrainage = imDrainage;
	}
	
	
//	
//	public String getOpenBankBranch() {
//		return openBankBranch;
//	}
//
//	public void setOpenBankBranch(String openBankBranch) {
//		this.openBankBranch = openBankBranch;
//	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getActiveFrom() {
		return activeFrom;
	}

	public void setActiveFrom(String activeFrom) {
		this.activeFrom = activeFrom;
	}

	public String getCustAppFrom() {
		return custAppFrom;
	}

	public void setCustAppFrom(String custAppFrom) {
		this.custAppFrom = custAppFrom;
	}

	public String getIsTdValidate() {
		return isTdValidate;
	}

	public void setIsTdValidate(String isTdValidate) {
		this.isTdValidate = isTdValidate;
	}

	public String getTdToken() {
		return tdToken;
	}

	public void setTdToken(String tdToken) {
		this.tdToken = tdToken;
	}

	public String getBrowserStr() {
		return browserStr;
	}

	public void setBrowserStr(String browserStr) {
		this.browserStr = browserStr;
	}

	public String getApplyFrom() {
		return applyFrom;
	}

	public void setApplyFrom(String applyFrom) {
		this.applyFrom = applyFrom;
	}

	public String getMdFlag() {
		return mdFlag;
	}

	public void setMdFlag(String mdFlag) {
		this.mdFlag = mdFlag;
	}

	public String getRebate() {
		return rebate;
	}

	public void setRebate(String rebate) {
		this.rebate = rebate;
	}

	public String getTempAppNo() {
		return tempAppNo;
	}

	public void setTempAppNo(String tempAppNo) {
		this.tempAppNo = tempAppNo;
	}

	public String getBankRealName() {
		return bankRealName;
	}

	public void setBankRealName(String bankRealName) {
		this.bankRealName = bankRealName;
	}

	public String getOpenBank() {
		return openBank;
	}

	public void setOpenBank(String openBank) {
		this.openBank = openBank;
	}

	public String getBankCard() {
		return bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	public String getIsSynPerAcct() {
		return isSynPerAcct;
	}

	public void setIsSynPerAcct(String isSynPerAcct) {
		this.isSynPerAcct = isSynPerAcct;
	}

	public String getMerProId() {
		return merProId;
	}

	public void setMerProId(String merProId) {
		this.merProId = merProId;
	}

	public String getMerProPrice() {
		return merProPrice;
	}

	public void setMerProPrice(String merProPrice) {
		this.merProPrice = merProPrice;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}

	public String getSaleCode() {
		return saleCode;
	}

	public void setSaleCode(String saleCode) {
		this.saleCode = saleCode;
	}

	public String getApplyType() {
		return applyType;
	}

	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public String getFirstRate() {
		return firstRate;
	}

	public void setFirstRate(String firstRate) {
		this.firstRate = firstRate;
	}

	public String getLoanProduct() {
		return loanProduct;
	}

	public void setLoanProduct(String loanProduct) {
		this.loanProduct = loanProduct;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public String getTranPrice() {
		return tranPrice;
	}

	public void setTranPrice(String tranPrice) {
		this.tranPrice = tranPrice;
	}

	public String getEndSchoolTime() {
		return endSchoolTime;
	}

	public void setEndSchoolTime(String endSchoolTime) {
		this.endSchoolTime = endSchoolTime;
	}

	// /**
	// * @return the proColor
	// */
	// public String getProColor() {
	// return proColor;
	// }
	//
	// /**
	// * @param proColor the proColor to set
	// */
	// public void setProColor(String proColor) {
	// this.proColor = proColor;
	// }

	// /**
	// * @return the proPeriod
	// */
	// public String getProPeriod() {
	// return proPeriod;
	// }
	//
	// /**
	// * @param proPeriod the proPeriod to set
	// */
	// public void setProPeriod(String proPeriod) {
	// this.proPeriod = proPeriod;
	// }

	/**
	 * @return the isDx
	 */
	public String getIsDx() {
		return isDx;
	}

	/**
	 * @param isDx
	 *            the isDx to set
	 */
	public void setIsDx(String isDx) {
		this.isDx = isDx;
	}

	/**
	 * @return the phoneDataId
	 */
	public String getPhoneDataId() {
		return phoneDataId;
	}

	/**
	 * @param phoneDataId
	 *            the phoneDataId to set
	 */
	public void setPhoneDataId(String phoneDataId) {
		this.phoneDataId = phoneDataId;
	}

	/**
	 * @return the phoneNum
	 */
	public String getPhoneNum() {
		return phoneNum;
	}

	/**
	 * @param phoneNum
	 *            the phoneNum to set
	 */
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	/**
	 * @return the proColor
	 */
	public String getProColor() {
		return proColor;
	}

	/**
	 * @param proColor
	 *            the proColor to set
	 */
	public void setProColor(String proColor) {
		this.proColor = proColor;
	}

	/**
	 * @return the reference
	 */
	public String getReference() {
		return reference;
	}

	/**
	 * @param reference
	 *            the reference to set
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}

	/**
	 * @return the recommend
	 */
	public String getRecommend() {
		return recommend;
	}

	/**
	 * @param recommend
	 *            the recommend to set
	 */
	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}

	/**
	 * @return the referenceTime
	 */
	public String getReferenceTime() {
		return referenceTime;
	}

	/**
	 * @param referenceTime
	 *            the referenceTime to set
	 */
	public void setReferenceTime(String referenceTime) {
		this.referenceTime = referenceTime;
	}

	/**
	 * @return the bussiness
	 */
	public String getBussiness() {
		return bussiness;
	}

	/**
	 * @param bussiness
	 *            the bussiness to set
	 */
	public void setBussiness(String bussiness) {
		this.bussiness = bussiness;
	}

	/**
	 * @return the upPage
	 */
	public String getUpPage() {
		return upPage;
	}

	/**
	 * @param upPage
	 *            the upPage to set
	 */
	public void setUpPage(String upPage) {
		this.upPage = upPage;
	}

	/**
	 * @return the referenceIP
	 */
	public String getReferenceIP() {
		return referenceIP;
	}

	/**
	 * @param referenceIP
	 *            the referenceIP to set
	 */
	public void setReferenceIP(String referenceIP) {
		this.referenceIP = referenceIP;
	}

	public String getCustInvitecode() {
		return custInvitecode;
	}

	public void setCustInvitecode(String custInvitecode) {
		this.custInvitecode = custInvitecode;
	}

	public String getCoupon_id() {
		return coupon_id;
	}

	public void setCoupon_id(String coupon_id) {
		this.coupon_id = coupon_id;
	}
	

}
