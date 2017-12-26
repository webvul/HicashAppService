package com.hengyuan.hicash.entity.app;

public class InputAppPay {

	private String createUser;

	private Long appId;

	private String createDate;

	private String updateDate;

	private String applicationNo;

	// -----申请人借款信息-----------------------------------

	/** 产品类型 */
	private String productType;

	/** 产品名称 */
	private String productName;

	/** 还款方式 */
	private String creditName;

	/** 每月还款日 */
	private String creditDay;

	/** 还款方式 */
	private String creditType;

	/** 借款用途 */
	private String creditUse;

	// ----申请人基本资料-----------------------------------------------------

	/** 姓名 */
	private String realName;

	/** 性别 */
	private String gender;

	/** 入学时间-年 */
	private String admissionYear;

	/** 入学时间-月 */
	private String admissionDay;

	/** qq号码 */
	private String qqNumber;

	/** 出生日期 */
	private String birthday;

	/** 居民身份证号 */
	private String identityNo;

	/** 证件有效期 */
	private String valid;

	/** 学号 */
	private String studentId;

	/** 年级 */
	private String userGreade;

	/** 所在学校 */
	private String school;

	/** 所在院系 */
	private String faculties;

	/** 专业 */
	private String specialty;

	/** 在读学历 */
	private String educational;

	/** 是否全日制 */
	private String fullTimeFlag;

	/** 户口所在地 */
	private String locationAddress;

	/** 户口邮编 */
	private String locationZip;

	/* 蓝领业务新增 */

	/** 是否为蓝领用户 */
	private String lanUserFlag;

	/** 邀请码（业务员工号） */
	private String inveteCode;

	/** 门店号 */
	private String storeCode;

	// -----申请人联系方式-------------------------------------------

	/** 手机号码 */
	private String mobileNo;

	/** E-mail地址 */
	private String emailAddress;

	/** 现居地邮编 */
	private String nowZip;

	/** 现在居地固定电话区号 */
	private String nowFixedTelArea;

	/** 现在居地固定电话区号 */
	private String nowFixedTel;

	/** 宿舍地址是否与现居地址相同 */
	private String nowDormFlag;

	/** 家庭地址邮编 */
	private String dormZip;

	/** 宿舍电话区号 */
	private String dormTelArea;

	/** 宿舍电话 */
	private String dormTel;

	// ---联系人资料------------------------------

	/** 直系亲属姓名 */
	private String fatherName;

	/** 直系亲属手机号 */
	private String fatherMobile;

	/** 父亲固定电话区号 */
	private String fatherTelArea;

	/** 父亲固定电话 */
	private String fatherTel;

	/** 直系亲属工作单位 */
	private String workUnits;

	/** 直系亲属工作单位或家庭地址 */
	private String workUnitsAddress;

	/** 母亲姓名 */
	private String motherName;

	/** 母亲手机号 */
	private String motherMobile;

	/** 母亲固定电话区号 */
	private String motherTelArea;

	/** 母亲固定电话 */
	private String motherTel;

	/** 父亲居住地址 */
	private String fatherAddress;

	/** 父亲居住地址邮编 */
	private String faAddressZip;

	/** 母亲居住地址 */
	private String matherAddress;

	/** 母亲居住地址邮编 */
	private String maAddressZip;

	/** 直系亲属关系 */
	private String familyRelation;

	/** 紧急联系亲属姓名 */
	private String relaName;

	/** 紧急联系亲属手机 */
	private String relaMobile;

	/** 关系 */
	private String relation;

	/** 紧急联系亲属工作单位 */
	private String relaWorkUnits;

	/** 紧急联系亲属工作单位地址 */
	private String relaWorkUnitsAddress;
	/** 工作单位地址 */
	private String relaWorkUnitsAddressSala;

	// ----销售代表填写---------------------------------

	/** 销售点代码 */
	private String saleSiteCode;

	/** 销售代表代码 */
	private String saleServerCode;

	private String userName;

	/* 白领信息 */

	/** 婚姻状况 */
	private String maritalStatus;

	/** 文化程度(最高学历) */
	private String education;

	/** 毕业时间 */
	private String graduation;

	/** 毕业时间-年 */
	private String graduationYear;

	/** 毕业时间-月 */
	private String graduationMonth;

	/** 单位名称 */
	private String unitName;

	/** 单位固定电话区号 */
	private String unitTelArea;

	/** 单位电话 */
	private String unitTel;

	/** 单位邮编 */
	private String unitZip;

	/** 月收入 */
	private String monthlyIncome;

	/** 任职部门 */
	private String officeSector;

	/** 职位职务 */
	private String jobDuties;

	/** 入职时间 */
	private String entryTime;

	/** 入职时间-年 */
	private String entryTimeYear;

	/** 入职时间-月 */
	private String entryTimeMonth;

	/** 行业类别 */
	private String sectors;

	/** 工作性质 */
	private String jobNature;

	/** 亲属姓名 */
	private String relaNameSala;

	/** 亲属手机 */
	private String relaMobileSala;

	/** 关系 */
	private String relationSala;

	/** 亲属工作单位 */
	private String relaWorkUnitsSala;

	/** 工作年限 */
	private String workExperience;
	/* 新增地址省市区代码存放 */

	/** 院校地址-省 */
	private String schoolProv;

	/** 院校地址-城市 */
	private String schoolCity;

	/** 院校地址-区域 */
	private String schoolArea;

	/** 院校地址 */
	private String schoolAddress;

	/** 工作单位地址-省 */
	private String workUnitProv;

	/** 工作单位地址-城市 */
	private String workUnitCity;

	/** 工作单位地址-区域 */
	private String workUnitArea;

	/** 单位地址 */
	private String unitAddress;

	/** 家庭地址-省 */
	private String famProv;

	/** 家庭地址-城市 */
	private String famCity;

	/** 家庭地址-区域 */
	private String famArea;

	/** 家庭地址 */
	private String dormAddress;

	/** 现居地址-省 */
	private String nowProv;

	/** 现居地址-城市 */
	private String nowCity;

	/** 现居地址-区域 */
	private String nowArea;

	/** 现居地址 */
	private String nowAddress;

	/** 现居地址类型 */
	private String nowadresstype;
	
	/**单位规模*/
	private String companyScale;
	
	/**单位工作年限*/
	private String companyWorkYear;

	/*
	 * 2013.12.17 新增白领录入字段 andy.niu
	 */

	/*
	 * 根据新的需求 婚姻状况字段延用以前版本的字段 maritalStatus
	 */

	/** 配偶姓名 */
	private String spouseRealName;

	/** 配偶手机号码 */
	private Long spouseMobileNo;

	// 户籍地址

	/** 户籍地址省份代码 */
	private String censusProvince;

	/** 户籍地址城市代码 */
	private String censusCity;

	/** 户籍地址区域代码 */
	private String censusArea;

	/** 户籍地址地址信息 */
	private String censusAddress;

	/** 户籍地址邮编 */
	private String censusZipCode;

	/** 单位地址邮编 */
	private String unitZipCode;

	/** 工号 */
	private String employeeNumber;

	/** 单位行业--数据字典配置 */
	private String unitIndustry;

	/** 住房状况--数据字典 */
	private String housingCondition;

	/** 家庭地址邮编 */
	private String familyZipCode;

	/** 现居地址邮编 */
	private String currentZip;

	/* 个人资金情况 */

	/** 月收入总额 */
	private String allMonthlyIncome;

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

	/** 开户行 */

	private String bankType;

	/** 开户网点 */
	private String bankBranch;

	/** 银行卡号 */
	private String bankCardNo;

	/** 开户省份 */
	private String bankProvince;

	/** 开户城市 */
	private String bankCity;

	/*
	 * 新增学生类型 andy.niu 2014.03.20 **
	 */

	/** 学生类型 */
	private String studentType;

	/** 代扣开户银行 */
	private String proxyOpenBank;

	/** 代扣银行卡号 */
	private String proxyBankCard;

	/** 首次还款时间 */
	private String firstPaymentDay;

	/** 合同确认时间 */
	private String compactCfmDate;

	/** 是否成功 */
	private String isSucc;

	/** 返回消息 */
	private String validateMsg;

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

	public String getLoanUse() {
		return loanUse;
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

	public String getQqNumber() {
		return qqNumber;
	}

	public void setQqNumber(String qqNumber) {
		this.qqNumber = qqNumber;
	}

	public String getIsSucc() {
		return isSucc;
	}

	public void setIsSucc(String isSucc) {
		this.isSucc = isSucc;
	}

	public String getValidateMsg() {
		return validateMsg;
	}

	public void setValidateMsg(String validateMsg) {
		this.validateMsg = validateMsg;
	}

	public String getProxyOpenBank() {
		return proxyOpenBank;
	}

	public void setProxyOpenBank(String proxyOpenBank) {
		this.proxyOpenBank = proxyOpenBank;
	}

	public String getProxyBankCard() {
		return proxyBankCard;
	}

	public void setProxyBankCard(String proxyBankCard) {
		this.proxyBankCard = proxyBankCard;
	}

	public String getFirstPaymentDay() {
		return firstPaymentDay;
	}

	public void setFirstPaymentDay(String firstPaymentDay) {
		this.firstPaymentDay = firstPaymentDay;
	}

	public String getCompactCfmDate() {
		return compactCfmDate;
	}

	public void setCompactCfmDate(String compactCfmDate) {
		this.compactCfmDate = compactCfmDate;
	}

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	public String getApplicationNo() {
		return applicationNo;
	}

	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCreditName() {
		return creditName;
	}

	public void setCreditName(String creditName) {
		this.creditName = creditName;
	}

	public String getCreditDay() {
		return creditDay;
	}

	public void setCreditDay(String creditDay) {
		this.creditDay = creditDay;
	}

	public String getCreditType() {
		return creditType;
	}

	public void setCreditType(String creditType) {
		this.creditType = creditType;
	}

	public String getCreditUse() {
		return creditUse;
	}

	public void setCreditUse(String creditUse) {
		this.creditUse = creditUse;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAdmissionYear() {
		return admissionYear;
	}

	public void setAdmissionYear(String admissionYear) {
		this.admissionYear = admissionYear;
	}

	public String getAdmissionDay() {
		return admissionDay;
	}

	public void setAdmissionDay(String admissionDay) {
		this.admissionDay = admissionDay;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getIdentityNo() {
		return identityNo;
	}

	public void setIdentityNo(String identityNo) {
		this.identityNo = identityNo;
	}

	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getUserGreade() {
		return userGreade;
	}

	public void setUserGreade(String userGreade) {
		this.userGreade = userGreade;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getFaculties() {
		return faculties;
	}

	public void setFaculties(String faculties) {
		this.faculties = faculties;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public String getEducational() {
		return educational;
	}

	public void setEducational(String educational) {
		this.educational = educational;
	}

	public String getFullTimeFlag() {
		return fullTimeFlag;
	}

	public void setFullTimeFlag(String fullTimeFlag) {
		this.fullTimeFlag = fullTimeFlag;
	}

	public String getLocationAddress() {
		return locationAddress;
	}

	public void setLocationAddress(String locationAddress) {
		this.locationAddress = locationAddress;
	}

	public String getLocationZip() {
		return locationZip;
	}

	public void setLocationZip(String locationZip) {
		this.locationZip = locationZip;
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

	public String getNowZip() {
		return nowZip;
	}

	public void setNowZip(String nowZip) {
		this.nowZip = nowZip;
	}

	public String getNowFixedTelArea() {
		return nowFixedTelArea;
	}

	public void setNowFixedTelArea(String nowFixedTelArea) {
		this.nowFixedTelArea = nowFixedTelArea;
	}

	public String getNowFixedTel() {
		return nowFixedTel;
	}

	public void setNowFixedTel(String nowFixedTel) {
		this.nowFixedTel = nowFixedTel;
	}

	public String getNowDormFlag() {
		return nowDormFlag;
	}

	public void setNowDormFlag(String nowDormFlag) {
		this.nowDormFlag = nowDormFlag;
	}

	public String getDormZip() {
		return dormZip;
	}

	public void setDormZip(String dormZip) {
		this.dormZip = dormZip;
	}

	public String getDormTelArea() {
		return dormTelArea;
	}

	public void setDormTelArea(String dormTelArea) {
		this.dormTelArea = dormTelArea;
	}

	public String getDormTel() {
		return dormTel;
	}

	public void setDormTel(String dormTel) {
		this.dormTel = dormTel;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getFatherMobile() {
		return fatherMobile;
	}

	public void setFatherMobile(String fatherMobile) {
		this.fatherMobile = fatherMobile;
	}

	public String getFatherTelArea() {
		return fatherTelArea;
	}

	public void setFatherTelArea(String fatherTelArea) {
		this.fatherTelArea = fatherTelArea;
	}

	public String getFatherTel() {
		return fatherTel;
	}

	public void setFatherTel(String fatherTel) {
		this.fatherTel = fatherTel;
	}

	public String getWorkUnits() {
		return workUnits;
	}

	public void setWorkUnits(String workUnits) {
		this.workUnits = workUnits;
	}

	public String getWorkUnitsAddress() {
		return workUnitsAddress;
	}

	public void setWorkUnitsAddress(String workUnitsAddress) {
		this.workUnitsAddress = workUnitsAddress;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getMotherMobile() {
		return motherMobile;
	}

	public void setMotherMobile(String motherMobile) {
		this.motherMobile = motherMobile;
	}

	public String getMotherTelArea() {
		return motherTelArea;
	}

	public void setMotherTelArea(String motherTelArea) {
		this.motherTelArea = motherTelArea;
	}

	public String getMotherTel() {
		return motherTel;
	}

	public void setMotherTel(String motherTel) {
		this.motherTel = motherTel;
	}

	public String getFatherAddress() {
		return fatherAddress;
	}

	public void setFatherAddress(String fatherAddress) {
		this.fatherAddress = fatherAddress;
	}

	public String getFaAddressZip() {
		return faAddressZip;
	}

	public void setFaAddressZip(String faAddressZip) {
		this.faAddressZip = faAddressZip;
	}

	public String getMatherAddress() {
		return matherAddress;
	}

	public void setMatherAddress(String matherAddress) {
		this.matherAddress = matherAddress;
	}

	public String getMaAddressZip() {
		return maAddressZip;
	}

	public void setMaAddressZip(String maAddressZip) {
		this.maAddressZip = maAddressZip;
	}

	public String getFamilyRelation() {
		return familyRelation;
	}

	public void setFamilyRelation(String familyRelation) {
		this.familyRelation = familyRelation;
	}

	public String getRelaName() {
		return relaName;
	}

	public void setRelaName(String relaName) {
		this.relaName = relaName;
	}

	public String getRelaMobile() {
		return relaMobile;
	}

	public void setRelaMobile(String relaMobile) {
		this.relaMobile = relaMobile;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getRelaWorkUnits() {
		return relaWorkUnits;
	}

	public void setRelaWorkUnits(String relaWorkUnits) {
		this.relaWorkUnits = relaWorkUnits;
	}

	public String getRelaWorkUnitsAddress() {
		return relaWorkUnitsAddress;
	}

	public void setRelaWorkUnitsAddress(String relaWorkUnitsAddress) {
		this.relaWorkUnitsAddress = relaWorkUnitsAddress;
	}

	public String getRelaWorkUnitsAddressSala() {
		return relaWorkUnitsAddressSala;
	}

	public void setRelaWorkUnitsAddressSala(String relaWorkUnitsAddressSala) {
		this.relaWorkUnitsAddressSala = relaWorkUnitsAddressSala;
	}

	public String getSaleSiteCode() {
		return saleSiteCode;
	}

	public void setSaleSiteCode(String saleSiteCode) {
		this.saleSiteCode = saleSiteCode;
	}

	public String getSaleServerCode() {
		return saleServerCode;
	}

	public void setSaleServerCode(String saleServerCode) {
		this.saleServerCode = saleServerCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getGraduation() {
		return graduation;
	}

	public void setGraduation(String graduation) {
		this.graduation = graduation;
	}

	public String getGraduationYear() {
		return graduationYear;
	}

	public void setGraduationYear(String graduationYear) {
		this.graduationYear = graduationYear;
	}

	public String getGraduationMonth() {
		return graduationMonth;
	}

	public void setGraduationMonth(String graduationMonth) {
		this.graduationMonth = graduationMonth;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
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

	public String getUnitZip() {
		return unitZip;
	}

	public void setUnitZip(String unitZip) {
		this.unitZip = unitZip;
	}

	public String getMonthlyIncome() {
		return monthlyIncome;
	}

	public void setMonthlyIncome(String monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}

	public String getOfficeSector() {
		return officeSector;
	}

	public void setOfficeSector(String officeSector) {
		this.officeSector = officeSector;
	}

	public String getJobDuties() {
		return jobDuties;
	}

	public void setJobDuties(String jobDuties) {
		this.jobDuties = jobDuties;
	}

	public String getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(String entryTime) {
		this.entryTime = entryTime;
	}

	public String getEntryTimeYear() {
		return entryTimeYear;
	}

	public void setEntryTimeYear(String entryTimeYear) {
		this.entryTimeYear = entryTimeYear;
	}

	public String getEntryTimeMonth() {
		return entryTimeMonth;
	}

	public void setEntryTimeMonth(String entryTimeMonth) {
		this.entryTimeMonth = entryTimeMonth;
	}

	public String getSectors() {
		return sectors;
	}

	public void setSectors(String sectors) {
		this.sectors = sectors;
	}

	public String getJobNature() {
		return jobNature;
	}

	public void setJobNature(String jobNature) {
		this.jobNature = jobNature;
	}

	public String getRelaNameSala() {
		return relaNameSala;
	}

	public void setRelaNameSala(String relaNameSala) {
		this.relaNameSala = relaNameSala;
	}

	public String getRelaMobileSala() {
		return relaMobileSala;
	}

	public void setRelaMobileSala(String relaMobileSala) {
		this.relaMobileSala = relaMobileSala;
	}

	public String getRelationSala() {
		return relationSala;
	}

	public void setRelationSala(String relationSala) {
		this.relationSala = relationSala;
	}

	public String getRelaWorkUnitsSala() {
		return relaWorkUnitsSala;
	}

	public void setRelaWorkUnitsSala(String relaWorkUnitsSala) {
		this.relaWorkUnitsSala = relaWorkUnitsSala;
	}

	public String getWorkExperience() {
		return workExperience;
	}

	public void setWorkExperience(String workExperience) {
		this.workExperience = workExperience;
	}

	public String getSchoolProv() {
		return schoolProv;
	}

	public void setSchoolProv(String schoolProv) {
		this.schoolProv = schoolProv;
	}

	public String getSchoolCity() {
		return schoolCity;
	}

	public void setSchoolCity(String schoolCity) {
		this.schoolCity = schoolCity;
	}

	public String getSchoolArea() {
		return schoolArea;
	}

	public void setSchoolArea(String schoolArea) {
		this.schoolArea = schoolArea;
	}

	public String getSchoolAddress() {
		return schoolAddress;
	}

	public void setSchoolAddress(String schoolAddress) {
		this.schoolAddress = schoolAddress;
	}

	public String getWorkUnitProv() {
		return workUnitProv;
	}

	public void setWorkUnitProv(String workUnitProv) {
		this.workUnitProv = workUnitProv;
	}

	public String getWorkUnitCity() {
		return workUnitCity;
	}

	public void setWorkUnitCity(String workUnitCity) {
		this.workUnitCity = workUnitCity;
	}

	public String getWorkUnitArea() {
		return workUnitArea;
	}

	public void setWorkUnitArea(String workUnitArea) {
		this.workUnitArea = workUnitArea;
	}

	public String getUnitAddress() {
		return unitAddress;
	}

	public void setUnitAddress(String unitAddress) {
		this.unitAddress = unitAddress;
	}

	public String getFamProv() {
		return famProv;
	}

	public void setFamProv(String famProv) {
		this.famProv = famProv;
	}

	public String getFamCity() {
		return famCity;
	}

	public void setFamCity(String famCity) {
		this.famCity = famCity;
	}

	public String getFamArea() {
		return famArea;
	}

	public void setFamArea(String famArea) {
		this.famArea = famArea;
	}

	public String getDormAddress() {
		return dormAddress;
	}

	public void setDormAddress(String dormAddress) {
		this.dormAddress = dormAddress;
	}

	public String getNowProv() {
		return nowProv;
	}

	public void setNowProv(String nowProv) {
		this.nowProv = nowProv;
	}

	public String getNowCity() {
		return nowCity;
	}

	public void setNowCity(String nowCity) {
		this.nowCity = nowCity;
	}

	public String getNowArea() {
		return nowArea;
	}

	public void setNowArea(String nowArea) {
		this.nowArea = nowArea;
	}

	public String getNowAddress() {
		return nowAddress;
	}

	public void setNowAddress(String nowAddress) {
		this.nowAddress = nowAddress;
	}

	public String getNowadresstype() {
		return nowadresstype;
	}

	public void setNowadresstype(String nowadresstype) {
		this.nowadresstype = nowadresstype;
	}

	public String getSpouseRealName() {
		return spouseRealName;
	}

	public void setSpouseRealName(String spouseRealName) {
		this.spouseRealName = spouseRealName;
	}

	public Long getSpouseMobileNo() {
		return spouseMobileNo;
	}

	public void setSpouseMobileNo(Long spouseMobileNo) {
		this.spouseMobileNo = spouseMobileNo;
	}

	public String getCensusProvince() {
		return censusProvince;
	}

	public void setCensusProvince(String censusProvince) {
		this.censusProvince = censusProvince;
	}

	public String getCensusCity() {
		return censusCity;
	}

	public void setCensusCity(String censusCity) {
		this.censusCity = censusCity;
	}

	public String getCensusArea() {
		return censusArea;
	}

	public void setCensusArea(String censusArea) {
		this.censusArea = censusArea;
	}

	public String getCensusAddress() {
		return censusAddress;
	}

	public void setCensusAddress(String censusAddress) {
		this.censusAddress = censusAddress;
	}

	public String getCensusZipCode() {
		return censusZipCode;
	}

	public void setCensusZipCode(String censusZipCode) {
		this.censusZipCode = censusZipCode;
	}

	public String getUnitZipCode() {
		return unitZipCode;
	}

	public void setUnitZipCode(String unitZipCode) {
		this.unitZipCode = unitZipCode;
	}

	public String getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public String getUnitIndustry() {
		return unitIndustry;
	}

	public void setUnitIndustry(String unitIndustry) {
		this.unitIndustry = unitIndustry;
	}

	public String getHousingCondition() {
		return housingCondition;
	}

	public void setHousingCondition(String housingCondition) {
		this.housingCondition = housingCondition;
	}

	public String getFamilyZipCode() {
		return familyZipCode;
	}

	public void setFamilyZipCode(String familyZipCode) {
		this.familyZipCode = familyZipCode;
	}

	public String getCurrentZip() {
		return currentZip;
	}

	public void setCurrentZip(String currentZip) {
		this.currentZip = currentZip;
	}

	public String getAllMonthlyIncome() {
		return allMonthlyIncome;
	}

	public void setAllMonthlyIncome(String allMonthlyIncome) {
		this.allMonthlyIncome = allMonthlyIncome;
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

	public String getBankBranch() {
		return bankBranch;
	}

	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}

	public String getBankCardNo() {
		return bankCardNo;
	}

	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}

	public String getBankProvince() {
		return bankProvince;
	}

	public void setBankProvince(String bankProvince) {
		this.bankProvince = bankProvince;
	}

	public String getBankCity() {
		return bankCity;
	}

	public void setBankCity(String bankCity) {
		this.bankCity = bankCity;
	}

	public String getStudentType() {
		return studentType;
	}

	public void setStudentType(String studentType) {
		this.studentType = studentType;
	}

	public String getBankType() {
		return bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
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

}
