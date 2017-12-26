package com.hengyuan.hicash.domain.query.mktApp;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.mktApp.MerRebateEntity;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 商户商品返点比率查询
 * @author Cary.Liu
 * @createDate 2015-04-29
 *
 */
public class MerRebateQuery extends AbstractDAO<MerRebateEntity> {
	
	private static Logger logger = Logger.getLogger(MerRebateQuery.class);

	@Override
	public MerRebateEntity mapping(ResultSet rs) throws SQLException {

		MerRebateEntity entity = null;
		
		if(rs != null){
			
			entity = new MerRebateEntity();
			entity.setRebateFlag(rs.getBoolean("rebateFlag"));
			entity.setMerRpt(StringUtils.valueOf(rs.getObject("merRPT")));
			entity.setIndustryCode(StringUtils.valueOf(rs.getObject("industryCode")));
			entity.setSupplierRpt(StringUtils.valueOf(rs.getObject("supplierRPT")));
		}
		
		return entity;
	}

	public String queryRebateByProId(String merProId){
		
		String querySql = "SELECT a.IS_REBATE AS rebateFlag,a.REBATE_PERCENT AS merRPT,a.INDUSTRY AS industryCode,b.REBATE_PERCENT AS supplierRPT "
	   		 + " FROM mer_product_info AS a  "
	   		 + " LEFT JOIN  d_supplier_info AS b ON  a.SUPPLIER_ID = b.supplierid "
	   		 + " WHERE ID = " + merProId;
		
		RecordUtils.writeAction(logger, null, querySql);
		
		MerRebateEntity entity = ConnManager.singleQuery(querySql, this);
		if(entity != null){
			if(entity.isRebateFlag()){
				return entity.getMerRpt();
			}else{
				return entity.getSupplierRpt();
			}
		}
		
		return Consts.FINAL_NUMBER_0; 
	}
	
}
