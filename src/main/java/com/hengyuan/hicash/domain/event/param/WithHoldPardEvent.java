package com.hengyuan.hicash.domain.event.param;

import java.util.Map;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.exception.DInputProxyBankException;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;

public class WithHoldPardEvent {
	private static Logger logger = Logger.getLogger(WithHoldPardEvent.class);
	
	//代扣驗證記錄表
	public void withholdPartInsert(Map<String, Object> inputMap) throws DInputProxyBankException {

		String updateSql = MapAssemForSql.getInsertSql(
				TableConsts.WITHHOLD_PART_RECORD, inputMap);

		RecordUtils.writeAction(logger, null, updateSql);

		int count=ConnManager.executeUpdate(updateSql);
		if(count<1){
			throw new DInputProxyBankException();
		}
	}
}
