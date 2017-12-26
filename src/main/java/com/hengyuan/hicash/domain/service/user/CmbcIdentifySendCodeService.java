package com.hengyuan.hicash.domain.service.user;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
import com.hengyuan.hicash.domain.query.user.CmbcIdentifySendCodeQuery;
import com.hengyuan.hicash.domain.query.user.CustomerQuery;
import com.hengyuan.hicash.domain.query.user.ServiceConfigQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.param.BankEntity;
import com.hengyuan.hicash.entity.user.CmbcIdentifySendCodeEntity;
import com.hengyuan.hicash.entity.user.CmbcIdentifySendCodeResEntity;
import com.hengyuan.hicash.entity.user.CustomerEntity;
import com.hengyuan.hicash.entity.user.ServiceConfigEntity;
import com.hengyuan.hicash.exception.HttpReturnException;
import com.hengyuan.hicash.exception.HttpUrlRemoteException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.CmbcIdentifySendCodeReq;
import com.hengyuan.hicash.parameters.request.user.DoubleIdentityValReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.CmbcIdentifySendCodeResp;
import com.hengyuan.hicash.parameters.response.user.DoubleIdentityValResp;
import com.hengyuan.hicash.utils.DateUtils;
import com.hengyuan.hicash.utils.HttpRemotePost;
import com.hengyuan.hicash.utils.ResourceUtils;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 民生银行代扣业务身份认证-用于发送动态验证码到用户手机。CP0032 返回的returnCode，1是发送短信成功了
 * 
 * @author leaf.Ren
 * @create date 2015-12-01
 */
public class CmbcIdentifySendCodeService implements RespService {

	private static Logger logger = Logger
			.getLogger(CmbcIdentifySendCodeService.class);

	private String resultCode = "";

	// private String bankName = "";
	private String status = "";
	private String validateCode = "";// 自定义代码
	private String validateMsg = "";// 自定义信息
	private String bussFlowNo = "";// 交易流水号

	private String bankRtnCode = ""; // 银行返回结果代码
	private String bankRtnMsg = ""; // 银行返回结果消息


	private String phoneVerCode = ""; // 验证码
	private String phoneToken = ""; // 银行返回手机令牌

	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		CmbcIdentifySendCodeReq sendCodeReq = (CmbcIdentifySendCodeReq) parmRequest;
	     //调用中投接口
		CmbcIdentifySendCodeResp sendCodeResp=sendZt(sendCodeReq);
		 
	try{

		//如果中投发送验证码失败或者认证成功,则调用诺亚,如果中投发送短信成功,下一步调用诺亚
		if(status.equals(Consts.SEND_CODE_STATUS_FAIL)||status.equals(Consts.IDENTIFY_STATUS_WAIT)||status.equals(Consts.IDENTIFY_STATUS_FAIL)){//中投认证失败,认证处理中,重新发起认证
			 //调用诺亚接口
			DoubleIdentityValResp doubleResp=sendNoah(sendCodeReq);
			
		   logger.info(sendCodeReq.getAccountName()+"中投验证失败:调用诺亚结果==="+new Gson().toJson(doubleResp));	
		  
		   //如果诺亚也认证失败,那么终止操作
		   if(doubleResp.getResultCode().equals(ResultCodes.NOAH_VALID_FAIL)){
			   logger.info("中投诺亚都失败");
			   //都失败，更改标志为都不支持
			   logger.info("用户："+sendCodeReq.getUserName()+"卡号"+sendCodeReq.getAccountNo()+"诺亚中投都认证失败，更新代扣标志为：ALLNO");
			   
			   DoubleIdentityValService doubleService=new DoubleIdentityValService();
			   //新版嗨钱网没有订单号,不更新
			   if(!StringUtils.isEmpty(sendCodeReq.getAppNo())){
				   
			   doubleService.updateInput(sendCodeReq.getAccountNo(), sendCodeReq.getBankCode(), sendCodeReq.getAppNo(), Consts.ALLNO);
			
			   }
			  sendCodeResp.setValStatus(Consts.IDENTIFY_STATUS_FAIL);//认证都失败
			  sendCodeResp.setRespMsg(doubleResp.getResultMsg());//都认证失败：提示信息提示诺亚的
			  
		   }else{//中投失败,诺亚成功
			   logger.info(sendCodeReq.getAccountName()+"中投认证失败，诺亚成功：");
			   sendCodeResp.setValStatus(Consts.IDENTIFY_STATUS_SUCC);//认证成功  
			   sendCodeResp.setRespMsg("验证成功");
			   sendCodeResp.setResultCode(ResultCodes.NORMAL);
		   }
			   
		}else if(status.equals(Consts.IDENTIFY_STATUS_SUCC)){//中投认证成功
			 //调用诺亚接口
			DoubleIdentityValResp doubleResp=sendNoah(sendCodeReq);
			
		   logger.info("中投验证成功:调用诺亚结果==="+new Gson().toJson(doubleResp));	
		   
		   if(doubleResp.getResultCode().equals(ResultCodes.NOAH_VALID_FAIL)){//中投认证成功,诺亚失败
			   //都成功，更改标志为都支持
			   logger.info("用户："+sendCodeReq.getUserName()+"卡号"+sendCodeReq.getAccountNo()+"诺亚中投都认证成功，更新代扣标志为：ALLDK");
			   
			   DoubleIdentityValService doubleService=new DoubleIdentityValService();
			   
			   if(!StringUtils.isEmpty(sendCodeReq.getAppNo())){
			   doubleService.updateInput(sendCodeReq.getAccountNo(), sendCodeReq.getBankCode(), sendCodeReq.getAppNo(), Consts.ALLDK);
			   }
			   sendCodeResp.setResultCode(ResultCodes.NORMAL);
			   sendCodeResp.setValStatus(Consts.IDENTIFY_STATUS_SUCC);//认证成功     
			  
		   }
		   sendCodeResp.setRespMsg("验证成功");
		}//其余的就是发送验证码成功
		
//		if(!StringUtils.isEmpty(sendCodeReq.getAppNo())){
//			
//		 DoubleIdentityValService doubleService=new DoubleIdentityValService();
//		//更新订单表验证标志位:
//		if(sendCodeResp.getValStatus().equals(Consts.IDENTIFY_STATUS_SUCC)){
//			
//			doubleService.updateInput(sendCodeReq.getAccountNo(), sendCodeReq.getBankCode(), sendCodeReq.getAppNo(), Consts.Y);
//		 
//		}else if(sendCodeResp.getValStatus().equals(Consts.IDENTIFY_STATUS_FAIL)){//认证失败,标志位更改为失败:N
//			doubleService.updateInput(sendCodeReq.getAccountNo(), sendCodeReq.getBankCode(), sendCodeReq.getAppNo(), Consts.N);
//		}
//		
//		}
		
		}catch(Exception e){
			logger.error("发送验证码异常:",e);
			sendCodeResp.setValStatus(Consts.IDENTIFY_STATUS_FAIL);	
			resultCode = ResultCodes.CMBC_IDENTIFY_SENDCODE_EXCEPTION;
			 sendCodeResp.setResultCode(resultCode);
		}
	    
	   logger.info(sendCodeReq.getAccountName()+"中投实名认证发送短信验证,返回前台结果:"+new Gson().toJson(sendCodeResp));
		return sendCodeResp;
	}

	/**
	 * 发送请求到中投
	 * @param sendCodeReq
	 * @return
	 */
	public CmbcIdentifySendCodeResp sendZt(CmbcIdentifySendCodeReq sendCodeReq){
		
		CmbcIdentifySendCodeResp sendCodeResp = new CmbcIdentifySendCodeResp();
		try {

			/* 获取用户资料 */
			CustomerEntity customer = queryCustomer(sendCodeReq.getUserName());
			if (customer != null) {
               
				/* 如果之前调用过中投银行卡验证接口 */
				if (querySendCodeSucc(sendCodeReq)) {// 已经认证通过
					// 中投认证成功:更新订单表代扣标志位,修改客户收款银行卡,增加代扣验证记录
					DoubleIdentityValResp resp=	operaZt(sendCodeReq);
					if(resp.getResultCode().equals(ResultCodes.NORMAL)){
						
					  status = Consts.IDENTIFY_STATUS_SUCC;	// 已经验证通过了
					  resultCode = ResultCodes.NORMAL;
					  
					}else{
						status = Consts.IDENTIFY_STATUS_FAIL;
						resultCode = ResultCodes.NORMAL;	
					}
					
					bankRtnMsg=resp.getResultMsg();
					     
					
				}else if(querySendCodeWait(sendCodeReq)){
					
					status = Consts.IDENTIFY_STATUS_WAIT;//状态为，处理中
					
					resultCode = ResultCodes.NORMAL;// 
				}else {

					CmbcIdentifySendCodeResEntity cmbcResp = remoteHttpSendCode(sendCodeReq);

					logger.info("\n#####【民生实名认证发送验证码结果："
							+ cmbcResp.getReturnCode() + "，"
							+ cmbcResp.getErrorMsg() + "，流水号："
							+ cmbcResp.getBussFlowNo() + ",手机验证码:"
							+ cmbcResp.getPhoneVerCode() + ",手机令牌："
							+ cmbcResp.getPhoneToken() + "】#####");
					bussFlowNo = cmbcResp.getBussFlowNo();

					bankRtnCode = cmbcResp.getRespCode();
					

					/* 发送短信成功 */
					if (Consts.REMOTE_RESULT_01
							.equals(cmbcResp.getReturnCode())) {

						status = Consts.SEND_CODE_STATUS_SUCC;
						resultCode = ResultCodes.NORMAL;
						// 发送短信成功
						phoneVerCode = cmbcResp.getPhoneVerCode();
						phoneToken = cmbcResp.getPhoneToken();
						bankRtnMsg = "发送短信验证码成功";
					} else {

						if (Consts.REMOTE_RESULT_404.equals(cmbcResp
								.getReturnCode())) {
							/* 连接超时 */
							resultCode = ResultCodes.NORMAL;
							status = Consts.SEND_CODE_STATUS_FAIL;
						} else {
							/* 发送短信失败 */
							status = Consts.SEND_CODE_STATUS_FAIL;
							resultCode = ResultCodes.NORMAL;
						}
						bankRtnMsg = "发送短信验证码失败";
					}
					validateCode = cmbcResp.getReturnCode();
					validateMsg = cmbcResp.getErrorMsg();

					/* 记录实名认证发送短信状态 */
					recordHmdProxyResult(sendCodeReq);
  
				}

	

			} else {
				status = Consts.SEND_CODE_STATUS_FAIL;
				resultCode = ResultCodes.PROXYDEDUCTMONEY_USER_NOTFOUND;
			}

		} catch (Exception e) {
			status = Consts.SEND_CODE_STATUS_FAIL;
			resultCode = ResultCodes.CMBC_IDENTIFY_SENDCODE_EXCEPTION;
		
			
		}
		sendCodeResp.setRespCode(bankRtnCode);
		sendCodeResp.setRespMsg(bankRtnMsg);//验证失败,银行返回
		sendCodeResp.setValStatus(status);//验证编码
		sendCodeResp.setPhoneToken(phoneToken);
		sendCodeResp.setPhoneVerCode(phoneVerCode);
		sendCodeResp.setResultCode(resultCode);
		sendCodeResp.setBussFlowNo(bussFlowNo);
		logger.info(sendCodeReq.getAccountName()+"中投实名认证发送短信验证,返回:"+new Gson().toJson(sendCodeResp));
		
		return sendCodeResp;
	}
	/**
	 * 记录发送短信验证码状态
	 * 
	 * @param proxyReq
	 * @param customer
	 */
	private void recordHmdProxyResult(CmbcIdentifySendCodeReq sendCodeReq) {
		   //查询中投银行名称
		String  bankName = queryBankName(sendCodeReq.getBankCode());
		Map<String, Object> inputMap = new HashMap<String, Object>();
		inputMap.put("USERNAME", sendCodeReq.getUserName());
		inputMap.put("account_name", sendCodeReq.getAccountName());
		inputMap.put("IDENTIfY_NO", sendCodeReq.getCertNo());
		inputMap.put("account_no", sendCodeReq.getAccountNo());
		inputMap.put("bank_name", bankName);
		inputMap.put("mobile_no", sendCodeReq.getMobileNo());
		inputMap.put("val_STATUS", status);
		inputMap.put("VALIDATE_MSG", validateMsg);
		inputMap.put("VALIDATE_code", validateCode);
		inputMap.put("BUSS_FLOWNO", bussFlowNo);

		inputMap.put("BANK_RTN_CODE", bankRtnCode);
		inputMap.put("BANK_RTN_MSG", bankRtnMsg);

		inputMap.put("CREATE_TIME", DateUtils.getDateStr(new Date()));
		inputMap.put("phone_VerCode", phoneVerCode);
		inputMap.put("PHONE_TOKEN", phoneToken);
		CmbcIdentifyEvent identifyValEvent = new CmbcIdentifyEvent();
		identifyValEvent.recordIdentifyVal(inputMap);
	}

	// /**
	// * 查询用户银行卡是否有验证记录
	// *
	// * @param proxyReq
	// * @param customer
	// * @return
	// */
	// public HmdProxyValEntity queryAccountValRecord(
	// ProxyDeductMoneyReq proxyReq, CustomerEntity customer) {
	//
	// HmdProxyValQuery query = new HmdProxyValQuery();
	// return query.queryByNameAndCard(customer.getRealName(),
	// proxyReq.getBankCard(), customer.getIdentityNo());
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
	 * 发送短信验证码
	 * 
	 * @param proxyReq
	 * @param customer
	 * @return
	 * @throws HttpException
	 * @throws IOException
	 * @throws HttpReturnException
	 * @throws HttpUrlRemoteException
	 */
	private CmbcIdentifySendCodeResEntity remoteHttpSendCode(
			CmbcIdentifySendCodeReq sendCodeReq) throws HttpException,
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
		
		parmMap.put("accountName", sendCodeReq.getAccountName());// 账户名称
	
		parmMap.put("bussFlowNo", merchantNo + timestamp + randomStr);// 交易流水号
		parmMap.put("mobileNo", sendCodeReq.getMobileNo());

		ServiceConfigQuery configQuery = new ServiceConfigQuery();
		ServiceConfigEntity serviceConfigEntity = configQuery
				.queryServiceByCode(Consts.CMBC_IDENTIFY_SENDCODE);

		String httpResp = "";

		try {

			httpResp = HttpRemotePost.sendHttp(
					serviceConfigEntity.getServiceUrl(), parmMap);

		} catch (ConnectTimeoutException e) {
			logger.error("\n[民生实名认证发送验证码]：异常 => 远程连接超时。。",e);
		
			CmbcIdentifySendCodeResEntity entity = new CmbcIdentifySendCodeResEntity();
			entity.setReturnCode("404");
			entity.setErrorMsg("远程连接超时");
			entity.setBussFlowNo("");
			entity.setRespCode("");
			entity.setRespMsg("");
			entity.setPhoneToken("");
			entity.setPhoneVerCode("");

			return entity;
		} catch (SocketTimeoutException e) {
			logger.error("\n[民生实名认证发送验证码]：异常 => 远程读取数据超时。。",e);
			CmbcIdentifySendCodeResEntity entity = new CmbcIdentifySendCodeResEntity();
			entity.setReturnCode("404");
			entity.setErrorMsg("远程读取数据超时");
			entity.setBussFlowNo("");
			entity.setRespCode("");
			entity.setRespMsg("");
			entity.setPhoneVerCode("");
			entity.setPhoneToken("");
			return entity;
		}

		return new Gson().fromJson(httpResp,
				CmbcIdentifySendCodeResEntity.class);
	}
	/**
	 * 获取用户验证银行卡记录
	 * 
	 * @param realName
	 * @param bankCard
	 * @return
	 */
	public boolean querySendCodeWait(CmbcIdentifySendCodeReq sendCodeReq) {

		CmbcIdentifySendCodeQuery sendCodeQuery = new CmbcIdentifySendCodeQuery();
		CmbcIdentifySendCodeEntity entity = sendCodeQuery
				.querySendCodeWait(sendCodeReq);

		if (entity != null) {

			return true;
		}

		return false;
	}
	
	/**
	 * 获取用户验证银行卡记录--中投
	 * @param sendCodeReq
	 * @return
	 */
	public boolean querySendCodeSucc(CmbcIdentifySendCodeReq sendCodeReq) {

		CmbcIdentifySendCodeQuery sendCodeQuery = new CmbcIdentifySendCodeQuery();
		CmbcIdentifySendCodeEntity entity = sendCodeQuery
				.querySendCodeSucc(sendCodeReq);

		if (entity != null) {

			return true;
		}

		return false;
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
	 * 更新订单表代扣标志位,修改客户收款银行卡,增加代扣验证记录
	 * 
	 * @param sendCodeReq
	 * @return
	 */
	public DoubleIdentityValResp operaZt(CmbcIdentifySendCodeReq sendCodeReq) {

		DoubleIdentityValReq doubleValReq = new DoubleIdentityValReq();

		DoubleIdentityValService doubleService = new DoubleIdentityValService();
		doubleValReq.setAccountName(sendCodeReq.getAccountName());
		doubleValReq.setAccountNo(sendCodeReq.getAccountNo());
		doubleValReq.setAppNo(sendCodeReq.getAppNo());
		doubleValReq.setBankCode(sendCodeReq.getBankCode());
		doubleValReq.setCertNo(sendCodeReq.getCertNo());
		doubleValReq.setMobileNo(sendCodeReq.getMobileNo());
		doubleValReq.setUserName(sendCodeReq.getUserName());
		doubleValReq.setWhichPart(Consts.ZTDK);
		doubleValReq.setDkProvince(sendCodeReq.getDkProvince());
		doubleValReq.setDkCity(sendCodeReq.getDkCity());
		doubleValReq.setDkAreaCode(sendCodeReq.getDkAreaCode());
		doubleValReq.setDkBankBranch(sendCodeReq.getDkBankBranch());
		return doubleService.updateDInputApp(doubleValReq);

	}
	/**
	 * 调用诺亚接口
	 * @param sendCodeReq
	 * @return
	 */
	public DoubleIdentityValResp sendNoah(CmbcIdentifySendCodeReq sendCodeReq){
		
	
		DoubleIdentityValReq doubleValReq=new DoubleIdentityValReq();
		
		 DoubleIdentityValService doubleService=new DoubleIdentityValService();
		 doubleValReq.setAccountName(sendCodeReq.getAccountName());
		 doubleValReq.setAccountNo(sendCodeReq.getAccountNo());
		 doubleValReq.setAppNo(sendCodeReq.getAppNo());
		 doubleValReq.setBankCode(sendCodeReq.getBankCode());
		 doubleValReq.setCertNo(sendCodeReq.getCertNo());
		 doubleValReq.setMobileNo(sendCodeReq.getMobileNo());
		 doubleValReq.setUserName(sendCodeReq.getUserName());
		 doubleValReq.setWhichPart(Consts.NYDK);
			doubleValReq.setDkProvince(sendCodeReq.getDkProvince());
			doubleValReq.setDkCity(sendCodeReq.getDkCity());
			doubleValReq.setDkAreaCode(sendCodeReq.getDkAreaCode());
			doubleValReq.setDkBankBranch(sendCodeReq.getDkBankBranch());

		 return  doubleService.noahValidResult(doubleValReq);
	}
}
