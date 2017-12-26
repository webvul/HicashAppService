package com.hengyuan.hicash.domain.query.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.user.CustCustomer;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 
 * @author Cary.Liu
 * @create 2014-09-25
 * 
 */
public class CustcustomerQuery extends AbstractDAO<CustCustomer> {
	
	private static Logger logger = Logger.getLogger(CustcustomerQuery.class);

	@Override
	public CustCustomer mapping(ResultSet rs) throws SQLException {
		CustCustomer custCustomer = new CustCustomer();
		if (rs != null) {
			custCustomer.setId(StringUtils.valueOf(rs.getObject("ID")));	//主键
			custCustomer.setCustType(StringUtils.valueOf(rs.getObject("customertype")));	//客户类型
			custCustomer.setSchoolId(StringUtils.valueOf(rs.getObject("SCHOOL_ID")));		//学校ID
			custCustomer.setSchool(StringUtils.valueOf(rs.getObject("school")));			//学校
			custCustomer.setUnitName(StringUtils.valueOf(rs.getObject("unitname")));		//单位名称
			custCustomer.setMobileNo(StringUtils.valueOf(rs.getObject("mobile")));			//用户手机号码
			custCustomer.setRealName(StringUtils.valueOf(rs.getObject("name")));			//用户真实姓名
			custCustomer.setUserName(StringUtils.valueOf(rs.getObject("username")));		//用户名
			custCustomer.setIdentityNo(StringUtils.valueOf(rs.getObject("identity_no")));	//身份证号码
			custCustomer.setDegree(StringUtils.valueOf(rs.getObject("degree")));			//学历
			custCustomer.setEmailAdress(StringUtils.valueOf(rs.getObject("email_adress")));
			custCustomer.setWorkAreaCity(StringUtils.valueOf(rs.getObject("work_area_city"))); //工作市
			custCustomer.setOtherAdressCity(StringUtils.valueOf(rs.getObject("other_adress_city"))); //现居住市
			custCustomer.setNowEduCation(StringUtils.valueOf(rs.getObject("now_education")));//最高学历
			custCustomer.setUserclass(StringUtils.valueOf(rs.getObject("userclass")));//年级
			custCustomer.setWorkExperience(StringUtils.valueOf(rs.getObject("workExperience")));//工作年限
			custCustomer.setFixedTel(StringUtils.valueOf(rs.getObject("fixed_tel")));//固定电话
			custCustomer.setFixedTelArea(StringUtils.valueOf(rs.getObject("fixed_tel_area")));//固定电话区号
			custCustomer.setValid(StringUtils.valueOf(rs.getObject("valid")));//证件有效期
			
			custCustomer.setImmediateName(StringUtils.valueOf(rs.getObject("immediate_Name")));//亲属姓名
			custCustomer.setImmediateRelation(StringUtils.valueOf(rs.getObject("immediate_Relation")));//亲属关系
			custCustomer.setImmediateMobile(StringUtils.valueOf(rs.getObject("immediate_Mobile")));//亲属手机
			custCustomer.setImmediateJob(StringUtils.valueOf(rs.getObject("immediate_Job")));//亲属单位
			custCustomer.setImmediateAdress(StringUtils.valueOf(rs.getObject("immediate_Adress")));//亲属单位地址
			
			custCustomer.setEmergencyName(StringUtils.valueOf(rs.getObject("emergency_Name")));//紧急联系人姓名
			custCustomer.setEmergencyRelation(StringUtils.valueOf(rs.getObject("emergency_Relation")));//紧急联系人关系
			custCustomer.setEmergencyMobile(StringUtils.valueOf(rs.getObject("emergency_Mobile")));//紧急联系人手机
			custCustomer.setEmergencyJob(StringUtils.valueOf(rs.getObject("emergency_Job")));//紧急联系人单位
			custCustomer.setEmergencyAdress(StringUtils.valueOf(rs.getObject("emergency_Adress")));//紧急联系人地址
			
			custCustomer.setFullTimeFlag(StringUtils.valueOf(rs.getObject("fullTime_flag")));//是否全日制
			
			custCustomer.setFimilyAreaProvince(StringUtils.valueOf(rs.getObject("fimily_Area_Province")));//家庭--省
			custCustomer.setFimilyAreaCity(StringUtils.valueOf(rs.getObject("fimily_Area_City")));//家庭--市
			custCustomer.setFimilyAreaArea(StringUtils.valueOf(rs.getObject("fimily_Area_Area")));//家庭--区
			custCustomer.setFimilyAreaRoad(StringUtils.valueOf(rs.getObject("fimily_Area_Road")));//家庭地址
			
			custCustomer.setOtherAdressProvince(StringUtils.valueOf(rs.getObject("other_Adress_Province")));//现居住省
			custCustomer.setOtherAdressArea(StringUtils.valueOf(rs.getObject("Other_Adress_Area")));//现居住区
			custCustomer.setOtherAccommodationAddress(StringUtils.valueOf(rs.getObject("other_Accommodation_Address")));//其他街道住址
			
			custCustomer.setStudentId(StringUtils.valueOf(rs.getObject("student_id")));//学号
			
			
			custCustomer.setEducational(StringUtils.valueOf(rs.getObject("educational")));//在读学历
			
			custCustomer.setAdmissionTime(StringUtils.valueOf(rs.getObject("admission_Time")));//入学时间
			
			custCustomer.setFaculties(StringUtils.valueOf(rs.getObject("faculties")));//所在院校
			custCustomer.setSchoolAreaProvince(StringUtils.valueOf(rs.getObject("school_Area_Province")));//院校--省
			custCustomer.setSchoolAreaCity(StringUtils.valueOf(rs.getObject("school_Area_City")));//院校--市
			custCustomer.setSchoolAreaArea(StringUtils.valueOf(rs.getObject("school_Area_Area")));//院校--区
			custCustomer.setSchoolAreaRoad(StringUtils.valueOf(rs.getObject("school_Area_Road")));//院校--院校地址
			
			custCustomer.setSpecialty(StringUtils.valueOf(rs.getObject("specialty")));//专业
			
			custCustomer.setEndSchoolTime(StringUtils.valueOf(rs.getObject("end_school_time")));//毕业时间
			
			custCustomer.setGraduatedSchool(StringUtils.valueOf(rs.getObject("graduated_School")));//白领毕业学校
			custCustomer.setUnitTelArea(StringUtils.valueOf(rs.getObject("unit_tel_area")));//白领单位固定电话区号
			custCustomer.setUnitTel(StringUtils.valueOf(rs.getObject("unit_tel")));//白领单位固定电话
			
			custCustomer.setMonthlyIncome(StringUtils.valueOf(rs.getObject("monthly_income")));//白领月收入
			custCustomer.setJobNature(StringUtils.valueOf(rs.getObject("job_nature")));//白领任职部门
			custCustomer.setJobDuties(StringUtils.valueOf(rs.getObject("job_duties")));//白领职务
			custCustomer.setMaritalStatus(StringUtils.valueOf(rs.getObject("marital_Status")));//婚姻状况
			custCustomer.setSpouseName(StringUtils.valueOf(rs.getObject("spouse_Name")));//配偶姓名
			custCustomer.setSpouseMobile(StringUtils.valueOf(rs.getObject("spouse_Mobile")));//配偶手机号码
			custCustomer.setUnitProperties(StringUtils.valueOf(rs.getObject("unit_Properties")));//单位性质
			custCustomer.setHousingConditions(StringUtils.valueOf(rs.getObject("housing_Conditions")));//住房状况
			custCustomer.setJobNumber(StringUtils.valueOf(rs.getObject("job_Number")));//工号
			custCustomer.setSectors(StringUtils.valueOf(rs.getObject("sectors")));//行业类别
			
			custCustomer.setPermanentAddressProvince(StringUtils.valueOf(rs.getObject("permanentAddress_Province")));//户籍地址--省
			custCustomer.setPermanentAddressCity(StringUtils.valueOf(rs.getObject("permanentAddress_City")));//户籍地址--市
			custCustomer.setPermanentAddressArea(StringUtils.valueOf(rs.getObject("permanentAddress_Area")));//户籍地址--区
			custCustomer.setPermanentAddressRaod(StringUtils.valueOf(rs.getObject("permanentAddress_Raod")));//户籍地址--详细地址
			custCustomer.setMonthlyConsumption(StringUtils.valueOf(rs.getObject("monthlyConsumption")));//月消费
			custCustomer.setMonthRent(StringUtils.valueOf(rs.getObject("monthRent")));//房租月供
			custCustomer.setLoanCount(StringUtils.valueOf(rs.getObject("loanCount")));//贷款数量
			custCustomer.setLoanMonthRent(StringUtils.valueOf(rs.getObject("loanMonthRent")));//贷款月供
			custCustomer.setCreditCardCount(StringUtils.valueOf(rs.getObject("creditCardCount")));//信用卡数量
			custCustomer.setCreditTotalAmount(StringUtils.valueOf(rs.getObject("creditTotalAmount")));// 贷款总额
			custCustomer.setCreditHigthAmount(StringUtils.valueOf(rs.getObject("creditHigthAmount")));//信用卡最高额度
			custCustomer.setEntryTime(StringUtils.valueOf(rs.getObject("entry_time")));//入职时间 
			custCustomer.setWorkAreaProvince(StringUtils.valueOf(rs.getObject("work_Area_Province")));//单位---省
			custCustomer.setWorkAreaArea(StringUtils.valueOf(rs.getObject("work_Area_Area")));//单位---区
			custCustomer.setWorkAreaRoad(StringUtils.valueOf(rs.getObject("work_Area_Road")));//单位地址 
			
			custCustomer.setUserIdcardFrontUrl(StringUtils.valueOf(rs.getObject("USER_IDCARD_FRONT")));
			custCustomer.setUserIdcardFrontUrlThum(StringUtils.valueOf(rs.getObject("USER_IDCARD_FRONT_THUM")));
			custCustomer.setIdcardFrontUrl(StringUtils.valueOf(rs.getObject("IDCARD_FRONT")));
			custCustomer.setIdcardFrontUrlThum(StringUtils.valueOf(rs.getObject("IDCARD_FRONT_THUM")));
			custCustomer.setIdcardVersoUrl(StringUtils.valueOf(rs.getObject("IDCARD_VERSO")));
			custCustomer.setIdcardVersoUrlThum(StringUtils.valueOf(rs.getObject("IDCARD_VERSO_THUM")));
		
			custCustomer.setStuCardFront(StringUtils.valueOf(rs.getObject("STU_CARD_FRONT")));
			custCustomer.setStuCardFrontThum(StringUtils.valueOf(rs.getObject("STU_CARD_FRONT_THUM")));
			
			custCustomer.setStuPhotoInfo(StringUtils.valueOf(rs.getObject("STU_PHOTO_INFO")));
			custCustomer.setStuPhotoInfoThum(StringUtils.valueOf(rs.getObject("STU_PHOTO_INFO_THUM")));
			
			custCustomer.setStuRegistInfo(StringUtils.valueOf(rs.getObject("STU_REGIST_INFO")));
			custCustomer.setStuRegistInfoThum(StringUtils.valueOf(rs.getObject("STU_REGIST_INFO_THUM")));
			
			custCustomer.setSchoolCardFront(StringUtils.valueOf(rs.getObject("SCHOOL_CARD_FRONT")));
			custCustomer.setSchoolCardFrontThum(StringUtils.valueOf(rs.getObject("SCHOOL_CARD_FRONT_THUM")));
			
			custCustomer.setSchoolCardVerso(StringUtils.valueOf(rs.getObject("SCHOOL_CARD_FRONT")));
			custCustomer.setSchoolCardVersoThum(StringUtils.valueOf(rs.getObject("SCHOOL_CARD_FRONT_THUM")));
			
			custCustomer.setStuType(StringUtils.valueOf(rs.getObject("STU_TYPE")));
			custCustomer.setQqNumber(StringUtils.valueOf(rs.getObject("qq_number")));
			custCustomer.setCustFrom(StringUtils.valueOf(rs.getObject("CUST_FROM")));
			
			/* 蓝领业务新增 */
			custCustomer.setLanUserFlag(StringUtils.valueOf(rs.getObject("lan_user_flag")));
			custCustomer.setInveteCode(StringUtils.valueOf(rs.getObject("INVITE_CODE")));
			custCustomer.setStoreCode(StringUtils.valueOf(rs.getObject("STORE_CODE")));
			custCustomer.setUserScenepicUrl(StringUtils.valueOf(rs.getObject("USER_SCENEPIC_URL")));
			custCustomer.setUserScenepicThumUrl(StringUtils.valueOf(rs.getObject("USER_SCENEPIC_THUM")));
			
			custCustomer.setNation(StringUtils.valueOf(rs.getObject("NATION")));
			custCustomer.setIdCardValStartDate(StringUtils.valueOf(rs.getObject("IDCARD_VALIDITY_STARTDATE")));
			custCustomer.setIdCardValEndDate(StringUtils.valueOf(rs.getObject("IDCARD_VALIDITY_ENDDATE")));
			custCustomer.setIdCardValidity(StringUtils.valueOf(rs.getObject("IDCARD_VALIDITY")));
			custCustomer.setLoanUse(StringUtils.valueOf(rs.getObject("borrow_money_use")));
			custCustomer.setCompanyScale(StringUtils.valueOf(rs.getObject("COMPANY_SCALE")));
			custCustomer.setCompanyWorkYear(StringUtils.valueOf(rs.getObject("COMPANY_WORK_YEAR")));
			custCustomer.setFulltimeDriver(StringUtils.valueOf(rs.getObject("fulltimeDriver")));
			custCustomer.setIdCardFrom(StringUtils.valueOf(rs.getObject("IDCARD_FROM")));
		}else{
			custCustomer=null;
			
		}
		return custCustomer;
	}

	/**
	 * 根据username查询客户信息
	 * 
	 * @param userName
	 * @return
	 */
	public CustCustomer queryCustCustomer(String userName) {

		List<String> selects = new ArrayList<String>();
		selects.add("id");
		selects.add("school");
		selects.add("SCHOOL_ID");
		selects.add("username");
		selects.add("name");
		selects.add("identity_no");
		selects.add("mobile");
		selects.add("degree");
		selects.add("customertype");
		selects.add("unitname");
		selects.add("email_adress");
		
		selects.add("work_area_city");
		selects.add("other_adress_city");
		selects.add("now_education");
		selects.add("userclass");
		selects.add("workExperience");
		selects.add("fixed_tel");
		selects.add("fixed_tel_area");
		selects.add("valid");
		
		selects.add("immediate_Name");
		selects.add("immediate_Relation");
		selects.add("immediate_Mobile");
		selects.add("immediate_Job");
		selects.add("immediate_Adress");
		
		selects.add("emergency_Name");
		selects.add("emergency_Relation");
		selects.add("emergency_Mobile");
		selects.add("emergency_Job");
		selects.add("emergency_Adress");
		
		selects.add("fullTime_flag");
		
		selects.add("fimily_Area_Province");
		selects.add("fimily_Area_City");
		selects.add("fimily_Area_Area");
		selects.add("fimily_Area_Road");
		
		selects.add("other_Adress_Province");
		selects.add("Other_Adress_Area");
		selects.add("other_Accommodation_Address");
		
		selects.add("student_id");
		selects.add("educational");
		
		selects.add("admission_Time");
		
		selects.add("faculties");
		
		selects.add("school_Area_Province");
		selects.add("school_Area_City");
		selects.add("school_Area_Area");
		selects.add("school_Area_Road");
		selects.add("specialty");
		
		selects.add("end_school_time");
		selects.add("sectors");
		
		selects.add("graduated_School");
		selects.add("unit_tel_area");
		selects.add("unit_tel");
		selects.add("monthly_income");
		selects.add("job_nature");
		selects.add("job_duties");
		selects.add("marital_Status");
		selects.add("spouse_Name");
		selects.add("spouse_Mobile");
		selects.add("unit_Properties");
		selects.add("housing_Conditions");
		selects.add("job_Number");
		
		selects.add("permanentAddress_Province");
		selects.add("permanentAddress_City");
		selects.add("permanentAddress_Area");
		selects.add("permanentAddress_Raod");
		selects.add("monthlyConsumption");
		selects.add("monthRent");
		selects.add("loanCount");
		selects.add("loanTotalAmount");
		selects.add("loanMonthRent");
		selects.add("creditCardCount");
		selects.add("creditTotalAmount");
		selects.add("creditHigthAmount");
		selects.add("entry_time");
		
		selects.add("work_Area_Province");
		selects.add("work_Area_Area");
		selects.add("work_Area_Road");
		
		selects.add("USER_IDCARD_FRONT");
		selects.add("USER_IDCARD_FRONT_THUM");
		selects.add("IDCARD_FRONT");
		selects.add("IDCARD_FRONT_THUM");
		selects.add("IDCARD_VERSO");
		selects.add("IDCARD_VERSO_THUM");
		
		selects.add("STU_CARD_FRONT");
		selects.add("STU_CARD_FRONT_THUM");
		
		selects.add("STU_PHOTO_INFO");
		selects.add("STU_PHOTO_INFO_THUM");
		
		selects.add("STU_REGIST_INFO");
		selects.add("STU_REGIST_INFO_THUM");
		
		selects.add("SCHOOL_CARD_FRONT");
		selects.add("SCHOOL_CARD_FRONT_THUM");
		
		selects.add("SCHOOL_CARD_VERSO");
		selects.add("SCHOOL_CARD_VERSO_THUM");
		
		
		selects.add("STU_TYPE");
		selects.add("qq_number");
		
		selects.add("CUST_FROM");
		
		/* 蓝领业务新增 */
		selects.add("lan_user_flag");
		selects.add("INVITE_CODE");
		selects.add("STORE_CODE");
		selects.add("USER_SCENEPIC_URL");
		selects.add("USER_SCENEPIC_THUM");
		
		selects.add("NATION");
		selects.add("IDCARD_VALIDITY_STARTDATE");
		selects.add("IDCARD_VALIDITY_ENDDATE");
		selects.add("IDCARD_VALIDITY");
		selects.add("borrow_money_use");
		selects.add("COMPANY_SCALE");
		selects.add("COMPANY_WORK_YEAR");
		selects.add("fulltimeDriver");//是否全职司机（0：其他，1：兼职，2全职）
		selects.add("IDCARD_FROM");
		
		Map<String, Object> conMap = new HashMap<String, Object>();
		conMap.put("username", userName);
		
		String custSql = MapAssemForSql.getSelectSql(selects, TableConsts.CUST_CUSTOMER, conMap);
		RecordUtils.writeAction(logger, userName, custSql);
		return ConnManager.singleQuery(custSql, this);

	}
	
	public static void main(String[] args) {
		System.out.println(new CustcustomerQuery().queryCustCustomer("caryliu").getCustFrom());
	}
	
	

}
