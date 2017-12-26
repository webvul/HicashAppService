package com.hengyuan.hicash.domain.service.user;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.event.user.CustUserEvent;
import com.hengyuan.hicash.domain.query.user.CustUserQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.user.CustUserEntity;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.ValidateCodeValReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.ValidateCodeValResp;

/**
 * 忘记密码-短信验证码验证 service
 * @author Cary.Liu
 * @createDate 2015-06-02
 *
 */
public class ValidateCodeValService implements RespService {

	private String resultCode = "";
	
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		ValidateCodeValReq valReq = (ValidateCodeValReq)parmRequest;
		ValidateCodeValResp valResp = new ValidateCodeValResp();
		
		try {
			
			 CustUserEntity custUser = queryCust(valReq.getMobileNo());
			 if(custUser != null){
				 
				 if(identifyValidate(valReq, custUser)){
						
					setEmptyToValidate(custUser.getUsername());
					resultCode = ResultCodes.NORMAL;
				}
				 
			 }else{
				 resultCode = ResultCodes.SENDPSWCODE_MOBILE_NOTFOUND;
			 }
			
		} catch (Exception e) {
			resultCode = ResultCodes.SENDPSWCODE_VALIDATE_EXCEPTION;
			e.printStackTrace();
		} finally{
			ConnManager.closeConn();
		}
		
		valResp.setResultCode(resultCode);
		return valResp;
	}
	
	
	/**
	 * 验证码验证
	 * @param registerReq
	 * @return
	 * @throws ParseException 
	 */
	public boolean identifyValidate(ValidateCodeValReq valReq,CustUserEntity custUser) throws ParseException{
		
		String tempMobileNo = valReq.getMobileNo();
		if(tempMobileNo != null && tempMobileNo.trim().equals(custUser.getMobileNo().trim())){
			/* 验证验证码是否正确 */
			if(valReq.getValidateCode().trim().equals(custUser.getMobileValidateCode())){
				
				/* 判断验证时间是否已经过期 */
				Calendar nowTime = Calendar.getInstance();
				Calendar validateTime = Calendar.getInstance();
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				validateTime.setTime(sdf.parse(custUser.getMobileValidateCodeValidTime()));
				if (nowTime.before(validateTime)) {
					
					return true;
				}else{
					resultCode = ResultCodes.SENDPSWCODE_VALIDATECODE_OUTTIME;
				}
				
			}else{
				resultCode = ResultCodes.SENDPSWCODE_VALIDATECODE_FAIL;
			}
			
		}else{
			resultCode = ResultCodes.SENDPSWCODE_INPUTMOBILE_FAIL;
		}
		
		return false;
	}
	
	public CustUserEntity queryCust(String mobileNo){
		
		CustUserQuery custQuery = new CustUserQuery();
		
		return custQuery.queryUserByMobile(mobileNo);
	}
	
	/**
	 * 清空验证码
	 * @param userName
	 */
	public void setEmptyToValidate(String userName){
		
		CustUserEvent userEvent = new CustUserEvent();
		
		userEvent.clearIdentifyingCode(userName);
	}
	
}
