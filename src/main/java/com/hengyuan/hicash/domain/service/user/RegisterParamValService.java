package com.hengyuan.hicash.domain.service.user;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ExceptionMsg;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.event.amount.TransactionEvent;
import com.hengyuan.hicash.domain.query.notic.CustTempIdentifyCodeQuery;
import com.hengyuan.hicash.domain.query.user.AccountQuery;
import com.hengyuan.hicash.domain.query.user.OnlyMobileQuery;
import com.hengyuan.hicash.domain.query.user.RegisterQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.notic.CustIdentifyCodeEntity;
import com.hengyuan.hicash.entity.user.AccountEntity;
import com.hengyuan.hicash.exception.UserExistsException;
import com.hengyuan.hicash.exception.UserIdCardExistException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.RegisterReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.RegisterResp;

/**
 * 用户注册参数验证 service
 * @author Cary.Liu
 * @createDate 2015-06-01
 *
 */
public class RegisterParamValService implements RespService {

	private String resultCode = "";
	
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		RegisterReq registerReq = (RegisterReq) parmRequest;
		RegisterResp registerResp = new RegisterResp();

		try {
			/* 请求参数验证 */
			validateUserInfo(registerReq);
			/* 获取验证码信息 */
			CustIdentifyCodeEntity identifyCode = queryTempIdentify(registerReq);
			if(identifyCode != null){
				/* 验证验证码信息 */
				if(identifyValidate(registerReq, identifyCode)){
					
//					ConnManager.beginTransaction();
//					/* 注册用户信息 */
//					registerUser(registerReq);
//					/* 获取额度 */
//					setAmount(registerReq);
//					/* 获取用户信息 */
//					queryUserInfo(registerReq);
//					
//					ConnManager.commit();
					resultCode = ResultCodes.NORMAL;
				}
				
			}else{
				resultCode = ResultCodes.REGISTER_GETIDENTIFY_FAIL;
			}
			
		} catch (UserExistsException e){
			resultCode = ResultCodes.REGISTER_USER_EXIST;
		} catch (UserIdCardExistException e){
			resultCode = ResultCodes.REGISTER_IDCARD_EXIST;
		} catch (Exception e) {
			resultCode = ResultCodes.REGISTER_EXCEPTION;
		} finally {
			ConnManager.closeConn();
		}

		registerResp.setUserName(registerReq.getMobileNo());
		registerResp.setRealName(registerReq.getRealName());
		registerResp.setMobileNo(registerReq.getMobileNo());
		registerResp.setIdentityNo(registerReq.getIdCard());
		registerResp.setCustType(registerReq.getCustType());
		registerResp.setCustTypeName(getCustTypeName(registerReq.getCustType()));
		registerResp.setCityCode(registerReq.getLiveCity());
//		registerResp.setLimitFlag(limitFlag);
//		registerResp.setAccountFlag(accountFlag);
//		registerResp.setAmount(amount);
		registerResp.setResultCode(resultCode);
//		registerResp.setToken(token);
		return registerResp;
	}
	
	/**
	 * 验证请求信息
	 * @param registerReq
	 * @return
	 * @throws UserExistsException 
	 * @throws UserIdCardExistException 
	 */
	public void validateUserInfo(RegisterReq registerReq) throws UserExistsException, UserIdCardExistException{
		
		/* 验证用户是否存在 */
		RegisterQuery registerQuery = new RegisterQuery();
		boolean isUserExist = registerQuery.isUserExistByApp(registerReq.getMobileNo());
		if(isUserExist){
			throw new UserExistsException(ExceptionMsg.USER_EXIST_EXCEPTION);
		}
		/* 验证用户身份证号码 */
		boolean isIdCardExist = registerQuery.isIdCardExist(registerReq.getIdCard());
		if(isIdCardExist){
			throw new UserIdCardExistException(ExceptionMsg.IDCARD_EXIST_EXCEPTION);
		}
	}
	
	/**
	 * 查询验证码信息
	 * @param registerReq
	 * @return
	 */
	public CustIdentifyCodeEntity queryTempIdentify(RegisterReq registerReq){
		
		CustTempIdentifyCodeQuery tempIdentifyQuery = new CustTempIdentifyCodeQuery();
		return tempIdentifyQuery.queryTempCode(registerReq.getMobileNo());
	}
	
	/**
	 * 验证码验证
	 * @param registerReq
	 * @return
	 * @throws ParseException 
	 */
	public boolean identifyValidate(RegisterReq registerReq,CustIdentifyCodeEntity identifyCode) throws ParseException{
		
		String tempMobileNo = identifyCode.getMobileNo();
		if(tempMobileNo != null && tempMobileNo.trim().equals(registerReq.getMobileNo().trim())){
			/* 验证验证码是否正确 */
			if(registerReq.getIdentifyingCode().trim().equals(identifyCode.getIdentifyCode())){
				
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
	
	/**
	 * 账户自动激活
	 * @param registerReq
	 */
	public void accountAutoActive(RegisterReq registerReq){
		
		AccountQuery accountQuery = new AccountQuery();
		List<AccountEntity> accountList = accountQuery.queryAccount(registerReq.getMobileNo());
		if(accountList!=null && accountList.size()>0){
			String accountId = accountList.get(0).getAccountId();
			TransactionEvent event = new TransactionEvent();
			event.accountAutoActive(accountId);
		}
		
	}
	
	

	
	/**
	 * 验证手机号码是否存在
	 * @param registerReq
	 * @return
	 * @author Cary.liu
	 * @createDate 2015-04-21
	 */
	public boolean isMobileExist(RegisterReq registerReq){
		OnlyMobileQuery mobileQuery = new OnlyMobileQuery();
		return mobileQuery.isExistMobile(registerReq.getMobileNo());
	}

	
	
	/**
	 * 得到客户类型名称
	 * @param custType
	 * @return
	 */
	public String getCustTypeName(String custType){
	
		if(org.apache.commons.lang.StringUtils.isEmpty(custType)){
			return "";
		}
		
		if(Consts.CUSTTYPE_KHL1.equals(custType)){
			return Consts.CUSTTYPE_KHL1_NAME;
		}else{
			return Consts.CUSTTYPE_KHL2_NAME;
		}
		
	}

}
