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
import com.hengyuan.hicash.entity.param.AreaEntity;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.StringUtils;


/**
 * 查询区域
 * @author Cary.Liu
 * @create 2014-08-21
 *
 */
public class AreaQuery extends AbstractDAO<AreaEntity>{

	private List<String> lists = new ArrayList<String>();
	
	public AreaQuery(){
		lists.add("AREA_ID");
		lists.add("AREA");
		lists.add("FATHER");
	}
	@Override
	public AreaEntity mapping(ResultSet rs) throws SQLException {

		AreaEntity entity = null;
		
		if(rs != null){
			entity = new AreaEntity();
			entity.setAreaId(StringUtils.valueOf(rs.getObject("AREA_ID")));
			entity.setAreaName(StringUtils.valueOf(rs.getObject("AREA")));
		}
		
		return entity;
	}
	
	/**
	 * 查询区域
	 * @param provinceId
	 * @return
	 */
	public AreaEntity queryArea(String areaId){
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("AREA_ID", areaId);
		
		String sql = MapAssemForSql.getSelectSql(lists, TableConsts.AREA, whereMap);
		AreaEntity entity = ConnManager.singleQuery(sql, this);
		return entity !=null ? entity:new AreaEntity();
	}
	
	/**
	 * 查询区域
	 * @param 根据城市code
	 * @return
	 */
	public List<AreaEntity> queryAreaByCity(String cityCode){
		List<String> list = new ArrayList<String>();
		list.add("AREA_ID");
		list.add("AREA");
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		setMap.put("FATHER", cityCode);
		String sql =MapAssemForSql.getSelectSql(list, TableConsts.AREA, setMap);
		
//		RecordUtils.writeAction(logger, uuid, sql);
		
		return ConnManager.executeQuery(sql, this);
	}
	
	/**
	 * 根据城市代码查询区域列表
	 * 
	 * @param cityCode
	 * @return
	 */
	public AreaEntity queryAreaByCityCode(String cityCode) {

		String sql = "SELECT city_id area_id,city AREA FROM city WHERE city_id = '" + cityCode
				+ "' UNION" 
				+ " SELECT area_id,AREA FROM AREA WHERE father = '"+cityCode+"' AND bankflag = 0";
		
		AreaEntity entity = ConnManager.singleQuery(sql, this);
		return entity;
	}
	
}
