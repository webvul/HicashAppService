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
import com.hengyuan.hicash.entity.param.ProvinceEntity;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 查询省份
 * @author Cary.Liu
 * @create 2014-08-21
 *
 */
public class ProvinceQuery extends AbstractDAO<ProvinceEntity>{

	private List<String> lists = new ArrayList<String>();
	
	public ProvinceQuery(){
		lists.add("PROVINCE_ID");
		lists.add("PROVINCE");
	}
	
	@Override
	public ProvinceEntity mapping(ResultSet rs) throws SQLException {

		ProvinceEntity entity = null;
		
		if(rs != null){
			entity = new ProvinceEntity();
			entity.setProvCode(StringUtils.valueOf(rs.getObject("PROVINCE_ID")));
			entity.setProvName(StringUtils.valueOf(rs.getObject("PROVINCE")));
		}
		
		return entity;
	}
	
	/**
	 * 查询省
	 * @param provinceId
	 * @return
	 */
	public ProvinceEntity queryProvince(String provinceId){
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("PROVINCE_ID", provinceId);
		
		String sql = MapAssemForSql.getSelectSql(lists, TableConsts.PROVINCE, whereMap);
		ProvinceEntity entity = ConnManager.singleQuery(sql, this);
		return entity != null? entity:new ProvinceEntity();
	}
	/**
	 * 得到所有的省份集合
	 * @return
	 */
	public List<ProvinceEntity> queryAllProvince(){
		List<String> list = new ArrayList<String>();
		list.add("PROVINCE_ID");
		list.add("PROVINCE");
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		
		String sql = MapAssemForSql.getSelectSql(list, TableConsts.PROVINCE, setMap);
		
//		RecordUtils.writeAction(logger, uuid, sql);
		
		return ConnManager.executeQuery(sql, this);
	}
}
