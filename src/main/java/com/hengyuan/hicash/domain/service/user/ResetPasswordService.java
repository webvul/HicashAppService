package com.hengyuan.hicash.domain.service.user;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.event.user.ResetPasswordEvent;
import com.hengyuan.hicash.domain.query.user.CustUserQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.domain.service.notic.ExternalService;
import com.hengyuan.hicash.entity.user.CustUserEntity;
import com.hengyuan.hicash.exception.SendMobileMsgException;
import com.hengyuan.hicash.exception.UpdatePassWordException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.ResetPasswordReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.ResetPasswordResp;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * 忘记密码 更新新密码
 * 
 * @author Eric.shi
 * @create date 2014-07-24
 * 
 */
public class ResetPasswordService implements RespService {

	private static Logger logger = Logger.getLogger(ResetPasswordService.class);
	
	CustUserQuery custUserQuery = new CustUserQuery();

	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {
		ResetPasswordReq req = (ResetPasswordReq) parmRequest;

		ResetPasswordResp resp = new ResetPasswordResp();
		try {
			
			ConnManager.beginTransaction();
			/* 0.根据用户名获取用户*/
			CustUserEntity entity = queryByUserName(req.getUserName());

			/* 1.新密码加密*/
			String salt = RandomStringUtils.random(20, true, true);
			Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
			String password = md5PasswordEncoder.encodePassword(req.getNewPassword(), salt);

			/* 开始更新数据库*/
			updatePassword(salt, password, req.getUserName());

			/* 发送修改密码成功短信*/
			sendValidateCode(req.getUserName(), entity.getMobileNo(),req.getNewPassword());
		
			resp.setResultCode(ResultCodes.NORMAL);
			ConnManager.commit();
		} catch (SendMobileMsgException e) {
			ConnManager.rollback();
			resp.setResultCode(ResultCodes.RESET_PASSWORD_SEND_EXCEPTION);
			
			/* 记录错误信息 */
			RecordUtils.writeError(logger, req.getUuid(), ResultCodes.RESET_PASSWORD_SEND_EXCEPTION, e);
		} catch (UpdatePassWordException e){
			ConnManager.rollback();
			resp.setResultCode(ResultCodes.RESET_PASSWORD_UPDATE_EXCEPTION);
			
			/* 记录错误信息 */
			RecordUtils.writeError(logger, req.getUuid(), ResultCodes.RESET_PASSWORD_UPDATE_EXCEPTION, e);
		} catch (Exception e) {
			ConnManager.rollback();
			resp.setResultCode(ResultCodes.RESET_PASSWORD_EXCEPTION);
			
			/* 记录错误信息 */
			RecordUtils.writeError(logger, req.getUuid(), ResultCodes.RESET_PASSWORD_EXCEPTION, e);
		} finally {
			ConnManager.closeConn();
		}
		return resp;
	}

	/**
	 * 修改密码
	 * 
	 * @throws UpdatePassWordException
	 * */
	public void updatePassword(String salt, String password, String usernme)
			throws UpdatePassWordException {

		ResetPasswordEvent event = new ResetPasswordEvent();
		event.updatePassword(salt, password, usernme);

	}

	/**
	 * 发送验证码
	 * 
	 * @throws SendMobileMsgException
	 * */
	public void sendValidateCode(String username, String mobile, String pssword)
			throws SendMobileMsgException {
		ExternalService externalService = new ExternalService();
		externalService.SuccSendSms(username, mobile, pssword);
	}

	/**
	 * 获取用户
	 * */
	public CustUserEntity queryByUserName(String username) {
		CustUserEntity entity = custUserQuery.queryByUserName(username);
		return entity != null ? entity : new CustUserEntity();
	}
}
