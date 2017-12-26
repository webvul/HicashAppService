package com.hengyuan.hicash.domain.event.user;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.user.ReferenceRecordEntity;
import com.hengyuan.hicash.exception.ReferenceRecordException;
import com.hengyuan.hicash.utils.DateUtils;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;



/**
 * 统计推荐人信息接口
 * 
 * @author lihua.ren
 * @createDate 2015-10-13
 *
 */
public class ReferenceRecordEvent {
private static Logger logger = Logger.getLogger(ReferenceRecordEvent.class);
	
	public void saveReferenceRecord(ReferenceRecordEntity recordEntity) throws ReferenceRecordException{
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		setMap.put("reference", recordEntity.getReference());
		setMap.put("recommend", recordEntity.getRecommend());
		setMap.put("referenceTime", DateUtils.getDateStr(new Date()));
		setMap.put("business", recordEntity.getBussiness());
		setMap.put("uppage", recordEntity.getUpPage());
		setMap.put("ip", recordEntity.getReferenceIP());
		setMap.put("app_application_no", recordEntity.getAppNo());
		setMap.put("HY_INDUSTRY_CODE", recordEntity.getHyIndustryCode());
		setMap.put("activityID", "1");

		
		String sql = MapAssemForSql.getInsertSql(TableConsts.REFERENCE_RECORD, setMap);
		
		RecordUtils.writeAction(logger, null, sql);
		if(ConnManager.executeUpdate(sql.toString()) <= 0){
			throw new ReferenceRecordException();
		}
	}
	
}
