package com.hengyuan.hicash.domain.query.amount;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.account.RuleInvestorsEntity;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

public class RuleInvestorsQuery extends AbstractDAO<RuleInvestorsEntity> {
	
	private static Logger logger = Logger.getLogger(RuleInvestorsQuery.class);
	
	@Override
	public RuleInvestorsEntity mapping(ResultSet rs) throws SQLException {
		
		RuleInvestorsEntity ruleInvestorsEntity = new RuleInvestorsEntity();
		
		if(rs!=null){
			ruleInvestorsEntity.setRuleType(StringUtils.valueOf(rs.getObject("rule_type")));
		}else{
			return null;
		}

		return ruleInvestorsEntity;
		
	}
	
	public List<RuleInvestorsEntity> queryRuleInvestlist(String id,String uuid){
		String sql = "SELECT rule_type FROM hy_rule_inverst_info WHERE rule_id = '"+id+"' GROUP BY rule_type ";
		RecordUtils.writeAction(logger, uuid, sql);
		return ConnManager.executeQuery(sql, this);
	}
	

}
