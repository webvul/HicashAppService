package com.hengyuan.hicash.domain.event.amount;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ExceptionMsg;
import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.exception.TranXSCZException;
import com.hengyuan.hicash.utils.DateUtils;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * 交易流水
 * @author Cary.Liu
 * @create 2014-10-13
 *
 */
public class TransactionEvent {

	private static Logger logger = Logger.getLogger(TransactionEvent.class);
	
	/**
	 * 系统自动激活
	 * @param accountId
	 */
	public void accountAutoActive(String accountId){
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		setMap.put("TRAN_TYPE", "XSCC");
		setMap.put("ACCOUNT_ID", accountId);
		setMap.put("TRANS_DESC", "系统自动激活");
		setMap.put("CREATE_TIME", DateUtils.getDateStr(new Date()));
		setMap.put("AMOUNT", "0");
		
		String updateSql = MapAssemForSql.getInsertSql(TableConsts.ACCT_TRANSACTION, setMap);
		
		ConnManager.executeUpdate(updateSql);
	}
	
	/**
	 * 嗨秒贷代扣线上充值
	 * @param accountId
	 * @throws TranXSCZException 
	 */
	public void hmdProxyTran(String accountId) throws TranXSCZException{
		
		BigDecimal amount = new BigDecimal("1.00");
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		setMap.put("TRAN_TYPE", "XSCC");
		setMap.put("ACCOUNT_ID", accountId);
		setMap.put("TRANS_DESC", "嗨秒贷代扣，线上充值￥" + amount);
		setMap.put("CREATE_TIME", DateUtils.getDateStr(new Date()));
		setMap.put("AMOUNT", amount);
		
		String updateSql = MapAssemForSql.getInsertSql(TableConsts.ACCT_TRANSACTION, setMap);
		
		RecordUtils.writeAction(logger, null, updateSql);
		
		if(ConnManager.executeUpdate(updateSql) <= 0){
			throw new TranXSCZException(ExceptionMsg.PROXY_HMD_TRAN_EXCEPTION);
		}
	}
	/**
	 * 嗨秒贷代扣线上充值5元
	 * @param accountId
	 * @throws TranXSCZException 
	 */
	public void hmdProxyTran5(String accountId) throws TranXSCZException{
		
		BigDecimal amount = new BigDecimal("5.00");
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		setMap.put("TRAN_TYPE", "XSCC");
		setMap.put("ACCOUNT_ID", accountId);
		setMap.put("TRANS_DESC", "嗨秒贷代扣，线上充值￥" + amount);
		setMap.put("CREATE_TIME", DateUtils.getDateStr(new Date()));
		setMap.put("AMOUNT", amount);
		
		String updateSql = MapAssemForSql.getInsertSql(TableConsts.ACCT_TRANSACTION, setMap);
		
		RecordUtils.writeAction(logger, null, updateSql);
		
		if(ConnManager.executeUpdate(updateSql) <= 0){
			throw new TranXSCZException(ExceptionMsg.PROXY_HMD_TRAN_EXCEPTION);
		}
	}
	/**
	 * 线上充值
	 * @param accountId
	 * @throws TranXSCZException 
	 */
	public void deductMoneyTran(String accountId,String amount) throws TranXSCZException{
		
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		setMap.put("TRAN_TYPE", "XSCC");
		setMap.put("ACCOUNT_ID", accountId);
		setMap.put("TRANS_DESC", "代扣线上充值￥" + amount);
		setMap.put("CREATE_TIME", DateUtils.getDateStr(new Date()));
		setMap.put("AMOUNT", amount);
		
		String updateSql = MapAssemForSql.getInsertSql(TableConsts.ACCT_TRANSACTION, setMap);
		
		RecordUtils.writeAction(logger, null, updateSql);
		
		if(ConnManager.executeUpdate(updateSql) <= 0){
			throw new TranXSCZException(ExceptionMsg.PROXY_HMD_TRAN_EXCEPTION);
		}
	}
	
	/**
	 * 嗨秒贷代扣线上充值
	 * @param accountId
	 * @throws TranXSCZException 
	 */
	public void lotteryTran(String accountId, String amount) throws TranXSCZException{
		
		
		Map<String, Object> setMap = new HashMap<String, Object>();
		setMap.put("TRAN_TYPE", "XSCC");
		setMap.put("ACCOUNT_ID", accountId);
		setMap.put("TRANS_DESC", "注册成功抽奖活动，线上充值￥" + amount);
		setMap.put("CREATE_TIME", DateUtils.getDateStr(new Date()));
		setMap.put("AMOUNT", amount);
		
		String updateSql = MapAssemForSql.getInsertSql(TableConsts.ACCT_TRANSACTION, setMap);
		
		RecordUtils.writeAction(logger, null, updateSql);
		
		if(ConnManager.executeUpdate(updateSql) <= 0){
			throw new TranXSCZException(ExceptionMsg.SAVE_TRAN_EXCEPTION);
		}
	}
	
}
