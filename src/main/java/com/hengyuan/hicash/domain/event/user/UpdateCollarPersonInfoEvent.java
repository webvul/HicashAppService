package com.hengyuan.hicash.domain.event.user;

import java.util.HashMap;
import java.util.Map;
import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.exception.UpdateCustomerException;
import com.hengyuan.hicash.parameters.request.user.CollarPersonReq;
import com.hengyuan.hicash.utils.MapAssemForSql;

/**
 * 
 * @author fish
 *
 * @date 2017年1月10日 上午11:12:27
 */
public class UpdateCollarPersonInfoEvent {

	/**
	 * 更新用户个人信息
	 * 
	 * @param userInfo
	 * @throws UpdateCustomerException
	 */
	public void updateCustomerPersonInfo(CollarPersonReq req) throws UpdateCustomerException {

		Map<String, Object> setMap = new HashMap<String, Object>();

		setMap.put("NAME", req.getReal_name());
		setMap.put("IDENTITY_NO", req.getId_no());
		setMap.put("MARITAL_STATUS", req.getMarital_status());
		setMap.put("NOW_EDUCATION", req.getEducation_code());
		setMap.put("OTHER_ADRESS_PROVINCE", req.getProvince());
		setMap.put("OTHER_ADRESS_CITY", req.getCity());
		setMap.put("OTHER_ADRESS_AREA", req.getArea());
		setMap.put("OTHER_ACCOMMODATION_ADDRESS", req.getAddress());
		setMap.put("EMAIL_ADRESS", req.getEmail());
		setMap.put("UNITNAME", req.getUnit_name());
		setMap.put("UNIT_TEL_AREA", req.getUnit_phone_area());
		setMap.put("UNIT_TEL", req.getUnit_phone());
		setMap.put("WORK_AREA_PROVINCE", req.getUnit_province());
		setMap.put("WORK_AREA_CITY", req.getUnit_city());
		setMap.put("WORK_AREA_AREA", req.getUnit_area());
		setMap.put("WORK_AREA_ROAD", req.getUnit_address());
		setMap.put("WORKEXPERIENCE", req.getWork_year());
		setMap.put("FULLTIMEDRIVER", req.getFulltimeDriver());
		setMap.put("QQNUMBER", req.getQqNumber());
		setMap.put("unit_properties", req.getUnit_properties());
//		setMap.put("CREDIT_CARD", req.getCredit_card());
//		if(null != req.getIdcard_validity_startdate() && !"".equals(req.getIdcard_validity_startdate())){
//			setMap.put("IDCARD_VALIDITY_STARTDATE", req.getIdcard_validity_startdate());
//		}
//		if(null != req.getIdcard_validity_enddate() && !"".equals(req.getIdcard_validity_enddate())){
//			setMap.put("IDCARD_VALIDITY_ENDDATE", req.getIdcard_validity_enddate());
//		}
		setMap.put("borrow_money_use", req.getLoan_purpose());
		Map<String, Object> wheremMap = new HashMap<String, Object>();

		wheremMap.put("USERNAME", req.getUserName());

		String sql = MapAssemForSql.getUpdateSql(TableConsts.CUST_CUSTOMER, setMap, wheremMap);
		if (ConnManager.executeUpdate(sql) <= 0) {
			throw new UpdateCustomerException();
		}
	}

}
