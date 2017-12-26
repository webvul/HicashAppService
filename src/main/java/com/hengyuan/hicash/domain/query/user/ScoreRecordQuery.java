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
import com.hengyuan.hicash.entity.user.HyScoreRecord;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 
 * @author fish
 *
 * @date 2017年1月10日 下午8:07:25
 */
public class ScoreRecordQuery extends AbstractDAO<HyScoreRecord> {

	private static Logger logger = Logger.getLogger(ScoreRecordQuery.class);

	@Override
	public HyScoreRecord mapping(ResultSet rs) throws SQLException {

		HyScoreRecord bean = new HyScoreRecord();

		if (rs != null) {
			bean.setIdNo(StringUtils.valueOf(rs.getObject("id_no")));
			bean.setScore(StringUtils.valueOf(rs.getObject("score")));
			bean.setResult(StringUtils.valueOf(rs.getObject("result")));
			bean.setCreateDate(StringUtils.valueOf(rs.getObject("create_date")));
			bean.setThresholdHigh(StringUtils.valueOf(rs.getObject("threshold_high")));
			bean.setThresholdLow(StringUtils.valueOf(rs.getObject("threshold_low")));
		} else {
			bean = null;
		}
		return bean;
	}

	public HyScoreRecord selectByIdNo(String idNo) {

		List<String> selects = new ArrayList<String>();

		selects.add("id_no");
		selects.add("score");
		selects.add("result");
		selects.add("create_date");
		selects.add("threshold_high");
		selects.add("threshold_low");

		Map<String, Object> conMap = new HashMap<String, Object>();
		conMap.put("id_No", idNo);

		String custSql = MapAssemForSql.getSelectSql(selects, TableConsts.HY_SCORE_RECORD, conMap);
		RecordUtils.writeAction(logger, idNo, custSql);
		return ConnManager.singleQuery(custSql, this);

	}

}
