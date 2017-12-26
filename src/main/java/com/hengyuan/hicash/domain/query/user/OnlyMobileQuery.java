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
import com.hengyuan.hicash.utils.MapAssemForSql;

/**
 * 手机唯一性验证 Dao
 * 
 * @author Cary.Liu
 * @createDate 2015-04-21
 * 
 */
public class OnlyMobileQuery extends AbstractDAO<Boolean>{
	
	private List<String> list;
	
	public OnlyMobileQuery(){
		
		list = new ArrayList<String>();
		list.add("MOBILENO");
	}
	
	
	@Override
	public Boolean mapping(ResultSet rs) throws SQLException {

		if(rs != null ){
			return true;
		}
		
		return false;
	}
	
	/**
	 * 查询手机号
	 * @param mobile
	 * @return
	 */
	public boolean isExistMobile(String mobile){
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("MOBILENO", mobile);
		
		String sql = MapAssemForSql.getSelectSql(list, TableConsts.CUST_USER,whereMap)
				+ " AND platform_Mark = '1' ";
		
		return ConnManager.singleQuery(sql, this);
	}
	
}
