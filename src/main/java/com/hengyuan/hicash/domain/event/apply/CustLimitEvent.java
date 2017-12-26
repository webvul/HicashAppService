package com.hengyuan.hicash.domain.event.apply;

import java.math.BigDecimal;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * @author Cary.Liu
 * @updateDate 2015-01-15
 */
public class CustLimitEvent {
	
	private static Logger logger = Logger.getLogger(CustLimitEvent.class);

	public int updateBlockAmtByUserName(BigDecimal blockAmt, String userName) {
		String rulesResultSql = "update cust_limit set block_amount = '" + blockAmt
				+ "' where user_name= '" + userName + "'";
		RecordUtils.writeAction(logger, null, rulesResultSql);
		return ConnManager.executeUpdate(rulesResultSql);

	}

}
