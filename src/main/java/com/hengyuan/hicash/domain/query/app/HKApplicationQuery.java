package com.hengyuan.hicash.domain.query.app;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 申请件信息查询
 * @author Cary.Liu
 * @create date 2014-07-26
 *
 */
public class HKApplicationQuery extends AbstractDAO<String> {
	
	private static Logger logger = Logger.getLogger(HKApplicationQuery.class);
	
	
	
	
	@Override
	public String mapping(ResultSet rs) throws SQLException {
		
		if (rs != null && rs.getObject("app_application_no") != "") {
			String appNO = StringUtils.valueOf(rs.getObject("app_application_no"));
			if(appNO != null && appNO != ""){
				return appNO;
			}
			return "";
		} 

		return "";
	}

	
	
	
	
	/**
	 * 获取还款中订单
	 * @param appNo
	 * @return
	 */
	public List<String> queryHkApp(String userName){
		String sql = "SELECT pay.app_application_no FROM d_application_pay AS pay WHERE pay.app_username = '"+userName+"' AND pay.ALLNODE = 'HKNODE'";
		RecordUtils.writeAction(logger, null, sql);
		return ConnManager.executeQuery(sql, this);
	}
	
}
