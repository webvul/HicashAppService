package com.hengyuan.hicash.domain.service.user;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.event.notic.NoticeEvent;
import com.hengyuan.hicash.domain.event.user.MobileUpdateEvent;
import com.hengyuan.hicash.domain.query.notic.NoticQuery;
import com.hengyuan.hicash.domain.query.user.MobileUpdateSendCodeQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.domain.service.notic.ExternalService;
import com.hengyuan.hicash.entity.notic.NoticeConfigure;
import com.hengyuan.hicash.entity.notic.NoticeObject;
import com.hengyuan.hicash.entity.notic.NoticeSendflow;
import com.hengyuan.hicash.entity.user.MobileUpdateEntity;
import com.hengyuan.hicash.exception.SendMobileMsgException;
import com.hengyuan.hicash.exception.UpdateAmountException;
import com.hengyuan.hicash.exception.UpdateMobileNoException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.MobileUpdateSendCodeReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.MobileUpdateSendCodeResp;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * 修改注册手机
 * 
 * @author Eric.shi
 * @create date 2014-07-23
 * 
 */
public class MobileUpdateSendCodeService implements RespService {
	
	private static Logger logger = Logger.getLogger(MobileUpdateSendCodeService.class);

	private String resultCode = ""; // 返回码
	MobileUpdateSendCodeQuery sendCodeQuery = new MobileUpdateSendCodeQuery();

	NoticQuery noticQuery = new NoticQuery();

	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		MobileUpdateSendCodeReq req = (MobileUpdateSendCodeReq) parmRequest;

		MobileUpdateSendCodeResp resp = new MobileUpdateSendCodeResp();
		try {
			ConnManager.beginTransaction();
			/* 获取原手机号 */
			MobileUpdateEntity entity = queryByMobile(req.getUserName());
			
			if(entity != null && entity.getMobile() != null&&req.getOldMobile().equals(entity.getMobile())){
			
				if (!req.getNewMobile().equals(entity.getMobile())) {
					
					/* 1.查询电话号码否已经存在*/
					boolean isMobileExists = isMobileExists(req.getNewMobile());
					System.out.println("更改手机验证");
					
					if (isMobileExists) {
						
						String validateCode = sendValidateCode(req.getNewMobile(), req.getUserName());
						
						// 保存验证码、接收验证码的手机和最后验证时间到数据库
						updateMobileValidateCode(req.getUserName(),validateCode,req.getNewMobile());
						
						resultCode = ResultCodes.NORMAL;
						ConnManager.commit();
						
					} else {
						ConnManager.rollback();
						resultCode = ResultCodes.UPDATE_NEW_MOBILE_EXIST;
					}
					
				} else {
					ConnManager.rollback();
					resultCode = ResultCodes.UPDATE_MOBILENO_ERROR;
				}
				
			}else{
				ConnManager.rollback();
				resultCode = ResultCodes.UPDATE_MOBILENO_OLD_FAIL;
			}
			
		} catch (SendMobileMsgException e) {
			ConnManager.rollback();
			resultCode = ResultCodes.UPDATE_MOBILE_SEND_EXCEPTION;
			
			/* 记录错误信息 */
			RecordUtils.writeError(logger, parmRequest.getUuid(), ResultCodes.UPDATE_MOBILE_SEND_EXCEPTION, e);
		} catch (UpdateMobileNoException e) {
			ConnManager.rollback();
			resultCode = ResultCodes.UPDATE_NEW_MOBILE_EXCEPTION;
			
			/* 记录错误信息 */
			RecordUtils.writeError(logger, parmRequest.getUuid(), ResultCodes.UPDATE_NEW_MOBILE_EXCEPTION, e);
		} catch (Exception e) {
			ConnManager.rollback();
			resultCode = ResultCodes.UPDATE_MOBILE_EXCEPTION;
			
			/* 记录错误信息 */
			RecordUtils.writeError(logger, parmRequest.getUuid(), ResultCodes.UPDATE_MOBILE_EXCEPTION, e);
		} finally {
			ConnManager.closeConn();
		}
		
		resp.setResultCode(resultCode);
		return resp;
	}

	/**
	 * 获取原手机号码
	 * 
	 * @param usernaem
	 *            用户名
	 * */
	public MobileUpdateEntity queryByMobile(String username) {
		MobileUpdateEntity entity = sendCodeQuery.queryByMobile(username);
		return entity != null ? entity : new MobileUpdateEntity();
	}

	/**
	 * 获取原手机号码
	 * 
	 * @param usernaem
	 *            用户名
	 * */
	public boolean isMobileExists(String mobile) {
		MobileUpdateEntity entity = sendCodeQuery.isMobileExists(mobile);
		if (entity != null) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 获取原手机号码
	 * 
	 * @param usernaem
	 *            用户名
	 * */
	public NoticeConfigure queryNoticeConfigure(String type) {
		NoticeConfigure entity = noticQuery.queryNoticeConfigure(type);
		return entity != null ? entity : new NoticeConfigure();
	}

	/**
	 * 生成验证码短信
	 * */
	public String sendValidateMsg(NoticeSendflow noticeFlow,
			NoticeObject noticeObject) {
		if (noticeFlow == null) {
			return null;
		} else {
			NoticeEvent noticeEvent = new NoticeEvent();
			noticeEvent.recordNotice(noticeFlow, noticeObject);
		}
		return null;
	}

	/**
	 * 保存验证码和最后验证时间到数据库
	 * @throws UpdateMobileNoException 
	 * @throws UpdateAmountException 
	 * */
	public void updateMobileValidateCode(String username, String validateCode,String newMobile) 
			throws UpdateMobileNoException, UpdateAmountException {
		
		MobileUpdateEvent event = new MobileUpdateEvent();
		event.updateMobileValidateCode(username, validateCode ,newMobile);
	}

	/**
	 * 发送验证码
	 * 
	 * @throws SendMobileMsgException
	 * */
	public String sendValidateCode(String mobile, String username)
			throws SendMobileMsgException {
		ExternalService externalService = new ExternalService();
		String validateCode = externalService.sendValidateMessage(mobile,
				username);
		return validateCode;
	}

}
