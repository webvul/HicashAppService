package com.hengyuan.hicash.domain.service.mktApp;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.event.user.SaveMyMessageEvent;
import com.hengyuan.hicash.domain.event.user.SaveMyMsgEvent;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.exception.SaveDeviceException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.CustDeviceReq;
import com.hengyuan.hicash.parameters.request.user.SaveMyMessageReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.SaveMyMessageResp;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * 
* @author xing.yuan
* 类说明:保存我的消息
 */
public class SaveMyMessageService implements RespService{
	
	private static Logger logger = Logger
			.getLogger(SaveMyMessageService.class);
	
	private SaveMyMessageEvent event=new SaveMyMessageEvent();
	
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {
		
		SaveMyMessageResp resp=new SaveMyMessageResp();
		String resultCode = "";
		Long start = System.currentTimeMillis();
		SaveMyMessageReq req = (SaveMyMessageReq) parmRequest;
		logger.info("单号:"+req.getUserName()+"开始保存我的信息");
		try {
			event.saveMyMessage(req);
			resultCode=ResultCodes.NORMAL;
			logger.info("单号:"+req.getUserName()+"保存我的消息成功");
		} catch (SaveDeviceException e) {
			resultCode = ResultCodes.DDSJ_MY_MESSAGE_IS_NULL;
			RecordUtils.writeError(logger, null, resultCode, e);
		}catch (Exception e) {
			resultCode = ResultCodes.DDSJ_CUST_DEVICE_EXCEPTION;
			e.printStackTrace();
		}finally {
			ConnManager.closeConn();
		}	
			
		logger.info("耗时[" + (System.currentTimeMillis() - start) + "]ms");
		resp.setResultCode(resultCode);
		return resp;
	}

}
