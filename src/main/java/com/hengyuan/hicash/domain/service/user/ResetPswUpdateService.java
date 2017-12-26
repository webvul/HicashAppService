package com.hengyuan.hicash.domain.service.user;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.event.user.CustUserEvent;
import com.hengyuan.hicash.domain.query.user.CustUserQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.user.CustUserEntity;
import com.hengyuan.hicash.exception.UpdateCustUserException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.ResetPswUpdateReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.ResetPswUpdateResp;

/**
 * 忘记密码-修改密码 service
 * 
 * @author Cary.Liu
 * @createDate 2015-06-12
 * 
 */
public class ResetPswUpdateService implements RespService {

	private String resultCode = "";
	
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		ResetPswUpdateReq pswUpdateReq = (ResetPswUpdateReq)parmRequest;
		ResetPswUpdateResp pswUpdateResp = new ResetPswUpdateResp();
		
		try {
			
			 CustUserEntity custUser = queryCust(pswUpdateReq.getMobileNo());
			 if(custUser != null){
				 
				 updateApprovePsw(pswUpdateReq,custUser.getUsername());
				 resultCode = ResultCodes.NORMAL;
			 }else{
				 resultCode = ResultCodes.SENDPSWCODE_MOBILE_NOTFOUND;
			 }
			
		} catch (Exception e) {
			resultCode = ResultCodes.RESETPSW_EXCEPTION;
			e.printStackTrace();
		} finally {
			ConnManager.closeConn();
		}
		
		pswUpdateResp.setResultCode(resultCode);
		return pswUpdateResp;
	}

	public CustUserEntity queryCust(String mobileNo){
		
		CustUserQuery custQuery = new CustUserQuery();
		
		return custQuery.queryUserByMobile(mobileNo);
	}
	
	/**
	 * 修改密码
	 * @param userName 用户名
	 * @param passWord 用户输入的密码
	 * @param uuid
	 * @throws UpdateApproveUserException
	 */
	public void updateApprovePsw(ResetPswUpdateReq pswUpdateReq,String userName) throws UpdateCustUserException{
		
		/* 1.新密码加密*/
		String salt = RandomStringUtils.random(20, true, true);
		Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
		String newPsw = md5PasswordEncoder.encodePassword(pswUpdateReq.getNewPassWord(), salt);
		
		CustUserEvent userEvent = new CustUserEvent();
		userEvent.updateUserPassWord(userName, newPsw, salt);
	}
	
}
