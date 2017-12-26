package com.hengyuan.hicash.domain.query.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ExceptionMsg;
import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.user.QueryAuthEntity;
import com.hengyuan.hicash.exception.SaveDeviceException;
import com.hengyuan.hicash.parameters.request.user.QueryAuthReq;
import com.hengyuan.hicash.utils.DateUtils;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;

public class QueryAuthQuery extends AbstractDAO<QueryAuthEntity>{
	
	private static Logger logger = Logger.getLogger(QueryAuthQuery.class);
	private String uuid;
	
	private List<String> lists = new ArrayList<String>();
	
	public QueryAuthQuery() {
		lists.add("sx_mobile");
		lists.add("sx_success_time");
	}
	
	@Override
	public QueryAuthEntity mapping(ResultSet rs) throws SQLException {
		QueryAuthEntity queryAuthEntity = null;
		if(rs!=null){
			queryAuthEntity = new QueryAuthEntity();
			queryAuthEntity.setMobile(rs.getString("sx_mobile"));
			if(rs.getString("sx_success_time").isEmpty()){
				queryAuthEntity.setCount("0");
			}else{
				queryAuthEntity.setCount(rs.getString("sx_success_time"));
			}
		}
		return queryAuthEntity;
	}

	public QueryAuthEntity queryData(String userName) {
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("username", userName);
		String sql = MapAssemForSql.getSelectSql(lists, TableConsts.DDSJ_LIMIT, whereMap);
		logger.info("通过用户名查询授信成功次数、授信成功手机号和最高可享额度");
		return ConnManager.singleQuery(sql, this);

	}
	
}
