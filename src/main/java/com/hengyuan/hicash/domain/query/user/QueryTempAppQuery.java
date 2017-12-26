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
import com.hengyuan.hicash.parameters.response.user.QuerySingleImgResp;
import com.hengyuan.hicash.parameters.response.user.UpdateTempAppInfoResp;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * @author Administrator
 *
 */
public class QueryTempAppQuery extends AbstractDAO<UpdateTempAppInfoResp> {

	private static Logger logger = Logger.getLogger(UpdateTempAppInfoResp.class);

	@Override
	public UpdateTempAppInfoResp mapping(ResultSet rs) throws SQLException {
		
		UpdateTempAppInfoResp resp = new UpdateTempAppInfoResp();
		if (rs != null) {

			resp.setValidateTypea(StringUtils.valueOf(rs
					.getObject("validate_typea")));
			resp.setValidateType(StringUtils.valueOf(rs
					.getObject("validate_type")));
			

		} else {
			return null;
		}
		return resp;
	}

	public UpdateTempAppInfoResp querySingleResp(String appNo) {

		List<String> list = new ArrayList<String>();


		list.add("validate_typea");
		list.add("validate_type");
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("temp_application_no", appNo);
		String sql = MapAssemForSql.getSelectSql(list,
				TableConsts.TEMP_APPLY_INFO, map);
		// 记录日志
		RecordUtils.writeAction(logger, appNo, sql);
		return ConnManager.singleQuery(sql, this);
	}
	//司机贷
	public UpdateTempAppInfoResp queryTempSingleResp(String appNo) {

		List<String> list = new ArrayList<String>();


		list.add("validate_typea");
		list.add("validate_type");
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("temp_application_no", appNo);
		String sql = MapAssemForSql.getSelectSql(list,
				TableConsts.DDSJ_TEMPAPPLY_INFO, map);
		// 记录日志
		RecordUtils.writeAction(logger, appNo, sql);
		return ConnManager.singleQuery(sql, this);
	}
	//嗨女神
	public UpdateTempAppInfoResp queryNSSingleResp(String appNo) {

		List<String> list = new ArrayList<String>();


		list.add("validate_typea");
		list.add("validate_type");
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("temp_application_no", appNo);
		String sql = MapAssemForSql.getSelectSql(list,
				TableConsts.CREDIT_TEMP_PAY, map);
		// 记录日志
		RecordUtils.writeAction(logger, appNo, sql);
		return ConnManager.singleQuery(sql, this);
	}
}
