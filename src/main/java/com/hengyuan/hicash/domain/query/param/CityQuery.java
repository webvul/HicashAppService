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
import com.hengyuan.hicash.entity.param.CityEntity;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 查询城市
 * @author Cary.Liu
 * @create 2014-08-21
 *
 */
public class CityQuery extends AbstractDAO<CityEntity>{

	private List<String> lists = new ArrayList<String>();
	
	public CityQuery(){
		lists.add("CITY_ID");
		lists.add("CITY");
		lists.add("FATHER");
	}
	
	@Override
	public CityEntity mapping(ResultSet rs) throws SQLException {

		CityEntity entity = null;
		
		if(rs != null){
			entity = new CityEntity();
			entity.setCityCode(StringUtils.valueOf(rs.getObject("CITY_ID")));
			entity.setCityName(StringUtils.valueOf(rs.getObject("CITY")));
			entity.setFatherCode(StringUtils.valueOf(rs.getObject("FATHER")));
		}
		
		return entity;
	}
	
	/**
	 * 查询市
	 * @param provinceId
	 * @return
	 */
	public CityEntity queryCity(String cityId){
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("CITY_ID", cityId);
		
		String sql = MapAssemForSql.getSelectSql(lists, TableConsts.CITY, whereMap);
		CityEntity entity = ConnManager.singleQuery(sql, this);
		return entity != null? entity:new CityEntity();
	}
	
	public CityEntity queryCityByName(String cityName){
		
//		Map<String, Object> whereMap = new HashMap<String, Object>();
//		whereMap.put("CITY", cityName);
		String querySql = "SELECT CITY_ID,CITY,FATHER FROM CITY WHERE CITY LIKE '%" + cityName + "%'";
		
		return ConnManager.singleQuery(querySql, this);
	}

	
	/**
	 * 根据省份代码查询数码3c开放城市列表
	 * @param provinceCode
	 * @return
	 */
	public List<CityEntity> queryCityByProCode(String provinceCode){
		
//		List<String> list = new ArrayList<String>();
//		list.add("CITY_ID");
//		list.add("city");
		Map<String, Object> setMap = new HashMap<String, Object>();
		setMap.put("FATHER", provinceCode);
		
		String sql = MapAssemForSql.getSelectSql(lists, TableConsts.CITY, setMap);
		
//		RecordUtils.writeAction(logger, uuid, sql);
		
		return ConnManager.executeQuery(sql, this); 
	}
	
	/**
	 * 查询省份代码
	 * @param cityId
	 * @return
	 */
	public String queryProvinceCode(String cityId){
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("CITY_ID", cityId);
		
		String sql = MapAssemForSql.getSelectSql(lists, TableConsts.CITY, whereMap);
		CityEntity entity = ConnManager.singleQuery(sql, this);
		
		return entity != null? entity.getFatherCode() : "";
	}
	
}
