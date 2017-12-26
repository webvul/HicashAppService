package com.hengyuan.hicash.domain.service.user;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.event.user.StuApp2UpdateEvent;
import com.hengyuan.hicash.domain.query.app.ApplicationQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.exception.UpdateContactException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.StuApp2UpdateReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.StuApp2UpdateResp;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * hicash手机端学生提现申请2完善逻辑类
 * 
 * @author lihua.Ren
 * @create date 2015-05-27
 *
 */
public class StuApp2UpdateService implements RespService {
	
	private static Logger logger = Logger.getLogger(StuApp2UpdateService.class);

	private StuApp2UpdateEvent constactMsgEvent = new StuApp2UpdateEvent();

	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		StuApp2UpdateReq updateMsgReq = (StuApp2UpdateReq) parmRequest;
		StuApp2UpdateResp updateMsgResp = new StuApp2UpdateResp();

		String resultCode = "";
		try {
			
			ConnManager.beginTransaction();
			
			if(Consts.UPDATE_UNIVERSITY_TYPE_P.equals(updateMsgReq.getUpdateType())){
				
				if(queryAppNoExist(updateMsgReq.getAppNo())){
					constactMsgEvent.updateContactInfo(updateMsgReq);
					ConnManager.commit();
					resultCode = ResultCodes.NORMAL;
				}else{
					resultCode = ResultCodes.STU_UPDATE_APPNO_ISNOTEXIST;
				}
				
			}else{
				constactMsgEvent.updateContactInfo(updateMsgReq);
				ConnManager.commit();
				resultCode = ResultCodes.NORMAL;
			}
			
			
			/* 一期客户信息服务暂不开放如下功能 Andy.Niu */
			/*boolean isExist = existQuery.isExistToContactInfo(updateMsgReq.getUserName());
			
			保存用户联系信息,isExist=true执行更新操作,isExist=false执行插入操作
			if(isExist){
				constactMsgEvent.updateContactinfo(updateMsgReq);
			}else{
				constactMsgEvent.saveContactInfo(updateMsgReq);
			}
			resultCode = ResultCodes.NORMAL;*/
		} catch (UpdateContactException e) {
			ConnManager.rollback();
			resultCode = ResultCodes.UPDATE_APP1_CONTACT_EXCEPTION;
		} catch (Exception e){
			ConnManager.rollback();
			resultCode = ResultCodes.UPDATE_APP1_CONTACT_EXCEPTION;
			/* 记录错误信息 */
			RecordUtils.writeError(logger, updateMsgReq.getUuid(), ResultCodes.CONTACT_APP_EXCEPTION, e);
			
		} finally {
			ConnManager.closeConn();
		}
	
		updateMsgResp.setResultCode(resultCode);
		return updateMsgResp;
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
