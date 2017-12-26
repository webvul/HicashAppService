package com.hengyuan.hicash.domain.service.user;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.event.user.SendAmountCodeEvent;
import com.hengyuan.hicash.domain.query.user.CustUserQuery;
import com.hengyuan.hicash.domain.query.user.OnlyMobileQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.domain.service.notic.ExternalService;
import com.hengyuan.hicash.exception.SendMobileMsgException;
import com.hengyuan.hicash.exception.UpdateAmountException;
import com.hengyuan.hicash.exception.UpdateMobileNoException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.SendAmountCodeReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.SendAmountCodeResp;

/**
 * 获取额度验证码 业务处理
 * @author Cary.Liu
 * @createDate 2015-04-21
 *
 */
public class SendAmountCodeService implements RespService {
	
	private String resultCode = "";
	
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		SendAmountCodeReq amountCodeReq = (SendAmountCodeReq)parmRequest;
		SendAmountCodeResp amountCodeResp = new SendAmountCodeResp();
		
		try {
			
			ConnManager.beginTransaction();
			
//			if(isUserExist(amountCodeReq.getUserName())){
				
				if(!isMobileExists(amountCodeReq.getMobile())){
					
					/* 获取授信额度发送验证码  暂无短信模板 */
					String validateCode = sendValidateCode(amountCodeReq.getMobile(),amountCodeReq.getUserName());

					/* 保存验证码和最后验证时间和发送验证码的手机到数据库 */
					updateAmountValidateCode(amountCodeReq.getUserName(), validateCode ,amountCodeReq.getMobile());
					
					ConnManager.commit();
					resultCode = ResultCodes.NORMAL;
				}else{
					resultCode = ResultCodes.SENDAMOUNTCODE_MOBILE_ISEXIST;
				}
				
//			}else{
//				resultCode = ResultCodes.SENDAMOUNTCODE_USER_NOTEXIST;
//			}
			
		} catch(SendMobileMsgException e){
			resultCode = ResultCodes.SENDAMOUNTCODE_SENDCODE_FAIL;
			ConnManager.rollback();
		} catch(UpdateMobileNoException e){
			resultCode = ResultCodes.SENDAMOUNTCODE_SAVECODE_FAIL;
			ConnManager.rollback();
		} catch (Exception e) {
			resultCode = ResultCodes.SENDAMOUNTCODE_EXCEPTION;
		} finally {
			ConnManager.closeConn();
		}

		
		amountCodeResp.setResultCode(resultCode);
		return amountCodeResp;
	}
	
	
	/**
	 * 发送验证码 （非获取授信额度验证码模板）
	 * @throws SendMobileMsgException
	 * */
	public String sendValidateCode(String mobile, String username)throws SendMobileMsgException {
		ExternalService externalService = new ExternalService();
		String validateCode = externalService.sendValidateMessage(mobile,username);
		return validateCode;
	}
	
	/**
	 * 保存授信额度验证码和最后验证时间到数据库
	 * @throws UpdateAmountException 
	 * */
	public void updateAmountValidateCode(String username, String validateCode ,String tempMobile) throws UpdateMobileNoException, UpdateAmountException {
		
		SendAmountCodeEvent amountCodeEvent = new SendAmountCodeEvent();
		amountCodeEvent.saveValidateCode(username, validateCode ,tempMobile);
	}
	
	/**
	 * 查询用户是否存在
	 * @param userName
	 * @return
	 */
	public boolean isUserExist(String userName){
		
		CustUserQuery userQuery = new CustUserQuery();
		return userQuery.isUserExist(userName);
	}
	
	/**
	 * 验证手机号码是否存在
	 * @param mobile 手机号码
	 */
	public boolean isMobileExists(String mobile) {
		
		OnlyMobileQuery mobileQuery = new OnlyMobileQuery();
		return mobileQuery.isExistMobile(mobile);
	}
}
