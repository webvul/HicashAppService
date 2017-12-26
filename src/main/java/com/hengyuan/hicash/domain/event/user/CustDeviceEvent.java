package com.hengyuan.hicash.domain.event.user;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ExceptionMsg;
import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.exception.SaveDeviceException;
import com.hengyuan.hicash.exception.SaveSupplierException;
import com.hengyuan.hicash.parameters.request.user.CustDeviceReq;
import com.hengyuan.hicash.utils.DateUtils;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;


/**
 * 
* @author dong.liu 
* 2017-4-7 下午5:49:02
* 类说明:保存设备信息
 */
public class CustDeviceEvent {
	
	private static Logger logger = Logger.getLogger(CustDeviceEvent.class);
	
	private String uuid;
	
	
	
	/**
	 * 保存商户信息
	 * @param req
	 * @throws SaveSupplierException
	 */
	public void saveCustDevice(CustDeviceReq req) throws SaveDeviceException{
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		
		setMap.put("username", req.getUsername());
		setMap.put("app_no", req.getApp_no());
		setMap.put("type", req.getType());
		setMap.put("sb_system", req.getSb_system());
		setMap.put("sb_type", req.getSb_type());
		setMap.put("udid", req.getUdid());
		setMap.put("create_time", DateUtils.getDateStr(new Date()));
		String updateSql = MapAssemForSql.getInsertSql(TableConsts.CUST_DEVICE,setMap);

		RecordUtils.writeAction(logger, uuid, updateSql);
		if (ConnManager.executeUpdate(updateSql) <= 0) {
			throw new SaveDeviceException(ExceptionMsg.SAVE_INFO_EXCEPTION);
		}
		
	}

	
}
