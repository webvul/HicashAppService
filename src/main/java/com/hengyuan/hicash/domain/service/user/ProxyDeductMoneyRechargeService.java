package com.hengyuan.hicash.domain.service.user;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ExceptionMsg;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.event.amount.TransactionEvent;
import com.hengyuan.hicash.domain.event.user.HmdProxyRecordEvent;
import com.hengyuan.hicash.domain.event.user.HmdProxyValEvent;
import com.hengyuan.hicash.domain.query.param.BankQuery;
import com.hengyuan.hicash.domain.query.user.AccountQuery;
import com.hengyuan.hicash.domain.query.user.CustomerQuery;
import com.hengyuan.hicash.domain.query.user.HmdProxyRecordQuery;
import com.hengyuan.hicash.domain.query.user.HmdProxyValQuery;
import com.hengyuan.hicash.domain.query.user.ServiceConfigQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.param.BankEntity;
import com.hengyuan.hicash.entity.param.CmbcGFAResEntity;
import com.hengyuan.hicash.entity.user.AccountEntity;
import com.hengyuan.hicash.entity.user.CustomerEntity;
import com.hengyuan.hicash.entity.user.HmdProxyRecordEntity;
import com.hengyuan.hicash.entity.user.HmdProxyValEntity;
import com.hengyuan.hicash.entity.user.ServiceConfigEntity;
import com.hengyuan.hicash.exception.HttpReturnException;
import com.hengyuan.hicash.exception.HttpUrlRemoteException;
import com.hengyuan.hicash.exception.TranXSCZException;
import com.hengyuan.hicash.exception.UpdateHmdProxyValException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.ProxyDeductMoneyRechargeReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.ProxyDeductMoneyRechargeResp;
import com.hengyuan.hicash.utils.DateUtils;
import com.hengyuan.hicash.utils.HttpRemotePost;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 嗨秒贷 验证用户收款银行卡信息,5块钱 代理扣款
 * 
 * @author Lihua.Ren
 * @createDate 2015-11-03
 *
 */
public class ProxyDeductMoneyRechargeService implements RespService {

	private static Logger logger = Logger
			.getLogger(ProxyDeductMoneyRechargeService.class);

	private String resultCode = "";

	private String bankName = "";
	private String status = "";
	private String validateMsg = "";
	private String bussFlowNo = "";// 交易流水号

	private String bankRtnCode = ""; // 银行返回结果代码

	private String bankRtnMsg = ""; // 银行返回结果消息

	// private String proxyStatus = "";
	// 鉴权验证码交易流水号
	// private String bussFlowNoVal = "";

	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		ProxyDeductMoneyRechargeReq proxyReq = (ProxyDeductMoneyRechargeReq) parmRequest;
		ProxyDeductMoneyRechargeResp proxyResp = new ProxyDeductMoneyRechargeResp();

		try {

			/* 获取用户资料 */
			CustomerEntity customer = queryCustomer(proxyReq.getUserName());
			if (customer != null) {

				ConnManager.beginTransaction();

				bankName = queryBankName(proxyReq.getOpenBank());

				/* 如果之前调用过代扣接口 */
				if (queryHmdProxy5Succ(customer.getRealName(),
						proxyReq.getBankCard())) {
					status = Consts.PROXY_STATUS_SUCC;// 代扣成功
					resultCode = ResultCodes.NORMAL;

				} else if (queryHmdProxy5Wait(customer.getRealName(),
						proxyReq.getBankCard())) {
					status = Consts.PROXY_STATUS_WAIT;// 代扣处理中
					resultCode = ResultCodes.NORMAL;
				} else if (queryHmdProxy5YEBZ(customer.getRealName(),
						proxyReq.getBankCard())) {
					status = Consts.PROXY_STATUS_YEBZ;// 代扣余额不足
					resultCode = ResultCodes.NORMAL;
				} else {
					// 查询短信信息
					HmdProxyValEntity identifyCodeEntity = querySendCodeEntity(proxyReq
							.getOrgBussFlowNo());

					if (identifyCodeEntity != null) {

						/* 客户号，订单号，手机令牌 */
						if (identifyValidate(proxyReq, identifyCodeEntity)) {
							
							CmbcGFAResEntity cmbcResp = remoteHttpProxyDeduct(
									proxyReq, customer);

							logger.info("\n#####【代扣扣款结果："
									+ cmbcResp.getReturnCode() + "，"
									+ cmbcResp.getErrorMsg() + "，流水号："
									+ cmbcResp.getBussFlowNo() + "】#####");
							bussFlowNo = cmbcResp.getBussFlowNo();

							bankRtnCode = cmbcResp.getRespCode();
							bankRtnMsg = cmbcResp.getRespMsg();

							/* 扣款成功 */
							if (Consts.REMOTE_RESULT_01.equals(cmbcResp
									.getReturnCode())) {

								status = Consts.PROXY_STATUS_SUCC;
								resultCode = ResultCodes.NORMAL;

								/* 代扣成功 充值到用户账户 */
								hmdTranAmount(customer.getUserName());

							} else {

								/* 处理中 */
								if (Consts.REMOTE_RESULT_00.equals(cmbcResp
										.getReturnCode())) {
									status = Consts.PROXY_STATUS_WAIT;
									resultCode = ResultCodes.NORMAL;

									// proxyStatus = Consts.PROXY_STATUS_WAIT;
								} else if (Consts.REMOTE_RESULT_04
										.equals(cmbcResp.getReturnCode())) {
									/* 代扣余额不足 */
									status = Consts.PROXY_STATUS_YEBZ;
									resultCode = ResultCodes.NORMAL;
									// proxyStatus = Consts.PROXY_STATUS_YEBZ;
								} else if (Consts.REMOTE_RESULT_404
										.equals(cmbcResp.getReturnCode())) {
									/* 连接超时 */
									resultCode = ResultCodes.NORMAL;
									status = Consts.FINAL_NUMBER_6;
								} else {
									/* 鉴权错误，失败 */
									status = Consts.PROXY_STATUS_FAIL;
									resultCode = ResultCodes.NO_RESULT;
								}

							}

							validateMsg = cmbcResp.getErrorMsg();

							/* 记录嗨秒贷代扣验证状态 */
							updateHmdProxyResult(proxyReq, customer);

							if (!Consts.REMOTE_RESULT_404.equals(cmbcResp
									.getReturnCode())) {
								/* 记录代扣状态 */
								recordHmdProxyStatus(proxyReq, customer, status);
							}

						}

					} else {

						resultCode = ResultCodes.PROXY_DEDUCTMONEYNEW_FALSE;
					}
				}
				ConnManager.commit();

			} else {
				resultCode = ResultCodes.PROXYDEDUCTMONEY_USER_NOTFOUND;
			}

		} catch (TranXSCZException e) {
			resultCode = ResultCodes.PROXYDEDUCTMONEY_PROXY_EXCEPTION;
			ConnManager.rollback();
		} catch (UpdateHmdProxyValException e) {
			resultCode = ResultCodes.HMD_PROXYDEDUCT_RECORD_UPDATE_EXCEPTION;
			e.printStackTrace();
			ConnManager.rollback();
		} 
		
		catch (Exception e) {
			resultCode = ResultCodes.PROXYDEDUCTMONEY_EXCETPION;
			e.printStackTrace();
			ConnManager.rollback();
		}
		
		finally {
			ConnManager.closeConn();
		}
		proxyResp.setValStatus(status);
		proxyResp.setResultCode(resultCode);
		return proxyResp;
	}

	/**
	 * 验证码验证
	 * 
	 * @param registerReq
	 * @return
	 * @throws ParseException
	 */
	public boolean identifyValidate(ProxyDeductMoneyRechargeReq confirmCodeReq,
			HmdProxyValEntity identifyCode) throws ParseException {
		String merOrderId = identifyCode.getMerOrderId();
		String custId = identifyCode.getCustId();
		String phoneToken = identifyCode.getPhoneToken();

		if (merOrderId != null
				&& merOrderId.trim().equals(
						confirmCodeReq.getMerOrderId().trim())) {

			if (custId != null
					&& custId.trim().equals(confirmCodeReq.getCustId().trim())) {
				if (phoneToken != null
						&& phoneToken.trim().equals(
								confirmCodeReq.getPhoneToken().trim())) {

					return true;

				} else {
					resultCode = ResultCodes.PROXY_DEDUCTMONEYNEW_PHONETOKEN_FALSE;
				}

			} else {
				resultCode = ResultCodes.PROXY_DEDUCTMONEYNEW_CUSTID_FALSE;
			}

		} else {
			resultCode = ResultCodes.PROXY_DEDUCTMONEYNEW_ORDERID_FALSE;
		}

		return false;

	}

	/**
	 * 获取用户验证记录
	 * 
	 * @param realName
	 * @param bankCard
	 * @return
	 */
	public HmdProxyValEntity querySendCodeEntity(String orgBussFlowNo) {

		HmdProxyValQuery confirmQuery = new HmdProxyValQuery();
		HmdProxyValEntity entity = confirmQuery
				.querySendCodeSucc(orgBussFlowNo);

		return entity;
	}

	/**
	 * 记录嗨秒贷代扣状态
	 * 
	 * @param proxyReq
	 * @param customer
	 * @param status
	 */
	private void recordHmdProxyStatus(ProxyDeductMoneyRechargeReq proxyReq,
			CustomerEntity customer, String status) {

		Map<String, Object> inputMap = new HashMap<String, Object>();
		inputMap.put("USERNAME", customer.getUserName());
		inputMap.put("REAL_NAME", customer.getRealName());
		inputMap.put("IDENTITY_NO", customer.getIdentityNo());
		inputMap.put("BANK_CARD", proxyReq.getBankCard());
		inputMap.put("BANK_NAME", bankName);
		inputMap.put("BUSS_FLOWNO", bussFlowNo);
		inputMap.put("PROXY_STATUS", status);
		inputMap.put("PROXY_AMOUNT", Consts.FINAL_DECIMAL_2);
		inputMap.put("CREATE_DATE", DateUtils.getDateStr(new Date()));
		// DOTO////增加那几个字段???????发送短信接口和验证短信接口，相对应的是订单号，所以这里要保存订单号
		HmdProxyRecordEvent recordEvent = new HmdProxyRecordEvent();
		recordEvent.recordHmdProxyRecord(inputMap);
	}

	/**
	 * 查询用户银行卡是否有验证记录
	 * 
	 * @param proxyReq
	 * @param customer
	 * @return
	 */
	public HmdProxyValEntity queryAccountValRecord(
			ProxyDeductMoneyRechargeReq proxyReq, CustomerEntity customer) {

		HmdProxyValQuery query = new HmdProxyValQuery();
		return query.queryByNameAndCard(customer.getRealName(),
				proxyReq.getBankCard(), customer.getIdentityNo());
	}

	// /**
	// * 查询用户银行卡是否有验证记录
	// * @param infoReq
	// * @param inputApp
	// * @return
	// */
	// public AccountValRecordEntity
	// queryAccountValRecord(ProxyDeductMoneyRechargeReq proxyReq,CustomerEntity
	// customer){
	//
	// AccountValRecordQuery query = new AccountValRecordQuery();
	// return query.queryRecord(proxyReq.getUserName(),
	// customer.getIdentityNo(), proxyReq.getBankCard());
	// }

	/**
	 * 获取用户资料
	 * 
	 * @param userName
	 * @return
	 */
	public CustomerEntity queryCustomer(String userName) {

		CustomerQuery query = new CustomerQuery();
		return query.queryCustByUserName(userName);
	}

	/**
	 * 代扣扣款
	 * 
	 * @param proxyReq
	 * @param customer
	 * @return
	 * @throws HttpException
	 * @throws IOException
	 * @throws HttpReturnException
	 * @throws HttpUrlRemoteException
	 */
	private CmbcGFAResEntity remoteHttpProxyDeduct(
			ProxyDeductMoneyRechargeReq proxyReq, CustomerEntity customer)
			throws HttpException, IOException, HttpReturnException,
			HttpUrlRemoteException {

		// String merchantNo = "CF3000034853";//测试商户号
		// String merchantNo = "CF2000027924";//生产商户号CF2000027924 20150728
		// 195222 963414
		String merchantNo = ResourceUtils.getValue("merchantNo");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String timestamp = sdf.format(new Date());
		String randomStr = RandomStringUtils.random(6, false, true);
		bussFlowNo = merchantNo + timestamp + randomStr;
		Map<String, String> parmMap = new HashMap<String, String>();
		/* 业务参数 */
		parmMap.put("accountNo", proxyReq.getBankCard());// 银行帐号
		parmMap.put("accountName", customer.getRealName());// 账户名称
		parmMap.put("tranAmt", "5");// 代扣金额
		parmMap.put("certNo", customer.getIdentityNo());// 用户身份证号码
		parmMap.put("bankName", bankName);// 代扣银行名称
		parmMap.put("bussFlowNo", bussFlowNo);// 交易流水号
		parmMap.put("custId", proxyReq.getCustId());
		parmMap.put("merOrderId", proxyReq.getMerOrderId());
		parmMap.put("phoneToken", proxyReq.getPhoneToken());
		parmMap.put("phoneVerCode", proxyReq.getPhoneVerCode());
		parmMap.put("mobileNo", proxyReq.getMobileNo());
		ServiceConfigQuery configQuery = new ServiceConfigQuery();
		ServiceConfigEntity serviceConfigEntity = configQuery
				.queryServiceByCode(Consts.SERVICE_BANKCARD_PROXYDEDUCT_RECHARGE);

		// String httpResp =
		// HttpRemotePost.sendHttp(serviceConfigEntity.getServiceUrl(),
		// parmMap);

		String httpResp = "";

		try {

			httpResp = HttpRemotePost.sendHttp(
					serviceConfigEntity.getServiceUrl(), parmMap);

		} catch (ConnectTimeoutException e) {
			logger.info("\n[嗨秒贷代扣]：异常 => 远程连接超时。。");
			e.printStackTrace();
			CmbcGFAResEntity entity = new CmbcGFAResEntity();
			entity.setReturnCode("404");
			entity.setErrorMsg("远程连接超时");
			entity.setBussFlowNo("");
			entity.setRespCode("");
			entity.setRespMsg("");
			return entity;
		} catch (SocketTimeoutException e) {
			logger.info("\n[嗨秒贷代扣]：异常 => 远程读取数据超时。。");
			e.printStackTrace();
			CmbcGFAResEntity entity = new CmbcGFAResEntity();
			entity.setReturnCode("404");
			entity.setErrorMsg("远程读取数据超时");
			entity.setBussFlowNo("");
			entity.setRespCode("");
			entity.setRespMsg("");
			return entity;
		}

		return new Gson().fromJson(httpResp, CmbcGFAResEntity.class);
	}

	/**
	 * 获取用户代扣记录
	 * 
	 * @param realName
	 * @param bankCard
	 * @return
	 */
	public boolean queryHmdProxy5Succ(String realName, String bankCard) {

		HmdProxyRecordQuery hmdProxyQuery = new HmdProxyRecordQuery();
		HmdProxyRecordEntity entity = hmdProxyQuery.queryProxySuccess(realName,
				bankCard);

		if (entity != null) {

			return true;
		}

		return false;
	}

	/**
	 * 获取用户代扣记录
	 * 
	 * @param realName
	 * @param bankCard
	 * @return
	 */
	public boolean queryHmdProxy5Wait(String realName, String bankCard) {

		HmdProxyRecordQuery hmdProxyQuery = new HmdProxyRecordQuery();
		HmdProxyRecordEntity entity = hmdProxyQuery.queryProxyWait(realName,
				bankCard);

		if (entity != null) {

			return true;
		}

		return false;
	}

	/**
	 * 获取用户代扣记录
	 * 
	 * @param realName
	 * @param bankCard
	 * @return
	 */
	public boolean queryHmdProxy5YEBZ(String realName, String bankCard) {

		HmdProxyRecordQuery hmdProxyQuery = new HmdProxyRecordQuery();
		HmdProxyRecordEntity entity = hmdProxyQuery.queryProxyYEBZ(realName,
				bankCard);

		if (entity != null) {

			return true;
		}

		return false;
	}

	/**
	 * 嗨秒贷代扣充值
	 * 
	 * @param userName
	 * @throws TranXSCZException
	 */
	private void hmdTranAmount(String userName) throws TranXSCZException {

		AccountQuery accountQuery = new AccountQuery();
		List<AccountEntity> accountList = accountQuery.queryAccount(userName);

		if (accountList != null && accountList.size() > 0) {
			String accountId = accountList.get(0).getAccountId();
			TransactionEvent event = new TransactionEvent();
			event.hmdProxyTran5(accountId);
		} else {

			throw new TranXSCZException(ExceptionMsg.PROXY_HMD_TRAN_EXCEPTION);
		}

	}

	/**
	 * 获取代扣银行名称
	 * 
	 * @param bankCode
	 * @return
	 */
	private String queryBankName(String bankCode) {

		BankQuery bankQuery = new BankQuery();
		BankEntity entity = bankQuery.queryProxyBankByCode(bankCode);
		if (entity != null) {
			return entity.getBankName();
		}
		return "";
	}

	/**
	 * 记录嗨秒贷代扣状态
	 * 
	 * @param proxyReq
	 * @param customer
	 * @throws UpdateHmdProxyValException
	 */
	private void updateHmdProxyResult(ProxyDeductMoneyRechargeReq proxyReq,
			CustomerEntity customer) throws UpdateHmdProxyValException {

		Map<String, Object> inputMap = new HashMap<String, Object>();

		inputMap.put("proxy_status", status);
		inputMap.put("VALIDATE_MSG", validateMsg);
		inputMap.put("BUSS_FLOWNO_VAL", bussFlowNo);
		inputMap.put("BANK_RTN_CODE", bankRtnCode);
		inputMap.put("BANK_RTN_MSG", bankRtnMsg);
		inputMap.put("UPDATE_TIME", DateUtils.getDateStr(new Date()));
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("BUSS_FLOWNO", proxyReq.getOrgBussFlowNo());

		HmdProxyValEvent hmdValEvent = new HmdProxyValEvent();
		hmdValEvent.updateHmdProxyVal5(inputMap, whereMap);
	}
}
