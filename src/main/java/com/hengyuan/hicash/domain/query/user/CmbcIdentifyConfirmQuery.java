package com.hengyuan.hicash.domain.query.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.user.CmbcIdentifySendCodeEntity;
import com.hengyuan.hicash.parameters.request.user.CmbcIdentifyConfirmReq;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 民生银行代扣业务身份认证-用于验证交易用户验证交易码。CP0030
 * 
 * @author LiHua.Ren
 * @create date 2015-12-02
 */
public class CmbcIdentifyConfirmQuery extends AbstractDAO<CmbcIdentifySendCodeEntity> {

	private static Logger logger = Logger.getLogger(CmbcIdentifyConfirmQuery.class);
	
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
	 * 获取用户四要素验证通过记录,这一步现在暂时去掉，因为这个用户有可能用不同的
	 * @return
	 */
	public CmbcIdentifySendCodeEntity queryConfirmSucc(CmbcIdentifyConfirmReq confirmReq){
		
		StringBuffer querySql = new StringBuffer(QUERY_SQL);
		querySql.append(" AND account_name = '"+ confirmReq.getAccountName() +"' AND account_no = '"+ confirmReq.getAccountNo() +"' ");
		
		querySql.append(" AND IDENTIfY_NO = '"+ confirmReq.getCertNo() +"' AND mobile_no = '"+ confirmReq.getMobileNo()+"'  AND username = '"+ confirmReq.getUserName() +"' ");
		// 验证状态为等待中,已经验证成功，
		querySql.append(" AND val_STATUS ='VALSUCC' ");
		
		RecordUtils.writeAction(logger, null, querySql.toString());
		return ConnManager.singleQuery(querySql.toString(), this);
	}
	/**
	 * 获取用户四要素验证通过记录
	 * @return
	 */
	public CmbcIdentifySendCodeEntity queryConfirmWait(CmbcIdentifyConfirmReq confirmReq){
		
		StringBuffer querySql = new StringBuffer(QUERY_SQL);
		querySql.append(" AND account_name = '"+ confirmReq.getAccountName() +"' AND account_no = '"+ confirmReq.getAccountNo() +"' ");
		
		querySql.append(" AND IDENTIfY_NO = '"+ confirmReq.getCertNo() +"' AND mobile_no = '"+ confirmReq.getMobileNo()+"'  AND username = '"+ confirmReq.getUserName() +"' ");
		// 验证状态为等待中,已经验证成功，
		querySql.append(" AND val_STATUS ='VALWAIT' ");
		
		RecordUtils.writeAction(logger, null, querySql.toString());
		return ConnManager.singleQuery(querySql.toString(), this);
	}	
	/**
	 * 获取用户四要素验证通过记录
	 * @return
	 */
	public CmbcIdentifySendCodeEntity querySendCodeSucc(CmbcIdentifyConfirmReq confirmReq){
		
		StringBuffer querySql = new StringBuffer(QUERY_SQL);
		querySql.append(" and BUSS_FLOWNO='"+confirmReq.getBussflowNo()+"' ");
//		querySql.append(" AND account_name = '"+ confirmReq.getAccountName() +"' AND account_no = '"+ confirmReq.getAccountNo() +"' ");
//		
//		querySql.append(" AND IDENTITY_NO = '"+ confirmReq.getCertNo() +"' AND mobile_no = '"+ confirmReq.getMobileNo()+"'  AND username = '"+ confirmReq.getUserName() +"' ");
		// 验证状态为短信发送成功,已经验证成功，
		querySql.append(" AND val_STATUS='SENDSUCC'");
		
		RecordUtils.writeAction(logger, null, querySql.toString());
		return ConnManager.singleQuery(querySql.toString(), this);
	}
	
	/**
	 * 获取用户四要素验证处理中
	 * @return
	 */
	public List<CmbcIdentifySendCodeEntity> querySendCodeWait(){
		
		StringBuffer querySql = new StringBuffer(QUERY_SQL);
		querySql.append(" AND val_STATUS='VALWAIT'");
		
		RecordUtils.writeAction(logger, null, querySql.toString());
		return ConnManager.executeQuery(querySql.toString(), this);
	}
	
	
	/**
	 * 获取用户四要素验证通过记录
	 * @return
	 */
	public CmbcIdentifySendCodeEntity queryConfirmSuccVal(String accountName,String accountNo,String identifyNo,String mobileNo){
		
		StringBuffer querySql = new StringBuffer(QUERY_SQL);
		querySql.append(" AND account_name = '"+ accountName +"' AND account_no = '"+ accountNo +"' ");
		
		querySql.append(" AND IDENTIfY_NO = '"+ identifyNo +"' AND mobile_no = '"+ mobileNo+"'");
		// 验证状态为等待中,已经验证成功，
		querySql.append(" AND val_STATUS ='VALSUCC' ");
		
		RecordUtils.writeAction(logger, null, querySql.toString());
		return ConnManager.singleQuery(querySql.toString(), this);
	}
}
