package com.hengyuan.hicash.domain.query.param;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.param.MerProductInfoEntity;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 查询商户商品
 * @author Cary.Liu
 * @createDate 2015-04-29
 */
public class MerProQuery extends AbstractDAO<MerProductInfoEntity> {

	private static final String QUERY_SQL = "SELECT ID,PRO_NAME FROM mer_product_info WHERE 1 = 1 ";
	
	@Override
	public MerProductInfoEntity mapping(ResultSet rs) throws SQLException {

		MerProductInfoEntity entity = null;
		
		if (rs != null) {

			entity = new MerProductInfoEntity();
			entity.setMerProId(StringUtils.valueOf(rs.getObject("ID")));
			entity.setMerProName(StringUtils.valueOf(rs.getObject("PRO_NAME")));
		}
		
		return entity;
	}

	public MerProductInfoEntity queryMerProById(String merProId){
		
		StringBuffer querySql = new StringBuffer(QUERY_SQL);
		querySql.append(" AND ID = " + merProId);
		
		return ConnManager.singleQuery(querySql.toString(), this);
	}
	
}
