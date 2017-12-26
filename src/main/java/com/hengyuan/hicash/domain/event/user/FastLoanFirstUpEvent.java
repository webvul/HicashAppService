package com.hengyuan.hicash.domain.event.user;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.exception.UpdateInfoException;
import com.hengyuan.hicash.parameters.request.user.FastLoanFirstUpReq;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * 秒贷保存资料资料dao
 * 
 * @author LiHua.Ren
 * @create date 2015-05-23
 * 
 */
public class FastLoanFirstUpEvent {
	
	private static Logger logger = Logger.getLogger(FastLoanFirstUpEvent.class);
private List<String> selectList = new ArrayList<String>();
	
	public FastLoanFirstUpEvent() {
		selectList.add("username");
		
	}
	/**
	 * 秒贷保存资料资料
	 * 
	 * @param req
	 * @return
	 * @throws UpdateInfoException 
	 */
	public void saveFastMsg(FastLoanFirstUpReq req) throws UpdateInfoException {
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		
		setMap.put("APPLY_PRICE", req.getFastAmt());//金额
		setMap.put("LOAN_PRODUCT", req.getFastPeriod());//期数
		setMap.put("CREATEAPP_FLAG", "0");
		setMap.put("username", req.getUserName());
		setMap.put("TEMP_APPLICATION_NO", req.getAppFlowNo());
		setMap.put("CREATE_DATE", FastLoanFirstUpEvent.getDateStr(new Date()));
		String updateSql = MapAssemForSql.getInsertSql(TableConsts.TEMP_APPLY_INFO, setMap);
		//记录日志
		RecordUtils.writeAction(logger, req.getUuid(), updateSql);
		if(ConnManager.executeUpdate(updateSql) <= 0){
			throw new UpdateInfoException();
		}
	}

	/**
	 * 修改秒贷期数，金额，未完成订单
	 * 
	 * @param req
	 * @return
	 * @throws UpdateInfoException 
	 */
	public void updateFastMsg(FastLoanFirstUpReq req) throws UpdateInfoException {
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		
		setMap.put("APPLY_PRICE", req.getFastAmt());
		setMap.put("LOAN_PRODUCT", req.getFastPeriod());
		setMap.put("TEMP_APPLICATION_NO", req.getAppFlowNo());
		setMap.put("UPDATE_DATE", FastLoanFirstUpEvent.getDateStr(new Date()));
		setMap.put("USER_IDCARD_FRONT","");
		setMap.put("IDCARD_FRONT","");
		setMap.put("IDCARD_VERSO", "");
		setMap.put("CREATEAPP_FLAG", "0");
		setMap.put("SERVICE_PASSWORD","");
		setMap.put("APP_APPLICATION_NO", new BigInteger("0"));
		setMap.put("CREATE_FROM", "");
		
		setMap.put("USER_IDCARD_FRONT_THUM","");
		setMap.put("IDCARD_FRONT_THUM","");
		setMap.put("IDCARD_VERSO_THUM", "");
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("username", req.getUserName());
		
		String updateSql = MapAssemForSql.getUpdateSql(TableConsts.TEMP_APPLY_INFO, setMap, whereMap);
		//记录日志
		RecordUtils.writeAction(logger, req.getUuid(), updateSql);
		if(ConnManager.executeUpdate(updateSql) <= 0){
			throw new UpdateInfoException();
		}
	}
	/**
	 * 修改秒贷期数，金额
	 * 
	 * @param req
	 * @return
	 * @throws UpdateInfoException 
	 */
	public void updateAmtPro(FastLoanFirstUpReq req) throws UpdateInfoException {
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		
		setMap.put("APPLY_PRICE", req.getFastAmt());
		setMap.put("LOAN_PRODUCT", req.getFastPeriod());
//		setMap.put("TEMP_APPLICATION_NO", req.getAppFlowNo());
		setMap.put("UPDATE_DATE", FastLoanFirstUpEvent.getDateStr(new Date()));
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("username", req.getUserName());
		whereMap.put("TEMP_APPLICATION_NO", req.getAppFlowNo());
		String updateSql = MapAssemForSql.getUpdateSql(TableConsts.TEMP_APPLY_INFO, setMap, whereMap);
		//记录日志
		RecordUtils.writeAction(logger, req.getUuid(), updateSql);
		if(ConnManager.executeUpdate(updateSql) <= 0){
			throw new UpdateInfoException();
		}
	}
	
	/**
	 *  修改临时订单 司机账号
	 * @param driverUsername
	 * @param tempAppNo
	 * @param uuid
	 * @throws UpdateInfoException
	 */
	public void updateDriverUsername(String driverUsername,String tempAppNo,String uuid) throws UpdateInfoException {
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		
		setMap.put("PHONE_DATA_ID", driverUsername);
		setMap.put("UPDATE_DATE", FastLoanFirstUpEvent.getDateStr(new Date()));
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("TEMP_APPLICATION_NO", tempAppNo);
		String updateSql = MapAssemForSql.getUpdateSql(TableConsts.TEMP_APPLY_INFO, setMap, whereMap);
		//记录日志
		RecordUtils.writeAction(logger, uuid, updateSql);
		if(ConnManager.executeUpdate(updateSql) <= 0){
			throw new UpdateInfoException();
		}
	}
	public static String getDateStr(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(calendar.getTime());
	}
	 
}
