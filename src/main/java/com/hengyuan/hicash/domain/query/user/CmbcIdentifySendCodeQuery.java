package com.hengyuan.hicash.domain.query.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.user.CmbcIdentifySendCodeEntity;
import com.hengyuan.hicash.parameters.request.user.CmbcIdentifySendCodeReq;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 民生银行代扣业务身份认证-用于发送动态验证码到用户手机。CP0032
 * 
 * @author leaf.Ren
 * @create date 2015-12-01
 */
public class CmbcIdentifySendCodeQuery extends AbstractDAO<CmbcIdentifySendCodeEntity> {

	private static Logger logger = Logger.getLogger(CmbcIdentifySendCodeQuery.class);
	
	private static final String QUERY_SQL = "SELECT * FROM cmbc_identify_val WHERE 1 = 1 ";
	
	@Override
	public CmbcIdentifySendCodeEntity mapping(ResultSet rs) throws SQLException {

		CmbcIdentifySendCodeEntity entity = null;
		
		if(rs != null ){
			
			entity = new CmbcIdentifySendCodeEntity();
			entity.setUserName(StringUtils.valueOf(rs.getObject("USERNAME")));
			entity.setCertNo(StringUtils.valueOf(rs.getObject("IDENTIfY_NO")));
			entity.setAccountNo(StringUtils.valueOf(rs.getObject("account_no")));
			entity.setAccountName(StringUtils.valueOf(rs.getObject("account_name")));
			entity.setCreateTime(StringUtils.valueOf(rs.getObject("CREATE_time")));
			entity.setUpdateTime(StringUtils.valueOf(rs.getObject("update_time")));
			entity.setBussflowNo(StringUtils.valueOf(rs.getObject("BUSS_FLOWNO")));
			entity.setValStatus(StringUtils.valueOf(rs.getObject("val_STATUS")));
			entity.setMobileNo(StringUtils.valueOf(rs.getObject("mobile_no")));
			entity.setPhoneToken(StringUtils.valueOf(rs.getObject("phone_Token")));
			entity.setPhoneVerCode(StringUtils.valueOf(rs.getObject("phone_VerCode")));
			entity.setBussflowNoConfirm(StringUtils.valueOf(rs.getObject("buss_flowNo_Confirm")));
			entity.setBussflowNoQuery(StringUtils.valueOf(rs.getObject("buss_flowNo_Query")));
			
		}
		
		return entity;
	}

	/**
	 * 获取用户四要素验证通过记录
	 * @return
	 */
	public CmbcIdentifySendCodeEntity querySendCodeSucc(CmbcIdentifySendCodeReq sendCodeReq){
		
		StringBuffer querySql = new StringBuffer(QUERY_SQL);
		
		querySql.append(" AND account_name = '"+ sendCodeReq.getAccountName() +"' AND account_no = '"+ sendCodeReq.getAccountNo() +"' ");
		
		querySql.append(" AND IDENTIFY_NO = '"+ sendCodeReq.getCertNo() +"' AND mobile_no = '"+ sendCodeReq.getMobileNo() +"'  AND username = '"+ sendCodeReq.getUserName() +"' ");
		// 验证状态为等待中,已经验证成功，
		querySql.append(" AND val_STATUS ='VALSUCC' ");
		
		RecordUtils.writeAction(logger, null, querySql.toString());
		return ConnManager.singleQuery(querySql.toString(), this);
	}
	
	/**
	 * 获取用户四要素验证通过记录,这一步现在暂时去掉，因为这个用户有可能用不同的
	 * @return
	 */
	public CmbcIdentifySendCodeEntity querySendCodeWait(CmbcIdentifySendCodeReq sendCodeReq){
		
		StringBuffer querySql = new StringBuffer(QUERY_SQL);
		querySql.append(" AND account_name = '"+ sendCodeReq.getAccountName() +"' AND account_no = '"+ sendCodeReq.getAccountNo() +"' ");
		
		querySql.append(" AND IDENTIfY_NO = '"+ sendCodeReq.getCertNo() +"' AND mobile_no = '"+ sendCodeReq.getMobileNo() +"'  AND username = '"+ sendCodeReq.getUserName() +"' ");
		// 验证状态为等待中,已经验证成功，
		querySql.append(" AND val_STATUS ='VALWAIT' ");
		
		RecordUtils.writeAction(logger, null, querySql.toString());
		return ConnManager.singleQuery(querySql.toString(), this);
	}
	/**
	 * 获取用户四要素验证通过记录,这一步现在暂时去掉，因为这个用户有可能用不同的
	 * @return
	 */
	public CmbcIdentifySendCodeEntity querySendCodeLimit(CmbcIdentifySendCodeReq sendCodeReq){
		
		StringBuffer querySql = new StringBuffer(QUERY_SQL);
		querySql.append(" AND account_name = '"+ sendCodeReq.getAccountName() +"' AND account_no = '"+ sendCodeReq.getAccountNo() +"' ");
		
		querySql.append(" AND IDENTIfY_NO = '"+ sendCodeReq.getCertNo() +"' AND mobile_no = '"+ sendCodeReq.getMobileNo() +"'  AND username = '"+ sendCodeReq.getUserName() +"' ");
		// 验证状态为等待中,已经验证成功，
		querySql.append(" AND val_STATUS ='VALSUCC' ");
		
		RecordUtils.writeAction(logger, null, querySql.toString());
		return ConnManager.singleQuery(querySql.toString(), this);
	}	
}
