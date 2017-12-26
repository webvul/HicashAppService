package com.hengyuan.hicash.domain.event.user;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * 用户登录 更新 Dao
 * @author Cary.Liu
 * @create date 2014-07-29
 *
 */
public class LoginEvent {

	
	private static Logger logger = Logger.getLogger(LoginEvent.class);
	/**
	 * 更新最后的登录时间
	 * @param userName
	 * @return
	 */
	public int updateLoginTime(String userName){
		String lastLoginTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		
		setMap.put("LASTLOGIN_TIME", lastLoginTime);
		
		setMap.put("LOCKED", "0");
		
		Map<String, Object> wheremMap = new HashMap<String, Object>();
		
		wheremMap.put("username", userName);
		
		String updateSql = MapAssemForSql.getUpdateSql(TableConsts.CUST_USER, setMap, wheremMap);
		//记录日志
		RecordUtils.writeAction(logger, userName, updateSql);
		return ConnManager.executeUpdate(updateSql);
	}
	
	/**
	 * 更新最后的登录时间、城市
	 * @param userName
	 * @return
	 */
	public int updateLoginTimeByApp(String userName,String cityCode){
		
		String lastLoginTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		
		setMap.put("LASTLOGIN_TIME", lastLoginTime);
		
		setMap.put("LOCKED", "0");
		
		setMap.put("LOGIN_CITY", cityCode);
		
		Map<String, Object> wheremMap = new HashMap<String, Object>();
		
		wheremMap.put("username", userName);
		
		String updateSql = MapAssemForSql.getUpdateSql(TableConsts.CUST_USER, setMap, wheremMap);
		//记录日志
		RecordUtils.writeAction(logger, userName, updateSql);
		return ConnManager.executeUpdate(updateSql);
	}
	
	/**
	 * 登录失败记录失败次数最大5次
	 * @return
	 */
	public int updateLockedNum(String userName,int lockedNum){
		Map<String, Object> setMap = new HashMap<String, Object>();
		
		setMap.put("LOCKED", lockedNum);
		
		Map<String, Object> wheremMap = new HashMap<String, Object>();
		
		wheremMap.put("username", userName);
		
		String updateSql = MapAssemForSql.getUpdateSql(TableConsts.CUST_USER, setMap, wheremMap);
		//记录日志
				RecordUtils.writeAction(logger, userName, updateSql);
		return ConnManager.executeUpdate(updateSql);
	}
	
	/**
	 * 插入登录认证标志LOGIN_TOKEN
	 * @param userName
	 * @return
	 */
	public int updateLoginToken(String userName,String loginToken){
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		
		setMap.put("LOGIN_TOKEN", loginToken);
		
		Map<String, Object> wheremMap = new HashMap<String, Object>();
		
		wheremMap.put("username", userName);
		
		String updateSql = MapAssemForSql.getUpdateSql(TableConsts.CUST_USER, setMap, wheremMap);
		
		return ConnManager.executeUpdate(updateSql);
	}
}
