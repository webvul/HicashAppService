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
import com.hengyuan.hicash.entity.user.DdsjLimit;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 
 * @author teng
 *
 */
public class DdsjLimitQuery extends AbstractDAO<DdsjLimit> {

	private static Logger logger = Logger.getLogger(DdsjLimitQuery.class);

	@Override
	public DdsjLimit mapping(ResultSet rs) throws SQLException {
		DdsjLimit bean = new DdsjLimit();
		if (rs != null) {
			bean.setUsername(StringUtils.valueOf(rs.getObject("username")));
			bean.setSx_mobile((StringUtils.valueOf(rs.getObject("sx_mobile"))));
			bean.setSx_mobile_password((StringUtils.valueOf(rs
					.getObject("sx_mobile_password"))));
			bean.setAmount((StringUtils.valueOf(rs.getObject("amount"))));
			bean.setLatelySx_success_date((StringUtils.valueOf(rs
					.getObject("latelySx_success_date"))));
			bean.setLatelyTe_success_date((StringUtils.valueOf(rs
					.getObject("latelyTe_success_date"))));
			bean.setSx_success_time((StringUtils.valueOf(rs
					.getObject("sx_success_time"))));
			bean.setIs_credit((StringUtils.valueOf(rs.getObject("is_credit"))));
			bean.setStatus((StringUtils.valueOf(rs.getObject("status"))));

		}else {
			bean = null;

		}
		return bean;
	}

	public DdsjLimit queryLimit(String userName) {

		List<String> selects = new ArrayList<String>();
		selects.add("username");
		selects.add("sx_mobile");
		selects.add("sx_mobile_password");
		selects.add("amount");
		selects.add("latelySx_success_date");
		selects.add("latelyTe_success_date");
		selects.add("sx_success_time");
		selects.add("is_credit");
		selects.add("status");

		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("username", userName);

		String querySql = MapAssemForSql.getSelectSql(selects,
				TableConsts.DDSJ_LIMIT, whereMap);
		
		// 记录日志
		RecordUtils.writeAction(logger, userName, querySql);
		return ConnManager.singleQuery(querySql.toString(), this);
	}

	/**
	 * 查询审核中和提额中的单子
	 * 
	 * @param userName
	 * @return
	 */
	public List<DdsjLimit> queryApplying(String userName) {

		String sql = "SELECT * FROM ddsj_limit WHERE 1 = 1 AND username = '"
				+ userName + "' AND STATUS in ('SXZ','TEZ')";

		RecordUtils.writeAction(logger, null, sql);
		return ConnManager.executeQuery(sql, this);
	}
	
	/**
	 * 查询最近一次提额时间
	 * @param userName
	 * @return
	 */
	public  List<DdsjLimit> querylatelyTeDate(String userName) {

		String sql = "SELECT * FROM ddsj_limit WHERE 1 = 1 AND username = '"
				+ userName + "'  and   latelyTe_success_date > DATE_ADD(NOW(), INTERVAL -1 MONTH)  ";

		RecordUtils.writeAction(logger, null, sql);
		return ConnManager.executeQuery(sql, this);
	}
		

}
