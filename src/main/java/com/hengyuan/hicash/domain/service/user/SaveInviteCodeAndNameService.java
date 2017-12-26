package com.hengyuan.hicash.domain.service.user;


import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.event.user.CustomerEvent;
import com.hengyuan.hicash.domain.query.user.CustUserQuery;
import com.hengyuan.hicash.domain.query.user.HyEmployeeQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.user.CustUserEntity;
import com.hengyuan.hicash.entity.user.HyEmployeeEntity;
import com.hengyuan.hicash.exception.InviteCodeNotFoundException;
import com.hengyuan.hicash.exception.SaveCustomerException;
import com.hengyuan.hicash.exception.StoreCodeInvalidException;
import com.hengyuan.hicash.exception.StoreCodeNotFoundException;
import com.hengyuan.hicash.exception.StoreNameExistException;
import com.hengyuan.hicash.exception.UpdateCustomerException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.SaveInviteCodeAndNameReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.UpdateLanUserInfoResp;

public class SaveInviteCodeAndNameService implements RespService {
	
	private String resultCode = "";
	
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		SaveInviteCodeAndNameReq userInfoReq = (SaveInviteCodeAndNameReq)parmRequest;
		UpdateLanUserInfoResp userInfoResp = new UpdateLanUserInfoResp();
		
		try {
			
			CustUserEntity custUser = queryCustUserByUserName(userInfoReq.getUserName());
			
			if(custUser != null){
				
				/* 请求参数验证 */
				validateUserInfo(userInfoReq);
				
				ConnManager.beginTransaction();				
				
				/* 保存用户资料 */
				saveInviteCodeAndName(userInfoReq);
				
				ConnManager.commit();

				resultCode = ResultCodes.NORMAL;
				
			}else{
				resultCode = ResultCodes.UPDATELANUSERINFO_USER_NOTFOUND;
			}					
		} catch (InviteCodeNotFoundException e){
			resultCode = ResultCodes.REGISTER_INVITECODE_NOTFOUND;
			e.printStackTrace();	
		} catch (UpdateCustomerException e){
			resultCode = ResultCodes.UPDATELANUSERINFO_EXCEPTION;
			e.printStackTrace();
			ConnManager.rollback();
		} catch (Exception e) {
			resultCode = ResultCodes.STU_APP1_INFO_EXCEPTION;
			e.printStackTrace();
		} finally {
			ConnManager.closeConn();
		}
		
		userInfoResp.setResultCode(resultCode);
		return userInfoResp;
	}
	
	
	/**
	 * 根据用户名查找用户
	 * @param userName
	 * @return
	 */
	public CustUserEntity queryCustUserByUserName(String userName){
		
		CustUserQuery custUserQuery = new CustUserQuery();
		return custUserQuery.queryByUserName(userName);
	}
    
	
	public void validateUserInfo(SaveInviteCodeAndNameReq userInfoReq) throws InviteCodeNotFoundException, StoreCodeNotFoundException, StoreCodeInvalidException, StoreNameExistException{
		
		/* 邀请码(业务员工号)是否存在 */
		HyEmployeeQuery empQuery = new HyEmployeeQuery();
		HyEmployeeEntity empEntity = empQuery.queryEmployeeByCode(userInfoReq.getInviteCode());
		if(empEntity == null){
			throw new InviteCodeNotFoundException();
		}
}
	/**
	 * 保存用户名字和邀请码
	 * @param userInfoReq
	 * @throws UpdateCustomerException
	 * @throws SaveCustomerException 
	 */
	public void saveInviteCodeAndName(SaveInviteCodeAndNameReq userInfoReq) throws UpdateCustomerException, SaveCustomerException{
		

		CustomerEvent event = new CustomerEvent();
		event.updateInviteCodeAndName(userInfoReq);
	}
	
}