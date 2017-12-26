package com.hengyuan.hicash.domain.event.activity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.activity.NewYearLotteryEntity;
import com.hengyuan.hicash.exception.UpdateLotteryException;
import com.hengyuan.hicash.utils.DateUtils;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * 记录用户抽奖
 * @author Cary.Liu
 * @createDate 2015-12-29
 *
 */
public class NewYearLotteryEvent {

	private static Logger logger = Logger.getLogger(NewYearLotteryEvent.class);
	
	public void recordUserLottery(NewYearLotteryEntity entity) throws UpdateLotteryException{
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		setMap.put("USERNAME", entity.getUserName());
		setMap.put("REAL_NAME", entity.getRealName());
		setMap.put("MOBILE", entity.getMobile());
		setMap.put("ZJ_PRIZE_NUM", entity.getZjPrizeNum());
		setMap.put("ZJ_PRIZE_NAME", entity.getZjPrizeName());
		setMap.put("CJ_PRIZE_NUMS", entity.getCjPrizeNums());
		setMap.put("CREATE_TIME", DateUtils.getDateStr(new Date()));
		setMap.put("EXPRESS_PROVINCE", entity.getExpressProvince());
		setMap.put("EXPRESS_CITY", entity.getExpressCity());
		setMap.put("EXPRESS_AREA", entity.getExpressArea());
		setMap.put("EXPRESS_DETAIL", entity.getExpressDetail());
		setMap.put("LOTTERY_TYPE", entity.getLotteryType());
		
		String sql = MapAssemForSql.getInsertSql(TableConsts.HY_NEWYEAR_LOTTERY, setMap);
		
		RecordUtils.writeAction(logger, null, sql);
		if(ConnManager.executeUpdate(sql.toString()) <= 0){
			throw new UpdateLotteryException();
		}
		
	}
	
}
