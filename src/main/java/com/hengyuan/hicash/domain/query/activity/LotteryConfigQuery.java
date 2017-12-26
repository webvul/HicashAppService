package com.hengyuan.hicash.domain.query.activity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.activity.LotteryConfigEntity;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 新年活动奖项配置查询DAO
 * @author Cary.Liu
 * @createDate 2015-12-29
 *
 */
public class LotteryConfigQuery extends AbstractDAO<LotteryConfigEntity> {

	private static Logger logger = Logger.getLogger(LotteryConfigQuery.class);
	
	private static final String QUERY_SQL = "SELECT * FROM hy_newyear_lottery_cfg WHERE 1 = 1 ";
	
	@Override
	public LotteryConfigEntity mapping(ResultSet rs) throws SQLException {

		LotteryConfigEntity entity = null;
		
		if(rs != null){
			
			entity = new LotteryConfigEntity();
			entity.setPrizeId(rs.getInt("PRIZE_ID"));
			entity.setPrizeName(StringUtils.valueOf(rs.getObject("PRIZE_NAME")));
			entity.setPrizeChance(rs.getBigDecimal("PRIZE_CHANCE"));
			entity.setPrizeNum(rs.getInt("PRIZE_NUMBER"));
			entity.setAlreadyLottoNumber(rs.getInt("ALREADY_LOTTO_NUMBER"));
			entity.setLottoNum(rs.getInt("LOTTO_NUMBER"));
			entity.setDesc(StringUtils.valueOf(rs.getObject("PRIZE_DESC")));
			entity.setLotteryType(StringUtils.valueOf(rs.getObject("LOTTERY_TYPE")));
			
		}
		
		return entity;
	}
	
	/**
	 * 获取奖项
	 * @param prizeId
	 * @return
	 */
	public LotteryConfigEntity queryLotteryCfgById(int prizeId) {
		
		StringBuffer querySql = new StringBuffer(QUERY_SQL);
		querySql.append(" PRIZE_ID = " + prizeId);
		
		RecordUtils.writeAction(logger, null, querySql.toString());
		
		return ConnManager.singleQuery(querySql.toString(), this);
	}
	
	/**
	 * 获取所有奖项
	 * @param prizeId
	 * @return
	 */
	public List<LotteryConfigEntity> queryLotteryCfgByAll(String lotteryType) {
		
		StringBuffer querySql = new StringBuffer(QUERY_SQL);
		/* 活动类型 */
		querySql.append(" AND LOTTERY_TYPE = " + lotteryType);
		
		querySql.append(" ORDER BY PRIZE_ID DESC");
		
		RecordUtils.writeAction(logger, null, querySql.toString());
		
		return ConnManager.executeQuery(querySql.toString(), this);
	}
			

}
