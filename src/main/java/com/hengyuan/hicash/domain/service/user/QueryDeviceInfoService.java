package com.hengyuan.hicash.domain.service.user;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.query.user.DeviceInfoQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.user.DeviceInfo;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.QueryDeviceReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.QueryDeviceResp;

/**
 * 查询滴滴司机贷能否授信
 * 
 * @author teng
 *
 */
public class QueryDeviceInfoService implements RespService {


	private DeviceInfoQuery deviceInfoQuery = new DeviceInfoQuery();

	public ParmResponse responseValue(ParmRequest parmRequest) {

		QueryDeviceReq req = (QueryDeviceReq) parmRequest;
		QueryDeviceResp resp = new QueryDeviceResp();
        String resultCode="";
		try {
			String app_no = req.getApp_no();

			DeviceInfo vo = deviceInfoQuery.queryApplyCredit(app_no);
			resultCode = ResultCodes.NORMAL;
			if(vo==null||"".equals(vo.getApp_no())||null==vo.getApp_no()){
				resultCode=ResultCodes.DDSJ_DEVICE_IS_NULL;
			}else{
				resp.setSb_system(vo.getSb_system());
				resp.setApp_no(vo.getApp_no());
				resp.setSb_type(vo.getSb_type());
				resp.setUdid(vo.getUdid());
				resp.setUsername(vo.getUsername());
			}

		} catch (Exception e) {
			resultCode = ResultCodes.CHECK_CREDIT_EXCEPTION;
		}
		resp.setResultCode(resultCode);
		return resp;
	}

}
