package com.hengyuan.hicash.domain.event.user;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.exception.DeleteCertificationException;
import com.hengyuan.hicash.exception.SaveCertificationException;
import com.hengyuan.hicash.exception.UpdateAmountException;
import com.hengyuan.hicash.exception.UpdateCustUserException;
import com.hengyuan.hicash.exception.UpdateMobileNoException;
import com.hengyuan.hicash.parameters.request.user.MobileUpdateReq;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * hicash修改用户注册手机号
 * 
 * @author Eric
 * @create date 2014-07-22
 */
public class MobileUpdateEvent {
	
	private static Logger logger = Logger.getLogger(MobileUpdateEvent.class);

	/**
	 * 根据认证状态和用户名删除认证项
	 * 
	 * @return
	 * @throws DeleteCertificationException 
	 */
	public void deleteCertification(String certificationType, String username) 
			throws DeleteCertificationException {
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		
		whereMap.put("USERNAME", username);
		whereMap.put("CERTIFICATION_TYPE", certificationType);
		
		String delSql = MapAssemForSql.getDeleteSql(TableConsts.CUST_CERTIFICATION, whereMap);
		//记录日志
				RecordUtils.writeAction(logger, username, delSql);
		if(ConnManager.executeUpdate(delSql) < 0){
			throw new DeleteCertificationException();
		}
	}

	/**
	 * 修改用户手机
	 * 
	 * @return
	 * @throws UpdateMobileNoException 
	 */
	public void updateCustCustomer(MobileUpdateReq mobileUpdateReq) 
			throws UpdateMobileNoException {

		Map<String, Object> setMap = new HashMap<String, Object>();
		setMap.put("MOBILE", mobileUpdateReq.getNewMobile());
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("USERNAME", mobileUpdateReq.getUserName());
		
		String sql = MapAssemForSql.getUpdateSql(TableConsts.CUST_CUSTOMER, setMap, whereMap);
		//记录日志
		RecordUtils.writeAction(logger, mobileUpdateReq.getUuid(), sql);
		if(ConnManager.executeUpdate(sql) <= 0){
			throw new UpdateMobileNoException();
		}
	}

	/**
	 * 添加基本认证对象
	 * 
	 * @return
	 * @throws SaveCertificationException 
	 */
	public void addCertification(MobileUpdateReq mobileUpdateReq) 
			throws SaveCertificationException {

		Map<String, Object> setMap = new HashMap<String, Object>();
		setMap.put("CERTIFICATION_TYPE", "MOBD");
		setMap.put("STATUS", "CERY");
		setMap.put("USERNAME", mobileUpdateReq.getUserName());
		setMap.put("SHOWP", "1");
		String sql = MapAssemForSql.getInsertSql(TableConsts.CUST_CERTIFICATION, setMap);
		//记录日志
				RecordUtils.writeAction(logger, mobileUpdateReq.getUuid(), sql);
		if(ConnManager.executeUpdate(sql) <= 0){
			throw new SaveCertificationException();
		}
	}

	/**
	 * 更新user表中手机号码，用于手机登陆
	 * 
	 * @return
	 * @throws UpdateCustUserException 
	 */
	public void updateCustUser(MobileUpdateReq mobileUpdateReq) 
			throws UpdateCustUserException {
		/*Map<String, Object> setMap = new HashMap<String, Object>();
		setMap.put("MOBILE_VALIDATE_CODE", "");
		setMap.put("MOBILE_VALIDATE_CODE_VALID_TIME", "");
		setMap.put("MOBILENO", mobileUpdateReq.getNewMobile());
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("USERNAME", mobileUpdateReq.getUserName());*/
		
		
		
		String sql = "UPDATE cust_user SET MOBILENO='"+mobileUpdateReq.getNewMobile()+"'," +
				"MOBILE_VALIDATE_CODE=NULL,MOBILE_VALIDATE_CODE_VALID_TIME=NULL  WHERE  USERNAME='"+mobileUpdateReq.getUserName()+"' ";
		//记录日志
				RecordUtils.writeAction(logger, mobileUpdateReq.getUuid(), sql);
		if(ConnManager.executeUpdate(sql) <= 0){
			throw new UpdateCustUserException();
		}

	}

	/**
	 * 保存验证码和最后验证时间到数据库
	 * 
	 * @return
	 * @throws UpdateMobileNoException
	 */
	public void updateMobileValidateCode(String username, String validateCode)
			throws UpdateMobileNoException {

		Calendar can = Calendar.getInstance();
		String time = Consts.CODE_TIME_OUT;
		can.add(Calendar.MINUTE, Integer.parseInt(time));
		Date afterTime = can.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String examineTime = format.format(afterTime);
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		setMap.put("MOBILE_VALIDATE_CODE", validateCode);
		setMap.put("MOBILE_VALIDATE_CODE_VALID_TIME",examineTime);

		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("USERNAME", username);

		String delSql =  MapAssemForSql.getUpdateSql(TableConsts.CUST_USER, setMap, whereMap);
		//记录日志
				RecordUtils.writeAction(logger, username, delSql);
		if (ConnManager.executeUpdate(delSql) <= 0) {
			throw new UpdateMobileNoException();
		}
	}
	
	public int updateCustUserByValidateCode(String username) {
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		setMap.put("MOBILE_VALIDATE_CODE", "");
		setMap.put("MOBILE_VALIDATE_CODE_VALID_TIME", "");
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("USERNAME", username);
		
		String sql =  MapAssemForSql.getUpdateSql(TableConsts.CUST_USER, setMap, whereMap);
		
//		System.out.println("修改用户手机SQL"+sql);
		//记录日志
		RecordUtils.writeAction(logger, username, sql);
		return ConnManager.executeUpdate(sql);
	}
	
	
	
	
	
	
	/**
	 * 保存额度验证码和最后验证时间到数据库
	 * @return
	 * @throws UpdateAmountException 
	 * @throws UpdateMobileNoException
	 * @author Andy.Niu
	 * @create 2014-08-13
	 */
	public void updateMobileValidateCode(String username, String validateCode ,String tempMobile)
			throws UpdateAmountException {
		System.out.println("更新手机绑定验证");
		Calendar can = Calendar.getInstance();
		String time = Consts.CODE_TIME_OUT;
		can.add(Calendar.MINUTE, Integer.parseInt(time));
		Date afterTime = can.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String examineTime = format.format(afterTime);
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		setMap.put("MOBILE_VALIDATE_CODE", validateCode);
		setMap.put("MOBILE_VALIDATE_CODE_VALID_TIME",examineTime);
		setMap.put("AMOUNT_VALIDATE_TEMP_MOBILE", tempMobile);

		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("USERNAME", username);

		String delSql =  MapAssemForSql.getUpdateSql(TableConsts.CUST_USER, setMap, whereMap);
		//记录日志
				RecordUtils.writeAction(logger, username, delSql);
		if (ConnManager.executeUpdate(delSql) <= 0) {
			throw new UpdateAmountException();
		}
	}

}
