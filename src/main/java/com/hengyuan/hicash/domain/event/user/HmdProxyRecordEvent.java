package com.hengyuan.hicash.domain.event.user;

import java.util.Map;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * 嗨秒贷代扣记录
 * 
 * @author Cary.Liu
 * @createDate 2015-07-23
 */
public class HmdProxyRecordEvent {
	
	private static Logger logger = Logger.getLogger(HmdProxyRecordEvent.class);

	public void recordHmdProxyRecord(Map<String, Object> inputMap){
		
		String updateSql = MapAssemForSql.getInsertSql(TableConsts.HMD_PROXY_RECORD, inputMap);
		
		RecordUtils.writeAction(logger, null, updateSql);
		
		ConnManager.executeUpdate(updateSql);
	}

}
