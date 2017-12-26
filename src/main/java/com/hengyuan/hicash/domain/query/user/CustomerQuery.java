package com.hengyuan.hicash.domain.query.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.user.CustomerEntity;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.SqlAssemblyUtils;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 查询用户信息
 * 
 * @author Cary.Liu
 * @create date 2014-07-26
 */
public class CustomerQuery extends AbstractDAO<CustomerEntity> {
	
	private static Logger logger = Logger.getLogger(CustomerQuery.class);

	private static final String USER_REALNAME_QUERY = "SELECT * FROM cust_customer WHERE 1 = 1 AND  platform_Mark='1' ";

	@Override
	public CustomerEntity mapping(ResultSet rs) throws SQLException {

		CustomerEntity customerEntity = null;
		if (rs != null) {
			customerEntity = new CustomerEntity();
			customerEntity.setUserName(StringUtils.valueOf(rs.getObject("USERNAME")));
			customerEntity.setCustType(StringUtils.valueOf(rs.getObject("customerType")));
			customerEntity.setMobile(StringUtils.valueOf(rs.getObject("MOBILE")));
			customerEntity.setRealName(StringUtils.valueOf(rs.getObject("NAME")));
			customerEntity.setIdentityNo(StringUtils.valueOf(rs.getObject("IDENTITY_NO")));
			customerEntity.setEducation(StringUtils.valueOf(rs.getObject("now_education")));
			
			customerEntity.setNowProvince(StringUtils.valueOf(rs.getObject("other_Adress_Province")));
			customerEntity.setNowCity(StringUtils.valueOf(rs.getObject("other_Adress_City")));
			customerEntity.setNowArea(StringUtils.valueOf(rs.getObject("Other_Adress_Area")));
			customerEntity.setNowAddress(StringUtils.valueOf(rs.getObject("other_Accommodation_Address")));
		
			customerEntity.setIdcardFrontUrl(StringUtils.valueOf(rs.getObject("IDCARD_FRONT")));
			customerEntity.setUserIdcardFrontUrl(StringUtils.valueOf(rs.getObject("USER_IDCARD_FRONT")));
			customerEntity.setIdcardVersoUrl(StringUtils.valueOf(rs.getObject("IDCARD_VERSO")));
			customerEntity.setSchoolCardFrontUrl(StringUtils.valueOf(rs
					.getObject("SCHOOL_CARD_FRONT")));
			customerEntity.setSchoolCardVersoUrl(StringUtils.valueOf(rs
					.getObject("SCHOOL_CARD_VERSO")));
			customerEntity.setStuCardFrontUrl(StringUtils.valueOf(rs
					.getObject("STU_CARD_FRONT")));
			customerEntity.setStuPhotoInfoUrl(StringUtils.valueOf(rs
					.getObject("STU_PHOTO_INFO")));
			customerEntity.setStuRegistInfoUrl(StringUtils.valueOf(rs
					.getObject("STU_REGIST_INFO")));
			/* 小图url */
			customerEntity.setIdcardFrontUrlThum(StringUtils.valueOf(rs.getObject("IDCARD_FRONT_THUM")));
			customerEntity.setUserIdcardFrontUrlThum(StringUtils.valueOf(rs.getObject("USER_IDCARD_FRONT_THUM")));
			customerEntity.setIdcardVersoUrlThum(StringUtils.valueOf(rs.getObject("IDCARD_VERSO_THUM")));
			customerEntity.setSchoolCardFrontUrlThum(StringUtils.valueOf(rs
					.getObject("SCHOOL_CARD_FRONT_THUM")));
			customerEntity.setSchoolCardVersoUrlThum(StringUtils.valueOf(rs
					.getObject("SCHOOL_CARD_VERSO_THUM")));
			customerEntity.setStuCardFrontUrlThum(StringUtils.valueOf(rs
					.getObject("STU_CARD_FRONT_THUM")));
			customerEntity.setStuPhotoInfoUrlThum(StringUtils.valueOf(rs
					.getObject("STU_PHOTO_INFO_THUM")));
			customerEntity.setStuRegistInfoUrlThum(StringUtils.valueOf(rs
					.getObject("STU_REGIST_INFO_THUM")));
			
			customerEntity.setExpressProvince(StringUtils.valueOf(rs.getObject("EXPRESS_PROVINCE")));
			customerEntity.setExpressCity(StringUtils.valueOf(rs.getObject("EXPRESS_CITY")));
			customerEntity.setExpressArea(StringUtils.valueOf(rs.getObject("EXPRESS_AREA")));
			customerEntity.setExpressDetail(StringUtils.valueOf(rs.getObject("EXPRESS_DETAIL")));
			
			/* 蓝领业务新增 */
			customerEntity.setLanUserFlag(StringUtils.valueOf(rs.getObject("lan_user_flag")));
			customerEntity.setInveteCode(StringUtils.valueOf(rs.getObject("INVITE_CODE")));
			customerEntity.setStoreCode(StringUtils.valueOf(rs.getObject("STORE_CODE")));
			customerEntity.setUserScenepicUrl(StringUtils.valueOf(rs.getObject("USER_SCENEPIC_URL")));
			customerEntity.setUserScenepicThumUrl(StringUtils.valueOf(rs.getObject("USER_SCENEPIC_THUM")));
			customerEntity.setRegLotteryFlag(StringUtils.valueOf(rs.getObject("REGISTER_LOTTERY_FLAG")));
			customerEntity.setRegCashRedPac(StringUtils.valueOf(rs.getObject("REGISTER_CASH_REDPAC")));
			customerEntity.setEmailAdress(StringUtils.valueOf(rs.getString("email_adress")));
			customerEntity.setLoanPurpose(StringUtils.valueOf(rs.getString("borrow_money_use")));
		} else {
			System.out.println("用户信息查询为空");
		}

		return customerEntity;
	}

	/**
	 * 查询用户的真实姓名
	 * 
	 * @param userName
	 * @return
	 */
	public String queryRealName(String userName) {
		StringBuffer sql = new StringBuffer(USER_REALNAME_QUERY);
		sql.append(SqlAssemblyUtils.receiveEqualSign("username", userName,false));
		CustomerEntity customerEntity = ConnManager.singleQuery(sql.toString(),this);
		
		if (customerEntity != null && customerEntity.getRealName() != null) {
			return customerEntity.getRealName();
		} else {
			return null;
		}
	}
	
	/**
	 * 查询用户的真实姓名
	 * 
	 * @param userName
	 * @return
	 */
	public String queryCustType(String userName) {
		StringBuffer sql = new StringBuffer(USER_REALNAME_QUERY);
		sql.append(SqlAssemblyUtils.receiveEqualSign("username", userName,false));
		
		//记录日志
		RecordUtils.writeAction(logger, userName, sql.toString());
		CustomerEntity customerEntity = ConnManager.singleQuery(sql.toString(),this);
		
		if (customerEntity != null && customerEntity.getCustType() != null) {
			return customerEntity.getCustType();
		} else {
			return null;
		}
	}
	
	/**
	 * 根据用户名查找用户
	 * @param userName
	 * @return
	 */
	public CustomerEntity queryCustomerByUserName(String userName){
		StringBuffer sql = new StringBuffer(USER_REALNAME_QUERY);
		sql.append(SqlAssemblyUtils.receiveEqualSign("username", userName,false));
		
		//记录日志
		RecordUtils.writeAction(logger, userName, sql.toString());
		CustomerEntity customerEntity = ConnManager.singleQuery(sql.toString(),this);
		
		if (customerEntity != null) {
			return customerEntity;
		} else {
			return new CustomerEntity();
		}
	}
	
	/**
	 * 根据用户名查找用户
	 * @param userName
	 * @return
	 */
	public CustomerEntity queryCustByUser(String userName){
		
		StringBuffer sql = new StringBuffer(USER_REALNAME_QUERY);
		
		sql.append(SqlAssemblyUtils.receiveEqualSign("username", userName,false));
		
		RecordUtils.writeAction(logger, userName, sql.toString());

		return ConnManager.singleQuery(sql.toString(),this);
	}
	
	/**
	 * 根据用户名查找用户
	 * @param userName
	 * @return
	 */
	public CustomerEntity queryCustByUserName(String userName){
		StringBuffer sql = new StringBuffer(USER_REALNAME_QUERY);
		sql.append(" AND username = '" + userName + "'" );
		logger.info("用户名为："+userName+"的sql："+sql.toString());
		//记录日志
		RecordUtils.writeAction(logger, userName, sql.toString());
		return ConnManager.singleQuery(sql.toString(),this);
	}
	
	/**
	 * 根据用户名查找用户
	 * @param userName
	 * @return
	 */
	public CustomerEntity queryCustByIdCard(String identityNo){
		
		StringBuffer sql = new StringBuffer(USER_REALNAME_QUERY);
		
		sql.append(" AND IDENTITY_NO = '" + identityNo + "'" );
		
		RecordUtils.writeAction(logger, identityNo, sql.toString());
		return ConnManager.singleQuery(sql.toString(),this);
	}
	
//	public static void main(String[] args) {
//		
//		CustomerQuery query = new CustomerQuery();
//		
//		CustomerEntity entity = query.queryCustomerByUserName("18217015991");
//		System.out.println(entity.getExpressProvince());
//	}

}
