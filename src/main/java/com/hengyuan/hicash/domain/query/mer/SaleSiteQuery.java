package com.hengyuan.hicash.domain.query.mer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.mer.SaleSiteEntity;
import com.hengyuan.hicash.utils.MapAssemForSql;

/**
 * 售点
 * @author Cary.liu
 *
 */
public class SaleSiteQuery extends AbstractDAO<SaleSiteEntity> {

	private List<String> list;
	
	public SaleSiteQuery(){
		list = new ArrayList<String>();
		list.add("siteid");
		list.add("site_default_supplier");
		list.add("site_sale_name");
		list.add("logicCode");
		list.add("supLogicCode");
		list.add("provice");
		list.add("site_city_code");
		list.add("area");
	}
	
	@Override
	public SaleSiteEntity mapping(ResultSet rs) throws SQLException {

		SaleSiteEntity entity = null;
			
		if(rs != null){
			
			entity = new SaleSiteEntity();
			entity.setSiteId(rs.getString("siteid"));
			entity.setDefaultSupplier(rs.getString("site_default_supplier"));
			entity.setSiteName(rs.getString("site_sale_name"));
			entity.setLogicCode(rs.getString("logicCode"));
			entity.setSupLogicCode(rs.getString("supLogicCode"));
			entity.setProvince(rs.getString("provice"));
			entity.setCity(rs.getString("site_city_code"));
			entity.setArea(rs.getString("area"));
			
		}
		
		return entity;
	}

	/**
	 * 根据ID查售点
	 * @param saleId
	 * @return
	 */
	public SaleSiteEntity querySaleSiteById(String saleId){
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		
		whereMap.put("siteid", saleId);
		
		String sql = MapAssemForSql.getSelectSql(list, TableConsts.SALE_SITE, whereMap);
		
		return ConnManager.singleQuery(sql, this);
	}
	
	/**
	 * 根据商户ID查售点
	 * @param saleId
	 * @return
	 */
	public List<SaleSiteEntity> querySaleSiteBySup(String supplierId){
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		
		whereMap.put("site_default_supplier", supplierId);
		
		String sql = MapAssemForSql.getSelectSql(list, TableConsts.SALE_SITE, whereMap);
		
		return ConnManager.executeQuery(sql, this);
	}
	
	
}
