package com.hengyuan.hicash.domain.service.user;

import java.util.List;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.query.user.AccountQuery;
import com.hengyuan.hicash.domain.query.user.CustLimitQuery;
import com.hengyuan.hicash.domain.query.user.CustUserQuery;
import com.hengyuan.hicash.domain.query.user.TransactionQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.user.AccountEntity;
import com.hengyuan.hicash.entity.user.CustLimitEntity;
import com.hengyuan.hicash.entity.user.TransactionEntity;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.AccountStatusReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.AccountStatusResp;

/**
 * 查看账户状态（是否激活账户和）
 * 
 * @author Cary.Liu
 * @create 2014-08-24
 *
 */
public class AccountStatusService implements RespService{

	private String resultCode = "";
	private String limitFlag = "0";//是否已授信额度
	private String accountFlag = "0";//是否激活账户
	
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		AccountStatusReq req = (AccountStatusReq)parmRequest;
		AccountStatusResp resp = new AccountStatusResp();
		
		try {
			
			if(isUserExist(req.getUserName())){
				queryCreditAccountFlag(req.getUserName());
				resultCode = ResultCodes.NORMAL;
			}else{
				resultCode = ResultCodes.USER_ACCOUNTSTATUS_USER_EXIST;
			}
			
		} catch (Exception e) {
			resultCode = ResultCodes.USER_ACCOUNTSTATUS_EXCEPTION;
		}
		resp.setResultCode(resultCode);
		resp.setLimitFlag(limitFlag);
		resp.setAccountFlag(accountFlag);
		return resp;
	}

	/**
	 * 验证用户是否存在
	 * @param userName
	 * @return
	 */
	public boolean isUserExist(String userName){
		
		CustUserQuery custUserQuery = new CustUserQuery();
		return custUserQuery.isUserExist(userName);
	}
	
	/**
	 * 查询是否激活过账户和授信额度
	 */
	public void queryCreditAccountFlag(String userName){
		CustLimitQuery limitQuery = new CustLimitQuery();
		CustLimitEntity limitEntity = limitQuery.queryCustLimitByUserName(userName);
		if(limitEntity != null){
			limitFlag = "1";//已经获取过额度
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
	
	
}
