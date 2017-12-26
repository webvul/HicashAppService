package com.hengyuan.hicash.domain.service.user;


import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.event.user.LoginEvent;
import com.hengyuan.hicash.domain.query.param.SystemParamQuery;
import com.hengyuan.hicash.domain.query.user.AccountQuery;
import com.hengyuan.hicash.domain.query.user.CustLimitQuery;
import com.hengyuan.hicash.domain.query.user.CustUserQuery;
import com.hengyuan.hicash.domain.query.user.CustomerQuery;
import com.hengyuan.hicash.domain.query.user.LoginQuery;
import com.hengyuan.hicash.domain.query.user.TransactionQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.user.AccountEntity;
import com.hengyuan.hicash.entity.user.CustLimitEntity;
import com.hengyuan.hicash.entity.user.CustUserEntity;
import com.hengyuan.hicash.entity.user.CustomerEntity;
import com.hengyuan.hicash.entity.user.LoginEntity;
import com.hengyuan.hicash.entity.user.TransactionEntity;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.LoginReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.LoginResp;
import com.hengyuan.hicash.utils.DateUtils;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 用户登录的逻辑
 * @author Cary.Liu
 * @createDate 2015-04-20
 * 
 */
public class LoginService implements RespService {
	
	private String resultCode = "";
	
	private String limitFlag = Consts.FINAL_NUMBER_0;//是否已获取过额度
	private String limitAmount = "";//额度值
	private String accountFlag = Consts.FINAL_NUMBER_0;//是否激活账户
	private String isDoubleSales = Consts.FINAL_NUMBER_0; // 是否是二次营销
	private String inOneMonthReg = Consts.FINAL_NUMBER_0; // 是否在一个月内注册用户
	private String isLanUserFlag = Consts.FINAL_NUMBER_0; // 是否为蓝领用户

	private LoginQuery loginQuery = new LoginQuery();
	private LoginEvent loginEvent = new LoginEvent();

	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		LoginReq loginReq = (LoginReq) parmRequest;
		LoginResp loginResp = new LoginResp();
		
		try {
			
			ConnManager.beginTransaction();
			LoginEntity existFlag = userLogin(loginReq);
			
			if (existFlag != null) {
				if(existFlag.getLockedNum() < 5){
					/* 验证密码是否正确 */
					if (isLoginSuccess(loginReq)) {
						
						updateLastLoginInfo(existFlag, loginReq);
						loginResp = setRespInfo(existFlag, loginResp,loginReq);
						resultCode = ResultCodes.NORMAL;
					} else {
						// 查询已存在用户连续登录失败的次数
						int lockedNum = queryLockedNum(loginReq);
						if (lockedNum < 5) {
							lockedNum++;
							loginEvent.updateLockedNum(existFlag.getUserName(),lockedNum);
							resultCode = ResultCodes.LOGIN_USERORPSW_FAIL;
						} else {
							resultCode = ResultCodes.LOGIN_USER_LOCKED;
						}
					}
					
				}else{
					resultCode = ResultCodes.LOGIN_USER_LOCKED;
				}

			} else {
				resultCode = ResultCodes.LOGIN_USERORPSW_FAIL;
			}
			
			ConnManager.commit();
		} catch (Exception e) {
			resultCode = ResultCodes.LOGIN_EXCEPTION;
			ConnManager.rollback();
		}finally{
			ConnManager.closeConn();
		}

		loginResp.setResultCode(resultCode);
		return loginResp;
	}
	
	/**
	 * 查询是否激活过账户和授信额度
	 * @param userName
	 */
	public void queryCreditAccountFlag(String userName){
		CustLimitQuery limitQuery = new CustLimitQuery();
		CustLimitEntity limitEntity = limitQuery.queryCustLimitByUserName(userName);
		if(limitEntity != null){
			limitFlag = "1";//已经获取过额度
			limitAmount = limitEntity.getTrustAmt();
			
		}
		AccountQuery accountQuery = new AccountQuery();
		List<AccountEntity> accountEntities = accountQuery.queryAccount(userName);
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
	 * 查询系统参数ID所对应的名称
	 * @param paramCode 系统参数代码dicCode
	 */
	public String queryParamName(String paramCode){
		SystemParamQuery query = new SystemParamQuery();
		return query.queryParamByDicCode(paramCode).getDicName();
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

	/**
	 * 根据用户名查找用户
	 * @param userName
	 * @return
	 */
	public CustomerEntity queryCustomerByUserName(String userName){
		CustomerQuery customerQuery = new CustomerQuery();
		return customerQuery.queryCustomerByUserName(userName);
	}
	
	/**
	 * 查询该用户是否存在
	 * 
	 * @param userName
	 * @return
	 */
	public boolean isUserExistQuery(LoginReq login) {
		LoginEntity entity = loginQuery.userExistQuery(login);

		if (entity != null && entity.isExistFlag()) {
			return true;
		}
		return false;
	}
	
	public LoginEntity userLogin(LoginReq login) {
		LoginEntity entity = loginQuery.userExistQuery(login);

		if (entity != null && entity.isExistFlag()) {
			return entity;
		}
		return null;
	}

	/**
	 * 验证密码是否正确
	 * @return
	 */
	public boolean isLoginSuccess(LoginReq login) {
		LoginEntity entity = loginQuery.userExistQuery(login);
		Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
		boolean isSucc = md5PasswordEncoder.isPasswordValid(
				entity.getOldPassword(), login.getPassWord(),
				entity.getOldSalt());
		return isSucc;
	}

	/**
	 * 获取连续登录失败次数
	 * 
	 * @param login
	 * @return
	 */
	public int queryLockedNum(LoginReq login) {
		LoginEntity entity = loginQuery.userExistQuery(login);
		if (entity != null) {
			return entity.getLockedNum();
		} else {
			return 0;
		}
	}
	
	/**
	 * 更新用户最后一次登录信息
	 * @param existFlag
	 * @param login
	 */
	public void updateLastLoginInfo(LoginEntity existFlag,LoginReq login){
		
		/* 更新最后一次登录时间 */
		loginEvent.updateLoginTimeByApp(existFlag.getUserName(),login.getCityCode());
		/* 更新最后一次登录认证标志 */
		loginEvent.updateLoginToken(existFlag.getUserName(), UUID.randomUUID().toString());
	}
	
	/**
	 * 设置用户登陆成功后的返回消息
	 * @param existFlag
	 * @param loginResp
	 * @return
	 */
	public LoginResp setRespInfo(LoginEntity existFlag,LoginResp loginResp,LoginReq loginReq){
		
		CustomerEntity customer = queryCustomerByUserName(existFlag.getUserName());
		CustUserEntity custUser = queryCustUserByUserName(existFlag.getUserName());
		
		if(customer != null){
			
			loginResp.setUserName(existFlag.getUserName());
			loginResp.setRealName(customer.getRealName());
			loginResp.setCustType(customer.getCustType());
			loginResp.setMobile(customer.getMobile());
			loginResp.setIdentityNo(customer.getIdentityNo());
			if(Consts.CUSTTYPE_KHL1.equals(customer.getCustType())){
				loginResp.setCustTypeName(Consts.CUSTTYPE_KHL1_NAME);;
			}else{
				loginResp.setCustTypeName(Consts.CUSTTYPE_KHL2_NAME);;
				loginResp.setEducation(customer.getEducation());
				loginResp.setEducationName(queryParamName(customer.getEducation()));
			}
			
		}
		if(custUser != null){
			loginResp.setToken(custUser.getLoginToken());
			loginResp.setPageIndex(custUser.getPageIndex());
			isDoubleSales = custUser.getIsDoubleSales();
			if(!StringUtils.isEmpty(custUser.getCreateTime())){
				// 用户是在注册后1个月内
				long longTime = DateUtils.getDaysBetween(custUser.getCreateTime(), new Date());
				if(longTime < 31){
					inOneMonthReg = Consts.FINAL_NUMBER_1;
				}
			}
		}
		if(customer != null){
			// 是为蓝领用户
			if(customer.getLanUserFlag() != null && Consts.FINAL_NUMBER_1.equals(customer.getLanUserFlag())){
				isLanUserFlag = Consts.FINAL_NUMBER_1;
			}
		}
		queryCreditAccountFlag(existFlag.getUserName());
		loginResp.setAccountFlag(accountFlag);
		loginResp.setLimitFlag(limitFlag);
		loginResp.setLimitAmount(limitAmount);
		loginResp.setCityCode(loginReq.getCityCode());
		loginResp.setIsDoubleSales(isDoubleSales);
		loginResp.setIsLanUserFlag(isLanUserFlag);
		loginResp.setInOneMonthReg(inOneMonthReg);
		
		return loginResp;
	}
	

}
