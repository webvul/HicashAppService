package com.hengyuan.hicash.domain.event.user;

import java.util.Map;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;

public class InformationEvent {

	private static Logger logger = Logger.getLogger(InformationEvent.class);

	public int recordValidateInfo(Map<String, Object> inputMap){
		
		String updateSql = MapAssemForSql.getInsertSql(TableConsts.VER_INFORMATION, inputMap);
		
		RecordUtils.writeAction(logger, null, updateSql);
		
		return ConnManager.executeUpdate(updateSql);
	}
	
}
