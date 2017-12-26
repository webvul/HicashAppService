package com.hengyuan.hicash.domain.service.user;

import java.io.IOException;

import org.apache.commons.httpclient.HttpException;

import com.google.gson.Gson;
import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.event.apply.TempAppInfoEvent;
import com.hengyuan.hicash.domain.query.app.ApplicationQuery;
import com.hengyuan.hicash.domain.query.app.InputAppPayQuery;
import com.hengyuan.hicash.domain.query.app.TempApplyQuery;
import com.hengyuan.hicash.domain.query.user.CustcustomerQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.domain.service.remote.RemoteService;
import com.hengyuan.hicash.entity.app.ApplicationEntity;
import com.hengyuan.hicash.entity.app.InputAppPay;
import com.hengyuan.hicash.entity.app.TempApplyEntity;
import com.hengyuan.hicash.entity.remote.AuthorizeReqParam;
import com.hengyuan.hicash.entity.remote.AuthorizeRespParam;
import com.hengyuan.hicash.entity.remote.BasicInfoEntity;
import com.hengyuan.hicash.entity.remote.ServicePswValReqParam;
import com.hengyuan.hicash.entity.remote.ServicePswValRespParam;
import com.hengyuan.hicash.entity.user.CustCustomer;
import com.hengyuan.hicash.exception.UpdateTempAppException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.ServicePswValReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.ServicePswValResp;

/**
 * 验证用户服务密码 service
 * @author Cary.Liu
 * @createDate 2105-06-09
 *
 */
public class ServicePswValService implements RespService {

	private String resultCode = "";
	
	/** 远程调用结果 */
	private String remoteResult = "0";
	
	private String webSite = "";
	
	private String token = "";
	
//	private static String remoteURL_authroize = "https://www.juxinli.com/orgApi/rest/v2/applications/hicash";
//	
//	private static String remoteURL_validate = "https://www.juxinli.com/orgApi/rest/v2/messages/collect/req";
	
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		ServicePswValReq pswReq = (ServicePswValReq)parmRequest;
		ServicePswValResp pswResp = new ServicePswValResp();
		
		try {
			
			ApplicationEntity appPay = queryAppPay(pswReq);
			if(appPay != null){
				
				if(tempAppExistFlag(pswReq)){
					
//					InputAppPay inputApp = queryInputApp(pswReq);
					CustCustomer customer = queryCustomer(pswReq);
					
					if(Consts.SUBMITTYPE_MOBILE.equals(pswReq.getSubmitType())){
						
						if(remoteByValidate(pswReq, customer)){
							resultCode = ResultCodes.NORMAL;
						}
//						else{
//							resultCode = ResultCodes.SERVICEPSWVAL_VALIDATE_FAIL;
//						}
						
					}else{
						
						/* 使用线程 */
						RemotePswThread thread = new RemotePswThread(pswReq, customer);
						thread.start();
						
						/* 远程调用授权token值 */
//						if(remoteByAuthorize(pswReq, customer)){
							
							/* 验证手机服务密码 */
//							if(remoteByValidate(pswReq, customer)){
								
								remoteResult = Consts.FINAL_NUMBER_1;
								
								saveServicePsw(pswReq, remoteResult);
								
								resultCode = ResultCodes.NORMAL;
//							}else{
//								resultCode = ResultCodes.SERVICEPSWVAL_VALIDATE_FAIL;
//							}
							
//						}else{
//							resultCode = ResultCodes.SERVICEPSWVAL_AUTHORIZE_FAIL;
//						}
						
					}
					
					
				}else{
					resultCode = ResultCodes.SERVICEPSWVAL_TEMPAPPNO_NOTFOUND;
				}
				
				
			}else{
				resultCode = ResultCodes.SERVICEPSWVAL_APPUSER_NOTFOUND;
			}
			
		} catch (UpdateTempAppException e){
			
		} catch (Exception e) {
			resultCode = ResultCodes.SERVICEPSWVAL_EXCEPTION;
			e.printStackTrace();
		} finally{
			ConnManager.closeConn();
		}
		
		pswResp.setToken(token);
		pswResp.setWebSite(webSite);
		pswResp.setResultCode(resultCode);
		return pswResp;
	}
	
	public ApplicationEntity queryAppPay(ServicePswValReq pswReq){
		
		ApplicationQuery appQuery = new ApplicationQuery();
		return appQuery.queryAppByUserAndAppNo(pswReq.getUserName(), pswReq.getAppNo());
	}
	
	/**
	 * 获取申请件信息
	 * @param pswReq
	 * @return
	 */
	public InputAppPay queryInputApp(ServicePswValReq pswReq){
		
		InputAppPayQuery inputAppQuery = new InputAppPayQuery();
		return inputAppQuery.queryInputAppByAppNo(pswReq.getAppNo());
	}
	
	/**
	 * 获取客户信息
	 * @param pswReq
	 * @return
	 */
	public CustCustomer queryCustomer(ServicePswValReq pswReq){
		
		CustcustomerQuery customerQuery = new CustcustomerQuery();
		return customerQuery.queryCustCustomer(pswReq.getUserName());
	}
	
	public boolean tempAppExistFlag(ServicePswValReq pswReq){
		
		TempApplyQuery tempQuery = new TempApplyQuery();
		TempApplyEntity entity = tempQuery.queryTempApplyByNo(pswReq.getUserName(), pswReq.getTempAppNo());
		if(entity != null){
			return true;
		}
		return false;
	}
	
	/**
	 * 聚信立获取授权token
	 * @return
	 * @throws IOException 
	 * @throws HttpException 
	 */
	private boolean remoteByAuthorize(ServicePswValReq pswReq,CustCustomer customer) {
		
//		try {
//		
//			AuthorizeReqParam reqParam = new AuthorizeReqParam();
//			BasicInfoEntity basicInfo = new BasicInfoEntity();
//			basicInfo.setName(customer.getRealName());
//			basicInfo.setId_card_num(customer.getIdentityNo());
//			basicInfo.setCell_phone_num(customer.getMobileNo());
//			reqParam.setBasic_info(basicInfo);
//			
//			Gson gson = new Gson();
//
//			String resultJson = httpRemote(remoteURL_authroize, gson.toJson(reqParam));
//			
//			AuthorizeRespParam respParam = gson.fromJson(resultJson, AuthorizeRespParam.class);
//			
//			token = respParam.getData().getToken();
//			webSite = respParam.getData().getDatasource().getWebsite();
//			
//			String result = respParam.getSuccess();
//			System.out.println("获取授权结果：\t" + result);
//			
//			if(!Consts.FINAL_BOOLEAN_TRUE.equalsIgnoreCase(result)){
//				return false;
//			}
//			
//		}catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
		return true;
	}
	
	/**
	 * 验证用户信息，生成报告
	 * @param pswReq
	 * @param customer
	 * @param inputApp
	 * @return
	 */
	private boolean remoteByValidate(ServicePswValReq pswReq,CustCustomer customer){
		
//		try {
//			
//			ServicePswValReqParam servicePswReq = new ServicePswValReqParam();
//		
//			servicePswReq.setAccount(customer.getMobileNo());
//			servicePswReq.setPassword(pswReq.getServicePsw());
//			if(Consts.SUBMITTYPE_MOBILE.equals(pswReq.getSubmitType())){
//				servicePswReq.setCaptcha(pswReq.getDynamicPsw());
//				servicePswReq.setType("SUBMIT_CAPTCHA");
//				servicePswReq.setToken(pswReq.getToken());
//				servicePswReq.setWebsite(pswReq.getWebSite());
//			}else{
//				servicePswReq.setToken(token);
//				servicePswReq.setWebsite(webSite);
//				servicePswReq.setCaptcha("");
//				servicePswReq.setType("");
//			}
//			
//			
//			Gson gson = new Gson();
//			String resultJson = httpRemote(remoteURL_validate, gson.toJson(servicePswReq));
//			
//			ServicePswValRespParam respParam = gson.fromJson(resultJson, ServicePswValRespParam.class);
//			
//			String result = respParam.getSuccess();
//			String processCode = respParam.getData().getProcess_code();
//			System.out.println("手机服务密码验证：\t" + result + "\t结果：" + respParam.getData().getContent());
//			
////			if("10002".equals(processCode)){
////				//服务密码错误
////				resultCode = ResultCodes.SERVICEPSWVAL_SERVICEPSW_FAIL;
////				return false;
////			}
//			if("10003".equals(processCode)){
//				//服务密码错误
//				resultCode = ResultCodes.SERVICEPSWVAL_SERVICEPSW_FAIL;
//				return false;
//			}
//			if("10004".equals(processCode)){
//				//动态密码错误
//				resultCode = ResultCodes.SERVICEPSWVAL_DTPSW_FAIL;
//				return false;
//			}
//			if("10006".equals(processCode)){
//				//动态密码失效，自动重新发送
//				resultCode = ResultCodes.SERVICEPSWVAL_DTPSW_FAIL;
//				return false;
//			}
//			if("10007".equals(processCode)){
//				//简单密码或初始密码无法登录
//				resultCode = ResultCodes.SERVICEPSWVAL_ESAYPSW_FAIL;
//				return false;
//			}
//			
//			if(!Consts.FINAL_BOOLEAN_TRUE.equalsIgnoreCase(result)){
//				resultCode = ResultCodes.SERVICEPSWVAL_VALIDATE_FAIL;
//				return false;
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			resultCode = ResultCodes.SERVICEPSWVAL_VALIDATE_FAIL;
//			return false;
//		}
		
		return true;
	}
	
	/**
	 * 远程调用
	 * @param url 调用地址
	 * @param jsonStr 请求json串
	 * @return
	 * @throws HttpException
	 * @throws IOException
	 */
	private String httpRemote(String url,String jsonStr) throws HttpException, IOException{
		
		RemoteService remoteService = new RemoteService();
		return remoteService.httpRemoteByJson(url, jsonStr);
	}
	
	/**
	 * 保存手机服务密码
	 * @param pswReq
	 * @throws UpdateTempAppException 
	 */
	private void saveServicePsw(ServicePswValReq pswReq,String remoteResult) throws UpdateTempAppException{
		
		/* 保存服务密码 */
		TempAppInfoEvent tempAppEvent = new TempAppInfoEvent();
		tempAppEvent.saveServicePsw(pswReq.getUserName(), pswReq.getTempAppNo(), pswReq.getServicePsw());
		/* 更新申请件节点 */
		
		
	}

}

class RemotePswThread extends Thread{
	
	private ServicePswValReq pswReq;
	
	private CustCustomer customer;
	
	private String webSite = "";
	
	private String token = "";
	
	private static String remoteURL_authroize = "https://www.juxinli.com/orgApi/rest/v2/applications/hicash";
	
	private static String remoteURL_validate = "https://www.juxinli.com/orgApi/rest/v2/messages/collect/req";
	
	public RemotePswThread(ServicePswValReq pswReq,CustCustomer customer){
		this.pswReq = pswReq;
		this.customer = customer;
	}

	@Override
	public void run() {

		try {
			
			System.out.println("调用聚信立接口...#####################################");
			
			AuthorizeReqParam reqParam = new AuthorizeReqParam();
			BasicInfoEntity basicInfo = new BasicInfoEntity();
			basicInfo.setName(customer.getRealName());
			basicInfo.setId_card_num(customer.getIdentityNo());
			basicInfo.setCell_phone_num(customer.getMobileNo());
			reqParam.setBasic_info(basicInfo);
			
			Gson gson = new Gson();

			String resultJson = httpRemote(remoteURL_authroize, gson.toJson(reqParam));
			
			AuthorizeRespParam respParam = gson.fromJson(resultJson, AuthorizeRespParam.class);
			
			token = respParam.getData().getToken();
			webSite = respParam.getData().getDatasource().getWebsite();
			
			String result = respParam.getSuccess();
			System.out.println("获取授权结果：\t" + result);
			
			if(Consts.FINAL_BOOLEAN_TRUE.equalsIgnoreCase(result)){
				
				ServicePswValReqParam servicePswReq = new ServicePswValReqParam();
				
				servicePswReq.setAccount(customer.getMobileNo());
				servicePswReq.setPassword(pswReq.getServicePsw());
				if(Consts.SUBMITTYPE_MOBILE.equals(pswReq.getSubmitType())){
					servicePswReq.setCaptcha(pswReq.getDynamicPsw());
					servicePswReq.setType("SUBMIT_CAPTCHA");
					servicePswReq.setToken(pswReq.getToken());
					servicePswReq.setWebsite(pswReq.getWebSite());
				}else{
					servicePswReq.setToken(token);
					servicePswReq.setWebsite(webSite);
					servicePswReq.setCaptcha("");
					servicePswReq.setType("");
				}
				
				String resultJson2 = httpRemote(remoteURL_validate, gson.toJson(servicePswReq));
				
				ServicePswValRespParam respParam2 = gson.fromJson(resultJson2, ServicePswValRespParam.class);
				
				String result2 = respParam2.getSuccess();
				String processCode = respParam2.getData().getProcess_code();
				System.out.println("手机服务密码验证：\t" + result2 + "\t结果：" + respParam2.getData().getContent());
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * 远程调用
	 * @param url 调用地址
	 * @param jsonStr 请求json串
	 * @return
	 * @throws HttpException
	 * @throws IOException
	 */
	private String httpRemote(String url,String jsonStr) throws HttpException, IOException{
		
		RemoteService remoteService = new RemoteService();
		return remoteService.httpRemoteByJson(url, jsonStr);
	}
	
	
	
}
