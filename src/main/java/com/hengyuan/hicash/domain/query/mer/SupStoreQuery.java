package com.hengyuan.hicash.domain.query.mer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.mer.SupStoreEntity;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 门店查询DAO-蓝领业务
 * @author Cary.Liu
 * @createDate 2016-01-12
 */
public class SupStoreQuery extends AbstractDAO<SupStoreEntity> {

	private static Logger logger = Logger.getLogger(SupStoreQuery.class);
	
	private static final String QUERY_SQL = "SELECT * FROM d_sup_store WHERE 1 = 1 ";
	
	@Override
	public SupStoreEntity mapping(ResultSet rs) throws SQLException {

		SupStoreEntity entity = null;
		
		if(rs != null){
			
			entity = new SupStoreEntity();
//			entity.setFrameNo(StringUtils.valueOf(rs.getObject("frameno")));
//			entity.setSupNo(StringUtils.valueOf(rs.getObject("sup_no")));
//			entity.setSupName(StringUtils.valueOf(rs.getObject("sup_name")));
//			entity.setUnitName(StringUtils.valueOf(rs.getObject("unit_name")));
//			entity.setStoreNo(StringUtils.valueOf(rs.getObject("store_no")));
//			entity.setStoreName(StringUtils.valueOf(rs.getObject("store_name")));
//			entity.setStatus(StringUtils.valueOf(rs.getObject("STATUS")));
			entity.setFrameno(StringUtils.valueOf(rs.getObject("frameno")));
			entity.setSupNo(StringUtils.valueOf(rs.getObject("sup_no")));
			entity.setSupName(StringUtils.valueOf(rs.getObject("sup_name")));
			entity.setUnitName(StringUtils.valueOf(rs.getObject("unit_name")));
			entity.setStoreNo(StringUtils.valueOf(rs.getObject("store_no")));
			entity.setStoreName(StringUtils.valueOf(rs.getObject("store_name")));
			entity.setCity(StringUtils.valueOf(rs.getObject("city")));
			entity.setStoreAdree(StringUtils.valueOf(rs.getObject("store_adree")));
			entity.setNextPath(StringUtils.valueOf(rs.getObject("next_path")));
			entity.setTopPath(StringUtils.valueOf(rs.getObject("top_path")));
			entity.setRoadNo(StringUtils.valueOf(rs.getObject("road_no")));
			entity.setOperatePower(StringUtils.valueOf(rs.getObject("operate_power")));
			entity.setOperateTime(StringUtils.valueOf(rs.getObject("operate_time")));
			entity.setLegalName(StringUtils.valueOf(rs.getObject("legal_name")));
			entity.setUnitPhone(StringUtils.valueOf(rs.getObject("unit_phone")));
			entity.setStatus(StringUtils.valueOf(rs.getObject("status")));
			
		}
		
		return entity;
	}
	
	/**
	 * 获取门店
	 * @param storeNo
	 * @return
	 */
	public SupStoreEntity querySupStoreByStoreNo(String storeNo){
		
		StringBuffer querySql = new StringBuffer(QUERY_SQL);
		
		querySql.append(" AND store_no = '" + storeNo + "'");
		
		RecordUtils.writeAction(logger, null, querySql.toString());
		return ConnManager.singleQuery(querySql.toString(), this);
	}
	
	/**
	 * 店名是否存在
	 * @param storeName
	 * @return
	 */
	public boolean isStoreNameExist(String storeName){
		
		StringBuffer querySql = new StringBuffer(QUERY_SQL);
		
		querySql.append(" AND store_name = '" + storeName + "'");
		
		RecordUtils.writeAction(logger, null, querySql.toString());
		List<SupStoreEntity> list = ConnManager.executeQuery(querySql.toString(), this);
		
		return (list != null && list.size() > 0) ? true : false;
	}
	
}
