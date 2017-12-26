package com.hengyuan.hicash.domain.service.user;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpException;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.event.user.DdsjTempapplyUpdateEvent;
import com.hengyuan.hicash.domain.query.user.CustcustomerQuery;
import com.hengyuan.hicash.domain.query.user.SaveReserveNum;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.user.CustCustomer;
import com.hengyuan.hicash.entity.user.QueryAuthEntity;
import com.hengyuan.hicash.exception.HttpReturnException;
import com.hengyuan.hicash.exception.HttpUrlRemoteException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.JxlCreditReq;
import com.hengyuan.hicash.parameters.request.user.JxlOrgApiApplicationsReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.JxlOrgApiApplicationsResp;
import com.hengyuan.hicash.utils.HttpRemotePost;
import com.hengyuan.hicash.utils.ResourceUtils;

/** 
 * @author blanke.qin
 * 2017年1月11日 下午6:13:41
 * 类说明 
 */
public class DriverCreditService  implements RespService{
	
	private static Logger logger = Logger.getLogger(DriverCreditService.class);
	
	private CustcustomerQuery custcustomerQuery = new CustcustomerQuery();
	
	private SaveReserveNum saveReserveNum = new SaveReserveNum();
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {
		  
		 JxlOrgApiApplicationsResp infoResp = new JxlOrgApiApplicationsResp();
		 
		 JxlOrgApiApplicationsReq infoReq = (JxlOrgApiApplicationsReq)parmRequest;
		 
		 CustCustomer custCustomer = new CustCustomer();
		 
		 custCustomer = custcustomerQuery.queryCustCustomer(infoReq.getUsername());
		 
		 if(custCustomer!=null){
			 
			if(infoReq.getTime().equals("2") || infoReq.getTime().equals("3") || infoReq.getTime().equals("4")){
//				QueryAuthEntity authEuntity=saveReserveNum.queryReserveNumber(infoReq.getUsername());
//				if(authEuntity !=null){
//					infoReq.setPassword(authEuntity.getReservePassword());
//					infoReq.setMobile(authEuntity.getReserveNumber());
//				}
				
				DdsjTempapplyUpdateEvent tempapplyUpdateEvent = new DdsjTempapplyUpdateEvent();
				tempapplyUpdateEvent.updPwdFromTempAppNo(infoReq.getPassword(),infoReq.getMobile(),infoReq.getTemp_app_no());
			}
			logger.info("用户名:"+custCustomer.getUserName()+"开始调用司机API认证");
			//组装请求参数
			HashMap<String, String> parmMap = new HashMap<String, String>();
			
			parmMap.put("id_card_no", custCustomer.getIdentityNo());
			parmMap.put("name", custCustomer.getRealName());
			parmMap.put("mobile", infoReq.getMobile());
			parmMap.put("work_addr", custCustomer.getWorkAreaRoad());
			parmMap.put("home_addr", custCustomer.getNowFimilyAdress());
			parmMap.put("message", infoReq.getMessage());
			parmMap.put("password", infoReq.getPassword());
			parmMap.put("seq_no", infoReq.getSeq_no());
			parmMap.put("time", infoReq.getTime());
			
			String str = null;
			
			try{
				
				str= HttpRemotePost.sendNoTimeHttp(ResourceUtils.getValue(Consts.DRIVER_CREDIT_URL),parmMap);
			
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("调司机API接口异常！用户名:"+custCustomer.getUserName());
				infoResp.setResultCode("0");
				infoResp.setResultMsg("网络服务异常！司机API错误！");
			} 
			
			
			try {
				JSONObject object = JSONObject.parseObject(str);
				
				if(!object.isEmpty()){
					
					infoResp.setResultCode(object.getString("resultCode"));
					infoResp.setResultMsg(object.getString("resultMsg"));
					
					if(object.containsKey("seq_no")){
						infoResp.setSeq_no(object.getString("seq_no"));
					}
					
				}else{
					infoResp.setResultCode("0");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("返回JSON格式错误！用户名:"+custCustomer.getUserName());
				
				infoResp.setResultCode("0");
			}
			 
		 }else{
			 
			 infoResp.setResultCode(ResultCodes.NO_RESULT);
			 infoResp.setResultMsg(ResourceUtils.getString(ResultCodes.NO_RESULT));
			 
		 }
		return infoResp;
	}
	
}
