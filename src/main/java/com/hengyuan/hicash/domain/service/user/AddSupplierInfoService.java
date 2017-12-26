package com.hengyuan.hicash.domain.service.user;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.event.user.AddSupplierInfoEvent;
import com.hengyuan.hicash.domain.query.notic.CustTempIdentifyCodeQuery;
import com.hengyuan.hicash.domain.query.param.SingleStrQuery;
import com.hengyuan.hicash.domain.query.user.ApproveUserQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.notic.CustIdentifyCodeEntity;
import com.hengyuan.hicash.entity.user.ApproveUser;
import com.hengyuan.hicash.exception.SaveApproveAuthroException;
import com.hengyuan.hicash.exception.SaveApproveException;
import com.hengyuan.hicash.exception.SaveSupplierException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.AddSupplierInfoReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.AddSupplierInfoResp;

/** 
 * 商户入驻-新增商户 service
 * @author Cary.Liu
 * @createDate 2015-07-10
 * 
 */
public class AddSupplierInfoService implements RespService {
	
	private String resultCode = "";
	
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		AddSupplierInfoReq req = (AddSupplierInfoReq)parmRequest;
		AddSupplierInfoResp resp = new AddSupplierInfoResp();
		
		try {
			
			if(!isMobileExist(req.getMobileNo())){
				
				if(!isUserNameExist(req.getSupplierUserName())){
					
					CustIdentifyCodeEntity identifyCode = queryTempIdentify(req.getMobileNo());
					if(identifyCode != null){
						
						/* 验证验证码信息 */
						if(identifyValidate(req, identifyCode)){
							
							ConnManager.beginTransaction();
							
							/* 注册商户 */
							registerSupplier(req);
							
							resultCode = ResultCodes.NORMAL;
							ConnManager.commit();
						}
						
					}else{
						resultCode = ResultCodes.ADDMERAPP_IDCODE_NOTFOUND;
					}
					
				}else{
					resultCode = ResultCodes.ADDMERAPP_USERNAME_EXIST;
				}
				
			}else{
				resultCode = ResultCodes.ADDMERAPP_MOBILE_EXIST;
			}
			
		}
//		catch (SaveApproveAuthroException e){
//			resultCode = ResultCodes.ADDMERAPP_REGISTER_FAIL1;
//			ConnManager.rollback();
//		} 
		catch (SaveApproveException e){
			resultCode = ResultCodes.ADDMERAPP_REGISTER_FAIL2;
			ConnManager.rollback();
		} catch (SaveSupplierException e){
			resultCode = ResultCodes.ADDMERAPP_REGISTER_FAIL3;
			ConnManager.rollback();
		} catch (Exception e) {
			resultCode = ResultCodes.ADDMERAPP_EXCETPION;
			ConnManager.rollback();
		} finally {
			ConnManager.closeConn();
		}
		
		resp.setResultCode(resultCode);
		return resp;
	}
	
	/**
	 * 注册商户
	 * @param req
	 * @throws SaveSupplierException 
	 * @throws SaveApproveException 
	 * @throws SaveApproveAuthroException 
	 */
	public void registerSupplier(AddSupplierInfoReq req) throws SaveSupplierException, SaveApproveException , Exception{
		
		AddSupplierInfoEvent addSupplierEvent = new AddSupplierInfoEvent(req.getUuid());
		SingleStrQuery strQuery = new SingleStrQuery();
		
		/* 保存商户信息  */
		addSupplierEvent.saveSupplierInfo(req);
		/* 保存登陆账号  */
		String supplierId = strQuery.getMaxIndex();
		addSupplierEvent.saveApproveUser(req,supplierId);
		/* 保存登陆账号权限 */
		String approveId = strQuery.getMaxIndex();
		saveApproveAuthro(approveId, req.getUuid());
//		return approveId;
	}
	
	/**
	 * 保存登陆账号权限
	 * @param approveId
	 * @param uuid
	 * @throws SaveApproveAuthroException
	 */
	public void saveApproveAuthro(String approveId,String uuid) throws SaveApproveAuthroException{
		
		AddSupplierInfoEvent addSupplierEvent = new AddSupplierInfoEvent(uuid);
		addSupplierEvent.saveApproveAuthro(approveId);
		
	}
	
	/**
	 * 查询验证码信息
	 * @param mobileNo
	 * @return
	 */
	public CustIdentifyCodeEntity queryTempIdentify(String mobileNo){
		
		CustTempIdentifyCodeQuery tempIdentifyQuery = new CustTempIdentifyCodeQuery();
		return tempIdentifyQuery.queryTempCodeByMobile(mobileNo);
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
	
	/**
	 * 用户名是否存在
	 * @param mobileNo
	 * @return
	 */
	public boolean isUserNameExist(String userName){
		
		ApproveUserQuery approveQuery = new ApproveUserQuery();
		ApproveUser approveUser = approveQuery.queryApprovByUserName(userName);
		if(approveUser != null){
			return true;
		}
		return false;
	}
	
	/**
	 * 验证码验证
	 * @param req
	 * @return
	 * @throws ParseException 
	 */
	public boolean identifyValidate(AddSupplierInfoReq req,CustIdentifyCodeEntity identifyCode) throws ParseException{
		
		String tempMobileNo = identifyCode.getMobileNo();
		if(tempMobileNo != null && tempMobileNo.trim().equals(req.getMobileNo().trim())){
			/* 验证验证码是否正确 */
			if(req.getIdentifyCode().trim().equals(identifyCode.getIdentifyCode())){
				
				/* 判断验证时间是否已经过期 */
				Calendar nowTime = Calendar.getInstance();
				Calendar validateTime = Calendar.getInstance();
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				validateTime.setTime(sdf.parse(identifyCode.getValidateTime()));
				if (nowTime.before(validateTime)) {
					
					return true;
				}else{
					resultCode = ResultCodes.REGISTER_IDENTIFY_OVERTIME;
				}
				
			}else{
				resultCode = ResultCodes.REGISTER_IDENTIFY_FAIL;
			}
			
		}else{
			resultCode = ResultCodes.REGISTER_MOBILE_FAIL;
		}
		
		return false;
	}
	
}
