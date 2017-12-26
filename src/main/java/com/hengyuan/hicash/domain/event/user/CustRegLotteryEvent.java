package com.hengyuan.hicash.domain.event.user;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.user.CustRegLotteryEntity;
import com.hengyuan.hicash.exception.UpdateLotteryException;
import com.hengyuan.hicash.utils.DateUtils;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * 注册成功抽奖记录DAO
 * @author Cary.Liu
 * @createDate 2016-01-11
 *
 */
public class CustRegLotteryEvent {

	private static Logger logger = Logger.getLogger(CustRegLotteryEvent.class);
	
	public void recordRegLottery(CustRegLotteryEntity entity) throws UpdateLotteryException{
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		setMap.put("USERNAME", entity.getUserName());
		setMap.put("AMOUNT", entity.getAmount());
		setMap.put("CREATE_TIME", DateUtils.getDateStr(new Date()));
		
		String sql = MapAssemForSql.getInsertSql(TableConsts.CUST_REG_LOTTERY, setMap);
		
		RecordUtils.writeAction(logger, null, sql);
		if(ConnManager.executeUpdate(sql.toString()) <= 0){
			throw new UpdateLotteryException();
		}
		
	}
	
}
