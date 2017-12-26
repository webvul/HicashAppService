package com.hengyuan.hicash.domain.query.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.user.AccountValRecordEntity;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 代扣账户验证记录 查询
 * 
 * @author Cary.Liu
 * @createDate 2015-07-22
 *
 */
public class AccountValRecordQuery extends AbstractDAO<AccountValRecordEntity> {

	private static final String QUERY_SQL = "SELECT * FROM ACCOUNT_VALIDATE_RECORD WHERE 1 = 1 ";
	
	private static Logger logger = Logger.getLogger(AccountValRecordQuery.class);
	
	@Override
	public AccountValRecordEntity mapping(ResultSet rs) throws SQLException {

		AccountValRecordEntity entity = null;
		
		if(rs != null){
			
			entity = new AccountValRecordEntity();
			entity.setId(StringUtils.valueOf(rs.getObject("ID")));
			entity.setUserName(StringUtils.valueOf(rs.getObject("USERNAME")));
			entity.setIdentityNo(StringUtils.valueOf(rs.getObject("USER_IDCARD")));
			entity.setOpenBank(StringUtils.valueOf(rs.getObject("OPEN_BANK")));
			entity.setBankCard(StringUtils.valueOf(rs.getObject("BANK_CARD")));
			entity.setValResult(StringUtils.valueOf(rs.getObject("VALIDATE_RESULT")));
			entity.setValMsg(StringUtils.valueOf(rs.getObject("VALIDATE_MSG")));
			entity.setCreateDate(StringUtils.valueOf(rs.getObject("CREATE_DATE")));
		}
				
		
		return entity;
	}
	
	/**
	 * 查询验证记录
	 * @param userName
	 * @param openBank
	 * @param bankCard
	 * @return
	 */
	public AccountValRecordEntity queryRecord(String userName,String identityNo,String bankCard){
		
		StringBuffer querySql = new StringBuffer(QUERY_SQL);
		querySql.append(" AND USERNAME = '"+ userName +"' AND USER_IDCARD = '"+ identityNo +"' AND BANK_CARD = '"+ bankCard +"'");
		
		
		RecordUtils.writeAction(logger, null, querySql.toString());
		return ConnManager.singleQuery(querySql.toString(), this);
	}

}
