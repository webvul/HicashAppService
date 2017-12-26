package com.hengyuan.hicash.entity.user;

/**
 * 客户
 * 
 * @author Cary.Liu
 * @create 2014-09-25
 *
 */
public class CustCustomer {
	
	/** ID */
	private String id;

	/** 学校ID */
	private String schoolId;

	/** 学校名称 */
	private String school;

	/** 用户名 */
	private String userName;

	/** 真实姓名 */
	private String realName;

	/** 手机号码 */
	private String mobileNo;

	/** 身份证号码 */
	private String identityNo;

	/** 学历 */
	private String degree;

	/** 客户类型 */
	private String custType;

	/** 单位名称 */
	private String unitName;

	/** 邮箱地址 */
	private String emailAdress;

	/** 工作市 */
	private String workAreaCity;

	/** 现居住市 */
	private String otherAdressCity;

	/** 最高学历 */
	private String nowEduCation;

	/** 年级 */
	private String userclass;

	/** 工作年限 */
	private String workExperience;

	/** 固定电话 */
	private String fixedTel;

	/** 固定电话区号 */
	private String fixedTelArea;

	/** 证件有效期 */
	private String valid;

	/** 直系亲属 */

	/** 直系亲属姓名 */
	private String immediateName;

	/** 直系亲属关系 */
	private String immediateRelation;

	/** 直系亲属手机号码 */
	private String immediateMobile;

	/** 直系亲属工作单位 */
	private String immediateJob;

	/** 直系亲属工作单位或家庭地址 */
	private String immediateAdress;

	/** 紧急联系人姓名 */
	private String emergencyName;

	/** 紧急联系人关系 */
	private String emergencyRelation;

	/** 紧急联系人手机号码 */
	private String emergencyMobile;

	/** 紧急联系人工作单位 */
	private String emergencyJob;

	/** 紧急联系人工作单位或家庭地址 */
	private String emergencyAdress;

	/** 是否全日制(学生)或者全职(白领) */
	private String fullTimeFlag;

	/** 家庭地址 */

	/** 省 */
	private String fimilyAreaProvince;

	/** 市 */
	private String fimilyAreaCity;

	/** 区/县 */
	private String fimilyAreaArea;

	/** 家庭街道地址 */
	private String fimilyAreaRoad;

	/** 现居地址 */
	private String nowFimilyAdress;

	/** 省 */
	private String otherAdressProvince;

	/** 区/县 */
	private String otherAdressArea;

	/** 其它街道地址 */
	private String otherAccommodationAddress;

	/** 学号 */
	private String studentId;

	/** 在读学历 */
	private String educational;

	/** 入学时间 */
	private String admissionTime;

	/** 所在院系 */
	private String faculties;

	/** 学校地址 */

	/** 省 */
	private String schoolAreaProvince;

	/** 市 */
	private String schoolAreaCity;

	/** 区/县 */
	private String schoolAreaArea;

	/** 学校街道地址 */
	private String schoolAreaRoad;

	/** 专业 */
	private String specialty;

	/** 毕业时间 */
	private String endSchoolTime;

	/** 行业类别 */
	private String sectors;

	/** 毕业院校 */
	private String graduatedSchool;

	/** 单位固定电话区号 */
	private String unitTelArea;

	/** 单位固定电话 */
	private String unitTel;

	/** 月收入 */
	private String monthlyIncome;

	/** 任职部门 */
	private String jobNature;

	/** 职位职务 */
	private String jobDuties;

	/** 婚姻状况 */
	private String maritalStatus;

	/** 配偶姓名 */
	private String spouseName;

	/** 配偶手机号码 */
	private String spouseMobile;

	/** 单位性质 */
	private String unitProperties;

	/** 住房状况 */
	private String housingConditions;

	/** 工号 */
	private String jobNumber;

	/** 户籍地址 */
	/** 省 */
	private String permanentAddressProvince;
	/** 市 */
	private String permanentAddressCity;
	/** 区 */
	private String permanentAddressArea;
	/** 详细地址 */
	private String permanentAddressRaod;

	/** 月消费 */
	private String monthlyConsumption;

	/** 房租月供 */
	private String monthRent;

	/** 贷款数量 */
	private String loanCount;

	/** 贷款总额 */
	private String loanTotalAmount;

	/** 贷款月供 */
	private String loanMonthRent;

	/* 信用卡信息 */

	/** 信用卡数量 */
	private String creditCardCount;

	/** 信用卡总额度 */
	private String creditTotalAmount;

	/** 信用卡最高额度 */
	private String creditHigthAmount;

	/** 入职时间 */
	private String entryTime;

	/** 省 */
	private String workAreaProvince;

	/** 区/县 */
	private String workAreaArea;

	/** 单位街道地址 */
	private String workAreaRoad;

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

	/** 学生证封面 */
	private String stuCardFront;

	/**  */
	private String stuCardFrontThum;

	/** 个人照片及基本信息 */
	private String stuPhotoInfo;

	/**  */
	private String stuPhotoInfoThum;

	/** 注册登记信息 */
	private String stuRegistInfo;

	/**  */
	private String stuRegistInfoThum;

	/** 校园卡正面 */
	private String schoolCardFront;

	/**  */
	private String schoolCardFrontThum;

	/** 校园卡反面 */
	private String schoolCardVerso;

	/**  */
	private String schoolCardVersoThum;

	/** 学生类型 */
	private String stuType;

	/** qq号码 */
	private String qqNumber;

	/**  */
	private String custFrom;

	/* 蓝领业务新增 */

	/** 是否为蓝领用户 */
	private String lanUserFlag;

	/** 邀请码（业务员工号） */
	private String inveteCode;

	/** 门店号 */
	private String storeCode;

	/** 用户现场照大图url */
	private String userScenepicUrl;

	/** 用户现场照小图url */
	private String userScenepicThumUrl;

	/** 民族 */
	private String nation;

	/** 身份证有效期开始时间 */
	private String idCardValStartDate;

	/** 身份证有效期结束时间 */
	private String idCardValEndDate;

	/** 份证有效期(期限) */
	private String idCardValidity;

	/** 借款用途 */
	private String loanUse;

	/** 单位规模 */
	private String companyScale;

	/** 单位工作年限 */
	private String companyWorkYear;

	/** 信用卡卡号 */
	private String creditCard;
	
	/** 是否全职司机（0：其他，1：兼职，2全职）*/
	private String fulltimeDriver;
	/**签发地*/
	private String idCardFrom;

	private String isUpName;//是否做过验证 0否 1 是
	
	
	
	
	public String getIsUpName() {
		return isUpName;
	}

	public void setIsUpName(String isUpName) {
		this.isUpName = isUpName;
	}


	public String getLoanUse() {
		return loanUse;
	}

	public String getFulltimeDriver() {
		return fulltimeDriver;
	}

	public void setFulltimeDriver(String fulltimeDriver) {
		this.fulltimeDriver = fulltimeDriver;
	}

	public void setLoanUse(String loanUse) {
		this.loanUse = loanUse;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getIdCardValStartDate() {
		return idCardValStartDate;
	}

	public void setIdCardValStartDate(String idCardValStartDate) {
		this.idCardValStartDate = idCardValStartDate;
	}

	public String getIdCardValEndDate() {
		return idCardValEndDate;
	}

	public void setIdCardValEndDate(String idCardValEndDate) {
		this.idCardValEndDate = idCardValEndDate;
	}

	public String getIdCardValidity() {
		return idCardValidity;
	}

	public void setIdCardValidity(String idCardValidity) {
		this.idCardValidity = idCardValidity;
	}

	public String getLanUserFlag() {
		return lanUserFlag;
	}

	public void setLanUserFlag(String lanUserFlag) {
		this.lanUserFlag = lanUserFlag;
	}

	public String getInveteCode() {
		return inveteCode;
	}

	public void setInveteCode(String inveteCode) {
		this.inveteCode = inveteCode;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getUserScenepicUrl() {
		return userScenepicUrl;
	}

	public void setUserScenepicUrl(String userScenepicUrl) {
		this.userScenepicUrl = userScenepicUrl;
	}

	public String getUserScenepicThumUrl() {
		return userScenepicThumUrl;
	}

	public void setUserScenepicThumUrl(String userScenepicThumUrl) {
		this.userScenepicThumUrl = userScenepicThumUrl;
	}

	public String getCustFrom() {
		return custFrom;
	}

	public void setCustFrom(String custFrom) {
		this.custFrom = custFrom;
	}

	public String getQqNumber() {
		return qqNumber;
	}

	public void setQqNumber(String qqNumber) {
		this.qqNumber = qqNumber;
	}

	public String getStuType() {
		return stuType;
	}

	public void setStuType(String stuType) {
		this.stuType = stuType;
	}

	public String getStuCardFront() {
		return stuCardFront;
	}

	public void setStuCardFront(String stuCardFront) {
		this.stuCardFront = stuCardFront;
	}

	public String getStuCardFrontThum() {
		return stuCardFrontThum;
	}

	public void setStuCardFrontThum(String stuCardFrontThum) {
		this.stuCardFrontThum = stuCardFrontThum;
	}

	public String getStuPhotoInfo() {
		return stuPhotoInfo;
	}

	public void setStuPhotoInfo(String stuPhotoInfo) {
		this.stuPhotoInfo = stuPhotoInfo;
	}

	public String getStuPhotoInfoThum() {
		return stuPhotoInfoThum;
	}

	public void setStuPhotoInfoThum(String stuPhotoInfoThum) {
		this.stuPhotoInfoThum = stuPhotoInfoThum;
	}

	public String getStuRegistInfo() {
		return stuRegistInfo;
	}

	public void setStuRegistInfo(String stuRegistInfo) {
		this.stuRegistInfo = stuRegistInfo;
	}

	public String getStuRegistInfoThum() {
		return stuRegistInfoThum;
	}

	public void setStuRegistInfoThum(String stuRegistInfoThum) {
		this.stuRegistInfoThum = stuRegistInfoThum;
	}

	public String getSchoolCardFront() {
		return schoolCardFront;
	}

	public void setSchoolCardFront(String schoolCardFront) {
		this.schoolCardFront = schoolCardFront;
	}

	public String getSchoolCardFrontThum() {
		return schoolCardFrontThum;
	}

	public void setSchoolCardFrontThum(String schoolCardFrontThum) {
		this.schoolCardFrontThum = schoolCardFrontThum;
	}

	public String getSchoolCardVerso() {
		return schoolCardVerso;
	}

	public void setSchoolCardVerso(String schoolCardVerso) {
		this.schoolCardVerso = schoolCardVerso;
	}

	public String getSchoolCardVersoThum() {
		return schoolCardVersoThum;
	}

	public void setSchoolCardVersoThum(String schoolCardVersoThum) {
		this.schoolCardVersoThum = schoolCardVersoThum;
	}

	public String getUserIdcardFrontUrl() {
		return userIdcardFrontUrl;
	}

	public void setUserIdcardFrontUrl(String userIdcardFrontUrl) {
		this.userIdcardFrontUrl = userIdcardFrontUrl;
	}

	public String getUserIdcardFrontUrlThum() {
		return userIdcardFrontUrlThum;
	}

	public void setUserIdcardFrontUrlThum(String userIdcardFrontUrlThum) {
		this.userIdcardFrontUrlThum = userIdcardFrontUrlThum;
	}

	public String getIdcardFrontUrl() {
		return idcardFrontUrl;
	}

	public void setIdcardFrontUrl(String idcardFrontUrl) {
		this.idcardFrontUrl = idcardFrontUrl;
	}

	public String getIdcardFrontUrlThum() {
		return idcardFrontUrlThum;
	}

	public void setIdcardFrontUrlThum(String idcardFrontUrlThum) {
		this.idcardFrontUrlThum = idcardFrontUrlThum;
	}

	public String getIdcardVersoUrl() {
		return idcardVersoUrl;
	}

	public void setIdcardVersoUrl(String idcardVersoUrl) {
		this.idcardVersoUrl = idcardVersoUrl;
	}

	public String getIdcardVersoUrlThum() {
		return idcardVersoUrlThum;
	}

	public void setIdcardVersoUrlThum(String idcardVersoUrlThum) {
		this.idcardVersoUrlThum = idcardVersoUrlThum;
	}

	public String getEmailAdress() {
		return emailAdress;
	}

	public void setEmailAdress(String emailAdress) {
		this.emailAdress = emailAdress;
	}

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
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

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getIdentityNo() {
		return identityNo;
	}

	public void setIdentityNo(String identityNo) {
		this.identityNo = identityNo;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getWorkAreaCity() {
		return workAreaCity;
	}

	public void setWorkAreaCity(String workAreaCity) {
		this.workAreaCity = workAreaCity;
	}

	public String getOtherAdressCity() {
		return otherAdressCity;
	}

	public void setOtherAdressCity(String otherAdressCity) {
		this.otherAdressCity = otherAdressCity;
	}

	public String getNowEduCation() {
		return nowEduCation;
	}

	public void setNowEduCation(String nowEduCation) {
		this.nowEduCation = nowEduCation;
	}

	public String getUserclass() {
		return userclass;
	}

	public void setUserclass(String userclass) {
		this.userclass = userclass;
	}

	public String getWorkExperience() {
		return workExperience;
	}

	public void setWorkExperience(String workExperience) {
		this.workExperience = workExperience;
	}

	public String getFixedTel() {
		return fixedTel;
	}

	public void setFixedTel(String fixedTel) {
		this.fixedTel = fixedTel;
	}

	public String getFixedTelArea() {
		return fixedTelArea;
	}

	public void setFixedTelArea(String fixedTelArea) {
		this.fixedTelArea = fixedTelArea;
	}

	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}

	public String getImmediateName() {
		return immediateName;
	}

	public void setImmediateName(String immediateName) {
		this.immediateName = immediateName;
	}

	public String getImmediateRelation() {
		return immediateRelation;
	}

	public void setImmediateRelation(String immediateRelation) {
		this.immediateRelation = immediateRelation;
	}

	public String getImmediateMobile() {
		return immediateMobile;
	}

	public void setImmediateMobile(String immediateMobile) {
		this.immediateMobile = immediateMobile;
	}

	public String getImmediateJob() {
		return immediateJob;
	}

	public void setImmediateJob(String immediateJob) {
		this.immediateJob = immediateJob;
	}

	public String getImmediateAdress() {
		return immediateAdress;
	}

	public void setImmediateAdress(String immediateAdress) {
		this.immediateAdress = immediateAdress;
	}

	public String getEmergencyName() {
		return emergencyName;
	}

	public void setEmergencyName(String emergencyName) {
		this.emergencyName = emergencyName;
	}

	public String getEmergencyRelation() {
		return emergencyRelation;
	}

	public void setEmergencyRelation(String emergencyRelation) {
		this.emergencyRelation = emergencyRelation;
	}

	public String getEmergencyMobile() {
		return emergencyMobile;
	}

	public void setEmergencyMobile(String emergencyMobile) {
		this.emergencyMobile = emergencyMobile;
	}

	public String getEmergencyJob() {
		return emergencyJob;
	}

	public void setEmergencyJob(String emergencyJob) {
		this.emergencyJob = emergencyJob;
	}

	public String getEmergencyAdress() {
		return emergencyAdress;
	}

	public void setEmergencyAdress(String emergencyAdress) {
		this.emergencyAdress = emergencyAdress;
	}

	public String getFullTimeFlag() {
		return fullTimeFlag;
	}

	public void setFullTimeFlag(String fullTimeFlag) {
		this.fullTimeFlag = fullTimeFlag;
	}

	public String getFimilyAreaProvince() {
		return fimilyAreaProvince;
	}

	public void setFimilyAreaProvince(String fimilyAreaProvince) {
		this.fimilyAreaProvince = fimilyAreaProvince;
	}

	public String getFimilyAreaCity() {
		return fimilyAreaCity;
	}

	public void setFimilyAreaCity(String fimilyAreaCity) {
		this.fimilyAreaCity = fimilyAreaCity;
	}

	public String getFimilyAreaArea() {
		return fimilyAreaArea;
	}

	public void setFimilyAreaArea(String fimilyAreaArea) {
		this.fimilyAreaArea = fimilyAreaArea;
	}

	public String getFimilyAreaRoad() {
		return fimilyAreaRoad;
	}

	public void setFimilyAreaRoad(String fimilyAreaRoad) {
		this.fimilyAreaRoad = fimilyAreaRoad;
	}

	public String getNowFimilyAdress() {
		return nowFimilyAdress;
	}

	public void setNowFimilyAdress(String nowFimilyAdress) {
		this.nowFimilyAdress = nowFimilyAdress;
	}

	public String getOtherAdressProvince() {
		return otherAdressProvince;
	}

	public void setOtherAdressProvince(String otherAdressProvince) {
		this.otherAdressProvince = otherAdressProvince;
	}

	public String getOtherAdressArea() {
		return otherAdressArea;
	}

	public void setOtherAdressArea(String otherAdressArea) {
		this.otherAdressArea = otherAdressArea;
	}

	public String getOtherAccommodationAddress() {
		return otherAccommodationAddress;
	}

	public void setOtherAccommodationAddress(String otherAccommodationAddress) {
		this.otherAccommodationAddress = otherAccommodationAddress;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getEducational() {
		return educational;
	}

	public void setEducational(String educational) {
		this.educational = educational;
	}

	public String getAdmissionTime() {
		return admissionTime;
	}

	public void setAdmissionTime(String admissionTime) {
		this.admissionTime = admissionTime;
	}

	public String getFaculties() {
		return faculties;
	}

	public void setFaculties(String faculties) {
		this.faculties = faculties;
	}

	public String getSchoolAreaProvince() {
		return schoolAreaProvince;
	}

	public void setSchoolAreaProvince(String schoolAreaProvince) {
		this.schoolAreaProvince = schoolAreaProvince;
	}

	public String getSchoolAreaCity() {
		return schoolAreaCity;
	}

	public void setSchoolAreaCity(String schoolAreaCity) {
		this.schoolAreaCity = schoolAreaCity;
	}

	public String getSchoolAreaArea() {
		return schoolAreaArea;
	}

	public void setSchoolAreaArea(String schoolAreaArea) {
		this.schoolAreaArea = schoolAreaArea;
	}

	public String getSchoolAreaRoad() {
		return schoolAreaRoad;
	}

	public void setSchoolAreaRoad(String schoolAreaRoad) {
		this.schoolAreaRoad = schoolAreaRoad;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public String getEndSchoolTime() {
		return endSchoolTime;
	}

	public void setEndSchoolTime(String endSchoolTime) {
		this.endSchoolTime = endSchoolTime;
	}

	public String getSectors() {
		return sectors;
	}

	public void setSectors(String sectors) {
		this.sectors = sectors;
	}

	public String getGraduatedSchool() {
		return graduatedSchool;
	}

	public void setGraduatedSchool(String graduatedSchool) {
		this.graduatedSchool = graduatedSchool;
	}

	public String getUnitTelArea() {
		return unitTelArea;
	}

	public void setUnitTelArea(String unitTelArea) {
		this.unitTelArea = unitTelArea;
	}

	public String getUnitTel() {
		return unitTel;
	}

	public void setUnitTel(String unitTel) {
		this.unitTel = unitTel;
	}

	public String getMonthlyIncome() {
		return monthlyIncome;
	}

	public void setMonthlyIncome(String monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}

	public String getJobNature() {
		return jobNature;
	}

	public void setJobNature(String jobNature) {
		this.jobNature = jobNature;
	}

	public String getJobDuties() {
		return jobDuties;
	}

	public void setJobDuties(String jobDuties) {
		this.jobDuties = jobDuties;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getSpouseName() {
		return spouseName;
	}

	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}

	public String getSpouseMobile() {
		return spouseMobile;
	}

	public void setSpouseMobile(String spouseMobile) {
		this.spouseMobile = spouseMobile;
	}

	public String getUnitProperties() {
		return unitProperties;
	}

	public void setUnitProperties(String unitProperties) {
		this.unitProperties = unitProperties;
	}

	public String getHousingConditions() {
		return housingConditions;
	}

	public void setHousingConditions(String housingConditions) {
		this.housingConditions = housingConditions;
	}

	public String getJobNumber() {
		return jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	public String getPermanentAddressProvince() {
		return permanentAddressProvince;
	}

	public void setPermanentAddressProvince(String permanentAddressProvince) {
		this.permanentAddressProvince = permanentAddressProvince;
	}

	public String getPermanentAddressCity() {
		return permanentAddressCity;
	}

	public void setPermanentAddressCity(String permanentAddressCity) {
		this.permanentAddressCity = permanentAddressCity;
	}

	public String getPermanentAddressArea() {
		return permanentAddressArea;
	}

	public void setPermanentAddressArea(String permanentAddressArea) {
		this.permanentAddressArea = permanentAddressArea;
	}

	public String getPermanentAddressRaod() {
		return permanentAddressRaod;
	}

	public void setPermanentAddressRaod(String permanentAddressRaod) {
		this.permanentAddressRaod = permanentAddressRaod;
	}

	public String getMonthlyConsumption() {
		return monthlyConsumption;
	}

	public void setMonthlyConsumption(String monthlyConsumption) {
		this.monthlyConsumption = monthlyConsumption;
	}

	public String getMonthRent() {
		return monthRent;
	}

	public void setMonthRent(String monthRent) {
		this.monthRent = monthRent;
	}

	public String getLoanCount() {
		return loanCount;
	}

	public void setLoanCount(String loanCount) {
		this.loanCount = loanCount;
	}

	public String getLoanTotalAmount() {
		return loanTotalAmount;
	}

	public void setLoanTotalAmount(String loanTotalAmount) {
		this.loanTotalAmount = loanTotalAmount;
	}

	public String getLoanMonthRent() {
		return loanMonthRent;
	}

	public void setLoanMonthRent(String loanMonthRent) {
		this.loanMonthRent = loanMonthRent;
	}

	public String getCreditCardCount() {
		return creditCardCount;
	}

	public void setCreditCardCount(String creditCardCount) {
		this.creditCardCount = creditCardCount;
	}

	public String getCreditTotalAmount() {
		return creditTotalAmount;
	}

	public void setCreditTotalAmount(String creditTotalAmount) {
		this.creditTotalAmount = creditTotalAmount;
	}

	public String getCreditHigthAmount() {
		return creditHigthAmount;
	}

	public void setCreditHigthAmount(String creditHigthAmount) {
		this.creditHigthAmount = creditHigthAmount;
	}

	public String getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(String entryTime) {
		this.entryTime = entryTime;
	}

	public String getWorkAreaProvince() {
		return workAreaProvince;
	}

	public void setWorkAreaProvince(String workAreaProvince) {
		this.workAreaProvince = workAreaProvince;
	}

	public String getWorkAreaArea() {
		return workAreaArea;
	}

	public void setWorkAreaArea(String workAreaArea) {
		this.workAreaArea = workAreaArea;
	}

	public String getWorkAreaRoad() {
		return workAreaRoad;
	}

	public void setWorkAreaRoad(String workAreaRoad) {
		this.workAreaRoad = workAreaRoad;
	}

	public String getCompanyScale() {
		return companyScale;
	}

	public void setCompanyScale(String companyScale) {
		this.companyScale = companyScale;
	}

	public String getCompanyWorkYear() {
		return companyWorkYear;
	}

	public void setCompanyWorkYear(String companyWorkYear) {
		this.companyWorkYear = companyWorkYear;
	}

	public String getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdCardFrom() {
		return idCardFrom;
	}

	public void setIdCardFrom(String idCardFrom) {
		this.idCardFrom = idCardFrom;
	}

	
}