package com.hengyuan.hicash.domain.service.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ExceptionMsg;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.event.amount.TransactionEvent;
import com.hengyuan.hicash.domain.event.user.CustRegLotteryEvent;
import com.hengyuan.hicash.domain.event.user.CustomerEvent;
import com.hengyuan.hicash.domain.query.user.AccountQuery;
import com.hengyuan.hicash.domain.query.user.CustRegLotteryQuery;
import com.hengyuan.hicash.domain.query.user.CustomerQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.user.AccountEntity;
import com.hengyuan.hicash.entity.user.CustRegLotteryEntity;
import com.hengyuan.hicash.entity.user.CustomerEntity;
import com.hengyuan.hicash.exception.TranXSCZException;
import com.hengyuan.hicash.exception.UpdateCustomerException;
import com.hengyuan.hicash.exception.UpdateLotteryException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.RegisterLotteryReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.RegisterLotteryResp;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 用户注册抽奖 service
 * 
 * @author Cary.Liu
 * @createDate 2016-01-11
 *
 */
public class RegisterLotteryService implements RespService {

	private String resultCode = "";
	
	/* 抽奖金额 */
	private String amount = Consts.FINAL_NUMBER_0;
	
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		RegisterLotteryReq lotteryReq = (RegisterLotteryReq)parmRequest;
		RegisterLotteryResp lotteryResp = new RegisterLotteryResp();
		
		try {
			
			CustomerEntity customer = queryCustomer(lotteryReq.getUserName());
			
			if(customer != null){
				
				if(isLotteryFlag(customer)){
					
					ConnManager.beginTransaction();
					/* 获取随机金额 */
					amount = StringUtils.getRandom(10, 20) + "";
					/* 将金额加到公司账户平台 */
					recAmount(lotteryReq.getUserName(), amount);
					/* 记录用户抽奖 */
					recordLottery(lotteryReq.getUserName(), amount);
					/* 更新客户信息 */
					updateCustomerLottery(lotteryReq.getUserName(), amount);
					
					ConnManager.commit();
					resultCode = ResultCodes.NORMAL;
				}else{
					resultCode = ResultCodes.REGLOTTERY_REPEAT_LOTTERY;
				}
				
			}else{
				resultCode = ResultCodes.REGLOTTERY_USER_NOTFOUND;
			}
			
		} catch (TranXSCZException e) {
			resultCode = ResultCodes.REGLOTTERY_SAVETRAN_EXCEPTION;
			e.printStackTrace();
			ConnManager.rollback();
		} catch (UpdateLotteryException e) {
			resultCode = ResultCodes.REGLOTTERY_RECORDLOTTERY_EXCEPTION;
			e.printStackTrace();
			ConnManager.rollback();
		} catch (UpdateCustomerException e) {
			resultCode = ResultCodes.REGLOTTERY_UPDATECUST_EXCEPTION;
			e.printStackTrace();
			ConnManager.rollback();
		} catch (Exception e) {
			resultCode = ResultCodes.REGLOTTERY_EXCEPTION;
			e.printStackTrace();
			ConnManager.rollback();
		} finally {
			ConnManager.closeConn();
		}
		
		lotteryResp.setResultCode(resultCode);
		lotteryResp.setLotteryAmount(amount);
		return lotteryResp;
	}
	
	/**
	 * 更新用户抽奖信息
	 * @param userName
	 * @param amount
	 * @throws UpdateCustomerException 
	 */
	private void updateCustomerLottery(String userName, String amount) throws UpdateCustomerException{
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		setMap.put("REGISTER_CASH_REDPAC", amount);
		setMap.put("REGISTER_LOTTERY_FLAG", Consts.FINAL_NUMBER_1);
		
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("USERNAME", userName);
		
		CustomerEvent customerEvent = new CustomerEvent();
		customerEvent.updateCustomerInfo(setMap, whereMap);
	}
	
	/**
	 * 记录用户抽奖
	 * @param userName
	 * @param amount
	 * @throws UpdateLotteryException
	 */
	private void recordLottery(String userName, String amount) throws UpdateLotteryException{
		
		CustRegLotteryEntity entity = new CustRegLotteryEntity();
		entity.setUserName(userName);
		entity.setAmount(amount);
		
		CustRegLotteryEvent event = new CustRegLotteryEvent();
		event.recordRegLottery(entity);
	}
	
	/**
	 * 充值
	 * @param userName
	 * @throws TranXSCZException 
	 */
	private void recAmount(String userName, String amount) throws TranXSCZException{
		
		AccountQuery accountQuery = new AccountQuery();
		List<AccountEntity> accountList = accountQuery.queryAccount(userName);
		
		if(accountList!=null && accountList.size()>0){
			String accountId = accountList.get(0).getAccountId();
			TransactionEvent event = new TransactionEvent();
			event.lotteryTran(accountId, amount);
		}else{
			throw new TranXSCZException(ExceptionMsg.SAVE_TRAN_EXCEPTION);
		}
		
	}
	
	private CustomerEntity queryCustomer(String userName){
		
		CustomerQuery customerQuery = new CustomerQuery();
		
		return customerQuery.queryCustByUser(userName);
	}
	
	/**
	 * 是否已经抽奖
	 * 不能重复抽奖
	 * @param customer
	 * @return
	 */
	private boolean isLotteryFlag(CustomerEntity customer){
		
		if(!Consts.FINAL_NUMBER_0.equals(customer.getRegLotteryFlag())){
			return false;
		}
		
		CustRegLotteryQuery lotteryQuery = new CustRegLotteryQuery();
		List<CustRegLotteryEntity> lotRecorde = lotteryQuery.queryLotteryRecord(customer.getUserName());
		if(lotRecorde != null && lotRecorde.size() > 0){
			return false;
		}
		
		return true;
	}
	
}
