package com.hengyuan.hicash.domain.query.himoney;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.himoney.AuthenticationEntity;
import com.hengyuan.hicash.parameters.request.himoney.AuthenticationInfoReq;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 
 * @author xuexin
 * @create 2017年7月14日 14:44:23
 */
public class AuthenticationInfoQuery extends AbstractDAO<AuthenticationEntity> {
	
	private static Logger logger = Logger.getLogger(AuthenticationInfoQuery.class);

	@Override
	public AuthenticationEntity mapping(ResultSet rs) throws SQLException {
		AuthenticationEntity entity=new AuthenticationEntity();
		if (rs != null) {
			entity.setId(Integer.valueOf(StringUtils.valueOf(rs.getObject("id"))));
			entity.setName(StringUtils.valueOf(rs.getObject("name")));
			entity.setTitle(StringUtils.valueOf(rs.getObject("title")));
			entity.setQuota(StringUtils.valueOf(rs.getObject("quota")));
			entity.setScore(StringUtils.valueOf(rs.getObject("score")));
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
	public List<AuthenticationEntity> queryAuthenticationInfoByAppNoUserName(AuthenticationInfoReq req,boolean isXuexin){
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT a.id,a.name,a.title,a.quota,a.score,b.status FROM ");
		sql.append(" authentication_basics a LEFT JOIN ( SELECT b.auth_id,b.status FROM authentication_info b WHERE b.temp_App_No = '"+req.getTempAppNo()+"' AND b.userName = '"+req.getUserName()+"' ) b ON b.auth_id = a.id ");
		sql.append(" WHERE a.isDisable = 1 ");
		//如果用户年龄为　２２岁及２２岁以下则显示显示学信网信息
		if(isXuexin){
			sql.append(" AND a.id !=1 ");
		}
		sql.append("  GROUP BY a.id ");
		
		RecordUtils.writeAction(logger, req.getTempAppNo()+req.getUserName(), sql.toString());
		return ConnManager.executeQuery(sql.toString(), this);
		
	}
	
	/** 根据tempAppNo查询认证信息
	 * @param tempAppNo
	 * @return AuthenticationInfoResp
	 */
	public List<AuthenticationEntity> queryAuthenticationInfo(AuthenticationInfoReq req){
		
		String sql="SELECT * FROM authentication_info where temp_App_No = '"+req.getTempAppNo()+"' AND userName = '"+req.getUserName()+"' AND auth_id = "+req.getAuthId();
		
		RecordUtils.writeAction(logger, req.getTempAppNo()+req.getUserName(), sql);
		return ConnManager.executeQuery(sql, this);
		
	}

}
