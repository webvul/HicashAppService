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
import com.hengyuan.hicash.entity.param.SchoolEntity;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

public class SchoolQuery extends AbstractDAO<SchoolEntity> {
	
	private static Logger logger = Logger.getLogger(SchoolQuery.class);

	@Override
	public SchoolEntity mapping(ResultSet rs) throws SQLException {
		
		SchoolEntity resp = new SchoolEntity();
		
		if (rs != null) {
			resp.setId(rs.getInt("ID"));
			
			resp.setShcoolName(StringUtils.valueOf(rs.getObject("shcool_name")));
		}else {
			return null;
		}
		
		return resp;
	}
	
	public SchoolEntity querySchoolInfo(Integer schoolid) {
		List<String> list = new ArrayList<String>();
		
		list.add("ID");
		list.add("shcool_name");
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		
		whereMap.put("ID", schoolid);
		
		String sql = MapAssemForSql.getSelectSql(list, TableConsts.UNIVERSITY, whereMap);
		//记录日志
				RecordUtils.writeAction(logger, null, sql); 
		return ConnManager.singleQuery(sql, this);
	}

}
