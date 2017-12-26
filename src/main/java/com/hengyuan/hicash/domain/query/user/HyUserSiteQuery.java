package com.hengyuan.hicash.domain.query.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.user.HyUserSite;
import com.hengyuan.hicash.utils.MapAssemForSql;

/**
 * 业务员商户售点对应 DAO
 * @author Cary.Liu
 * @createDate 2015-03-24
 *
 */
public class HyUserSiteQuery extends AbstractDAO<HyUserSite> {

	private List<String> columns = new ArrayList<String>();
	
	public HyUserSiteQuery(){
		
		columns.add("Id");
		columns.add("MerchantId");
		columns.add("MerchantName");
		columns.add("SiteId");
		columns.add("SiteName");
		columns.add("UserNum");
		columns.add("UserName");
		columns.add("SiteProId");
		columns.add("SiteProName");
		columns.add("SiteCityId");
		columns.add("SiteCityName");
		columns.add("CreateDate");
	}
	
	@Override
	public HyUserSite mapping(ResultSet rs) throws SQLException {

		HyUserSite entity = null;
		
		if(rs != null){
			
			entity = new HyUserSite();
			entity.setId(rs.getInt("Id"));
			entity.setMerchantId(rs.getString("MerchantId"));
			entity.setMerchantName(rs.getString("MerchantName"));
			entity.setSiteId(rs.getString("SiteId"));
			entity.setSiteName(rs.getString("SiteName"));
			entity.setUserNum(rs.getString("UserNum"));
			entity.setUserName(rs.getString("UserName"));
			entity.setSiteProId(rs.getString("SiteProId"));
			entity.setSiteProName(rs.getString("SiteProName"));
			entity.setSiteCityId(rs.getString("SiteCityId"));
			entity.setSiteCityName(rs.getString("SiteCityName"));
			entity.setCreateDate(rs.getString("CreateDate"));
			
		}
		
		return entity;
	}

	/**
	 * 查询业务员对应的商户
	 * @param userNumber
	 * @return
	 */
	public List<HyUserSite> querySupplierListByUserNum(String userNumber){
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("UserNum", userNumber);
		
		String sql = MapAssemForSql.getSelectSqlByGroupOrOrder(columns, TableConsts.HY_USER_SITE, whereMap," GROUP BY MerchantId");
		
		return ConnManager.executeQuery(sql, this);
	}
	
	
	/**
	 * 查询业务员对应的售点
	 * @param userNumber
	 * @return
	 */
	public List<HyUserSite> querySiteListByUserNum(String userNumber,String supplierId){
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("UserNum", userNumber);
		whereMap.put("MerchantId", supplierId);
		
		String sql = MapAssemForSql.getSelectSqlByGroupOrOrder(columns, TableConsts.HY_USER_SITE, whereMap," GROUP BY SiteId");
		
		return ConnManager.executeQuery(sql, this);
	}
	
	/**
	 * 查询业务员对应的售点
	 * @param userNumber
	 * @return
	 */
	public List<HyUserSite> querySiteListByUserNum(String userNumber){
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("UserNum", userNumber);
		
		String sql = MapAssemForSql.getSelectSqlByGroupOrOrder(columns, TableConsts.HY_USER_SITE, whereMap," GROUP BY SiteId");
		
		return ConnManager.executeQuery(sql, this);
	}
	
	/**
	 * 查询售点对应的业务员
	 * @param siteCode
	 * @return
	 */
	public List<HyUserSite> querySiteListBySite(String siteCode){
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("SITEID", siteCode);
		
		String sql = MapAssemForSql.getSelectSql(columns, TableConsts.HY_USER_SITE, whereMap);
		
		return ConnManager.executeQuery(sql, this);
	}
	
}
