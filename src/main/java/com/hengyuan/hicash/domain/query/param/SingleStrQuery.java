package com.hengyuan.hicash.domain.query.param;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;

/**
 * 单列字符查询
 * @author Cary.Liu
 * @createDate 2015-07-10
 *
 */
public class SingleStrQuery extends AbstractDAO<String> {

	@Override
	public String mapping(ResultSet rs) throws SQLException {

		if(rs != null){
			return String.valueOf(rs.getObject("STR"));
		}
		
		return null;
	}

	/**
	 * 获取商户主键ID
	 * @return
	 */
	public String queryPrimaryBySupplier() {
		
		String sql = "SELECT AUTO_INCREMENT AS STR FROM information_schema.`TABLES` WHERE TABLE_NAME='" + TableConsts.SUPPLIER +"';";

		return ConnManager.singleQuery(sql, this);
	}
	
	/**
	 * 获取approve_user主键ID
	 * @return
	 */
	public String queryPrimaryByApprove() {
		
		String sql = "SELECT AUTO_INCREMENT AS STR FROM information_schema.`TABLES` WHERE TABLE_NAME='" + TableConsts.APPROVE_USER +"';";

		return ConnManager.singleQuery(sql, this);
	}
	
	/**
	 * 获取主键
	 * @return
	 */
	public String getMaxIndex(){
		
		String sql = "SELECT @@IDENTITY AS STR";
		
		return ConnManager.singleQuery(sql, this);
	}
	
}
