package com.hengyuan.hicash.domain.query.user;

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
import com.hengyuan.hicash.entity.user.CustCustomer;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 
 * @author fish
 *
 * @date 2017年1月11日 上午9:44:40
 */
public class RelationInfoQuery extends AbstractDAO<CustCustomer> {

	private static Logger logger = Logger.getLogger(RelationInfoQuery.class);

	@Override
	public CustCustomer mapping(ResultSet rs) throws SQLException {
		CustCustomer custCustomer = new CustCustomer();

		if (rs != null) {

			custCustomer.setUserName(StringUtils.valueOf(rs.getObject("USERNAME"))); // 用户名
			
			custCustomer.setMaritalStatus(StringUtils.valueOf(rs.getObject("MARITAL_STATUS"))); // 婚姻状况

			custCustomer.setImmediateName(StringUtils.valueOf(rs.getObject("IMMEDIATE_NAME"))); // 直系亲属姓名
			custCustomer.setImmediateRelation(StringUtils.valueOf(rs.getObject("IMMEDIATE_RELATION"))); // 直系亲属关系
			custCustomer.setImmediateMobile(StringUtils.valueOf(rs.getObject("IMMEDIATE_MOBILE"))); // 直系亲属手机号

			custCustomer.setEmergencyName(StringUtils.valueOf(rs.getObject("EMERGENCY_NAME"))); // 紧急联系人姓名
			custCustomer.setEmergencyRelation(StringUtils.valueOf(rs.getObject("EMERGENCY_RELATION"))); // 紧急联系人关系
			custCustomer.setEmergencyMobile(StringUtils.valueOf(rs.getObject("EMERGENCY_MOBILE"))); // 紧急联系人手机号

			custCustomer.setSpouseName(StringUtils.valueOf(rs.getObject("SPOUSE_NAME"))); // 配偶姓名
			custCustomer.setSpouseMobile(StringUtils.valueOf(rs.getObject("SPOUSE_MOBILE"))); // 配偶手机号

		} else {
			custCustomer = null;

		}
		return custCustomer;
	}

	/**
	 * 根据username查询客户信息
	 * 
	 * @param userName
	 * @return
	 */
	public CustCustomer queryCustCustomer(String userName) {

		List<String> selects = new ArrayList<String>();

		selects.add("USERNAME");
		selects.add("MARITAL_STATUS");
		selects.add("IMMEDIATE_NAME");
		selects.add("IMMEDIATE_RELATION");
		selects.add("IMMEDIATE_MOBILE");
		selects.add("EMERGENCY_NAME");
		selects.add("EMERGENCY_RELATION");
		selects.add("EMERGENCY_MOBILE");
		selects.add("SPOUSE_NAME");
		selects.add("SPOUSE_MOBILE");

		Map<String, Object> conMap = new HashMap<String, Object>();
		conMap.put("USERNAME", userName);

		String custSql = MapAssemForSql.getSelectSql(selects, TableConsts.CUST_CUSTOMER, conMap);
		RecordUtils.writeAction(logger, userName, custSql);
		return ConnManager.singleQuery(custSql, this);

	}

}
