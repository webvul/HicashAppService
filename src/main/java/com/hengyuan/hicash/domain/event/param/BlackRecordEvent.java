package com.hengyuan.hicash.domain.event.param;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.remote.BlackRecordEntity;
import com.hengyuan.hicash.utils.DateUtils;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;


public class BlackRecordEvent {

	private static Logger logger = Logger.getLogger(BlackRecordEvent.class);
	
	public void recordBlackInfo(BlackRecordEntity entity){
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		setMap.put("USERNAME", StringUtils.getString(entity.getUserName()));
		setMap.put("REAL_NAME", StringUtils.getString(entity.getRealName()));
		setMap.put("MOBILE", StringUtils.getString(entity.getMobile()));
		setMap.put("IDENTITY_NO", StringUtils.getString(entity.getIdentityNo()));
		setMap.put("FROM_TYPE", StringUtils.getString(entity.getFromType()));
		setMap.put("BLACK_DESC", StringUtils.getString(entity.getBlackDesc()));
		setMap.put("RESULT_CODE", entity.getResultCode());
		setMap.put("CREATE_TIME", DateUtils.getDateStr(new Date()));
		setMap.put("REMOTE_IP", StringUtils.getString(entity.getRemoteIp()));
		
		String updateSql = MapAssemForSql.getInsertSql(TableConsts.D_BLACK_RECORD, setMap);
		
		RecordUtils.writeAction(logger, null, updateSql);
		
		ConnManager.executeUpdate(updateSql);
	}
	
}
