package com.hengyuan.hicash.domain.service.user;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ExceptionMsg;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.event.amount.TransactionEvent;
import com.hengyuan.hicash.domain.event.index.PageIndexEvent;
import com.hengyuan.hicash.domain.event.user.AccountEvent;
import com.hengyuan.hicash.domain.event.user.CustUserEvent;
import com.hengyuan.hicash.domain.event.user.CustomerEvent;
import com.hengyuan.hicash.domain.event.user.LoginEvent;
import com.hengyuan.hicash.domain.query.mer.SupStoreQuery;
import com.hengyuan.hicash.domain.query.notic.CustTempIdentifyCodeQuery;
import com.hengyuan.hicash.domain.query.user.AccountQuery;
import com.hengyuan.hicash.domain.query.user.CustLimitQuery;
import com.hengyuan.hicash.domain.query.user.HyEmployeeQuery;
import com.hengyuan.hicash.domain.query.user.RegisterQuery;
import com.hengyuan.hicash.domain.query.user.TransactionQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.domain.service.amount.CreDreamAmtService;
import com.hengyuan.hicash.entity.mer.SupStoreEntity;
import com.hengyuan.hicash.entity.notic.CustIdentifyCodeEntity;
import com.hengyuan.hicash.entity.user.AccountEntity;
import com.hengyuan.hicash.entity.user.CustLimitEntity;
import com.hengyuan.hicash.entity.user.HyEmployeeEntity;
import com.hengyuan.hicash.entity.user.TransactionEntity;
import com.hengyuan.hicash.exception.InviteCodeNotFoundException;
import com.hengyuan.hicash.exception.SaveAccountException;
import com.hengyuan.hicash.exception.SaveAuthorityException;
import com.hengyuan.hicash.exception.SaveCustUserException;
import com.hengyuan.hicash.exception.SaveCustomerException;
import com.hengyuan.hicash.exception.SaveRegisInfoException;
import com.hengyuan.hicash.exception.StoreCodeInvalidException;
import com.hengyuan.hicash.exception.StoreCodeNotFoundException;
import com.hengyuan.hicash.exception.UpdatePageIndexException;
import com.hengyuan.hicash.exception.UserExistsException;
import com.hengyuan.hicash.exception.UserIdCardExistException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.amount.CreDreamAmtReq;
import com.hengyuan.hicash.parameters.request.user.RegisterByllReq;
import com.hengyuan.hicash.parameters.request.user.RegisterReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.amount.CreDreamAmtResp;
import com.hengyuan.hicash.parameters.response.user.RegisterByllResp;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 蓝领活动注册service
 * @author Cary.Liu
 * @createDate 2016-01-11
 *
 */
public class RegisterByllService implements RespService {

	private String resultCode = "";
	private String token = "";
	private String amount = "";
	
	private String limitFlag = "0";//是否已获取过额度
	private String accountFlag = "0";//是否激活账户
	
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		RegisterByllReq registerReq = (RegisterByllReq)parmRequest;
		RegisterByllResp registerResp = new RegisterByllResp();
		
		try {
			
			/* 请求参数验证 */
			validateUserInfo(registerReq);
			/* 获取验证码信息 */
			CustIdentifyCodeEntity identifyCode = queryTempIdentify(registerReq);
			if(identifyCode != null){
				/* 验证验证码信息 */
				if(identifyValidate(registerReq, identifyCode)){
					
					ConnManager.beginTransaction();
					/* 注册用户信息 */
					registerUser(registerReq);
					/* 获取额度 */
					setAmount(registerReq);
					/* 获取用户信息 */
					queryUserInfo(registerReq);
					
					ConnManager.commit();
					resultCode = ResultCodes.NORMAL;
				}
				
			}else{
				resultCode = ResultCodes.REGISTER_GETIDENTIFY_FAIL;
			}

		} catch (StoreCodeInvalidException e) {
			resultCode = ResultCodes.REGISTER_STORECODE_INVALID;
		} catch (StoreCodeNotFoundException e) {
			resultCode = ResultCodes.REGISTER_STORECODE_NOTFOUND;
		} catch (InviteCodeNotFoundException e) {
			resultCode = ResultCodes.REGISTER_INVITECODE_NOTFOUND;
		} catch (UserExistsException e){
			resultCode = ResultCodes.REGISTER_USER_EXIST;
		} catch (UserIdCardExistException e){
			resultCode = ResultCodes.REGISTER_IDCARD_EXIST;
		} catch (SaveCustUserException e) {
			ConnManager.rollback();
			resultCode = ResultCodes.REGISTER_USER_EXCEPTION;
		} catch (SaveAuthorityException e) {
			ConnManager.rollback();
			resultCode = ResultCodes.REGISTER_AUTH_EXCEPTION;
		} catch (SaveCustomerException e) {
			ConnManager.rollback();
			resultCode = ResultCodes.REGISTER_CUST_EXCEPTION;
		} catch (SaveAccountException e) {
			ConnManager.rollback();
			resultCode = ResultCodes.REGISTER_ACCOUNT_EXCEPTION;
		} catch (UpdatePageIndexException e){
			ConnManager.rollback();
			resultCode = ResultCodes.REGISTER_PAGEINDEX_UPDATEEXCEPTION;
		} catch (Exception e) {
			resultCode = ResultCodes.REGISTER_EXCEPTION;
			ConnManager.rollback();
		} finally {
			ConnManager.closeConn();
		}
		
		registerResp.setUserName(registerReq.getMobileNo());
		registerResp.setRealName(registerReq.getRealName());
		registerResp.setMobileNo(registerReq.getMobileNo());
		registerResp.setIdentityNo(registerReq.getIdCard());
		registerResp.setCustType(registerReq.getCustType());
		registerResp.setCustTypeName(getCustTypeName(registerReq.getCustType()));
		registerResp.setLimitFlag(limitFlag);
		registerResp.setAccountFlag(accountFlag);
		registerResp.setAmount(amount);
		registerResp.setResultCode(resultCode);
		registerResp.setToken(token);
		return registerResp;
	}
	
	/**
	 * 验证请求信息
	 * @param registerReq
	 * @return
	 * @throws UserExistsException 
	 * @throws UserIdCardExistException 
	 * @throws InviteCodeNotFoundException 
	 * @throws StoreCodeNotFoundException 
	 * @throws StoreCodeInvalidException 
	 */
	public void validateUserInfo(RegisterByllReq registerReq) throws UserExistsException, UserIdCardExistException, InviteCodeNotFoundException, StoreCodeNotFoundException, StoreCodeInvalidException{
		
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
		
		/* 邀请码(业务员工号)是否存在 */
		HyEmployeeQuery empQuery = new HyEmployeeQuery();
		HyEmployeeEntity empEntity = empQuery.queryEmployeeByCode(registerReq.getInviteCode());
		if(empEntity == null){
			throw new InviteCodeNotFoundException();
		}
		
		/* 门店号是否存在 */
		SupStoreQuery storeQuery = new SupStoreQuery();
		SupStoreEntity storeEntity = storeQuery.querySupStoreByStoreNo(registerReq.getStoreCode());
		if(storeEntity == null){
			throw new StoreCodeNotFoundException();
		}
		/* 门店号是否有效（审核通过） */
		if(!Consts.FINAL_NUMBER_1.equals(storeEntity.getStatus())){
			throw new StoreCodeInvalidException();
		}
		
	}
	
	/**
	 * 查询验证码信息
	 * @param registerReq
	 * @return
	 */
	public CustIdentifyCodeEntity queryTempIdentify(RegisterByllReq registerReq){
		
		CustTempIdentifyCodeQuery tempIdentifyQuery = new CustTempIdentifyCodeQuery();
		return tempIdentifyQuery.queryTempCode(registerReq.getMobileNo());
	}
	
	/**
	 * 验证码验证
	 * @param registerReq
	 * @return
	 * @throws ParseException 
	 */
	public boolean identifyValidate(RegisterByllReq registerReq,CustIdentifyCodeEntity identifyCode) throws ParseException{
		
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
	 * 创建用户
	 * @param registerReq
	 * @return
	 * @author Cary.Liu
	 * @throws SaveAuthorityException 
	 * @throws SaveCustUserException 
	 * @throws SaveCustomerException 
	 * @throws SaveAccountException 
	 * @createDate 2015-04-21
	 */
	private void registerUser(RegisterByllReq registerReq) throws SaveCustUserException, SaveAuthorityException, SaveCustomerException, SaveAccountException {

		/* 创建用户 信息 */
		CustUserEvent custUserEvent = new CustUserEvent();
		custUserEvent.createCustUserByll(registerReq);

		CustomerEvent customerEvent = new CustomerEvent();
		customerEvent.createCustomerByll(registerReq);

		/* 创建客户财务信息 */
		AccountEvent accountEvent = new AccountEvent();
		accountEvent.createAccountByll(registerReq);
		
		/* 生成登陆Token */
		LoginEvent loginEvent = new LoginEvent();
		token = UUID.randomUUID().toString();
		loginEvent.updateLoginToken(registerReq.getMobileNo(), token);
		
		/* 自动激活账户 */
		AccountQuery accountQuery = new AccountQuery();
		List<AccountEntity> accountList = accountQuery.queryAccount(registerReq.getMobileNo());
		if(accountList!=null && accountList.size()>0){
			String accountId = accountList.get(0).getAccountId();
			TransactionEvent event = new TransactionEvent();
			event.accountAutoActive(accountId);
		}
	}
	
	/**
	 * 授信额度
	 * @throws SaveRegisInfoException 
	 * @throws UpdatePageIndexException 
	 * @throws SQLException 
	 */
	private void setAmount(RegisterByllReq registerReq) throws UpdatePageIndexException, SQLException{

		CreDreamAmtReq req = new CreDreamAmtReq();
		
		req.setCustType(registerReq.getCustType());
		req.setUsername(registerReq.getMobileNo());
		req.setRealname(registerReq.getRealName());
		req.setIdentity(registerReq.getIdCard());
		
		CreDreamAmtService service = new CreDreamAmtService();
		CreDreamAmtResp creDreamAmtResp = (CreDreamAmtResp)service.responseValue(req);
	  
		if (ResultCodes.NORMAL.equals(creDreamAmtResp.getResultCode())) {
				
			/* 保存用户资料* */
//			CustomerEvent customerEvent = new CustomerEvent();
//			customerEvent.updateRegistInfo(registerReq);
			/* 更新页面跳转索引 */
			PageIndexEvent event = new PageIndexEvent();
			event.updateUserPageIndex(registerReq.getMobileNo(), Consts.PAGE_INDEX_5);
			amount = StringUtils.valueOf(creDreamAmtResp.getAmount());
		}
		
	}
	
	/**
	 * 获取用户信息
	 */
	public void queryUserInfo(RegisterByllReq registerReq){
		
		CustLimitQuery limitQuery = new CustLimitQuery();
		CustLimitEntity limitEntity = limitQuery.queryCustLimitByUserName(registerReq.getMobileNo());
		if(limitEntity != null){
			limitFlag = "1";//已经获取过额度
			
		}
		AccountQuery accountQuery = new AccountQuery();
		List<AccountEntity> accountEntities = accountQuery.queryAccount(registerReq.getMobileNo());
		if(!accountEntities.isEmpty()&&accountEntities.size()>0){
			TransactionQuery transactionQuery = new TransactionQuery();
			for (AccountEntity accountEntity : accountEntities) {
				List<TransactionEntity> entitys = transactionQuery.queryTransaction(accountEntity.getAccountId());
				if(!entitys.isEmpty()&&entitys.size()>0){
					accountFlag = "1";
					return;
				}
			}
			
		}
		
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
