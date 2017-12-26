package com.hengyuan.hicash.domain.query.himoney;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.himoney.AuthenticationBasicsEntity;
import com.hengyuan.hicash.entity.himoney.AuthenticationEntity;
import com.hengyuan.hicash.parameters.request.himoney.AuthenticationInfoReq;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 
 * @author xuexin
 * @create 2017年7月14日 14:44:23
 */
public class AuthenticationBasicsQuery extends AbstractDAO<AuthenticationBasicsEntity> {
	
	private static Logger logger = Logger.getLogger(AuthenticationBasicsQuery.class);

	@Override
	public AuthenticationBasicsEntity mapping(ResultSet rs) throws SQLException {
		AuthenticationBasicsEntity entity = new AuthenticationBasicsEntity();
		if (rs != null) {
			entity.setId(Integer.valueOf(StringUtils.valueOf(rs.getObject("id"))));
			entity.setName(StringUtils.valueOf(rs.getObject("name")));
			entity.setTitle(StringUtils.valueOf(rs.getObject("title")));
			entity.setQuota(Integer.valueOf(StringUtils.valueOf(rs.getObject("quota"))));
			entity.setScore(Integer.valueOf(StringUtils.valueOf(rs.getObject("score"))));
		}else {
			entity=null;
		}
		return entity;
	}
	
	/** 根据AuthId查询认证信息
	 * @param AuthId
	 * @return AuthenticationInfoResp
	 */
	public AuthenticationBasicsEntity queryApplicationPayById(AuthenticationInfoReq req){
		
		String sql="select * from  authentication_basics where isDisable = 1 AND id = "+req.getAuthId();
		
		RecordUtils.writeAction(logger, req.getTempAppNo()+req.getUserName(), sql);
		return ConnManager.executeQuery(sql, this).get(0);
	}
	
}
