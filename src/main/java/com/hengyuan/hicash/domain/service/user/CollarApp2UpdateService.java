package com.hengyuan.hicash.domain.service.user;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.event.user.CollarApp2UpdateEvent;
import com.hengyuan.hicash.domain.query.app.ApplicationQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.exception.UpdateInfoException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.CollarApp2UpdateReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.CollarApp2UpdateResp;
import com.hengyuan.hicash.utils.RecordUtils;



/**
 * 白领完善资料业务处理
 * 
 * @author LiHua.Ren
 * @create date 2015-05-23
 * 
 */
public class CollarApp2UpdateService implements RespService {
	
	private static Logger logger = Logger.getLogger(CollarApp2UpdateService.class);

	private CollarApp2UpdateEvent collarUpdateMsgEvent = new CollarApp2UpdateEvent();

	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		CollarApp2UpdateReq collarUpdateMsgReq = (CollarApp2UpdateReq) parmRequest;
		CollarApp2UpdateResp collarUpdateMsgResp = new CollarApp2UpdateResp();
		String resultCode = "";
		String resultMsg = ""; // 返回消息
		try {

			ConnManager.beginTransaction();
			
			if(Consts.UPDATE_UNIVERSITY_TYPE_P.equals(collarUpdateMsgReq.getUpdateType())){
				
				if(queryAppNoExist(collarUpdateMsgReq.getAppNo())){
					collarUpdateMsgEvent.updateCollarMsg(collarUpdateMsgReq);
					ConnManager.commit();
					resultCode = ResultCodes.NORMAL;
				}else{
					resultCode = ResultCodes.STU_UPDATE_APPNO_ISNOTEXIST;
				}
				
			}else{
				collarUpdateMsgEvent.updateCollarMsg(collarUpdateMsgReq);
				ConnManager.commit();
				resultCode = ResultCodes.NORMAL;
			}

			
			
			/*boolean isExist = existQuery
					.isExistToPersonalInfo(collarUpdateMsgReq.getUserName());

			 isExist = true 更新数据 isExist = true 插入数据 
			if (isExist) {
				collarUpdateMsgEvent.updatePersonalinfo(collarUpdateMsgReq);
			} else {
				collarUpdateMsgEvent.savePersonalInfo(collarUpdateMsgReq);
			}*/

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

		collarUpdateMsgResp.setResultCode(resultCode);
		collarUpdateMsgResp.setResultMsg(resultMsg);
		return collarUpdateMsgResp;
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
