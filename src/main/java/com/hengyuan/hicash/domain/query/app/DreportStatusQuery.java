package com.hengyuan.hicash.domain.query.app;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.app.DreportStatusEntity;
import com.hengyuan.hicash.entity.app.TempApplyEntity;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;


/**
 * 认证状态查询
* @author dong.liu 
* 2017-12-22 下午5:09:22
* 类说明
 */
public class DreportStatusQuery extends AbstractDAO<DreportStatusEntity> {

	private static Logger logger = Logger.getLogger(DreportStatusQuery.class);
	
	
	public static final String QUERY_SQL = "SELECT * FROM d_report_status WHERE 1 = 1 ";
	
	public DreportStatusEntity mapping(ResultSet rs) throws SQLException {

		DreportStatusEntity entity = null;
		if(rs != null){
			entity = new DreportStatusEntity();
			entity.setId(StringUtils.valueOf(rs.getObject("ID")));
			entity.setConfirm_date(StringUtils.valueOf(rs.getObject("confirm_date")));
			entity.setCreate_date(StringUtils.valueOf(rs.getObject("create_date")));
			entity.setTemp_appno(StringUtils.valueOf(rs.getObject("temp_appno")));
			entity.setType(StringUtils.valueOf(rs.getObject("type")));
		}
		
		return entity;
	}
	
     
     public DreportStatusEntity queryTempByTempNo(String tempAppNo){
 		
 		StringBuffer querySql = new StringBuffer(QUERY_SQL);
 		querySql.append(" AND temp_appno = '"+ tempAppNo +"'");
 		
 		RecordUtils.writeAction(logger, null, querySql.toString());
 		
 		return ConnManager.singleQuery(querySql.toString(), this);
 	}
     
   
 	   
}
