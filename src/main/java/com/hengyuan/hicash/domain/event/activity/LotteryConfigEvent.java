package com.hengyuan.hicash.domain.event.activity;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.exception.UpdateLotterycfgException;
import com.hengyuan.hicash.utils.RecordUtils;

public class LotteryConfigEvent {
	
	private static Logger logger = Logger.getLogger(LotteryConfigEvent.class);

	/**
	 * 更新奖项中奖次数
	 * @param prizeId
	 * @throws UpdateLotterycfgException
	 */
	public void updateAreadyLottoNum(int prizeId) throws UpdateLotterycfgException{
		
		String updateSql = "UPDATE hy_newyear_lottery_cfg SET ALREADY_LOTTO_NUMBER = ALREADY_LOTTO_NUMBER + 1 WHERE PRIZE_ID = " + prizeId;
		
		RecordUtils.writeAction(logger, null, updateSql);
		
		if(ConnManager.executeUpdate(updateSql) <= 0){
			throw new UpdateLotterycfgException();
		};
		
	}
	
	/**
	 * 更新奖项抽取次数(每抽取一次都会更新，不管有没有抽中)
	 * @param prizeId
	 * @throws UpdateLotterycfgException
	 */
	public void updateLottoNum(int prizeId) throws UpdateLotterycfgException{
		
		String updateSql = "UPDATE hy_newyear_lottery_cfg SET LOTTO_NUMBER = LOTTO_NUMBER + 1 WHERE PRIZE_ID = " + prizeId;
		
		RecordUtils.writeAction(logger, null, updateSql);
		
		if(ConnManager.executeUpdate(updateSql) <= 0){
			throw new UpdateLotterycfgException();
		};
		
	}
	
}
