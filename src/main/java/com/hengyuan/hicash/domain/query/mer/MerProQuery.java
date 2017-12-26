package com.hengyuan.hicash.domain.query.mer;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.param.MerProductInfoEntity;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

public class MerProQuery extends AbstractDAO<MerProductInfoEntity> {

	
	private static Logger logger = Logger.getLogger(MerProQuery.class);

	@Override
	public MerProductInfoEntity mapping(ResultSet rs) throws SQLException {

		if (rs != null) {

			MerProductInfoEntity rateEntity = new MerProductInfoEntity();
			rateEntity.setIsRebate(StringUtils.valueOf(rs.getObject("rebateFlag")));
			rateEntity.setIndustryCode(StringUtils.valueOf(rs.getObject("industryCode")));
			rateEntity.setMerProductRPT(StringUtils.valueOf(rs.getObject("merRPT")));
			rateEntity.setSupplierRPT(StringUtils.valueOf(rs.getObject("supplierRPT")));
			return rateEntity;
		} else {
			System.out.println("查询的结果为空");
			return null;
		}

	}

  /**
   * 根据商户id得到行业代码和返点比例
   * @param merid
   * @param uuid
   * @return
   */
   public MerProductInfoEntity  queryMerProductByid(String merid,String uuid){
	   String sql ="SELECT a.IS_REBATE AS rebateFlag,a.REBATE_PERCENT AS merRPT,a.INDUSTRY AS industryCode,b.REBATE_PERCENT AS supplierRPT " +
	   		" FROM mer_product_info AS a  " +
	   		" LEFT JOIN  d_supplier_info AS b ON  a.SUPPLIER_ID = b.supplierid " +
	   		" WHERE id = "+merid+"";
		RecordUtils.writeAction(logger, uuid, sql);
	  return ConnManager.singleQuery(sql, this);
   }
	
}
