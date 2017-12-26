package com.hengyuan.hicash.domain.event.apply;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.param.AutoBatchRuleEntity;
import com.hengyuan.hicash.exception.SaveAutoRuleAppException;
import com.hengyuan.hicash.utils.DateUtils;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;

public class AutoBatchRuleEvent {

	private static Logger logger = Logger.getLogger(AutoBatchRuleEvent.class);
	
	public void saveAutoRuleApp(AutoBatchRuleEntity entity) throws SaveAutoRuleAppException{
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		setMap.put("APP_NO", entity.getAppNo());
		setMap.put("LOAN_PRODUCT_ID",entity.getLoanProduct());
		setMap.put("APP_AMOUNT", entity.getApplyAmount());
		setMap.put("APP_USER_NAME", entity.getUserName());
		setMap.put("APP_CUST_TYPE", entity.getCustType());
		setMap.put("BATCH_STATUS", entity.getStatus());
		setMap.put("CREATE_TIME", DateUtils.getDateStr(new Date()));
		
		String sql = MapAssemForSql.getInsertSql(TableConsts.AUTO_BATCH_RULES, setMap);

		RecordUtils.writeAction(logger, null, sql);
		if(ConnManager.executeUpdate(sql.toString()) <= 0){
			throw new SaveAutoRuleAppException();
		}
		
	}
	
}
