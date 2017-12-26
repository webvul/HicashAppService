package com.hengyuan.hicash.domain.service.user;

import java.io.IOException;
import java.net.SocketTimeoutException;
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
import com.hengyuan.hicash.domain.event.user.AccountRecordEvent;
import com.hengyuan.hicash.domain.event.user.HmdProxyRecordEvent;
import com.hengyuan.hicash.domain.event.user.HmdProxyValEvent;
import com.hengyuan.hicash.domain.event.user.InformationEvent;
import com.hengyuan.hicash.domain.query.param.BankQuery;
import com.hengyuan.hicash.domain.query.user.AccountQuery;
import com.hengyuan.hicash.domain.query.user.CustomerQuery;
import com.hengyuan.hicash.domain.query.user.HmdProxyRecordQuery;
import com.hengyuan.hicash.domain.query.user.HmdProxyValQuery;
import com.hengyuan.hicash.domain.query.user.ServiceConfigQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.param.BankEntity;
import com.hengyuan.hicash.entity.param.CmbcCardRealCheckResp;
import com.hengyuan.hicash.entity.param.CmbcGFAResEntity;
import com.hengyuan.hicash.entity.user.AccountEntity;
import com.hengyuan.hicash.entity.user.CustomerEntity;
import com.hengyuan.hicash.entity.user.HmdProxyRecordEntity;
import com.hengyuan.hicash.entity.user.HmdProxyValEntity;
import com.hengyuan.hicash.entity.user.ServiceConfigEntity;
import com.hengyuan.hicash.exception.HttpReturnException;
import com.hengyuan.hicash.exception.HttpUrlRemoteException;
import com.hengyuan.hicash.exception.TranXSCZException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.ProxyDeductMoneyReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.ProxyDeductMoneyResp;
import com.hengyuan.hicash.utils.DateUtils;
import com.hengyuan.hicash.utils.HttpRemotePost;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 嗨秒贷 service
 * 验证用户收款银行卡信息
 * 代理扣款
 * @author Cary.Liu
 * @createDate 2015-07-22
 *
 */
public class ProxyDeductMoneyService implements RespService {
	
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

		ProxyDeductMoneyReq proxyReq = (ProxyDeductMoneyReq)parmRequest;
		ProxyDeductMoneyResp proxyResp = new ProxyDeductMoneyResp();
		
		try {
			
			/* 获取用户资料 */
			CustomerEntity customer = queryCustomer(proxyReq.getUserName());
			if(customer != null){
				
				ConnManager.beginTransaction();
				
				bankName = queryBankName(proxyReq.getOpenBank());
				
				// 银行卡是否验证通过
//				boolean isSuccVal = false;
//				logger.info("\n#####【人行征信验证 > 姓名："+ customer.getRealName() +"，身份证："+ customer.getIdentityNo() +"，银行卡号："+ proxyReq.getBankCard() +"】#####");
				/* 获取用户银行卡验证记录 */
//				AccountValRecordEntity accountValEnity = queryAccountValRecord(proxyReq, customer);
//				HmdProxyValEntity accountValEnity = queryAccountValRecord(proxyReq, customer);
//				if(accountValEnity != null){
//					logger.info("\n#####【本地库 有 记录】#####");
//					
//					bankName = accountValEnity.getBankName();
//					validateMsg = accountValEnity.getValMsg();
//					/* 验证通过的记录  2 为人行征信验证未通过*/
//					if(!Consts.FINAL_NUMBER_2.equals(accountValEnity.getStatus())){
//						
//						isSuccVal = true;
//						
//					}else{
//						//未通过
//						resultCode = ResultCodes.PROXYDEDUCTMONEY_BANKCARD_FAIL1;
//					}
//					
//				}else{
//					logger.info("\n#####【本地库 无 记录，远程调用获取用户人行征信验证开始】#####");
//					 
//					/* 未获取到用户银行卡验证记录，调用接口验证 */
//					CmbcCardRealCheckResp remoteResp = remoteHttpAmt(proxyReq,customer);
//					logger.info("\n#####【验证结果：" + remoteResp.getReturnCode()+ "\t" + remoteResp.getErrorMsg() + "】#####");
//					/* 记录验证信息 */
//					isSuccVal = recordAccountValidate(proxyReq, customer,remoteResp);
//					bankName = remoteResp.getBankName();
//					validateMsg = remoteResp.getErrorMsg(); 
					
//				}
				
				/* 银行卡验证成功 */
//				if(isSuccVal){
					
//					System.out.println("#####【人行征信验证：通过】#####");
					
					 /* 如果之前调用过代扣接口 */
					 if(queryHmdProxy(customer.getRealName(), proxyReq.getBankCard())){
						 
						 resultCode = ResultCodes.NORMAL;
						 
					 }else{
						 
						 CmbcGFAResEntity cmbcResp = remoteHttpProxyDeduct(proxyReq, customer);
							
							logger.info("\n#####【代扣扣款结果："+ cmbcResp.getReturnCode() +"，"+cmbcResp.getErrorMsg() + "，流水号："+cmbcResp.getBussFlowNo()+"】#####");
							bussFlowNo = cmbcResp.getBussFlowNo();

							bankRtnCode = cmbcResp.getRespCode();
							bankRtnMsg = cmbcResp.getRespMsg();
							
							/* 扣款成功 */
							if(Consts.REMOTE_RESULT_01.equals(cmbcResp.getReturnCode())){
								
								status = Consts.FINAL_NUMBER_1;
								resultCode = ResultCodes.NORMAL;
								
								proxyStatus = Consts.PROXY_STATUS_SUCC;
								
								/* 代扣成功 充值到用户账户 */
								hmdTranAmount(customer.getUserName());
							}else{
								
								/* 处理中 */
								if(Consts.REMOTE_RESULT_00.equals(cmbcResp.getReturnCode())){
									status = Consts.FINAL_NUMBER_3;
									resultCode = ResultCodes.NORMAL;
									
									proxyStatus = Consts.PROXY_STATUS_WAIT;
								}else if (Consts.REMOTE_RESULT_04.equals(cmbcResp.getReturnCode())){
									/* 代扣余额不足 */
									status = Consts.FINAL_NUMBER_4;
									resultCode = ResultCodes.NORMAL;
									proxyStatus = Consts.PROXY_STATUS_YEBZ;
								}else if(Consts.REMOTE_RESULT_404.equals(cmbcResp.getReturnCode())){
									/* 连接超时 */
									resultCode = ResultCodes.NORMAL;
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
							recordHmdProxyResult(proxyReq, customer);
							
							if(!Consts.REMOTE_RESULT_404.equals(cmbcResp.getReturnCode())){
								/* 记录代扣状态 */
								recordHmdProxyStatus(proxyReq, customer, proxyStatus);
							}
							
					 }
					
//				}
//				else{
//					
//					System.out.println("#####【人行征信验证：未通过】#####");
//					// 2：人行征信验证未通过
//					status = Consts.FINAL_NUMBER_2;
//					
//					resultCode = ResultCodes.PROXYDEDUCTMONEY_BANKCARD_FAIL2;
//					
//					 /* 记录嗨秒贷代扣验证状态 */
//					recordHmdProxyResult(proxyReq, customer);
//				}
				
				ConnManager.commit();
				
			}else{
				resultCode = ResultCodes.PROXYDEDUCTMONEY_USER_NOTFOUND;
			}
			
		} catch (TranXSCZException e){
			resultCode = ResultCodes.PROXYDEDUCTMONEY_PROXY_EXCEPTION;
			ConnManager.rollback();
		} catch (Exception e) {
			resultCode = ResultCodes.PROXYDEDUCTMONEY_EXCETPION;
			e.printStackTrace();
			ConnManager.rollback();
		} finally {
			ConnManager.closeConn();
		}
		
		proxyResp.setResultCode(resultCode);
		return proxyResp;
	}
	
	/**
	 * 记录嗨秒贷代扣状态
	 * @param proxyReq
	 * @param customer
	 */
	private void recordHmdProxyResult(ProxyDeductMoneyReq proxyReq,CustomerEntity customer){
		
		Map<String , Object> inputMap = new HashMap<String, Object>();
		inputMap.put("USERNAME", customer.getUserName());
		inputMap.put("REAL_NAME", customer.getRealName());
		inputMap.put("IDENTITY_NO", customer.getIdentityNo());
		inputMap.put("BANK_CARD", proxyReq.getBankCard());
		
		inputMap.put("BANK_NAME", bankName);
		inputMap.put("STATUS", status);
		inputMap.put("VALIDATE_MSG",validateMsg);
		inputMap.put("BUSS_FLOWNO", bussFlowNo);
		
		inputMap.put("BANK_RTN_CODE",bankRtnCode);
		inputMap.put("BANK_RTN_MSG", bankRtnMsg);
		
		inputMap.put("CREATE_DATE", DateUtils.getDateStr(new Date()));
		
		HmdProxyValEvent hmdValEvent = new HmdProxyValEvent();
		hmdValEvent.recordHmdProxyVal(inputMap);
	}
	
	/**
	 * 记录嗨秒贷代扣状态
	 * @param proxyReq
	 * @param customer
	 * @param status
	 */
	private void recordHmdProxyStatus(ProxyDeductMoneyReq proxyReq,CustomerEntity customer,String status){
		
		Map<String , Object> inputMap = new HashMap<String, Object>();
		inputMap.put("USERNAME", customer.getUserName());
		inputMap.put("REAL_NAME", customer.getRealName());
		inputMap.put("IDENTITY_NO", customer.getIdentityNo());
		inputMap.put("BANK_CARD", proxyReq.getBankCard());
		
		inputMap.put("BANK_NAME", bankName);
		inputMap.put("BUSS_FLOWNO", bussFlowNo);
		inputMap.put("PROXY_STATUS", status);
		inputMap.put("PROXY_AMOUNT", Consts.FINAL_DECIMAL_1);
		inputMap.put("CREATE_DATE", DateUtils.getDateStr(new Date()));
		
		HmdProxyRecordEvent recordEvent = new HmdProxyRecordEvent();
		recordEvent.recordHmdProxyRecord(inputMap);
	}
	
	/**
	 * 获取代扣银行名称
	 * @param bankCode
	 * @return
	 */
	private String queryProxyBankName(String bankCode){
		
		BankQuery query = new BankQuery();
		BankEntity entity = query.queryProxyBankByCode(bankCode);
		if(entity != null){
			return entity.getBankName();
		}
		return "";
	}
	
	/**
	 * 查询用户银行卡是否有验证记录
	 * @param proxyReq
	 * @param customer
	 * @return
	 */
	public HmdProxyValEntity queryAccountValRecord(ProxyDeductMoneyReq proxyReq,CustomerEntity customer){
		
		HmdProxyValQuery query = new HmdProxyValQuery();
		return query.queryByNameAndCard(customer.getRealName(), proxyReq.getBankCard(), customer.getIdentityNo());
	}
	
//	/**
//	 * 查询用户银行卡是否有验证记录
//	 * @param infoReq
//	 * @param inputApp
//	 * @return
//	 */
//	public AccountValRecordEntity queryAccountValRecord(ProxyDeductMoneyReq proxyReq,CustomerEntity customer){
//		
//		AccountValRecordQuery query = new AccountValRecordQuery();
//		return query.queryRecord(proxyReq.getUserName(), customer.getIdentityNo(), proxyReq.getBankCard());
//	}
	
	/**
	 * 获取用户资料
	 * @param userName
	 * @return
	 */
	public CustomerEntity queryCustomer(String userName){
		
		CustomerQuery query = new CustomerQuery();
		return query.queryCustByUserName(userName);
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
	private CmbcGFAResEntity remoteHttpProxyDeduct(ProxyDeductMoneyReq proxyReq,CustomerEntity customer) throws HttpException, IOException, HttpReturnException, HttpUrlRemoteException{
		
//		String merchantNo = "CF3000034853";//测试商户号
//		String merchantNo = "CF2000027924";//生产商户号CF2000027924 20150728 195222 963414
		String merchantNo = ResourceUtils.getValue("merchantNo");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String timestamp = sdf.format(new Date());
		String randomStr = RandomStringUtils.random(6,false,true);
		
		Map<String, String> parmMap = new HashMap<String, String>();
		/* 业务参数 */
		parmMap.put("accountNo", proxyReq.getBankCard());// 银行帐号
		parmMap.put("accountName", customer.getRealName());// 账户名称
		parmMap.put("tranAmt", "1");// 代扣金额
		parmMap.put("certNo", customer.getIdentityNo());// 用户身份证号码
		parmMap.put("bankName", bankName);// 代扣银行名称
		parmMap.put("bussFlowNo", merchantNo + timestamp + randomStr);//交易流水号
		
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
	 * 远程调用 人行征信
	 * 验证 姓名，身份证号码，银行卡号是否匹配
	 * @param proxyReq 请求参数
	 * @param customer 客户信息
	 * @return
	 * @throws HttpException
	 * @throws IOException
	 * @throws HttpReturnException
	 * @throws HttpUrlRemoteException
	 */
	private CmbcCardRealCheckResp remoteHttpAmt(ProxyDeductMoneyReq proxyReq,CustomerEntity customer)throws HttpException, IOException, HttpReturnException, HttpUrlRemoteException {

		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String timestamp = sdf.format(new Date());
		String merchantNo = "1134";
		String randomStr = RandomStringUtils.random(5,false,true);
		
		/* 用户银行账户信息验证 */
		Map<String, String> parmMap = new HashMap<String, String>();
		
		/* 固定参数 */
		parmMap.put("version", "01");//版本号
		parmMap.put("msgType", "0001");//报文类型
		parmMap.put("chanId", "99");//渠道代码
		parmMap.put("merchantNo", merchantNo);//商户号
		parmMap.put("clientDate", timestamp);//客户端系统日期
		parmMap.put("serverDate", timestamp);//服务端日期
		parmMap.put("tranFlow", merchantNo + timestamp + randomStr);//交易流水号
		parmMap.put("tranCode", "SC0001");//请求交易码
		parmMap.put("respCode", "");//返回码
		parmMap.put("respMsg", "");//信息描述
		parmMap.put("bankCode", "");//银行编码
		parmMap.put("terminalType", "01");//验证接口的终端类型 01:APP
		parmMap.put("bankCarkType", "A");//银行卡类型D 借记卡C 贷A 全部(默认)
		/* 业务参数 */
		parmMap.put("bankCardNo", proxyReq.getBankCard());//银行卡号 6226620608276911
		parmMap.put("idName", customer.getRealName());//姓名 任利华
		parmMap.put("idNum", customer.getIdentityNo());//身份证号 411422199111130640
		
		ServiceConfigQuery configQuery = new ServiceConfigQuery();
		ServiceConfigEntity serviceConfigEntity = configQuery.queryServiceByCode(Consts.SERVICE_BANKCARD_VAL);

		String httpResp = HttpRemotePost.sendHttp(serviceConfigEntity.getServiceUrl(), parmMap);
		
		return new Gson().fromJson(httpResp, CmbcCardRealCheckResp.class);
	}
	
	/**
	 * 记录验证结果
	 * @param infoReq
	 * @param inputApp
	 * @param remoteResp
	 */
	private boolean recordAccountValidate(ProxyDeductMoneyReq proxyReq,CustomerEntity customer,CmbcCardRealCheckResp remoteResp){
		
		boolean isSucc = false;
		AccountRecordEvent acctEvent = new AccountRecordEvent();
		InformationEvent infoEvent = new InformationEvent();
		/* 远程调用返回0为成功，否则为不成功 */
		String remoteResult = Consts.FINAL_NUMBER_2;
		/* 保存数据库1为成功，2位不成功，0为未验证 */
		if(Consts.FINAL_NUMBER_0.equals(remoteResp.getReturnCode())){
			remoteResult = Consts.FINAL_NUMBER_1;
		}
		Map<String , Object> inputMap = new HashMap<String, Object>();
		inputMap.put("USERNAME", customer.getUserName());
		inputMap.put("REALNAME", customer.getRealName());
		inputMap.put("USER_IDCARD", customer.getIdentityNo());
		inputMap.put("OPEN_BANK", proxyReq.getOpenBank());
		inputMap.put("BANK_CARD", proxyReq.getBankCard());
		inputMap.put("VALIDATE_RESULT", remoteResult);
		inputMap.put("VALIDATE_MSG", remoteResp.getErrorMsg());
		inputMap.put("CREATE_DATE", DateUtils.getDateStr(new Date()));
		/* 记录验证结果 */
		acctEvent.recordAccountValidate(inputMap);
		/* 如果用户账户信息验证通过，则记录人行信息 */
		if(Consts.FINAL_NUMBER_1.equals(remoteResult)){
			
			Map<String , Object> inputInfoMap = new HashMap<String, Object>();
			inputInfoMap.put("IDENTITY_NO", customer.getIdentityNo());
			inputInfoMap.put("REAL_NAME", customer.getRealName());
			inputInfoMap.put("ver_Status", Consts.FINAL_NUMBER_1);
			infoEvent.recordValidateInfo(inputInfoMap);
			isSucc = true;
		}
		
		return isSucc;
	}
	
	/**
	 * 获取用户代扣记录
	 * @param realName
	 * @param bankCard
	 * @return
	 */
	public boolean queryHmdProxy(String realName,String bankCard){
		
		HmdProxyRecordQuery hmdProxyQuery = new HmdProxyRecordQuery();
		HmdProxyRecordEntity entity = hmdProxyQuery.queryProxySucc(realName, bankCard);
		
		if(entity != null){
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * 嗨秒贷代扣充值
	 * @param userName
	 * @throws TranXSCZException 
	 */
	private void hmdTranAmount(String userName) throws TranXSCZException{
		
		AccountQuery accountQuery = new AccountQuery();
		List<AccountEntity> accountList = accountQuery.queryAccount(userName);
		
		if(accountList!=null && accountList.size()>0){
			String accountId = accountList.get(0).getAccountId();
			TransactionEvent event = new TransactionEvent();
			event.hmdProxyTran(accountId);
		}else{
			
			throw new TranXSCZException(ExceptionMsg.PROXY_HMD_TRAN_EXCEPTION);
		}
		
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

}
