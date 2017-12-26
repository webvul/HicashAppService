package com.hengyuan.hicash.domain.event.user;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.exception.UpdatePassWordException;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * 忘记密码修改密码
 * 
 * @author Eric
 * @create date 2014-07-24
 */
public class ResetPasswordEvent {
	
	private static Logger logger = Logger.getLogger(ResetPasswordEvent.class);

	/**
	 * 修改密码
	 * 
	 * @param salt
	 * @param password
	 * @param usernme
	 * @author Andy.Niu
	 * @throws UpdatePassWordException
	 * @create 2014-08-04
	 */
	public void updatePassword(String salt, String password, String usernme)
			throws UpdatePassWordException {
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		setMap.put("SALT", salt);
		setMap.put("PASSWORD",password);
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("USERNAME", usernme);
		
		String sql =  MapAssemForSql.getUpdateSql(TableConsts.CUST_USER, setMap, whereMap);
		//记录日志
		RecordUtils.writeAction(logger, usernme, sql);
		if (ConnManager.executeUpdate(sql) <= 0) {
			throw new UpdatePassWordException();
		}
	}

}
