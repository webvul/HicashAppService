package com.hengyuan.hicash.domain.event.user;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.entity.user.CustFundShouquanEntity;
import com.hengyuan.hicash.entity.user.FaceOriginalDetailEntity;
import com.hengyuan.hicash.entity.user.HyReportRecordEntity;
import com.hengyuan.hicash.entity.user.HyScoreRecord;
import com.hengyuan.hicash.exception.HyReportREcordException;
import com.hengyuan.hicash.exception.UpdateCustomerException;
import com.hengyuan.hicash.exception.UpdateInfoException;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * 
 * @author fish
 *
 * @date 2017年1月10日 下午8:19:31
 */
public class YiDaoMarkEvent {

	private static Logger logger = Logger.getLogger(YiDaoMarkEvent.class);

	public void insertSelective(HyScoreRecord record) {
		String insertSql = "INSERT INTO hy_score_record " + "(" + "id_no, " + "score, " + "result, " + "create_date,"
				+ "threshold_high, " + "threshold_low)" + "VALUES" + "(" + "'" + record.getIdNo() + "', " + "'"
				+ record.getScore() + "', " + "'" + record.getResult() + "'," + "'" + record.getCreateDate() + "', "
				+ "'" + record.getThresholdHigh() + "'," + "'" + record.getThresholdLow() + "')";
		RecordUtils.writeAction(logger, null, insertSql);
		ConnManager.executeUpdate(insertSql);
	}
	
	/**
	 * 保存人脸识别记录
	 * @param req
	 * @throws UpdateInfoException
	 */
	public void saveFaceMsg(HyScoreRecord record) {
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		
		setMap.put("id_no", record.getIdNo());//
		setMap.put("score", record.getScore());
		setMap.put("result",record.getResult());
		setMap.put("threshold_low",record.getThresholdLow());
		setMap.put("threshold_high", record.getThresholdHigh());
		setMap.put("which_part",record.getWhichPart());
		setMap.put("temp_no", record.getTempNo());
		setMap.put("create_date", FastLoanFirstUpEvent.getDateStr(new Date()));
		
		
		
		String updateSql = MapAssemForSql.getInsertSql(TableConsts.HY_SCORE_RECORD, setMap);
		//记录日志
		RecordUtils.writeAction(logger,record.getIdNo(), updateSql);
		if(ConnManager.executeUpdate(updateSql) <= 0){
			logger.info(record.getIdNo()+"记录人脸识别失败");
		}
	}
	
	
	/**
	 * FACE++返回原始数据
	 * @param req
	 * @throws UpdateInfoException
	 */
	public void saveFaceMsg(FaceOriginalDetailEntity record) {
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		
		setMap.put("id_no", record.getIdNo());//
		setMap.put("face_str", record.getFaceStr());
		
		setMap.put("create_date", FastLoanFirstUpEvent.getDateStr(new Date()));

		String updateSql = MapAssemForSql.getInsertSql(TableConsts.FACE_ORIGINAL_DETAIL, setMap);
		//记录日志
		RecordUtils.writeAction(logger,record.getIdNo(), updateSql);
		if(ConnManager.executeUpdate(updateSql) <= 0){
			logger.info(record.getIdNo()+"face++保存原始数据异常");
		}
	}
	
	
	/**
	 * 保存公积金授权记录
	 * @param req
	 * @throws HyReportREcordException 
	 * @throws UpdateInfoException
	 */
	public void saveHyReportRecord(HyReportRecordEntity record) throws HyReportREcordException {
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		
		setMap.put("id_no", record.getId_no());//
		setMap.put("name", record.getName());
		
		//1外卖，2公积金 
		if("WM".equals(record.getType())){
			setMap.put("type", 1);	
		}else{
			setMap.put("type", 2);	
		}
		
		setMap.put("mobile", record.getMobile());
		setMap.put("status","0");
		setMap.put("create_time", FastLoanFirstUpEvent.getDateStr(new Date()));

		String updateSql = MapAssemForSql.getInsertSql(TableConsts.HY_REPORT_RECORD, setMap);
		//记录日志
		RecordUtils.writeAction(logger,record.getId_no(), updateSql);
		if(ConnManager.executeUpdate(updateSql) <= 0){
			logger.info(record.getId_no()+"公积金，外卖授权记录保存异常");
			throw new HyReportREcordException();
			
		}
	}

	/**
	 * 保存公积金授权记录
	 * @param req
	 * @throws HyReportREcordException 
	 * @throws UpdateInfoException
	 */
	public void saveFundRecord(CustFundShouquanEntity record) throws HyReportREcordException {
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		
		setMap.put("id_no", record.getId_no());//
		setMap.put("name", record.getName());
		setMap.put("type", record.getType());
		
		setMap.put("shou_quan", record.getShou_quan());
		setMap.put("create_time", FastLoanFirstUpEvent.getDateStr(new Date()));

		String updateSql = MapAssemForSql.getInsertSql(TableConsts.CUST_FUND_SHOUQUAN, setMap);
		//记录日志
		RecordUtils.writeAction(logger,record.getId_no(), updateSql);
		if(ConnManager.executeUpdate(updateSql) <= 0){
			logger.info(record.getId_no()+"公积金授权记录保存异常");
			throw new HyReportREcordException();
			
		}
	}
	
	
	/**
	 * 更新公积金记录
	 * 
	 * @param userInfo
	 * @throws UpdateCustomerException
	 */
	public void updateFundRecord(String fundDo,String idNo) throws HyReportREcordException {

		Map<String, Object> setMap = new HashMap<String, Object>();

		setMap.put("shou_quan", fundDo);
		setMap.put("create_time", FastLoanFirstUpEvent.getDateStr(new Date()));

		Map<String, Object> wheremMap = new HashMap<String, Object>();

		wheremMap.put("id_no", idNo);
		wheremMap.put("type", "GJJ");
		String sql = MapAssemForSql.getUpdateSql(TableConsts.CUST_FUND_SHOUQUAN, setMap, wheremMap);
	
		if (ConnManager.executeUpdate(sql) <= 0) {
			logger.info(idNo+",公积金授权记录保存异常");
			throw new HyReportREcordException();
		}
	}
	
	/**
	 * 更新蓝领用户资料
	 * 
	 * @param userInfo
	 * @throws UpdateCustomerException
	 */
	public void updateByIdNo(HyScoreRecord record) throws UpdateCustomerException {

		Map<String, Object> setMap = new HashMap<String, Object>();

		setMap.put("score", record.getScore());
		setMap.put("result", record.getResult());
		setMap.put("threshold_high", record.getThresholdHigh());
		setMap.put("threshold_low", record.getThresholdLow());

		Map<String, Object> wheremMap = new HashMap<String, Object>();

		wheremMap.put("id_no", record.getIdNo());

		String sql = MapAssemForSql.getUpdateSql(TableConsts.HY_SCORE_RECORD, setMap, wheremMap);
		if (ConnManager.executeUpdate(sql) <= 0) {
			throw new UpdateCustomerException();
		}
	}

}
