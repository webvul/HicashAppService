package com.hengyuan.hicash.domain.event.user;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.exception.SaveInfoException;
import com.hengyuan.hicash.exception.UpdateInfoException;
import com.hengyuan.hicash.parameters.request.user.CallPhoneQuartetProtocalUpReq;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * 	电信签订四方协议,修改用户的email
 * 
 * @author lihua.Ren
 * @create date 2015-08-20
 *
 */
public class CallPHoneQuarteProtocalUpEvent{
	
	private static Logger logger = Logger.getLogger(CallPHoneQuarteProtocalUpEvent.class);

	/**
	 * 修改email信息
	 * 
	 * @param updateMsgReq
	 * @return
	 * @throws UpdateInfoException 
	 *
	 */
	public void updatEmail(CallPhoneQuartetProtocalUpReq updateMsgReq) throws UpdateInfoException{
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		setMap.put("email_adress", updateMsgReq.getEmail());
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("username", updateMsgReq.getUserName());
		
		String updateSql = MapAssemForSql.getUpdateSql(TableConsts.CUST_CUSTOMER, setMap, whereMap);
		//记录日志
		RecordUtils.writeAction(logger, updateMsgReq.getUuid(), updateSql);
		if(ConnManager.executeUpdate(updateSql) <= 0){
			throw new UpdateInfoException();
		}
	}
	

}
