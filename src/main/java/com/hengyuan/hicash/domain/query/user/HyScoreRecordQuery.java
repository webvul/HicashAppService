package com.hengyuan.hicash.domain.query.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.dao.AbstractDAO;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.user.HyScoreRecord;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

public class HyScoreRecordQuery extends AbstractDAO<HyScoreRecord>{
	private static Logger logger = Logger.getLogger(HyScoreRecordQuery.class);
	
	
	@Override
	public HyScoreRecord mapping(ResultSet rs) throws SQLException {
		 
		HyScoreRecord hyScoreRecord = new HyScoreRecord();
		
		if(rs!=null){
//			hyScoreRecord.setCreateDate(StringUtils.valueOf(rs.getObject("create_date")));
			hyScoreRecord.setIdNo(StringUtils.valueOf(rs.getObject("id_no")));
//			hyScoreRecord.setResult(StringUtils.valueOf(rs.getObject("result")));
//			hyScoreRecord.setScore(StringUtils.valueOf(rs.getObject("score")));
//			hyScoreRecord.setThresholdHigh(StringUtils.valueOf(rs.getObject("threshold_high")));
//			hyScoreRecord.setThresholdLow(StringUtils.valueOf(rs.getObject("threshold_low")));
//			hyScoreRecord.setWhichPart(StringUtils.valueOf(rs.getObject("threshold_low")));
			hyScoreRecord.setTempNo(StringUtils.valueOf(rs.getObject("temp_No")));
//			hyScoreRecord.setCreateDate(StringUtils.valueOf(rs.getObject("create_date")));
//			hyScoreRecord.setId(StringUtils.valueOf(rs.getObject("id")));
//			hyScoreRecord.setWhichPart(StringUtils.valueOf(rs.getObject("which_Part")));
//			
			
			return hyScoreRecord;
		}else{
			
			System.out.println("查询的结果集为空");
			return null;
			
		}
	}
	
	/**
	 * 两次的概念是，一个临时单号只有两次机会
	 * 根据用户名和验证机构查询当天易道验证记录
	 * @param realName
	 * @param identity
	 * @return
	 */
	public List<HyScoreRecord> queryFirByIdNoTemp(String idNo,String uuid,String whichPart,String tempNo ){
		
     StringBuffer querySql = new StringBuffer("select id_no,temp_No from HY_SCORE_RECORD where 1=1 ");
     querySql.append(" AND result !='Y'");
		querySql.append(" AND id_no = '" + idNo + "'");
		querySql.append(" AND which_Part = '" + whichPart + "'");
		querySql.append(" AND temp_No = '"+tempNo+"' AND to_days(create_date) = to_days(now()) and temp_no  is not null");
		
		//记录日志
		RecordUtils.writeAction(logger, uuid, querySql.toString());
		
		return ConnManager.executeQuery(querySql.toString(), this);
		

	}
	
//	/**
//	*两次的概念是，一天只能走两个临时失败单
//	 * 根据用户名和验证机构查询当天易道验证记录
//	 * @param realName
//	 * @param identity
//	 * @return
//	 */
//	public List<HyScoreRecord> queryFirByIdNo(String idNo,String uuid,String whichPart){
//		
//      StringBuffer querySql = new StringBuffer("select distinct temp_No,id_no from HY_SCORE_RECORD where 1=1 ");
//      querySql.append(" AND result !='Y'");
//		querySql.append(" AND id_no = '" + idNo + "'");
//		querySql.append(" AND which_Part = '" + whichPart + "'");
//		querySql.append(" AND to_days(create_date) = to_days(now()) and temp_no  is not null");
//		
//		//记录日志
//		RecordUtils.writeAction(logger, uuid, querySql.toString());
//		
//		return ConnManager.executeQuery(querySql.toString(), this);
//		
//
//	}

	/**
	 * 根据用户名和验证机构查询当天face++验证记录
	 * @param realName
	 * @param identity
	 * @return
	 */
	public List<HyScoreRecord> querySecByIdNo(String idNo,String uuid,String whichPart){
		
      StringBuffer querySql = new StringBuffer("select id_no,temp_No from HY_SCORE_RECORD where 1=1 ");
      querySql.append(" AND result !='Y'");
		querySql.append(" AND id_no = '" + idNo + "'");
		querySql.append(" AND which_Part = '" + whichPart + "'");
		querySql.append(" AND to_days(create_date) = to_days(now()) and temp_no  is not null");
		
		//记录日志
		RecordUtils.writeAction(logger, uuid, querySql.toString());
		
		return ConnManager.executeQuery(querySql.toString(), this);
		

	}
}
