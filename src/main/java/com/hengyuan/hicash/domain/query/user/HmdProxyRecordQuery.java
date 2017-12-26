package com.hengyuan.hicash.domain.query.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.user.HmdProxyRecordEntity;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 嗨秒贷代扣记录
 * @author Cary.Liu
 * @createDate 2015-07-23
 *
 */
public class HmdProxyRecordQuery extends AbstractDAO<HmdProxyRecordEntity> {

	private static Logger logger = Logger.getLogger(HmdProxyValQuery.class);
	
	private static final String QUERY_SQL = "SELECT * FROM hmd_proxydeduct_record WHERE 1 = 1 ";
	
	@Override
	public HmdProxyRecordEntity mapping(ResultSet rs) throws SQLException {

		HmdProxyRecordEntity entity = null;
		
		if(rs != null ){
			
			entity = new HmdProxyRecordEntity();
			entity.setUserName(StringUtils.valueOf(rs.getObject("USERNAME")));
			entity.setRealName(StringUtils.valueOf(rs.getObject("REAL_NAME")));
			entity.setIdentityNo(StringUtils.valueOf(rs.getObject("IDENTITY_NO")));
			entity.setBankCard(StringUtils.valueOf(rs.getObject("BANK_CARD")));
			entity.setBankName(StringUtils.valueOf(rs.getObject("BANK_NAME")));
			entity.setCreateDate(StringUtils.valueOf(rs.getObject("CREATE_DATE")));
			entity.setBussFlowNo(StringUtils.valueOf(rs.getObject("BUSS_FLOWNO")));
			entity.setProxyStatus(StringUtils.valueOf(rs.getObject("PROXY_STATUS")));
		}
		
		return entity;
	}

	/**
	 * 获取用户代扣通过记录
	 * @param realName
	 * @param bankCard
	 * @return
	 */
	public HmdProxyRecordEntity queryProxySucc(String realName,String bankCard){
		
		StringBuffer querySql = new StringBuffer(QUERY_SQL);
		querySql.append(" AND REAL_NAME = '"+ realName +"' AND BANK_CARD = '"+ bankCard +"' ");
		// 代扣状态为等待中、成功、余额不足
		querySql.append(" AND PROXY_STATUS IN('WAIT','SUCC','YEBZ')");
		
		RecordUtils.writeAction(logger, null, querySql.toString());
		return ConnManager.singleQuery(querySql.toString(), this);
	}
	
	/**
	 * 获取用户代扣通过记录
	 * @param realName
	 * @param bankCard
	 * @return
	 */
	public HmdProxyRecordEntity queryProxySuccess(String realName,String bankCard){
		
		StringBuffer querySql = new StringBuffer(QUERY_SQL);
		querySql.append(" AND REAL_NAME = '"+ realName +"' AND BANK_CARD = '"+ bankCard +"' ");
		// 代扣状态为等待中、成功、余额不足
		querySql.append(" AND PROXY_STATUS ='SUCC'");
		
		RecordUtils.writeAction(logger, null, querySql.toString());
		return ConnManager.singleQuery(querySql.toString(), this);
	}
	/**
	 * 获取用户代扣通过记录
	 * @param realName
	 * @param bankCard
	 * @return
	 */
	public HmdProxyRecordEntity queryProxyWait(String realName,String bankCard){
		
		StringBuffer querySql = new StringBuffer(QUERY_SQL);
		querySql.append(" AND REAL_NAME = '"+ realName +"' AND BANK_CARD = '"+ bankCard +"' ");
		// 代扣状态为等待中、成功、余额不足
		querySql.append(" AND PROXY_STATUS ='WAIT'");
		
		RecordUtils.writeAction(logger, null, querySql.toString());
		return ConnManager.singleQuery(querySql.toString(), this);
	}
	/**
	 * 获取用户代扣通过记录
	 * @param realName
	 * @param bankCard
	 * @return
	 */
	public HmdProxyRecordEntity queryProxyYEBZ(String realName,String bankCard){
		
		StringBuffer querySql = new StringBuffer(QUERY_SQL);
		querySql.append(" AND REAL_NAME = '"+ realName +"' AND BANK_CARD = '"+ bankCard +"' ");
		// 代扣状态为等待中、成功、余额不足
		querySql.append(" AND PROXY_STATUS ='YEBZ' ");
		
		RecordUtils.writeAction(logger, null, querySql.toString());
		return ConnManager.singleQuery(querySql.toString(), this);
	}
}
