package com.hengyuan.hicash.domain.service.order;


import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.query.app.ApplicationPayQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.app.ApplicationPay;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.order.ApplicationPayReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.order.ApplicationPayResp;
import com.hengyuan.hicash.utils.RegexValidate;


/**
 * 
 * @author xuexin
 *
 * @date 2017年1月10日 上午9:56:00
 */
public class ApplicationPayService implements RespService {
	
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		ApplicationPayQuery query = new ApplicationPayQuery();

		ApplicationPayReq req = (ApplicationPayReq) parmRequest;
		ApplicationPayResp resp = new ApplicationPayResp();
		try {
			if(	RegexValidate.isNull(req.getAppNo())) {
				resp.setResultCode(ResultCodes.STU_UPDATE_APPNO_NOTNULL);
			}else{
				ConnManager.beginTransaction();
				
				String status = query.queryHINSAppStatus(req.getAppNo());
				if(!status.equals("")){
					resp.setStatus(status);
					resp.setResultCode(ResultCodes.NORMAL);
				}else{
					resp.setStatus(status);
					resp.setResultCode(ResultCodes.NO_RESULT);
				}
			}
			
		} catch (Exception e) {
			ConnManager.rollback();
		}finally {
			ConnManager.closeConn();
		}
		return resp;
	}


	
}
