package com.hengyuan.hicash.domain.event.apply;

import java.util.HashMap;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.app.ApplicationPay;
import com.hengyuan.hicash.entity.app.InputAppPay;
import com.hengyuan.hicash.exception.UpdateAppPayException;
import com.hengyuan.hicash.exception.UpdateInputAppException;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * @author mary.luo
 *
 */
public class CreateAppPayEvent {
	
	private static Logger logger = Logger.getLogger(CreateAppPayEvent.class);

	/**
	 * 创建申请件1
	 * 
	 * @param applicationPay
	 */
	public int createApplication(ApplicationPay applicationPay) {
		String createAppSql = "INSERT INTO d_application_pay "
				+ "(app_application_no,pro_id,app_creditproduct_id,app_username,app_sale_code,"
				+ "app_install_ment,app_month_pay,"
				+ "app_dis_count,isallocation,isMerchant," + " tranPrice,"
				+ "productType,provice,app_city_code,ALLNODE,NODE,"
				+ "STATUS,APPLY_AMOUNT,pay_type" + ")VALUES('"
				+ applicationPay.getApplicationNo()
				+ "',"
				+ "'"
				+ applicationPay.getProductInfo()
				+ "',"
				+ "'"
				+ applicationPay.getCreditProductId()
				+ "',"
				+ "'"
				+ applicationPay.getUsername()
				+ "',"
				+ "'"
				+ applicationPay.getSaleCode()
				+ "',"
				+ "'"
				+ applicationPay.getInstallMent()
				+ "',"
				+ "'"
				+ applicationPay.getMonthPay()
				+ "',"
				+ "'"
				+ applicationPay.getDisCount()
				+ "',"
				+ ""
				+ applicationPay.isIsallocation()
				+ ","
				+ ""
				+ applicationPay.isMerchant()
				+ ","
				+ "'"
				+ applicationPay.getTranPrice()
				+ "',"
				+ "'"
				+ applicationPay.getProductType()
				+ "',"
				+ "'"
				+ applicationPay.getProvice()
				+ "',"
				+ "'"
				+ applicationPay.getCityCode()
				+ "',"
				+ "'"
				+ applicationPay.getAllNode()
				+ "',"
				+ "'"
				+ applicationPay.getNode()
				+ "',"
				+ "'"
				+ applicationPay.getStatus()
				+ "',"
				+ "'"
				+ applicationPay.getApplyAmount()
				+ "',"
				+ "'"
				+ applicationPay.getPayType() + "')";
		
		RecordUtils.writeAction(logger, null, createAppSql);
		return ConnManager.executeUpdate(createAppSql);
	}

	/**
	 * 创建申请件2 *
	 * 
	 * @param inputAppPay
	 */
	public int createInputAppPay(InputAppPay inputAppPay) {
		String createAppSql = "INSERT INTO d_input_app (" 
//				"appid,"
				+ "create_date,create_user,update_date,"
				+ "update_user,admissionDay,admissionYear,"
				+ "applicationNo,creditDay,creditName,"
				+ "creditType,creditUse,dormAddress,dormTel,"
				+ "dormTelArea,dormZip,educational,"
				+ "emailAddress,faAddressZip,faculties,"
				+ "fatherAddress,fatherMobile,fatherName,"
				+ "fatherTel,fatherTelArea,fullTimeFlag,"
				+ "gender,identityNo,locationAddress,"
				+ "locationZip,mobileNo,motherMobile,"
				+ "motherName,motherTel,motherTelArea,"
				+ "nowAddress,nowDormFlag,nowFixedTel,"
				+ "nowFixedTelArea,nowZip,productName,"
				+ "productType,realName,relaMobile,relaName,"
				+ "relation,saleServerCode,saleSiteCode,"
				+ "school,specialty,studentId,faAddressTel,"
				+ "maAddressZip,matherAddress,userName,"
				+ "birthday,familyRelation,relaWorkUnits,"
				+ "relaWorkUnitsAddress,schoolAddress,valid,"
				+ "workUnits,workUnitsAddress,userGreade,"
				+ "education,entryTime,graduation,"
				+ "jobDuties,maritalStatus,monthlyIncome,"
				+ "officeSector,relaMobileSala,relaNameSala,"
				+ "relaWorkUnitsAddressSala,relaWorkUnitsSala,"
				+ "relationSala,sectors,unitAddress,"
				+ "unitName,unitTel,unitZip,workExperience,"
				+ "jobNature,unitTelArea,contactArea,"
				+ "contactCity,contactProv,famArea,famCity,"
				+ "famProv,nowArea,nowCity,nowProv,"
				+ "relaArea,relaCity,relaProv,schoolArea,"
				+ "schoolCity,schoolProv,entryTimeDay,"
				+ "entryTimeYear,graduationDay,graduationYear,"
				+ "nowadresstype,workUnitArea,workUnitCity,"
				+ "workUnitProv,admissionMonth,entryTimeMonth,"
				+ "graduationMonth," + " allMonthlyIncome," + " censusAddress,"
				+ "censusArea,censusCity,censusProvince,"
				+ "censusZipCode,creditCardCount,"
				+ "creditHigthAmount,creditTotalAmount,"
				+ "currentZip,employeeNumber,familyZipCode,"
				+ "housingCondition,loanCount,loanMonthRent,"
				+ "loanTotalAmount,monthRent,monthlyConsumption,"
				+ "spouseMobileNo,spouseRealName,unitIndustry,"
				+ "unitZipCode,BANK_BRANCH,BANK_CARD_NO,"
				+ "BANK_TYPE,STUDENT_TYPE,bank_city,"
				+ "bank_province,new_product_Name )VALUES" + "("
//						"'"
//				+ inputAppPay.getAppId()
//				+ "',"
				+ "'"
				+ inputAppPay.getCreateDate()
				+ "',"
				+ "'',"
				+ "'"
				+ inputAppPay.getUpdateDate()
				+ "',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'"
				+ inputAppPay.getApplicationNo()
				+ "',"
				+ "'"
				+ inputAppPay.getCreditDay()
				+ "',"
				+ "'',"
				+ "'"
				+ inputAppPay.getCreditType()
				+ "',"
				+ "'"
				+ inputAppPay.getCreditUse()
				+ "',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'"
				+ inputAppPay.getProductName()
				+ "',"
				+ "'"
				+ inputAppPay.getProductType()
				+ "',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'0',"
				+ "'0',"
				+ "'0',"
				+ "'0',"
				+ "'0',"
				+ "'0',"
				+ "'0',"
				+ "'0',"
				+ "'0',"
				+ "'0',"
				+ "'"
				+ inputAppPay.getCreditHigthAmount()
				+ "',"
				+ "'',"
				+ "'',"
				+ "'',"
				+ "'0',"
				+ "'0',"
				+ "'0',"
				+ "'0',"
				+ "'0',"
				+ "'0',"
				+ "'0',"
				+ "'0',"
				+ "'0',"
				+ "'"
				+ inputAppPay.getBankBranch()
				+ "',"
				+ "'"
				+ inputAppPay.getBankCardNo()
				+ "',"
				+ "'"
				+ inputAppPay.getBankType()
				+ "',"
				+ "'',"
				+ "'"
				+ inputAppPay.getBankCity()
				+ "',"
				+ "'"
				+ inputAppPay.getBankProvince() + "','')";
		RecordUtils.writeAction(logger, null, createAppSql);
		return ConnManager.executeUpdate(createAppSql);
	}

	public void updateApplicationPay(ApplicationPay applicationPay) throws UpdateAppPayException {

		String updateAppSql = "UPDATE d_application_pay " + " SET  ";
		if(applicationPay.getApplicationNode()!=null){
			updateAppSql += " app_application_node = '"+applicationPay.getApplicationNode()+"',";
		}
		if(applicationPay.getApplicationStatus()!=null){
			updateAppSql += " app_application_status = '"+applicationPay.getApplicationStatus()+"',";
		}
		if(applicationPay.getUsername()!=null){
			updateAppSql +=  "app_username = '"+applicationPay.getUsername()+"',";
		}
		if(applicationPay.getUserIdentityNo()!=null){
			updateAppSql += "app_user_identityno = '"+applicationPay.getUserIdentityNo()+"',";
		}
		if(!StringUtils.isEmpty(applicationPay.getMerchAntEndTime())){
			updateAppSql += "merchant_endtime = '"+applicationPay.getMerchAntEndTime()+"',";
		}
		if(!StringUtils.isEmpty(applicationPay.getApprovalProcessEndDate())){
			updateAppSql +="approval_pro_end_date = '"+applicationPay.getApprovalProcessEndDate()+"',";
		}
		
		if(!StringUtils.isEmpty(applicationPay.getApprovalProcessEndName())){
			updateAppSql +="approval_pro_end_name = '"+applicationPay.getApprovalProcessEndName()+"',";
		}
		
		if(!StringUtils.isEmpty(applicationPay.getApprovalProcessEndnode())){
			updateAppSql += "approval_pro_end_node = '"+applicationPay.getApprovalProcessEndnode()+"',";
		}
		if(!StringUtils.isEmpty(applicationPay.getFaceResult())){
			updateAppSql += "faceResult = '"+applicationPay.getFaceResult()+"',";
		}
		
        if(!StringUtils.isEmpty(applicationPay.getFaceRefause())){
        	updateAppSql += "app_face_refause = '"+applicationPay.getFaceRefause()+"',";
        }
		
        if(!StringUtils.isEmpty(applicationPay.getRefuseFlag())){
        	updateAppSql += "REFUSE_FLAG = '"+applicationPay.getRefuseFlag()+"',";
        }
        
		if(applicationPay.getAppStage()!=null){
			updateAppSql += "app_app_stage = '"+applicationPay.getAppStage()+"',";
		}
		if(applicationPay.getPayMentFlag()!=null){
			updateAppSql += "app_payment_flag = "+applicationPay.getPayMentFlag()+",";
		}
		if(applicationPay.getCustomerType()!=null){
			updateAppSql += "app_cust_type = '"+applicationPay.getCustomerType()+"',";
		}
		if(applicationPay.getAddress()!=null){
			updateAppSql += "app_address = '"+applicationPay.getAddress()+"',";
		}
		if(applicationPay.getGradeWork()!=null){
			updateAppSql += "app_grade_work = '"+applicationPay.getGradeWork()+"',";
		}
		if(applicationPay.getAllNode()!=null){
			updateAppSql += "ALLNODE = '"+applicationPay.getAllNode()+"'," ;
		}
		if(applicationPay.getNode()!=null){
			updateAppSql += "NODE = '"+applicationPay.getNode()+"',";
		}
		if(applicationPay.getStatus()!=null){
			updateAppSql += "STATUS = '"+applicationPay.getStatus()+"',";
		}
		if(applicationPay.getReceiveFlag()!=null){
			updateAppSql += "app_receive_flag = "+applicationPay.getReceiveFlag()+",";
		}
		if(applicationPay.getReceiveCodeFlag()!=null){
			updateAppSql += "app_code_flag = "+applicationPay.getReceiveCodeFlag()+",";
		}
		if(applicationPay.getCloseFlag()!=null){
			updateAppSql += "app_close_flag = "+applicationPay.getCloseFlag()+",";
		}
		if(applicationPay.getCheckFlag()!=null){
			updateAppSql += "app_check_flag = "+applicationPay.getCheckFlag()+",";
		}
		if(applicationPay.getContractFlag()!=null){
			updateAppSql += "app_contract_flag = "+applicationPay.getContractFlag()+",";
		}
		if(applicationPay.getDeliveryFlag()!=null){
			updateAppSql += "app_delivery_flag ="+applicationPay.getDeliveryFlag()+",";
		}
		if(applicationPay.getSubmitFlag()!=null){
			updateAppSql += "app_submit_flag = "+applicationPay.getSubmitFlag()+",";
		}
		if(applicationPay.getUploadFlag()!=null){
			updateAppSql += "app_upload_flag = "+applicationPay.getUploadFlag()+",";
		}
		if(applicationPay.getCompFlag()!=null){
			updateAppSql += "compFlag = "+applicationPay.getCompFlag();
		}
		
		if(updateAppSql.endsWith(",")){
			updateAppSql=updateAppSql.substring(0,updateAppSql.length()-1);
		}
			updateAppSql += "  WHERE  app_application_no = '"+applicationPay.getApplicationNo()+"' ";
		
			RecordUtils.writeAction(logger, null, updateAppSql);
			if(ConnManager.executeUpdate(updateAppSql) <= 0){
				throw new UpdateAppPayException();
			}
//		return ConnManager.executeUpdate(updateAppSql);
	}

	public void updateInputAppPay(InputAppPay inputAppPay) throws UpdateInputAppException {

		String updateAppSql = "UPDATE d_input_app "+
								"SET "+
								"realName = '"+inputAppPay.getRealName()+"'," +
								"identityNo = '"+inputAppPay.getIdentityNo()+"'," +
								"nowFixedTel = '"+inputAppPay.getNowFixedTel()+"'," +
								"nowFixedTelArea = '"+inputAppPay.getNowFixedTelArea()+"'," +
								"valid = '"+inputAppPay.getValid()+"'," +
								"emailAddress = '"+inputAppPay.getEmailAddress()+"'," +
								"mobileNo = '"+inputAppPay.getMobileNo()+"'," +
								"fatherName = '"+inputAppPay.getFatherName()+"'," +
								"familyRelation = '"+inputAppPay.getFamilyRelation()+"'," +
								"fatherMobile = '"+inputAppPay.getFatherMobile()+"'," +
								"workUnits = '"+inputAppPay.getWorkUnits()+"'," +
								"workUnitsAddress = '"+inputAppPay.getWorkUnitsAddress()+"'," +
								"relaName = '"+inputAppPay.getRelaName()+"'," +
								"relation = '"+inputAppPay.getRelation()+"'," +
								"relaMobile = '"+inputAppPay.getRelaMobile()+"'," +
								"relaWorkUnits = '"+inputAppPay.getRelaWorkUnits()+"'," +
								"relaWorkUnitsAddress = '"+inputAppPay.getRelaWorkUnitsAddress()+"'," +
								"fullTimeFlag = '"+inputAppPay.getFullTimeFlag()+"'," +
								"userName = '"+inputAppPay.getUserName()+"'," +
								"create_date = now()," +
								"create_user = '"+inputAppPay.getCreateUser()+"'," +

								"famProv = '"+inputAppPay.getFamProv()+"'," +
								"famCity = '"+inputAppPay.getFamCity()+"'," +
								"famArea = '"+inputAppPay.getFamArea()+"'," +
								"dormAddress = '"+inputAppPay.getDormAddress()+"',"+
								"nowProv = '"+inputAppPay.getNowProv()+"',"+
								"nowCity    = '"+inputAppPay.getNowCity()+"',"+
								"nowArea    = '"+inputAppPay.getNowArea()+"',"+
								"nowAddress = '"+inputAppPay.getNowAddress()+"',"+
								"gender     = '"+inputAppPay.getGender()+"',"+
								"birthday   = '"+inputAppPay.getBirthday()+"',";
		/*2016-04-20*/
		if(inputAppPay.getCompanyScale()!=null && !"".equals(inputAppPay.getCompanyScale())){
			updateAppSql += " COMPANY_SCALE = '"+inputAppPay.getCompanyScale()+"',";
		}
		
		if(inputAppPay.getCompanyWorkYear()!=null && !"".equals(inputAppPay.getCompanyWorkYear())){
			updateAppSql += " COMPANY_WORK_YEAR = '"+inputAppPay.getCompanyWorkYear()+"',";
		}
		
		
		if(inputAppPay.getNation()!=null && !"".equals(inputAppPay.getNation())){
			updateAppSql += " NATION = '"+inputAppPay.getNation()+"',";
		}
		
		if(inputAppPay.getIdCardValStartDate()!=null && !"".equals(inputAppPay.getIdCardValStartDate())){
			updateAppSql += " IDCARD_VALIDITY_STARTDATE = '"+inputAppPay.getIdCardValStartDate()+"',";
		}
		
		if(inputAppPay.getIdCardValEndDate()!=null && !"".equals(inputAppPay.getIdCardValEndDate())){
			updateAppSql += " IDCARD_VALIDITY_ENDDATE = '"+inputAppPay.getIdCardValEndDate()+"',";
		}
		
		if(inputAppPay.getIdCardValidity()!=null && !"".equals(inputAppPay.getIdCardValidity())){
			updateAppSql += " IDCARD_VALIDITY = '"+inputAppPay.getIdCardValidity()+"',";
		}

		if(inputAppPay.getLoanUse()!=null && !"".equals(inputAppPay.getLoanUse())){
			updateAppSql += " borrow_money_use = '"+inputAppPay.getLoanUse()+"',";
		}
		
		if(inputAppPay.getSchool()!=null && !"".equals(inputAppPay.getSchool())){
			updateAppSql += " school = '"+inputAppPay.getSchool()+"',";
		}
		
		if(inputAppPay.getStudentId()!=null && !"".equals(inputAppPay.getStudentId())){
			updateAppSql += " studentId = '"+inputAppPay.getStudentId()+"',";
		}
		if(inputAppPay.getEducational()!=null && !"".equals(inputAppPay.getEducational())){
			updateAppSql += " educational = '"+inputAppPay.getEducational()+"',";
		}
		// 入学时间-年
		if(inputAppPay.getAdmissionYear()!=null && !"".equals(inputAppPay.getAdmissionYear())){
			updateAppSql += " admissionYear = '"+inputAppPay.getAdmissionYear()+"',";
		}
//		if(inputAppPay.getAdmissionDay()!=null && !"".equals(inputAppPay.getAdmissionDay())){
//			updateAppSql += " admissionDay = '"+inputAppPay.getAdmissionDay()+"',";
//		}
		// 入学时间-月
		if(inputAppPay.getAdmissionDay()!=null && !"".equals(inputAppPay.getAdmissionDay())){
			updateAppSql += " admissionMonth = '"+inputAppPay.getAdmissionDay()+"',";
		}
		if(inputAppPay.getFaculties()!=null && !"".equals(inputAppPay.getFaculties())){
			updateAppSql += " faculties = '"+inputAppPay.getFaculties()+"',";
		}
		if(inputAppPay.getSchoolProv()!=null && !"".equals(inputAppPay.getSchoolProv())){
			updateAppSql += " schoolProv = '"+inputAppPay.getSchoolProv()+"',";
		}
		if(inputAppPay.getSchoolCity()!=null && !"".equals(inputAppPay.getSchoolCity())){
			updateAppSql += " schoolCity = '"+inputAppPay.getSchoolCity()+"',";
		}
		if(inputAppPay.getSchoolArea()!=null && !"".equals(inputAppPay.getSchoolArea())){
			updateAppSql += " schoolArea = '"+inputAppPay.getSchoolArea()+"',";
		}
		if(inputAppPay.getSchoolAddress()!=null && !"".equals(inputAppPay.getSchoolAddress())){
			updateAppSql += " schoolAddress = '"+inputAppPay.getSchoolAddress()+"',";
		}
		if(inputAppPay.getSpecialty()!=null && !"".equals(inputAppPay.getSpecialty())){
			updateAppSql += " specialty = '"+inputAppPay.getSpecialty()+"',";
		}
		if(inputAppPay.getUserGreade()!=null && !"".equals(inputAppPay.getUserGreade())){
			updateAppSql += " userGreade = '"+inputAppPay.getUserGreade()+"',";
		}if(inputAppPay.getEducation()!=null && !"".equals(inputAppPay.getEducation())){
			updateAppSql += " education = '"+inputAppPay.getEducation()+"',";
		}
		if(inputAppPay.getGraduationYear()!=null && !"".equals(inputAppPay.getGraduationYear())){
			updateAppSql += " graduationYear = '"+inputAppPay.getGraduationYear()+"',";
		}
		if(inputAppPay.getGraduationMonth()!=null && !"".equals(inputAppPay.getGraduationMonth())){
			updateAppSql += " graduationMonth = '"+inputAppPay.getGraduationMonth()+"',";
		}
		if(inputAppPay.getWorkExperience()!=null && !"".equals(inputAppPay.getWorkExperience())){
			updateAppSql += " workExperience = '"+inputAppPay.getWorkExperience()+"',";
		}
		if(inputAppPay.getSectors()!=null && !"".equals(inputAppPay.getSectors())){
			updateAppSql += " sectors = '"+inputAppPay.getSectors()+"',";
		}
		if(inputAppPay.getUnitName()!=null && !"".equals(inputAppPay.getUnitName())){
			updateAppSql += " unitName = '"+inputAppPay.getUnitName()+"',";
		}
		if(inputAppPay.getUnitTelArea()!=null && !"".equals(inputAppPay.getUnitTelArea())){
			updateAppSql += " unitTelArea = '"+inputAppPay.getUnitTelArea()+"',";
		}
		if(inputAppPay.getUnitTel()!=null && !"".equals(inputAppPay.getUnitTel())){
			updateAppSql += " unitTel = '"+inputAppPay.getUnitTel()+"',";
		}
		if(inputAppPay.getMonthlyIncome()!=null && !"".equals(inputAppPay.getMonthlyIncome())){
			updateAppSql += " monthlyIncome = '"+inputAppPay.getMonthlyIncome()+"',";
		}
		if(inputAppPay.getOfficeSector()!=null && !"".equals(inputAppPay.getOfficeSector())){
			updateAppSql += " officeSector = '"+inputAppPay.getOfficeSector()+"',";
		}
		if(inputAppPay.getJobDuties()!=null && !"".equals(inputAppPay.getJobDuties())){
			updateAppSql += " jobDuties = '"+inputAppPay.getJobDuties()+"',";
		}
		if(inputAppPay.getMaritalStatus()!=null && !"".equals(inputAppPay.getMaritalStatus())){
			updateAppSql += " maritalStatus = '"+inputAppPay.getMaritalStatus()+"',";
		}
		if(inputAppPay.getSpouseRealName()!=null && !"".equals(inputAppPay.getSpouseRealName())){
			updateAppSql += " spouseRealName = '"+inputAppPay.getSpouseRealName()+"',";
		}
		if(inputAppPay.getSpouseMobileNo()!=null && !"".equals(inputAppPay.getSpouseMobileNo())){
			updateAppSql += " spouseMobileNo = '"+inputAppPay.getSpouseMobileNo()+"',";
		}
		// 学生类型
		if(inputAppPay.getStudentType()!=null && !"".equals(inputAppPay.getStudentType())){
			updateAppSql += " STUDENT_TYPE = '"+inputAppPay.getStudentType()+"',";
		}
		
		// qq号码
		if (inputAppPay.getQqNumber() != null&& !"".equals(inputAppPay.getQqNumber())) {
			updateAppSql += " QQ_NUMBER = '" + inputAppPay.getQqNumber()+ "',";
		}
		
		
		if(inputAppPay.getUnitIndustry()!=null && !"".equals(inputAppPay.getUnitIndustry())){
			updateAppSql += " unitIndustry = '"+inputAppPay.getUnitIndustry()+"',";
		}if(inputAppPay.getHousingCondition()!=null && !"".equals(inputAppPay.getHousingCondition())){
			updateAppSql += " housingCondition = '"+inputAppPay.getHousingCondition()+"',";
		}
		if(inputAppPay.getEmployeeNumber()!=null && !"".equals(inputAppPay.getEmployeeNumber())){
			updateAppSql += " employeeNumber = '"+inputAppPay.getEmployeeNumber()+"',";
		}
		if(inputAppPay.getCensusProvince()!=null && !"".equals(inputAppPay.getCensusProvince())){
			updateAppSql += " censusProvince = '"+inputAppPay.getCensusProvince()+"',";
		}
		if(inputAppPay.getCensusCity()!=null && !"".equals(inputAppPay.getCensusCity())){
			updateAppSql += " censusCity = '"+inputAppPay.getCensusCity()+"',";
		}
		if(inputAppPay.getCensusArea()!=null && !"".equals(inputAppPay.getCensusArea())){
			updateAppSql += " censusArea = '"+inputAppPay.getCensusArea()+"',";
		}
		if(inputAppPay.getCensusAddress()!=null && !"".equals(inputAppPay.getCensusAddress())){
			updateAppSql += " censusAddress = '"+inputAppPay.getCensusAddress()+"',";
		}
		if(inputAppPay.getMonthlyConsumption()!=null && !"".equals(inputAppPay.getMonthlyConsumption())){
			updateAppSql += " monthlyConsumption = '"+inputAppPay.getMonthlyConsumption()+"',";
		}
		if(inputAppPay.getMonthRent()!=null && !"".equals(inputAppPay.getMonthRent())){
			updateAppSql += " monthRent = '"+inputAppPay.getMonthRent()+"',";
		}
		if(inputAppPay.getLoanCount()!=null && !"".equals(inputAppPay.getLoanCount())){
			updateAppSql += " loanCount = '"+inputAppPay.getLoanCount()+"',";
		}
		if(inputAppPay.getLoanTotalAmount()!=null && !"".equals(inputAppPay.getLoanTotalAmount())){
			updateAppSql += " loanTotalAmount = '"+inputAppPay.getLoanTotalAmount()+"',";
		}
		if(inputAppPay.getLoanMonthRent()!=null && !"".equals(inputAppPay.getLoanMonthRent())){
			updateAppSql += " loanMonthRent = '"+inputAppPay.getLoanMonthRent()+"',";
		}
		if(inputAppPay.getCreditCardCount()!=null && !"".equals(inputAppPay.getCreditCardCount())){
			updateAppSql += " creditCardCount = '"+inputAppPay.getCreditCardCount()+"',";
		}
		if(inputAppPay.getCreditTotalAmount()!=null && !"".equals(inputAppPay.getCreditTotalAmount())){
			updateAppSql += " creditTotalAmount = '"+inputAppPay.getCreditTotalAmount()+"',";
		}
		if(inputAppPay.getCreditHigthAmount()!=null && !"".equals(inputAppPay.getCreditHigthAmount())){
			updateAppSql += " creditHigthAmount = '"+inputAppPay.getCreditHigthAmount()+"',";
		}
		if(inputAppPay.getEntryTimeYear()!=null && !"".equals(inputAppPay.getEntryTimeYear())){
			updateAppSql += " entryTimeYear = '"+inputAppPay.getEntryTimeYear()+"',";
		}
		if(inputAppPay.getEntryTimeMonth()!=null && !"".equals(inputAppPay.getEntryTimeMonth())){
			updateAppSql += " entryTimeMonth = '"+inputAppPay.getEntryTimeMonth()+"',";
		}
		if(inputAppPay.getWorkUnitProv()!=null && !"".equals(inputAppPay.getWorkUnitProv())){
			updateAppSql += " workUnitProv = '"+inputAppPay.getWorkUnitProv()+"',";
		}
		if(inputAppPay.getWorkUnitCity()!=null && !"".equals(inputAppPay.getWorkUnitCity())){
			updateAppSql += " workUnitCity = '"+inputAppPay.getWorkUnitCity()+"',";
		}
		if(inputAppPay.getWorkUnitArea()!=null && !"".equals(inputAppPay.getWorkUnitArea())){
			updateAppSql += " workUnitArea = '"+inputAppPay.getWorkUnitArea()+"',";
		}
		if(inputAppPay.getUnitAddress()!=null && !"".equals(inputAppPay.getUnitAddress())){
			updateAppSql += " unitAddress = '"+inputAppPay.getUnitAddress()+"',";
		}
		
		if(inputAppPay.getLanUserFlag()!=null && !"".equals(inputAppPay.getLanUserFlag())){
			updateAppSql += " LAN_USER_FLAG = '"+inputAppPay.getLanUserFlag()+"',";
		}
		
		if(inputAppPay.getInveteCode()!=null && !"".equals(inputAppPay.getInveteCode())){
			updateAppSql += " INVITE_CODE = '"+inputAppPay.getInveteCode()+"',";
		}
		
		if(inputAppPay.getStoreCode()!=null && !"".equals(inputAppPay.getStoreCode())){
			updateAppSql += " STORE_CODE = '"+inputAppPay.getStoreCode()+"',";
		}
		
		if(updateAppSql.endsWith(",")){
			updateAppSql=updateAppSql.substring(0,updateAppSql.length()-1);
		}
								
		updateAppSql += " where applicationNo='" +inputAppPay.getApplicationNo()+"'";
		RecordUtils.writeAction(logger, null, updateAppSql);
		if(ConnManager.executeUpdate(updateAppSql) <= 0){
			throw new UpdateInputAppException();
		}
//		return ConnManager.executeUpdate(updateAppSql);

	}
	
	public void updateAppBlue(HashMap<String, Object> setMap,
			HashMap<String, Object> whereMap) throws UpdateAppPayException {

		String updateSql = MapAssemForSql.getUpdateSql(TableConsts.APPLICATION_PAY,
				setMap, whereMap);

		RecordUtils.writeAction(logger, null, updateSql);

		if (ConnManager.executeUpdate(updateSql.toString()) <= 0) {
			throw new UpdateAppPayException();
		}
	}
}
