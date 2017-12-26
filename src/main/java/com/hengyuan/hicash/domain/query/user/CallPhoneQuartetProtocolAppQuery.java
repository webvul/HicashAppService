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
import com.hengyuan.hicash.parameters.request.user.CallPhoneQuartetProtocolMsgReq;
import com.hengyuan.hicash.parameters.response.user.CallPhoneQuartetProtocolAppResp;
import com.hengyuan.hicash.parameters.response.user.CallPhoneQuartetProtocolMsgResp;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * @author Administrator
 *
 */
public class CallPhoneQuartetProtocolAppQuery extends
		AbstractDAO<CallPhoneQuartetProtocolAppResp> {

	// /** 根据AppNo查询申请表
	// * @param applicationNo
	// * @return ApplicationPay
	// */
	private static Logger logger = Logger
			.getLogger(CallPhoneQuartetProtocolMsgQuery.class);

	@Override
	public CallPhoneQuartetProtocolAppResp mapping(ResultSet rs) throws SQLException {
		CallPhoneQuartetProtocolAppResp resp = new CallPhoneQuartetProtocolAppResp();
		if(rs != null){

	        resp.setApplyAmount(StringUtils.valueOf(rs.getObject("APPLY_AMOUNT")));
			resp.setProAmount(StringUtils.valueOf(rs.getObject("tranprice")));
			resp.setLoanProductId(StringUtils.valueOf(rs.getObject("app_creditproduct_id")));
			resp.setProType(StringUtils.valueOf(rs.getObject("hy_industry_code")));
			
		}else{
			return null;
		}
		return resp;
	}

	public CallPhoneQuartetProtocolAppResp queryCallPhoneQuartet(
			CallPhoneQuartetProtocolMsgReq req) {

		List<String> list = new ArrayList<String>();

		list.add("app_creditproduct_id");
		list.add("tranprice");
		list.add("hy_industry_code");
		list.add("APPLY_AMOUNT");
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("app_application_no", req.getAppNo());

		String sql = MapAssemForSql.getSelectSql(list,
				TableConsts.APPLICATION_PAY, map);
		// 记录日志
		RecordUtils.writeAction(logger, req.getAppNo(), sql);
		return ConnManager.singleQuery(sql, this);
	}

}
