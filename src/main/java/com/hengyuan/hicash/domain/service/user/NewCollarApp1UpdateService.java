package com.hengyuan.hicash.domain.service.user;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.event.user.CollarApp1UpdateEvent;
import com.hengyuan.hicash.domain.event.user.InputAppEvent;
import com.hengyuan.hicash.domain.event.user.NewCollarApp1UpdateEvent;
import com.hengyuan.hicash.domain.query.app.ApplicationQuery;
import com.hengyuan.hicash.domain.query.app.InputAppPayQuery;
import com.hengyuan.hicash.domain.query.user.CustomerQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.app.InputAppPay;
import com.hengyuan.hicash.entity.user.CustomerEntity;
import com.hengyuan.hicash.exception.UpdateInfoException;
import com.hengyuan.hicash.exception.UpdateInputAppException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.CollarApp1UpdateReq;
import com.hengyuan.hicash.parameters.request.user.NewCollarApp1UpdateReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.CollarApp1UpdateResp;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * 白领完善资料业务处理1
* @author lihua.Ren
 * @create date 2015-06-17
 *
 */
public class NewCollarApp1UpdateService implements RespService {
	
	private static Logger logger = Logger.getLogger(NewCollarApp1UpdateService.class);

	private NewCollarApp1UpdateEvent collarUpdateMsgEvent = new NewCollarApp1UpdateEvent();

	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		NewCollarApp1UpdateReq collarUpdateMsgReq = (NewCollarApp1UpdateReq) parmRequest;
		CollarApp1UpdateResp collarUpdateMsgResp = new CollarApp1UpdateResp();
		String resultCode = "";
		String resultMsg = ""; // 返回消息
		try {

			ConnManager.beginTransaction();
			
			if(Consts.UPDATE_UNIVERSITY_TYPE_P.equals(collarUpdateMsgReq.getUpdateType())){
				
				if(queryAppNoExist(collarUpdateMsgReq.getAppNo())){
					
					
						
						collarUpdateMsgEvent.updateCollarMsg(collarUpdateMsgReq);
//						updateSpouseInfo(collarUpdateMsgReq);
						ConnManager.commit();
						resultCode = ResultCodes.NORMAL;
					
					
				}else{
					resultCode = ResultCodes.STU_UPDATE_APPNO_ISNOTEXIST;
				}
			}else{
				collarUpdateMsgEvent.updateCollarMsg(collarUpdateMsgReq);
//				updateSpouseInfo(collarUpdateMsgReq);
				ConnManager.commit();
				resultCode = ResultCodes.NORMAL;
			}
				


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
	
//	/*查询用户的appNO是否存在*/
//	public boolean queryAppNoExist(String appNo, String userName) {
//		InputAppPayQuery inputQuery = new InputAppPayQuery();
//		InputAppPay input= inputQuery.queryInputAppByAppNoAndUser(appNo, userName);
//		if (input != null) {
//			return true;
//		}
//		return false;
//	}
	
	/**
	 * 配偶手机号码是否与当前用户手机号相同
	 * @param userName
	 * @param spouseMobile
	 * @return
	 */
	public boolean isSpouseMobile(String userName,String spouseMobile){
		
		CustomerQuery custQuery = new CustomerQuery();
		CustomerEntity cust = custQuery.queryCustomerByUserName(userName);
		if(cust != null && cust.getMobile() != ""){
			if(cust.getMobile().equals(spouseMobile.trim())){
				return true;
			}
		}
		
		return false;
	}
	public void updateSpouseInfo(CollarApp1UpdateReq collarUpdateMsgReq) throws UpdateInputAppException
			 {

		InputAppEvent event = new InputAppEvent();

		Map<String, Object> setMap = new HashMap<String, Object>();
		// 如果婚姻状况是已婚
		if (Consts.HUNYIN_Q001.equals(collarUpdateMsgReq.getMatrimonySystem())) {
			setMap.put("spouseMobileNo", collarUpdateMsgReq.getSpouseMobile());
			setMap.put("spouseRealName", collarUpdateMsgReq.getSpouseName());
		} 
//		
//		else {
//			setMap.put("spouseMobileNo", "");
//			setMap.put("spouseRealName", "");
//		}
		event.updateInputAppInfo(collarUpdateMsgReq.getAppNo(), setMap);
	}
}
