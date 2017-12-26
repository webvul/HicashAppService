package com.hengyuan.hicash.domain.event.user;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.exception.ChangePasswordException;
import com.hengyuan.hicash.parameters.request.user.ChangePasswordReq;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * hicash修改密码dao
 * 
 * @author Cary.Liu
 * @create date 2014-07-17
 */
public class ChangePasswordEvent {
	
	private static Logger logger = Logger.getLogger(ChangePasswordEvent.class);

	/**
	 * 根据用户名修改密码
	 * 
	 * @return
	 * @throws ChangePasswordException
	 */
	public void updatePassoword(ChangePasswordReq changePasswordReq)
			throws ChangePasswordException {
		String salt = RandomStringUtils.random(20, true, true);
		Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
		String password = md5PasswordEncoder.encodePassword(
				changePasswordReq.getNewPassword(), salt);

		Map<String, Object> setMap = new HashMap<String, Object>();
		
		setMap.put("PASSWORD", password);
		setMap.put("salt", salt);
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		
		whereMap.put("username", changePasswordReq.getUsername());

		String updateSql = MapAssemForSql.getUpdateSql(TableConsts.CUST_USER, setMap, whereMap);
		//记录日志
		RecordUtils.writeAction(logger, changePasswordReq.getUuid(), updateSql);
		if (ConnManager.executeUpdate(updateSql) <= 0) {
			throw new ChangePasswordException();
		}
	}
}
