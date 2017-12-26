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
import com.hengyuan.hicash.entity.param.SaleSiteListEntity;
import com.hengyuan.hicash.utils.MapAssemForSql;

/**
 * 售点查询
 * @author Cary.Liu
 * @createDate 2015-04-22
 *
 */
public class SaleSiteQuery extends AbstractDAO<SaleSiteListEntity> {

	private List<String> selectCol = new ArrayList<String>();
	
	public SaleSiteQuery(){
		
		selectCol.add("siteid");
		selectCol.add("site_sale_name");
		selectCol.add("provice");
		selectCol.add("site_city_code");
		selectCol.add("area");
		selectCol.add("logicCode");
		selectCol.add("sale_site_allnode");
		selectCol.add("sale_site_node");
		selectCol.add("sale_site_status");
		selectCol.add("site_address");
		selectCol.add("contact_name");
		selectCol.add("site_tel");
		
	}
	
	@Override
	public SaleSiteListEntity mapping(ResultSet rs) throws SQLException {

		SaleSiteListEntity saleSite = null;
		
		if(rs != null){
			
			saleSite = new SaleSiteListEntity();
			saleSite.setSiteId(rs.getInt("siteid"));
			saleSite.setSiteSaleName(rs.getString("site_sale_name"));
			saleSite.setProvinceCode(rs.getString("provice"));
			saleSite.setCityCode(rs.getString("site_city_code"));
			saleSite.setAreaCode(rs.getString("area"));
			saleSite.setLogicCode(rs.getString("logicCode"));
			saleSite.setAllNode(rs.getString("sale_site_allnode"));
			saleSite.setNode(rs.getString("sale_site_node"));
			saleSite.setStatus(rs.getString("sale_site_status"));
			saleSite.setSiteAddress(rs.getString("site_address"));
			saleSite.setContactName(rs.getString("contact_name"));
			saleSite.setChargeTel(rs.getString("site_tel"));
			
		}
		
		return saleSite;
	}
	
	/**
	 * 查询售点
	 * @param siteId
	 * @return
	 */
	public SaleSiteListEntity querySaleSiteById(String siteId){
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("siteid", siteId);
		
		String querySql = MapAssemForSql.getSelectSql(selectCol, TableConsts.SALE_SITE, whereMap);
		
		return ConnManager.singleQuery(querySql, this);
	}
	
	public List<SaleSiteListEntity> querySiteList(String supplierId){
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("site_default_supplier", supplierId);
		
		String querySql = MapAssemForSql.getSelectSql(selectCol, TableConsts.SALE_SITE, whereMap);
		
		return ConnManager.executeQuery(querySql, this);
		
	}
	
	public List<SaleSiteListEntity> querySiteListByLimit(String supplierId){
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("site_default_supplier", supplierId);
		
		String querySql = MapAssemForSql.getSelectSql(selectCol, TableConsts.SALE_SITE, whereMap) + " LIMIT 2";
		
		return ConnManager.executeQuery(querySql, this);
		
	}

}
