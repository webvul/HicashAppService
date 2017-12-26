package com.hengyuan.hicash.domain.service.user;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.query.user.CustcustomerStuQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.user.CustCustomer;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.QueryValNameReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.QueryValNameResp;
import com.hengyuan.hicash.utils.RecordUtils;

/** 
 * @author dong.liu 
 * 2017-9-5 下午5:40:46
 * 类说明 
 */
public class QueryValNameService implements RespService{

	
	private CustcustomerStuQuery custcustomerStuQuery = new CustcustomerStuQuery();
	
	private static Logger logger = Logger.getLogger(QueryValNameService.class);

	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {
		// TODO Auto-generated method stub
		QueryValNameReq req = (QueryValNameReq) parmRequest;
		QueryValNameResp resp = new QueryValNameResp();
		try {
			CustCustomer cust=custcustomerStuQuery.queryCustCustomer(req.getUserName());
			if(cust!=null){
				 resp.setIsUpdateName(cust.getIsUpName());
				 resp.setRealName(cust.getRealName());
				 resp.setResultCode(ResultCodes.NORMAL);
				
			}else{
				resp.setResultCode(ResultCodes.REGISTER_NOT_EXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.setResultCode(ResultCodes.REGISTER_QUERY_EXCEPTION);
			/* 记录错误信息 */
			RecordUtils.writeError(logger, "", ResultCodes.REGISTER_QUERY_EXCEPTION, e);
		}
		
		return resp;
	}

}
