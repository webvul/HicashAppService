package com.hengyuan.hicash.domain.service.user;

import java.util.Date;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.event.param.SuppStoreEvent;
import com.hengyuan.hicash.domain.event.user.CustomerEvent;
import com.hengyuan.hicash.domain.query.mer.SupStoreQuery;
import com.hengyuan.hicash.domain.query.mktApp.PayFlowNoQuery;
import com.hengyuan.hicash.domain.query.user.CustUserQuery;
import com.hengyuan.hicash.domain.query.user.HyEmployeeQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.mer.SupStoreEntity;
import com.hengyuan.hicash.entity.user.CustUserEntity;
import com.hengyuan.hicash.entity.user.HyEmployeeEntity;
import com.hengyuan.hicash.exception.InviteCodeNotFoundException;
import com.hengyuan.hicash.exception.QueryFlowNoException;
import com.hengyuan.hicash.exception.StoreCodeInvalidException;
import com.hengyuan.hicash.exception.StoreCodeNotFoundException;
import com.hengyuan.hicash.exception.StoreNameExistException;
import com.hengyuan.hicash.exception.UpdateCustomerException;
import com.hengyuan.hicash.exception.UpdateSupStoreException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.UpdateLanUserInfoReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.UpdateLanUserInfoResp;
import com.hengyuan.hicash.utils.DateUtils;

/**
 * 更新蓝领用户资料 service
 * @author Cary.Liu
 * @createDate 2016-03-02
 *
 */
public class UpdateLanUserInfoService implements RespService {

	private String resultCode = "";
	
	private String storeNo = "";
	
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		UpdateLanUserInfoReq userInfoReq = (UpdateLanUserInfoReq)parmRequest;
		UpdateLanUserInfoResp userInfoResp = new UpdateLanUserInfoResp();
		
		try {
			
			CustUserEntity custUser = queryCustUserByUserName(userInfoReq.getUserName());
			
			if(custUser != null){
				
				/* 请求参数验证 */
				validateUserInfo(userInfoReq);
				
				ConnManager.beginTransaction();
				
				/* 保存门店 */
				saveStoreInfo(userInfoReq);
				
				/* 修改用户资料 */
				updateUserInfo(userInfoReq);
				
				ConnManager.commit();

				resultCode = ResultCodes.NORMAL;
				
			}else{
				resultCode = ResultCodes.UPDATELANUSERINFO_USER_NOTFOUND;
			}
			
			
		} catch (UpdateSupStoreException e){
			resultCode = ResultCodes.UPDATELANUSERINFO_SAVESTORE_EXCEPTION;
			e.printStackTrace();
			ConnManager.rollback();
		} catch (QueryFlowNoException e){
			resultCode = ResultCodes.UPDATELANUSERINFO_QUERYSEQ_EXCEPTION;
			e.printStackTrace();
			ConnManager.rollback();
		} catch (StoreCodeInvalidException e) {
			resultCode = ResultCodes.REGISTER_STORECODE_INVALID;
			e.printStackTrace();
		} catch (StoreCodeNotFoundException e) {
			resultCode = ResultCodes.REGISTER_STORECODE_NOTFOUND;
			e.printStackTrace();
		} catch (StoreNameExistException e) {
			resultCode = ResultCodes.REGISTER_STORENAME_EXIST;
			e.printStackTrace();
		} catch (InviteCodeNotFoundException e) {
			resultCode = ResultCodes.REGISTER_INVITECODE_NOTFOUND;
		} catch (UpdateCustomerException e){
			resultCode = ResultCodes.UPDATELANUSERINFO_EXCEPTION;
			e.printStackTrace();
			ConnManager.rollback();
		} catch (Exception e) {
			resultCode = ResultCodes.UPDATELANUSERINFO_EXCEPTION;
			e.printStackTrace();
			ConnManager.rollback();
		} finally {
			ConnManager.closeConn();
		}
		
		userInfoResp.setResultCode(resultCode);
		return userInfoResp;
	}
	
	/**
	 * 保存门店
	 * @param userInfoReq
	 * @throws QueryFlowNoException 
	 * @throws UpdateSupStoreException 
	 */
	private void saveStoreInfo(UpdateLanUserInfoReq userInfoReq) throws QueryFlowNoException, UpdateSupStoreException{
		
		/* 如果新增门店 */
		if(Consts.FINAL_NUMBER_0.equals(userInfoReq.getIsHaveStore())){
			
			/* 获取门店号序列  */
			storeNo = getNextSeq();
			
			/* 验证门店号是否存在 */
			
			while (isStoreNoExist(storeNo)){
				storeNo = getNextSeq();
			}
			
			/* 保存门店 */
			saveStore(userInfoReq);
			
		}
		
	}
	
	private void saveStore(UpdateLanUserInfoReq userInfoReq) throws UpdateSupStoreException{
		
		SuppStoreEvent event = new SuppStoreEvent();
		SupStoreEntity entity = new SupStoreEntity();
		entity.setFrameno(DateUtils.getDateStrByFlow2(new Date()));
		entity.setSupNo("");
		entity.setSupName("");
		entity.setUnitName(userInfoReq.getUnitName());
		entity.setStoreNo(storeNo);
		entity.setStoreName(userInfoReq.getStoreName());
		entity.setCity(userInfoReq.getCity());
		entity.setStoreAdree(userInfoReq.getAddress());
		entity.setTopPath(userInfoReq.getStorePicUrl());
		entity.setNextPath(userInfoReq.getLocaPicUrl());
		entity.setRoadNo(userInfoReq.getRoadNo());
		entity.setOperatePower(userInfoReq.getOperatePower());
		entity.setOperateTime(userInfoReq.getOperateTime());
		entity.setLegalName(userInfoReq.getLegalName());
		entity.setUnitPhone(userInfoReq.getUnitPhone());
		entity.setStatus("4");  // 待审核
		entity.setStorePicUrl1(userInfoReq.getStorePicUrl1());
		entity.setStorePicUrl2(userInfoReq.getStorePicUrl2());
		entity.setStorePicUrl3(userInfoReq.getStorePicUrl3());
		
		event.saveStore(entity);
	}
	
	private boolean isStoreNoExist(String storeNo){
		
		SupStoreQuery storeQuery = new SupStoreQuery();
		SupStoreEntity entity = storeQuery.querySupStoreByStoreNo(storeNo);
		
		return entity != null ? true : false;
	}
	
	/**
	 * 获取序列
	 * @return
	 * @throws QueryFlowNoException
	 */
	private String getNextSeq() throws QueryFlowNoException{
		
		PayFlowNoQuery sequenceQuery = new PayFlowNoQuery();
		
		return sequenceQuery.queryFlowNo(Consts.SUP_STORE_SEQ);
	}
	
	/**
	 * 验证请求信息
	 * @param registerReq
	 * @return
	 * @throws InviteCodeNotFoundException 
	 * @throws StoreCodeNotFoundException 
	 * @throws StoreCodeInvalidException 
	 * @throws StoreNameExistException 
	 */
	public void validateUserInfo(UpdateLanUserInfoReq userInfoReq) throws InviteCodeNotFoundException, StoreCodeNotFoundException, StoreCodeInvalidException, StoreNameExistException{
		
		/* 邀请码(业务员工号)是否存在 */
		HyEmployeeQuery empQuery = new HyEmployeeQuery();
		HyEmployeeEntity empEntity = empQuery.queryEmployeeByCode(userInfoReq.getInviteCode());
		if(empEntity == null){
			throw new InviteCodeNotFoundException();
		}
		
		/* 如果现有门店 */
		SupStoreQuery storeQuery = new SupStoreQuery();
		if(Consts.FINAL_NUMBER_1.equals(userInfoReq.getIsHaveStore())){
		
			/* 门店号是否存在 */
			SupStoreEntity storeEntity = storeQuery.querySupStoreByStoreNo(userInfoReq.getStoreCode());
			if(storeEntity == null){
				throw new StoreCodeNotFoundException();
			}
			
			/* 门店号是否有效（审核通过） */
			if(!Consts.FINAL_NUMBER_1.equals(storeEntity.getStatus())){
				throw new StoreCodeInvalidException();
			}
			
		}else{
			
			/* 新增门店，店名是否已存在 */
			if(storeQuery.isStoreNameExist(userInfoReq.getStoreName().trim())){
				throw new StoreNameExistException();
			}
			
		}
		
	}
	
	/**
	 * 更新资料
	 * @param userInfoReq
	 * @throws UpdateCustomerException
	 */
	public void updateUserInfo(UpdateLanUserInfoReq userInfoReq) throws UpdateCustomerException{
		
		if(Consts.FINAL_NUMBER_0.equals(userInfoReq.getIsHaveStore())){
			userInfoReq.setStoreCode(storeNo);
		}
		
		CustomerEvent event = new CustomerEvent();
		event.updateLanUserInfo(userInfoReq);
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

}
