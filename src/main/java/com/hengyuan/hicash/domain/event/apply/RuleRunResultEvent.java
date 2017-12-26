package com.hengyuan.hicash.domain.event.apply;



import org.apache.log4j.Logger;

import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.app.RulesRunResult;
import com.hengyuan.hicash.exception.UpdateRulesResultFaildException;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * @author Mary.Luo
 *
 */
public class RuleRunResultEvent {

	private static Logger logger = Logger.getLogger(RuleRunResultEvent.class);
	
	public void  createRulesRunResult(RulesRunResult rulesRunResult) throws UpdateRulesResultFaildException {
		String rulesResultSql = "INSERT INTO d_rules_result " + "("
				+ "APP_APPLICATION_NO," + "RULE_NO," + "RUN_RESULT,"
				+ "RUN_RS_RECORD," + "RUN_TIME," +"number,"+"recordId"+ ")" + "VALUES" + "(" + "'"
				+ rulesRunResult.getApplicationNo() + "'," + "'"
				+ rulesRunResult.getRuleNo() + "'," + "'"
				+ rulesRunResult.getRunResult() + "'," + "'"
				+ rulesRunResult.getRunRsRecord() + "'," + "'"
				+ rulesRunResult.getRunTime() + "'," +"'"
				+rulesRunResult.getNumber()+"',"+"'"
				+rulesRunResult.getRecordId()+"')";
		
		
		RecordUtils.writeAction(logger, null, rulesResultSql);
		if(ConnManager.executeUpdate(rulesResultSql)<=0){
			
			throw new UpdateRulesResultFaildException();
		}

	}

}
