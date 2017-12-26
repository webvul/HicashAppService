package com.hengyuan.hicash.domain.query.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.user.ChangetPasswordEntity;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.SqlAssemblyUtils;
import com.hengyuan.hicash.utils.StringUtils;


/**
 * 修改密码的查询dao
 * 
 * @author Cary.Liu
 * @create date 2014-07-17
 *
 */
public class ChangePasswordQuery extends AbstractDAO<ChangetPasswordEntity>{
	
	private static Logger logger = Logger.getLogger(ChangePasswordQuery.class);
	
	/** 查询用户 */
	private static final String USER_PASSWORD_QUERY = "SELECT * FROM cust_user WHERE 1=1 AND platform_Mark = '1' ";
	
	
	@Override
	public ChangetPasswordEntity mapping(ResultSet rs) throws SQLException {
		
		ChangetPasswordEntity changePasswordEntity = new ChangetPasswordEntity();
		
		if(rs!=null) {
			changePasswordEntity.setOldPassword(StringUtils.valueOf(rs.getObject("password")));
			changePasswordEntity.setOldSalt(StringUtils.valueOf(rs.getObject("salt")));
		}else{
			System.out.println("查询的结果集为空");
			return null;
		}
		
		return changePasswordEntity;
	}
	
	/**
	 * 根据用户名查询该用户是否存在
	 * @param userName
	 * @return
	 */
	public ChangetPasswordEntity queryUserExistByUserName(String userName){
		
		StringBuffer sql = new StringBuffer(USER_PASSWORD_QUERY);
		sql.append(SqlAssemblyUtils.receiveEqualSign("username", userName, false));
		
		//记录日志
		RecordUtils.writeAction(logger, userName, sql.toString());
		return ConnManager.singleQuery(sql.toString(), this);
	}
	
	/**
	 * 查询用户原始密码
	 * @return
	 */
	public ChangetPasswordEntity getUserOldPasswordByUserName(String userName){
		
		StringBuffer sql = new StringBuffer(USER_PASSWORD_QUERY);
		sql.append(SqlAssemblyUtils.receiveEqualSign("username", userName, false));
		//记录日志
		RecordUtils.writeAction(logger, userName, sql.toString());
		return ConnManager.singleQuery(sql.toString(), this);
	}

}
