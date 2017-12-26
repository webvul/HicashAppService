package com.hengyuan.hicash.domain.service.remote;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.HttpException;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ExceptionMsg;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.event.amount.TransactionEvent;
import com.hengyuan.hicash.domain.event.user.HmdProxyRecordEvent;
import com.hengyuan.hicash.domain.event.user.HmdProxyValEvent;
import com.hengyuan.hicash.domain.query.app.ApplicationQuery;
import com.hengyuan.hicash.domain.query.mktApp.PayFlowNoQuery;
import com.hengyuan.hicash.domain.query.param.BankQuery;
import com.hengyuan.hicash.domain.query.user.AccountQuery;
import com.hengyuan.hicash.domain.query.user.CustomerQuery;
import com.hengyuan.hicash.domain.query.user.ServiceConfigQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.domain.service.user.ProxyDeductMoneyService;
import com.hengyuan.hicash.entity.app.ApplicationEntity;
import com.hengyuan.hicash.entity.param.BankEntity;
import com.hengyuan.hicash.entity.param.CmbcGFAResEntity;
import com.hengyuan.hicash.entity.user.AccountEntity;
import com.hengyuan.hicash.entity.user.CustomerEntity;
import com.hengyuan.hicash.entity.user.ServiceConfigEntity;
import com.hengyuan.hicash.exception.HttpReturnException;
import com.hengyuan.hicash.exception.HttpUrlRemoteException;
import com.hengyuan.hicash.exception.QueryFlowNoException;
import com.hengyuan.hicash.exception.TranXSCZException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.DeductMoneyReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.DeductMoneyResp;
import com.hengyuan.hicash.utils.DateUtils;
import com.hengyuan.hicash.utils.HttpRemotePost;
import com.hengyuan.hicash.utils.ResourceUtils;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 扣款 service
 * @author cary.Liu
 * @createDate 2015-08-27
 *
 */
public class DeductMoneyService implements RespService {

	private static Logger logger = Logger.getLogger(ProxyDeductMoneyService.class);
	
	private String resultCode = "";
	
	private String bankName = "";
	private String status = "";
	private String validateMsg = "";
	private String bussFlowNo = "";//交易流水号
	
	private String bankRtnCode = ""; // 银行返回结果代码
	
	private String bankRtnMsg = ""; // 银行返回结果消息
	
	private String proxyStatus = "";
	
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		DeductMoneyReq moneyReq = (DeductMoneyReq)parmRequest;
		DeductMoneyResp moneyResp = new DeductMoneyResp();
		
		try {
			
			/* 获取用户资料 */
			CustomerEntity customer = queryCustomer(moneyReq.getUserName());
			if(customer != null){
				
				if(isExistHkApp(moneyReq.getUserName())){
					
					logger.info("\n##代扣扣款["+DateUtils.getDateStr(new Date())+"，用户："+customer.getUserName()+"，金额："+moneyReq.getAmount()+"]##");
					
					/* 获取系统银行名称 */
					bankName = queryBankName(moneyReq.getOpenBank());
					
					bussFlowNo = getFlowNo();
					
					/* 插入初始代扣记录 */
					int i = initProxyVal(moneyReq, customer);
					
					if(i > 0){
						
						CmbcGFAResEntity cmbcResp = remoteHttpProxyDeduct(moneyReq, customer,bankName);
						
						ConnManager.beginTransaction();
						
						logger.info("\n#####【"+DateUtils.getDateStr(new Date())+" 代扣充值扣款结果："+ cmbcResp.getReturnCode() +"，"+cmbcResp.getErrorMsg() + "，流水号："+cmbcResp.getBussFlowNo()+"】#####");
//						bussFlowNo = cmbcResp.getBussFlowNo();

						bankRtnCode = cmbcResp.getRespCode();
						bankRtnMsg = cmbcResp.getRespMsg();
							
							/* 扣款成功 */
							if(Consts.REMOTE_RESULT_01.equals(cmbcResp.getReturnCode())){
								
								status = Consts.FINAL_NUMBER_1;
								resultCode = ResultCodes.NORMAL;
								
								proxyStatus = Consts.PROXY_STATUS_SUCC;
								
								/* 代扣成功 充值到用户账户 */
								deductTranAmount(customer.getUserName(),moneyReq);
							}else{
								
								/* 处理中 */
								if(Consts.REMOTE_RESULT_00.equals(cmbcResp.getReturnCode())){
									status = Consts.FINAL_NUMBER_3;
									resultCode = ResultCodes.PROXYDEDUCTMONEY_DOING;
									
									proxyStatus = Consts.PROXY_STATUS_WAIT;
								}else if (Consts.REMOTE_RESULT_04.equals(cmbcResp.getReturnCode())){
									/* 代扣余额不足 */
									status = Consts.FINAL_NUMBER_4;
									resultCode = ResultCodes.PROXYDEDUCTMONEY_BANLANCE_LACK;
									proxyStatus = Consts.PROXY_STATUS_YEBZ;
								}else if(Consts.REMOTE_RESULT_404.equals(cmbcResp.getReturnCode())){
									/* 连接超时 */
									resultCode = ResultCodes.PROXYDEDUCTMONEY_REMOTE_OUTTIME;
									status = Consts.FINAL_NUMBER_6;
								} else {
									/* 代扣错误，失败 */
									status = Consts.FINAL_NUMBER_5;
									resultCode = ResultCodes.PROXYDEDUCTMONEY_BANKCARD_FAIL3;
									proxyStatus = Consts.PROXY_STATUS_FAIL;
								}
								
							}
							
							validateMsg = cmbcResp.getErrorMsg();
							
							 /* 记录嗨秒贷代扣验证状态 */
//							recordHmdProxyResult(moneyReq, customer);
							updateHmdProxyResult(moneyReq, customer);
							
							if(!Consts.REMOTE_RESULT_404.equals(cmbcResp.getReturnCode())){
								/* 记录代扣状态 */
								recordHmdProxyStatus(moneyReq, customer, proxyStatus);
							}
						
							ConnManager.commit();
						
					}else{
						resultCode = ResultCodes.PROXYDEDUCTMONEY_INIT_EXCEPTION;
					}
					
				}else{
					resultCode = ResultCodes.PROXYDEDUCTMONEY_HKAPP_NOTFOUND;
				}
				
			}else{
				resultCode = ResultCodes.PROXYDEDUCTMONEY_USER_NOTFOUND;
			}
			
		} catch(QueryFlowNoException e){
			resultCode = ResultCodes.PROXYDEDUCTMONEY_FLOWNO_EXCEPTION;
		} catch (TranXSCZException e){
			resultCode = ResultCodes.PROXYDEDUCTMONEY_PROXY_EXCEPTION;
			ConnManager.rollback();
		} catch (Exception e) {
			resultCode = ResultCodes.PROXYDEDUCTMONEY_EXCETPION;
			ConnManager.rollback();
			e.printStackTrace();
			
		} finally {
			ConnManager.closeConn();
		}
		
		moneyResp.setResultCode(resultCode);
		return moneyResp;
	}
	
	/**
	 * 用户是否存在还款中或者交易完成的订单
	 * @param userName
	 * @return
	 */
	private boolean isExistHkApp(String userName){
		
		ApplicationQuery appQuery = new ApplicationQuery();
		
		List<ApplicationEntity> list = appQuery.queryHkApp(userName);
		
		if(list != null && list.size() > 0){
			
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 记录嗨秒贷代扣状态
	 * @param proxyReq
	 * @param customer
	 * @param status
	 */
	private void recordHmdProxyStatus(DeductMoneyReq proxyReq,CustomerEntity customer,String status){
		
		Map<String , Object> inputMap = new HashMap<String, Object>();
		inputMap.put("USERNAME", customer.getUserName());
		inputMap.put("REAL_NAME", customer.getRealName());
		inputMap.put("IDENTITY_NO", customer.getIdentityNo());
		inputMap.put("BANK_CARD", proxyReq.getBankCard());
		
		inputMap.put("BANK_NAME", bankName);
		inputMap.put("BUSS_FLOWNO", bussFlowNo);
		inputMap.put("PROXY_STATUS", status);
		inputMap.put("PROXY_AMOUNT", proxyReq.getAmount());
		inputMap.put("CREATE_DATE", DateUtils.getDateStr(new Date()));
		
		HmdProxyRecordEvent recordEvent = new HmdProxyRecordEvent();
		recordEvent.recordHmdProxyRecord(inputMap);
	}
	
	/**
	 * 记录嗨秒贷代扣状态
	 * @param proxyReq
	 * @param customer
	 */
//	private void recordHmdProxyResult(DeductMoneyReq proxyReq,CustomerEntity customer){
//		
//		Map<String , Object> inputMap = new HashMap<String, Object>();
//		inputMap.put("USERNAME", customer.getUserName());
//		inputMap.put("REAL_NAME", customer.getRealName());
//		inputMap.put("IDENTITY_NO", customer.getIdentityNo());
//		inputMap.put("BANK_CARD", proxyReq.getBankCard());
//		
//		inputMap.put("BANK_NAME", bankName);
//		inputMap.put("STATUS", status);
//		inputMap.put("VALIDATE_MSG",validateMsg);
//		inputMap.put("BUSS_FLOWNO", bussFlowNo);
//		
//		inputMap.put("BANK_RTN_CODE",bankRtnCode);
//		inputMap.put("BANK_RTN_MSG", bankRtnMsg);
//		
//		inputMap.put("CREATE_DATE", DateUtils.getDateStr(new Date()));
//		
//		HmdProxyValEvent hmdValEvent = new HmdProxyValEvent();
//		hmdValEvent.recordHmdProxyVal(inputMap);
//	}
	
	/**
	 * 记录嗨秒贷代扣状态
	 * @param proxyReq
	 * @param customer
	 */
	private void updateHmdProxyResult(DeductMoneyReq proxyReq,CustomerEntity customer){
		
		Map<String , Object> inputMap = new HashMap<String, Object>();
//		inputMap.put("USERNAME", customer.getUserName());
//		inputMap.put("REAL_NAME", customer.getRealName());
//		inputMap.put("IDENTITY_NO", customer.getIdentityNo());
//		inputMap.put("BANK_CARD", proxyReq.getBankCard());
		
//		inputMap.put("BANK_NAME", bankName);
		inputMap.put("STATUS", status);
		inputMap.put("VALIDATE_MSG",validateMsg);
//		inputMap.put("BUSS_FLOWNO", bussFlowNo);
		
		inputMap.put("BANK_RTN_CODE",bankRtnCode);
		inputMap.put("BANK_RTN_MSG", bankRtnMsg);
		
		Map<String , Object> whereMap = new HashMap<String, Object>();
		whereMap.put("BUSS_FLOWNO", bussFlowNo);
		
		HmdProxyValEvent hmdValEvent = new HmdProxyValEvent();
		hmdValEvent.updateHmdProxyVal(inputMap, whereMap);
	}
	
	/**
	 * 获取代扣银行名称
	 * @param bankCode
	 * @return
	 */
	private String queryBankName(String bankCode){
		
		BankQuery bankQuery = new BankQuery();
		BankEntity entity = bankQuery.queryProxyBankByCode(bankCode);
		if(entity != null){
			return entity.getBankName();
		}
		return "";
	}
	
	/**
	 * 充值
	 * @param userName
	 * @throws TranXSCZException 
	 */
	private void deductTranAmount(String userName,DeductMoneyReq moneyReq) throws TranXSCZException{
		
		AccountQuery accountQuery = new AccountQuery();
		List<AccountEntity> accountList = accountQuery.queryAccount(userName);
		
		if(accountList!=null && accountList.size()>0){
			String accountId = accountList.get(0).getAccountId();
			TransactionEvent event = new TransactionEvent();
			event.deductMoneyTran(accountId,moneyReq.getAmount());
		}else{
			
			throw new TranXSCZException(ExceptionMsg.PROXY_DEDUCTMONEY_EXCEPTION);
		}
		
	}
	
	/**
	 * 代扣扣款
	 * @param proxyReq
	 * @param customer
	 * @return
	 * @throws HttpException
	 * @throws IOException
	 * @throws HttpReturnException
	 * @throws HttpUrlRemoteException
	 */
	private CmbcGFAResEntity remoteHttpProxyDeduct(DeductMoneyReq proxyReq,CustomerEntity customer,String bankName) throws HttpException, IOException, HttpReturnException, HttpUrlRemoteException{
		
//		String merchantNo = "CF3000034853";//测试商户号
//		String merchantNo = "CF2000027924";//生产商户号CF2000027924 20150728 195222 963414
//		String merchantNo = ResourceUtils.getValue("merchantNo");
		
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//		String timestamp = sdf.format(new Date());
//		String randomStr = RandomStringUtils.random(6,false,true);
		
		Map<String, String> parmMap = new HashMap<String, String>();
		/* 业务参数 */
		parmMap.put("accountNo", proxyReq.getBankCard());// 银行帐号
		parmMap.put("accountName", customer.getRealName());// 账户名称
		parmMap.put("tranAmt", proxyReq.getAmount());// 代扣金额
		parmMap.put("certNo", customer.getIdentityNo());// 用户身份证号码
		parmMap.put("bankName", bankName);// 代扣银行名称
		parmMap.put("bussFlowNo", bussFlowNo);//交易流水号
		
		ServiceConfigQuery configQuery = new ServiceConfigQuery();
		ServiceConfigEntity serviceConfigEntity = configQuery.queryServiceByCode(Consts.SERVICE_BANKCARD_PROXYDEDUCT);

//		String httpResp = HttpRemotePost.sendHttp(serviceConfigEntity.getServiceUrl(), parmMap);
		
		String httpResp = "";
		
		try {
		
			httpResp = HttpRemotePost.sendHttp(serviceConfigEntity.getServiceUrl(), parmMap);
			
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
		} catch (SocketTimeoutException e){
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
	 * 代扣，初始加载代扣记录
	 * @param proxyReq
	 * @param customer
	 */
	private int initProxyVal(DeductMoneyReq proxyReq,CustomerEntity customer){
		
		Map<String , Object> inputMap = new HashMap<String, Object>();
		inputMap.put("USERNAME", customer.getUserName());
		inputMap.put("REAL_NAME", customer.getRealName());
		inputMap.put("IDENTITY_NO", customer.getIdentityNo());
		inputMap.put("BANK_CARD", proxyReq.getBankCard());
		
		inputMap.put("BANK_NAME", bankName);
		inputMap.put("STATUS", Consts.FINAL_NUMBER_0);
		inputMap.put("VALIDATE_MSG",Consts.PROXY_STR2);
		inputMap.put("BUSS_FLOWNO", bussFlowNo);
		inputMap.put("PROXY_DESC", Consts.PROXY_STR1.replace("{1}", customer.getUserName()).replace("{2}", DateUtils.getDateStr(new Date())).replace("{3}", proxyReq.getAmount()));
		
//		inputMap.put("BANK_RTN_CODE",bankRtnCode);
//		inputMap.put("BANK_RTN_MSG", bankRtnMsg);
		
		inputMap.put("CREATE_DATE", DateUtils.getDateStr(new Date()));
		
		HmdProxyValEvent hmdValEvent = new HmdProxyValEvent();
		return hmdValEvent.initProxyVal(inputMap);
	}
	
	/**
	 * 生成交易流水
	 * @return
	 * @throws QueryFlowNoException 
	 */
	private String getFlowNo() throws QueryFlowNoException{
		
		PayFlowNoQuery flowQuery = new PayFlowNoQuery();
		String flowStr = flowQuery.queryFlowNo(Consts.PROXY_DEDUCT_SEQ);
		
		String merchantNo = ResourceUtils.getValue("merchantNo");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String timestamp = sdf.format(new Date());
//		String randomStr = RandomStringUtils.random(6,false,true);
		
//		return merchantNo + timestamp + randomStr;
		return merchantNo + timestamp + StringUtils.lampLeft(flowStr, "0", 6);
	}
	
	/**
	 * 获取用户资料
	 * @param userName
	 * @return
	 */
	public CustomerEntity queryCustomer(String userName){
		
		CustomerQuery query = new CustomerQuery();
		return query.queryCustByUserName(userName);
	}
	
}
