package com.hengyuan.hicash.domain.event.user;

import java.util.Map;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.exception.CmbcIdentifyException;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * 民生银行代扣银行卡验证
 * 
 * @author Lihua.Ren
 * @createDate 2015-12-01
 *
 */
public class CmbcIdentifyEvent {
	private static Logger logger = Logger.getLogger(CmbcIdentifyEvent.class);
	public void recordIdentifyVal(Map<String, Object> inputMap) {

		String updateSql = MapAssemForSql.getInsertSql(
				TableConsts.CMBC_IDENTIFY_VAL, inputMap);

		RecordUtils.writeAction(logger, null, updateSql);

		ConnManager.executeUpdate(updateSql);
	}
	
	public void updateIdentify(Map<String, Object> inputMap,
			Map<String, Object> whereMap) throws CmbcIdentifyException {

		String updateSql = MapAssemForSql.getUpdateSql(
				TableConsts.CMBC_IDENTIFY_VAL, inputMap, whereMap);

		RecordUtils.writeAction(logger, null, updateSql);

		if (ConnManager.executeUpdate(updateSql) <= 0) {
			throw new CmbcIdentifyException();
		}
	}
}
