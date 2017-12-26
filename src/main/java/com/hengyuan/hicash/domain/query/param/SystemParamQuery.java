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
import com.hengyuan.hicash.entity.param.SystemParamEntity;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 系统参数查询dao
 * @author Cary.Liu
 * @create 2014-08-21
 *
 */
public class SystemParamQuery extends AbstractDAO<SystemParamEntity> {

	private List<String> lists = new ArrayList<String>();
	
	public SystemParamQuery(){
		lists.add("DTY_CODE");
		lists.add("DIC_CODE");
		lists.add("DIC_NAME");
	}
	
	@Override
	public SystemParamEntity mapping(ResultSet rs) throws SQLException {

		SystemParamEntity entity = null;
		if(rs != null){
			entity = new SystemParamEntity();
			entity.setDtyCode(StringUtils.valueOf(rs.getObject("DTY_CODE")));
			entity.setDicCode(StringUtils.valueOf(rs.getObject("DIC_CODE")));
			entity.setDicName(StringUtils.valueOf(rs.getObject("DIC_NAME")));
		}
		
		return entity;
	}
	
	
	public SystemParamEntity queryParamByDicCode(String dicCode){
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("DIC_CODE", dicCode);
		
		String sql = MapAssemForSql.getSelectSql(lists, TableConsts.DIT_DIC, whereMap);
		SystemParamEntity entity = ConnManager.singleQuery(sql, this);
		
		return entity != null? entity:new SystemParamEntity();
		
	}

	
	
}
