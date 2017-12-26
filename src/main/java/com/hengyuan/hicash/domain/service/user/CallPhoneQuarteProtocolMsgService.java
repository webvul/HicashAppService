package com.hengyuan.hicash.domain.service.user;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.query.user.CallPhoneQuartetProtocolAppQuery;
import com.hengyuan.hicash.domain.query.user.CallPhoneQuartetProtocolMsgQuery;
import com.hengyuan.hicash.domain.query.user.CustcustomerQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.user.CustCustomer;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.CallPhoneQuartetProtocolMsgReq;
import com.hengyuan.hicash.parameters.response.user.CallPhoneQuartetProtocolAppResp;
import com.hengyuan.hicash.parameters.response.user.CallPhoneQuartetProtocolMsgResp;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * 	电信签订四方协议展示，姓名，申请产品名字，每月还款日，每月还款：元期----逻辑类
 * 
 * @author lihua.Ren
 * @create date 2015-08-20
 *
 */
public class CallPhoneQuarteProtocolMsgService  implements RespService {
	

	private static Logger logger = Logger.getLogger(CallPhoneQuarteProtocolMsgService.class);

	private CallPhoneQuartetProtocolMsgQuery quartetQuery = new CallPhoneQuartetProtocolMsgQuery();
	private CallPhoneQuartetProtocolAppQuery  query=new CallPhoneQuartetProtocolAppQuery();
	private CustcustomerQuery custCustomerQuery=new CustcustomerQuery();

	
	@Override
	public CallPhoneQuartetProtocolMsgResp responseValue(ParmRequest parmRequest) {
		CallPhoneQuartetProtocolMsgReq req = (CallPhoneQuartetProtocolMsgReq) parmRequest;
		CallPhoneQuartetProtocolMsgResp resp = null;
		CallPhoneQuartetProtocolAppResp resp1 = null;
		
		try{
				resp = quartetQuery.queryCallPhoneQuartet(req);
				//根据用户名查出email
				CustCustomer customer=custCustomerQuery.queryCustCustomer(resp.getUserName());
				//查出行业代码
				resp1=query.queryCallPhoneQuartet(req);
				resp.setProAmount(resp1.getProAmount());
				resp.setLoanProductId(resp1.getLoanProductId());
				resp.setProType(resp1.getProType());
				resp.setApplyAmount(resp1.getApplyAmount());
				resp.setEmail(customer.getEmailAdress());
				if(resp != null){
					resp.setResultCode(ResultCodes.NORMAL);
				}else{
					
					resp.setResultCode(ResultCodes.CALL_PHONE_QUARTET_PROTOCOL_NULL);
				}
			
			
		}catch (Exception e) {
			resp = new CallPhoneQuartetProtocolMsgResp();
			resp.setResultCode(ResultCodes.CALL_PHONE_QUARTET_PROTOCOL_EXCEPTION);
			
			/* 记录错误信息 */
			RecordUtils.writeError(logger, req.getUuid(), ResultCodes.CALL_PHONE_QUARTET_PROTOCOL_EXCEPTION, e);
		} finally {
			ConnManager.closeConn();
		}

		
		return resp;
	}

}
