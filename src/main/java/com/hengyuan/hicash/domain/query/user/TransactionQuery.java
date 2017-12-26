package com.hengyuan.hicash.domain.query.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.user.TransactionEntity;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.StringUtils;

public class TransactionQuery extends AbstractDAO<TransactionEntity> {

	private List<String> lists = new ArrayList<String>();

	public TransactionQuery() {
		lists.add("ACCOUNT_ID");
	}

	@Override
	public TransactionEntity mapping(ResultSet rs) throws SQLException {
		
		TransactionEntity entity = null;
		
		if(rs != null){
			entity = new TransactionEntity();
			entity.setAccountId(StringUtils.valueOf(rs.getObject("ACCOUNT_ID")));
		}
		
		return entity;
	}
	
	
	public List<TransactionEntity> queryTransaction(String accountId){
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("ACCOUNT_ID", accountId);
		whereMap.put("TRAN_TYPE", Consts.TRAN_TYPE_XSCC);//在线充值
		
		String sql = MapAssemForSql.getSelectSql(lists, TableConsts.ACCT_TRANSACTION, whereMap);
		return ConnManager.executeQuery(sql, this);
	}
	
}
