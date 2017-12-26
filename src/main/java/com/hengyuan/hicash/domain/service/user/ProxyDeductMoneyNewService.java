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
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.event.user.HmdProxyValEvent;
import com.hengyuan.hicash.domain.query.param.BankQuery;
import com.hengyuan.hicash.domain.query.user.CustomerQuery;
import com.hengyuan.hicash.domain.query.user.HmdProxyRecordQuery;
import com.hengyuan.hicash.domain.query.user.ServiceConfigQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.param.BankEntity;
import com.hengyuan.hicash.entity.param.CmbcGFAResEntityNew;
import com.hengyuan.hicash.entity.user.CustomerEntity;
import com.hengyuan.hicash.entity.user.HmdProxyRecordEntity;
import com.hengyuan.hicash.entity.user.ServiceConfigEntity;
import com.hengyuan.hicash.exception.HttpReturnException;
import com.hengyuan.hicash.exception.HttpUrlRemoteException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.ProxyDeductMoneyNewReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.ProxyDeductMoneyNewResp;
import com.hengyuan.hicash.utils.DateUtils;
import com.hengyuan.hicash.utils.HttpRemotePost;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 嗨秒贷
 * 验证用户收款银行卡信息,5块钱
 * 代理扣款
 * @author Lihua.Ren
 * @createDate 2015-11-03
 *
 */
public class ProxyDeductMoneyNewService implements RespService {

	private static Logger logger = Logger
			.getLogger(ProxyDeductMoneyNewService.class);

	private String resultCode = "";

	private String bankName = "";
	private String status = "";
	private String validateMsg = "";
	private String bussFlowNo = "";// 交易流水号

	private String bankRtnCode = ""; // 银行返回结果代码

	private String bankRtnMsg = ""; // 银行返回结果消息

	private String custId = ""; // 银行返回客户号

	private String merOrderId = ""; // 银行返回订单号
	private String phoneToken = ""; // 银行返回手机令牌




	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		ProxyDeductMoneyNewReq proxyReq = (ProxyDeductMoneyNewReq) parmRequest;
		ProxyDeductMoneyNewResp proxyResp = new ProxyDeductMoneyNewResp();

		try {

			/* 获取用户资料 */
			CustomerEntity customer = queryCustomer(proxyReq.getUserName());
			if (customer != null) {

				ConnManager.beginTransaction();

				bankName = queryBankName(proxyReq.getOpenBank());
				/* 如果之前调用过代扣接口 */
				if (queryHmdProxy5Succ(customer.getRealName(),
						proxyReq.getBankCard())) {
					status = Consts.PROXY_STATUS_SUCC;//代扣成功
					resultCode = ResultCodes.NORMAL;

				} else if(queryHmdProxy5Wait(customer.getRealName(),
						proxyReq.getBankCard())){
					status = Consts.PROXY_STATUS_WAIT;//代扣处理中
					resultCode = ResultCodes.NORMAL;
				}else if(queryHmdProxy5YEBZ(customer.getRealName(),
						proxyReq.getBankCard())){
					status = Consts.PROXY_STATUS_YEBZ;//代扣余额不足
					resultCode = ResultCodes.NORMAL;	
				}			
				else 
				{
					CmbcGFAResEntityNew cmbcResp = remoteHttpProxyDeduct(
							proxyReq, customer);

					logger.info("\n#####【代扣快捷鉴权结果：" + cmbcResp.getReturnCode()
							+ "，" + cmbcResp.getErrorMsg() + "，流水号："
							+ cmbcResp.getBussFlowNo() + "】#####");
					bussFlowNo = cmbcResp.getBussFlowNo();

					bankRtnCode = cmbcResp.getRespCode();
					bankRtnMsg = cmbcResp.getRespMsg();

					/* 发送短信成功 */
					if (Consts.REMOTE_RESULT_01
							.equals(cmbcResp.getReturnCode())) {

						status = Consts.PROXY_STATUS_CODE_SUCC;//发送短信成功
						resultCode = ResultCodes.NORMAL;
						// 发送短信成功
						custId=cmbcResp.getCustId();
						merOrderId=cmbcResp.getMerOrderId();
						phoneToken=cmbcResp.getPhoneToken();
					} else {

						if (Consts.REMOTE_RESULT_404.equals(cmbcResp
								.getReturnCode())) {
							/* 连接超时 */
							resultCode = ResultCodes.NORMAL;
							status = Consts.FINAL_NUMBER_6;
						} else {
							/* 发送短信失败 */
							status = Consts.PROXY_STATUS_CODE_FAIL;
							resultCode = ResultCodes.NORMAL;
						}

					}

					validateMsg = cmbcResp.getErrorMsg();
                    
					/* 记录嗨秒贷代扣验证状态 */
					recordHmdProxyResult(proxyReq, customer);



				}


				ConnManager.commit();

			} else {
				resultCode = ResultCodes.PROXYDEDUCTMONEY_USER_NOTFOUND;
			}

		} catch (Exception e) {
			resultCode = ResultCodes.PROXYDEDUCTMONEY_EXCETPION;
			e.printStackTrace();
			ConnManager.rollback();
		} finally {
			ConnManager.closeConn();
		}
		proxyResp.setValStatus(status);
		proxyResp.setCustId(custId);
		proxyResp.setMerOrderId(merOrderId);
		proxyResp.setPhoneToken(phoneToken);
		proxyResp.setResultCode(resultCode);
		proxyResp.setOrgBussFlowNo(bussFlowNo);
		return proxyResp;
	}

	/**
	 * 记录嗨秒贷代扣状态
	 * 
	 * @param proxyReq
	 * @param customer
	 */
	private void recordHmdProxyResult(ProxyDeductMoneyNewReq proxyReq,
			CustomerEntity customer) {

		Map<String, Object> inputMap = new HashMap<String, Object>();
		inputMap.put("USERNAME", customer.getUserName());
		inputMap.put("REAL_NAME", customer.getRealName());
		inputMap.put("IDENTITY_NO", customer.getIdentityNo());
		inputMap.put("BANK_CARD", proxyReq.getBankCard());

		inputMap.put("BANK_NAME", bankName);
		inputMap.put("PROXY_STATUS", status);
		inputMap.put("VALIDATE_MSG", validateMsg);
		inputMap.put("BUSS_FLOWNO", bussFlowNo);

		inputMap.put("BANK_RTN_CODE", bankRtnCode);
		inputMap.put("BANK_RTN_MSG", bankRtnMsg);

		inputMap.put("CREATE_DATE", DateUtils.getDateStr(new Date()));
		inputMap.put("BANK_PHONENO", proxyReq.getMobileNo());
		inputMap.put("BANK_CUST_ID", custId);
		inputMap.put("BANK_ORDERID", merOrderId);
		inputMap.put("BANK_PHONETOKEN", phoneToken);
		HmdProxyValEvent hmdValEvent = new HmdProxyValEvent();
		hmdValEvent.recordHmdProxyVal(inputMap);
	}

	
//	/**
//	 * 查询用户银行卡是否有验证记录
//	 * 
//	 * @param proxyReq
//	 * @param customer
//	 * @return
//	 */
//	public HmdProxyValEntity queryAccountValRecord(
//			ProxyDeductMoneyReq proxyReq, CustomerEntity customer) {
//
//		HmdProxyValQuery query = new HmdProxyValQuery();
//		return query.queryByNameAndCard(customer.getRealName(),
//				proxyReq.getBankCard(), customer.getIdentityNo());
//	}

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
	private CmbcGFAResEntityNew remoteHttpProxyDeduct(
			ProxyDeductMoneyNewReq proxyReq, CustomerEntity customer)
			throws HttpException, IOException, HttpReturnException,
			HttpUrlRemoteException {

		// String merchantNo = "CF3000034853";//测试商户号
		// String merchantNo = "CF2000027924";//生产商户号CF2000027924 20150728
		// 195222 963414
		String merchantNo = ResourceUtils.getValue("merchantNo");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String timestamp = sdf.format(new Date());
		String randomStr = RandomStringUtils.random(6, false, true);

		Map<String, String> parmMap = new HashMap<String, String>();
		/* 业务参数 */
		parmMap.put("accountNo", proxyReq.getBankCard());// 银行帐号
		parmMap.put("accountName", customer.getRealName());// 账户名称
		parmMap.put("tranAmt", "5");// 代扣金额
		parmMap.put("certNo", customer.getIdentityNo());// 用户身份证号码
		parmMap.put("bankName", bankName);// 代扣银行名称
		parmMap.put("bussFlowNo", merchantNo + timestamp + randomStr);// 交易流水号
		parmMap.put("mobileNo", proxyReq.getMobileNo());

		ServiceConfigQuery configQuery = new ServiceConfigQuery();
		ServiceConfigEntity serviceConfigEntity = configQuery
				.queryServiceByCode(Consts.SERVICE_BANKCARD_PROXYDEDUCT_NEW);

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
			CmbcGFAResEntityNew entity = new CmbcGFAResEntityNew();
			entity.setReturnCode("404");
			entity.setErrorMsg("远程连接超时");
			entity.setBussFlowNo("");
			entity.setRespCode("");
			entity.setRespMsg("");
			entity.setCustId("");
			entity.setMerOrderId("");
			entity.setPhoneToken("");
		
			return entity;
		} catch (SocketTimeoutException e) {
			logger.info("\n[嗨秒贷代扣]：异常 => 远程读取数据超时。。");
			e.printStackTrace();
			CmbcGFAResEntityNew entity = new CmbcGFAResEntityNew();
			entity.setReturnCode("404");
			entity.setErrorMsg("远程读取数据超时");
			entity.setBussFlowNo("");
			entity.setRespCode("");
			entity.setRespMsg("");
			entity.setCustId("");
			entity.setMerOrderId("");
			entity.setPhoneToken("");
			return entity;
		}

		return new Gson().fromJson(httpResp, CmbcGFAResEntityNew.class);
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

}
