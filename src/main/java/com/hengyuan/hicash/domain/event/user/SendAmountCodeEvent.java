package com.hengyuan.hicash.domain.event.user;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.exception.UpdateAmountException;
import com.hengyuan.hicash.exception.UpdateMobileNoException;
import com.hengyuan.hicash.utils.DateUtils;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * 发送获取额度验证码 DAO
 * @author Cary.Liu
 * @createDate 2015-04-21
 *
 */
public class SendAmountCodeEvent {
	
	private static Logger logger = Logger.getLogger(SendAmountCodeEvent.class);

	/**
	 * 保存额度验证码和最后验证时间到数据库
	 * @return
	 * @throws UpdateAmountException 
	 * @throws UpdateMobileNoException
	 * @author Andy.Niu
	 * @create 2014-08-13
	 */
	public void updateAmountValidateCode(String username, String validateCode ,String tempMobile)
			throws UpdateAmountException {
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		setMap.put("AMOUNT_VALIDATE_CODE", validateCode);
		setMap.put("AMOUNT_VALIDATE_CODE_TIME",DateUtils.getAfterMinuteTime(Consts.CODE_TIME_OUT));
		setMap.put("AMOUNT_VALIDATE_TEMP_MOBILE", tempMobile);

		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("USERNAME", username);

		String delSql =  MapAssemForSql.getUpdateSql(TableConsts.CUST_USER, setMap, whereMap);
		
		RecordUtils.writeAction(logger, username, delSql);
		if (ConnManager.executeUpdate(delSql) <= 0) {
			throw new UpdateAmountException();
		}
	}
	
	/**
	 * 保存额度验证码和最后验证时间到数据库
	 * @return
	 * @throws UpdateAmountException 
	 * @author Cary.Liu
	 * @createDate 2015-04-21
	 */
	public void saveValidateCode(String userName, String validateCode ,String tempMobile)
			throws UpdateAmountException {
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		setMap.put("USERNAME", userName);
		setMap.put("MOBILENO",tempMobile);
		setMap.put("IDENTIFYCODE", validateCode);
		setMap.put("SEND_IDENTIFYCODE_TIME", DateUtils.getAfterMinuteTime(Consts.CODE_TIME_OUT));
		setMap.put("CREATE_TIME", DateUtils.getDateStr(new Date()));

		String updateSql =  MapAssemForSql.getInsertSql(TableConsts.TEMP_IDENTIFYCODE, setMap);
		
		RecordUtils.writeAction(logger, userName, updateSql);
		if (ConnManager.executeUpdate(updateSql) <= 0) {
			throw new UpdateAmountException();
		}
	}
	
	/**
	 * 保存重置密码验证码和最后验证时间到数据库
	 * @return
	 * @throws UpdateAmountException 
	 * @throws UpdateMobileNoException
	 * @author Andy.Niu
	 * @create 2014-08-13
	 */
	public void updateResetPswValidateCode(String userName, String validateCode)throws UpdateAmountException {
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		setMap.put("MOBILE_VALIDATE_CODE", validateCode);
		setMap.put("MOBILE_VALIDATE_CODE_VALID_TIME",DateUtils.getAfterMinuteTime(Consts.CODE_TIME_OUT));

		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("USERNAME", userName);

		String delSql =  MapAssemForSql.getUpdateSql(TableConsts.CUST_USER, setMap, whereMap);
		
		RecordUtils.writeAction(logger, null, delSql);
		if (ConnManager.executeUpdate(delSql) <= 0) {
			throw new UpdateAmountException();
		}
	}
	
}
