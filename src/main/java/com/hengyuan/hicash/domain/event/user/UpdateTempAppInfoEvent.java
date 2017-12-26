package com.hengyuan.hicash.domain.event.user;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.exception.SaveSupplierException;
import com.hengyuan.hicash.exception.UpdateTempAppException;
import com.hengyuan.hicash.parameters.request.user.UpdateTempAppInfoReq;
import com.hengyuan.hicash.utils.MapAssemForSql;

/** 
 * @author dong.liu 
 * 2017-3-2 下午2:44:51
 * 类说明 :临时订单保存验证状态
 */
public class UpdateTempAppInfoEvent {

	
	private static Logger logger = Logger.getLogger(UpdateTempAppInfoEvent.class);
	
	
	/**
	 * 保存商户信息
	 * @param req
	 * @throws SaveSupplierException
	 */
	public void updateTempAppInfo(UpdateTempAppInfoReq req) throws UpdateTempAppException{
		

		Map<String, Object> setMap = new HashMap<String, Object>();

		setMap.put("validate_type", req.getValidateType());
		setMap.put("validate_typea", req.getValidateTypea());

		Map<String, Object> wheremMap = new HashMap<String, Object>();

		wheremMap.put("TEMP_APPLICATION_NO", req.getTempAppNo());

		String sql = MapAssemForSql.getUpdateSql(TableConsts.TEMP_APPLY_INFO, setMap, wheremMap);
		if (ConnManager.executeUpdate(sql) <= 0) {
			throw new UpdateTempAppException();
		}
		
	}
	
	/**
	 * 保存司机贷信息
	 * @param req
	 * @throws SaveSupplierException
	 */
	public void UpdateTempValStatus(UpdateTempAppInfoReq req) throws UpdateTempAppException{
		

		Map<String, Object> setMap = new HashMap<String, Object>();

		setMap.put("validate_type", req.getValidateType());
		setMap.put("validate_typea", req.getValidateTypea());

		Map<String, Object> wheremMap = new HashMap<String, Object>();

		wheremMap.put("TEMP_APPLICATION_NO", req.getTempAppNo());

		String sql = MapAssemForSql.getUpdateSql(TableConsts.DDSJ_TEMPAPPLY_INFO, setMap, wheremMap);
		if (ConnManager.executeUpdate(sql) <= 0) {
			throw new UpdateTempAppException();
		}
		
	}
	/**
	 * 保存嗨女神信息
	 * @param req
	 * @throws SaveSupplierException
	 */
	public void updateNSAppInfo(UpdateTempAppInfoReq req) throws UpdateTempAppException{
		

		Map<String, Object> setMap = new HashMap<String, Object>();

		setMap.put("validate_type", req.getValidateType());
		setMap.put("validate_typea", req.getValidateTypea());

		Map<String, Object> wheremMap = new HashMap<String, Object>();

		wheremMap.put("TEMP_APPLICATION_NO", req.getTempAppNo());

		String sql = MapAssemForSql.getUpdateSql(TableConsts.CREDIT_TEMP_PAY, setMap, wheremMap);
		if (ConnManager.executeUpdate(sql) <= 0) {
			throw new UpdateTempAppException();
		}
		
	}

}
