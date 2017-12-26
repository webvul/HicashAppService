package com.hengyuan.hicash.entity.app;

import java.util.Date;

/**
 * 前台申请单
 * 
 * @author Cary.Liu
 * @create 2014-09-25
 */
public class ApplicationPay {

	private String createUser;

	/** 申请单序号 */
	private String applicationNo;

	/** 申请件所属逻辑ID */
	private String logicCode;

	private String updateDate;

	private String createDate;

	/** 申请产品 */
	private String productInfo;

	/** 申请件节点 */
	private String applicationNode;

	/** 申请状态 */
	private String applicationStatus;

	/** 申请状态-申请中， 还款中，申请完成 */
	private String appStage;

	/** 月还款 */
	private String monthPay;

	/** 还款期数 */
	private String installMent;

	/** 申请人 */
	private String username;

	/** 方便联系日期 */
	private String convenDate;

	/** 方便联系时间(数据字典) */
	private String convenTime;

	/** 预约日期 */
	private String reserveDate;

	/** 预约时间(数据字典) */
	private String reserveTime;

	/** 信贷产品ID */
	private String creditProductId;

	/** 原始的申请贷款产品 */
	private String oldCreditProduct;

	/** 确切联系时间 */
	private Date contactTime;

	/** 电话客服名 */
	private String telephoneCus;

	/** 现场服务销售 */
	private String saleServer;

	/** 现场销售代表代码 */
	private String saleCode;

	/** 申请的产品颜色 */
	private String productColor;

	/** 致电描述 */
	private String telDesc;

	/** 用户手机号 */
	private String userMobile;

	/** 用户身份证号码 */
	private String userIdentityNo;

	/** 用户真实姓名 */
	private String userRealName;

	/** 电话预约结果 */
	private String telResult;

	/** 电话客服开始联系时间 */
	private Date telStartTime;

	/** 电话客服结束联系时间 */
	private Date telEndTime;

	/** 修改联系时间次数 */
	private Integer editTime;

	/** 是否命中黑名单 */
	private Boolean inBlackList;

	/** 命中黑名单原因描述 */
	private String blackListDesc;

	/** 现场录入岗人员 */
	private String siteName;

	/** 到达现场服务岗时间 */
	private Date siteStartTime;

	/** 现场服务岗结束时间 */
	private Date siteEndTime;

	/** 图片描述 */
	private String picDesc;

	/** 申请单结束时间 */
	private Date appEndTime;

	/* 初审 */

	/** 初审人员 */
	private String approvalName;

	/** 初审意见 */
	private String approvalDesc;

	/** 初审开始时间 */
	private Date approvalStartTime;

	/** 初审结束时间 */
	private Date approvalEndTime;

	/** 初审建议拒绝原因码 */
	private String firstDenyingCode;

	/** 教育情况 */
	private String approvalDesc1;

	/** 家庭情况 */
	private String approvalDesc2;

	/** 生活费及开销情况 */
	private String approvalDesc3;

	/** 兴趣爱好 */
	private String approvalDesc4;

	/* 电核 */

	/** 电核人 */
	private String electricName;

	/** 电核描述 */
	private String electricDesc;

	/** 电核开始时间 */
	private Date electricStartTime;

	/** 电核结束时间 */
	private Date electricEndTime;

	/** 电核建议拒绝原因 */
	private String electricDenyingCode;

	/* 终审 */

	/** 终审人 */
	private String faceName;

	/** 终审意见 */
	private String faceDesc;

	/** 终审开始时间 */
	private Date faceStartTime;

	/** 终审结束时间 */
	private Date faceEndTime;

	/** 终审拒绝原因 */
	private String faceRefause;

	/* 合同岗 */

	/** 合同岗操作人 */
	private String contractName;

	/** 合同岗描述 */
	private String contractDesc;

	/** 合同岗拒绝原因 */
	private String contractRefause;

	/** 到达合同岗时间 */
	private Date contractStartTime;

	/** 离开合同岗时间 */
	private Date contractEndTime;

	/* 特权审批岗 */

	/** 特权审批人 */
	private String privilegeName;

	/** 特权审批意见 */
	private String privilegeDesc;

	/** 特权审批开始时间 */
	private Date privilegeStartTime;

	/** 特权审批结束时间 */
	private Date privilegeEndTime;

	/** 特权审批拒绝原因码 */
	private String privilegeRefause;

	/* 提货岗 */

	/** 提货岗操作人 */
	private String deliveryName;

	/** 提货岗描述 */
	private String deliveryDesc;

	/** 到达提货岗时间 */
	private Date deliveryStartTime;

	/** 离开提货岗时间 */
	private Date deliveryEndTime;

	/** 提货岗拒绝原因代码 */
	private String deliveryRefause;

	/* 分案描述 */

	/** 电话客服主管分案描述 */
	private String telDescCharge;

	/** 电话客服主管 */
	private String telDescName;

	/** 现场录入人员意见 */
	private String siteDescCharge;

	/** 销售主管分案描述 */
	private String saleDescCharge;

	/** 销售主管 */
	private String saleDescName;

	/** 审批主管分案描述 */
	private String approvalDescCharge;

	/** 审批主管 */
	private String approvalDescName;

	/* 流程归档 */

	/** 归档人 */
	private String closeName;

	/** 归档描述 */
	private String closeDesc;

	/** 流程归档开始时间 */
	private Date closeStartTime;

	/** 流程归档结束时间 */
	private Date closeEndTime;

	/* 标志位 */

	/** 合同是否已经打印过 */
	private Boolean contractFlag;

	/** 提货单是否打印过 */
	private Boolean deliveryFlag;

	/** 所属城市 */
	private String cityCode;

	/** 所属售点 */
	private String saleSite;

	/** 售点ID */
	private String siteCode;

	/** 商户ID */
	private String supplier;

	/** 商户名称 */
	private String supplierName;

	/* 提货岗 */

	/** 提货岗操作人 */
	private String receiveName;

	/** 开始提货时间 */
	private Date receiveStartTime;

	/** 提货完成时间 */
	private Date receiveEndTime;

	/** 提货描述 */
	private String receiveDesc;

	/** 提货码 */
	private String receiveCode;

	/** 是否已经提货 */
	private Boolean receiveFlag;

	/** 提货码是否已经验证通过 */
	private Boolean receiveCodeFlag;

	/** 是否已经归档 */
	private Boolean closeFlag;

	/* 提货确认岗 */

	/** 提货确认人名称 */
	private String checkName;

	/** 确认描述 */
	private String checkDesc;

	/** 提货确认开始时间 */
	private Date checkStartTime;

	/** 提货确认结束时间 */
	private Date checkEndTime;

	/** 提货是否已经确认 */
	private Boolean checkFlag;

	/** 申请信息是否已经提交 */
	private Boolean submitFlag;

	/** 资料是否已经上传 */
	private Boolean uploadFlag;

	/** 首期付款是否已经支付 */
	private Boolean payMentFlag;

	/** 客户类型 */
	private String customerType;

	/** 申请学校 */
	private String address;

	/** 年级，工作年限 */
	private String gradeWork;

	/** 申请折扣 */
	private String disCount;

	/*
	 * 2013-07-07增加 记录申请产品的规格信息 产品颜色延用以前的字段 skyncp
	 */
	/** 产品行业 */
	private String industryCode;

	/** 产品类型 */
	private String productType;

	/** 产品品牌 */
	private String brandShow;

	/** 手机机型 */
	private String paraTypeShow;

	/** 运营商 */
	private String operatorMerShow;

	/** pad机型 */
	private String padTypeShow;

	/** 电子产品内存 */
	private String flashSizeShow;

	/** CPU */
	private String cpuShow;

	/** 产品硬盘 */
	private String hardDriveShow;

	/** 镜头 */
	private String lensTypeShow;

	/** 成交价格 */
	private String tranPrice;

	/** 商户产品ID */
	private String merProId;

	/** 申请件是否已成交 */
	private Boolean compFlag;

	private String provice;

	private String area;

	/** 协议是否已经上传 */
	private Boolean treatyUploadFlag;

	/** 协议URL */
	private String treatyUploadURL;

	/** 旅游产品定金 */
	private String earnestMoney;

	/** 已付旅游产品定金 */
	private String payedEarnestMoney;

	/** 申请历史是否被删除 */
	private Boolean historyConfig;

	/** M0 电话状态 */
	private String moStatus;

	/** M0电话备注 */
	private String moInfo;

	/** M0电话操作时间 */
	private Date operatingDate;

	/*
	 * 新商户流程节点状态字段
	 * 
	 * 2013.11.01更新
	 */

	/** 所在节点 */
	private String node;
	/** 存流程节点 **/

	/** 状态 */
	private String status;
	/** 存流程状态 **/

	/** 所有节点 */
	private String allNode;
	/** 存大节点 **/

	/** 还款日期 */
	private String repaymentDate;

	/* 2014.2.8 add by andy.niu */
	/** 投资状态 */
	private String inverstStatus;

	/** 首付比率 */
	private String firstPayRate;

	/** 首付金额 */
	private String firstPayAmount;

	/** 首付本金 */
	private String firstPayPrincipal;

	/*
	 * 新增申请额度字段 andy.niu 2014.03.21 **
	 */
	public String applyAmount;

	/** 申请的推荐码 */
	private String appReferralCode;

	/**
	 * 支付方式 FIRST 比例首付支付 NORMAL 正常首付 CASH 现金贷 add author:colin
	 * 判断该申请是正常协议还是有首付比例的协议
	 * */
	public String payType;

	/**
	 * 投资方式，1为david_fu 0为北银 andy.niu 2014.04.11
	 */
	public Integer investMode;

	/**
	 * 民族性质 汉族 RAC2, 维吾尔族 RAC1, 其他 RAC3
	 * */
	public String nation;

	/** 协议确认日期 **/
	public Date confirmAgreementDate;

	/** 协议是否确认 **/
	public Boolean confirmAgreement;

	/** 产品详情 */
	private String productDetail;

	/** 商户提交申请时间 */
	private String merchAntEndTime;

	/** 判断是客服取消还是前台取消或者审批取消 */
	/** 客服取消为true，审批为false，客户为空 **/
	private Boolean cancel;

	/** 催收分配给的操作员 */
	private String operator;

	/** 催收是否分配 */
	private boolean isallocation;

	/** 催收致电结果 */
	private String collectionResult;

	/** 催收致电备注 */
	private String collectionDesc;

	/** 是否选择商户 */
	private boolean Merchant;

	/** 审批流程结束时间 */
	private String approvalProcessEndDate;

	/** 审批流程结束人员 */
	private String approvalProcessEndName;

	/** 审批流程结束阶段,Y是预审结束，Z是终审结束,S系统结束 */
	private String approvalProcessEndnode;

	/** 终审结果 */
	private String faceResult;

	/** 0 审批拒 1 进件拒-聚 2 进件拒-秉 3 进件拒-同 4 进件拒-自 5鹏元风险评分 6鹏元黑名单 */
	private String refuseFlag;
	
	private String applyFrom;

	public String getRefuseFlag() {
		return refuseFlag;
	}

	public void setRefuseFlag(String refuseFlag) {
		this.refuseFlag = refuseFlag;
	}

	public String getIndustryCode() {
		return industryCode;
	}

	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}

	public String getMerProId() {
		return merProId;
	}

	public void setMerProId(String merProId) {
		this.merProId = merProId;
	}

	public String getFaceResult() {
		return faceResult;
	}

	public void setFaceResult(String faceResult) {
		this.faceResult = faceResult;
	}

	public String getApprovalProcessEndDate() {
		return approvalProcessEndDate;
	}

	public void setApprovalProcessEndDate(String approvalProcessEndDate) {
		this.approvalProcessEndDate = approvalProcessEndDate;
	}

	public String getApprovalProcessEndName() {
		return approvalProcessEndName;
	}

	public void setApprovalProcessEndName(String approvalProcessEndName) {
		this.approvalProcessEndName = approvalProcessEndName;
	}

	public String getApprovalProcessEndnode() {
		return approvalProcessEndnode;
	}

	public void setApprovalProcessEndnode(String approvalProcessEndnode) {
		this.approvalProcessEndnode = approvalProcessEndnode;
	}

	public String getApplicationNo() {
		return applicationNo;
	}

	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}

	public String getLogicCode() {
		return logicCode;
	}

	public void setLogicCode(String logicCode) {
		this.logicCode = logicCode;
	}

	public String getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(String productInfo) {
		this.productInfo = productInfo;
	}

	public void setCreditProductId(String creditProductId) {
		this.creditProductId = creditProductId;
	}

	public void setOldCreditProduct(String oldCreditProduct) {
		this.oldCreditProduct = oldCreditProduct;
	}

	public String getApplicationNode() {
		return applicationNode;
	}

	public void setApplicationNode(String applicationNode) {
		this.applicationNode = applicationNode;
	}

	public String getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	public String getAppStage() {
		return appStage;
	}

	public void setAppStage(String appStage) {
		this.appStage = appStage;
	}

	public String getMonthPay() {
		return monthPay;
	}

	public void setMonthPay(String monthPay) {
		this.monthPay = monthPay;
	}

	public String getInstallMent() {
		return installMent;
	}

	public void setInstallMent(String installMent) {
		this.installMent = installMent;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getConvenDate() {
		return convenDate;
	}

	public void setConvenDate(String convenDate) {
		this.convenDate = convenDate;
	}

	public String getConvenTime() {
		return convenTime;
	}

	public void setConvenTime(String convenTime) {
		this.convenTime = convenTime;
	}

	public String getReserveDate() {
		return reserveDate;
	}

	public void setReserveDate(String reserveDate) {
		this.reserveDate = reserveDate;
	}

	public String getReserveTime() {
		return reserveTime;
	}

	public void setReserveTime(String reserveTime) {
		this.reserveTime = reserveTime;
	}

	public Date getContactTime() {
		return contactTime;
	}

	public void setContactTime(Date contactTime) {
		this.contactTime = contactTime;
	}

	public String getTelephoneCus() {
		return telephoneCus;
	}

	public void setTelephoneCus(String telephoneCus) {
		this.telephoneCus = telephoneCus;
	}

	public String getSaleServer() {
		return saleServer;
	}

	public void setSaleServer(String saleServer) {
		this.saleServer = saleServer;
	}

	public String getSaleCode() {
		return saleCode;
	}

	public void setSaleCode(String saleCode) {
		this.saleCode = saleCode;
	}

	public String getProductColor() {
		return productColor;
	}

	public void setProductColor(String productColor) {
		this.productColor = productColor;
	}

	public String getTelDesc() {
		return telDesc;
	}

	public void setTelDesc(String telDesc) {
		this.telDesc = telDesc;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getUserIdentityNo() {
		return userIdentityNo;
	}

	public void setUserIdentityNo(String userIdentityNo) {
		this.userIdentityNo = userIdentityNo;
	}

	public String getUserRealName() {
		return userRealName;
	}

	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}

	public String getTelResult() {
		return telResult;
	}

	public void setTelResult(String telResult) {
		this.telResult = telResult;
	}

	public Date getTelStartTime() {
		return telStartTime;
	}

	public void setTelStartTime(Date telStartTime) {
		this.telStartTime = telStartTime;
	}

	public Date getTelEndTime() {
		return telEndTime;
	}

	public void setTelEndTime(Date telEndTime) {
		this.telEndTime = telEndTime;
	}

	public Integer getEditTime() {
		return editTime;
	}

	public void setEditTime(Integer editTime) {
		this.editTime = editTime;
	}

	public Boolean getInBlackList() {
		return inBlackList;
	}

	public void setInBlackList(Boolean inBlackList) {
		this.inBlackList = inBlackList;
	}

	public String getBlackListDesc() {
		return blackListDesc;
	}

	public void setBlackListDesc(String blackListDesc) {
		this.blackListDesc = blackListDesc;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public Date getSiteStartTime() {
		return siteStartTime;
	}

	public void setSiteStartTime(Date siteStartTime) {
		this.siteStartTime = siteStartTime;
	}

	public Date getSiteEndTime() {
		return siteEndTime;
	}

	public void setSiteEndTime(Date siteEndTime) {
		this.siteEndTime = siteEndTime;
	}

	public String getPicDesc() {
		return picDesc;
	}

	public void setPicDesc(String picDesc) {
		this.picDesc = picDesc;
	}

	public Date getAppEndTime() {
		return appEndTime;
	}

	public void setAppEndTime(Date appEndTime) {
		this.appEndTime = appEndTime;
	}

	public String getApprovalName() {
		return approvalName;
	}

	public void setApprovalName(String approvalName) {
		this.approvalName = approvalName;
	}

	public String getApprovalDesc() {
		return approvalDesc;
	}

	public void setApprovalDesc(String approvalDesc) {
		this.approvalDesc = approvalDesc;
	}

	public Date getApprovalStartTime() {
		return approvalStartTime;
	}

	public void setApprovalStartTime(Date approvalStartTime) {
		this.approvalStartTime = approvalStartTime;
	}

	public Date getApprovalEndTime() {
		return approvalEndTime;
	}

	public void setApprovalEndTime(Date approvalEndTime) {
		this.approvalEndTime = approvalEndTime;
	}

	public String getFirstDenyingCode() {
		return firstDenyingCode;
	}

	public void setFirstDenyingCode(String firstDenyingCode) {
		this.firstDenyingCode = firstDenyingCode;
	}

	public String getApprovalDesc1() {
		return approvalDesc1;
	}

	public void setApprovalDesc1(String approvalDesc1) {
		this.approvalDesc1 = approvalDesc1;
	}

	public String getApprovalDesc2() {
		return approvalDesc2;
	}

	public void setApprovalDesc2(String approvalDesc2) {
		this.approvalDesc2 = approvalDesc2;
	}

	public String getApprovalDesc3() {
		return approvalDesc3;
	}

	public void setApprovalDesc3(String approvalDesc3) {
		this.approvalDesc3 = approvalDesc3;
	}

	public String getApprovalDesc4() {
		return approvalDesc4;
	}

	public void setApprovalDesc4(String approvalDesc4) {
		this.approvalDesc4 = approvalDesc4;
	}

	public String getElectricName() {
		return electricName;
	}

	public void setElectricName(String electricName) {
		this.electricName = electricName;
	}

	public String getElectricDesc() {
		return electricDesc;
	}

	public void setElectricDesc(String electricDesc) {
		this.electricDesc = electricDesc;
	}

	public Date getElectricStartTime() {
		return electricStartTime;
	}

	public void setElectricStartTime(Date electricStartTime) {
		this.electricStartTime = electricStartTime;
	}

	public Date getElectricEndTime() {
		return electricEndTime;
	}

	public void setElectricEndTime(Date electricEndTime) {
		this.electricEndTime = electricEndTime;
	}

	public String getElectricDenyingCode() {
		return electricDenyingCode;
	}

	public void setElectricDenyingCode(String electricDenyingCode) {
		this.electricDenyingCode = electricDenyingCode;
	}

	public String getFaceName() {
		return faceName;
	}

	public void setFaceName(String faceName) {
		this.faceName = faceName;
	}

	public String getFaceDesc() {
		return faceDesc;
	}

	public void setFaceDesc(String faceDesc) {
		this.faceDesc = faceDesc;
	}

	public Date getFaceStartTime() {
		return faceStartTime;
	}

	public void setFaceStartTime(Date faceStartTime) {
		this.faceStartTime = faceStartTime;
	}

	public Date getFaceEndTime() {
		return faceEndTime;
	}

	public void setFaceEndTime(Date faceEndTime) {
		this.faceEndTime = faceEndTime;
	}

	public String getFaceRefause() {
		return faceRefause;
	}

	public void setFaceRefause(String faceRefause) {
		this.faceRefause = faceRefause;
	}

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public String getContractDesc() {
		return contractDesc;
	}

	public void setContractDesc(String contractDesc) {
		this.contractDesc = contractDesc;
	}

	public String getContractRefause() {
		return contractRefause;
	}

	public void setContractRefause(String contractRefause) {
		this.contractRefause = contractRefause;
	}

	public Date getContractStartTime() {
		return contractStartTime;
	}

	public void setContractStartTime(Date contractStartTime) {
		this.contractStartTime = contractStartTime;
	}

	public Date getContractEndTime() {
		return contractEndTime;
	}

	public void setContractEndTime(Date contractEndTime) {
		this.contractEndTime = contractEndTime;
	}

	public String getPrivilegeName() {
		return privilegeName;
	}

	public void setPrivilegeName(String privilegeName) {
		this.privilegeName = privilegeName;
	}

	public String getPrivilegeDesc() {
		return privilegeDesc;
	}

	public void setPrivilegeDesc(String privilegeDesc) {
		this.privilegeDesc = privilegeDesc;
	}

	public Date getPrivilegeStartTime() {
		return privilegeStartTime;
	}

	public void setPrivilegeStartTime(Date privilegeStartTime) {
		this.privilegeStartTime = privilegeStartTime;
	}

	public Date getPrivilegeEndTime() {
		return privilegeEndTime;
	}

	public void setPrivilegeEndTime(Date privilegeEndTime) {
		this.privilegeEndTime = privilegeEndTime;
	}

	public String getPrivilegeRefause() {
		return privilegeRefause;
	}

	public void setPrivilegeRefause(String privilegeRefause) {
		this.privilegeRefause = privilegeRefause;
	}

	public String getDeliveryName() {
		return deliveryName;
	}

	public void setDeliveryName(String deliveryName) {
		this.deliveryName = deliveryName;
	}

	public String getDeliveryDesc() {
		return deliveryDesc;
	}

	public void setDeliveryDesc(String deliveryDesc) {
		this.deliveryDesc = deliveryDesc;
	}

	public Date getDeliveryStartTime() {
		return deliveryStartTime;
	}

	public void setDeliveryStartTime(Date deliveryStartTime) {
		this.deliveryStartTime = deliveryStartTime;
	}

	public Date getDeliveryEndTime() {
		return deliveryEndTime;
	}

	public void setDeliveryEndTime(Date deliveryEndTime) {
		this.deliveryEndTime = deliveryEndTime;
	}

	public String getDeliveryRefause() {
		return deliveryRefause;
	}

	public void setDeliveryRefause(String deliveryRefause) {
		this.deliveryRefause = deliveryRefause;
	}

	public String getTelDescCharge() {
		return telDescCharge;
	}

	public void setTelDescCharge(String telDescCharge) {
		this.telDescCharge = telDescCharge;
	}

	public String getTelDescName() {
		return telDescName;
	}

	public void setTelDescName(String telDescName) {
		this.telDescName = telDescName;
	}

	public String getSiteDescCharge() {
		return siteDescCharge;
	}

	public void setSiteDescCharge(String siteDescCharge) {
		this.siteDescCharge = siteDescCharge;
	}

	public String getSaleDescCharge() {
		return saleDescCharge;
	}

	public void setSaleDescCharge(String saleDescCharge) {
		this.saleDescCharge = saleDescCharge;
	}

	public String getSaleDescName() {
		return saleDescName;
	}

	public void setSaleDescName(String saleDescName) {
		this.saleDescName = saleDescName;
	}

	public String getApprovalDescCharge() {
		return approvalDescCharge;
	}

	public void setApprovalDescCharge(String approvalDescCharge) {
		this.approvalDescCharge = approvalDescCharge;
	}

	public String getApprovalDescName() {
		return approvalDescName;
	}

	public void setApprovalDescName(String approvalDescName) {
		this.approvalDescName = approvalDescName;
	}

	public String getCloseName() {
		return closeName;
	}

	public void setCloseName(String closeName) {
		this.closeName = closeName;
	}

	public String getCloseDesc() {
		return closeDesc;
	}

	public void setCloseDesc(String closeDesc) {
		this.closeDesc = closeDesc;
	}

	public Date getCloseStartTime() {
		return closeStartTime;
	}

	public void setCloseStartTime(Date closeStartTime) {
		this.closeStartTime = closeStartTime;
	}

	public Date getCloseEndTime() {
		return closeEndTime;
	}

	public void setCloseEndTime(Date closeEndTime) {
		this.closeEndTime = closeEndTime;
	}

	public Boolean getContractFlag() {
		return contractFlag;
	}

	public void setContractFlag(Boolean contractFlag) {
		this.contractFlag = contractFlag;
	}

	public Boolean getDeliveryFlag() {
		return deliveryFlag;
	}

	public void setDeliveryFlag(Boolean deliveryFlag) {
		this.deliveryFlag = deliveryFlag;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getSaleSite() {
		return saleSite;
	}

	public void setSaleSite(String saleSite) {
		this.saleSite = saleSite;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getReceiveName() {
		return receiveName;
	}

	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}

	public Date getReceiveStartTime() {
		return receiveStartTime;
	}

	public void setReceiveStartTime(Date receiveStartTime) {
		this.receiveStartTime = receiveStartTime;
	}

	public Date getReceiveEndTime() {
		return receiveEndTime;
	}

	public void setReceiveEndTime(Date receiveEndTime) {
		this.receiveEndTime = receiveEndTime;
	}

	public String getReceiveDesc() {
		return receiveDesc;
	}

	public void setReceiveDesc(String receiveDesc) {
		this.receiveDesc = receiveDesc;
	}

	public String getReceiveCode() {
		return receiveCode;
	}

	public void setReceiveCode(String receiveCode) {
		this.receiveCode = receiveCode;
	}

	public Boolean getReceiveFlag() {
		return receiveFlag;
	}

	public void setReceiveFlag(Boolean receiveFlag) {
		this.receiveFlag = receiveFlag;
	}

	public Boolean getReceiveCodeFlag() {
		return receiveCodeFlag;
	}

	public void setReceiveCodeFlag(Boolean receiveCodeFlag) {
		this.receiveCodeFlag = receiveCodeFlag;
	}

	public Boolean getCloseFlag() {
		return closeFlag;
	}

	public void setCloseFlag(Boolean closeFlag) {
		this.closeFlag = closeFlag;
	}

	public String getCheckName() {
		return checkName;
	}

	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}

	public String getCheckDesc() {
		return checkDesc;
	}

	public void setCheckDesc(String checkDesc) {
		this.checkDesc = checkDesc;
	}

	public Date getCheckStartTime() {
		return checkStartTime;
	}

	public void setCheckStartTime(Date checkStartTime) {
		this.checkStartTime = checkStartTime;
	}

	public Date getCheckEndTime() {
		return checkEndTime;
	}

	public void setCheckEndTime(Date checkEndTime) {
		this.checkEndTime = checkEndTime;
	}

	public Boolean getCheckFlag() {
		return checkFlag;
	}

	public void setCheckFlag(Boolean checkFlag) {
		this.checkFlag = checkFlag;
	}

	public Boolean getSubmitFlag() {
		return submitFlag;
	}

	public void setSubmitFlag(Boolean submitFlag) {
		this.submitFlag = submitFlag;
	}

	public Boolean getUploadFlag() {
		return uploadFlag;
	}

	public void setUploadFlag(Boolean uploadFlag) {
		this.uploadFlag = uploadFlag;
	}

	public Boolean getPayMentFlag() {
		return payMentFlag;
	}

	public void setPayMentFlag(Boolean payMentFlag) {
		this.payMentFlag = payMentFlag;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGradeWork() {
		return gradeWork;
	}

	public void setGradeWork(String gradeWork) {
		this.gradeWork = gradeWork;
	}

	public String getDisCount() {
		return disCount;
	}

	public void setDisCount(String disCount) {
		this.disCount = disCount;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getBrandShow() {
		return brandShow;
	}

	public void setBrandShow(String brandShow) {
		this.brandShow = brandShow;
	}

	public String getParaTypeShow() {
		return paraTypeShow;
	}

	public void setParaTypeShow(String paraTypeShow) {
		this.paraTypeShow = paraTypeShow;
	}

	public String getOperatorMerShow() {
		return operatorMerShow;
	}

	public void setOperatorMerShow(String operatorMerShow) {
		this.operatorMerShow = operatorMerShow;
	}

	public String getPadTypeShow() {
		return padTypeShow;
	}

	public void setPadTypeShow(String padTypeShow) {
		this.padTypeShow = padTypeShow;
	}

	public String getFlashSizeShow() {
		return flashSizeShow;
	}

	public void setFlashSizeShow(String flashSizeShow) {
		this.flashSizeShow = flashSizeShow;
	}

	public String getCpuShow() {
		return cpuShow;
	}

	public void setCpuShow(String cpuShow) {
		this.cpuShow = cpuShow;
	}

	public String getHardDriveShow() {
		return hardDriveShow;
	}

	public void setHardDriveShow(String hardDriveShow) {
		this.hardDriveShow = hardDriveShow;
	}

	public String getLensTypeShow() {
		return lensTypeShow;
	}

	public void setLensTypeShow(String lensTypeShow) {
		this.lensTypeShow = lensTypeShow;
	}

	public String getTranPrice() {
		return tranPrice;
	}

	public void setTranPrice(String tranPrice) {
		this.tranPrice = tranPrice;
	}

	public Boolean getCompFlag() {
		return compFlag;
	}

	public void setCompFlag(Boolean compFlag) {
		this.compFlag = compFlag;
	}

	public String getProvice() {
		return provice;
	}

	public void setProvice(String provice) {
		this.provice = provice;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Boolean getTreatyUploadFlag() {
		return treatyUploadFlag;
	}

	public void setTreatyUploadFlag(Boolean treatyUploadFlag) {
		this.treatyUploadFlag = treatyUploadFlag;
	}

	public String getTreatyUploadURL() {
		return treatyUploadURL;
	}

	public void setTreatyUploadURL(String treatyUploadURL) {
		this.treatyUploadURL = treatyUploadURL;
	}

	public String getEarnestMoney() {
		return earnestMoney;
	}

	public void setEarnestMoney(String earnestMoney) {
		this.earnestMoney = earnestMoney;
	}

	public String getPayedEarnestMoney() {
		return payedEarnestMoney;
	}

	public void setPayedEarnestMoney(String payedEarnestMoney) {
		this.payedEarnestMoney = payedEarnestMoney;
	}

	public Boolean getHistoryConfig() {
		return historyConfig;
	}

	public void setHistoryConfig(Boolean historyConfig) {
		this.historyConfig = historyConfig;
	}

	public String getMoStatus() {
		return moStatus;
	}

	public void setMoStatus(String moStatus) {
		this.moStatus = moStatus;
	}

	public String getMoInfo() {
		return moInfo;
	}

	public void setMoInfo(String moInfo) {
		this.moInfo = moInfo;
	}

	public Date getOperatingDate() {
		return operatingDate;
	}

	public void setOperatingDate(Date operatingDate) {
		this.operatingDate = operatingDate;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAllNode() {
		return allNode;
	}

	public void setAllNode(String allNode) {
		this.allNode = allNode;
	}

	public String getRepaymentDate() {
		return repaymentDate;
	}

	public void setRepaymentDate(String repaymentDate) {
		this.repaymentDate = repaymentDate;
	}

	public String getInverstStatus() {
		return inverstStatus;
	}

	public void setInverstStatus(String inverstStatus) {
		this.inverstStatus = inverstStatus;
	}

	public String getFirstPayRate() {
		return firstPayRate;
	}

	public void setFirstPayRate(String firstPayRate) {
		this.firstPayRate = firstPayRate;
	}

	public String getFirstPayAmount() {
		return firstPayAmount;
	}

	public void setFirstPayAmount(String firstPayAmount) {
		this.firstPayAmount = firstPayAmount;
	}

	public String getFirstPayPrincipal() {
		return firstPayPrincipal;
	}

	public void setFirstPayPrincipal(String firstPayPrincipal) {
		this.firstPayPrincipal = firstPayPrincipal;
	}

	public String getApplyAmount() {
		return applyAmount;
	}

	public void setApplyAmount(String applyAmount) {
		this.applyAmount = applyAmount;
	}

	public String getAppReferralCode() {
		return appReferralCode;
	}

	public void setAppReferralCode(String appReferralCode) {
		this.appReferralCode = appReferralCode;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public Integer getInvestMode() {
		return investMode;
	}

	public void setInvestMode(Integer investMode) {
		this.investMode = investMode;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public Date getConfirmAgreementDate() {
		return confirmAgreementDate;
	}

	public void setConfirmAgreementDate(Date confirmAgreementDate) {
		this.confirmAgreementDate = confirmAgreementDate;
	}

	public Boolean getConfirmAgreement() {
		return confirmAgreement;
	}

	public void setConfirmAgreement(Boolean confirmAgreement) {
		this.confirmAgreement = confirmAgreement;
	}

	public String getProductDetail() {
		return productDetail;
	}

	public void setProductDetail(String productDetail) {
		this.productDetail = productDetail;
	}

	public String getMerchAntEndTime() {
		return merchAntEndTime;
	}

	public void setMerchAntEndTime(String merchAntEndTime) {
		this.merchAntEndTime = merchAntEndTime;
	}

	public Boolean getCancel() {
		return cancel;
	}

	public void setCancel(Boolean cancel) {
		this.cancel = cancel;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public boolean isIsallocation() {
		return isallocation;
	}

	public void setIsallocation(boolean isallocation) {
		this.isallocation = isallocation;
	}

	public String getCollectionResult() {
		return collectionResult;
	}

	public void setCollectionResult(String collectionResult) {
		this.collectionResult = collectionResult;
	}

	public String getCollectionDesc() {
		return collectionDesc;
	}

	public void setCollectionDesc(String collectionDesc) {
		this.collectionDesc = collectionDesc;
	}

	public boolean isMerchant() {
		return Merchant;
	}

	public void setMerchant(boolean merchant) {
		Merchant = merchant;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getCreditProductId() {
		return creditProductId;
	}

	public String getOldCreditProduct() {
		return oldCreditProduct;
	}

	public String getApplyFrom() {
		return applyFrom;
	}

	public void setApplyFrom(String applyFrom) {
		this.applyFrom = applyFrom;
	}
	
	

}
