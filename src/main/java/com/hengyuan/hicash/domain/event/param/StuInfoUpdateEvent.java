package com.hengyuan.hicash.domain.event.param;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.exception.UpdateInfoException;
import com.hengyuan.hicash.parameters.request.param.StuInfoReq;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * 
* @author dong.liu 
* 2017-1-10 下午3:44:58
* 类说明 :学生个人信息修改
 */
public class StuInfoUpdateEvent {
	
	private static Logger logger = Logger.getLogger(StuInfoUpdateEvent.class);

	/**
	 * 修改个人信息
	 * 
	 * @param req
	 * @return
	 * @throws UpdateInfoException 
	 */
	public void updateCollarMsg(StuInfoReq req) throws UpdateInfoException {
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		
		setMap.put("NAME", req.getRealName());// 用户真实姓名
		setMap.put("IDENTITY_NO", req.getIdentityNo()); // 身份证号码
		setMap.put("MARITAL_STATUS", req.getMaritalStatus());// 婚姻状况
		setMap.put("OTHER_ADRESS_PROVINCE", req.getOtherAdressProvince());// 现居住省
		setMap.put("OTHER_ADRESS_CITY", req.getOtherAdressCity());// 现居住市
		setMap.put("OTHER_ADRESS_AREA", req.getOtherAdressArea());// 现居住区
		setMap.put("OTHER_ACCOMMODATION_ADDRESS", req.getOtherAccommodationAddress());// 现居街道住址
		setMap.put("EMAIL_ADRESS", req.getEmailAdress());// 电子邮箱
//		setMap.put("IDCARD_VALIDITY_STARTDATE", req.getIdcard_validity_startdate());
//		setMap.put("IDCARD_VALIDITY_ENDDATE", req.getIdcard_validity_enddate());
		setMap.put("borrow_money_use", req.getLoan_purpose());
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("username", req.getUserName());
		
		String updateSql = MapAssemForSql.getUpdateSql(TableConsts.CUST_CUSTOMER, setMap, whereMap);
		//记录日志
		RecordUtils.writeAction(logger, req.getUuid(), updateSql);
		if(ConnManager.executeUpdate(updateSql) <= 0){
			throw new UpdateInfoException();
		}
	}

}
