package com.hengyuan.hicash.domain.query.mer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.mer.MerProductEntity;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 获取商户产品DAO
 * @author Cary.Liu
 * @createDate 2015-03-28
 *
 */
public class MerProductQuery extends AbstractDAO<MerProductEntity> {

	private static final String QUERY_SQL = "SELECT INDUSTRY,ID,PRO_NAME,PRO_DESC,PRO_MODEL,PRICE,PRO_STOCK,PRO_TYPE,PRO_COLOR,SUPPLIER_ID,supplierName,HY_PROID,PRO_TITLE FROM MER_PRODUCT_INFO WHERE 1 = 1 AND ENABLED = 1 AND STATUS = 'STATUS03' ";
//	AND SPREAD_STATUS = 'TG03' 推广中的产品
	@Override
	public MerProductEntity mapping(ResultSet rs) throws SQLException {

		MerProductEntity entity = null;
		
		if(rs != null){
			
			entity = new MerProductEntity();
			entity.setMerProId(StringUtils.valueOf(rs.getObject("ID")));
			entity.setProName(StringUtils.valueOf(rs.getObject("PRO_NAME")));
			entity.setProDesc(StringUtils.valueOf(rs.getObject("PRO_DESC")));
			entity.setProModel(StringUtils.valueOf(rs.getObject("PRO_MODEL")));
			entity.setProPrice(StringUtils.valueOf(rs.getObject("PRICE")));
			entity.setProStock(StringUtils.valueOf(rs.getObject("PRO_STOCK")));
			entity.setProType(StringUtils.valueOf(rs.getObject("PRO_TYPE")));
			entity.setProColor(StringUtils.valueOf(rs.getObject("PRO_COLOR")));
			entity.setSupplierId(StringUtils.valueOf(rs.getObject("SUPPLIER_ID")));
			entity.setSupplierName(StringUtils.valueOf(rs.getObject("supplierName")));
			entity.setHyProId(StringUtils.valueOf(rs.getObject("HY_PROID")));
			entity.setProTitle(StringUtils.valueOf(rs.getObject("PRO_TITLE")));
			entity.setIndustryCode(StringUtils.valueOf(rs.getObject("INDUSTRY")));
		}
		
		
		return entity;
	}

	/**
	 * 获取商户上架商品
	 * @param supplierId
	 * @return
	 */
	public List<MerProductEntity> queryMerProBySupplier(String supplierId){
		
		StringBuffer querySql = new StringBuffer(QUERY_SQL);
		
		querySql.append(" AND SUPPLIER_ID = " + supplierId + "");
		
		return ConnManager.executeQuery(querySql.toString(), this);
	}
	
	/**
	 * 根据商品ID获取商户商品
	 * @param supplierId
	 * @return
	 */
	public MerProductEntity queryMerProByProId(String proId){
		
		StringBuffer querySql = new StringBuffer(QUERY_SQL);
		
		querySql.append(" AND ID = " + proId + "");
		
		return ConnManager.singleQuery(querySql.toString(), this);
	}
	
}
