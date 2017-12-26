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
import com.hengyuan.hicash.entity.user.DdsjLimitRecord;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;

public class DdsjLimitRecordQuery extends AbstractDAO<DdsjLimitRecord>{

	private static Logger logger = Logger.getLogger(DdsjLimitRecordQuery.class);
	@Override
	public DdsjLimitRecord mapping(ResultSet rs) throws SQLException {
		DdsjLimitRecord ddsjLimitRecord = null ;
		if(rs!=null){
			ddsjLimitRecord = new DdsjLimitRecord();
			ddsjLimitRecord.setAmount(rs.getDouble("amount"));
			ddsjLimitRecord.setOld_amount(rs.getDouble("old_amount"));
			ddsjLimitRecord.setUsername(rs.getString("username"));
			ddsjLimitRecord.setType(rs.getString("type"));
			ddsjLimitRecord.setCreate_time(rs.getString("create_time"));
		}
		return ddsjLimitRecord;
	}

	public DdsjLimitRecord queryDdsjLimitRecord(String userName){
		List<String> selects = new ArrayList<String>();
		selects.add("username");
		selects.add("amount");
		selects.add("old_amount");
		selects.add("type");
		selects.add("create_time");
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("username", userName);

		String querySql = MapAssemForSql.getSelectSql(selects,
				TableConsts.DDSJ_LIMIT_RECORD, whereMap);
		// 记录日志
		RecordUtils.writeAction(logger, userName, querySql);
		return ConnManager.singleQuery(querySql.toString(), this);
	}
}
