package com.hengyuan.hicash.domain.query.mktApp;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.mktApp.HinsTempCredit;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 
* @author dong.liu 
* 2017-3-24 上午10:22:06
* 类说明:临时订单
 */
public class HinsTempCreditQuery extends AbstractDAO<HinsTempCredit> {
	
	private static Logger logger = Logger.getLogger(HinsTempCreditQuery.class);

	@Override
	public HinsTempCredit mapping(ResultSet rs) throws SQLException {
		
		HinsTempCredit vo = new HinsTempCredit();

		if (rs != null) {
			vo.setTemp_application_no(StringUtils.valueOf(rs.getObject("temp_application_no")));//
			vo.setUsername(StringUtils.valueOf(rs.getObject("username")));//
			vo.setCredit_type(StringUtils.valueOf(rs.getObject("credit_type")));//
			vo.setCreateapp_flag(StringUtils.valueOf(rs.getObject("createapp_flag")));//
			vo.setHy_industry_code(StringUtils.valueOf(rs.getObject("hy_industry_code")));
			vo.setHinsPid(StringUtils.valueOf(rs.getObject("hins_pid")));
		}
		return vo;
	}
	
	
	
	/**
	 * 根据appPayNo查询credit_temp_pay信息
	 */
	public HinsTempCredit queryTempCreditByAppId(String tempNo){
		
		String sql="SELECT temp_application_no,username,credit_type,createapp_flag,hy_industry_code,hins_pid " +
				"FROM credit_temp_pay WHERE temp_application_no='"+tempNo+"' ";
		
		RecordUtils.writeAction(logger, tempNo, sql);
		return ConnManager.singleQuery(sql, this);
		
	}
	
}
