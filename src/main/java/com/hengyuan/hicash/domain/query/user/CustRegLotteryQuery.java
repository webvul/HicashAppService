package com.hengyuan.hicash.domain.query.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.user.CustRegLotteryEntity;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * （蓝领）用户注册成功，抽奖记录查询DAO
 * @author Cary.Liu
 * @createDate 2016-01-11
 *
 */
public class CustRegLotteryQuery extends AbstractDAO<CustRegLotteryEntity> {

	private static Logger logger = Logger.getLogger(CustRegLotteryQuery.class);
	
	private static final String QUERY_SQL = "SELECT * FROM cust_register_lottery WHERE 1 = 1 ";
	
	@Override
	public CustRegLotteryEntity mapping(ResultSet rs) throws SQLException {

		CustRegLotteryEntity entity = null;
		
		if(rs != null){
			
			entity = new CustRegLotteryEntity();
			entity.setUserName(StringUtils.valueOf(rs.getObject("USERNAME")));
			entity.setAmount(StringUtils.valueOf(rs.getObject("AMOUNT")));
			entity.setCreateDate(StringUtils.valueOf(rs.getObject("CREATE_TIME")));
		}
		
		return entity;
	}
	
	/**
	 * 获取用户抽奖记录
	 * @param userName
	 * @return
	 */
	public List<CustRegLotteryEntity> queryLotteryRecord(String userName){
		
		StringBuffer querySql = new StringBuffer(QUERY_SQL);
		
		querySql.append(" AND USERNAME = '" + userName + "'");
		
		RecordUtils.writeAction(logger, null, querySql.toString());
		return ConnManager.executeQuery(querySql.toString(), this);
	}

}
