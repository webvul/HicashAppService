package com.hengyuan.hicash.domain.query.himoney;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.himoney.LineCreditInfoEntity;
import com.hengyuan.hicash.parameters.request.himoney.AuthenticationInfoReq;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 
 * @author xuexin
 * @create 2017年7月14日 14:44:23
 */
public class LineCreditInfoQuery extends AbstractDAO<LineCreditInfoEntity> {
	
	private static Logger logger = Logger.getLogger(LineCreditInfoQuery.class);

	@Override
	public LineCreditInfoEntity mapping(ResultSet rs) throws SQLException {
		LineCreditInfoEntity entity=new LineCreditInfoEntity();
		if (rs != null) {
			entity.setId(Integer.valueOf(StringUtils.valueOf(rs.getObject("id"))) );
			entity.setAppNo(StringUtils.valueOf(rs.getObject("app_no")));
			entity.setTotalQuota(Integer.valueOf(StringUtils.valueOf(rs.getObject("total_quota"))) );
			entity.setTotalScore(Integer.valueOf(StringUtils.valueOf(rs.getObject("total_score"))) );
		}else {
			entity=null;
		}
		return entity;
	}
	
	/** 根据UserName查询认证信息
	 * @param UserName
	 * @return AuthenticationInfoResp
	 */
	public List<LineCreditInfoEntity> queryLineCreditInfo(AuthenticationInfoReq req,boolean isXuexin){
		StringBuffer sql = new StringBuffer();
		sql.append(" select a.id,a.app_no,a.total_quota,a.total_score from");
		sql.append(" line_credit_info a where");
		if(isXuexin){
			sql.append(" a.app_no in('2','"+req.getTempAppNo()+"') ORDER BY a.id DESC LIMIT 1");
		}else{
			sql.append(" a.app_no in('1','"+req.getTempAppNo()+"') ORDER BY a.id DESC LIMIT 1");
		}
		
		RecordUtils.writeAction(logger, req.getUserName(), sql.toString());
		return ConnManager.executeQuery(sql.toString(), this);
		
	}
	
}
