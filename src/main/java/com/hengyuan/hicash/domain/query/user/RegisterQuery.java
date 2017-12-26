package com.hengyuan.hicash.domain.query.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * 用户注册查询dao
 * @author Cary.Liu
 * @createDate 2015-04-21
 *
 */
public class RegisterQuery extends AbstractDAO<String> {
	
	private static Logger logger = Logger.getLogger(RegisterQuery.class);

	@Override
	public String mapping(ResultSet rs) throws SQLException {
		
		if(rs != null){
			return ResultCodes.NORMAL;
		}
		
		return null;
	}
	
	/**
	 * 验证用户名是否存在
	 * @param userName
	 * @return
	 * @author Andy.Niu
	 * @create 2014-08-01
	 */
	public boolean isUserExist(String userName){
		
		String querySql = "SELECT USERNAME  FROM cust_user WHERE username ='" + userName + "' "
				+ "UNION "
				+ "SELECT USERNAME FROM acct_account WHERE USERNAME = '" + userName + "' "
				+ "UNION "
				+ "SELECT USERNAME FROM approve_user WHERE USERNAME = '" + userName + "' "
				+ "UNION "
				+ "SELECT SUPPLIER_NAME FROM d_supplier_info WHERE SUPPLIER_NAME = '" + userName + "' ";
		
		String result = ConnManager.singleQuery(querySql, this);
		//记录日志
				RecordUtils.writeAction(logger, userName, querySql); 
		return result != null ? true : false;
	}
	
	/**
	 * 验证用户是否存在(包括手机)
	 * @param userName
	 * @return
	 */
	public boolean isUserExistByApp(String userName){
		
		String querySql = "SELECT USERNAME  FROM cust_user WHERE (username ='"+ userName +"' OR MOBILENO = '"+ userName +"')"
				+ " UNION "
				+ " SELECT USERNAME FROM cust_customer WHERE (USERNAME = '"+ userName +"' OR MOBILE = '"+ userName +"')"
				+ " UNION "
				+ " SELECT USERNAME FROM acct_account WHERE USERNAME = '"+ userName +"' "
				+ " UNION "
				+ " SELECT USERNAME FROM approve_user WHERE USERNAME = '"+ userName +"' "
				+ " UNION "
				+ " SELECT SUPPLIER_NAME FROM d_supplier_info WHERE SUPPLIER_NAME = '"+ userName +"' ";
		
		String result = ConnManager.singleQuery(querySql, this);
		return result != null ? true : false;
	}
	
	/**
	 * 用户身份证号是否存在
	 * @param idCard
	 * @return
	 */
	public boolean isIdCardExist(String idCard){
		
		String querySql = "SELECT USERNAME FROM cust_customer WHERE IDENTITY_NO = '" + idCard + "'";
		
		String result = ConnManager.singleQuery(querySql, this);
		return result != null ? true : false;
	}
	
	
}
