package com.hengyuan.hicash.domain.event.user;

import java.util.Map;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * 代扣银行账户验证记录
 * @author Cary.Liu
 * @createDate 2015-05-07
 *
 */
public class AccountRecordEvent {
	
	private static Logger logger = Logger.getLogger(AccountRecordEvent.class);

	public int recordAccountValidate(Map<String, Object> inputMap){
		
		String updateSql = MapAssemForSql.getInsertSql(TableConsts.ACCOUNT_RECORD, inputMap);
		
		RecordUtils.writeAction(logger, null, updateSql);
		
		return ConnManager.executeUpdate(updateSql);
	}
	
}
