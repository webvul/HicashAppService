package com.hengyuan.hicash.domain.event.user;

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
import com.hengyuan.hicash.parameters.request.mktApp.CreateAppPayReq;
import com.hengyuan.hicash.parameters.request.user.CallPhoneDataNumUpdateReq;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * 
 * 电信专案,保存套餐号码
 * @author LiHua.Ren
 * @createDate 2015-08-18
 */
public class CallPhoneDataNumUpEvent {

private static Logger logger = Logger.getLogger(CallPhoneDataNumUpEvent.class);
private List<String> selectList = new ArrayList<String>();

public CallPhoneDataNumUpEvent() {
	selectList.add("username");
	
}
/**
 * 电信专案,套餐号码
 * 
 * @param req
 * @return
 * @throws UpdateInfoException 
 */
public void saveCallDataNum(CallPhoneDataNumUpdateReq req) throws UpdateInfoException {
	
	Map<String, Object> setMap = new HashMap<String, Object>();
	
	setMap.put("pro_phoneNum", req.getPhoneNum());
	setMap.put("phone_data_id", req.getPhoneDataId());
	setMap.put("USERNAME", req.getUserName());
	setMap.put("TEMP_APPLICATION_NO", req.getAppFlowNo());
	setMap.put("app_type", req.getAppType());
	setMap.put("PRO_NAME", req.getProColor());
	setMap.put("PROMER_ID", req.getProMerId());
	setMap.put("LOAN_PRODUCT", req.getProPeriod());
	setMap.put("APPLY_PRICE", req.getProPrice());
	setMap.put("CREATE_DATE", FastLoanFirstUpEvent.getDateStr(new Date()));
	String updateSql = MapAssemForSql.getInsertSql(TableConsts.TEMP_APPLY_INFO, setMap);
	//记录日志
	RecordUtils.writeAction(logger, req.getUuid(), updateSql);
	if(ConnManager.executeUpdate(updateSql) <= 0){
		throw new UpdateInfoException();
	}
}

/**
 * 电信专案,电信专案,套餐号码更改
 * 
 * @param req
 * @return
 * @throws UpdateInfoException 
 */
public void updateDataNumPro(CallPhoneDataNumUpdateReq req) throws UpdateInfoException {
	
	Map<String, Object> setMap = new HashMap<String, Object>();
	
	setMap.put("pro_phoneNum", req.getPhoneNum());
	setMap.put("phone_data_id", req.getPhoneDataId());
	setMap.put("app_type", req.getAppType());
	setMap.put("USERNAME", req.getUserName());
	setMap.put("PRO_Name", req.getProColor());
	setMap.put("PROMER_ID", req.getProMerId());
	setMap.put("LOAN_PRODUCT", req.getProPeriod());
	setMap.put("APPLY_PRICE", req.getProPrice());
	setMap.put("update_date", FastLoanFirstUpEvent.getDateStr(new Date()));
	Map<String, Object> whereMap = new HashMap<String, Object>();
	whereMap.put("username", req.getUserName());
	whereMap.put("TEMP_APPLICATION_NO", req.getAppFlowNo());
	whereMap.put("app_type", req.getAppType());
	String updateSql = MapAssemForSql.getUpdateSql(TableConsts.TEMP_APPLY_INFO, setMap, whereMap);
	//记录日志
	RecordUtils.writeAction(logger, req.getUuid(), updateSql);
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
//锁定手机号码一段时间
/**
 * 
 * 
 * @param req
 * @return
 * @throws UpdateInfoException 
 */
public void updatePhoneStatue(CreateAppPayReq req) throws UpdateInfoException {
	
	Map<String, Object> setMap = new HashMap<String, Object>();
	
	setMap.put("inoruse", "1");
	Map<String, Object> whereMap = new HashMap<String, Object>();
	whereMap.put("phone_num",req.getPhoneNum());
	String updateSql = MapAssemForSql.getUpdateSql(TableConsts.CALL_PHONE_NUM, setMap, whereMap);
	//记录日志
	RecordUtils.writeAction(logger, req.getUuid(), updateSql);
	if(ConnManager.executeUpdate(updateSql) <= 0){
		throw new UpdateInfoException();
	}
}

}
