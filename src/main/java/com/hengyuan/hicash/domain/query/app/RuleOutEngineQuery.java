package com.hengyuan.hicash.domain.query.app;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.app.LoanProduct;
import com.hengyuan.hicash.entity.app.RuleOutEngine;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

/** 
 * @author blianke.qin
 * 2017年1月10日 下午2:04:36
 * 类说明 
 */
public class RuleOutEngineQuery extends AbstractDAO<RuleOutEngine> {
	
	private static Logger logger = Logger.getLogger(RuleOutEngineQuery.class);
	
	@Override
	public RuleOutEngine mapping(ResultSet rs) throws SQLException {
		RuleOutEngine ruleOutEngine = new RuleOutEngine();

		if (rs != null) {
			ruleOutEngine.setId(rs.getInt("id"));
			ruleOutEngine.setAppno(rs.getString("appno"));
			ruleOutEngine.setAlert(rs.getString("alert"));
			ruleOutEngine.setReasononegrade(rs.getString("reasononegrade"));
			ruleOutEngine.setReasononegrade2(rs.getString("reasononegrade2"));
			ruleOutEngine.setFacresult(rs.getString("facresult"));
			ruleOutEngine.setA_score_card(rs.getString("a_score_card"));
			ruleOutEngine.setCreate_time(rs.getString("create_time"));
			
		}
		return ruleOutEngine;
	}
	
	
	/**
	 * 根据流水号查询规则跑批信息
	 */
	public RuleOutEngine queryRuleOutEngine(String appno){
		String sql="SELECT id ,appno,reasononegrade ,reasononegrade2,facresult ,alert,a_score_card ,create_time FROM  rule_out_engine where appno='"+appno+"' ORDER BY create_Time DESC LIMIT 1";
		
		RecordUtils.writeAction(logger, appno, sql);
		return ConnManager.singleQuery(sql, this);
		
	}

}
