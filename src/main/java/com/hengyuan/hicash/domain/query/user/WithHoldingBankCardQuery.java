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
import com.hengyuan.hicash.parameters.response.user.WithHoldingBankCardResp;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 查询申请件的代扣银行卡
 * 
 * @author lihua.Ren
 * @create date 2015-12-03
 *
 */
public class WithHoldingBankCardQuery extends
		AbstractDAO<WithHoldingBankCardResp> {

	private static Logger logger = Logger
			.getLogger(WithHoldingBankCardQuery.class);

	@Override
	public WithHoldingBankCardResp mapping(ResultSet rs) throws SQLException {

		WithHoldingBankCardResp resp = new WithHoldingBankCardResp();
		if (rs != null) {
			resp.setBankCard(StringUtils.valueOf(rs.getObject("PROXY_BANKCARD")));
			resp.setMobileNo(StringUtils.valueOf(rs.getObject("mobileno")));
			resp.setOpenBank(StringUtils.valueOf(rs.getObject("PROXY_OPENBANK")));
			resp.setIdentifyNo(StringUtils.valueOf(rs.getObject("identityno")));
			resp.setRealName(StringUtils.valueOf(rs.getObject("REALNAME")));//是否认证通过
			resp.setProxyFrom(StringUtils.valueOf(rs.getObject("PROXY_FROM")));
		} else {
			return null;
		}
		return resp;
	}

	public WithHoldingBankCardResp queryBankMobile(String appNo) {

		List<String> list = new ArrayList<String>();

		list.add("PROXY_BANKCARD");
		list.add("PROXY_OPENBANK");
		list.add("mobileno");
		list.add("identityno");
		list.add("REALNAME");
		list.add("PROXY_FROM");
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("applicationNo", appNo);

		String sql = MapAssemForSql.getSelectSql(list,
				TableConsts.INPUT_APP, map);
		// 记录日志
		RecordUtils.writeAction(logger, appNo, sql);
		return ConnManager.singleQuery(sql, this);
	}

}
