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
import com.hengyuan.hicash.entity.user.DeviceInfo;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;
/**
 * 
* @author dong.liu 
* 2017-4-9 下午4:25:38
* 类说明
 */
public class DeviceInfoQuery extends AbstractDAO<DeviceInfo> {

	private static Logger logger = Logger.getLogger(DeviceInfoQuery.class);

	@Override
	public DeviceInfo mapping(ResultSet rs) throws SQLException {
		DeviceInfo bean = new DeviceInfo();
		if (rs != null) {
		
			bean.setUsername(StringUtils.valueOf(rs.getObject("username")));
			bean.setApp_no(StringUtils.valueOf(rs.getObject("app_no")));//
			bean.setSb_system(StringUtils.valueOf(rs.getObject("sb_system")));
			bean.setSb_type(StringUtils.valueOf(rs.getObject("sb_type")));
			bean.setUdid(StringUtils.valueOf(rs.getObject("udid")));
			bean.setType(StringUtils.valueOf(rs.getObject("type")));
			
		}
		return bean;
	}

	public DeviceInfo queryApplyCredit(String app_no) {

		List<String> selects = new ArrayList<String>();
		selects.add("username");
		selects.add("app_no");
		selects.add("type");
		selects.add("sb_system");
		selects.add("sb_type");
		selects.add("udid");
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("app_no", app_no);

		String querySql = MapAssemForSql.getSelectSql(selects,
				TableConsts.CUST_DEVICE, whereMap);
		// 记录日志
		RecordUtils.writeAction(logger, app_no, querySql);
		return ConnManager.singleQuery(querySql.toString(), this);
	}

	

}
