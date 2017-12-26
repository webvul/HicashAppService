package com.hengyuan.hicash.domain.query.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * 用户注册查询Dao
 * 查询cust_user表的id
 * @author Cary.Liu
 * @create date 2014-07-23
 */
public class RegisterToAuthorityQuery extends AbstractDAO<String> {
	
	private static Logger logger = Logger.getLogger(RegisterToAuthorityQuery.class);

	@Override
	public String mapping(ResultSet rs) throws SQLException {

		if(rs!=null&&rs.getString("id")!=null){
			System.out.println("用户ID：【"+rs.getString("id")+"】");
			return rs.getString("id");
		}else{
			return null;
		}
	}
	
	/**
	 * 查询用户id
	 * @param userName
	 * @return
	 */
	public String queryCustUserId(String userName){
		List<String> list  = new ArrayList<String>();
		list.add("id");
		Map<String, Object> whereMap = new HashMap<String, Object>();
		
		whereMap.put("username", userName);
		
		String querySql = MapAssemForSql.getSelectSql(list, TableConsts.CUST_USER, whereMap);
		//记录日志
				RecordUtils.writeAction(logger, userName, querySql); 
		return ConnManager.singleQuery(querySql, this);
	}
	
}
