package com.hengyuan.hicash.domain.query.notic;

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
import com.hengyuan.hicash.entity.notic.NoticeConfigure;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * 查询短信信息dao
 * 
 * @author Cary.Liu
 * @createDate 2014-07-22
 *
 */
public class NoticQuery extends AbstractDAO<NoticeConfigure>{
	private static Logger logger = Logger.getLogger(NoticQuery.class);
	private List<String> list;
	
	public NoticQuery(){
		list = new ArrayList<String>();
		list.add("SMS_FLAG");
		list.add("BUSS_NAME");
		list.add("SMS_SEND_TYPE");
		list.add("SMS_TEMPLATE");
		
	}
	
	@Override
	public NoticeConfigure mapping(ResultSet rs) throws SQLException {
		NoticeConfigure entity = new NoticeConfigure();
		if (rs != null) {
			entity.setSmsFlag(rs.getBoolean("SMS_FLAG"));
			entity.setBussName(rs.getString("BUSS_NAME"));
			entity.setSmsSendType(rs.getString("SMS_SEND_TYPE"));
			entity.setSmsTemplate(rs.getString("SMS_TEMPLATE"));
		}else {
			return null;
		}
		
		
		return entity;
	}
	
	/**
	 * 查询模板是否存在
	 * */
	public NoticeConfigure queryNoticeConfigure(String type) {
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		
		whereMap.put("BUSS_ID", type);
		
		String sql = MapAssemForSql.getSelectSql(list, TableConsts.NOTICE_CONFIGURE, whereMap);
		
		//记录日志
		RecordUtils.writeAction(logger, type, sql);
		
		return ConnManager.singleQuery(sql, this);
	}

}
