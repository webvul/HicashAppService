package com.hengyuan.hicash.domain.query.mktApp;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
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
public class TempCreditQuery extends AbstractDAO<TempCredit> {
	
	private static Logger logger = Logger.getLogger(TempCreditQuery.class);

	@Override
	public TempCredit mapping(ResultSet rs) throws SQLException {
		
		TempCredit vo = new TempCredit();

		if (rs != null) {
			vo.setTemp_application_no(StringUtils.valueOf(rs.getObject("temp_application_no")));//
			vo.setUsername(StringUtils.valueOf(rs.getObject("username")));//
			vo.setCredit_type(StringUtils.valueOf(rs.getObject("credit_type")));//
			vo.setCreateapp_flag(StringUtils.valueOf(rs.getObject("createapp_flag")));//
			vo.setDdsj_auth_password(StringUtils.valueOf(rs.getObject("ddsj_auth_password")));//
			vo.setDdsj_auth_phone(StringUtils.valueOf(rs.getObject("ddsj_auth_phone")));//
		}
		return vo;
	}
	
	
	
	/**
	 * 根据appPayNo查询ddsj_tempapply_credit信息
	 */
	public TempCredit queryTempCreditByAppId(String tempNo){
		
		String sql="SELECT temp_application_no,username,credit_type,createapp_flag, " +
		        "ddsj_auth_password,ddsj_auth_phone "+
				"FROM ddsj_tempapply_credit WHERE temp_application_no='"+tempNo+"' ";
		
		RecordUtils.writeAction(logger, tempNo, sql);
		return ConnManager.singleQuery(sql, this);
		
	}
	
}
