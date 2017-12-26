package com.hengyuan.hicash.domain.query.mer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.mer.SupplierEntity;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * 商户查询
 * @author Cary.liu
 *
 */
public class SupplierQuery extends AbstractDAO<SupplierEntity> {
	
	private static Logger logger = Logger.getLogger(SupplierQuery.class);

	private List<String> list;
	
	public SupplierQuery(){
		list = new ArrayList<String>();
		list.add("supplierid");
		list.add("supplier_name");
	}
	
	@Override
	public SupplierEntity mapping(ResultSet rs) throws SQLException {

		SupplierEntity entity = null;
			
		if(rs != null){
			
			entity = new SupplierEntity();
			entity.setSupplierId(rs.getString("supplierid"));
			entity.setSupplierName(rs.getString("supplier_name"));
			
		}
		
		return entity;
	}

	/**
	 * 根据ID查售点
	 * @param saleId
	 * @return
	 */
	public SupplierEntity querySaleSiteById(String supplierId){
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		
		whereMap.put("supplierid", supplierId);
		
		String sql = MapAssemForSql.getSelectSql(list, TableConsts.SUPPLIER, whereMap);
		
		return ConnManager.singleQuery(sql, this);
	}
	
	/**
	 * 根据用户ID查
	 * @param saleId
	 * @return
	 */
	public SupplierEntity queryMerById(String supplierId){
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		
		whereMap.put("supplierid", supplierId);
		
		String sql = MapAssemForSql.getSelectSql(list, TableConsts.SUPPLIER, whereMap);
		
		RecordUtils.writeAction(logger, null, sql);
		
		return ConnManager.singleQuery(sql, this);
	}
	
	/**
	 * 根据城市code
	 * @param saleId
	 * @return
	 */
	public List<SupplierEntity> queryMerByCity(String cityCode){
		String sql="select supplierid,supplier_name from d_supplier_info where city='"+cityCode+"' AND (mer_node NOT IN ('MERN01','MERN02') OR (mer_node ='MERN07' AND mer_status!='MERSTA06')) AND SUPPLIER_STATUS ='1' and SM_Flag=1";

//		Map<String, Object> whereMap = new HashMap<String, Object>();
//		
//		whereMap.put("city", cityCode);
//		whereMap.put("city", cityCode);//审核通过的商户
//		whereMap.put("city", cityCode);//数码产品
//		String sql = MapAssemForSql.getSelectSql(list, TableConsts.SUPPLIER, whereMap);
		 ConnManager.singleQuery(sql, this);
		RecordUtils.writeAction(logger, null, sql);
		
		return ConnManager.executeQuery(sql, this);
	}
}
