package com.hengyuan.hicash.domain.query.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.user.ApproveuserBusiness;
import com.hengyuan.hicash.exception.ApproveBusinessException;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * @author Mary.Luo
 * 
 */
public class AproveBussQuery extends AbstractDAO<ApproveuserBusiness> {
	
	private static Logger logger = Logger.getLogger(AproveBussQuery.class);

	@Override
	public ApproveuserBusiness mapping(ResultSet rs) throws SQLException {
		ApproveuserBusiness approveuserBusiness = new ApproveuserBusiness();
		if (rs != null) {
			approveuserBusiness.setSaleCode(rs.getString("sale_code"));

		}
		return approveuserBusiness;
	}

	/**
	 * 根据schoolId查询开放城市信息
	 * @throws ApproveBusinessException 
	 */
	public ApproveuserBusiness getApproveuserBusinessBySchool(String schoolId) 
			throws ApproveBusinessException {

		String sql = "select sale_code from approve_user_business where school_id='"
				+ schoolId + "'";
		
		RecordUtils.writeAction(logger, schoolId, sql);
		ApproveuserBusiness approveuserBusiness = ConnManager.singleQuery(sql, this);;
		
		if(approveuserBusiness != null){
			return approveuserBusiness; 
		}else{
			throw new ApproveBusinessException(); 
		}

	}

}
