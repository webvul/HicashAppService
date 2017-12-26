package com.hengyuan.hicash.domain.query.himoney;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.himoney.AuthenticationEntity;
import com.hengyuan.hicash.entity.himoney.AuthenticationInfoEntity;
import com.hengyuan.hicash.parameters.request.himoney.AuthenticationInfoReq;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 
 * @author xuexin
 * @create 2017年7月14日 14:44:23
 */
public class AuthenticationInfoTableQuery extends AbstractDAO<AuthenticationInfoEntity> {
	
	private static Logger logger = Logger.getLogger(AuthenticationInfoTableQuery.class);

	@Override
	public AuthenticationInfoEntity mapping(ResultSet rs) throws SQLException {
		AuthenticationInfoEntity entity=new AuthenticationInfoEntity();
		if (rs != null) {
			entity.setId(Integer.valueOf(StringUtils.valueOf(rs.getObject("id"))));
			entity.setStatus(StringUtils.valueOf(rs.getObject("status")));
		}else {
			entity=null;
		}
		return entity;
	}
	
	
	/** 根据tempAppNo查询认证信息
	 * @param tempAppNo
	 * @return AuthenticationInfoResp
	 */
	public List<AuthenticationInfoEntity> queryAuthenticationInfo(AuthenticationInfoReq req){
		
		String sql="SELECT * FROM authentication_info where temp_App_No = '"+req.getTempAppNo()+"' AND userName = '"+req.getUserName()+"' AND auth_id = "+req.getAuthId();
		
		RecordUtils.writeAction(logger, req.getTempAppNo()+req.getUserName(), sql);
		return ConnManager.executeQuery(sql, this);
		
	}
}
