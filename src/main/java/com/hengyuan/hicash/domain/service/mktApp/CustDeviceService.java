package com.hengyuan.hicash.domain.service.mktApp;
import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.event.user.CustDeviceEvent;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.exception.SaveDeviceException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.CustDeviceReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.CustDeviceResp;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * 
* @author dong.liu 
* 2017-4-7 下午6:06:41
* 类说明:保存设备信息
 */
public class CustDeviceService implements RespService {

	private static Logger logger = Logger
			.getLogger(CustDeviceService.class);

	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {
     
		CustDeviceResp resp = new CustDeviceResp();
		String resultCode = "";
		Long start = System.currentTimeMillis();
		CustDeviceReq req = (CustDeviceReq) parmRequest;
		 logger.info("单号:"+req.getApp_no()+"开始保存设备信息");
		try {
			CustDeviceEvent event=new CustDeviceEvent();
			 event.saveCustDevice(req);
			 resultCode=ResultCodes.NORMAL;
		} catch (SaveDeviceException e) {
			resultCode = ResultCodes.DDSJ_CUST_DEVICE_EXCEPTION;
			RecordUtils.writeError(logger, null, resultCode, e);
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			resultCode = ResultCodes.DDSJ_CUST_DEVICE_EXCEPTION;
			e.printStackTrace();
		} 
		finally {
			ConnManager.closeConn();
		}
		System.out.println("耗时[" + (System.currentTimeMillis() - start) + "]ms");
		resp.setResultCode(resultCode);

		return resp;
	}


}
