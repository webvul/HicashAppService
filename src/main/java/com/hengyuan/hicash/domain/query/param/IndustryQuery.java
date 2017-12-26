package com.hengyuan.hicash.domain.query.param;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.param.IndustryEntity;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 查询行业DAO
 * @author Cary.Liu
 * @createDate 2015-05-19
 *
 */
public class IndustryQuery extends AbstractDAO<IndustryEntity> {

	private static final String QUERY_SQL = "SELECT HY_INDUSTRY_CODE,HY_INDUSTRY_NAME,HY_INDUSTRY_LOGIC_CODE,HY_INDUSTRY_PARENT_LOGIC_CODE FROM hy_industry_param WHERE 1 = 1 ";
	
	@Override
	public IndustryEntity mapping(ResultSet rs) throws SQLException {

		IndustryEntity entity = null;
		if(rs != null){
			
			entity = new IndustryEntity();
			entity.setIndustryCode(StringUtils.valueOf(rs.getObject("HY_INDUSTRY_CODE")));
			entity.setIndustryName(StringUtils.valueOf(rs.getObject("HY_INDUSTRY_NAME")));
			entity.setIndustryLogicCode(StringUtils.valueOf(rs.getObject("HY_INDUSTRY_LOGIC_CODE")));
			entity.setIndustryPrtLogicCode(StringUtils.valueOf(rs.getObject("HY_INDUSTRY_PARENT_LOGIC_CODE")));
		}
		
		return entity;
	}

	/**
	 * 根据行业代码获取行业
	 * @param industryCode
	 * @return
	 */
	public IndustryEntity queryIndustry(String industryCode){
		
		StringBuffer querySql = new StringBuffer(QUERY_SQL);
		querySql.append(" AND  HY_INDUSTRY_CODE = '"+ industryCode +"'");
		
		return ConnManager.singleQuery(querySql.toString(), this);
	}
	
	/**
	 * 根据行业逻辑代码获取行业
	 * @param industryLogicCode
	 * @return
	 */
	public IndustryEntity queryIndustryByLogicCode(String industryLogicCode){
		
		StringBuffer querySql = new StringBuffer(QUERY_SQL);
		querySql.append(" AND HY_INDUSTRY_LOGIC_CODE = '"+ industryLogicCode +"'");
		
		return ConnManager.singleQuery(querySql.toString(), this);
	}
	
}
