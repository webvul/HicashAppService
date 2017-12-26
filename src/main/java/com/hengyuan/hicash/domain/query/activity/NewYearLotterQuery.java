package com.hengyuan.hicash.domain.query.activity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.activity.NewYearLotteryEntity;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 新年活动抽奖明细查询Dao
 * @author Cary.Liu
 * @createDate 2015-12-29
 *
 */
public class NewYearLotterQuery extends AbstractDAO<NewYearLotteryEntity> {

	private static Logger logger = Logger.getLogger(NewYearLotterQuery.class);
	
	private static final String QUERY_SQL = "SELECT * FROM hy_newyear_lottery WHERE 1 = 1 ";
	
	@Override
	public NewYearLotteryEntity mapping(ResultSet rs) throws SQLException {

		NewYearLotteryEntity entity = null;
		
		if (rs != null){
			
			entity = new NewYearLotteryEntity();
			entity.setId(rs.getInt("ID"));
			entity.setUserName(StringUtils.valueOf(rs.getObject("USERNAME")));
			entity.setRealName(StringUtils.valueOf(rs.getObject("REAL_NAME")));
			entity.setMobile(StringUtils.valueOf(rs.getObject("MOBILE")));
			entity.setZjPrizeNum(StringUtils.valueOf(rs.getObject("ZJ_PRIZE_NUM")));
			entity.setCjPrizeNums(StringUtils.valueOf(rs.getObject("CJ_PRIZE_NUMS")));
			entity.setCreateTime(StringUtils.valueOf(rs.getObject("CREATE_TIME")));
			entity.setExpressProvince(StringUtils.valueOf(rs.getObject("EXPRESS_PROVINCE")));
			entity.setExpressCity(StringUtils.valueOf(rs.getObject("EXPRESS_CITY")));
			entity.setExpressArea(StringUtils.valueOf(rs.getObject("EXPRESS_AREA")));
			entity.setExpressDetail(StringUtils.valueOf(rs.getObject("EXPRESS_DETAIL")));
			entity.setLotteryType(StringUtils.valueOf(rs.getObject("LOTTERY_TYPE")));
			
		}
		
		return entity;
	}
	
	public List<NewYearLotteryEntity> queryUserlotteryRecord(String userName, String lotteryType){
		
		StringBuffer querySql = new StringBuffer(QUERY_SQL);
		/* 用户名 */
		querySql.append(" AND USERNAME = '" + userName + "'");
		/* 活动类型 */
		querySql.append(" AND LOTTERY_TYPE = " + lotteryType);
		
		RecordUtils.writeAction(logger, null, querySql.toString());
		
		return ConnManager.executeQuery(querySql.toString(), this);
	}
	
	/**
	 * 用户是否中奖
	 * @param userName
	 * @param lotteryType
	 * @return
	 */
	public boolean isInLotteryFlag(String userName, String lotteryType){
		
		StringBuffer querySql = new StringBuffer(QUERY_SQL);
		/* 用户名 */
		querySql.append(" AND USERNAME = '" + userName + "'");
		/* 活动类型 */
		querySql.append(" AND LOTTERY_TYPE = " + lotteryType);
		/* 是否中奖 */
		querySql.append(" AND ZJ_PRIZE_NUM <> 0");
		
		RecordUtils.writeAction(logger, null, querySql.toString());
		
		List<NewYearLotteryEntity> list = ConnManager.executeQuery(querySql.toString(), this);
		
		return (list != null && list.size() > 0) ? true : false;
	}

}
