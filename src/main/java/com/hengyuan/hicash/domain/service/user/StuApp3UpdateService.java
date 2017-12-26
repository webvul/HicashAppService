package com.hengyuan.hicash.domain.service.user;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.event.user.StuApp3UpdateEvent;
import com.hengyuan.hicash.domain.query.app.ApplicationQuery;
import com.hengyuan.hicash.domain.query.notic.NoticeToApproveService;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.ServiceConvertEntity;
import com.hengyuan.hicash.exception.UpdateContactException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.StuApp3UpdateReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.StuApp3UpdateResp;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * hicash手机端学生提现申请3完善逻辑类
 * 
 * @author lihua.Ren
 * @create date 2015-05-27
 *
 */
public class StuApp3UpdateService implements RespService{


	private static Logger logger = Logger.getLogger(StuApp3UpdateService.class);

	private StuApp3UpdateEvent updateMsgEvent = new StuApp3UpdateEvent();

	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		StuApp3UpdateReq updateMsgReq = (StuApp3UpdateReq) parmRequest;
		StuApp3UpdateResp updateMsgResp = new StuApp3UpdateResp();

		String resultCode = "";

		try {

			if(Consts.UPDATE_UNIVERSITY_TYPE_P.equals(updateMsgReq.getUpdateType())){
				
				if(queryAppNoExist(updateMsgReq.getAppNo())){
					updateMsgEvent.updateContactsInfo(updateMsgReq);
					
					/* 远程调用提交申请接口 */
//					String resultStr = remoteHttp(updateMsgReq);
					
					/* 短信通知业务员 */
					sendToApprove(updateMsgReq.getUserName(), updateMsgReq.getAppNo());
					
					resultCode = ResultCodes.NORMAL;
				}else{
					resultCode = ResultCodes.STU_UPDATE_APPNO_ISNOTEXIST;
				}
				
			}else{
				updateMsgEvent.updateContactsInfo(updateMsgReq);
				resultCode = ResultCodes.NORMAL;
			}
			
			
			/* 客户信息服务一期暂不开放 Andy.Niu */
			/*boolean isExist = existQuery.isExistToContactsInfo(updateMsgReq.getUserName());
			
			isExist = true 更新数据 isExist = false 插入数据
			
			if (isExist) {
				updateMsgEvent.updateContactsinfo(updateMsgReq);
			} else {
				updateMsgEvent.saveContactsInfo(updateMsgReq);
			}*/
			
		} catch (UpdateContactException e) {
			resultCode = ResultCodes.UPDATE_APP_EXCEPTION;
			
			/* 记录错误信息 */
//			RecordUtils.writeError(logger, updateMsgReq.getUuid(), ResultCodes.UPDATE_CONTACTS_EXCEPTION, e);
//		}catch (HttpException e) {
//			RecordUtils.writeError(logger, parmRequest.getUuid(), ResultCodes.REMOTE_ERROR, e);
//			resultCode = ResultCodes.REMOTE_ERROR;
//		} catch (IOException e) {
//			RecordUtils.writeError(logger, parmRequest.getUuid(), ResultCodes.REMOTE_RETURN_ERROR, e);
//			resultCode =  ResultCodes.REMOTE_RETURN_ERROR;
//		} catch (HttpReturnException e) {
//			RecordUtils.writeError(logger, parmRequest.getUuid(), ResultCodes.REMOTE_SERVICE_ERROR, e);
//			resultCode =  ResultCodes.REMOTE_SERVICE_ERROR;
//		} catch (HttpUrlRemoteException e) {
//			RecordUtils.writeError(logger, parmRequest.getUuid(), ResultCodes.REMOTE_URL_ERROR, e);
//			resultCode =  ResultCodes.REMOTE_URL_ERROR;
		} catch (Exception e){
			resultCode = ResultCodes.APP_EXCEPTION;
			/* 记录错误信息 */
			RecordUtils.writeError(logger, updateMsgReq.getUuid(), ResultCodes.APP_EXCEPTION, e);
		} finally {
			ConnManager.closeConn();
		}
		
		updateMsgResp.setResultCode(resultCode);
		return updateMsgResp;
	}
	
	
	public String getUrl(ServiceConvertEntity serviceConvertEntity){
		StringBuffer buffer = new StringBuffer("http://");
		buffer.append(serviceConvertEntity.getServiceIp());
		if(!StringUtils.isEmpty(serviceConvertEntity.getServicePort())){
			buffer.append(":" + serviceConvertEntity.getServicePort());
		}
		buffer.append(serviceConvertEntity.getServiceUrl());
		return buffer.toString();
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
	
	/**
	 * 短信通知业务员
	 * @param userName
	 * @param appNo
	 */
	public void sendToApprove(String userName,String appNo){
		
		NoticeToApproveService noticeToApprove = new NoticeToApproveService();
		
		noticeToApprove.sendToApprove(userName, appNo);
	}

}
