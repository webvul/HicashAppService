package com.hengyuan.hicash.domain.query.param;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.param.BankEntity;
import com.hengyuan.hicash.utils.MapAssemForSql;

/**
 * 获取系统银行
 * @author Cary.Liu
 * @createDate 2015-02-05
 *
 */
public class BankQuery extends AbstractDAO<BankEntity> {

	private List<String> column = new ArrayList<String>();
	
	public BankQuery(){
		
		column.add("bankcode");
		column.add("bankname");
		column.add("bankno");
	}
	
	@Override
	public BankEntity mapping(ResultSet rs) throws SQLException {

		BankEntity entity = null;
		if(rs != null){
			entity = new BankEntity();
			entity.setBankCode(rs.getString("bankcode"));
			entity.setBankName(rs.getString("bankname"));
			entity.setBankNo(rs.getString("bankno"));
		}
		
		return entity;
	}

	public List<BankEntity> queryBankList(){
		
		String querySql = MapAssemForSql.getSelectSql(column, TableConsts.SYS_BANK, new HashMap<String, Object>());
		return ConnManager.executeQuery(querySql, this);
	}
	
	/**
	 * 获取开户行名称
	 * @param bankCode
	 * @return
	 */
	public BankEntity queryBankName(String bankCode){
		
		Map<String , Object> whereMap = new HashMap<String, Object>();
		whereMap.put("bankcode", bankCode);
		
		String querySql = MapAssemForSql.getSelectSql(column, TableConsts.SYS_BANK, whereMap);
		
		return ConnManager.singleQuery(querySql, this);
	}
	
	public List<BankEntity> queryProxyBankList(){
		
		String querySql = MapAssemForSql.getSelectSql(column, TableConsts.SYS_PROXY_BANK, new HashMap<String, Object>());
		return ConnManager.executeQuery(querySql, this);
	}
	
	public BankEntity queryProxyBankByCode(String bankCode){
		
		Map<String , Object> whereMap = new HashMap<String, Object>();
		whereMap.put("bankcode", bankCode);
		
		String querySql = MapAssemForSql.getSelectSql(column, TableConsts.SYS_PROXY_BANK, whereMap);
		return ConnManager.singleQuery(querySql, this);
	}
	
}
