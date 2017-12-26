package com.hengyuan.hicash.domain.query.mktApp;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.mktApp.ApplyCredit;
import com.hengyuan.hicash.entity.mktApp.LoanLoan;
import com.hengyuan.hicash.entity.mktApp.TempCredit;
import com.hengyuan.hicash.entity.param.LoanStatus;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 
* @author dong.liu 
* 2017-3-24 上午10:22:06
* 类说明:临时订单
 */
public class ApplyCreditQuery extends AbstractDAO<ApplyCredit> {
	
	private static Logger logger = Logger.getLogger(ApplyCreditQuery.class);

	
	@Override
	public ApplyCredit mapping(ResultSet rs) throws SQLException {
		
		ApplyCredit vo = new ApplyCredit();

		if (rs != null) {
			vo.setAppNo(StringUtils.valueOf(rs.getObject("app_application_no")));
			vo.setNode(StringUtils.valueOf(rs.getObject("node")));
			vo.setStatus(StringUtils.valueOf(rs.getObject("status")));
			vo.setUsername(StringUtils.valueOf(rs.getObject("username")));
		}
		return vo;
	}
	
	
	
	/**
	 * 根据appPayNo查询ddsj_apply_credit信息
	 */
	public ApplyCredit queryApplyCredit(String appNo){
		
		String sql="SELECT app_application_no,status,node,username " +
				"FROM ddsj_apply_credit WHERE app_application_no='"+appNo+"' ";
		
		RecordUtils.writeAction(logger, appNo, sql);
		return ConnManager.singleQuery(sql, this);
		
	}
	
}
