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
import com.hengyuan.hicash.entity.user.HinsLimit;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 
* @author dong.liu 
* 2017-5-10 下午8:21:02
* 类说明
 */
public class HinsLimitQuery extends AbstractDAO<HinsLimit> {

	private static Logger logger = Logger.getLogger(HinsLimitQuery.class);

	@Override
	public HinsLimit mapping(ResultSet rs) throws SQLException {
		HinsLimit bean = new HinsLimit();
		if (rs != null) {
			bean.setUsername(StringUtils.valueOf(rs.getObject("username")));
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

	public HinsLimit queryLimit(String userName) {

		List<String> selects = new ArrayList<String>();
		selects.add("username");
		selects.add("amount");
		selects.add("latelySx_success_date");
		selects.add("latelyTe_success_date");
		selects.add("sx_success_time");
		selects.add("is_credit");
		selects.add("status");

		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("username", userName);

		String querySql = MapAssemForSql.getSelectSql(selects,
				TableConsts.CREDIT_LIMIT, whereMap);
		
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
	public List<HinsLimit> queryApplying(String userName) {

		String sql = "SELECT * FROM "+TableConsts.CREDIT_LIMIT+" WHERE 1 = 1 AND username = '"
				+ userName + "' AND STATUS in ('SXZ','TEZ')";

		RecordUtils.writeAction(logger, null, sql);
		return ConnManager.executeQuery(sql, this);
	}
	
	/**
	 * 查询最近一次提额时间
	 * @param userName
	 * @return
	 */
	public  List<HinsLimit> querylatelyTeDate(String userName) {

		String sql = "SELECT * FROM "+TableConsts.CREDIT_LIMIT+" WHERE 1 = 1 AND username = '"
				+ userName + "'  and   latelyTe_success_date > DATE_ADD(NOW(), INTERVAL -1 MONTH)  ";

		RecordUtils.writeAction(logger, null, sql);
		return ConnManager.executeQuery(sql, this);
	}
		

}
