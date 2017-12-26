package com.hengyuan.hicash.domain.query.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.user.HmdProxyValEntity;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

public class HmdProxyValQuery extends AbstractDAO<HmdProxyValEntity> {

	private static Logger logger = Logger.getLogger(HmdProxyValQuery.class);
	
	private static final String QUERY_SQL = "SELECT * FROM hmd_proxydeduct_validate WHERE 1 = 1 ";
	
	@Override
	public HmdProxyValEntity mapping(ResultSet rs) throws SQLException {

		HmdProxyValEntity entity = null;
		
		if(rs != null ){
			
			entity = new HmdProxyValEntity();
			entity.setUserName(StringUtils.valueOf(rs.getObject("USERNAME")));
			entity.setRealName(StringUtils.valueOf(rs.getObject("REAL_NAME")));
			entity.setIdentityNo(StringUtils.valueOf(rs.getObject("IDENTITY_NO")));
			entity.setBankCard(StringUtils.valueOf(rs.getObject("BANK_CARD")));
			entity.setBankName(StringUtils.valueOf(rs.getObject("BANK_NAME")));
			entity.setStatus(StringUtils.valueOf(rs.getObject("STATUS")));
			entity.setValMsg(StringUtils.valueOf(rs.getObject("VALIDATE_MSG")));
			entity.setCreateDate(StringUtils.valueOf(rs.getObject("CREATE_DATE")));
			entity.setBussFlowNo(StringUtils.valueOf(rs.getObject("BUSS_FLOWNO")));
			entity.setBussFlowNoVal(StringUtils.valueOf(rs.getObject("BUSS_FLOWNO_VAL")));
			entity.setMerOrderId(StringUtils.valueOf(rs.getObject("BANK_ORDERID")));
			entity.setCustId(StringUtils.valueOf(rs.getObject("BANK_CUST_ID")));
			entity.setPhoneToken(StringUtils.valueOf(rs.getObject("BANK_PHONETOKEN")));
		}
		
		return entity;
	}

	/**
	 * 获取用户代扣记录
	 * @param realName
	 * @param bankCard
	 * @param identityNo
	 * @return
	 */
	public HmdProxyValEntity queryByNameAndCard(String realName,String bankCard,String identityNo){
		
		StringBuffer querySql = new StringBuffer(QUERY_SQL);
		querySql.append(" AND REAL_NAME = '"+ realName +"' AND BANK_CARD = '"+ bankCard +"' AND IDENTITY_NO = '"+ identityNo +"'");
		
		RecordUtils.writeAction(logger, null, querySql.toString());
		return ConnManager.singleQuery(querySql.toString(), this);
	}
	
	/**
	 * 获取用户代扣通过记录
	 * @param realName
	 * @param bankCard
	 * @return
	 */
	public HmdProxyValEntity queryProxySucc(String realName,String bankCard){
		
		StringBuffer querySql = new StringBuffer(QUERY_SQL);
		querySql.append(" AND REAL_NAME = '"+ realName +"' AND BANK_CARD = '"+ bankCard +"' ");
		// 扣款成功，余额不足  或者 处理中状态 并且 扣款状态为等待中、成功、余额不足
		querySql.append(" AND STATUS IN (1,4) OR (STATUS = 3 AND PROXY_STATUS IN('WAIT','SUCC','YEBZ'))");
		
		RecordUtils.writeAction(logger, null, querySql.toString());
		return ConnManager.singleQuery(querySql.toString(), this);
	}
	/**
	 * 获取用户代扣通过记录
	 * @param realName
	 * @param bankCard
	 * @return
	 */
	public HmdProxyValEntity querySendCodeSucc(String bussFlowNo){
		
		StringBuffer querySql = new StringBuffer(QUERY_SQL);
		querySql.append(" AND BUSS_FLOWNO = '"+ bussFlowNo +"' AND PROXY_STATUS ='CODESUCC'");
		
		RecordUtils.writeAction(logger, null, querySql.toString());
		return ConnManager.singleQuery(querySql.toString(), this);
	}
}
