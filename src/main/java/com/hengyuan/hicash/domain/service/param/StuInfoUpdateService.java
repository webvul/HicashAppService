package com.hengyuan.hicash.domain.service.param;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.event.param.StuInfoUpdateEvent;
import com.hengyuan.hicash.domain.query.app.ApplicationQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.exception.UpdateInfoException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.param.StuInfoReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.param.StuInfoResp;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * 
* @author dong.liu 
* 2017-1-10 下午3:44:58
* 类说明 :学生个人信息修改
 */
public class StuInfoUpdateService implements RespService {
	
	private static Logger logger = Logger.getLogger(StuInfoUpdateService.class);

	private StuInfoUpdateEvent stuInfoUpdateEvent=new StuInfoUpdateEvent();

	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		
		StuInfoReq stuInfoReq = (StuInfoReq) parmRequest;
		StuInfoResp stuInfoResp=new StuInfoResp();
		String resultCode = "";
		String resultMsg = ""; // 返回消息
		try {
			ConnManager.beginTransaction();
			
			         stuInfoUpdateEvent.updateCollarMsg(stuInfoReq);
					ConnManager.commit();
					resultCode = ResultCodes.NORMAL;
		} catch (UpdateInfoException e) {
			ConnManager.rollback();
			resultCode = ResultCodes.COLLAR_INFO_SAVE_EXCEPTION;
			
			/* 记录错误信息 */
			RecordUtils.writeError(logger, parmRequest.getUuid(), ResultCodes.COLLAR_INFO_SAVE_EXCEPTION, e);
		} catch (Exception e) {
			ConnManager.rollback();
			resultCode = ResultCodes.COLLAR_INFO_EXCEPTION;
			/* 记录错误信息 */
			RecordUtils.writeError(logger, parmRequest.getUuid(), ResultCodes.COLLAR_INFO_EXCEPTION, e);
		} finally {
			ConnManager.closeConn();
		}

		stuInfoResp.setResultCode(resultCode);
		stuInfoResp.setResultMsg(resultMsg);
		return stuInfoResp;
	}
	
	/**
	 * 查询流水号是否存在
	 * @param appNo
	 * @return
	 */
	public boolean queryAppNoExist(String appNo){
		
		ApplicationQuery applicationQuery = new ApplicationQuery();
		return applicationQuery.queryAppNoExist(appNo);
	}

}
