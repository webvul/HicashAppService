package com.hengyuan.hicash.domain.query.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ExceptionMsg;
import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.user.QueryAuthEntity;
import com.hengyuan.hicash.exception.SaveDeviceException;
import com.hengyuan.hicash.parameters.request.user.QueryAuthReq;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

public class SaveReserveNum extends AbstractDAO<QueryAuthEntity>{

	private static Logger logger = Logger.getLogger(SaveReserveNum.class);
	private String uuid;
	
	private List<String> lists = new ArrayList<String>();
	
	public SaveReserveNum() {
		lists.add("username");
		lists.add("reserve_number");
		lists.add("reserve_password");
		
	}
	@Override
	public QueryAuthEntity mapping(ResultSet rs) throws SQLException {
		QueryAuthEntity queryAuthEntity = null;
		if(rs!=null){
			queryAuthEntity = new QueryAuthEntity();
			
			queryAuthEntity.setReserveNumber(StringUtils.valueOf(rs.getObject("username")));
			queryAuthEntity.setReserveNumber(StringUtils.valueOf(rs.getObject("reserve_number")));
			queryAuthEntity.setReservePassword(StringUtils.valueOf(rs.getObject("reserve_password")));
			
		}
		return queryAuthEntity;
	}
	public void saveReserveNumber(QueryAuthReq queryAuthReq) throws SaveDeviceException{
		Map<String, Object> setMap = new HashMap<String, Object>();
		
		setMap.put("username", queryAuthReq.getUserName());
		
			setMap.put("reserve_number", queryAuthReq.getReserveNumber());
			setMap.put("reserve_password", queryAuthReq.getReservePassword());
		String updateSql = MapAssemForSql.getInsertSql(TableConsts.DDSJ_RESERVE_NUMBER,setMap);

		RecordUtils.writeAction(logger, uuid, updateSql);
		if (ConnManager.executeUpdate(updateSql) <= 0) {
			throw new SaveDeviceException(ExceptionMsg.SAVE_INFO_EXCEPTION);
		}	
	}
	
	public QueryAuthEntity queryReserveNumber(String userName) {
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("username", userName);
		
		String sql = MapAssemForSql.getSelectSql(lists, TableConsts.DDSJ_RESERVE_NUMBER, whereMap);
		logger.info("通过用户名查询预留司机账号");
		return ConnManager.singleQuery(sql, this);

	}
	public void updateReserveNumber(QueryAuthReq queryAuthReq) throws SaveDeviceException{
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		
		whereMap.put("username", queryAuthReq.getUserName());
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		
		setMap.put("reserve_number", queryAuthReq.getReserveNumber());
		setMap.put("reserve_password", queryAuthReq.getReservePassword());
		String updateSql = MapAssemForSql.getUpdateSql(TableConsts.DDSJ_RESERVE_NUMBER,setMap,whereMap);

		RecordUtils.writeAction(logger, uuid, updateSql);
		if (ConnManager.executeUpdate(updateSql) <= 0) {
			throw new SaveDeviceException(ExceptionMsg.SAVE_INFO_EXCEPTION);
		}
	}
}
