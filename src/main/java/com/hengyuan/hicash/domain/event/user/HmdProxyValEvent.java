package com.hengyuan.hicash.domain.event.user;

import java.util.Map;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.exception.UpdateHmdProxyValException;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * 嗨秒贷代扣验证结果
 * 
 * @author Cary.Liu
 * @createDate 2015-07-23
 */
public class HmdProxyValEvent {

	private static Logger logger = Logger.getLogger(HmdProxyValEvent.class);

	public void recordHmdProxyVal(Map<String, Object> inputMap) {

		String updateSql = MapAssemForSql.getInsertSql(
				TableConsts.HMD_PROXY_VAL, inputMap);

		RecordUtils.writeAction(logger, null, updateSql);

		ConnManager.executeUpdate(updateSql);
	}

	public void updateHmdProxyVal(Map<String, Object> inputMap,
			Map<String, Object> whereMap) {

		String updateSql = MapAssemForSql.getUpdateSql(
				TableConsts.HMD_PROXY_VAL, inputMap, whereMap);

		RecordUtils.writeAction(logger, null, updateSql);
	}

	public void updateHmdProxyVal5(Map<String, Object> inputMap,
			Map<String, Object> whereMap) throws UpdateHmdProxyValException {

		String updateSql = MapAssemForSql.getUpdateSql(
				TableConsts.HMD_PROXY_VAL, inputMap, whereMap);

		RecordUtils.writeAction(logger, null, updateSql);

		if (ConnManager.executeUpdate(updateSql) <= 0) {
			throw new UpdateHmdProxyValException();
		}
	}

	public int initProxyVal(Map<String, Object> inputMap) {

		String updateSql = MapAssemForSql.getInsertSql(
				TableConsts.HMD_PROXY_VAL, inputMap);

		RecordUtils.writeAction(logger, null, updateSql);

		int count = ConnManager.executeUpdate(updateSql);

		return count;
	}

}
