package com.hengyuan.hicash.domain.event.user;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ExceptionMsg;
import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.exception.SaveDeviceException;
import com.hengyuan.hicash.exception.SaveSupplierException;
import com.hengyuan.hicash.parameters.request.user.SaveMyMessageReq;
import com.hengyuan.hicash.utils.DateUtils;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;

public class SaveMyMessageEvent {
	
	private static Logger logger = Logger.getLogger(SaveMyMessageEvent.class);
	
	private String uuid;
	/**
	 * 保存我的消息
	 * @param req
	 * @throws SaveSupplierException
	 */
	public void saveMyMessage(SaveMyMessageReq req) throws SaveDeviceException{
			
		Map<String, Object> setMap = new HashMap<String, Object>();
		
		setMap.put("username", req.getUserName());
		setMap.put("code", req.getCode());
		setMap.put("mobile", req.getMobile());
		setMap.put("id_no", req.getIdNo());
		setMap.put("name", req.getName());
		setMap.put("is_read","0");
		setMap.put("title", req.getTitle());
		setMap.put("content", req.getContent());
		setMap.put("operate", req.getOperate());
		setMap.put("type", req.getType());
		setMap.put("create_time", DateUtils.getDateStr(new Date()));
		if(StringUtils.isNotBlank(req.getAppNo())){
			setMap.put("appNo", req.getAppNo());
		}
		String updateSql = MapAssemForSql.getInsertSql(TableConsts.CUST_USER_MESSAGE,setMap);

		RecordUtils.writeAction(logger, uuid, updateSql);
		if (ConnManager.executeUpdate(updateSql) <= 0) {
			throw new SaveDeviceException(ExceptionMsg.SAVE_INFO_EXCEPTION);
		}	
	}

}
