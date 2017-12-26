package com.hengyuan.hicash.domain.event.user;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.TableConsts;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.exception.SaveAccountException;
import com.hengyuan.hicash.parameters.request.user.RegisterByllReq;
import com.hengyuan.hicash.parameters.request.user.RegisterReq;
import com.hengyuan.hicash.utils.DateUtils;
import com.hengyuan.hicash.utils.MapAssemForSql;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * 账户信息操作类
 * @author Cary.Liu
 * @createDate 2015-04-21
 */
public class AccountEvent {
	
	private static Logger logger = Logger.getLogger(AccountEvent.class);
	
	/**
	 * 用户注册创建账户信息
	 * @param req
	 * @throws SaveAccountException
	 * @author Cary.Liu
	 * @create 2014-08-01
	 */
	public void createAccount(RegisterReq req) throws SaveAccountException {

		Map<String, Object> setMap = new HashMap<String, Object>();
		setMap.put("USERNAME", req.getMobileNo());
		setMap.put("BATCH_TIME", DateUtils.getDateStr(new Date()));
		setMap.put("CREATE_TIME", DateUtils.getDateStr(new Date()));
		setMap.put("ACCOUNT_TYPE", "CONS");
		setMap.put("CREDIT_LIMIT_BLANCE", "0");
		setMap.put("TOTAL_LOAN_TIMES", "0");
		setMap.put("LOAN_SUCC_TIMES", "0");
		setMap.put("TOTAL_LOAN_AMOUNT", "0");
		setMap.put("PRE_AUTHO_AMOUNT", "0");

		String createSql = MapAssemForSql.getInsertSql(TableConsts.ACC_ACCOUNT,setMap);
		RecordUtils.writeAction(logger, req.getUuid(), createSql);
		if (ConnManager.executeUpdate(createSql) <= 0) {
			throw new SaveAccountException();
		}

	}
	
	/**
	 * 用户注册创建账户信息
	 * @param req
	 * @throws SaveAccountException
	 * @author Cary.Liu
	 * @create 2014-08-01
	 */
	public void createAccountByll(RegisterByllReq req) throws SaveAccountException {

		Map<String, Object> setMap = new HashMap<String, Object>();
		setMap.put("USERNAME", req.getMobileNo());
		setMap.put("BATCH_TIME", DateUtils.getDateStr(new Date()));
		setMap.put("CREATE_TIME", DateUtils.getDateStr(new Date()));
		setMap.put("ACCOUNT_TYPE", "CONS");
		setMap.put("CREDIT_LIMIT_BLANCE", "0");
		setMap.put("TOTAL_LOAN_TIMES", "0");
		setMap.put("LOAN_SUCC_TIMES", "0");
		setMap.put("TOTAL_LOAN_AMOUNT", "0");
		setMap.put("PRE_AUTHO_AMOUNT", "0");

		String createSql = MapAssemForSql.getInsertSql(TableConsts.ACC_ACCOUNT,setMap);
		RecordUtils.writeAction(logger, req.getUuid(), createSql);
		if (ConnManager.executeUpdate(createSql) <= 0) {
			throw new SaveAccountException();
		}

	}
	
}
