package com.hengyuan.hicash.domain.query.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.user.LoginEntity;
import com.hengyuan.hicash.parameters.request.user.LoginReq;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * 用户登录dao
 * 
 * @author Cary.Liu
 * @create date 2014-07-18
 *
 */
public class LoginQuery extends AbstractDAO<LoginEntity> {
	
	private static Logger logger = Logger.getLogger(LoginQuery.class);
	
	private static final String USER_EXIST_QUERY = "SELECT id,password,salt,LOCKED,USERNAME FROM cust_user WHERE 1 = 1 AND platform_Mark = '1' ";
	
	
	@Override
	public LoginEntity mapping(ResultSet rs) throws SQLException {
		LoginEntity loginEntity = new LoginEntity();
		if(rs != null){
			loginEntity.setExistFlag(true);
			loginEntity.setUserName(rs.getString("USERNAME"));
			loginEntity.setOldPassword(rs.getString("password"));
			loginEntity.setOldSalt(rs.getString("salt"));
			loginEntity.setLockedNum(rs.getInt("LOCKED"));
			loginEntity.setId(rs.getString("id"));
		}else{
			System.out.println("登录用户不存在");
			loginEntity.setExistFlag(false);
		}
		
		return loginEntity;
	}
	
	/**
	 * 查询用户是否存在
	 * @param userName
	 * @return
	 */
	public LoginEntity userExistQuery(LoginReq login){
		StringBuffer querySql = new StringBuffer(USER_EXIST_QUERY);
		//用户名输入有可以有用户名也可以有手机号码
		querySql.append(" AND (username='"+login.getUserName()+"' OR mobileno='"+login.getUserName()+"')");
		
		//记录日志
		RecordUtils.writeAction(logger, login.getUuid(), querySql.toString());
		return ConnManager.singleQuery(querySql.toString(), this);
	}
	
	/**
	 * 查询用户是否存在
	 * @param userName 用户名或者手机号
	 * @return
	 */
	public LoginEntity queryUserByUserAndPhone(String userName){
		StringBuffer querySql = new StringBuffer(USER_EXIST_QUERY);
		//用户名输入有可以有用户名也可以有手机号码
		querySql.append(" AND (username='"+userName+"' OR mobileno='"+userName+"')");
		
		//记录日志
		RecordUtils.writeAction(logger, null, querySql.toString());
		return ConnManager.singleQuery(querySql.toString(), this);
	}
	
	
}
