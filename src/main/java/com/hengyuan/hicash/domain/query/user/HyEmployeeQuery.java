package com.hengyuan.hicash.domain.query.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.user.HyEmployeeEntity;
import com.hengyuan.hicash.utils.StringUtils;



/**
 * 员工查询
 * @author LiHua.Ren
 * @create date 2016-01-25
 *
 */
public class HyEmployeeQuery extends AbstractDAO<HyEmployeeEntity> {

	private static final String QUERY_SQL = "SELECT H_USERNAME,H_EMPLOYEE_REAL_NAME,H_AGENT_LOGIC_CODE,H_EMPLOYEE_NO,H_PROVINCE_ID,H_CITY_CODE FROM hy_employee WHERE 1 = 1 ";
	
	@Override
	public HyEmployeeEntity mapping(ResultSet rs) throws SQLException {

		HyEmployeeEntity entity = null;
		if(rs != null){
			
			entity = new HyEmployeeEntity();
			entity.setUserName(StringUtils.valueOf(rs.getObject("H_USERNAME")));
			entity.setRealName(StringUtils.valueOf(rs.getObject("H_EMPLOYEE_REAL_NAME")));
			entity.setEmployeeCode(StringUtils.valueOf(rs.getObject("H_EMPLOYEE_NO")));
			entity.setAgentLogicCode(StringUtils.valueOf(rs.getObject("H_AGENT_LOGIC_CODE")));
			entity.setProvince(StringUtils.valueOf(rs.getObject("H_PROVINCE_ID")));
			entity.setCity(StringUtils.valueOf(rs.getObject("H_CITY_CODE")));
		}
		
		return entity;
	}

	/**
	 * 根据员工号查员工
	 * @param saleCode
	 * @return
	 */
	public HyEmployeeEntity queryEmployeeByCode(String saleCode){
		
		StringBuffer querySql = new StringBuffer(QUERY_SQL);
		querySql.append(" AND H_EMPLOYEE_NO = '"+ saleCode +"'");
		System.out.println(querySql);
		return ConnManager.singleQuery(querySql.toString(), this);
	}
	
}
