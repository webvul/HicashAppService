package com.hengyuan.hicash.domain.event.user;

import java.util.HashMap;
import java.util.Map;
import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.exception.UpdateCustomerException;
import com.hengyuan.hicash.parameters.request.user.UpdateCustCardReq;
import com.hengyuan.hicash.utils.MapAssemForSql;

/**
 * 
 * @author fish
 *
 * @date 2017年1月15日 下午2:16:27
 */
public class UpdateCustCardEvent {

	/**
	 * 更新用户个人信息
	 * 
	 * @param userInfo
	 * @throws UpdateCustomerException
	 */
	public void updateCustomerPersonInfo(UpdateCustCardReq req) throws UpdateCustomerException {

		Map<String, Object> setMap = new HashMap<String, Object>();

		setMap.put("PERMANENTADDRESS_PROVINCE", req.getPermanentAddressProvince());
		setMap.put("PERMANENTADDRESS_CITY", req.getPermanentAddressCity());
		setMap.put("PERMANENTADDRESS_AREA", req.getPermanentAddressArea());
		setMap.put("PERMANENTADDRESS_RAOD", req.getPermanentAddressRaod());
		setMap.put("NATION", req.getNation());
		setMap.put("IDCARD_VALIDITY_STARTDATE", req.getIdCardValStartDate());
		setMap.put("IDCARD_VALIDITY_ENDDATE", req.getIdCardValEndDate());
		setMap.put("NAME", req.getName());
		setMap.put("IDENTITY_NO", req.getIdentityNo());
		setMap.put("IDCARD_FROM", req.getIdcardFrom());

		Map<String, Object> wheremMap = new HashMap<String, Object>();

		wheremMap.put("USERNAME", req.getUserName());

		String sql = MapAssemForSql.getUpdateSql(TableConsts.CUST_CUSTOMER, setMap, wheremMap);
		if (ConnManager.executeUpdate(sql) <= 0) {
			throw new UpdateCustomerException();
		}
	}

}
