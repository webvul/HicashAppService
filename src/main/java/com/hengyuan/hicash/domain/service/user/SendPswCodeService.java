package com.hengyuan.hicash.domain.service.user;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.event.user.SendAmountCodeEvent;
import com.hengyuan.hicash.domain.query.user.CustUserQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.domain.service.notic.ExternalService;
import com.hengyuan.hicash.entity.user.CustUserEntity;
import com.hengyuan.hicash.exception.SendMobileMsgException;
import com.hengyuan.hicash.exception.UpdateAmountException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.SendPswCodeReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.SendPswCodeResp;

/**
 * 忘记密码-发送验证码 service
 * @author Cary.Liu
 * @createDate 2015-06-12
 *
 */
public class SendPswCodeService implements RespService {

	private String resultCode = "";
	
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		SendPswCodeReq codeReq = (SendPswCodeReq)parmRequest;
		SendPswCodeResp codeResp = new SendPswCodeResp();
		
		try {
			
			 CustUserEntity custUser = queryCust(codeReq.getMobileNo());
			 if(custUser != null){
				 
				 ConnManager.beginTransaction();
				 
				 /* 发送验证码 */
				 String validateCode = sendValidateCode(codeReq.getMobileNo(), custUser.getUsername());
				 
				 if (validateCode != null) {
					 
					 /* 保存验证码 */
					 saveValidateCode(custUser.getUsername(), validateCode);
					 resultCode = ResultCodes.NORMAL;
					 
				 }else{
					 resultCode = ResultCodes.SENDPSWCODE_SEND_FAIL;
				 }
				 
				 ConnManager.commit();
			 }else{
				 resultCode = ResultCodes.SENDPSWCODE_MOBILE_NOTFOUND;
			 }
			
		} catch (UpdateAmountException e){
			resultCode = ResultCodes.SENDPSWCODE_SAVE_FAIL;
			ConnManager.rollback();
		} catch (SendMobileMsgException e){
			resultCode = ResultCodes.SENDPSWCODE_SEND_FAIL;
			ConnManager.rollback();
		} catch (Exception e) {
			resultCode = ResultCodes.SENDPSWCODE_EXCEPTION;
			e.printStackTrace();
			ConnManager.rollback();
		} finally {
			ConnManager.closeConn();
		}
		
		codeResp.setResultCode(resultCode);
		return codeResp;
	}
	
	public CustUserEntity queryCust(String mobileNo){
		
		CustUserQuery custQuery = new CustUserQuery();
		
		return custQuery.queryUserByMobile(mobileNo);
	}
	
	/**
	 * 发送验证码 （非获取授信额度验证码模板）
	 * @throws SendMobileMsgException
	 * */
	public String sendValidateCode(String mobile, String username) throws SendMobileMsgException {
		ExternalService externalService = new ExternalService();
		String validateCode = externalService.sendValidateMessage(mobile,username);
		return validateCode;
	}

	/**
	 * 保存验证码
	 * @param userName
	 * @param validateCode
	 * @throws UpdateAmountException
	 */
	public void saveValidateCode(String userName,String validateCode) throws UpdateAmountException{
		
		SendAmountCodeEvent sendCodeEvent = new SendAmountCodeEvent();
		sendCodeEvent.updateResetPswValidateCode(userName, validateCode);
	}
	
}
