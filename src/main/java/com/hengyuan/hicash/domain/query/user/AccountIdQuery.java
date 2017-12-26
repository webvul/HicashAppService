package com.hengyuan.hicash.domain.query.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 收款账户主键查询Dao
 * 
 * @author Cary.Liu
 * @create date 2014-08-15
 * 
 */
public class AccountIdQuery extends AbstractDAO<String> {

	@Override
	public String mapping(ResultSet rs) throws SQLException {
		if (rs != null) {
			return StringUtils.valueOf(rs.getObject("AUTO_INCREMENT"));
		} else {
			System.out.println("银行卡主键查询为空");
		}

		return null;
	}

	/**
	 * 查询银行卡主键
	 * 
	 * @return
	 */
	public String queryUpdateId() {
		
		String sql = "SELECT AUTO_INCREMENT FROM information_schema.`TABLES` WHERE TABLE_NAME='" + TableConsts.CUST_USER_CARD +"';";

		return ConnManager.singleQuery(sql, this);
	}

}
