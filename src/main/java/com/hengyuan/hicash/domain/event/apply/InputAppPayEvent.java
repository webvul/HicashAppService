package com.hengyuan.hicash.domain.event.apply;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.app.InputAppPay;
import com.hengyuan.hicash.entity.user.CustCustomer;
import com.hengyuan.hicash.exception.CreateInputPayException;
import com.hengyuan.hicash.exception.UpdateInputAppException;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 申请资料记录操作类
 * @author Cary.Liu
 * @create date 2014-08-07
 * @update date 2015-01-08 Himars改版V2.2
 * 
 */
public class InputAppPayEvent {
	
	private static Logger logger = Logger.getLogger(InputAppPayEvent.class);
	
	/**
	 * 初始化申请件信息
	 * @param inputPay
	 * @throws CreateInputPayException 
	 * @author Andy.Niu
	 * @create 2014-08-07
	 */
	public void createInputAppPay(Map<String,Object> inputPay) 
			throws CreateInputPayException{
		
		String createSql = MapAssemForSql.getInsertSql(TableConsts.INPUT_APP, inputPay);
		RecordUtils.writeAction(logger, null, createSql);
		if(ConnManager.executeUpdate(createSql) <= 0){
			throw new CreateInputPayException();
		}
		
	}
	
	/**
	 * 初始化申请件信息
	 * @param inputPay
	 * @param appNo
	 * @throws UpdateInputAppException 
	 * @author Andy.Niu
	 * @create 2014-10-15
	 */
	public void updateInputAppPay(Map<String,Object> inputPay,String appNo) 
			throws UpdateInputAppException{
		
		Map<String, Object> condit = new HashMap<String, Object>();
		condit.put("applicationNo", appNo);
		
		String updateSql = MapAssemForSql.getUpdateSql(TableConsts.INPUT_APP, inputPay,condit);
		RecordUtils.writeAction(logger, null, updateSql);
		if(ConnManager.executeUpdate(updateSql) <= 0){
			throw new UpdateInputAppException();
		}
		
	}
	
	/**
	 * 得到修改参数
	 * @param custCustomer
	 * @param appPayNo
	 * @return
	 */
	public InputAppPay getInputAppPay(CustCustomer custCustomer, String appPayNo) {
		InputAppPay inputAppPay = new InputAppPay();

		inputAppPay.setApplicationNo(appPayNo);

		inputAppPay.setRealName(custCustomer.getRealName());// 姓名

		inputAppPay.setIdentityNo(custCustomer.getIdentityNo());// 身份证

		inputAppPay.setNowFixedTel(custCustomer.getFixedTel());// 固定电话

		inputAppPay.setNowFixedTelArea(custCustomer.getFixedTelArea());// 固定电话区号

		inputAppPay.setValid(custCustomer.getValid());// 身份证有效期

		inputAppPay.setEmailAddress(custCustomer.getEmailAdress());// 电子邮箱

		inputAppPay.setMobileNo(custCustomer.getMobileNo());// 手机号码

		/***** 直系亲属信息 ******/

		inputAppPay.setFatherName(custCustomer.getImmediateName());// 亲属姓名

		inputAppPay.setFamilyRelation(custCustomer.getImmediateRelation());// 亲属关系

		inputAppPay.setFatherMobile(custCustomer.getImmediateMobile());// 亲属手机

		inputAppPay.setWorkUnits(custCustomer.getImmediateJob());// 亲属单位

		inputAppPay.setWorkUnitsAddress(custCustomer.getImmediateAdress());// 亲属单位地址

		/***** 紧急联系人信息 ******/

		inputAppPay.setRelaName(custCustomer.getEmergencyName());// 紧急联系人姓名

		inputAppPay.setRelation(custCustomer.getEmergencyRelation());// 紧急联系人关系

		inputAppPay.setRelaMobile(custCustomer.getEmergencyMobile());// 紧急联系人手机

		inputAppPay.setRelaWorkUnits(custCustomer.getEmergencyJob());// 紧急联系人单位

		inputAppPay.setRelaWorkUnitsAddress(custCustomer.getEmergencyAdress());// 紧急联系人单位地址

		inputAppPay.setFullTimeFlag(custCustomer.getFullTimeFlag());// 是否全日制

		inputAppPay.setUserName(custCustomer.getUserName());// 用户名userGreade



		inputAppPay.setCreateUser(custCustomer.getUserName());// 创建人

		/** 家庭地址 */
		inputAppPay.setFamProv(custCustomer.getFimilyAreaProvince());// 家庭--省

		inputAppPay.setFamCity(custCustomer.getFimilyAreaCity());// 家庭--市

		inputAppPay.setFamArea(custCustomer.getFimilyAreaArea());// 家庭--区

		inputAppPay.setDormAddress(custCustomer.getFimilyAreaRoad());// 家庭地址

		/** 现居地址 */

		inputAppPay.setNowProv(custCustomer.getOtherAdressProvince());// 现居--省

		inputAppPay.setNowCity(custCustomer.getOtherAdressCity());// 现居--市

		inputAppPay.setNowArea(custCustomer.getOtherAdressArea());// 现居--区

		inputAppPay.setNowAddress(custCustomer.getOtherAccommodationAddress());// 现居地址

		if ("KHL1".equals(custCustomer.getCustType())) {

			inputAppPay.setSchool(custCustomer.getSchool());// 学校

			inputAppPay.setStudentId(custCustomer.getStudentId());// 学号

			inputAppPay.setEducational(custCustomer.getEducational());// 在读学历

			if (custCustomer.getAdmissionTime() == null || custCustomer.getAdmissionTime().equals("")) {

			} else {

				inputAppPay.setAdmissionYear(custCustomer.getAdmissionTime()
						.substring(0, 4));// 入学时间-年

				inputAppPay.setAdmissionDay(custCustomer.getAdmissionTime()
						.substring(5, 7));// 入学时间-月

			}

			inputAppPay.setFaculties(custCustomer.getFaculties());// 所在院系

			inputAppPay.setSchoolProv(custCustomer.getSchoolAreaProvince());// 院校--省

			inputAppPay.setSchoolCity(custCustomer.getSchoolAreaCity());// 院校--市

			inputAppPay.setSchoolArea(custCustomer.getSchoolAreaArea());// 院校--区

			inputAppPay.setSchoolAddress(custCustomer.getSchoolAreaRoad());// 院校地址

			inputAppPay.setSpecialty(custCustomer.getSpecialty());// 专业

			inputAppPay.setUserGreade(custCustomer.getUserclass());// 用户年级

		} else {

			inputAppPay.setEducation(custCustomer.getNowEduCation());// 白领学历

			if (custCustomer.getEndSchoolTime() == null || custCustomer.getEndSchoolTime().equals("")) {

			} else {

				inputAppPay.setGraduationYear(custCustomer.getEndSchoolTime()
						.substring(0, 4));// 白领毕业时间--年

				inputAppPay.setGraduationMonth(custCustomer.getEndSchoolTime()
						.substring(5, 7));// 白领毕业时间--月

			}

			inputAppPay.setWorkExperience(custCustomer.getWorkExperience());// 白领工作年限

			inputAppPay.setSectors(custCustomer.getSectors());// 白领行业类别

			inputAppPay.setUnitName(custCustomer.getUnitName());// 白领单位名称

			inputAppPay.setSchool(custCustomer.getGraduatedSchool());// 白领毕业学校

			inputAppPay.setUnitTelArea(custCustomer.getUnitTelArea());// 白领单位固定电话区号

			inputAppPay.setUnitTel(custCustomer.getUnitTel());// 白领单位固定电话

			inputAppPay.setMonthlyIncome(custCustomer.getMonthlyIncome());// 白领月收入

			inputAppPay.setOfficeSector(custCustomer.getJobNature());// 白领任职部门

			inputAppPay.setJobDuties(custCustomer.getJobDuties());// 白领职务

			inputAppPay.setMaritalStatus(custCustomer.getMaritalStatus());// 婚姻状况

			if ("Q001".equals(custCustomer.getMaritalStatus())) {

				inputAppPay.setSpouseRealName(custCustomer.getSpouseName());// 配偶姓名
				if (com.mysql.jdbc.StringUtils.isNullOrEmpty(custCustomer
						.getSpouseMobile())) {
					if (!com.mysql.jdbc.StringUtils.isNullOrEmpty(custCustomer
							.getSpouseMobile())) {
						inputAppPay.setSpouseMobileNo(Long
								.parseLong(custCustomer.getSpouseMobile()));// 配偶手机号码
					}

				}

			}

			inputAppPay.setUnitIndustry(custCustomer.getUnitProperties());// 单位性质

			inputAppPay.setHousingCondition(custCustomer.getHousingConditions());// 住房状况

			inputAppPay.setEmployeeNumber(custCustomer.getJobNumber());// 工号

			inputAppPay.setCensusProvince(custCustomer.getPermanentAddressProvince());// 户籍地址--省

			inputAppPay.setCensusCity(custCustomer.getPermanentAddressCity());// 户籍地址--市

			inputAppPay.setCensusArea(custCustomer.getPermanentAddressArea());// 户籍地址--区

			inputAppPay.setCensusAddress(custCustomer.getPermanentAddressRaod());// 户籍地址--详细地址

			inputAppPay.setMonthlyConsumption(custCustomer.getMonthlyConsumption());// 月消费

			inputAppPay.setMonthRent(custCustomer.getMonthRent());// 房租月供

			inputAppPay.setLoanCount(custCustomer.getLoanCount());// 贷款数量

			inputAppPay.setLoanTotalAmount(custCustomer.getLoanTotalAmount());// 贷款总额

			inputAppPay.setLoanMonthRent(custCustomer.getLoanMonthRent());// 贷款月供

			inputAppPay.setCreditCardCount(custCustomer.getCreditCardCount());// 信用卡数量

			inputAppPay.setCreditTotalAmount(custCustomer
					.getCreditTotalAmount());// 信用卡总额度

			inputAppPay.setCreditHigthAmount(custCustomer
					.getCreditHigthAmount());// 信用卡最高额度

			if (custCustomer.getEntryTime() == null  || custCustomer.getEntryTime().equals("")) {

			} else {

				inputAppPay.setEntryTimeYear(custCustomer.getEntryTime()
						.substring(0, 4));// 白领入职时间--年

				inputAppPay.setEntryTimeMonth(custCustomer.getEntryTime()
						.substring(5, 7));// 白领入职时间--月

			}

			inputAppPay.setWorkUnitProv(custCustomer.getWorkAreaProvince());// 单位---省

			inputAppPay.setWorkUnitCity(custCustomer.getWorkAreaCity());// 单位---市

			inputAppPay.setWorkUnitArea(custCustomer.getWorkAreaArea());// 单位---市

			inputAppPay.setUnitAddress(custCustomer.getWorkAreaRoad());// 单位地址

		}
		// 根据身份证信息计算性别
		inputAppPay.setGender(StringUtils.getGenderByIdCard(custCustomer
				.getIdentityNo()));// 性别

		/* 出生日期 */
		inputAppPay.setBirthday(inputAppPay.getIdentityNo().substring(6, 10)
				+ "-" + inputAppPay.getIdentityNo().substring(10, 12) + "-"
				+ inputAppPay.getIdentityNo().substring(12, 14));

		return inputAppPay;
	}
	
	/**
	 * 更新申请件creditName
	 * @param inputPay
	 * @throws UpdateInputAppException
	 */
	public void updateInputAppPayCreditName(InputAppPay inputPay)throws UpdateInputAppException {
		String sql="UPDATE d_input_app set creditName='"+inputPay.getCreditName()+"' where identityNo='"+inputPay.getIdentityNo()+"'";
		
		RecordUtils.writeAction(logger, null, sql);
		if (ConnManager.executeUpdate(sql) <= 0) {
			throw new UpdateInputAppException();
		}
		
	}
	
	/**
	 * 更新申请件creditName
	 * @param inputPay
	 * @throws UpdateInputAppException
	 */
	public void updateInputAppCreditName(InputAppPay inputPay) {
		String sql="UPDATE d_input_app SET creditName='"+inputPay.getCreditName()+"' WHERE applicationno='"+inputPay.getApplicationNo()+"'";
		
		RecordUtils.writeAction(logger, null, sql);
		ConnManager.executeUpdate(sql);
	}
	
	/**
	 * 更改额度/期数
	 * @param appPay
	 * @throws UpdateInputAppException 
	 */
	public void updateInpuAppByChangeAmount(InputAppPay inputApp) throws UpdateInputAppException {
		
		Map<String, Object> updateCol = new HashMap<String, Object>();
		updateCol.put("creditName", inputApp.getCreditName());
	
		Map<String, Object> whereCol = new HashMap<String, Object>();
		whereCol.put("applicationNo", inputApp.getApplicationNo());
		
		String updateSql =  MapAssemForSql.getUpdateSql(TableConsts.INPUT_APP, updateCol,whereCol);
		RecordUtils.writeAction(logger, null, updateSql);
		
		if(ConnManager.executeUpdate(updateSql) < 1){
			throw new UpdateInputAppException();
		}
	}
	/**
	 * 更新申请件代扣银行卡
	 * @param inputPay
	 * @throws UpdateInputAppException
	 */
	public void updateInputAppCard(String appNo,String bankCard,String openCard)throws UpdateInputAppException {
		String sql="UPDATE d_input_app set PROXY_BANKCARD='"+bankCard+"',PROXY_OPENBANK='"+openCard+"' where applicationNo='"+appNo+"'";
		
		RecordUtils.writeAction(logger, null, sql);
		if (ConnManager.executeUpdate(sql) <= 0) {
			throw new UpdateInputAppException();
		}
		
	}
	
}
