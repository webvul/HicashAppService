package com.hengyuan.hicash.domain.query.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.user.DdsjLimit;
import com.hengyuan.hicash.entity.user.NSLimit;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

public class NSLimitQuery extends AbstractDAO<NSLimit>{

	@Override
	public NSLimit mapping(ResultSet rs) throws SQLException {
		NSLimit bean = new NSLimit();
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

	public NSLimit queryLimit(String userName) {

		List<String> selects = new ArrayList<String>();
		selects.add("username");
		selects.add("latelySx_success_date");
		selects.add("latelyTe_success_date");
		selects.add("sx_success_time");
		selects.add("is_credit");
		selects.add("status");
		selects.add("amount");

		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("username", userName);

		String querySql = MapAssemForSql.getSelectSql(selects,
				TableConsts.CREDIT_LIMIT, whereMap);
		
		// 记录日志
		RecordUtils.writeAction(logger, userName, querySql);
		return ConnManager.singleQuery(querySql.toString(), this);
	}
}
