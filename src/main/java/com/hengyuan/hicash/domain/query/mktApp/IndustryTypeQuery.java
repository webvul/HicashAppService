package com.hengyuan.hicash.domain.query.mktApp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.mktApp.IndustryTypeEntity;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 获取行业类别DAO
 * @author Cary.Liu
 * @createDate 2015-04-28
 *
 */
public class IndustryTypeQuery extends AbstractDAO<IndustryTypeEntity> {

	private static final String QUERY_SQL = "SELECT a.HY_INDUSTRY_CODE,a.HY_INDUSTRY_NAME,b.CUSTOMER_CODE,b.UPLOAD_MODEL FROM hy_industry_param a "
			+ " JOIN hy_customer_industry b ON a.HY_INDUSTRY_CODE = b.INDUSTRY_CODE "
			+ " WHERE 1 = 1";

	
	
	@Override
	public IndustryTypeEntity mapping(ResultSet rs) throws SQLException {

		IndustryTypeEntity entity = null;
		
		if(rs != null){
			
			entity = new IndustryTypeEntity();
			String industryCode = StringUtils.valueOf(rs.getObject("HY_INDUSTRY_CODE"));
			entity.setIndustryCode(industryCode);
			entity.setIndustryName(StringUtils.valueOf(rs.getObject("HY_INDUSTRY_NAME")));
			entity.setCustType(StringUtils.valueOf(rs.getObject("CUSTOMER_CODE")));
			entity.setUploadModel(StringUtils.valueOf(rs.getObject("UPLOAD_MODEL")));
			if(Consts.ZZG_INDUSTRY_CODE.equals(industryCode)){
				entity.setPayType(Consts.APPFLOW_TYPE_CASH);
			}else{
				entity.setPayType(Consts.APPFLOW_TYPE_3C);
			}
		}
		
		return entity;
	}
	
	/**
	 * 获取指定客户类型下的二级行业
	 * @param custType
	 * @return
	 */
	public List<IndustryTypeEntity> queryIndustryList(String custType){
		
		StringBuffer querySql = new StringBuffer(QUERY_SQL);
		/* 二级行业，开放使用 */
		querySql.append(" AND a.HY_LEVEL = 2 AND a.STATUS = 1 AND b.STATUS = 1");
		/* 客户类型 */
		querySql.append(" AND b.CUSTOMER_CODE = '"+ custType +"'");
		
		
		return ConnManager.executeQuery(querySql.toString(), this);
	}
	
	/**
	 * 获取指定客户类型下的二级行业
	 * @param custType
	 * @return
	 */
	public List<IndustryTypeEntity> querySecondIndustryList(){
		
		StringBuffer querySql = new StringBuffer(QUERY_SQL);
		/* 二级行业，开放使用 */
		querySql.append(" AND a.HY_LEVEL = 2 AND a.STATUS = 1 AND b.STATUS = 1");
		
		return ConnManager.executeQuery(querySql.toString(), this);
	}
	
	/**
	 * 获取一级行业
	 * @return
	 */
	public List<IndustryTypeEntity> queryMerIndustryList(){
		
		String querySql = "SELECT HY_INDUSTRY_CODE,HY_INDUSTRY_NAME,'' AS CUSTOMER_CODE,'' AS UPLOAD_MODEL FROM hy_industry_param WHERE HY_LEVEL = 1 AND STATUS = 1";

		return ConnManager.executeQuery(querySql.toString(), this);
	}
	
	/**
	 * 指定客户类型是否允许申请指定行业
	 * @param custType
	 * @return
	 */
	public boolean queryIndustryByCust(String custType,String industryCode){
		
		StringBuffer querySql = new StringBuffer(QUERY_SQL);
		/* 二级行业，开放使用 */
		querySql.append(" AND a.HY_LEVEL = 2 AND a.STATUS = 1 AND b.STATUS = 1");
		/* 客户类型 /行业代码*/
		querySql.append(" AND b.CUSTOMER_CODE = '"+ custType +"' AND a.HY_INDUSTRY_CODE = '"+industryCode+"' ");
		
		IndustryTypeEntity entity = ConnManager.singleQuery(querySql.toString(), this);
		
		return entity != null ? true : false;
	}
	/**
	 * 指定客户类型是否允许申请指定行业
	 * @param custType
	 * @return
	 */
	public IndustryTypeEntity queryIndustryByIndustry(String custType,String industryCode){
		
		StringBuffer querySql = new StringBuffer(QUERY_SQL);
		/* 二级行业，开放使用 */
		querySql.append(" AND a.HY_LEVEL = 2 AND a.STATUS = 1 AND b.STATUS = 1");
		/* 客户类型 /行业代码*/
		querySql.append(" AND b.CUSTOMER_CODE = '"+ custType +"' AND a.HY_INDUSTRY_CODE = '"+industryCode+"' ");
		System.out.println(querySql+"mmmmm");
		IndustryTypeEntity entity = ConnManager.singleQuery(querySql.toString(), this);
		
		return entity;
	}
	
	/**
	 * 获取指定客户类型下的行业的上传模板
	 * @param custType
	 * @return
	 */
	public IndustryTypeEntity queryIndustryModel(String custType,String industryCode){
		
		StringBuffer querySql = new StringBuffer(QUERY_SQL);
		/* 二级行业，开放使用 */
		querySql.append(" AND b.CUSTOMER_CODE = '"+custType+"' AND a.HY_INDUSTRY_CODE = '"+industryCode+"'");
		
		return ConnManager.singleQuery(querySql.toString(), this);
	}

}
