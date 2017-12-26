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
import com.hengyuan.hicash.entity.user.CustLimitEntity;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

public class CustLimitQuery extends AbstractDAO<CustLimitEntity>{
	private static Logger logger = Logger.getLogger(CustLimitQuery.class);
	private List<String> list;
	
	public CustLimitQuery(){
		list = new ArrayList<String>();
		
		list.add("USER_NAME");
		list.add("USE_AMOUNT");
		list.add("TRUST_AMOUNT");
		list.add("MAX_AMOUNT");
		list.add("BLOCK_AMOUNT");
		list.add("APPLY_AMOUNT");
	}
	
	@Override
	public CustLimitEntity mapping(ResultSet rs) throws SQLException {
		
		  CustLimitEntity custLimitEntity = new CustLimitEntity(); 
		  
		  if(rs!=null){
			  
			  custLimitEntity.setUserName(StringUtils.valueOf(rs.getObject("USER_NAME")));
			  custLimitEntity.setUseAmt(StringUtils.valueOf(rs.getObject("USE_AMOUNT")));
			  custLimitEntity.setTrustAmt(StringUtils.valueOf(rs.getObject("TRUST_AMOUNT")));
			  custLimitEntity.setMaxAmt(StringUtils.valueOf(rs.getObject("MAX_AMOUNT")));
			  custLimitEntity.setBlockAmt(StringUtils.valueOf(rs.getObject("BLOCK_AMOUNT")));
			  custLimitEntity.setApplyAmt(StringUtils.valueOf(rs.getObject("APPLY_AMOUNT")));
			  
		  }else{
			  System.out.println("结果集为空");
			  return null;
		  }
		
		return custLimitEntity;
	}

	/**
	 * 根据用户查询额度
	 * @param username
	 * @return
	 */
	public CustLimitEntity queryCustLimitByUserName(String username){
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		setMap.put("USER_NAME", username);
		
		
		String sql = MapAssemForSql.getSelectSql(list, TableConsts.CUST_LIMIT, setMap);
		//记录日志
		RecordUtils.writeAction(logger, username, sql);
		return ConnManager.singleQuery(sql,this);
		
	}
}
