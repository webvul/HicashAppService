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
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.event.user.CmbcIdentifyEvent;
import com.hengyuan.hicash.domain.query.param.BankQuery;
import com.hengyuan.hicash.domain.query.user.CmbcIdentifyConfirmQuery;
import com.hengyuan.hicash.domain.query.user.CustomerQuery;
import com.hengyuan.hicash.domain.query.user.HmdProxyValQuery;
import com.hengyuan.hicash.domain.query.user.ServiceConfigQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.param.BankEntity;
import com.hengyuan.hicash.entity.user.CmbcIdentifyConfirmResEntity;
import com.hengyuan.hicash.entity.user.CmbcIdentifySendCodeEntity;
import com.hengyuan.hicash.entity.user.CustomerEntity;
import com.hengyuan.hicash.entity.user.HmdProxyValEntity;
import com.hengyuan.hicash.entity.user.ServiceConfigEntity;
import com.hengyuan.hicash.exception.CmbcIdentifyException;
import com.hengyuan.hicash.exception.DInputProxyBankException;
import com.hengyuan.hicash.exception.HttpReturnException;
import com.hengyuan.hicash.exception.HttpUrlRemoteException;
import com.hengyuan.hicash.exception.UpdateHmdProxyValException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.CmbcIdentifyConfirmReq;
import com.hengyuan.hicash.parameters.request.user.DoubleIdentityValReq;
import com.hengyuan.hicash.parameters.request.user.ProxyDeductMoneyRechargeReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.CmbcIdentifyConfirmResp;
import com.hengyuan.hicash.parameters.response.user.DoubleIdentityValResp;
import com.hengyuan.hicash.utils.DateUtils;
import com.hengyuan.hicash.utils.HttpRemotePost;
import com.hengyuan.hicash.utils.ResourceUtils;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 民生银行代扣业务身份认证-用于验证交易用户验证交易码。CP0030
 * 
 * @author leaf.Ren
 * @create date 2015-12-02 update 2016-08-02
 */
public class CmbcIdentifyConfirmService implements RespService {

	private static Logger logger = Logger
			.getLogger(CmbcIdentifyConfirmService.class);

	private String resultCode = "";

	private String status = "";
	private String validateMsg = "";
	private String validateCode = "";
	private String bussFlowNoConfirm = "";// 交易流水号
	private String queryBussFlowNo = "";
	private String bankRtnCode = ""; // 银行返回结果代码

	private String bankRtnMsg = ""; // 银行返回结果消息

	// //鉴权交易流水号
//	 private String orgBussFlowNo = "";

	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		CmbcIdentifyConfirmReq confirmReq = (CmbcIdentifyConfirmReq) parmRequest;
		// 请求中投
		CmbcIdentifyConfirmResp confirmResp = sendZT(confirmReq);

		try {

			// 如果中投验证验证码失败或者认证成功,调用诺亚
			if (status.equals(Consts.IDENTIFY_STATUS_FAIL)
					|| status.equals(Consts.IDENTIFY_STATUS_WAIT)||status.equals(Consts.IDENTIFY_STATUS_FAIL)) {// 中投认证失败,认证处理中,重新发起认证

				// 调用诺亚接口
				DoubleIdentityValResp doubleResp = sendNoah(confirmReq);

				logger.info("中投验证失败:调用诺亚结果===" + new Gson().toJson(doubleResp));

				// 如果诺亚也认证失败,那么终止操作
				if (doubleResp.getResultCode().equals(
						ResultCodes.NOAH_VALID_FAIL)) {
					  //都失败，更改标志为都不支持
					   logger.info("用户："+confirmReq.getUserName()+"卡号"+confirmReq.getAccountNo()+"诺亚中投都认证失败，更新代扣标志为：ALLNO");
					   
					   DoubleIdentityValService doubleService=new DoubleIdentityValService();
					   
					   if(!StringUtils.isEmpty(confirmReq.getAppNo())){
						   
					   doubleService.updateInput(confirmReq.getAccountNo(), confirmReq.getBankCode(), confirmReq.getAppNo(), Consts.ALLNO);
					  
					   }
					confirmResp.setValStatus(Consts.IDENTIFY_STATUS_FAIL);// 认证都失败
					confirmResp.setRespMsg(doubleResp.getResultMsg());//都认证失败：提示信息提示诺亚的
				} else {// 中投失败,诺亚成功
					logger.info("中投失败诺亚成功");
					confirmResp.setResultCode(ResultCodes.NORMAL);
					confirmResp.setValStatus(Consts.IDENTIFY_STATUS_SUCC);// 认证成功
					confirmResp.setRespMsg("验证成功");
				}

			} else if (status.equals(Consts.IDENTIFY_STATUS_SUCC)) {// 中投认证成功

				
				// 调用诺亚接口
				DoubleIdentityValResp doubleResp = sendNoah(confirmReq);
				

				logger.info("中投验证成功:调用诺亚结果===" + new Gson().toJson(doubleResp));

				if (doubleResp.getResultCode().equals(
						ResultCodes.NOAH_VALID_FAIL)) {// 中投认证成功,诺亚失败
					confirmResp.setValStatus(Consts.IDENTIFY_STATUS_SUCC);// 认证成功
					confirmResp.setRespMsg("验证成功");
					logger.info("中投成功，诺亚失败");
				}else if(doubleResp.getResultCode().equals(ResultCodes.NORMAL)){//中投诺亚都认证成功
					  //都成功，更改标志为都支持
					   logger.info("用户："+confirmReq.getUserName()+"卡号"+confirmReq.getAccountNo()+"诺亚中投都认证成功，更新代扣标志为：ALLDK");
					   
					   DoubleIdentityValService doubleService=new DoubleIdentityValService();
					   //新版嗨钱网没有流水号
					   if(!StringUtils.isEmpty(confirmReq.getAppNo())){
					   doubleService.updateInput(confirmReq.getAccountNo(), confirmReq.getBankCode(), confirmReq.getAppNo(), Consts.ALLDK);
					   }
					   confirmResp.setValStatus(Consts.IDENTIFY_STATUS_SUCC);// 认证成功
					  
						 
				}
				 confirmResp.setResultCode(ResultCodes.NORMAL);
				confirmResp.setRespMsg("验证成功");	
			}

		}catch (DInputProxyBankException e) {
			logger.info("更新订单异常");
			logger.error("更新订单异常:", e);
			resultCode = ResultCodes.VALID_BANK_AAAA_NOTNULL;
			confirmResp.setValStatus(Consts.IDENTIFY_STATUS_FAIL);	
		}
		catch (Exception e) {
			logger.info("进入了没啊啊啊啊啊啊啊啊：");
			logger.error("验证验证码异常:", e);
			resultCode = ResultCodes.CMBC_IDENTIFY_CONFIRM_EXCEPTION;
			confirmResp.setValStatus(Consts.IDENTIFY_STATUS_FAIL);
		}
		confirmResp.setResultCode(resultCode);
		
		logger.info("验证验证码返回前端数据:"+new Gson().toJson(confirmResp));
		return confirmResp;
	}

	// 验证验证码
	public CmbcIdentifyConfirmResp sendZT(CmbcIdentifyConfirmReq confirmReq) {

		CmbcIdentifyConfirmResp confirmResp = new CmbcIdentifyConfirmResp();

		try {
             
			/* 获取用户资料 */
			CustomerEntity customer = queryCustomer(confirmReq.getUserName());
			if (customer != null) {


				/* 如果之前验证成功 */
				if (queryConfirmSucc(confirmReq)) {
					// 中投认证成功:更新订单表代扣标志位,修改客户收款银行卡,增加代扣验证记录
					DoubleIdentityValResp resp=	operaZt(confirmReq);
					if(resp.getResultCode().equals(ResultCodes.NORMAL)){
					status = Consts.IDENTIFY_STATUS_SUCC;
					resultCode = ResultCodes.NORMAL;
					}else{
						status = Consts.IDENTIFY_STATUS_FAIL;
						resultCode = ResultCodes.NORMAL;	
					}
					bankRtnMsg=resp.getResultMsg();

				} else if (queryConfirmWait(confirmReq)) {
					status = Consts.IDENTIFY_STATUS_WAIT;
					resultCode = ResultCodes.NORMAL;
				} else {

					// 查询短信信息
					CmbcIdentifySendCodeEntity identifyCodeEntity = querySendCodeEntity(confirmReq);

					if (identifyCodeEntity != null) {

						/* 验证验证码信息 */
						if (identifyValidate(confirmReq, identifyCodeEntity)) {
							logger.info("开始调用中投的验证验证码地址了老了");
							CmbcIdentifyConfirmResEntity cmbcResp = remoteHttpProxyDeduct(confirmReq);

							logger.info("\n#####【民生银行实名认证验证码结果："
									+ cmbcResp.getReturnCode() + "，"
									+ cmbcResp.getErrorMsg() + "，流水号："
									+ cmbcResp.getBussFlowNo() + "，银行返回信息："
									+ cmbcResp.getRespMsg() + ",银行返回代码："
									+ cmbcResp.getRespCode() + "】#####");
							bussFlowNoConfirm = cmbcResp.getBussFlowNo();

							bankRtnCode = cmbcResp.getRespCode();
							bankRtnMsg = cmbcResp.getRespMsg();

							/* 验证成功 */
							if (Consts.REMOTE_RESULT_01.equals(cmbcResp
									.getReturnCode())) {
								logger.info("验证验证码正常返回：开始操作");
								// 中投认证成功:更新订单表代扣标志位,修改客户收款银行卡,增加代扣验证记录
								DoubleIdentityValResp resp=	operaZt(confirmReq);
								
								if(resp.getResultCode().equals(ResultCodes.NORMAL)){
									
								status = Consts.IDENTIFY_STATUS_SUCC;
								resultCode = ResultCodes.NORMAL;
								
								}else{
									status = Consts.IDENTIFY_STATUS_FAIL;
									resultCode = ResultCodes.NORMAL;	
								}

							} else {

								/* 处理中 */
								if (Consts.REMOTE_RESULT_00.equals(cmbcResp
										.getReturnCode())) {
									status = Consts.IDENTIFY_STATUS_WAIT;
									resultCode = ResultCodes.NORMAL;
								} else if (Consts.REMOTE_RESULT_404
										.equals(cmbcResp.getReturnCode())) {
									/* 连接超时 */
									resultCode = ResultCodes.NORMAL;
									status = Consts.IDENTIFY_STATUS_FAIL;
								} else {
									/* 认证失败 */
									status = Consts.IDENTIFY_STATUS_FAIL;
									resultCode = ResultCodes.NORMAL;
								}

							}

							validateMsg = cmbcResp.getErrorMsg();
							validateCode = cmbcResp.getReturnCode();
							/* 记录实名认证验证状态 */
							logger.info(confirmReq.getAccountName()+"开始更新验证码：");
							
						}else{
							logger.info(confirmReq.getAccountName()+"验证验证码失败");
							status = Consts.IDENTIFY_STATUS_FAIL;
							resultCode = ResultCodes.NORMAL;
						}
						
					} else {
						status = Consts.IDENTIFY_STATUS_FAIL;
						resultCode =ResultCodes.NORMAL;
					}

				}

			

			} else {
				status = Consts.IDENTIFY_STATUS_FAIL;
				resultCode = ResultCodes.PROXYDEDUCTMONEY_USER_NOTFOUND;

			}
			
			updateIdentifyResult(confirmReq);
			
		} catch (CmbcIdentifyException e) {
			logger.error("验证验证码异常:",e);
			status = Consts.IDENTIFY_STATUS_FAIL;
			resultCode = ResultCodes.CMBC_IDENTIFY_CONFIRM_STATUS_EXCEPTION;

		} catch (Exception e) {
			logger.error("验证验证码异常:",e);
			status = Consts.IDENTIFY_STATUS_FAIL;
			resultCode = ResultCodes.CMBC_IDENTIFY_CONFIRM_EXCEPTION;

		}

		confirmResp.setRespCode(bankRtnCode);
		confirmResp.setRespMsg(bankRtnMsg);
		confirmResp.setValStatus(status);
		confirmResp.setErrorMsg(validateMsg);
		confirmResp.setResultCode(resultCode);
		confirmResp.setBussFlowNo(bussFlowNoConfirm);
		logger.info(confirmReq.getAccountName()+"验证验证码返回记录:" + new Gson().toJson(confirmResp));
		return confirmResp;
	}

	/**
	 * 更新订单表代扣标志位,修改客户收款银行卡,增加代扣验证记录
	 * 
	 * @param sendCodeReq
	 * @return
	 */
	public DoubleIdentityValResp operaZt(CmbcIdentifyConfirmReq confirmReq) {

		DoubleIdentityValReq doubleValReq = new DoubleIdentityValReq();

		DoubleIdentityValService doubleService = new DoubleIdentityValService();
		doubleValReq.setAccountName(confirmReq.getAccountName());
		doubleValReq.setAccountNo(confirmReq.getAccountNo());
		doubleValReq.setAppNo(confirmReq.getAppNo());
		doubleValReq.setBankCode(confirmReq.getBankCode());
		doubleValReq.setCertNo(confirmReq.getCertNo());
		doubleValReq.setMobileNo(confirmReq.getMobileNo());
		doubleValReq.setUserName(confirmReq.getUserName());
		doubleValReq.setWhichPart(Consts.ZTDK);
		doubleValReq.setDkProvince(confirmReq.getDkProvince());
		doubleValReq.setDkCity(confirmReq.getDkCity());
		doubleValReq.setDkAreaCode(confirmReq.getDkAreaCode());
		doubleValReq.setDkBankBranch(confirmReq.getDkBankBranch());
	
		return doubleService.updateDInputApp(doubleValReq);

	}

	/**
	 * 调用诺亚接口
	 * 
	 * @param sendCodeReq
	 * @return
	 */
	public DoubleIdentityValResp sendNoah(CmbcIdentifyConfirmReq confirmReq) {

		DoubleIdentityValReq doubleValReq = new DoubleIdentityValReq();

		DoubleIdentityValService doubleService = new DoubleIdentityValService();
		doubleValReq.setAccountName(confirmReq.getAccountName());
		doubleValReq.setAccountNo(confirmReq.getAccountNo());
		doubleValReq.setAppNo(confirmReq.getAppNo());
		doubleValReq.setBankCode(confirmReq.getBankCode());
		doubleValReq.setCertNo(confirmReq.getCertNo());
		doubleValReq.setMobileNo(confirmReq.getMobileNo());
		doubleValReq.setUserName(confirmReq.getUserName());
		doubleValReq.setWhichPart(Consts.NYDK);
		doubleValReq.setDkProvince(confirmReq.getDkProvince());
		doubleValReq.setDkCity(confirmReq.getDkCity());
		doubleValReq.setDkAreaCode(confirmReq.getDkAreaCode());
		doubleValReq.setDkBankBranch(confirmReq.getDkBankBranch());
		return doubleService.noahValidResult(doubleValReq);
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
	private CmbcIdentifyConfirmResEntity remoteHttpProxyDeduct(
			CmbcIdentifyConfirmReq confirmReq) throws HttpException,
			IOException, HttpReturnException, HttpUrlRemoteException {

		// String merchantNo = "CF3000034853";//测试商户号
		// String merchantNo = "CF2000027924";//生产商户号CF2000027924 20150728
		// 195222 963414
		String merchantNo = ResourceUtils.getValue("merchantNo");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String timestamp = sdf.format(new Date());
		String randomStr = RandomStringUtils.random(6, false, true);

		Map<String, String> parmMap = new HashMap<String, String>();
		/* 业务参数 */
		parmMap.put("accountNo", confirmReq.getAccountNo());// 银行帐号
		parmMap.put("accountName", confirmReq.getAccountName());// 账户名称
		parmMap.put("certNo", confirmReq.getCertNo());// 用户身份证号码
		parmMap.put("bussFlowNo", merchantNo + timestamp + randomStr);// 交易流水号
		parmMap.put("mobileNo", confirmReq.getMobileNo());
		parmMap.put("phoneToken", confirmReq.getPhoneToken());
		parmMap.put("phoneVerCode", confirmReq.getPhoneVerCode());
		String  bankName = queryBankName(confirmReq.getBankCode());
		 parmMap.put("bankName", bankName);
		ServiceConfigQuery configQuery = new ServiceConfigQuery();

		ServiceConfigEntity serviceConfigEntity = configQuery
				.queryServiceByCode(Consts.CMBC_IDENTIFY_SENDCODE_VAL);

		// String httpResp =
		// HttpRemotePost.sendHttp(serviceConfigEntity.getServiceUrl(),
		// parmMap);

		String httpResp = "";

		try {

			httpResp = HttpRemotePost.sendHttp(
					serviceConfigEntity.getServiceUrl(), parmMap);

		} catch (ConnectTimeoutException e) {
			logger.info("\n[民生实名认证验证码]：异常 => 远程连接超时。。");
			e.printStackTrace();
			CmbcIdentifyConfirmResEntity entity = new CmbcIdentifyConfirmResEntity();
			entity.setReturnCode("404");
			entity.setErrorMsg("民生实名认证验证码远程读取数据超时");
			entity.setBussFlowNo("");
			entity.setRespCode("");
			entity.setRespMsg("");
			return entity;
		} catch (SocketTimeoutException e) {
			logger.info("\n[民生实名认证验证码]：异常 => 远程读取数据超时。。");
			e.printStackTrace();
			CmbcIdentifyConfirmResEntity entity = new CmbcIdentifyConfirmResEntity();
			entity.setReturnCode("404");
			entity.setErrorMsg("民生实名认证验证码远程读取数据超时");
			entity.setBussFlowNo("");
			entity.setRespCode("");
			entity.setRespMsg("");

			return entity;
		}

		return new Gson()
				.fromJson(httpResp, CmbcIdentifyConfirmResEntity.class);
	}

	// /**
	// * 远程调用 人行征信 验证 姓名，身份证号码，银行卡号是否匹配
	// *
	// * @param proxyReq
	// * 请求参数
	// * @param customer
	// * 客户信息
	// * @return
	// * @throws HttpException
	// * @throws IOException
	// * @throws HttpReturnException
	// * @throws HttpUrlRemoteException
	// */
	// private CmbcCardRealCheckResp remoteHttpAmt(
	// ProxyDeductMoneyRechargeReq proxyReq, CustomerEntity customer)
	// throws HttpException, IOException, HttpReturnException,
	// HttpUrlRemoteException {
	//
	// SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	// String timestamp = sdf.format(new Date());
	// String merchantNo = "1134";
	// String randomStr = RandomStringUtils.random(5, false, true);
	//
	// /* 用户银行账户信息验证 */
	// Map<String, String> parmMap = new HashMap<String, String>();
	//
	// /* 固定参数 */
	// parmMap.put("version", "01");// 版本号
	// parmMap.put("msgType", "0001");// 报文类型
	// parmMap.put("chanId", "99");// 渠道代码
	// parmMap.put("merchantNo", merchantNo);// 商户号
	// parmMap.put("clientDate", timestamp);// 客户端系统日期
	// parmMap.put("serverDate", timestamp);// 服务端日期
	// parmMap.put("tranFlow", merchantNo + timestamp + randomStr);// 交易流水号
	// parmMap.put("tranCode", "SC0001");// 请求交易码
	// parmMap.put("respCode", "");// 返回码
	// parmMap.put("respMsg", "");// 信息描述
	// parmMap.put("bankCode", "");// 银行编码
	// parmMap.put("terminalType", "01");// 验证接口的终端类型 01:APP
	// parmMap.put("bankCarkType", "A");// 银行卡类型D 借记卡C 贷A 全部(默认)
	// /* 业务参数 */
	// parmMap.put("bankCardNo", proxyReq.getBankCard());// 银行卡号
	// // 6226620608276
	// parmMap.put("idName", customer.getRealName());// 姓名 任利华
	// parmMap.put("idNum", customer.getIdentityNo());// 身份证号
	// // 411422199111130
	//
	// ServiceConfigQuery configQuery = new ServiceConfigQuery();
	// ServiceConfigEntity serviceConfigEntity = configQuery
	// .queryServiceByCode(Consts.SERVICE_BANKCARD_VAL);
	//
	// String httpResp = HttpRemotePost.sendHttp(
	// serviceConfigEntity.getServiceUrl(), parmMap);
	//
	// return new Gson().fromJson(httpResp, CmbcCardRealCheckResp.class);
	// }
	//
	//
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
	 * 获取用户验证记录
	 * 
	 * @param realName
	 * @param bankCard
	 * @return
	 */
	public CmbcIdentifySendCodeEntity querySendCodeEntity(
			CmbcIdentifyConfirmReq confirmReq) {

		CmbcIdentifyConfirmQuery confirmQuery = new CmbcIdentifyConfirmQuery();
		CmbcIdentifySendCodeEntity entity = confirmQuery
				.querySendCodeSucc(confirmReq);

		return entity;
	}

	/**
	 * 获取用户验证记录
	 * 
	 * @param realName
	 * @param bankCard
	 * @return
	 */
	public boolean queryConfirmWait(CmbcIdentifyConfirmReq confirmReq) {

		CmbcIdentifyConfirmQuery confirmQuery = new CmbcIdentifyConfirmQuery();
		CmbcIdentifySendCodeEntity entity = confirmQuery
				.queryConfirmWait(confirmReq);

		if (entity != null) {

			return true;
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
	public boolean queryConfirmSucc(CmbcIdentifyConfirmReq confirmReq) {

		CmbcIdentifyConfirmQuery confirmQuery = new CmbcIdentifyConfirmQuery();
		CmbcIdentifySendCodeEntity entity = confirmQuery
				.queryConfirmSucc(confirmReq);

		if (entity != null) {

			return true;
		}

		return false;
	}

	/**
	 * 记录实名认证状态
	 * 
	 * @param proxyReq
	 * @param customer
	 * @throws UpdateHmdProxyValException
	 * @throws CmbcIdentifyException
	 */
	private void updateIdentifyResult(CmbcIdentifyConfirmReq confirmReq)
			throws CmbcIdentifyException {

		Map<String, Object> inputMap = new HashMap<String, Object>();

		inputMap.put("val_STATUS", status);
		inputMap.put("VALIDATE_MSG", validateMsg);
		inputMap.put("VALIDATE_code", validateCode);

		inputMap.put("BANK_RTN_CODE", bankRtnCode);
		inputMap.put("BANK_RTN_MSG", bankRtnMsg);
		inputMap.put("BUSS_FLOWNO_CONFIRM", bussFlowNoConfirm);
		inputMap.put("phone_vercode", confirmReq.getPhoneVerCode());
		inputMap.put("update_time", DateUtils.getDateStr(new Date()));

		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("buss_flowNo", confirmReq.getBussflowNo());
		logger.info(confirmReq.getAccountName()+"要更新验证码：");
		CmbcIdentifyEvent identifyEvent = new CmbcIdentifyEvent();
		identifyEvent.updateIdentify(inputMap, whereMap);
	}

	/**
	 * 验证码验证
	 * 
	 * @param registerReq
	 * @return
	 * @throws ParseException
	 */
	public boolean identifyValidate(CmbcIdentifyConfirmReq confirmCodeReq,
			CmbcIdentifySendCodeEntity identifyCode) throws ParseException {

		logger.info(confirmCodeReq.getAccountName()+"验证令牌和验证码开始");
//		/* 验证码 */
//		String phoneVarCode = identifyCode.getPhoneVerCode();
		/* 手机令牌 */
		String phoneToken = identifyCode.getPhoneToken();
     
       logger.info(confirmCodeReq.getAccountName()+"输入验证码："+confirmCodeReq.getPhoneVerCode());
	

			if (phoneToken != null
					&& phoneToken.trim().equals(
							confirmCodeReq.getPhoneToken().trim())) {

				return true;

			} else {
				logger.info(confirmCodeReq.getAccountName()+"验证令牌不正确");
				resultCode = ResultCodes.CMBC_IDENTIFY_CONFIRM_TOKEN_FALSE;
			}

		

		return false;

	}

	/**
	 * 实名认证查询接口
	 * 
	 * @param withholdingEntity
	 * @return
	 * @throws HttpException
	 * @throws IOException
	 * @throws HttpReturnException
	 * @throws HttpUrlRemoteException
	 * @throws UpdateHmdProxyValException
	 * @throws CmbcIdentifyException
	 */
	public CmbcIdentifyConfirmResEntity queryIdentify(
			CmbcIdentifySendCodeEntity confirmReq) throws HttpException,
			IOException, HttpReturnException, HttpUrlRemoteException,
			CmbcIdentifyException {
		String merchantNo = ResourceUtils.getValue("merchantNo");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String timestamp = sdf.format(new Date());
		String randomStr = RandomStringUtils.random(6, false, true);
		CmbcIdentifyConfirmReq confirmReq1 = new CmbcIdentifyConfirmReq();
		confirmReq1.setAccountName("");
		confirmReq1.setAccountNo("");
		confirmReq1.setCertNo("");
		confirmReq1.setMobileNo("");
		Map<String, String> parmMap = new HashMap<String, String>();
		/* 业务参数 */
		String sendQueryNo = merchantNo + timestamp + randomStr;
		parmMap.put("accountNo", confirmReq.getAccountNo());// 银行帐号
		parmMap.put("accountName", confirmReq.getAccountName());// 账户名称
		parmMap.put("certNo", confirmReq.getCertNo());// 用户身份证号码
		parmMap.put("bussFlowNo", sendQueryNo);// 交易流水号
		parmMap.put("mobileNo", confirmReq.getMobileNo());
		ServiceConfigQuery configQuery = new ServiceConfigQuery();

		ServiceConfigEntity serviceConfigEntity = configQuery
				.queryServiceByCode(Consts.CMBC_IDENTIFY_QUERY_VAL);

		String httpResp = HttpRemotePost.sendHttp(
				serviceConfigEntity.getServiceUrl(), parmMap);

		Gson json = new Gson();

		CmbcIdentifyConfirmResEntity resEntity = json.fromJson(httpResp,
				CmbcIdentifyConfirmResEntity.class);
		// JSONObject jsonObject = JSONObject.fromObject(httpResp);
		// Object pojo = JSONObject.toBean(jsonObject,
		// CmbcIdentifyConfirmResEntity.class);
		// CmbcIdentifyConfirmResEntity resEntity =
		// (CmbcIdentifyConfirmResEntity) pojo;
		queryBussFlowNo = resEntity.getBussFlowNo();
		logger.info("流水号：" + queryBussFlowNo + "实名认证返回结果，"
				+ resEntity.getReturnCode() + "流水号："
				+ resEntity.getBussFlowNo());
		if ("01".equals(resEntity.getReturnCode())) {
			status = Consts.IDENTIFY_STATUS_SUCC;

		} else if ("03".equals(resEntity.getReturnCode())) {
			status = Consts.IDENTIFY_STATUS_FAIL;
		} else if ("00".equals(resEntity.getReturnCode())) {
			status = Consts.IDENTIFY_STATUS_WAIT;
		}
		validateMsg = resEntity.getErrorMsg();
		validateCode = resEntity.getReturnCode();
		bankRtnCode = resEntity.getRespCode();
		bankRtnMsg = resEntity.getRespMsg();
		updateIdentifyQuery(confirmReq.getBussflowNo());
		return resEntity;

	}

	/**
	 * 记录实名认证状态
	 * 
	 * @param proxyReq
	 * @param customer
	 * @throws UpdateHmdProxyValException
	 * @throws CmbcIdentifyException
	 */
	private void updateIdentifyQuery(String bussFlowNo)
			throws CmbcIdentifyException {

		Map<String, Object> inputMap = new HashMap<String, Object>();

		inputMap.put("val_STATUS", status);
		inputMap.put("VALIDATE_MSG", validateMsg);
		inputMap.put("VALIDATE_code", validateCode);

		inputMap.put("BANK_RTN_CODE", bankRtnCode);
		inputMap.put("BANK_RTN_MSG", bankRtnMsg);
		inputMap.put("BUSS_FLOWNO_QUERY", queryBussFlowNo);

		inputMap.put("update_time", DateUtils.getDateStr(new Date()));

		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("buss_flowNo", bussFlowNo);

		CmbcIdentifyEvent identifyEvent = new CmbcIdentifyEvent();
		identifyEvent.updateIdentify(inputMap, whereMap);
	}
	
	public List<CmbcIdentifySendCodeEntity> queryWaitList() {

		CmbcIdentifyConfirmQuery confirmQuery = new CmbcIdentifyConfirmQuery();
		return confirmQuery.querySendCodeWait();
	}
}
