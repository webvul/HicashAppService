package com.hengyuan.hicash.domain.service.user;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.event.user.SendAmountCodeEvent;
import com.hengyuan.hicash.domain.query.user.ApproveUserQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.domain.service.notic.ExternalService;
import com.hengyuan.hicash.entity.user.ApproveUser;
import com.hengyuan.hicash.exception.SendMobileMsgException;
import com.hengyuan.hicash.exception.UpdateAmountException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.notic.SendSupplierCodeReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.notic.SendSupplierCodeResp;

/**
 * 商户入驻-发送验证码 service
 * @author Cary.Liu
 * @createDate 2015-07-10
 *
 */
public class SendSupplierCodeService implements RespService {

	private String resultCode = "";
	
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		SendSupplierCodeReq codeReq = (SendSupplierCodeReq)parmRequest;
		SendSupplierCodeResp codeResp = new SendSupplierCodeResp();
		
		try {
			
			ConnManager.beginTransaction();
			if(!isMobileExist(codeReq.getMobileNo())){
				
				/* 获取授信额度发送验证码  暂无短信模板 */
				String validateCode = sendValidateCode(codeReq.getMobileNo());
				
				/* 保存验证码和最后验证时间和发送验证码的手机到数据库 */
				updateAmountValidateCode(codeReq.getMobileNo(), validateCode);
				
				ConnManager.commit();
				resultCode = ResultCodes.NORMAL;
			}else{
				resultCode = ResultCodes.SENDSUPPLIERCODE_MOBILE_EXIST;
			}
			
		} catch (SendMobileMsgException e){
			resultCode = ResultCodes.SENDSUPPLIERCODE_SENDCODE_FAIL;
			ConnManager.rollback();
		} catch (UpdateAmountException e){
			resultCode = ResultCodes.SENDSUPPLIERCODE_SAVECODE_FAIL;
			ConnManager.rollback();
		} catch (Exception e) {
			e.printStackTrace();
			resultCode = ResultCodes.SENDSUPPLIERCODE_EXCEPTION;
		} finally {
			ConnManager.closeConn();
		}
		
		codeResp.setResultCode(resultCode);
		return codeResp;
	}

	/**
	 * 发送验证码 
	 * @throws SendMobileMsgException
	 * */
	public String sendValidateCode(String mobile)throws SendMobileMsgException {
		ExternalService externalService = new ExternalService();
		String validateCode = externalService.smsSupplierCode(mobile);
		return validateCode;
	}
	
	/**
	 * 保存授信额度验证码和最后验证时间到数据库
	 * @throws UpdateAmountException 
	 * */
	public void updateAmountValidateCode(String mobileNo, String validateCode ) throws UpdateAmountException {
		
		SendAmountCodeEvent amountCodeEvent = new SendAmountCodeEvent();
		amountCodeEvent.saveValidateCode("", validateCode ,mobileNo);
	}
	
	/**
	 * 手机号码是否存在
	 * @param mobileNo
	 * @return
	 */
	public boolean isMobileExist(String mobileNo){
		
		ApproveUserQuery approveQuery = new ApproveUserQuery();
		ApproveUser approveUser = approveQuery.queryApprovByMobile(mobileNo);
		if(approveUser != null){
			return true;
		}
		return false;
	}
	
}
