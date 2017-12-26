package com.hengyuan.hicash.domain.query.amount;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.account.InvestorsEntity;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

public class QueryInvestorsQuery extends AbstractDAO<InvestorsEntity> {

	private static Logger logger = Logger.getLogger(QueryInvestorsQuery.class);
	
	@Override
	public InvestorsEntity mapping(ResultSet rs) throws SQLException {

		InvestorsEntity investorsEntity = new InvestorsEntity();
		
		
		if(rs!=null){
			investorsEntity.setId(StringUtils.valueOf(rs.getObject("id")));
			investorsEntity.setApplyEnd(StringUtils.valueOf(rs.getObject("APPLY_END")));
			investorsEntity.setApplyStart(StringUtils.valueOf(rs.getObject("APPLY_START")));
			investorsEntity.setEndDate(StringUtils.valueOf(rs.getObject("END_DATE")));
			investorsEntity.setInversterName(StringUtils.valueOf(rs.getObject("INVERSTER_NAME")));
			investorsEntity.setMonthAmtLimit(StringUtils.valueOf(rs.getObject("MONTH_AMT_LIMIT")));
			investorsEntity.setMonthCountLimit(StringUtils.valueOf(rs.getObject("MONTH_COUNT_LIMIT")));
			investorsEntity.setStartDate(StringUtils.valueOf(rs.getObject("START_DATE")));
			investorsEntity.setTotalAmtLimit(StringUtils.valueOf(rs.getObject("TOTAL_AMT_LIMIT")));
			investorsEntity.setTotalCountLimit(StringUtils.valueOf(rs.getObject("TOTAL_COUNT_LIMIT")));
			investorsEntity.setAccountId(StringUtils.valueOf(rs.getObject("ACCOUNT_ID")));
		}else{
			return null;
		}

		return investorsEntity;
	}

	/**
	 * 
	 * @param uuid
	 * @param loanProductId
	 * @return
	 */
	public InvestorsEntity queryRuleInvest(String uuid,String loanProductId){
		String sql ="SELECT id,INVERSTER_NAME,APPLY_START,APPLY_END,TOTAL_COUNT_LIMIT,TOTAL_AMT_LIMIT,MONTH_COUNT_LIMIT,MONTH_AMT_LIMIT,START_DATE,END_DATE,ACCOUNT_ID FROM hy_rule_invest WHERE STATUS = 1 AND LOANPRODUCT_ID = "+loanProductId+" ORDER BY DISPLAY DESC";
		RecordUtils.writeAction(logger, uuid, sql);
		return ConnManager.singleQuery(sql, this);
	
	}
	
	/**
	 * 
	 * @param uuid
	 * @param loanProductId
	 * @return
	 */
	public InvestorsEntity queryDefaultRuleInvest(String uuid){
		String sql ="SELECT id,INVERSTER_NAME,APPLY_START,APPLY_END,TOTAL_COUNT_LIMIT,TOTAL_AMT_LIMIT,MONTH_COUNT_LIMIT,MONTH_AMT_LIMIT,START_DATE,END_DATE,ACCOUNT_ID FROM hy_rule_invest WHERE STATUS = 1 AND LOANPRODUCT_ID = 0 ";
		RecordUtils.writeAction(logger, uuid, sql);
		return ConnManager.singleQuery(sql, this);
	
	}
	
	
	public List<InvestorsEntity> queryRuleInvestlist(String uuid){
		String sql = "SELECT id,INVERSTER_NAME,APPLY_START,APPLY_END,TOTAL_COUNT_LIMIT,TOTAL_AMT_LIMIT,MONTH_COUNT_LIMIT,MONTH_AMT_LIMIT,START_DATE,END_DATE,ACCOUNT_ID FROM hy_rule_invest ORDER BY DISPLAY";
		RecordUtils.writeAction(logger, uuid, sql);
		return ConnManager.executeQuery(sql, this);
	}
	

}
