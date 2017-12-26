package com.hengyuan.hicash.domain.query.mktApp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.mktApp.MktBusiCityEntity;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 二次营销-业务开放城市 Dao
 * @author Cary.Liu
 * @create 2015-03-13
 *
 */
public class MktBusiCityQuery extends AbstractDAO<MktBusiCityEntity> {

	private List<String> columns = new ArrayList<String>();
	
	public MktBusiCityQuery(){
		
		columns.add("city");
		columns.add("cityName");
		columns.add("provice");
		columns.add("proviceName");
	}
	
	@Override
	public MktBusiCityEntity mapping(ResultSet rs) throws SQLException {

		MktBusiCityEntity entity = null;
		
		if(rs != null){
			
			entity = new MktBusiCityEntity();
			entity.setCityCode(StringUtils.valueOf(rs.getObject("city")));
			entity.setCityName(StringUtils.valueOf(rs.getObject("cityName")));
			entity.setProvinceCode(StringUtils.valueOf(rs.getObject("provice")));
			entity.setProvinceName(StringUtils.valueOf(rs.getObject("proviceName")));
			
		}
		
		return entity;
	}

	/**
	 * 二次营销-获取业务开放城市
	 * @param custType
	 * @return
	 */
	public List<MktBusiCityEntity> queryBusiCityList(String custType){
		
//		Map<String, Object> whereMap = new HashMap<String, Object>();
//		whereMap.put("cust_Type", custType);
//		
//		String sql = MapAssemForSql.getSelectSql(columns, TableConsts.BUSI_CASH_CITY, whereMap);
		
		String querySql = "SELECT city,cityName,provice,proviceName FROM d_busi_cash_city GROUP BY city";
		
		return ConnManager.executeQuery(querySql, this);
	}
	
	
	
}
