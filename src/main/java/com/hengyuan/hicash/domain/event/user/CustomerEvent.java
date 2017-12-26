package com.hengyuan.hicash.domain.event.user;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.exception.SaveCustomerException;
import com.hengyuan.hicash.exception.SaveRegisInfoException;
import com.hengyuan.hicash.exception.UpdateCustomerException;
import com.hengyuan.hicash.parameters.request.user.RegisterByllReq;
import com.hengyuan.hicash.parameters.request.user.RegisterReq;
import com.hengyuan.hicash.parameters.request.user.SaveInviteCodeAndNameReq;
import com.hengyuan.hicash.parameters.request.user.UpdateLanUserInfoReq;
import com.hengyuan.hicash.utils.DateUtils;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;


/**
 * 用户信息操作类
 * @author Cary.Liu
 * @createDate 2015-04-21
 */
public class CustomerEvent {
	
	private static Logger logger = Logger.getLogger(CustomerEvent.class);

	/**
	 * 用户注册保存客户信息
	 * @param registerReq
	 * @throws SaveCustomerException
	 * @author Cary.Liu
	 * @create 2014-08-01
	 */
	public void createCustomer(RegisterReq registerReq) 
			throws SaveCustomerException {

		Map<String, Object> setMap = new HashMap<String, Object>();
		
		setMap.put("USERNAME", registerReq.getMobileNo());
		setMap.put("MOBILE", registerReq.getMobileNo());
		setMap.put("CREATE_TIME", DateUtils.getDateStr(new Date()));
		setMap.put("IDENTITY_NO", registerReq.getIdCard());
		setMap.put("NAME", registerReq.getRealName());
		setMap.put("customerType", registerReq.getCustType());
		setMap.put("registerStap", Consts.NOT_BASIC);
		setMap.put("other_Adress_City", registerReq.getLiveCity());
		
		/* 2016-02-23新增 */
		setMap.put("REGISTER_FROM", StringUtils.getString(registerReq.getRegisterFrom())); // 设备来源
		setMap.put("CUST_FROM", StringUtils.getString(registerReq.getCustFrom())); // 注册来源
		setMap.put("ACTIVE_FROM", StringUtils.getString(registerReq.getActiveFrom())); // 活动来源
		
		String createSql = MapAssemForSql.getInsertSql(TableConsts.CUST_CUSTOMER, setMap);

		RecordUtils.writeAction(logger, registerReq.getUuid(), createSql);
		if (ConnManager.executeUpdate(createSql) <= 0) {
			throw new SaveCustomerException();
		}
	}
	
	/**
	 * 用户注册保存客户信息-蓝领
	 * @param registerReq
	 * @throws SaveCustomerException
	 * @author Cary.Liu
	 * @create 2014-08-01
	 */
	public void createCustomerByll(RegisterByllReq registerReq) throws SaveCustomerException {

		Map<String, Object> setMap = new HashMap<String, Object>();
		
		setMap.put("USERNAME", registerReq.getMobileNo());
		setMap.put("MOBILE", registerReq.getMobileNo());
		setMap.put("CREATE_TIME", DateUtils.getDateStr(new Date()));
		setMap.put("IDENTITY_NO", registerReq.getIdCard());
		setMap.put("NAME", registerReq.getRealName());
		setMap.put("customerType", registerReq.getCustType());
		setMap.put("registerStap", Consts.NOT_BASIC);
		setMap.put("lan_user_flag", Consts.FINAL_NUMBER_1); // 是否是蓝领用户
		setMap.put("INVITE_CODE", registerReq.getInviteCode());
		setMap.put("STORE_CODE", registerReq.getStoreCode());
		setMap.put("USER_SCENEPIC_URL", registerReq.getUserScenepicUrl());
		setMap.put("USER_SCENEPIC_THUM", registerReq.getUserScenepicThumUrl());
//		setMap.put("other_Adress_City", registerReq.getLiveCity());
		
		String createSql = MapAssemForSql.getInsertSql(TableConsts.CUST_CUSTOMER, setMap);

		RecordUtils.writeAction(logger, registerReq.getUuid(), createSql);
		if (ConnManager.executeUpdate(createSql) <= 0) {
			throw new SaveCustomerException();
		}
	}
	
	/**
	 * 更新蓝领用户资料
	 * @param userInfo
	 * @throws UpdateCustomerException 
	 */
	public void updateLanUserInfo(UpdateLanUserInfoReq userInfo) throws UpdateCustomerException{
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		
		setMap.put("lan_user_flag", Consts.FINAL_NUMBER_1); // 是否是蓝领用户
		setMap.put("INVITE_CODE", userInfo.getInviteCode());
		setMap.put("STORE_CODE", userInfo.getStoreCode());
		if(!StringUtils.isEmpty(userInfo.getUserScenepicUrl())){
			setMap.put("USER_SCENEPIC_URL", userInfo.getUserScenepicUrl());
		}
		if(!StringUtils.isEmpty(userInfo.getUserScenepicThumUrl())){
			setMap.put("USER_SCENEPIC_THUM", userInfo.getUserScenepicThumUrl());
		}
//		if(Consts.FINAL_NUMBER_0.equals(userInfo.getIsHaveStore())){
//			setMap.put("LANUSER_UNITPIC_URL", userInfo.getUnitPicUrl());
//		}
		
		
		Map<String, Object> wheremMap = new HashMap<String, Object>();
		
		wheremMap.put("USERNAME", userInfo.getUserName());
		
		String sql = MapAssemForSql.getUpdateSql(TableConsts.CUST_CUSTOMER, setMap, wheremMap);
		if(ConnManager.executeUpdate(sql) <= 0){
			throw new UpdateCustomerException();
		}

		
	}
	
	/**
	 * 更新蓝领用户资料
	 * @param userInfo
	 * @throws UpdateCustomerException 
	 * @throws SaveCustomerException 
	 */
	public void updateInviteCodeAndName(SaveInviteCodeAndNameReq userInfo) throws UpdateCustomerException, SaveCustomerException{
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		setMap.put("INVITE_CODE", userInfo.getInviteCode());
				
		Map<String, Object> wheremMap = new HashMap<String, Object>();
		
		wheremMap.put("USERNAME", userInfo.getUserName());
		
		String sql = MapAssemForSql.getUpdateSql(TableConsts.CUST_CUSTOMER, setMap, wheremMap);
		if(ConnManager.executeUpdate(sql) <= 0){
			throw new UpdateCustomerException();
		}
		
	}
	
	/**
	 * 注册信息资料录入
	 * @param registerReq
	 * @return
	 * @throws SaveRegisInfoException 
	 */
	public void updateRegistInfo(RegisterReq registerReq) throws SaveRegisInfoException{
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		
		setMap.put("IDENTITY_NO", registerReq.getIdCard());
		setMap.put("NAME", registerReq.getRealName());
		setMap.put("customerType", registerReq.getCustType());
		setMap.put("registerStap", Consts.NOT_BASIC);
		setMap.put("MOBILE", registerReq.getMobileNo());
		setMap.put("other_Adress_City", registerReq.getLiveCity());
		
		Map<String, Object> wheremMap = new HashMap<String, Object>();
		
		wheremMap.put("USERNAME", registerReq.getMobileNo());
		
		String sql = MapAssemForSql.getUpdateSql(TableConsts.CUST_CUSTOMER, setMap, wheremMap);
		if(ConnManager.executeUpdate(sql) <= 0){
			throw new SaveRegisInfoException();
		}

	}
	
	/**
	 * 更新客户信息表
	 * @return
	 * @throws UpdateCustomerException 
	 */
	public void updateCustomerInfo(Map<String, Object> setMap, Map<String, Object> whereMap) throws UpdateCustomerException{
		
		String sql = MapAssemForSql.getUpdateSql(TableConsts.CUST_CUSTOMER, setMap, whereMap);
		if(ConnManager.executeUpdate(sql) <= 0){
			throw new UpdateCustomerException();
		}

	}
	
	/**
	 * 用户注册保存客户信息
	 * @param registerReq
	 * @throws SaveCustomerException
	 * @author Cary.Liu
	 * @create 2014-08-01
	 */
	public void createCustomerNew(RegisterReq registerReq) 
			throws SaveCustomerException {

		Map<String, Object> setMap = new HashMap<String, Object>();
		
		setMap.put("USERNAME", registerReq.getMobileNo());
		setMap.put("MOBILE", registerReq.getMobileNo());
		setMap.put("CREATE_TIME", DateUtils.getDateStr(new Date()));
		setMap.put("customerType", registerReq.getCustType());
		setMap.put("registerStap", Consts.NOT_BASIC);
		setMap.put("other_Adress_City", registerReq.getLiveCity());
		
		/* 2016-02-23新增 */
		setMap.put("REGISTER_FROM", StringUtils.getString(registerReq.getRegisterFrom())); // 设备来源
		setMap.put("CUST_FROM", StringUtils.getString(registerReq.getCustFrom())); // 注册来源
		setMap.put("ACTIVE_FROM", StringUtils.getString(registerReq.getActiveFrom())); // 活动来源
		
		String createSql = MapAssemForSql.getInsertSql(TableConsts.CUST_CUSTOMER, setMap);

		RecordUtils.writeAction(logger, registerReq.getUuid(), createSql);
		if (ConnManager.executeUpdate(createSql) <= 0) {
			throw new SaveCustomerException();
		}
	}
	

}
