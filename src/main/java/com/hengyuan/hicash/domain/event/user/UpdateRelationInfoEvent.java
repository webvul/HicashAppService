package com.hengyuan.hicash.domain.event.user;

import java.util.HashMap;
import java.util.Map;

import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.exception.UpdateCustomerException;
import com.hengyuan.hicash.parameters.request.user.RelationInfoReq;
import com.hengyuan.hicash.utils.MapAssemForSql;

/**
 * 
 * @author fish
 *
 * @date 2017年1月11日 上午11:24:48
 */
public class UpdateRelationInfoEvent {

	/**
	 * 更新用户联系人信息
	 * 
	 * @param req
	 * @throws UpdateCustomerException
	 */
	public void updateCustomerRelationInfo(RelationInfoReq req) throws UpdateCustomerException {

		Map<String, Object> setMap = new HashMap<String, Object>();

		setMap.put("IMMEDIATE_NAME", req.getImmediate_name());
		setMap.put("IMMEDIATE_RELATION", req.getImmediate_relation());
		setMap.put("IMMEDIATE_MOBILE", req.getImmediate_mobile());

		setMap.put("EMERGENCY_NAME", req.getEmergency_name());
		setMap.put("EMERGENCY_RELATION", req.getEmergency_relation());
		setMap.put("EMERGENCY_MOBILE", req.getEmergency_mobile());

		if (null != req.getSpouse_name() && !"".equals(req.getSpouse_name())) {
			setMap.put("SPOUSE_NAME", req.getSpouse_name());
		}
		if (null != req.getSpouse_mobile() && !"".equals(req.getSpouse_mobile())) {
			setMap.put("SPOUSE_MOBILE", req.getSpouse_mobile());
		}

		Map<String, Object> wheremMap = new HashMap<String, Object>();

		wheremMap.put("USERNAME", req.getUserName());

		String sql = MapAssemForSql.getUpdateSql(TableConsts.CUST_CUSTOMER, setMap, wheremMap);
		if (ConnManager.executeUpdate(sql) <= 0) {
			throw new UpdateCustomerException();
		}
	}

}
