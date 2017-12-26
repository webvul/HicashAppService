package com.hengyuan.hicash.domain.query.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.user.AccountEntity;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.StringUtils;

public class AccountQuery extends AbstractDAO<AccountEntity> {

	private List<String> lists = new ArrayList<String>();

	public AccountQuery() {
		lists.add("ID");
		lists.add("USERNAME");
	}

	@Override
	public AccountEntity mapping(ResultSet rs) throws SQLException {
		
		AccountEntity entity = null;
		
		if(rs != null){
			entity = new AccountEntity();
			entity.setAccountId(StringUtils.valueOf(rs.getObject("ID")));
			entity.setUserName(StringUtils.valueOf(rs.getObject("USERNAME")));
		}
		
		return entity;
	}
	
	
	public List<AccountEntity> queryAccount(String userName){
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("USERNAME", userName);
		
		String sql = MapAssemForSql.getSelectSql(lists, TableConsts.ACC_ACCOUNT, whereMap);
		return ConnManager.executeQuery(sql, this);
	}
	
}
