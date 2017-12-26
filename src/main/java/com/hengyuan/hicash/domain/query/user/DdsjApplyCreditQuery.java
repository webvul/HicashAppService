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
import com.hengyuan.hicash.entity.user.DdsjApplyCredit;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 
 * @author teng
 *
 */
public class DdsjApplyCreditQuery extends AbstractDAO<DdsjApplyCredit> {

	private static Logger logger = Logger.getLogger(DdsjApplyCreditQuery.class);

	@Override
	public DdsjApplyCredit mapping(ResultSet rs) throws SQLException {
		DdsjApplyCredit bean = new DdsjApplyCredit();
		if (rs != null) {
			bean.setApp_application_no((StringUtils.valueOf(rs
					.getObject("app_application_no"))));
			bean.setRealname((StringUtils.valueOf(rs.getObject("realname"))));
			bean.setDdsj_auth_phone((StringUtils.valueOf(rs
					.getObject("ddsj_auth_phone"))));
			bean.setMobile((StringUtils.valueOf(rs.getObject("mobile"))));
			bean.setIdentityno((StringUtils.valueOf(rs.getObject("identityno"))));
			bean.setUsername((StringUtils.valueOf(rs.getObject("username"))));
			bean.setSx_amount((StringUtils.valueOf(rs.getObject("sx_amount"))));
			bean.setStatus((StringUtils.valueOf(rs.getObject("status"))));
			bean.setNode((StringUtils.valueOf(rs.getObject("node"))));
			bean.setCreate_date((StringUtils.valueOf(rs
					.getObject("create_date"))));
			bean.setCredit_type((StringUtils.valueOf(rs
					.getObject("credit_type"))));
		}
		return bean;
	}

	public DdsjApplyCredit queryApplyCredit(String userName) {

		List<String> selects = new ArrayList<String>();
		selects.add("app_application_no");
		selects.add("realname");
		selects.add("ddsj_auth_phone");
		selects.add("mobile");
		selects.add("identityno");
		selects.add("username");
		selects.add("sx_amount");
		selects.add("status");
		selects.add("node");
		selects.add("create_date");
		selects.add("credit_type");

		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("username", userName);

		String querySql = MapAssemForSql.getSelectSql(selects,
				TableConsts.DDSJ_APPLY_CREDIT, whereMap);
		// 记录日志
		RecordUtils.writeAction(logger, userName, querySql);
		return ConnManager.singleQuery(querySql.toString(), this);
	}

	/**
	 * 查询一个月是否有被拒的单子
	 * 
	 * @param userName
	 * @return
	 */

	public List<DdsjApplyCredit> queryAppByBeforeMonth(String userName) {

		String sql = "SELECT app_application_no,realname,ddsj_auth_phone,mobile,identityno,sx_amount,status ,node ,username,create_date,credit_type  FROM ddsj_apply_credit WHERE 1 = 1 AND username = '"
				+ userName
				+ "' AND STATUS in ('STATUS21') AND CREATE_DATE > DATE_ADD(NOW(), INTERVAL -1 MONTH) AND credit_type = 'SX'";

		RecordUtils.writeAction(logger, null, sql);
		return ConnManager.executeQuery(sql, this);
	}

	
	/**
	 * 查询最新订单的状态
	 * 
	 * @param userName
	 * @return
	 */

	public DdsjApplyCredit queryAppByByuserName(String userName) {

		String sql = "SELECT app_application_no,realname,ddsj_auth_phone,mobile,identityno,sx_amount,status ,node ,username,create_date,credit_type  FROM ddsj_apply_credit WHERE 1 = 1 AND username = '"
				+ userName
				+ "' order by create_date desc limit 1 ";

		RecordUtils.writeAction(logger, null, sql);
		return ConnManager.singleQuery(sql, this);
	}

	
	/**
	 * 查询一个月内是有提额的单子
	 *  查询额度表最近提额成功时间
	 * @param userName
	 * @return
	 */

	public List<DdsjApplyCredit> queryLimitBeforeMonth(String userName) {

		String sql = "SELECT app_application_no,realname,ddsj_auth_phone,mobile,identityno,sx_amount,status ,node ,username,create_date,credit_type  FROM ddsj_apply_credit WHERE 1 = 1 AND username = '"
				+ userName
				+ "' AND CREATE_DATE > DATE_ADD(NOW(), INTERVAL -1 MONTH) AND credit_type = 'TE'";
		RecordUtils.writeAction(logger, null, sql);
		return ConnManager.executeQuery(sql, this);
	}

	public DdsjApplyCredit queryRefuseApp(String username) {
		String sql = "SELECT app_application_no,realname,ddsj_auth_phone,mobile,identityno,sx_amount,status ,node ,username,create_date,credit_type  FROM ddsj_apply_credit WHERE 1 = 1 AND username = '"
				+ username
				+ "' AND credit_type = 'SX' ORDER BY CREATE_DATE DESC LIMIT 1";

		RecordUtils.writeAction(logger, null, sql);
		return ConnManager.singleQuery(sql, this);
	}
	public DdsjApplyCredit queryQx(String username) {
		String sql = "SELECT app_application_no,realname,ddsj_auth_phone,mobile,identityno,sx_amount,status ,node ,username,create_date,credit_type  FROM ddsj_apply_credit WHERE 1 = 1 AND username = '"
				+ username
				+ "' AND credit_type = 'SX'   ORDER BY CREATE_DATE DESC LIMIT 1";

		RecordUtils.writeAction(logger, null, sql);
		return ConnManager.singleQuery(sql, this);
	}	
	
}
