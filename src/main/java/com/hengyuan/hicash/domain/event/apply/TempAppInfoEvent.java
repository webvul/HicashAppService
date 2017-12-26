package com.hengyuan.hicash.domain.event.apply;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.exception.UpdateTempAppException;
import com.hengyuan.hicash.exception.UploadPicException;
import com.hengyuan.hicash.parameters.request.upload.SaveSingleImgReq;
import com.hengyuan.hicash.utils.DateUtils;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.RegexValidate;

/**
 * 临时申请表 event
 * @author Cary.Liu
 * @createDate 2015-05-26
 *
 */
public class TempAppInfoEvent {
	
	private static Logger logger = Logger.getLogger(TempAppInfoEvent.class);
	
	public void updatePicInfo(SaveSingleImgReq imgReq,String picType,String picPath) throws UploadPicException{
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		if(Consts.IMG_TYPE_ZL112.equals(picType)){
			setMap.put("USER_IDCARD_FRONT", picPath);
		}else if(Consts.IMG_TYPE_ZL02.equals(picType)){
			setMap.put("IDCARD_FRONT", picPath);
		}else if(Consts.IMG_TYPE_ZL03.equals(picType)){
			setMap.put("IDCARD_VERSO", picPath);
		}
		setMap.put("UPDATE_DATE", DateUtils.getDateStr(new Date()));
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("USERNAME", imgReq.getUserName());
		whereMap.put("TEMP_APPLICATION_NO", imgReq.getTempAppNo());
		
		String sql = MapAssemForSql.getUpdateSql(TableConsts.TEMP_APPLY_INFO, setMap, whereMap);
		
		RecordUtils.writeAction(logger, null, sql);
		if(ConnManager.executeUpdate(sql.toString()) <= 0){
			throw new UploadPicException();
		}
	}
	
	/**
	 * 申请件创建成功后更新临时申请表
	 * @param userName
	 * @param tempAppNo
	 * @param appNo
	 * @throws UpdateTempAppException
	 */
	public void createAppSuccFlag(String userName,String tempAppNo,String appNo,String createFlag) throws UpdateTempAppException{
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		setMap.put("CREATEAPP_FLAG", createFlag);
		setMap.put("APP_APPLICATION_NO", appNo);
		setMap.put("CREATE_FROM", Consts.CREATEAPP_FROM_HICASHAPP);
		setMap.put("UPDATE_DATE", DateUtils.getDateStr(new Date()));
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("USERNAME", userName);
		whereMap.put("TEMP_APPLICATION_NO", tempAppNo);
		
		String sql = MapAssemForSql.getUpdateSql(TableConsts.TEMP_APPLY_INFO, setMap, whereMap);
		
		RecordUtils.writeAction(logger, null, sql);
		if(ConnManager.executeUpdate(sql.toString()) <= 0){
			throw new UpdateTempAppException();
		}
		
	}
	
	/**
	 * 申请件创建成功后更新临时申请表
	 * @param userName
	 * @param tempAppNo
	 * @param appNo
	 * @throws UpdateTempAppException
	 */
	public void saveServicePsw(String userName,String tempAppNo,String servicePsw) throws UpdateTempAppException{
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		
		setMap.put("SERVICE_PASSWORD", servicePsw);
		setMap.put("UPDATE_DATE", DateUtils.getDateStr(new Date()));
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("USERNAME", userName);
		whereMap.put("TEMP_APPLICATION_NO", tempAppNo);
		
		String sql = MapAssemForSql.getUpdateSql(TableConsts.TEMP_APPLY_INFO, setMap, whereMap);
		
		RecordUtils.writeAction(logger, null, sql);
		if(ConnManager.executeUpdate(sql.toString()) <= 0){
			throw new UpdateTempAppException();
		}
		
	}
	/**
	 * 申请件创建成功后更新临时申请表电信专案用
	 * @param userName
	 * @param tempAppNo
	 * @param appNo
	 * @throws UpdateTempAppException
	 */
	public void createAppSuccFlagDx(String userName,String tempAppNo,String appNo,String createFlag,String isDx) throws UpdateTempAppException{
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		setMap.put("CREATEAPP_FLAG", createFlag);
		setMap.put("APP_APPLICATION_NO", appNo);
		setMap.put("CREATE_FROM", Consts.CREATEAPP_FROM_HICASHAPPDX);
		setMap.put("UPDATE_DATE", DateUtils.getDateStr(new Date()));
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("USERNAME", userName);
		whereMap.put("TEMP_APPLICATION_NO", tempAppNo);
		whereMap.put("APP_TYPE", isDx);
		String sql = MapAssemForSql.getUpdateSql(TableConsts.TEMP_APPLY_INFO, setMap, whereMap);
		
		RecordUtils.writeAction(logger, null, sql);
		if(ConnManager.executeUpdate(sql.toString()) <= 0){
			throw new UpdateTempAppException();
		}
		
	}
	
	/**
	 * 创建临时申请
	 * @param input
	 * @throws UpdateTempAppException
	 */
	public void createTempApp(HashMap<String, Object> input) throws UpdateTempAppException{
		
		String sql = MapAssemForSql.getInsertSql(TableConsts.TEMP_APPLY_INFO, input);
		
		RecordUtils.writeAction(logger, null, sql);
		
		if(ConnManager.executeUpdate(sql.toString()) <= 0){
			throw new UpdateTempAppException();
		}
	}
	
	/**
	 * 删除临时申请件图片
	 * @param picId
	 * @param userName
	 */
	public void deleteTempAppPic(String picId, String userName){
		
		String deleteSql = "DELETE FROM cust_tempapply_pic WHERE PIC_ID = "+ picId +" AND USERNAME = '" + userName + "'";
		
		RecordUtils.writeAction(logger, null, deleteSql);
		
		ConnManager.executeUpdate(deleteSql);
	}
	
	/**
	 * 更新临时申请件状态为初审
	 * @param userName
	 * @param tempAppNo
	 * @param appNo
	 * @throws UpdateTempAppException
	 */
	public void updateTempAppToCs(String userName,String tempAppNo,String createFlag) throws UpdateTempAppException{
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		setMap.put("CREATEAPP_FLAG", createFlag);
		setMap.put("UPDATE_DATE", DateUtils.getDateStr(new Date()));
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("USERNAME", userName);
		whereMap.put("TEMP_APPLICATION_NO", tempAppNo);
		String sql = MapAssemForSql.getUpdateSql(TableConsts.TEMP_APPLY_INFO, setMap, whereMap);
		
		RecordUtils.writeAction(logger, null, sql);
		if(ConnManager.executeUpdate(sql.toString()) <= 0){
			throw new UpdateTempAppException();
		}
		
	}
	
	public void updateTempCreateAppFlag(String appNo) throws UpdateTempAppException{
		
		String sql = "UPDATE CUST_TEMPAPPLY_INFO SET CREATEAPP_FLAG = 1 WHERE APP_APPLICATION_NO = '"+ appNo +"'";
		
		RecordUtils.writeAction(logger, null, sql);
		
		if(ConnManager.executeUpdate(sql.toString()) <= 0){
			throw new UpdateTempAppException();
		}
		
	}
	
	
	public void updateTempOperFlag(String appTempNo,String operatorFlag,String xuexinFlag) throws UpdateTempAppException{
		
		StringBuffer sbSql = new StringBuffer();
		
		sbSql.append(" UPDATE CUST_TEMPAPPLY_INFO SET UPDATE_DATE = NOW() ");
		if(!RegexValidate.isNull(operatorFlag)){
			sbSql.append(",JXL_PHONE_RESULT = "+operatorFlag+" ");
		}
		if(!RegexValidate.isNull(xuexinFlag)){
			sbSql.append(",REPORT_TYPE = "+xuexinFlag+" ");
		}
		
		sbSql.append(" WHERE TEMP_APPLICATION_NO = '"+ appTempNo +"' ");
		
		RecordUtils.writeAction(logger, null, sbSql.toString());
		
		if(ConnManager.executeUpdate(sbSql.toString()) <= 0){
			throw new UpdateTempAppException();
		}
		
	}
	
	public void updateTempPro(String appNo,String pro) throws UpdateTempAppException{
		
		String sql = "UPDATE CUST_TEMPAPPLY_INFO SET PRO_PHONENUM ='"+pro+"' WHERE TEMP_APPLICATION_NO = '"+ appNo +"'";
		
		RecordUtils.writeAction(logger, null, sql);
		
		if(ConnManager.executeUpdate(sql.toString()) <= 0){
			throw new UpdateTempAppException();
		}
		
	}
}
