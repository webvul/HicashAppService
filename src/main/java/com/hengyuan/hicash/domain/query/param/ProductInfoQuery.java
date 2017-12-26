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
import com.hengyuan.hicash.entity.param.ProductInfoEntity;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.StringUtils;

public class ProductInfoQuery extends AbstractDAO<ProductInfoEntity> {
	
	private List<String> lists = new ArrayList<String>();

	public ProductInfoQuery() {
		lists.add("pro_id");
		lists.add("pro_product_name");
		
		lists.add("pro_product_discribtion");
		lists.add("pro_product_type");
		lists.add("pro_product_no");
	}
	
	@Override
	public ProductInfoEntity mapping(ResultSet rs) throws SQLException {

		if (rs != null) {

			ProductInfoEntity productInfoEntity = new ProductInfoEntity();

			productInfoEntity.setProductId(StringUtils.valueOf(rs.getObject("pro_id")));
			productInfoEntity.setProductName(StringUtils.valueOf(rs.getObject("pro_product_name")));
			
			productInfoEntity.setProductDiscribtion(StringUtils.valueOf(rs.getObject("pro_product_discribtion")));
			productInfoEntity.setProductType(StringUtils.valueOf(rs.getObject("pro_product_type")));
			productInfoEntity.setProductNo(StringUtils.valueOf(rs.getObject("pro_product_no")));

			return productInfoEntity;
		} 
		
		return null;

	}

	/**
	 * 根据产品ID获取产品名称
	 * 
	 * @return
	 */
	public String queryProNameById(String productId) {

		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("pro_id", productId);
		
		String sql = MapAssemForSql.getSelectSql(lists, TableConsts.PRODUCT_INFO, whereMap);
		ProductInfoEntity entity = ConnManager.singleQuery(sql, this);
		if(entity != null){
			return entity.getProductName();
		}
		
		return null;
	}
	
	/**
	 * 根据销售码查询ApproveUser表
	 */
	public ProductInfoEntity queryProductInfoById(String proId) {

		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("pro_id", proId);
		String sql = MapAssemForSql.getSelectSql(lists, TableConsts.PRODUCT_INFO, whereMap);
		
		return ConnManager.singleQuery(sql, this);
	}
}
