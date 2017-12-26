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
import com.hengyuan.hicash.entity.user.Coupon;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.StringUtils;

public class CouponQuery extends AbstractDAO<Coupon> {

	private List<String> lists = new ArrayList<String>();

	public CouponQuery() {
		lists.add("ID");
		lists.add("DISCOUNT");
	}

	@Override
	public Coupon mapping(ResultSet rs) throws SQLException {

		Coupon entity = null;

		if (rs != null) {
			entity = new Coupon();
			entity.setId(StringUtils.valueOf(rs.getObject("ID")));
			entity.setCoupon(StringUtils.valueOf(rs.getObject("DISCOUNT")));
		}

		return entity;
	}

	public Coupon queryCoupon(String userName) {

		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("ID", userName);

		String sql = MapAssemForSql.getSelectSql(lists, TableConsts.COUPON,
				whereMap);
		return ConnManager.singleQuery(sql, this);
	}

}
