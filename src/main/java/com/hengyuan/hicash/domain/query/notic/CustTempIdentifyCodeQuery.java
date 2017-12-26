package com.hengyuan.hicash.domain.query.notic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.notic.CustIdentifyCodeEntity;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 查询用户注册获取额度验证码发送DAO
 * @author Cary.Liu
 * @createDate 2015-04-21
 *
 */
public class CustTempIdentifyCodeQuery extends AbstractDAO<CustIdentifyCodeEntity> {

	private static final String QUERY_SQL = "SELECT USERNAME,MOBILENO,IDENTIFYCODE,SEND_IDENTIFYCODE_TIME,CREATE_TIME FROM CUST_TEMP_IDENTIFYCODE WHERE 1 = 1 ";
	
	@Override
	public CustIdentifyCodeEntity mapping(ResultSet rs) throws SQLException {

		CustIdentifyCodeEntity entity = null;
		
		if(rs != null){
			
			entity = new CustIdentifyCodeEntity();
			entity.setUserName(StringUtils.valueOf(rs.getObject("USERNAME")));
			entity.setMobileNo(StringUtils.valueOf(rs.getObject("MOBILENO")));
			entity.setIdentifyCode(StringUtils.valueOf(rs.getObject("IDENTIFYCODE")));
			entity.setValidateTime(StringUtils.valueOf(rs.getObject("SEND_IDENTIFYCODE_TIME")));
			entity.setCreateTime(StringUtils.valueOf(rs.getObject("CREATE_TIME")));
			
		}
		
		return entity;
	}
	
	public CustIdentifyCodeEntity queryTempCode(String userName){
		
		StringBuffer querySql = new StringBuffer(QUERY_SQL);
		querySql.append(" AND USERNAME = '"+ userName +"' ORDER BY CREATE_TIME DESC LIMIT 1");
		
		List<CustIdentifyCodeEntity> list = ConnManager.executeQuery(querySql.toString(), this);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	public CustIdentifyCodeEntity queryTempCodeByMobile(String mobileNo){
		
		StringBuffer querySql = new StringBuffer(QUERY_SQL);
		querySql.append(" AND MOBILENO = '"+ mobileNo +"' ORDER BY CREATE_TIME DESC LIMIT 1");
		
		List<CustIdentifyCodeEntity> list = ConnManager.executeQuery(querySql.toString(), this);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

}
