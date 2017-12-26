package com.hengyuan.hicash.domain.query.param;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.param.ProShowEntity;
import com.hengyuan.hicash.parameters.request.param.ProShowDetailReq;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 产品展示详情 Dao
 * 
 * @author Cary.Liu
 * @createDate 2015-04-22
 *
 */
public class ProShowDetailQuery extends AbstractDAO<ProShowEntity> {

	private static Logger logger = Logger.getLogger(ProShowDetailQuery.class);
	
	@Override
	public ProShowEntity mapping(ResultSet rs) throws SQLException {

		ProShowEntity proShowEntity = null;
		
		if(rs != null){
			
			proShowEntity = new ProShowEntity();
//			proShowEntity.setProShowId(rs.getObject("ID"));
//			proShowEntity.setCityCode(rs.getObject("CITY_CODE"));
//			proShowEntity.setChannelId(rs.getObject("CHANNEL_ID"));
			proShowEntity.setMerProId(StringUtils.valueOf(rs.getObject("MER_PRO_ID")));
			proShowEntity.setProName(StringUtils.valueOf(rs.getObject("PRO_NAME")));
			proShowEntity.setProTitle(StringUtils.valueOf(rs.getObject("PRO_TITLE")));
			proShowEntity.setProDesc(StringUtils.valueOf(rs.getObject("PRO_DESC")));
			proShowEntity.setMonthly(StringUtils.valueOf(rs.getObject("MONTHLY")));
			proShowEntity.setSupplierName(StringUtils.valueOf(rs.getObject("supplier_name")));
			proShowEntity.setProType(StringUtils.valueOf(rs.getObject("PRO_TYPE")));
			proShowEntity.setMerType(StringUtils.valueOf(rs.getObject("supplier_Type")));
			proShowEntity.setSupplierCity(StringUtils.valueOf(rs.getObject("supplier_city")));
			proShowEntity.setHyProId(StringUtils.valueOf(rs.getObject("HY_PROID")));
			proShowEntity.setPrice(StringUtils.valueOf(rs.getObject("PRICE")));
			proShowEntity.setSupplierId(StringUtils.valueOf(rs.getObject("supplierid")));
			proShowEntity.setProvince(StringUtils.valueOf(rs.getObject("provice")));
			proShowEntity.setProClass(StringUtils.valueOf(rs.getObject("PRO_CLASS")));
			proShowEntity.setIndustryCode(StringUtils.valueOf(rs.getObject("INDUSTRY")));
		}
		
		return proShowEntity;
	}
	
	/**
	 * 获取展示商品
	 * @param proShowReq
	 * @return
	 */
	public ProShowEntity queryProShowList(ProShowDetailReq proShowReq){
		
		String querySql = "SELECT b.provice,a.PRO_TYPE,a.ID AS MER_PRO_ID,a.PRO_NAME,a.PRO_TYPE,a.PRO_TITLE,a.PRO_DESC,a.PRICE,a.PRO_CLASS,"
				 + " (FLOOR(a.PRICE/24)) AS MONTHLY,b.supplier_name,b.supplierid,b.supplier_Type,b.city AS supplier_city,a.HY_PROID,a.INDUSTRY "
				 + " FROM MER_PRODUCT_INFO a "
				 + " LEFT JOIN d_supplier_info b ON a.SUPPLIER_ID = b.supplierid "
				 + " WHERE 1 = 1 AND a.ID = " + proShowReq.getMerProId();
		
		RecordUtils.writeAction(logger, null, querySql);
		
		return ConnManager.singleQuery(querySql, this);
	}
	
}
