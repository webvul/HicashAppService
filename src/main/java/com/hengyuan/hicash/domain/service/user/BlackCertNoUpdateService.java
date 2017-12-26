package com.hengyuan.hicash.domain.service.user;

import java.util.List;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.log4j.Logger;

import com.bc.util.Helper;
import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.event.param.BlackRecordEvent;
import com.hengyuan.hicash.domain.event.user.BlackCertNoUpdateEvent;
import com.hengyuan.hicash.domain.query.rule.RulesQuery;
import com.hengyuan.hicash.domain.query.user.CustomerQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.remote.BlackRecordEntity;
import com.hengyuan.hicash.entity.user.CustomerEntity;
import com.hengyuan.hicash.exception.UpdateAmountException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.BlackCertNoUpdateReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.BlackCertNoUpdateResp;
import com.hengyuan.hicash.utils.ResourceUtils;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * @author Administrator
 *
 */
public class BlackCertNoUpdateService implements RespService {

	private static Logger logger = Logger
			.getLogger(BlackCertNoUpdateService.class);

	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {
		BlackCertNoUpdateReq updateMsgReq = (BlackCertNoUpdateReq) parmRequest;
		BlackCertNoUpdateResp updateMsgResp = new BlackCertNoUpdateResp();
		String resultCode = "";
		String resultMsg = ""; // 返回消息
		logger.info("#####\n[HicashAppService 身份证黑名单接口]#####");
		try {
			RulesQuery rulesQuery = new RulesQuery();
		
			//查询本地库中是否是黑名单
			List certNoList = rulesQuery.queryidentityNo(updateMsgReq.getCertNo());
			// 如果本地库中已经是黑名单
			if (certNoList != null && certNoList.size() > 0) {
				resultCode = ResultCodes.BLACK_CERTNO_UPDATE_ALREADY;
				logger.info("\n#####[身份证号：【"+ updateMsgReq.getCertNo() +"】命中[本地库]黑名单]#####");
			}// 如果不存在库黑名单中，则调用黑名单接口
			else{
				
				//查询黑名单记录表中，今天是否已经调用了接口（每天调用一次）
				String identify=rulesQuery.queryIdentifyRecord(updateMsgReq.getCertNo());
				//如果调用过接口
				if(!StringUtils.isEmpty(identify)){
					//查看是否是黑名单
					String resultIdentify=rulesQuery.queryIdentifyRecordYN(updateMsgReq.getCertNo());
					//判断返回状态信息
					if (resultIdentify.equals("200")) {
						
						resultCode = ResultCodes.BLACK_CERTNO_UPDATE_SAVEBLACK;
						logger.info("\n#####[身份证号：【"+ updateMsgReq.getCertNo() +"】命中[远程接口]黑名单]#####");
					}else{
						resultCode = ResultCodes.NORMAL;
					}	
				}else{
				// 如果接口返回是黑名单，则存入数据库
				String blackRes=sendService(updateMsgReq.getCertNo());
				logger.info("\n#####[身份证号：【"+ updateMsgReq.getCertNo() +"】身份证远程接口验证：返回状态码 "+ blackRes +"]#####");
				// 接口调用记录
				recordBlackInfo(updateMsgReq, blackRes);
				
				//判断返回状态信息
				if (blackRes.equals("200")) {
					// 保存进黑名单
					saveBlack(updateMsgReq.getCertNo());
					
					resultCode = ResultCodes.BLACK_CERTNO_UPDATE_SAVEBLACK;
//					resultCode = ResultCodes.BLACK_CERTNO_UPDATE_ALREADY;
					logger.info("\n#####[身份证号：【"+ updateMsgReq.getCertNo() +"】命中[远程接口]黑名单]#####");
				}
				else if(blackRes.equals("201")) {
					resultCode = ResultCodes.NORMAL;
				}
				else if(blackRes.equals("100")) {
					resultCode = ResultCodes.BLACK_CERTNO_UPDATE_FALLUSERPWD;
				}
				
				else if(blackRes.equals("102")) {
					resultCode = ResultCodes.BLACK_CERTNO_UPDATE_NOROUL;
				}
				else if(blackRes.equals("101")) {
					resultCode = ResultCodes.BLACK_CERTNO_UPDATE_BALANCESMALL;
				}
				else if(blackRes.equals("500")) {
					resultCode = ResultCodes.BLACK_CERTNO_UPDATE_FALLSERVICE;
				}
				
			}
			}
			

		} catch (Exception e) {
			resultCode = ResultCodes.CALL_PHONE_DATA_SAVE_EXCEPTION;
			e.printStackTrace();
		} finally {
			ConnManager.closeConn();
		}

		updateMsgResp.setResultCode(resultCode);
		updateMsgResp.setResultMsg(resultMsg);
		return updateMsgResp;
	}

	public String sendService(String certNO) {
		
		String SOAP_URL=ResourceUtils.getValue(Consts.BLACKURL);
//		String userName=ResourceUtils.getValue(Consts.BLACKUSERNAME);
//		String pwd=ResourceUtils.getValue(Consts.BLACKPASSWORK);
		String apikeyStr = ResourceUtils.getValue(Consts.APIKEY);
		String method =ResourceUtils.getValue(Consts.BLACKMETHOD);
		
			/**
			 * 创建cxf客户端对象
			 */
			JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
			//创建调用SOAP_URL的Client 
			Client client = dcf.createClient(SOAP_URL);  
			Object[] res = null;
				try {
					//调用具体的方法
					//invoke参数说明  参数1：调用的具体方法  参数2～3，分别 wsdl文件中的
					/**
					 * <xs:element minOccurs="0" name="userName" type="xs:string"/>
						<xs:element minOccurs="0" name="passWord" type="xs:string"/>
						<xs:element minOccurs="0" name="idCard" type="xs:string"/>
					 */
					//按顺序写入
//					res =  client.invoke("getSingleRecord",userName,pwd,certNO);
					System.out.println("url：" + SOAP_URL + "\napikeyStr：" + apikeyStr + "\nmethod：" + method);
					System.out.println("加密参数【身份证：" + certNO + "，加密字符串：" + apikeyStr + " 】");
					String apiKey = Helper.encrypt(certNO, apikeyStr); // 加密 
					System.out.println("加密结果：" + apiKey);
					res =  client.invoke(method, certNO, apiKey);
					
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("秉承黑名单接口调用【" + certNO + "】 异常：\n" + e.getMessage());
				}
				String objResult=(String)res[0];
				System.out.println(objResult); 
				return objResult;

//		//如果是黑名单
//		if(objResult.equals("200")){
//			return "1";
//		}else if(objResult.equals("201")){
//		return "2";
//		}else{
//			return"3";
//		}
	}

	public void saveBlack(String identityNO) throws UpdateAmountException {

		BlackCertNoUpdateEvent event = new BlackCertNoUpdateEvent();
		event.saveIdentity(identityNO);
	}
	
	private void recordBlackInfo(BlackCertNoUpdateReq updateMsgReq, String result){
		
		CustomerQuery customerQuery = new CustomerQuery();
		CustomerEntity customer = customerQuery.queryCustByIdCard(updateMsgReq.getCertNo());
		
		BlackRecordEntity entity = new BlackRecordEntity();
		
		if(customer != null){
			entity.setUserName(customer.getUserName());
//			entity.setRealName(customer.getRealName());
		}
		entity.setIdentityNo(updateMsgReq.getCertNo());
		entity.setFromType(Consts.FROMTYPE_FRBC);
		entity.setResultCode(result);
		entity.setBlackDesc(Consts.FINDAL_DESC1);
		entity.setRemoteIp(updateMsgReq.getRequestIp());
		
		BlackRecordEvent event = new BlackRecordEvent();
		event.recordBlackInfo(entity);
	}
	
	public static void main(String[] args) {
		
		BlackCertNoUpdateService service = new BlackCertNoUpdateService();
//		service.recordBlackInfo("430703199312143253", "200");
//		service.sendService("111111111111111110");
		
		String SOAP_URL = ResourceUtils.getValue(Consts.BLACKURL);
		String userName = ResourceUtils.getValue(Consts.BLACKUSERNAME);
		String apikeyStr = ResourceUtils.getValue(Consts.APIKEY);
		String method =ResourceUtils.getValue(Consts.BLACKMETHOD);
		
		try {
			
			// 秉承黑名单测试地址
//			String SOAP_URL= "http://www.bccash8.com:10010/interface/assets/querybasedata?wsdl";
			String idCard = "111111111111111110"; // 430703199312143254
			// 加密字符串 通过身份证号和加密APIKEY调用encrypt得到
			String apiKey = Helper.encrypt(idCard, apikeyStr);
			System.out.println(apiKey);
			JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
			Client client = dcf.createClient(SOAP_URL);  
			
			
			Object[] res =  client.invoke(method, idCard, apiKey);

			System.out.println(res[0]);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	

}
