package com.hengyuan.hicash.domain.event.user;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.exception.UpdateTempAppException;
import com.hengyuan.hicash.parameters.request.user.UpdateMsgStatusReq;
import com.hengyuan.hicash.utils.MapAssemForSql;

/** 
 * 类说明 :修改信息状态:1/已读,0/未读
 */
public class UpdateMsgStatusEvent {
	
	private static Logger logger = Logger.getLogger(UpdateMsgStatusEvent.class);
	
	public void UpdateMsgStatus(UpdateMsgStatusReq req) throws UpdateTempAppException{
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		
		setMap.put("is_read", 1);
		
		Map<String, Object> wheremMap = new HashMap<String, Object>();
		
		wheremMap.put("id", req.getId());
		
		String sql = MapAssemForSql.getUpdateSql(TableConsts.CUST_USER_MESSAGE, setMap, wheremMap);
		logger.info("SQL执行成功");
		if (ConnManager.executeUpdate(sql) <= 0) {
			throw new UpdateTempAppException();
		}
		
	}
	
}
