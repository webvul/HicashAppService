package com.hengyuan.hicash.entity.param;

/**
 * 商户门店实体
 * 
 * @author Cary.Liu
 * @create 2014-09-23
 * 
 */
public class SaleSiteListEntity {

	/** 门店id */
	private Integer siteId;

	/** 门店名称 */
	private String siteSaleName;

	/** 负责人 */
	private String chargeName;

	/** 联系电话 */
	private String siteTel;

	/** 联系手机 */
	private String legalTel;

	/** 所在城市 */
	private String siteCityName;

	/** 售点地址 */
	private String siteAddress;

	/** 所属商户 */
	private String siteDefaultSupplierName;

	/** 申请日期 */
	private String approvalStartTime;

	/** 商户状态 */
	private String saleSiteStatus;

	/** 所在省代码 */
	private String provinceCode;

	/** 所在省名称 */
	private String provinceName;

	/** 所在市代码 */
	private String cityCode;

	/** 所在市名称 */
	private String cityName;

	/** 所在区代码 */
	private String areaCode;

	/** 所在区名称 */
	private String areaName;

	private String endTime;

	private String logicCode;

	/** 大节点 */
	private String allNode;

	/** 节点 */
	private String node;

	/** 状态 */
	private String status;

	/* 门店详情 */

	/** 产品类型 */
	private String proType;

	/** 和商户的关系 */
	private String supRelation;

	/** 所属商户 */
	private String supplierName;

	/** 营业执照名称 */
	private String busiLice;

	private String businessArea;

	private String siteCode;

	private String hours;

	private String peakTime;

	private String openTime;

	private String legalName;

	private String chargeTel;

	private String contactName;

	private String operatingArea;

	private String empNum;

	private String leaseTerm;

	private String propertycase;

	private String operatingYear;

	private String januarySales;

	private String inventoriesCase;

	private String inspectionYear;

	private String customerGroups;

	private String location;

	private String badRecord;

	private String industryReputation;

	private String familyBackground;

	private String otherPresentations;

	public String getAllNode() {
		return allNode;
	}

	public void setAllNode(String allNode) {
		this.allNode = allNode;
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

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getBusiLice() {
		return busiLice;
	}

	public void setBusiLice(String busiLice) {
		this.busiLice = busiLice;
	}

	public String getProType() {
		return proType;
	}

	public void setProType(String proType) {
		this.proType = proType;
	}

	public String getSupRelation() {
		return supRelation;
	}

	public void setSupRelation(String supRelation) {
		this.supRelation = supRelation;
	}

	public String getBusinessArea() {
		return businessArea;
	}

	public void setBusinessArea(String businessArea) {
		this.businessArea = businessArea;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public String getHours() {
		return hours;
	}

	public void setHours(String hours) {
		this.hours = hours;
	}

	public String getPeakTime() {
		return peakTime;
	}

	public void setPeakTime(String peakTime) {
		this.peakTime = peakTime;
	}

	public String getOpenTime() {
		return openTime;
	}

	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}

	public String getLegalName() {
		return legalName;
	}

	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}

	public String getChargeTel() {
		return chargeTel;
	}

	public void setChargeTel(String chargeTel) {
		this.chargeTel = chargeTel;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getOperatingArea() {
		return operatingArea;
	}

	public void setOperatingArea(String operatingArea) {
		this.operatingArea = operatingArea;
	}

	public String getEmpNum() {
		return empNum;
	}

	public void setEmpNum(String empNum) {
		this.empNum = empNum;
	}

	public String getLeaseTerm() {
		return leaseTerm;
	}

	public void setLeaseTerm(String leaseTerm) {
		this.leaseTerm = leaseTerm;
	}

	public String getPropertycase() {
		return propertycase;
	}

	public void setPropertycase(String propertycase) {
		this.propertycase = propertycase;
	}

	public String getOperatingYear() {
		return operatingYear;
	}

	public void setOperatingYear(String operatingYear) {
		this.operatingYear = operatingYear;
	}

	public String getJanuarySales() {
		return januarySales;
	}

	public void setJanuarySales(String januarySales) {
		this.januarySales = januarySales;
	}

	public String getInventoriesCase() {
		return inventoriesCase;
	}

	public void setInventoriesCase(String inventoriesCase) {
		this.inventoriesCase = inventoriesCase;
	}

	public String getInspectionYear() {
		return inspectionYear;
	}

	public void setInspectionYear(String inspectionYear) {
		this.inspectionYear = inspectionYear;
	}

	public String getCustomerGroups() {
		return customerGroups;
	}

	public void setCustomerGroups(String customerGroups) {
		this.customerGroups = customerGroups;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getBadRecord() {
		return badRecord;
	}

	public void setBadRecord(String badRecord) {
		this.badRecord = badRecord;
	}

	public String getIndustryReputation() {
		return industryReputation;
	}

	public void setIndustryReputation(String industryReputation) {
		this.industryReputation = industryReputation;
	}

	public String getFamilyBackground() {
		return familyBackground;
	}

	public void setFamilyBackground(String familyBackground) {
		this.familyBackground = familyBackground;
	}

	public String getOtherPresentations() {
		return otherPresentations;
	}

	public void setOtherPresentations(String otherPresentations) {
		this.otherPresentations = otherPresentations;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getSiteTel() {
		return siteTel;
	}

	public void setSiteTel(String siteTel) {
		this.siteTel = siteTel;
	}

	public String getLegalTel() {
		return legalTel;
	}

	public void setLegalTel(String legalTel) {
		this.legalTel = legalTel;
	}

	public String getChargeName() {
		return chargeName;
	}

	public void setChargeName(String chargeName) {
		this.chargeName = chargeName;
	}

	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

	public String getSiteSaleName() {
		return siteSaleName;
	}

	public void setSiteSaleName(String siteSaleName) {
		this.siteSaleName = siteSaleName;
	}

	public String getSiteCityName() {
		return siteCityName;
	}

	public void setSiteCityName(String siteCityName) {
		this.siteCityName = siteCityName;
	}

	public String getSiteAddress() {
		return siteAddress;
	}

	public void setSiteAddress(String siteAddress) {
		this.siteAddress = siteAddress;
	}

	public String getSiteDefaultSupplierName() {
		return siteDefaultSupplierName;
	}

	public void setSiteDefaultSupplierName(String siteDefaultSupplierName) {
		this.siteDefaultSupplierName = siteDefaultSupplierName;
	}

	public String getApprovalStartTime() {
		return approvalStartTime;
	}

	public void setApprovalStartTime(String approvalStartTime) {
		this.approvalStartTime = approvalStartTime;
	}

	public String getSaleSiteStatus() {
		return saleSiteStatus;
	}

	public void setSaleSiteStatus(String saleSiteStatus) {
		this.saleSiteStatus = saleSiteStatus;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getLogicCode() {
		return logicCode;
	}

	public void setLogicCode(String logicCode) {
		this.logicCode = logicCode;
	}

}
