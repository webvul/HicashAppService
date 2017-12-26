package com.hengyuan.hicash.domain.event.user;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.query.param.SingleIntQuery;
import com.hengyuan.hicash.exception.SaveAuthorityException;
import com.hengyuan.hicash.exception.SaveCustUserException;
import com.hengyuan.hicash.exception.UpdateCustUserException;
import com.hengyuan.hicash.parameters.request.user.RegisterByllReq;
import com.hengyuan.hicash.parameters.request.user.RegisterReq;
import com.hengyuan.hicash.utils.DateUtils;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * CUST_USER增删改操作
 * @author Cary.Liu
 * @create 2014-08-01
 */
public class CustUserEvent {

	private static Logger logger = Logger.getLogger(CustUserEvent.class);
	
	/**
	 * 注册新用户
	 * @param registerReq
	 * @throws SaveCustUserException
	 * @throws SaveAuthorityException 
	 * @author Cary.Liu
	 * @create 2014-08-01
	 */
	public void createCustUser(RegisterReq registerReq) 
			throws SaveCustUserException, SaveAuthorityException {
		
		String salt = RandomStringUtils.random(20, true, true);
		Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
		String password = passwordEncoder.encodePassword(registerReq.getPassWord(), salt);

		Map<String, Object> setMap = new HashMap<String, Object>();
		
		setMap.put("USERNAME", registerReq.getMobileNo());
		setMap.put("MOBILENO", registerReq.getMobileNo());
		setMap.put("ENABLED", "1");
		setMap.put("PASSWORD", password);
		setMap.put("SALT", salt);
		setMap.put("LOCKED", "0");
		setMap.put("INFO_COMPLETE_PERCENT", "0");
		setMap.put("CERTY_PERCENT", "0");
		setMap.put("INFO_SCORE", "0");
		setMap.put("CREATE_TIME", DateUtils.getDateStr(new Date()));

		setMap.put("REGISTER_FROM", registerReq.getRegisterFrom());
		setMap.put("CUST_FROM", registerReq.getCustFrom());
		
		String updateSql = MapAssemForSql.getInsertSql(TableConsts.CUST_USER, setMap);
		RecordUtils.writeAction(logger, registerReq.getUuid(), updateSql);
		if (ConnManager.executeUpdate(updateSql) < 0) {
			throw new SaveCustUserException();
		}

		createAuthority(registerReq.getMobileNo());
	}
	
	/**
	 * 注册新用户-蓝领
	 * @param registerReq
	 * @throws SaveCustUserException
	 * @throws SaveAuthorityException 
	 * @author Cary.Liu
	 * @create 2014-08-01
	 */
	public void createCustUserByll(RegisterByllReq registerReq) throws SaveCustUserException, SaveAuthorityException {
		
		String salt = RandomStringUtils.random(20, true, true);
		Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
		String password = passwordEncoder.encodePassword(registerReq.getPassWord(), salt);

		Map<String, Object> setMap = new HashMap<String, Object>();
		
		setMap.put("USERNAME", registerReq.getMobileNo());
		setMap.put("MOBILENO", registerReq.getMobileNo());
		setMap.put("ENABLED", "1");
		setMap.put("PASSWORD", password);
		setMap.put("SALT", salt);
		setMap.put("LOCKED", "0");
		setMap.put("INFO_COMPLETE_PERCENT", "0");
		setMap.put("CERTY_PERCENT", "0");
		setMap.put("INFO_SCORE", "0");
		setMap.put("CREATE_TIME", DateUtils.getDateStr(new Date()));

		setMap.put("REGISTER_FROM", registerReq.getRegisterFrom());
		setMap.put("CUST_FROM", registerReq.getCustFrom());
		
		String updateSql = MapAssemForSql.getInsertSql(TableConsts.CUST_USER, setMap);
		RecordUtils.writeAction(logger, registerReq.getUuid(), updateSql);
		if (ConnManager.executeUpdate(updateSql) < 0) {
			throw new SaveCustUserException();
		}

		createAuthority(registerReq.getMobileNo());
	}
	
	
	/**
	 * 注册用户授权
	 * @param userName
	 * @throws SaveAuthorityException
	 * @author Andy.Niu
	 * @create 2014-08-01
	 */
	private void createAuthority(String userName) throws SaveAuthorityException{
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		
//		setMap.put("USER_ID", "ID");
//		setMap.put("AUTHORITY_ID", Consts.DEFAULT_AUTHORITY);
//		
//		String createSql = MapAssemForSql.getInsertSqlBySelect(TableConsts.CUST_USER_AUTHORITY, setMap, "FROM cust_user WHERE USERNAME = '" + userName + "'");
		
		SingleIntQuery singleQuery = new SingleIntQuery();
		
		setMap.put("USER_ID", singleQuery.queryUserId(userName));
		setMap.put("AUTHORITY_ID", Consts.DEFAULT_AUTHORITY);
		
		String createSql = MapAssemForSql.getInsertSql(TableConsts.CUST_USER_AUTHORITY, setMap);
		
		if(ConnManager.executeUpdate(createSql) <= 0){
			throw new SaveAuthorityException();
		}
		
	}
	
	/**
	 * 清空忘记密码验证码信息
	 * @param userName
	 */
	public void clearIdentifyingCode(String userName){
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		setMap.put("MOBILE_VALIDATE_CODE", "");
//		setMap.put("MOBILE_VALIDATE_CODE_VALID_TIME", "");
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("USERNAME", userName);
		
		String updateSql = MapAssemForSql.getUpdateSql(TableConsts.CUST_USER, setMap, whereMap);
		RecordUtils.writeAction(logger, null, updateSql);
		ConnManager.executeUpdate(updateSql);
	}
	
	/**
	 * 更新用户密码
	 * @param userName
	 * @param newPassWord
	 * @param salt
	 * @throws UpdateCustUserException 
	 */
	public void updateUserPassWord(String userName,String newPassWord,String salt) throws UpdateCustUserException{
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		setMap.put("PASSWORD", newPassWord);
		setMap.put("SALT", salt);
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("USERNAME", userName);
		
		String updateSql = MapAssemForSql.getUpdateSql(TableConsts.CUST_USER, setMap, whereMap);
		RecordUtils.writeAction(logger, null, updateSql);
		if (ConnManager.executeUpdate(updateSql) < 0) {
			throw new UpdateCustUserException();
		}
		
	}
	
	/**
	 * 更新用户抽奖次数
	 * @param userName
	 * @throws UpdateCustUserException 
	 */
	public void updateUserLotteryInfo(String userName) throws UpdateCustUserException{
		
		String updateSql = "UPDATE CUST_USER SET IS_LOTTERY = 1,LOTTERY_NUM = LOTTERY_NUM - 1 WHERE USERNAME = '" + userName + "'";
	
		RecordUtils.writeAction(logger, null, updateSql);
		if (ConnManager.executeUpdate(updateSql) < 0) {
			throw new UpdateCustUserException();
		}
		
	}
	
	/**
	 * 更新用户抽奖次数
	 * @param userName
	 * @throws UpdateCustUserException 
	 */
	public void updateUserLotteryNum(String userName) throws UpdateCustUserException{
		
		String updateSql = "UPDATE CUST_USER SET LOTTERY_NUM = 1 WHERE USERNAME = '" + userName + "'";
	
		RecordUtils.writeAction(logger, null, updateSql);
		if (ConnManager.executeUpdate(updateSql) < 0) {
			throw new UpdateCustUserException();
		}
		
	}
	
}
