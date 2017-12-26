package com.hengyuan.hicash.domain.query.app;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.user.CustLimit;
import com.hengyuan.hicash.utils.RecordUtils;



/**
 * @author Mary.Luo
 *
 */
public class CustLimitBlueQuery extends AbstractDAO<CustLimit> {
	
	private static Logger logger = Logger.getLogger(CustLimitBlueQuery.class);

	@Override
	public CustLimit mapping(ResultSet rs) throws SQLException {
		CustLimit custLimit = new CustLimit();
		if (rs != null) {
			
			custLimit.setBlockAmt(new BigDecimal(rs.getString("block_amount")));
			custLimit.setUseAmt(new BigDecimal(rs.getString("USE_AMOUNT")));
		}
		return custLimit;
	}
	
	/**
	 * 根据用户名查询客户额度信息
	 * @param userName
	 * @return
	 */
	public CustLimit getCustLimitByUserName(String userName){
		
		String sql="select block_amount,USE_AMOUNT from cust_limit where user_name='"+userName+"'";
		
		RecordUtils.writeAction(logger, userName, sql);
		return ConnManager.singleQuery(sql, this);
		
	}

}
